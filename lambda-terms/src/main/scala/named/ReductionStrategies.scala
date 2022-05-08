package named
import scala.languageFeature.implicitConversions
import scala.annotation.tailrec

implicit def TermToSome(t: Term): Some[Term] = Some(t)

def normalReduction(m: Term): Option[Term] = m match
  case λ(x, n) => normalReduction(n).map(y => λ(x, y))// if n is reducible then λ(x, nR(n)) else None (because λ(x, n) will also be non reducible)
  case Apl(λ(x,p), q) => currySub(p, x, q)
  case Apl(p, q) =>
    val reducedP = normalReduction(p)
    reducedP match
      case Some(t) => Apl(t, q)
      case None => normalReduction(q).map(y => Apl(p, y))// if nR(q) is term then Apl(p, nR(q)) else None (because neither p nor q are reducible and p is not λ(x,p))
  case _ => None

def applicativeReduction(m: Term): Option[Term] = m match
  case λ(x, n) => applicativeReduction(n).map(y => λ(x, y))
  case Apl(p, q) =>
    val reducedP = applicativeReduction(p)
    lazy val reducedQ = applicativeReduction(q)//the AR(q) will not be calculated if not needed
    if reducedP.isDefined then Apl(reducedP.get, q)
    else if reducedQ.isDefined then Apl(p, reducedQ.get)
    else p match
      case λ(x,p) => currySub(p, x, q)
      case _ => None
  case _ => None

@tailrec
def fullReduction(strategy: Term => Option[Term])(m: Term): Term =
  strategy(m) match
    case Some(t) => fullReduction(strategy)(t)
    case None => m
