# Dotty Records

## Extensible Derivation Rules

Extensibility of a record type `R` with a field with label `l` and type `V` is determined by a recursive case analysis on the type tree of `R`. The following rules apply:

- If an instance of `Extensible[R,"l",V]` already exists in scope, extension is allowed.
Note that this rule is not as circular as it may seem;
Synthesis is only triggered provided that no such instance was found in scope in the first place. It merely serves as a useful base case for deriving extensiblity of {\it sub parts} of the type tree of R.

- A `TypeRef` is a reference to some other {\it underlying} type, and the `TypeRef` can be extended if the underlying type can be extended.

- A `TermRef` is the singleton type of a term `r` such as `r.type`. Such a type can be {\it widened} to the more general type of `r`, and the `TermRef` is extensible if the widened type is extensible.

- The expression `this.type` is typed as `ThisType` and can be extended if the underlying type of `this` can be extended.

- A lazily evaluated expression that returns a value of type `T` is assigned an `ExprType` which is extensible if `T` is extensible.

- A `TypeBound` denotes an abstract type that is restricted to be a subtype of the upper bound and a supertype of the lower bound. The lower bound makes a worst case scenario for records extension as it denotes the maximal number of fields the record type might contain and the bounded type is therefore extensible only if the lower bound is extensible.

- An `AndType(A, B)` denotes the intersection between types `A` and `B`. Since the resulting type will contain the fields from both `A` and `B`, extension is allowed only if both `A` and `B` are themselves extensible.

- An `OrType(A, B)` denotes the union between types `A` and `B`. Extension is allowed only if extensibility can be proved for both cases `A` and `B`.

- A `RefinedType` denotes a refinement of a parent type `P` with a member `n` of type `U`. Extension is allowed if the parent can be extended and `n` is different from `l` or `V <: U`.

- A `ClassInfo` denotes a class type, and extension is only allowed if `l` is not already a member.

- An `AppliedType` is the instantiation of a polymorphic type and extension is only allowed if `l` is not already a member.

- A `ConstantType` is the type of a constant literal, and extension is only allowed if `l` is not already a member.

- In all other cases extension is rejected.

(A complete diagram over Dotty types can be found at <https://dotty.epfl.ch/docs/internals/type-system.html>)