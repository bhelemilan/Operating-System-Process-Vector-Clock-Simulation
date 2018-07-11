# Operating-System-Process-Vector-Clock-Simulation
```
SAMPLE RUNS AND THEIR OUTPUT	
***************************************************************
NOTE THAT: You may give Process Events and/or Message Sequences in any order
Once you are done, give input -1 to finish, then it will print output of events for each process with vector clock

Example 1
-------------------------------------------------------------------------------------------------------------------------
Enter number of Processes:3



-------------------------------------------------------------------------------------------------------------------------
Entry For Processes' Events and Message Sequences
	Enter Process Event (Eg. 1-1) or Message Sequence (Eg. 1-1->2-1)
	Duplicate entry for Process Event or Message Sequence are ignored
	Note: Enter -1 to finish
-------------------------------------------------------------------------------------------------------------------------
Input:1-1
Input:1-2
Input:1-3
Input:1-4
Input:1-1->2-2
Input:1-3->3-4
Input:2-1
Input:2-2
Input:2-3
Input:2-4
Input:2-3->3-1
Input:3-1
Input:3-2
Input:3-3
Input:3-4
Input:3-2->1-2
Input:-1



-------------------------------------------------------------------------------------------------------------------------
Displaying each process and its events horizontally (With Vector Clock)
-------------------------------------------------------------------------------------------------------------------------
1-1: (1,0,0)     1-2: (2,3,2)     1-3: (3,3,2)     1-4: (4,3,2)     
2-1: (0,1,0)     2-2: (1,2,0)     2-3: (1,3,0)     2-4: (1,4,0)     
3-1: (1,3,1)     3-2: (1,3,2)     3-3: (1,3,3)     3-4: (3,3,4)

Example 2
-------------------------------------------------------------------------------------------------------------------------
Enter number of Processes:3



-------------------------------------------------------------------------------------------------------------------------
Entry For Processes' Events and Message Sequences
	Enter Process Event (Eg. 1-1) or Message Sequence (Eg. 1-1->2-1)
	Duplicate entry for Process Event or Message Sequence are ignored
	Note: Enter -1 to finish
-------------------------------------------------------------------------------------------------------------------------
Input:1-4
Input:1-2
Input:3-1->2-1
Input:1-1
Input:1-3
Input:2-2->1-1
Input:2-3->3-2
Input:2-5->3-4
Input:2-4
Input:2-5
Input:2-1
Input:2-3
Input:2-2
Input:3-3->1-3
Input:3-5->1-4
Input:1-2->2-4
Input:3-5
Input:3-4
Input:3-1
Input:3-2
Input:3-3
Input:-1



-------------------------------------------------------------------------------------------------------------------------
Displaying each process and its events horizontally (With Vector Clock)
-------------------------------------------------------------------------------------------------------------------------
1-1: (1,2,1)     1-2: (2,2,1)     1-3: (3,3,3)     1-4: (4,5,5)     
2-1: (0,1,1)     2-2: (0,2,1)     2-3: (0,3,1)     2-4: (2,4,1)     2-5: (2,5,1)     
3-1: (0,0,1)     3-2: (0,3,2)     3-3: (0,3,3)     3-4: (2,5,4)     3-5: (2,5,5)
```