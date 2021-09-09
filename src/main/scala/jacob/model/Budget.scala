package jacob
package model

import jacob.newtype.*

import cats.*

import java.time.*
import java.util.*

// Higher-kinded Budget type representable in arbitrary F.
final case class BudgetK[F[_]](
  name: F[String],
  month: F[YearMonth],
  amount: F[Dollars],
)

// Budget containing all fields, the canonical representation of a Budget.
type Budget = BudgetK[Id]
object Budget extends BaseModelFactory[BudgetK, Id]

// Budget containing optional fields, intended to be used to keep incremental state.
type BudgetUpdate = BudgetK[Option]
object BudgetUpdate extends ModelFactory[BudgetK, Option]:
  val Empty: BudgetUpdate = ModelFactory.deriveEmpty[BudgetK, Option]

// Just a UUID to id them.
type BudgetId = BudgetId.Type
val BudgetId = NewType.of[UUID]

// Budget with its UUID attached.
type BudgetWithId[F[_]] = BudgetWithId.DataWithId[F]
object BudgetWithId extends DataWithIdFactory[BudgetK, BudgetId]

