(add-pvar-name "A" (make-arity))
(add-pvar-name "B" (make-arity))
(add-pvar-name "C" (make-arity))

(add-var-name "x" (py "alpha"))
(add-var-name "y" (py "alpha"))
(add-var-name "f"(py "alpha=>beta"))

(add-pvar-name "p"(make-arity(py "alpha")))


;; deMorgan
;; ¬(A ∧ B) ↔ ¬A ∨ ¬B
;; =>
(set-goal (pf "(A & B -> F) -> (A -> F) ord (B -> F)"))
(assume "u")
(use "Stab")
(assume "v")
(use "u")
(split)

(use "Stab")
(assume "w")
(use "v")
(intro 0)
(use "w")

(use "Stab")
(assume "w")
(use "v")
(intro 1)
(use "w")

;; <=
(set-goal (pf "(A -> F) ord (B -> F) -> (A & B -> F)"))
(assume "u")
(assume "v")
(elim "u")

(assume "w")
(use "w")
(use "v")

(assume "w")
(use "w")
(use "v")

;; ¬(A ∨ B) ↔ ¬A ∧ ¬B
;; =>
(set-goal (pf "(A ord B -> F) -> (A -> F) & (B -> F)"))
(assume "u")
(split)

(assume "v")
(use "u")
(intro 0)
(use "v")

(assume "v")
(use "u")
(intro 1)
(use "v")

;; <=
(set-goal (pf "(A -> F) & (B -> F) -> (A ord B -> F)"))
(assume "u" "v")
(elim "v")

(use "u")

(use "u")


;; ¬∀xA ↔ ∃x¬A
;; =>
(set-goal (pf "(all x p x -> F) -> ex x (p x -> F)"))
(assume "u")
(use "Stab")
(assume "v")
(use "u")
(assume "x")
(use "Stab")
(assume "w")
(use "v")
(ex-intro (pt "x"))
(use "w")

;; <=
(set-goal (pf "ex x (p x -> F) -> (all x p x -> F)"))
(assume "u")
(assume "v")
(ex-elim "u")
(assume "x")
(assume "w")
(use "w")
(use "v")

;; ¬∃xA ↔ ∀x¬A
;; =>
(set-goal (pf "(ex x p x -> F) -> all x (p x -> F)"))
(assume "u")
(assume "x")
(assume "v")
(use "u")
(ex-intro (pt "x"))
(use "v")

;; <=
(set-goal (pf "all x (p x -> F) -> ex x p x -> F"))
(assume "u" "v")
(ex-elim "v")
(assume "x" "w")
(use-with "u" (pt "x") "w")
