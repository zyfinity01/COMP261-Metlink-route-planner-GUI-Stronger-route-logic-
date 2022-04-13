# COMP 261 Assignment 1 - gandhinira

## What the code does
* [x] Minimum: A* search
* [x] Core: Components and Connections
* [x] Complete[75-85]: Time taken
* Challenge
   * [x] Allow time or distance in A* search

I have completed the Minimum, Core, Completion alongside the addition of a button that allows switching between time or distance for the A* search which are apart of Challenge.


## core
For core the main objective was getting A* working, for this stage I essentially used the pseudo code to my advantage to generate the A* class, the reason A* is needed and is better than dijkstras algorithm for pathfinding is because we can use a heuristic to allow the algorithm to find the goal node faster, in the case of core distance to the goal node was used, however functionality was added so that the user can switch between choosing distance or time as the heuristic, time may be better as it more acurately represents real life situations [code for AStar](/src/main/java/comp261/assig2/AStar.java#L21-177)

## completion
The completion code was calculating the time taken, this includes the time taken for the trip alongside updating the A* algorithm so that it uses time as the cost rather than the distance. I also implemented walking so that the user can input a  
## Challenge stuff
For challenge I managed to complete mouse panning and scroll wheel zooming with the use of scenebuilder, I used the javafx scenebuilder to set what the actions do and what methods they call.

I also used scenebuilder for the language switcher button that allows the user to switch between english and tereo, I added the button using the scenebuilder GUI to drag and drop the button onto the panel.
