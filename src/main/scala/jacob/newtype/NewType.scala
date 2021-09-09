package jacob
package newtype

import scala.compiletime.erasedValue
import java.util.UUID

object NewType:
  class GenNewType[A]:
    opaque type Type = A
    def apply(a: A): Type = a
    extension (a: Type) def value: A = a
    given (using CanEqual[A, A]): CanEqual[Type, Type] = CanEqual.derived

  class NewTypeBool extends GenNewType[Boolean]:
    override opaque type Type = Boolean
    override def apply(a: Boolean): Type = a
    extension (a: Type) override def value: Boolean = a

  class NewTypeDouble extends GenNewType[Double]:
    override opaque type Type = Double
    override def apply(a: Double): Type = a
    extension (a: Type) override def value: Double = a

  class NewTypeInt extends GenNewType[Int]:
    override opaque type Type = Int
    override def apply(a: Int): Type = a
    extension (a: Type) override def value: Int = a

  class NewTypeString extends GenNewType[String]:
    override opaque type Type = String
    override def apply(a: String): Type = a
    extension (a: Type) override def value: String = a

  class NewTypeUuid extends GenNewType[UUID]:
    override opaque type Type = UUID
    override def apply(a: UUID): Type = a
    def randomUuid: Type = UUID.randomUUID()
    extension (a: Type) override def value: UUID = a

  transparent inline def of[A] = inline erasedValue[A] match
    case _: Boolean => new NewTypeBool
    case _: Double  => new NewTypeDouble
    case _: Int     => new NewTypeInt
    case _: String  => new NewTypeString
    case _: UUID    => new NewTypeUuid
    case _          => new GenNewType[A]

  type of[A] = A match
    case Boolean => NewTypeBool
    case Double => NewTypeDouble
    case Int => NewTypeInt
    case String => NewTypeString
    case UUID => NewTypeUuid
    case _ => GenNewType[A]

object NewSubtype:
  class GenNewSubtype[A]:
    opaque type Type <: A = A
    def apply(a: A): Type = a
    extension (a: Type) def value: A = a
    given (using CanEqual[A, A]): CanEqual[Type, Type] = CanEqual.derived

  class NewSubtypeBool extends GenNewSubtype[Boolean]:
    override opaque type Type <: Boolean = Boolean
    override def apply(a: Boolean): Type = a
    extension (a: Type) override def value: Boolean = a

  class NewSubtypeDouble extends GenNewSubtype[Double]:
    override opaque type Type <: Double = Double
    override def apply(a: Double): Type = a
    extension (a: Type) override def value: Double = a

  class NewSubtypeInt extends GenNewSubtype[Int]:
    override opaque type Type <: Int = Int
    override def apply(a: Int): Type = a
    extension (a: Type) override def value: Int = a

  class NewSubtypeString extends GenNewSubtype[String]:
    override opaque type Type <: String = String
    override def apply(a: String): Type = a
    extension (a: Type) override def value: String = a

  class NewSubtypeUuid extends GenNewSubtype[UUID]:
    override opaque type Type <: UUID = UUID
    override def apply(a: UUID): Type = a
    def randomUuid: Type = UUID.randomUUID()
    extension (a: Type) override def value: UUID = a

  transparent inline def of[A] = inline erasedValue[A] match
    case _: Boolean => new NewSubtypeBool
    case _: Double  => new NewSubtypeDouble
    case _: Int     => new NewSubtypeInt
    case _: String  => new NewSubtypeString
    case _: UUID    => new NewSubtypeUuid
    case _          => new GenNewSubtype[A]

  type of[A] = A match
    case Boolean => NewSubtypeBool
    case Double => NewSubtypeDouble
    case Int => NewSubtypeInt
    case String => NewSubtypeString
    case UUID => NewSubtypeUuid
    case _ => GenNewSubtype[A]
