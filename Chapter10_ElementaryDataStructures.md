第10章习题答案
=
#####10.1-2 说明如何用一个数组A[1..n]来实现两个栈，使得两个栈中的元素总和不到n时，两个都不会发生上溯。注意PUSH和POP操作的时间应为O(1).  
代码见[一个数组实现两个栈TwoStacksInOneArray](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap10_ElementaryDataStructures/TwoStacksInOneArray.java)  

#####10.1-3 用图示说明队列操作过程ENQUEUE(Q, 4), ENQUEUE(Q, 1), ENQUEUE(Q, 3), DEQUEUE(Q), ENQUEUE(Q, 8), and DEQUEUE(Q) in array Q[1..6].  
![](https://github.com/zhuxiuwei/CLRS/blob/master/Images/10.1-3.png)  

#####10.1-4 Queue代码。要能处理上溢和下溢  
代码见[Queue](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap10_ElementaryDataStructures/Queue.java)  

#####10.1-5 双端队列Deque，其插入和删除操作都可以在两端完成。写出4个在两端插入和删除的O(1)操作。  

#####10.1-6 两个栈实现队列，并分析相关运行时间。  
代码见[Queue_By2Stacks](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap10_ElementaryDataStructures/Queue_By2Stacks.java)  
入队时间O(1)。
出队时，如果出队元素在stack2（stack2不为空），直接stack2.pop，O(1)。否则，stack1要把所有元素先弹栈再依次入stack2，然后stack2.pop，O(n)。  

#####10.1-7 两个队列实现栈，并分析相关运行时间。  
代码见[Stack_By2Queue](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap10_ElementaryDataStructures/Stack_By2Queue.java)  
入队时间O(1)。
出队时，每次都要把一个队列的数除了队尾的都重新插入另外一个队列。时间复杂度O(n-1)。  

#####10.2-1 单链表上的插入、删除操作能否O(1)实现？  
插入能，删除不能。  

#####10.2-2 单链表实现栈。push pop仍是O(1)。  
代码见[Stack_ByLinkedList](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap10_ElementaryDataStructures/Stack_ByLinkedList.java)  

#####10.2-3 单链表实现队列。enqueue和dequeue仍是O(1)。  
代码见[Queue_ByLinkedList](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap10_ElementaryDataStructures/Queue_ByLinkedList.java)  
需要注意的是，要实现O(1)级别的dequeue，需要LinkedList提供O(1)的removeLast()方法。  
O(1)的removeLast()在我的[LinkedList](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap10_ElementaryDataStructures/LinkedList.java)类里已实现。  

#####10.2-5 字典序的单链表INSERT, DELETE和SEARCH。并分析时间。  
代码见[LinkedList_Sorted](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap10_ElementaryDataStructures/LinkedList_Sorted.java)  
时间复杂度，如果插入、查找、删除的元素不在字典区间内，则O(1)。否则O(n)。  
