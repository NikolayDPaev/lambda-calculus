// nestedλ counts the number of abstractions over the current term
// bVToλMap maps bounded variable to its abstraction, for id of the abstraction is used
// the current value of nestedλ (+ 1 since we count the current abstraction also)
def deleteNames(
  t: named.Term,
  context: Vector[named.Var],
  nestedλ: Int = 0,
  bVtoλMap: Map[named.Var, Int] = Map()
): nameless.Term =
  t match
    case named.Var(x) =>
      if bVtoλMap.contains(x) then
        // if the variable is bounded then its index is the number of abstractions between its occurence and the abstraction where it is bounded
        nameless.Var(nestedλ - bVtoλMap(x))
      else
        // if not then its index is its de Bruijn index + the number of abstractions
        nameless.Var(context.indexOf(named.Var(x)) + nestedλ)
    case named.Apl(m, n) =>
      nameless.Apl(deleteNames(m, context, nestedλ, bVtoλMap), deleteNames(n, context, nestedλ, bVtoλMap))
    case named.λ(x, p) =>
      // increments the number of abstractions and add the bounded variable to the map
      nameless.λ(deleteNames(p, context, nestedλ + 1, bVtoλMap + (x -> (nestedλ + 1))))

// returns a new var different than the vars in the context and the previously bounded vars
def newVar(context: Vector[named.Var], boundedVars: Map[Int, named.Var]): named.Var =
  val varsList = "xyzutvwabcdef"
  varsList.filter(c => !context.contains(named.Var(c)) && !boundedVars.values.toList.contains(named.Var(c))).head

// nestedλ counts the number of abstractions over the current term
// boundedVars maps the abstraction to the name of its bouunded variable
// abstractions are identified by their degree of nesting(starting from 1)
def addNames(
  t: nameless.Term,
  context: Vector[named.Var],
  nestedλ: Int = 0,
  boundedVars: Map[Int, named.Var] = Map()
): named.Term =
  t match
    case nameless.Var(n) =>
      if n >= nestedλ then
        // if the variable is larger than the abstractions count then it is free
        named.Var(context(n - nestedλ))
      else
        // else its name is the name that is index of its abstraction
        named.Var(boundedVars(nestedλ - n))
    case nameless.Apl(m, n) =>
      named.Apl(addNames(m, context, nestedλ, boundedVars), addNames(n, context, nestedλ, boundedVars))
    case nameless.λ(p) =>
      val variable = newVar(context, boundedVars)
      // increments the number of abstractions and add the new bounded variable name to the map
      named.λ(variable, addNames(p, context, nestedλ + 1, boundedVars + (nestedλ + 1 -> variable)))
