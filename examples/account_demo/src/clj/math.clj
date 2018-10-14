(ns math)

(defn compute
  []
  (Math/pow (Math/PI) 2))

(defn -main
  [times]
  (time
    (apply + (repeatedly times compute))))

