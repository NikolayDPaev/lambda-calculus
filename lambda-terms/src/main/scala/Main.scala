@main
def hello: Unit = 
  // val t = Abs("x", λ("x", "y"))
  // val t2 = λ("y", Abs("x", λ("x", "y")))
  // println(t)
  // val t3 = CurrySub(t, "y", t2)
  // println(t2)
  // val t4 = CurrySub(t3, "x", t3)
  // println(NewVar(t, t2))
  
  // val (term, rest) = named.ParseString("(λy(x(λxy)))")
  // println(term)

  // (y(λx xyz)z)[y -> zy]
  println(named.CurrySub(named.ParseString("((y(λx((xy)z)))z)"), 'y', named.ParseString("(zy)")))
  // y(λx xy(λuuxy))[y -> z(λv vyz)]
  println(named.CurrySub(named.ParseString("(y(λx((xy)(λu((ux)y)))))"), 'y', named.ParseString("(z(λv((vy)z)))")))
  // 0(λ01(λ012))[0 -> 2(λ013)]
  println(nameless.Sub(nameless.Abs(0, nameless.λ(nameless.Abs(nameless.Abs(0,1), nameless.λ(nameless.Abs(nameless.Abs(0, 1), 2))))), 0, nameless.Abs(2, nameless.λ(nameless.Abs(nameless.Abs(0, 1), 3)))))
  println(nameless.Sub(nameless.ParseString("(0(λ((01)(λ((01)2)))))"), 0, nameless.ParseString("(2(λ((01)3)))")))
