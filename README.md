# COMP 261 Assignment 1 - gandhinira

## What the code does
* [x] Minimum: A* search
* [x] Core: Components and Connections
* [x] Completion: Time taken
* Challenge
   * [x] Allow time or distance in A* search

I have completed the Minimum, Core, Completion alongside the addition of a button that allows switching between time or distance for the A* search which are apart of Challenge.


## core
For core the main objective was getting A* working, for this stage I essentially used the pseudo code to my advantage to generate the A* class, the reason A* is needed and is better than dijkstras algorithm for pathfinding is because we can use a heuristic to allow the algorithm to find the goal node faster, in the case of core distance to the goal node was used, however functionality was added so that the user can switch between choosing distance or time as the heuristic, time may be better as it more acurately represents real life situations [code for AStar](/src/main/java/comp261/assig2/AStar.java#L21-177)

## completion
The completion code was calculating the time taken, this includes the time taken for the trip alongside updating the A* algorithm so that it uses time as the cost rather than the distance. I also implemented walking so that the user can input a maximum walking distance so that trips can be created making use of train lines or stops that are close to each other, similar to how this is done on metlinks journey planner.

## Challenge stuff
For challenge I headed over to the scenebuilder application and created a button, this button changes text and switches between either distance or time, depending on which one is selected a different cost will be used when doing the A* calculations. This could be useful when debugging or just a good to know option.


## Directed multigraphs
In the directed multigraph (within our A* algorithm) its important that the edges are stored as the path is generated, this is because the way A* works is that there is a fringe, with this fringe in real time adding all neighbouring nodes to the fringe, the code then dynamically in real time via a priority queue ensures the top of the queue is the closest node with us setting every operating node as visited and adding it to our visitedstops list, this process is then continued until our operating node becomes the goal node. After finding the goal node we can traverse backwards as the newly found goal node will have a previous node where that node will have a previous node also until we traverse to the starting node which is the shortest path. This is why we need to store the edges while the path is generated so that A* can find the shortest path in the most efficient way, even though it has a space complexity of O(b^d).
