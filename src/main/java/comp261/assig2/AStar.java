package comp261.assig2;

/*
Dijkstra search alogrithm

@author: Simon McCallum, Github Copilot
*/

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.ResourceBundle.Control;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

// dijkstra class for path finding in the graph
public class AStar {

    public static ArrayList<Edge> findShortestPathEdges(Graph graph, Stop start, Stop end) {

        // check if the start and end stops are null
        if (start == null || end == null) {
            return null;
        }
        // used to check how many nodes you visited. Lower is better H.
        int totalExplored = 0;

        ArrayList<Stop> stops = graph.getStopList();

        // creating a comparison function for the priority queue based on Path
        Comparator<PathItem> pathStopCompare = new PathCostComparator();
        // create a new priority queue for the finge
        PriorityQueue<PathItem> fringe = new PriorityQueue<PathItem>(pathStopCompare);

        // create a new array list for the path to be extracted
        ArrayList<PathItem> visitedStops = new ArrayList<PathItem>();

        double currentFringeCost = 0;

        // vital step to make sure you can find the new path
        graph.resetVisited();

        start.setCost(0);

        double f = g(start) + heuristic(start, end, graph.getDoDistance()); // also could be f(start,start, 0, start,end);
        fringe.add(new PathItem(start, 0, f, null, null)); // the input is (node, cost, f, prev, edge)

        // while the queue is not empty
        while (!fringe.isEmpty()) {
            // get the stop with the lowest cost
            PathItem currentPathItem = fringe.poll();
            Stop currentStop = currentPathItem.getStop();

            // check if the stop from the queue has been visited
            if (currentStop.isVisited()) {
                continue;
            }
            // if the stop has not been visited, mark as visited and add to visitedStops
            // list
            currentStop.setVisited(true);
            visitedStops.add(currentPathItem);
            // set the cost for the current node
            currentStop.setCost(currentPathItem.cost);
            currentFringeCost = currentPathItem.cost;

            totalExplored++;

            // if the current stop is the end stop
            if (currentStop == end) {
                // path to return
                ArrayList<Edge> shortestEdgePath = makeEdgePath(graph, visitedStops, start, end);
                return shortestEdgePath;
            }

            // go through each of the current stop's neighbours and add to the fringe
            for (Edge edge : currentStop.getNeighbours()) {
                // get the neighbour from the edge
                Stop neighbour = edge.getToStop();

                // if the neighbour has not been visited already
                if (!neighbour.isVisited()) {
                    // set the neighbour's cost to the current stop's cost + the edge's cost (time
                    // or distance)
                    edge.setCost(edge.getTime());

                    if (graph.getDoDistance()) {
                        System.out.println("dist");
                        neighbour.setCost(currentStop.getCost() + edge.getDistance());
                    } else {
                        System.out.println("time");
                        neighbour.setCost(currentStop.getCost() + edge.getTime());
                    }

                    // Error checking is useful
                    if (neighbour.getCost() < 0) {
                        System.out.println("Error: negative cost");
                    }
                    // calculate the new f value using g edge cost and heuristic
                    // something like
                    // f = g(start, currentStop) + edge.getCost() + heuristic(neighbour, end);
                    System.out.println(edge.getTime());
                    f = f(currentStop, edge.getCost(), neighbour, end, graph.getDoDistance());

                    // add the neighbour to the queue as a new PathItem
                    fringe.add(new PathItem(neighbour, neighbour.getCost(), f, currentStop, edge));
                    // using somthing like neighbour, cost, f, prev, edge
                }
            }
        }

        // comment on failue
        System.out.println("Error: " + start + " to " + end + " not found");
        return new ArrayList<Edge>();
    }

    /**
     * build a path from the end back to the start from the PathItem data
     * 
     * @param graph
     * @param visited the nodes visited while searching for this trip
     * @param start
     * @param goal
     * @return the list of stops in the path
     */
    private static ArrayList<Edge> makeEdgePath(Graph graph, ArrayList<PathItem> visited, Stop start, Stop goal) {
        ArrayList<Edge> path = new ArrayList<Edge>();
        Edge currentItem = visited.get(visited.size() - 1).getEdge(); // the last edge added is on the path

        // while the current item is not the start
        while (currentItem.getFromStop() != start) {
            path.add(currentItem);
            // find the stop from the visited PathItems
            for (PathItem visitedItems : visited) {
                if (visitedItems.getEdge() == null) {
                    continue;
                }
                if (visitedItems.getEdge().getToStop() == currentItem.getFromStop()) {
                    if (visitedItems.getEdge().getToStop() == null) {
                        return null;
                    }
                    currentItem = visitedItems.getEdge();
                }
            }
            if (currentItem == null) { // if not invalid path
                System.out.println("error: ");
                return null;
            }
        }
        // finally add the start and return
        path.add(currentItem);
        return path;
    }

    public static double f(Stop current, double edgeCost, Stop neighbour, Stop end, boolean doDistance) {
        return g(current) + edgeCost + heuristic(neighbour, end, doDistance);
    }

    public static double g(Stop current) {
        return current.getCost();
    }

    public static double heuristic(Stop current, Stop goal, boolean doDistance) {
        // calculate a heuristic for the current stop to the goal stop
        double d = 0;
        if (doDistance) {
            d = current.distance(goal);
        } else {
            d = current.distance(goal) / Transport.TRAIN_SPEED_MPS;
        }
        return d;
    }

}
