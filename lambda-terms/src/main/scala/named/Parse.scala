package named

// string must be in this grammar:
// L = x | (LL) | (λxL)
// where x is single character
def parseStringHelper(str: String): (Term, String) =
  assert(!str.isEmpty)
  if str.head != '(' then (Var(str.head), str.tail)
  else
    val token = str.tail
    token.head match
      case 'λ' =>
        val x = token.tail.head
        val (p, rest) = parseStringHelper(token.tail.tail)
        assert(!rest.isEmpty && rest.head == ')')
        val rest2 = rest.tail
        (λ(Var(x), p), rest2)
      case _ =>
        val (m, rest) = parseStringHelper(token)
        val (n, rest2) = parseStringHelper(rest)
        assert(!rest2.isEmpty && rest2.head == ')')
        val rest3 = rest2.tail
        (Apl(m, n), rest3)

def parseString(str: String): Term =
  val (term, rest) = parseStringHelper(str)
  term
