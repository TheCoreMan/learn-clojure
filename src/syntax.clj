(ns learn-clojure
  (:gen-class))

(defn learning-syntax
  []
  (println "Starting with https://clojure.org/guides/learn/syntax#_test_your_knowledge")

  (println "1. Using the REPL, compute the sum of 7654 and 1234.")
  ; Instead of directly using a repl, I typed all this out in a file and used `, e` to evaluate and write as a comment.
  (+ 7654 1234)  ; spacemacs printed "=> 8888"

  (println "Rewrite the following algebraic expression as a Clojure expression: ( 7 + 3 * 4 + 5 ) / 10.")
  (/ (+ 7 (* 3 4) 5) 10)  ; 12 / 5

  (println "Using REPL documentation functions, find the documentation for the rem and mod functions. Compare the results of the provided expressions based on the documentation.")
                                        ; Can't really do this here, I'll just write what I did
                                        ; To open the REPL in Spacemacs: `, s a` calls cider-switch-to-repl-buffer
                                        ; Then, I loaded the REPL functions using (require '[clojure.repl :refer :all])
                                        ; Then, to see the docs for rem and mod, all I did was:
  ;; learn-clojure> (doc rem)
  ;; -------------------------
  ;; clojure.core/rem
  ;; ([num div])
  ;; remainder of dividing numerator by denominator.
  ;; nil
  ;; learn-clojure> (doc mod)
  ;; -------------------------
  ;; clojure.core/mod
  ;; ([num div])
  ;; Modulus of num and div. Truncates toward negative infinity.
  ;; nil
                                        ; Then I played around a little more, trying rem and mod myself and looking at their source:
  ;; learn-clojure> (source rem)
  ;; (defn rem
  ;;   "remainder of dividing numerator by denominator."
  ;;   {:added "1.0"
  ;;    :static true
  ;;    :inline (fn [x y] `(. clojure.lang.Numbers (remainder ~x ~y)))}
  ;;   [num div]
  ;;   (. clojure.lang.Numbers (remainder num div)))
  ;; nil
  ;; learn-clojure> (source mod)
  ;; (defn mod
  ;;   "Modulus of num and div. Truncates toward negative infinity."
  ;;   {:added "1.0"
  ;;    :static true}
  ;;   [num div]
  ;;   (let [m (rem num div)]
  ;;     (if (or (zero? m) (= (pos? num) (pos? div)))
  ;;       m
  ;;       (+ m div))))
  ;; nil

  (println "Using find-doc, find the function that prints the stack trace of the most recent REPL exception.")
  ;; learn-clojure> (find-doc "stack trace")
  ;; -------------------------
  ;; clojure.stacktrace/e
  ;; ([])
  ;; REPL utility.  Prints a brief stack trace for the root cause of the
  ;; most recent exception.
  )
