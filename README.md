Random Maze Generator
=====================

This code generates a random, closed maze on a
positive Cartesian plane such as the one below.


```
(0,0)
 ----------> X
|
|
| Positive Plane
|
v
Y
```

The algorithm it employs is to begin with each cell in the maze completely walled off.
Inspect a random wall in the maze and knock it out if there's no path connecting the
cells on either side of the wall. Once all cells are connected, the maze is defined.

To make a playable maze, simple add an entry and exit point. If you're interested in
making your randomly generated maze as challenging as possible, one way to do so is to
find the two cells in the maze with the longest path between them and use them as entry
and exit. One method for doing so would be an all-pairs shortest path algorithm like
[Floyd-Warshall](http://en.wikipedia.org/wiki/Floyd%E2%80%93Warshall_algorithm).

