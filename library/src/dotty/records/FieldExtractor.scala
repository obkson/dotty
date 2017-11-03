package dotty.records

trait FieldExtractor[R <: Record] {
  type L <: String
  type V <: Any
  def headOption(r: R): Option[(L, V)]
}

object FieldExtractor {
  def apply[R <: Record, L0 <: String, V0 <: Any](label: L0): FieldExtractor[R] = new FieldExtractor[R] {
    type L = L0
    type V = V0
    def headOption(r: R): Option[(L, V)] = r.get(label)
  }
}