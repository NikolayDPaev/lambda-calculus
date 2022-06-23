import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class NamedTest extends AnyFlatSpec with Matchers:

  "parseString" should "should parse a lambda term" in {
    named.parseString("(λy(x(λxy)))") shouldBe named.λ('y', named.Apl('x', named.λ('x', 'y')))
    named.parseString("(y(λx((xy)(λy((ux)y)))))") shouldBe
      named.Apl('y', named.λ('x', named.Apl(named.Apl('x', 'y'), named.λ('y', named.Apl(named.Apl('u', 'x'), 'y')))))
  }

  "areAlphaEq" should "check for alpha equivalence" in {
    val t = named.parseString("(y(λx((xy)(λy((ux)y)))))")
    named.areAlphaEq(t, t) shouldBe true

    val t1 = named.parseString("(y(λt((ty)(λx((ut)x)))))")
    named.areAlphaEq(t, t1) shouldBe true

    val t2 = named.parseString("(λy(λx(xy)))")
    val t3 = named.parseString("(λx(λy(yx)))")
    named.areAlphaEq(t2, t3) shouldBe true
    named.areAlphaEq(t, t2) shouldBe false

    val t4 = named.parseString("(λx(λyx))")
    val t5 = named.parseString("(λx(λyy))")
    named.areAlphaEq(t4, t5) shouldBe false

  }

  "currySub" should "correctly substitute" in {
    named.currySub(named.parseString("(y(λx((xy)(λy((ux)y)))))"), 'y', named.parseString("(z(λv((vy)z)))")) shouldBe
      named.parseString("((z(λv((vy)z)))(λx((x(z(λv((vy)z))))(λy((ux)y)))))")
  }
