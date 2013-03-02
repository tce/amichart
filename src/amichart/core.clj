(ns amichart.core
  (:use [incanter core charts io]))

(defn readAMIData
  "This function reads in a ami text data file and returns its data.

   Lessons Learned:
   1. The correct delimiter is \tab rather than \t
  "
  [filename]
        (read-dataset filename
        :header true
    :delim \tab
    :skip 2))


(defn filterNaNs
  "This filters the data in the given column to exclude NaNs

  Lessons Learned:
  1. For some reason, the line (colname data) does not return the column of data, but nil
     Of course, it's ($ colname data)
  "
  [colname data]
  (filter #(not (Double/isNaN %1)) ($ colname data))
  )

(defn showVar
  "This funciton takes one column of data and graphs it"
  [colname data]
  (let [ filteredCol (filterNaNs colname data )
         chart (line-chart (range 0 (length filteredCol)) filteredCol)
         ]
     (set-y-label chart (str colname ))
     (set-x-label chart "Time")
     (set-title chart (str "Graph for " colname))
     (view chart)
     chart))

(defn colRange
  "This function return a range from 0 to the length of the list passed in"
  [columnValues]
  (range 0 (length columnValues)))

(defn addDataColumn
  "This function adds a column of data to a line chart"
  [chart columnValues]
  (println (str "Adding " columnValues))
  (add-categories chart (colRange columnValues) columnValues))

(defn showVars
  "This function takes a list of column names and graphs them"
  [namelist data]
  (let [filteredColumns (map #(filterNaNs % data) namelist )
        chart (line-chart (colRange (first filteredColumns)) (first filteredColumns))]
    (map #(addDataColumn chart  %) (rest filteredColumns))
    (set-y-label chart (str namelist ))
     (set-x-label chart "Time")
     (set-title chart (str "Graph for " namelist))
     (view chart)
     chart))


;;  Vars for testing

 (def fn1 "/Users/teskridge/Desktop/RacingVestAgent_0_6809628031594_2013-02-15-11-32-45-BrandsHatch-Jelley-Morning3-yellow.txt")
 (def fn2 "/Users/teskridge/Desktop/RacingVestAgent_0_6738308482405_2013-02-15-11-20-51-BrandsHatch-Jelley-Morning3-yellow.txt")

;;(def d1 (readAMIData fn1))
(def d2 (readAMIData fn2))
(:column-names d2)
