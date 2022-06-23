package named

def areAlphaEq(a: Term, b: Term): Boolean = (a, b) match
  case (Var(x), Var(y)) => x == y
  case (Apl(m1, n1), Apl(m2, n2)) => areAlphaEq(m1, m2) && areAlphaEq(n1, n2)
  case (λ(x, m), λ(y, n)) =>
    if x == y then areAlphaEq(m, n)
    else
      val z = newVar(vars(m), vars(n))
      areAlphaEq(naiveSub(m, x, z), naiveSub(n, y, z))
  case _ => false
