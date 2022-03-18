package nameless

def Translate(t: Term, d: Int, c: Int = 0): Term =
  t match
    case Var(k) =>
      if k < c then
        k
      else
        k + d
    case Abs(m1, m2) =>
      Abs(Translate(m1, d, c), Translate(m2, d, c))
    case λ(p) =>
      λ(Translate(p, d, c+1))      
        
def Sub(m: Term, k: Int, n: Term): Term =
  m match
    case Var(i) =>
      if k == i then n
      else i
    case Abs(m1, m2) =>
      Abs(Sub(m1, k, n), Sub(m2, k, n))
    case λ(m) =>
      λ(Sub(m, k+1, Translate(n, 1)))
