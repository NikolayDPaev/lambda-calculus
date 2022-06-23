import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class NamelessTest extends AnyFlatSpec with Matchers:

  "parseString" should "should parse a nameless lambda term" in {
    nameless.parseString("(λ(1(λ1)))") shouldBe nameless.λ(nameless.Apl(1, nameless.λ(1)))
    nameless.parseString("(0(λ((01)(λ((01)2)))))") shouldBe
      nameless.Apl(0, nameless.λ(nameless.Apl(nameless.Apl(0, 1), nameless.λ(nameless.Apl(nameless.Apl(0, 1), 2)))))
  }

  "sub" should "correctly substitute" in {
    nameless.sub(nameless.parseString("(0(λ((01)(λ((01)2)))))"), 0, nameless.parseString("(2(λ((01)3)))")) shouldBe
      nameless.parseString("((2(λ((01)3)))(λ((0(3(λ((02)4))))(λ((01)(4(λ((03)5))))))))")
  }
