(add-pvar-name "A" (make-arity))
(add-pvar-name "B" (make-arity))
(add-pvar-name "C" (make-arity))

(add-var-name "x" (py "alpha"))
(add-var-name "y" (py "alpha"))
(add-var-name "f"(py "alpha=>beta"))

(add-pvar-name "p"(make-arity(py "alpha")))

;; A ∨˜ B ↔ A ∨ B
;; =>
(set-goal (pf "((A -> F) -> (B -> F) -> F) -> A ord B"))
(assume "u")
(use "Stab")
(assume "v")
(use "u")

(assume "w")
(use "v")
(intro 0)
(use "w")

(assume "t")
(use "v")
(intro 1)
(use "t")

;; <=
(set-goal (pf "A ord B -> ((A -> F) -> (B -> F) -> F)"))
(assume "u" "v" "w")
(elim "u")

(use "v")
(use "w")

;; A ∧˜ B ↔ A ∧ B
;; =>
(set-goal (pf "((A -> B -> F) -> F) -> A & B"))
(assume "u")
(split)

(use "Stab")
(assume "v")
(use "u")
(assume "w" "t")
(use "v")
(use "w")

(use "Stab")
(assume "v")
(use "u")
(assume "w" "t")
(use "v")
(use "t")

;; shorter alternative
(set-goal (pf "((A -> B -> F) -> F) -> A & B"))
(assume "u")
(use "Stab")
(assume "v")
(use "u")
(assume "w" "t")
(use "v")
(split)

(use "w")
(use "t")

;; <=
(set-goal (pf "A & B -> (A -> B -> F) -> F"))
(assume "u" "v")
(use "v")

(use "u")
(use "u")

;; ∃˜xA ↔ ∃xA
;; =>
(set-goal (pf "((all x (p x -> F)) -> F) -> ex x p x"))
(assume "u")
(use "Stab")
(assume "v")
(use "u")

(assume "x")
(assume "w")
(use "v")

(ex-intro (pt "x"))
(use "w")

;; <=
(set-goal (pf "ex x p x -> (all x (p x -> F)) -> F"))
(assume "u" "v")
(ex-elim "u")
(use "v")
