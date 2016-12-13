(ns game.core)

(def total-of-lifes 6)
(def secret-word "clojure")

(defn lost [] (print "You lost the game"))
(defn won [] (print "You won the game"))

(defn letters-missing [word successful]
  (remove (fn [letter] (contains? successful (str letter))) word))

(defn successful-full-word? [word successful]
  (empty? (letters-missing word successful)))

(defn letters-missing [word successful]
  (remove (fn [letter] (contains? successful (str letter))) word)  )

(defn read-letter! []
  (read-line))


(defn hit-the-word? [guess word]
  (.contains word guess))

(defn print-game [lifes word successful]
  (println "Lifes -> " lifes)
  (doseq [letter (seq word)]
    (if (contains? successful (str letter))
      (print letter " ") 
      (print "_" " ")))
    (println))

(defn game [lifes word successful]
  (print-game lifes word successful) 
  (cond
    (= lifes 0) (lost)
    (successful-full-word? word successful) (won)
      :else 
      (let [guess (read-letter!)]
          (if (hit-the-word? guess word)
              (do
                (println "Hit is correct!")
                (recur lifes word (conj successful guess)))
              (do
                (println "Hit is wrong!")
              (recur (dec lifes) word successful))))))

(defn start-the-game [] 
  (game total-of-lifes secret-word #{}))

(defn -main [& args]
  (start-the-game))














