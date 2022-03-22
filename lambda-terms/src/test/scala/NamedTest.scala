import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class NamedTest extends AnyFlatSpec with Matchers:

  "parseString" should "should parse a lambda term" in {
    named.parseString("(λy(x(λxy)))") shouldBe named.λ('y', named.Apl('x', named.λ('x','y')))
    named.parseString("(y(λx((xy)(λy((ux)y)))))") shouldBe 
      named.Apl('y', named.λ('x', named.Apl(named.Apl('x', 'y'), named.λ('y', named.Apl(named.Apl('u', 'x'), 'y')))))
  }

  "currySub" should "correctly substitute" in {
    named.currySub(named.parseString("(y(λx((xy)(λy((ux)y)))))"), 'y', named.parseString("(z(λv((vy)z)))")) shouldBe 
      named.parseString("((z(λv((vy)z)))(λx((x(z(λv((vy)z))))(λy((ux)y)))))")
  }
