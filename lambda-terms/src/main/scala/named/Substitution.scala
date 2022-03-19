package named

def fV(t: Term): Set[Var] = t match
  case Var(x) => Set(x)
  case Apl(m, n) => fV(m).union(fV(n))
  case λ(x, p) => fV(p).excl(x)

def newVar(p: Term, n: Term): Var = 
  val varsList = "xyzwabcdef"
  val freeVars = fV(p).union(fV(n))

  varsList.filter((c) => !freeVars.contains(c)).head

def currySub(m: Term, x: Var, n: Term): Term = m match
  case Var(y) =>
    if x == Var(y) then n
    else y
  case Apl(m1, m2) => Apl(currySub(m1, x, n), currySub(m2, x, n))
  case λ(y, p) => 
    if x == y then λ(x, p)
    else if (!fV(p).contains(x) || !fV(n).contains(y)) then λ(y, currySub(p, x, n))
    else
      val z = newVar(p, n)
      λ(z, currySub(currySub(p, y, z), x, n))
