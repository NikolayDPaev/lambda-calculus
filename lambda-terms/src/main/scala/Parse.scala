def ParseString(str: String): (Term, String) = 
  if str.head != '(' then (Var(s"${str.head}"), str.tail)
  else
    val token = str.tail
    token.head match
      case 'λ' =>
        val x = token.tail.head
        val (p, rest) = ParseString(token.tail.tail)
        assert(rest.head == ')')
        val rest2 = rest.tail
        (λ(Var(s"${x}"),p), rest2)
      case _ =>
        val (m, rest) = ParseString(token)
        val (n, rest2) = ParseString(rest)
        assert(rest2.head == ')')
        val rest3 = rest2.tail
        (Abs(m, n), rest3)   
          
