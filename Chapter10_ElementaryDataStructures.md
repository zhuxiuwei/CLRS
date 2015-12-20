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

#####10.2-6 两个连边的O(1)的Union操作。  
代码见[LinkedList之union方法](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap10_ElementaryDataStructures/LinkedList.java)  

#####10.2-6 链表反转。非递归。只用固定的额外存储空间。  
代码见[LinkedList之revert方法](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap10_ElementaryDataStructures/LinkedList.java)  

#####10.3-1 画图表示序列{13,4,8,19,5,1}的__多数组__表现形式和__单数组__表现形式。
假设数组能容纳10个元素。见下图（元素在数组中方的位置我是随机选的）    
![](https://github.com/zhuxiuwei/CLRS/blob/master/Images/10.3-1.png)  

#####10.3-2 对一组同构对象的单数组表示法实现，写出过程allocate-object和free-object。  
代码见[ObjectImplBySingleArray](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap10_ElementaryDataStructures/ObjectImplBySingleArray.java)  
数组实现的链表操作，和普通的链表操作时间复杂度一样，插入是O(1)，查找和删除都是最坏O(n)。  

#####10.3-3 allocate-object和free-object的实现过程中，为什么不需要对prev重置？  
书上的实现中确实没有对prev的重置，但是[我自己的实现](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap10_ElementaryDataStructures/ObjectImplBySingleArray.java)中还是重置了prev的。
可能是不需要prev操作吧。  

#####10.3-4 我们常常希望一个双链表中的所有元素在存储器中能够紧凑地排列在一起，例如使用多重数组表示中的前m个下标位置（在一个分页的虚拟计算机环境中情况就是这样的）。
假设链表以外没有指向链表元素的指针，请说明如何实现过程ALLOCATE_OBJECT和FREE_OBJECT，才能使这种表比较紧凑。（提示：使用栈的数组实现）  
思路是，增加的时候总是往数组能用的最小下标增加（类似stack push）；  
删除时，如果删除的是最大的数组下标，啥也不用动；如果不是，删除后，可以把最大数组下表的元素移动到被删除空出来的数组位置。这涉及到一些指针的操作而已。  

#####10.4-4 __左孩子右兄弟表示法__的n个节点的树，写出O(n)的输出所有元素的过程。  
 代码见[Tree_LefrChildRightSibling](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap10_ElementaryDataStructures/Tree_LefrChildRightSibling.java)  
 实现了先根遍历，中根遍历，后根遍历和层次遍历。注意怎么快速建立一棵树的。  
