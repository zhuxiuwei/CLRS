第6章习题答案
=
#####6.1-1 高度为h的堆，元素个数最多、最少是多少？  
最小：(∑(i from 0 to h-1)2^h-1) + 1 = 2^h  
最大：∑(i from 0 to h)2^h = 2^(h+1) - 1  

#####6.1-4 一个所有元素都不相同的最大堆，该堆的最小元素在哪里？  
在全部叶子节点中的一个。  

#####6.1-5 已排序的数组是最小堆吗？  
是。  

#####6.1-6 数组⟨23,17,14,6,13,10,1,5,7,12⟩ 是不是最大堆?  
不是。数组元素6和7违反定义。  

#####6.2-1  描述过程：MAX-HEAPIFY(A, 3) on the array A=⟨27,17,3,16,13,10,1,5,7,12,4,8,9,0⟩.  
3和10交换，再和9交换。  

#####6.2-2 最小堆伪代码，并和最大堆运行时间比较  
代码见[Prac622_Min_Heapify.java](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap06/Prac622_Min_Heapify.java)  
运行时间和最大堆一样， 0(lgn)  

#####6.2-3  A[i]比所有孩子都大，调用max-heapify(A, i)的后果是？  
没有效果，已符合最大堆定义  

#####6.2-5  递归效率可能导致某些编译器产生低效代码，重写 max-heapify用循环替代递归  
代码见[Prac625_Max_Heapify_NoRescursive.java](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap06/Prac625_Max_Heapify_NoRescursive.java)  

#####6.3-2  为什么建堆算法，是从最后一个非叶节点递减，而不是从第一个非叶节点递增  
递增的话，逻辑不对可能导致结果不对。因为子节点的调整可能破坏大根堆的性质。简单地例子：
4 1 3 10，从根节点4调整的话，最终结果就是错误的4 10 3 1。（期望值：10 4 3 1）  

#####6.5-1  
结果：13 12 9 5 6 8 7 4 0 1 2，15移除。  

#####6.5-2  
结果： 15 13 10 5 12 9 7 4 0 6 2 1 8  

#####6.5-3 用最小堆实现最小优先队列。  
代码见[PriorityQueueMinHeap](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap06/Prac653_PriorityQueueMinHeap.java)  

#####6.5-4  
相当于让队列数组里的最后一个元素当前值为-∞，对这个元素调用HEAP-INCREASE-KEY时，key(新值)就一定比A[i]（当前值）大，从而能执行到进行上浮调整的代码，让新插入的元素走到可能的合适位置。  

#####6.5-6  
相当于每次while，都把parent的值赋到当前位置。当找到最终位置后，再把最初的值放到最终的位置。总的来说确实能减少交换次数，但不是真的“只有一次交换”  

#####6.5-7 用优先队列实现先入先出队列和栈。  
假设是最大优先队列。  
对于先入先出队列，相当于不断把元素按照 priority递减的顺序加入优先队列。  
对于栈，相当于不断把元素按照 priority递增的顺序加入优先队列。  

#####6.5-8  
见代码[Max_Heap_Delete](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap06/Prac658_Max_Heap_Delete.java)  
思想：交换节点i和堆中最后一个节点，然后调用heapify(a, i)调整堆。  

#####6.5-9 合并k个有序链表为1个有序链表，k个连边一共有n个元素，则时间复杂度O(n * lgk)  
思路：每次从k个链表中取出1个放到数组a里，heapify(a, 1)数组，从堆里取出root放到结果集，然后从上一个root所在的链表集合中取出下一个放到堆，
如果上一个root所在的链表集合没有下一个元素，就从有下一个元素的链表集合里随机取一个元素加到数组a中。重复以上步骤直到所有元素都在结果集。  
代码见[CombineKOrderedList](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap06/Prac659_CombineKOrderedList.java)  

###-------- 思考题 ----------  
6-1  
a. 不总是一样。如1 2 3 4 5 6， 经典算法是6 5 3 4 2 1， 本算法是6 4 5 1 3 2  
b. 没做  

6-2 d叉堆  
a. 数组下标从0开始，对于index=i的元素，父： i-1/k，子：i x k + 1, i x k + 2, ... , i x k + k.  
b. log(k,n)  
c. 和二叉堆类似，也是根元素和最后一个元素交换，然后从root开始heapify。  
时间效率，相当于就是heapify的效率，O(log(k,n)) 
d. 和二叉堆类似，先放在数组最后，然后不断（上浮）。时间效率O(log(k,n))  
e. 和二叉堆一样，和parent比较大小，比parent大的话就上浮。时间效率O(log(k,n))  

6-3 杨氏矩阵  
a. 画法不唯一 
2 3 4 ∞ | 5 8 9 ∞ | 12 14 16 ∞ | ∞ ∞ ∞ ∞  
或 2 3 4 5 | 8 9 12 14 | 16 ∞ ∞ ∞ | ∞ ∞ ∞ ∞  
或 2 3 4 12 | 8 14 ∞ ∞ | 9 16 ∞ ∞ | ∞ ∞ ∞ ∞
b. 略  
c. **Extract-MIN** 思想：和堆的extract-max类似。最小的数一定是出现在矩阵中的第一个数。EXTRACT-MIN取出第一个数，并用∞替换之，然后不断和其右邻居、下邻居中较小的一个进行交换，直到找到合适位置为止。（到达边界，或者邻居都不比它小）  
d. **插入** 从左到右，从上到下，遍历找到第一个∞。将其用新插入的数替换，然后将这个数不断“上浮”，上浮的过程是和其左/上邻居中较小的一个不断交换直到到达合适位置。  
e. **排序** 和堆排序思想类似。用c的算法不断取出最小的即可。  
f. **查找** （不会，看的这个[答案](http://clrs.skanev.com/06/problems/03.html)）:We from the **lower-left** corner. We check the current element current with the one we're looking for key and move **up** if current**>**key and **right** if current**<**key. We declare success if current=key and otherwise terminate if we walk off the tableau.  

