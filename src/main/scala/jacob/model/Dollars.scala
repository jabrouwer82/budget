package jacob
package model

import cats.*
import jacob.newtype.*

object Dollars extends NewSubtype.of[Int] {
  def of(amount: Double): Dollars = Dollars(math.round(amount * 100).toInt)

  val formatter = java.text.NumberFormat.getCurrencyInstance

  given Show[Dollars] =
    Show.show(formatter.format)
}

type Dollars = Dollars.Type
