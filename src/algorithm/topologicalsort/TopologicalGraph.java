package algorithm.topologicalsort;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * TopologicalSort deals with directed acyclic graph (DAG)
 */
public class TopologicalGraph {
    private final int numNodes;

    private final int[] indegrees;

    /**
     * Map<from, to>
     */
    private final Map<Integer, Set<Integer>> directionMap;

    /**
     * 2 dimension array [[v1,v2]...] meaning there is a directed connection from v2 to v1
     */
    public TopologicalGraph(
            final int numNodes,
            final int[][] directions
    ) {
        this.numNodes = numNodes;
        this.indegrees = new int[numNodes];

        final Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] direction: directions) {
            final Set<Integer> toSet = map.getOrDefault(direction[1], new HashSet<>());
            toSet.add(direction[0]);

            map.put(direction[1], toSet);
            indegrees[direction[0]]++;
        }
        this.directionMap = map;
    }

    public int[] getOrder() {
        final int[] res = new int[numNodes];
        final Queue<Integer> processingQ = new LinkedList<>();

        // fill processing queue with initial node with 0 indegree
        for(int i=0; i<numNodes; i++) {
            if (indegrees[i]==0) {
                processingQ.add(i);
            }
        }

        int index = 0;
        while(!processingQ.isEmpty()) {
            final int curr = processingQ.poll();
            res[index++] = curr;

            final Set<Integer> toSet = directionMap.getOrDefault(curr, Collections.emptySet());
            toSet.forEach(toVertex -> {
                indegrees[toVertex]--;
                if (indegrees[toVertex] == 0) {
                    processingQ.add(toVertex);
                }
            });
        }

        // check if there is untouched nodes
        for (int i = 0; i<numNodes; i++) {
            if (indegrees[i] != 0) {
                return new int[] {}; // if so, there is no topological solution
            }
        }

        return res;
    }
}
