import scala.languageFeature.implicitConversions

trait Term()
object Term:
  implicit def StringToTerm(s: String): Term = Var(s)

case class Var(x: String) extends Term:
  override def toString(): String = x
  override def hashCode(): Int = x.##
  override def equals(obj: Any): Boolean = obj match
  case that: Var => x == that.x
  case _ => false
object Var:
  implicit def StringToVar(s: String): Var = Var(s)
  implicit def VarToString(v: Var): String = v.x

case class Abs(m: Term, n: Term) extends Term:
  override def toString(): String = s"(${m}${n})"

case class λ(x: Var, p:Term) extends Term:
  override def toString(): String = s"(λ${x}${p})"
