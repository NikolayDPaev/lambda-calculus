import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class TranslationTest extends AnyFlatSpec with Matchers:
  val context = Vector[named.Var](named.Var('y'), named.Var('x'), named.Var('z'))

  "delete names" should "translate the named term to nameless" in {
    deleteNames(named.parseString("(y(λx((xy)(λu((ux)y)))))"), context) shouldBe nameless.parseString(
      "(0(λ((01)(λ((01)2)))))"
    )
  }

  "delete names" should "translate the nameless term to named" in {
    named.areAlphaEq(
      addNames(nameless.parseString("(0(λ((01)(λ((01)2)))))"), context),
      named.parseString("(y(λx((xy)(λu((ux)y)))))")
    ) shouldBe true
  }
