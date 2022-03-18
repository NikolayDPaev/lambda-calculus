package named

// string must be in this grammar:
// L = x | (LL) | (λxL)
// where x is single character
def parseString(str: String): (Term, String) = 
  if str.head != '(' then (Var(str.head), str.tail)
  else
    val token = str.tail
    token.head match
      case 'λ' =>
        val x = token.tail.head
        val (p, rest) = parseString(token.tail.tail)
        assert(rest.head == ')')
        val rest2 = rest.tail
        (λ(Var(x),p), rest2)
      case _ =>
        val (m, rest) = parseString(token)
        val (n, rest2) = parseString(rest)
        assert(rest2.head == ')')
        val rest3 = rest2.tail
        (Abs(m, n), rest3)   
          
def ParseString(str: String): Term = 
  val (term, rest) = parseString(str)
  term
