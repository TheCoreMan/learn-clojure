(ns learn-clojure
  (:gen-class))

;; 1) Define a function greet that takes no arguments and prints "Hello". Replace the ___ with the implementation: (defn greet [] _)
;; NOTE: I changed the function name from greet to greetq1 to avoid clashes between different questions
(defn greetq1 [] (println "Hello"))
;; Testing in REPL:
;; learn-clojure> (greetq1)
;; Hello
;; nil

;; 2) Redefine greet using def, first with the fn special form and then with the #() reader macro.
;;
;; ;; using fn
;; (def greet __)
(def greetq2a (fn [] (println "Hello")))
;; Testing in REPL:
;; learn-clojure> (greetq2a)
;; Hello
;; nil

;; ;; using #()
;; (def greet __)
(def greetq2b #(println "Hello"))
;; Testing in REPL:
;; learn-clojure> (greetq2b)
;; Hello
;; nil

;; 3) Define a function greeting which:
;; Given no arguments, returns "Hello, World!"
;; Given one argument x, returns "Hello, x!"
;; Given two arguments x and y, returns "x, y!"
;; Hint use the str function to concatenate strings
;; (doc str)
;; NOTE: Here's what this returns:
;; learn-clojure> (doc str)
;; -------------------------
;; clojure.core/str
;; ([] [x] [x & ys])
;; With no args, returns the empty string. With one arg x, returns
;; x.toString().  (str nil) returns the empty string. With more than
;; one arg, returns the concatenation of the str values of the args.

(defn greeting-q3
  ([] (greeting-q3 "World"))
  ([x] (greeting-q3 "Hello" x))
  ([x y] (str x ", " y "!")))

;; For testing
;; NOTE: This is cool! A new way to test instead of just manually running things in the repl.
(assert (= "Hello, World!" (greeting-q3)))
(assert (= "Hello, Clojure!" (greeting-q3 "Clojure")))
(assert (= "Good morning, Clojure!" (greeting-q3 "Good morning" "Clojure")))

;; 4) Define a function do-nothing which takes a single argument x and returns it, unchanged.

(defn do-nothing [x] x)

;; NOTE: added some tests:
(assert (= 4 (do-nothing 4)))
(assert (= "asdf" (do-nothing "asdf")))

;; In Clojure, this is the identity function. By itself, identity is not very useful, but it is sometimes necessary when working with higher-order functions.
;; (source identity)

;; NOTE: I was surprised that `(defn do-nothing [x] (x))` doesn't work.
;; After all, why this: `(defn do-nothing [x] x)`
;; But not this: `(defn do-nothing [x] (x))`
;; What I didn't realize was that I thought that `(x)` is an expression that would just evaluate to `x`, but it's not!
;; `(f x)` is a list sintactically, and a function invokation semantically. The first position in the list is the thing to invoke (in the function position). So `(f)` means INVOCATION of f's VALUE (which normally is a function).
;; `(x)` means INVOCATION of `x`'s VALUE, but `x`'s value is non-invokable!

;; 5) Define a function always-thing which takes any number of arguments, ignores all of them, and returns the number 100.

(defn always-thing [& ignoring-these-args] 100)
;; NOTE: How this looks in the REPL:
;; learn-clojure> (always-thing "asdf")
;; 100
;; NOTE: added some tests:
(assert (= 100 (always-thing)))
(assert (= 100 (always-thing 100)))
(assert (= 100 (always-thing "asdf" "zxcv")))

;; 6) Define a function make-thingy which takes a single argument x. It should return another function, which takes any number of arguments and always returns x.
(defn make-thingy [x] (fn [& args] x))

;; Tests
(let [n (rand-int Integer/MAX_VALUE)
      f (make-thingy n)]
  (assert (= n (f)))
  (assert (= n (f 123)))
  (assert (= n (apply f 123 (range)))))

;; In Clojure, this is the constantly function.
;; (source constantly)
;; NOTE: I didn't understand why this function was useful, so I [looked it up here](https://stackoverflow.com/a/4018731/4119906)

;; 7) Define a function triplicate which takes another function and calls it three times, without any arguments.

(defn triplicate [f] (f) (f) (f))
;; NOTE: Wrote this function to test
(defn test-triplicate [] (println "Called from triplicate"))
;; NOTE: In REPL, it looked like this:
;; learn-clojure> (triplicate test-triplicate)
;; Called from triplicate
;; Called from triplicate
;; Called from triplicate
;; nil

;; 8) Define a function opposite which takes a single argument f. It should return another function which takes any number of arguments, applies f on them, and then calls not on the result. The not function in Clojure does logical negation.

(defn opposite [f]
  (fn [& args] (not (f args))))
;; NOTE: For testing, defined this function:
(defn always-true [& args] true)
;; NOTE: Testing in REPL:
;; learn-clojure> (always-true 1 2 3)
;; true
;; learn-clojure> (opposite always-true)
;; #function[learn-clojure/opposite/fn--7457]
;; learn-clojure> ((opposite always-true))
;; false

;; 9) Define a function triplicate2 which takes another function and any number of arguments, then calls that function three times on those arguments. Re-use the function you defined in the earlier triplicate exercise.

(defn triplicate2-with-hashtag [f & args]
  (triplicate #(apply f args)))
;; NOTE: For testing:
(defn test-triplicate2 [x y & zs] (println "x: " x " | y: " y " | zs: " zs))
;; NOTE: Testing in REPL
;; learn-clojure> (test-triplicate2 "we've had one, yes" "but what about second breakfast?" "What about elevenses?" "Luncheon?" "Afternoon tea?" "Dinner?" "Supper?")
;; x:  we've had one, yes  | y:  but what about second breakfast?  | zs:  (What about elevenses? Luncheon? Afternoon tea? Dinner? Supper?)
;; nil
;; learn-clojure> (triplicate2-with-hashtag test-triplicate2 "xxx" "yyy" "z1" "z2")
;; x:  xxx  | y:  yyy  | zs:  (z1 z2)
;; x:  xxx  | y:  yyy  | zs:  (z1 z2)
;; x:  xxx  | y:  yyy  | zs:  (z1 z2)
;; nil
;; learn-clojure> (triplicate2-with-hashtag test-triplicate2 "xxx" "yyy")
;; x:  xxx  | y:  yyy  | zs:  nil
;; x:  xxx  | y:  yyy  | zs:  nil
;; x:  xxx  | y:  yyy  | zs:  nil
;; nil
;; NOTE: Why doesn't the fn syntax work?
(defn triplicate2-with-fn [f & args]
  (triplicate (fn [] (apply f args))))
;; learn-clojure> (triplicate2-with-fn test-triplicate2 "xxx" "yyy" "z1" "z2")
;; ("xxx" "yyy" "z1" "z2")

;; NOTE: Testing with asserts instead

(def ^:dynamic twome 2)
(binding [twome twome]
  (defn test-triplicate3 [x] (set! twome (* x 2)) twome)

  (assert (= (triplicate2-with-fn test-triplicate3 twome) 16) (str "expected 16, got " twome))
  )


