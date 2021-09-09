package jacob
package model

import jacob.newtype.*

val Account = NewType.of[String]
type Account = Account.Type

