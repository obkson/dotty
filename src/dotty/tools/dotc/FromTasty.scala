/* dotc
 * Copyright 2005-2015 LAMP/EPFL
 * @author  Martin Odersky
 */
package dotty.tools
package dotc

import core._
import Contexts._
import Symbols._
import SymDenotations._
import typer.FrontEnd
import Phases.Phase
import util._
import Decorators._
import dotty.tools.dotc.transform.Pickler
import pickling.DottyUnpickler

/** Compiler for TASTY files.
 *  Usage:
 *
 *    scala dotty.tools.dotc.FromTasty (option | classname)*
 *
 *  Options are as for dotc.
 *  Classnames are fully qualified names of top-level classes that need to have a TASTY attribute.
 *  Example:
 *
 *    scala dotty.tools.dotc.FromTasty -Xprint:front extMethods.T
 */
object FromTasty extends Driver {
  override def newCompiler(): Compiler = new TASTYCompiler

  class TASTYCompiler extends Compiler {

    override def phases: List[List[Phase]] = {
      val backendPhases = super.phases.dropWhile {
        case List(_: Pickler) => false
        case _ => true
      }.tail
      List(new ReadTastyTreesFromClasses) :: backendPhases
    }

    override def newRun(implicit ctx: Context): Run = {
      reset()
      new TASTYRun(this)(rootContext)
    }
  }

  class TASTYRun(comp: Compiler)(implicit ctx: Context) extends Run(comp) {
    override def compile(classNames: List[String]) = {
      units = classNames.map(new TASTYCompilationUnit(_))
      compileUnits()
    }
  }

  class TASTYCompilationUnit(val className: String) extends CompilationUnit(NoSource) {
    override def toString = s"class file $className"
  }

  class ReadTastyTreesFromClasses extends FrontEnd {
    override def runOn(units: List[CompilationUnit])(implicit ctx: Context): List[CompilationUnit] =
      units.map(readTASTY)

    def readTASTY(unit: CompilationUnit)(implicit ctx: Context): CompilationUnit = unit match {
      case unit: TASTYCompilationUnit =>
        val className = unit.className.toTypeName
        val clsd =
          if (className.contains('.')) ctx.base.staticRef(className)
          else defn.EmptyPackageClass.info.decl(className)
        def cannotUnpickle(reason: String) = {
          ctx.error(s"class $className cannot be unpickled because $reason")
          unit
        }
        clsd match {
          case clsd: ClassDenotation =>
            clsd.infoOrCompleter match {
              case info: ClassfileLoader =>
                info.load(clsd) match {
                  case Some(unpickler: DottyUnpickler) =>
                    val (List(unpickled), source) = unpickler.body(readPositions = true)
                    val unit1 = new CompilationUnit(source)
                    unit1.tpdTree = unpickled
                    unit1
                  case _ =>
                    cannotUnpickle(s"its class file ${info.classfile} does not have a TASTY attribute")
                }
              case info =>
                cannotUnpickle(s"its info of type ${info.getClass} is not a ClassfileLoader")
            }
          case _ =>
            ctx.error(s"class not found: $className")
            unit
        }
    }
  }
}
