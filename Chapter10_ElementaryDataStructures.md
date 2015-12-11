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
