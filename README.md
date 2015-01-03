This is a Scala program to solver the 4 circles number allocation problem.

See section 3.1, figure 5, of the [Stanford Encyclopedia of Philosophy]
(http://plato.stanford.edu/entries/diagrams/#LimDiaRepRea) diagrams article for a diagram that shows the four
intersecting circles we'll be working with.

The problem concerns how to allocate a single number to each region from (1 .. 13), such that every number is used
once and only once, and the total of all four circles is the same.

We are looking for all valid circle totals for which there exists at least one solution (as opposed to all of the
solutions, which is a much larger number).

The program uses a brute force approach to solving the problem. It reports all circle totals, and the last solution
found for each circle total.