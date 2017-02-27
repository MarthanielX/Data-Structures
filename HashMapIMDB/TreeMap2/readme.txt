I just ran for (int i =0; i < 100; i+= 10) around then entire main method,
and had i be the number of times to put each title/plot pair int the map.
I replaced the promptUser (for a prefix) with a write to a file, which is
where I got the data below. Adding each entry to the map multiple times 
just mean the program will have to navigate the BST again and then replace
the previous data with the same thing. It looks like navigating the BST once 
takes about .01e10 nanoseconds, which isn't much considering how long the 
program as a whole takes to run. It seems prety clear that reading in the
file is the slow part of the program, because almost everything in the main 
besides the put statement is part of reading in the file. The jump in runtime
from 0 to 10 is likely because Java saves time by not doing certain operations
that it knows won't end up mattering. 

Times each entry was added: 0
Program runtime: 1.2414601942E10

Times each entry was added: 10
Program runtime: 1.6494434143E10

Times each entry was added: 20
Program runtime: 1.7253463879E10

Times each entry was added: 30
Program runtime: 1.8306501506E10

Times each entry was added: 40
Program runtime: 1.9553871308E10

Times each entry was added: 50
Program runtime: 2.0672546654E10

Times each entry was added: 60
Program runtime: 2.1970586495E10

Times each entry was added: 70
Program runtime: 2.2993720707E10

Times each entry was added: 80
Program runtime: 2.385989781E10

Times each entry was added: 90
Program runtime: 2.5038407289E10
