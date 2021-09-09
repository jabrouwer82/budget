package jacob
package model

import cats.*

import scala.deriving.Mirror
import scala.compiletime.erasedValue

trait BaseModelFactory[A[_[_]], F[_]]:
  import ModelFactory.*
  def apply(using Mirror.ProductOf[A[F]]) = ModelFactory.deriveApply[A, F]

trait ModelFactory[A[_[_]], F[_]](using Applicative[F]) extends BaseModelFactory[A, F]:
  def of(using Mirror.ProductOf[A[F]], Mirror.ProductOf[A[Id]]) = ModelFactory.deriveApplicativeApply[A, F]

extension [A[_[_]] <: Product](d: A[Id])(using AId: Mirror.ProductOf[A[Id]])
  def lift[G[_]](using G: Applicative[G], AG: Mirror.ProductOf[A[G]]): A[G] =
    (ModelFactory.deriveApplicativeApply[A, G]).apply(Tuple.fromProductTyped(d))

object ModelFactory:
  transparent inline def deriveApply[A[_[_]], F[_]](using AF: Mirror.ProductOf[A[F]]): AF.MirroredElemTypes => A[F] =
    (args: AF.MirroredElemTypes) => AF.fromProduct(args)

  transparent inline def deriveApplicativeApply[A[_[_]], F[_]](
    using AId: Mirror.ProductOf[A[Id]],
    AF: Mirror.ProductOf[A[F]],
    F: Applicative[F],
  ): AId.MirroredElemTypes => A[F] =
    (args: AId.MirroredElemTypes) => AF.fromProduct(args.map([A] => (arg: A) => F.pure(arg)))

  inline def deriveEmpty[A[_[_]], F[_]](using AF: Mirror.ProductOf[A[F]], M: MonoidK[F]): A[F] =
    AF.fromProduct(deriveEmptyTuple[AF.MirroredElemTypes, F])

  transparent inline def deriveEmptyTuple[Mets <: Tuple, F[_]](using F: MonoidK[F]): Tuple =
    inline erasedValue[Mets] match
      case EmptyTuple => EmptyTuple
      case _: (head *: tail) => F.empty[head] *: deriveEmptyTuple[tail, F]
