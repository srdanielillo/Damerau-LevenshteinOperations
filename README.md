# Damerau-LevenshteinOperations
Java method to get one optimal path (Minimum steps needed) to transform one string into another. 

# Output
    -sXY     -> swap        X by Y (If X == Y then cost of the operation is zero)
    -iX      -> insert      X
    -bX      -> delete      X
    -wXYUV   -> transpose   XY
  
# Examples
    -Input:      {"3356711", "3365711"}
    -Outpout:    s33s33w5665s77s11s11
  
    -Input:      {"121", "11"}
    -Outpout:    s11b2s11

# Notes
    -The method generates the distance matrix using the Damerau-Levenshtein method.
    -Thanks to this it can calculate the minimum edit distance between two different strings of same/different length.
    -Any suggestion would be listened, contribute to this and create a strong java edit distance library!!.
