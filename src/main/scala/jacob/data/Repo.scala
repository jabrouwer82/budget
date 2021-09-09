package jacob
package data

import jacob.model.JError

import scala.util.*


trait Repo[A[_[_]], F[_]](using val storage: Storage[A]):
  def getAll(): F[Either[JError, List[storage.DataWithId]]]
  def getChar(id: storage.IdType): F[Either[JError, Option[storage.DataType]]]
  def createChar(a: storage.DataType): F[Either[JError, storage.IdType]]
  def updateChar(id: storage.IdType, a: storage.DataUpdate): F[Either[JError, Unit]]
  def deleteChar(id: storage.IdType): F[Either[JError, Unit]]
