# Skip List

It is an alternative data structure for balanced trees. Balancing trees in general is a complicated task as maintaining it would involve rotation, coloring logic etc.

Picture Skip List as a List in which the node n is connected to another node, that is the k-th node after node n.
A node that has k forwarding pointers is called a level k node.
If we design the list such that the $2^i$th node n has exact pointers that point at $2^i$th node after n, then this would realise a searching time of $\text{O(lg n)}$.
At the same time we randomly decide when inserting a node at which k-th level it is, that is, it can be a level 1 node with 50%, level 2 with 25% and so on.



# Reference
*Pugh, W. (1990). "Skip lists: a probabilistic alternative to balanced trees" Skip lists: a probabilistic alternative to balanced trees. Communications of the ACM, 33(6), pp.668-676.*