package jacob
package model

import jacob.newtype.*

import cats.*
import cats.kernel.*
import cats.implicits.*

import java.time.*
import java.util.*


final case class TxnK[F[_]](
  account: F[Account],
  date: F[LocalDate],
  amount: F[Dollars],
  payee: F[String],
  note: F[String],
  category: F[Category],
  cleared: F[Boolean],
  ord: F[Int] = 0,
)


type Txn = TxnK[Id]
object Txn extends BaseModelFactory[TxnK, Id]

type TxnUpdate = TxnK[Option]
object TxnUpdate extends ModelFactory[TxnK, Option]

type TxnId = TxnId.Type
val TxnId = NewType.of[UUID]


type TxnWithId[F[_]] = TxnWithId.DataWithId[F]
object TxnWithId extends DataWithIdFactory[TxnK, TxnId]
