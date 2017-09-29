(ns tester
  (:import [tgt TestThread]))

(def ITERS 10000000)

(defn benchmark [iters afn]
  (time
   (dotimes [_ iters]
     (afn))))

(defn run-tgt-test []
  (let [runner (fn []
                  (benchmark ITERS
                    (fn []
                      (let [^TestThread t (Thread/currentThread)]
                        (.global t)
                        ))))]
    (.start (TestThread. runner "a"))
    ))

(defn run-thread-local-test []
  (let [tl (ThreadLocal.)]
    (.set tl "a")
    (benchmark ITERS
      (fn []
        (.get tl)
        ))))

(defn run-field-test []
  (let [field2 "a"]
    (benchmark ITERS
      (fn []
        field2
        ))))
