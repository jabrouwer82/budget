package jacob

import cats.*
import cats.implicits.*
import jacob.model.*

@main def main = {
  val t = Dollars(5)
  println(t.show)
  println(Dollars(t + 5).show)
}
