package algo.graph;

import ds.graph.Edge;
import ds.graph.Vertex;
import ds.graph.WeightedGraph;

import java.util.TreeSet;

/**
 * Created by sherxon on 1/7/17.
 */
/**
* This is the algorithm to find shortest Path in weighted and non-negative edged graph. Graph can be directed
* or undirected. This is not optimized version as shortestPath() method searches vertex with minimal weight
* every time. To optimize fibonacci heap can be used. This algorithm finds shortest path from source vertex
* to all other reachable vertexes. Time complexity is O(VE)
* */
public class Dijsktra<V, E extends Number> {
    WeightedGraph<V, E> graph;

    public Dijsktra(WeightedGraph<V, E> graph) {
        this.graph = graph;
    }

    public void shortestPath(V a) {
        Vertex<V> source=graph.getVertex(a);
        TreeSet<Vertex<V>> priorityQueue = new TreeSet<>();
        for (Vertex<V> vertex : graph.getVertices()) {
            if(source==vertex)vertex.setWeight(0);
            else vertex.setWeight(Integer.MAX_VALUE);
            priorityQueue.add(vertex);
        }
        while (!priorityQueue.isEmpty()) {
            Vertex<V> min = priorityQueue.pollFirst();
            min.setVisited(true);
            for (Edge<E, V> edge : graph.getEdges(min)) {
                if (edge.getTo().isVisited()) continue;

                Number newPath = min.getWeight().doubleValue() + edge.getWeight().doubleValue();
                if (edge.getTo().getWeight().doubleValue() > newPath.doubleValue()) {
                    priorityQueue.remove(edge.getTo());
                    edge.getTo().setWeight(newPath);
                    edge.getTo().setParent(min);
                    priorityQueue.add(edge.getTo());
                }
            }
        }


    }


}
