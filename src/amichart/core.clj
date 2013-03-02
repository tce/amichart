(ns amichart.core
  (:use [incanter core charts io]))

(defn readAMIData
"This function reads in a ami text data file and returns its data"
  [filename]
        (read-dataset filename
        :header true
    :delim \t
    :skip 2))


(defn filterNaNs
  "This filters the data in the given column to exclude NaNs"
  [colname data]
  (filter #(not (Double/isNaN %1)) (colname data))
  )

(defn showVar [colname]
        (let [ filteredCol (filterNaNs colname)
                         chart (line-chart (range 0 (length filteredCol)) filteredCol)
         ]
     (set-y-label chart (str colname ))
     (set-x-label chart "Time")
     (set-title chart (str "Graph for " colname))
     (view chart)
     chart))


 (def fn1 "/Users/teskridge/Desktop/RacingVestAgent_0_6809628031594_2013-02-15-11-32-45-BrandsHatch-Jelley-Morning3-yellow.txt")
 (def fn2 "/Users/teskridge/Desktop/RacingVestAgent_0_6738308482405_2013-02-15-11-20-51-BrandsHatch-Jelley-Morning3-yellow.txt")

;;(def d1 (readAMIData fn1))
(def d2 (readAMIData fn2))
