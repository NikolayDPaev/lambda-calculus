@main
def hello: Unit = 
  // val t = Abs("x", λ("x", "y"))
  // val t2 = λ("y", Abs("x", λ("x", "y")))
  // println(t)
  // val t3 = CurrySub(t, "y", t2)
  // println(t2)
  // val t4 = CurrySub(t3, "x", t3)
  // println(NewVar(t, t2))
  
  val (term, rest) = ParseString("(λy(x(λxy)))")
  println(term)
