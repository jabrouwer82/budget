package jacob
package data

import cats.*

trait Storage[A[_[_]]]:
  type IdType
  type DataType = A[Id]
  type DataUpdate = A[Option]
  type DataWithId = (IdType, DataType)

object Storage:
  def apply[A[_[_]]](using storage: Storage[A]): Storage[A] = storage
