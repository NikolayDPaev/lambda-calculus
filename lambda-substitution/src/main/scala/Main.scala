def FV(t: Term): Set[Var] = t match
  case Var(x) => Set(x)
  case Abs(m, n) => FV(m).union(FV(n))
  case λ(x, p) => FV(p).excl(x)

def NewVar(p: Term, n: Term): Var = 
  val str = "xyzwabcdef"
  val varsList = str.map((c) => s"${c}")
  val freeVars = FV(p).union(FV(n))

  varsList.filter((c) => !freeVars.contains(c)).head

def CurrySub(m: Term, x: Var, n: Term): Term = m match
  case Var(y) =>
    if x == Var(y) then n
    else y
  case Abs(m1, m2) => Abs(CurrySub(m1, x, n), CurrySub(m2, x, n))
  case λ(y, p) => 
    if x == y then λ(x, p)
    else if (!FV(p).contains(x) || !FV(n).contains(y)) then λ(y, CurrySub(p, x, n))
    else
      val z = NewVar(p, n)
      λ(z, CurrySub(CurrySub(p, y, z), x, n))

@main
def hello: Unit = 
  val t = Abs("x", λ("x", "y"))
  val t2 = λ("y", Abs("x", λ("x", "y")))
  println(t)
  val t3 = CurrySub(t, "y", t2)
  println(t2)
  val t4 = CurrySub(t3, "x", t3)
  println(NewVar(t, t2))
