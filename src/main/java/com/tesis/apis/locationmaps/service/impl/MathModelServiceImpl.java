
package com.tesis.apis.locationmaps.service.impl;

import com.tesis.apis.locationmaps.service.MathModelService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MathModelServiceImpl implements MathModelService {

    @Override
    public List<Integer> resolveTspModel(Integer[][] model) {
        
       int N, start = 0;
       N = model.length; 
       Integer minTourCost = Integer.MAX_VALUE;
       List<Integer> tour = new ArrayList<>();
       if (N <= 2) throw new IllegalStateException("N <= 2 not yet supported.");
       if (N != model[0].length) throw new IllegalStateException("Matrix must be square (n x n)");
          final int END_STATE = (1 << N) - 1;
          
        Integer[][] memo = new Integer[N][1 << N];

        // Add all outgoing edges from the starting node to memo table.
        for (int end = 0; end < N; end++) {
        if (end == start) continue;
            memo[end][(1 << start) | (1 << end)] = model[start][end];
        }

        for (int r = 3; r <= N; r++) {
            for (int subset : combinations(r, N)) {
                if (notIn(start, subset)) continue;
                for (int next = 0; next < N; next++) {
                    if (next == start || notIn(next, subset)) continue;
                    int subsetWithoutNext = subset ^ (1 << next);
                    Integer minDist = Integer.MAX_VALUE;
                    for (int end = 0; end < N; end++) {
                        if (end == start || end == next || notIn(end, subset)) continue;
                        Integer newDistance = memo[end][subsetWithoutNext] + model[end][next];
                        if (newDistance < minDist) {
                            minDist = newDistance;
                        }
                    }
                    memo[next][subset] = minDist;
                }
            }
        }
                // Connect tour back to starting node and minimize cost.
        for (int i = 0; i < N; i++) {
            if (i == start) continue;
            Integer tourCost = memo[i][END_STATE] + model[i][start];
            if (tourCost < minTourCost) {
                    minTourCost = tourCost;
            }
        }

                int lastIndex = start;
                int state = END_STATE;
                tour.add(start);

                // Reconstruct TSP path from memo table.
                for (int i = 1; i < N; i++) {
      
                    int index = -1;
                    for (int j = 0; j < N; j++) {
                        if (j == start || notIn(j, state)) continue;
                        if (index == -1) index = j;
                        Integer prevDist = memo[index][state] + model[index][lastIndex];
                        Integer newDist  = memo[j][state] + model[j][lastIndex];
                        if (newDist < prevDist) {
                                    index = j;
                        }
                    }

                    tour.add(index);
                    state = state ^ (1 << index);
                    lastIndex = index;
                }

        tour.add(start);
        Collections.reverse(tour);
        return tour;
  }

  private static boolean notIn(int elem, int subset) {
    return ((1 << elem) & subset) == 0;
  }

  // This method generates all bit sets of size n where r bits 
  // are set to one. The result is returned as a list of integer masks.
  public static List<Integer> combinations(int r, int n) {
    List<Integer> subsets = new ArrayList<>();
    combinations(0, 0, r, n, subsets);
    return subsets;
  }

  // To find all the combinations of size r we need to recurse until we have
  // selected r elements (aka r = 0), otherwise if r != 0 then we still need to select
  // an element which is found after the position of our last selected element
  private static void combinations(int set, int at, int r, int n, List<Integer> subsets)
  {
    
    // Return early if there are more elements left to select than what is available.
    int elementsLeftToPick = n - at;
    if (elementsLeftToPick < r) return;

    // We selected 'r' elements so we found a valid subset!
    if (r == 0) {
      subsets.add(set);
    } else {
      for (int i = at; i < n; i++) {
        // Try including this element
        set |= 1 << i;

        combinations(set, i + 1, r - 1, n, subsets);

        // Backtrack and try the instance where we did not include this element
        set &= ~(1 << i);
      }
    }  
  }
}
