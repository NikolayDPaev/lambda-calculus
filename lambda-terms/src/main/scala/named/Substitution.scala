package named

def vars(t: Term): Set[Var] = t match
  case Var(x) => Set(x)
  case Apl(m, n) => freeVars(m).union(freeVars(n))
  case λ(x, p) => freeVars(p)

def freeVars(t: Term): Set[Var] = t match
  case Var(x) => Set(x)
  case Apl(m, n) => freeVars(m).union(freeVars(n))
  case λ(x, p) => freeVars(p).excl(x)

def naiveSub(m: Term, x: Var, n: Term): Term = m match
  case Var(y) =>
    if x == Var(y) then n
    else y
  case Apl(m1, m2) => Apl(naiveSub(m1, x, n), naiveSub(m2, x, n))
  case λ(y, p) => λ(y, naiveSub(p, x, n))

def newVar(u: Set[Var], v: Set[Var]): Var =
  val varsList = "xyzwabcdef"
  val union = u.union(v)
  varsList.filter(c => !union.contains(c)).head

def currySub(m: Term, x: Var, n: Term): Term = m match
  case Var(y) =>
    if x == Var(y) then n
    else y
  case Apl(m1, m2) => Apl(currySub(m1, x, n), currySub(m2, x, n))
  case λ(y, p) =>
    if x == y then λ(x, p)
    else if !freeVars(p).contains(x) || !freeVars(n).contains(y) then λ(y, currySub(p, x, n))
    else
      val z = newVar(freeVars(p), freeVars(n))
      λ(z, currySub(currySub(p, y, z), x, n))
