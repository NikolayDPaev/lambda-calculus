(add-pvar-name "A" (make-arity))
(add-pvar-name "B" (make-arity))
(add-pvar-name "C" (make-arity))

(add-var-name "x" (py "alpha"))
(add-var-name "y" (py "alpha"))
(add-var-name "f"(py "alpha=>beta"))

(add-pvar-name "p"(make-arity(py "alpha")))

;; Hilbert axioms

;; (A → B → C) → (A → B) → A → C
(set-goal (pf "(A -> B -> C) -> (A -> B) -> A -> C"))
(assume "u" "v" "w")
(use "u")
(use "w")
(use "v")
(use "w")

;; A → B → A
(set-goal (pf "A -> B -> A"))
(assume "u" "v")
(use "u")

;; A ∧ B → A, A ∧ B → B
(set-goal (pf "A & B -> A"))
(assume "u")
(use "u")

(set-goal (pf "A & B -> B"))
(assume "u")
(use "u")

;; A → B → A ∧ B
(set-goal (pf "A -> B -> A & B"))
(assume "u" "v")
(split)
(use "u")
(use "v")

;; A → A ∨ B, B → A ∨ B
(set-goal (pf "A -> A ord B"))
(assume "u")
(intro 0)
(use "u")

(set-goal (pf "B -> A ord B"))
(assume "u")
(intro 1)
(use "u")

;; (A → C) → (B → C) → A ∨ B → C
(set-goal (pf "(A -> C) -> (B -> C) -> A ord B -> C"))
(assume "u" "v" "w")
(elim "w")
(use "u")
(use "v")

;; ∀x (B → A) → (B → ∀xA), ако x ∈/ FV(B)
(set-goal (pf "all x (B -> p x) -> (B -> all x p x)"))
(assume "u")
(assume "v")
(assume "x")
(use "u")
(use "v")

;; ∀x (A → B) → (∃xA → B), ако x ∈/ FV(B)
(set-goal (pf "all x (p x -> B) -> (ex x p x -> B)"))
(assume "u")
(assume "v")
(ex-elim "v")
(use "u")
