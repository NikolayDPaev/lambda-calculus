import scala.runtime.stdLibPatches.language.experimental.namedTypeArguments
@main
def hello: Unit = 
  // val t = Apl("x", λ("x", "y"))
  // val t2 = λ("y", Apl("x", λ("x", "y")))
  // println(t)
  // val t3 = currySub(t, "y", t2)
  // println(t2)
  // val t4 = currySub(t3, "x", t3)
  // println(NewVar(t, t2))
  
  // val (term, rest) = named.parseString("(λy(x(λxy)))")
  // println(term)

  // (y(λx xyz)z)[y -> zy]
  println(named.currySub(named.parseString("((y(λx((xy)z)))z)"), 'y', named.parseString("(zy)")))
  // y(λx xy(λuuxy))[y -> z(λv vyz)]
  println(named.currySub(named.parseString("(y(λx((xy)(λu((ux)y)))))"), 'y', named.parseString("(z(λv((vy)z)))")))
  // 0(λ01(λ012))[0 -> 2(λ013)]
  println(nameless.sub(nameless.Apl(0, nameless.λ(nameless.Apl(nameless.Apl(0,1), nameless.λ(nameless.Apl(nameless.Apl(0, 1), 2))))), 0, nameless.Apl(2, nameless.λ(nameless.Apl(nameless.Apl(0, 1), 3)))))
  println(nameless.sub(nameless.parseString("(0(λ((01)(λ((01)2)))))"), 0, nameless.parseString("(2(λ((01)3)))")))

  println(deleteNames(named.parseString("(λx((xy)((λu((zu)(λv(vy))))z)))"), Vector(named.Var('y'), named.Var('x'), named.Var('z'))))
  println(addNames(nameless.parseString("(0(λ((01)(λ((01)2)))))"), Vector(named.Var('x'), named.Var('y'))))

  val withNames = addNames(nameless.parseString("(0(λ((01)(λ((01)2)))))"), Vector(named.Var('x'), named.Var('y')))
  println(deleteNames(withNames, Vector(named.Var('x'), named.Var('y'))))

  val withoutNames = deleteNames(named.parseString("(λx((xy)((λu((zu)(λv(vy))))z)))"), Vector(named.Var('y'), named.Var('x'), named.Var('z')))
  println(addNames(withoutNames, Vector(named.Var('y'), named.Var('x'), named.Var('z'))))

  val m = nameless.parseString("(0(λ((01)(λ((01)2)))))")
  val x = 0
  val n = nameless.parseString("(2(λ((01)3)))")  
  val mSubn = nameless.sub(m, x, n)

  val m1 = addNames(m, Vector(named.Var('y'), named.Var('x'), named.Var('z')))
  val n1 = addNames(n, Vector(named.Var('y'), named.Var('x'), named.Var('z')))
  val m1Subn1 = named.currySub(m1, 'y', n1)
  
  println(mSubn)
  println(deleteNames(m1Subn1, Vector(named.Var('y'), named.Var('x'), named.Var('z'))))

  println(deleteNames(named.parseString("(y(λx((xy)(λu((ux)y)))))"), Vector(named.Var('y'), named.Var('x'), named.Var('z'))))

  println(named.areAlphaEq(named.parseString("(λy(λx(xy)))"), named.parseString("(λx(λy(yx)))")))
