Algorithm Complexity Analysis Report          
This project implements and benchmarks several classic algorithms:            
          
Sorting: MergeSort, QuickSort          
          
Selection: Deterministic Select (Median of Medians)          
        
Geometric: Closest Pair of Points (Divide & Conquer)          
        
The benchmarks track:          
      
Runtime              
Comparisons        
Allocations        
Recursion depth          

2. Analysis of Running-Time Recurrences

2.1 MergeSort
Recurrence: T(n) = 2*T(n/2) + O(n)
Master Theorem: Case 2 (a = 2, b = 2, f(n) = Θ(n))
Complexity: O(n log n)
Space: O(n) due to auxiliary buffer
Observations: recursion depth grows logarithmically with n

2.2 QuickSort
Recurrence (average): T(n) = T(k) + T(n-k-1) + O(n)
Master Theorem: Case depends on pivot choice
Balanced pivot → O(n log n)
Worst-case (sorted input) → O(n^2)
Tail recursion optimization reduces stack depth

2.3 Deterministic Select (Median of Medians)
Recurrence: T(n) = T(n/5) + T(7n/10) + O(n)
Akra-Bazzi intuition:
Partition guarantees at least 30%-70% split
Linear recursion solution → O(n) worst-case
Depth grows logarithmically relative to n/5 recursions

2.4 Closest Pair of Points
Recurrence: T(n) = 2*T(n/2) + O(n) + strip processing
Master Theorem: Case 2 (a = 2, b = 2, f(n) = O(n))
Complexity: O(n log n)
Observations: strip check limited to 7 points ensures linear merge


Akra-Bazzi Approach
General Framework
For recurrences: T(n) = Σ(i=1 to k) a_i × T(n/b_i) + f(n)
The Method

Find critical exponent p: Solve Σ(i=1 to k) a_i/b_i^p = 1
Apply formula: T(n) = Θ(n^p × (1 + ∫[1 to n] f(u)/(u^(p+1)) du))

DeterministicSelect Application
Step 1: Find p
For T(n) = T(n/5) + T(7n/10) + Θ(n):
1/5^p + 1/(10/7)^p = 1
1/5^p + (7/10)^p = 1

Step 2: Since p < 1
When p < 1, the integral term becomes O(1), so:  T(n) = Θ(n^p × constant) = Θ(n^0.69) ≈ Θ(n)


| Algorithm           | Time Complexity | Comparison Growth | Memory Usage           |
|--------------------|----------------|-----------------|-----------------------|
| MergeSort           | Θ(n log n)     | ~n log n        | Θ(n) auxiliary buffer |
| QuickSort           | Θ(n log n) avg | ~n log n avg    | Θ(log n) recursion    |
| DeterministicSelect | Θ(n)           | ~n linear       | Θ(log n) recursion    |

Conclusions
Theoretical Insights

Master Theorem provides elegant analysis for standard divide-and-conquer
Case classification predicts which algorithms scale better
Akra-Bazzi extends analysis to complex multi-way recursions
DeterministicSelect achieves guaranteed linear time through careful design

Practical Applications

Performance prediction: Theory guides expected runtime scaling
Algorithm selection: Complexity analysis informs practical choices
Optimization opportunities: Understanding bottlenecks enables improvements
Validation framework: Theory provides benchmarks for implementation quality

Implementation Validation
Our experimental framework should confirm:

MergeSort: Consistent Θ(n log n) across all inputs
QuickSort: Average Θ(n log n), worst Θ(n²) for pathological cases
DeterministicSelect: Guaranteed Θ(n) regardless of input distribution

Future Research Directions

Constant factor optimization: Reduce hidden constants in implementations
Hybrid algorithms: Combine benefits of different approaches
Cache-aware analysis: Extend theoretical model to include memory hierarchy
Parallel extensions: Adapt recurrence analysis to multi-core scenarios
