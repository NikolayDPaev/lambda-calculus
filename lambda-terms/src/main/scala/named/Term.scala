package named
import scala.languageFeature.implicitConversions

trait Term
object Term:
  implicit def charToTerm(c: Char): Term = Var(c)

case class Var(x: Char) extends Term:
  override def toString(): String = s"$x"
  override def hashCode(): Int = x.##
  override def equals(obj: Any): Boolean = obj match
  case that: Var => x == that.x
  case _ => false

object Var:
  implicit def charToVar(c: Char): Var = Var(c)
  implicit def varToChar(v: Var): Char = v.x

case class Apl(m: Term, n: Term) extends Term:
  override def toString(): String = s"(${m}${n})"

case class λ(x: Var, p:Term) extends Term:
  override def toString(): String = s"(λ${x}${p})"
