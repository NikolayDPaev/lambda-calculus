@main
def main(): Unit = 

  println("((y(λx((xy)z)))z)[y -> (zy)]")
  println(named.currySub(named.parseString("((y(λx((xy)z)))z)"), 'y', named.parseString("(zy)")))
  println()
 
  println("(y(λx((xy)(λu((ux)y)))))[y -> z(λv vyz)")
  println(named.currySub(named.parseString("(y(λx((xy)(λu((ux)y)))))"), 'y', named.parseString("(z(λv((vy)z)))")))
  println()

  println("0(λ01(λ012))[0 -> 2(λ013)]")
  println(nameless.sub(nameless.parseString("(0(λ((01)(λ((01)2)))))"), 0, nameless.parseString("(2(λ((01)3)))")))
  println()

  println("(λx((xy)((λu((zu)(λv(vy))))z))) to nameless")
  println(deleteNames(named.parseString("(λx((xy)((λu((zu)(λv(vy))))z)))"), Vector(named.Var('y'), named.Var('x'), named.Var('z'))))
  println()

  println("(0(λ((01)(λ((01)2))))) to named")
  println(addNames(nameless.parseString("(0(λ((01)(λ((01)2)))))"), Vector(named.Var('x'), named.Var('y'))))
  println()

  println("(0(λ((01)(λ((01)2))))) -> named -> nameless")
  val withNames = addNames(nameless.parseString("(0(λ((01)(λ((01)2)))))"), Vector(named.Var('x'), named.Var('y')))
  println(deleteNames(withNames, Vector(named.Var('x'), named.Var('y'))))
  println()

  println("(λx((xy)((λu((zu)(λv(vy))))z))) -> nameless -> named")
  val withoutNames = deleteNames(named.parseString("(λx((xy)((λu((zu)(λv(vy))))z)))"), Vector(named.Var('y'), named.Var('x'), named.Var('z')))
  println(addNames(withoutNames, Vector(named.Var('y'), named.Var('x'), named.Var('z'))))
  println()

  // sub of nameless should be equal to nameless -> named -> curry sub -> nameless
  val m = nameless.parseString("(0(λ((01)(λ((01)2)))))")
  val x = 0
  val n = nameless.parseString("(2(λ((01)3)))")
  val mSubn = nameless.sub(m, x, n)
  
  val m1 = addNames(m, Vector(named.Var('y'), named.Var('x'), named.Var('z')))
  val n1 = addNames(n, Vector(named.Var('y'), named.Var('x'), named.Var('z')))
  val m1Subn1 = named.currySub(m1, 'y', n1)
  
  println("(0(λ((01)(λ((01)2)))))[0 -> (2(λ((01)3)))]")
  println(mSubn)
  println()

  println("but through: add names -> curry sub -> delete names")
  println(deleteNames(m1Subn1, Vector(named.Var('y'), named.Var('x'), named.Var('z'))))
  println()

  println("(λy(λx(xy))) and (λx(λy(yx))) should be alpha equivalent")
  println(named.areAlphaEq(named.parseString("(λy(λx(xy)))"), named.parseString("(λx(λy(yx)))")))


  println(named.fullReduction(named.normalReduction)(named.parseString("((λxx)(λyy))")))
  println(named.fullReduction(named.normalReduction)(named.parseString("((λx(λyy))((λzz)(λaa)))")))

  println(named.fullReduction(named.applicativeReduction)(named.parseString("((λxx)(λyy))")))
  println(named.fullReduction(named.applicativeReduction)(named.parseString("((λx(λyy))((λzz)(λaa)))")))
