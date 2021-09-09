package jacob
package model

trait DataWithIdFactory[DataType[_[_]], IdType]:
  export DataWithId.apply

  case class DataWithId[F[_]](
    id: IdType,
    data: DataType[F],
  )

