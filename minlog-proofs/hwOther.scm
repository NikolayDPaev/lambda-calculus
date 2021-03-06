(add-pvar-name "A" (make-arity))
(add-pvar-name "B" (make-arity))
(add-pvar-name "C" (make-arity))

(add-var-name "x" (py "alpha"))
(add-var-name "y" (py "alpha"))
(add-var-name "f"(py "alpha=>beta"))

(add-pvar-name "p"(make-arity(py "alpha")))

;; c ((A → B) → A) → A
(set-goal (pf "((A -> B) -> A) -> A"))
(assume "u")
(use "Stab")
(assume "v")
(use "v")
(use "u")
(assume "w")
(use "Stab")
(assume "t")
(use "v")
(use "w")
(cdp)

;; i (¬¬A → ¬¬B) → ¬¬(A → B)
(set-goal (pf "(((A -> F) -> F) -> ((B -> F) -> F)) -> ((A -> B) -> F) -> F"))
(assume "u" "v")
(use "u")
(assume "w")
(use "v")
(assume "t")

(use "Efq")
(use "w")
(use "t")

(assume "t")
(use "v")
(assume "r")
(use "t")


;; c (A → ∃xB) → ∃x (A → B), ако x ∈/ FV(A)
; (set-goal (pf "(A -> ex x p x) -> ex y (A -> p y)"))
; (assume "u")
; (use "Stab")
; (assume "v")
; (use "v")
; (ex-intro (pt "y"))
; (use "Stab")
; (assume "w")
; (use "w")
; (use "Stab")
; (assume "t")
; (use "t")
; (assume "r")
; (use "Stab")
; (assume "i")


;; c ((A → B) → C) → (A → C) → C
(set-goal (pf "((A -> B) -> C) -> (A -> C) -> C"))
(assume "u")
(assume "v")
(use "Stab")
(assume "w")
(use "w")
(use "u")
(assume "t")
(use "Stab")
(assume "r")
(use "w")
(use "u")
(use "Stab")
(assume "e")
(use "w")
(use "v")
(use "t")

;; c (A → B) ∨ (B → A)
(set-goal (pf "(A -> B) ord (B -> A)"))
(use "Stab")
(assume "u")
(use "u")
(intro 0)
(assume "v")
(use "Stab")
(assume "w")
(use "u")
(intro 1)
(assume "t")
(use "v")
