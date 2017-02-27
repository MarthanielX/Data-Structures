I've found that the runtime decreases as we increase array size, although as we 
increase the array size more and more the decreases in run time level off, appearing 
to approach an asymptote. This reflects the relatively equal tradeoff of dealing with
collisions vs creating empty nodes.

Memory useage initially increases, drops sharply, and then begins increasing again 
as array size increases. It seems like memory should increase with array size, but
this is probably because I created an empty LinkedList for every single location in
the array, which wastes memory if the array is mostly empty. 