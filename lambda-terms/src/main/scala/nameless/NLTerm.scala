package nameless

trait Term
object Term:
  implicit def IntToTerm(n: Int): Term = Var(n)

case class Var(n: Int) extends Term:
  override def toString(): String = n.toString
  override def hashCode(): Int = n.##
  override def equals(obj: Any): Boolean = obj match
  case that: Var => n == that.n
  case _ => false
object Var:
  implicit def IntToVar(n: Int): Var = Var(n)
  implicit def VarToInt(v: Var): Int = v.n

case class Abs(m: Term, n: Term) extends Term:
  override def toString(): String = s"(${m}${n})"

case class λ(p:Term) extends Term:
  override def toString(): String = s"(λ${p})"
