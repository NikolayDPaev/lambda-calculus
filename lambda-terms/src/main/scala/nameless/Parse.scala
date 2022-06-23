package nameless

// string must be in this grammar:
// L = x | (LL) | (λL)
// where x is integer
def parseStringHelper(str: String): (Term, String) =
  if str.head != '(' then (Var(str.head - '0'), str.tail)
  else
    val token = str.tail
    token.head match
      case 'λ' =>
        val (p, rest) = parseStringHelper(token.tail)
        assert(rest.head == ')')
        val rest2 = rest.tail
        (λ(p), rest2)
      case _ =>
        val (m, rest) = parseStringHelper(token)
        val (n, rest2) = parseStringHelper(rest)
        assert(rest2.head == ')')
        val rest3 = rest2.tail
        (Apl(m, n), rest3)

def parseString(str: String): Term =
  val (term, rest) = parseStringHelper(str)
  term
