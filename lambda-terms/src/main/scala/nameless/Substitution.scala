package nameless

def translate(t: Term, d: Int, c: Int = 0): Term =
  t match
    case Var(k) =>
      if k < c then
        k
      else
        k + d
    case Apl(m1, m2) =>
      Apl(translate(m1, d, c), translate(m2, d, c))
    case λ(p) =>
      λ(translate(p, d, c+1))      
        
def sub(m: Term, k: Int, n: Term): Term =
  m match
    case Var(i) =>
      if k == i then n
      else i
    case Apl(m1, m2) =>
      Apl(sub(m1, k, n), sub(m2, k, n))
    case λ(m) =>
      λ(sub(m, k+1, translate(n, 1)))
