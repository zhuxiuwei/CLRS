需要注意的的题目总结
=
#####第2章 算法基础  
[二分查找递归非递归算法](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap02_GettingStarted/prac235_BinarySearch.java) ★★★  
不是很顺畅，退出情况要思考清楚，这个地方花了些时间，  

[统计逆序对个数](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap02_GettingStarted/Thinks24_Inversion_NiXuDui.java) ★★★  
开始算法思路有问题。而且书上不提示的话，估计想不到用归并排序思想。  

#####第4章 分治策略  
[最大子数组的线性算法](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap04_DivideAndConquer/MaxSubArray_Lineary.java) ★★★★★  
 花费大量时间。属于那种递归写起来挺直观，但如果写成“面向过程”的写法算法有很复杂，很容易出错。  
 
 [矩阵乘法的原始算法](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap04_DivideAndConquer/MatrixMultipleOriginal.java) ★★★☆  
  加和公式写错了，导致结果不对，花费1小时时间。写的太慢！  
  【教训】应该在纸上先列下式子，再写代码。代码上来就写导致出错。刚开始矩阵乘法的算法都记错了就开始乱写。  
当有多重循环时，从业务逻辑中（如矩阵乘法的计算公式），推导出i,j,k变量分别应该在哪个位置的能力需要加强。  

[矩阵乘法的递归算法](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap04_DivideAndConquer/MatrixMultipleRecursive.java) ★★★★  
【难度】 不是很熟练，在纸上写会有问题。难在递归时 -- 也就是分割矩阵时的建模 --- 怎么抽象出矩阵分割，定义函数签名，怎么处理两个矩阵的加法（后来发现实际“不用”做）。建好模后，写起来还比较顺畅。  

[矩阵乘法的Strassen算法](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap04_DivideAndConquer/MatrixMultipleStrassen.java) ★★☆  
【难度】 难度和矩阵乘法递归类似。因为有先前经验，这次还算顺利，虽然也花了不少时间（1小时+），主要是因为式子太多，一不小心就会写错~~更多像是耐心活。  

#####第6章 堆排序  
[最小堆实现的最小优先队列](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap06_HeapSort/Prac653_PriorityQueueMinHeap.java) ★★  
不算难，但是比较值得关注。用到的Java知识点挺多的。  

[将k个有序链表合并成一个有序链表的O(lgk x n)算法](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap06_HeapSort/Prac659_CombineKOrderedList.java) ★★☆  
花费时间略长。大概2h+。难点在于，如何表达，从n个list里取“下一个”的代码逻辑。逻辑略复杂，手写会有问题。  

#####第8章 线性时间排序  
[CountNumberInRange](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap08_SortInLinerTime/CountNumberInRange.java) ★★★  
[8.2-4]查找0..k之间n个数的数组，落在区间[a..b]内的数字的个数。思路不难，但写的时候有三个地儿出错，如果手写会有问题。  

#####第9章 中位数和顺序统计量  
[Find2ndSmallest](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap09_MediansAndOrderStatistics/Find2ndSmallest.java) ★★★  
[9.1-1] 找到n个不同元素的数组中第二小的元素，最坏情况下需要n+⌈lgn⌉-1次比较的算法？  
花费时间较长，纸上写写不完。开始的时候思路不清晰。  
	1. 数组越界问题。  
	2. 算法思路问题。没想到这种两两比较的快速的算法思路，看的网上的答案。  

#####第10章 基本数据结构  
[Queue](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap10_ElementaryDataStructures/Queue.java) ★★☆  
不算难，有两个【注意点】：   
1. 队满时的判断。注意不要还有明明一个剩余空间，就告知满了。  
2. 出队的时候，也要判断head是否需要轮转回到0。  

[字典序的单链表](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap10_ElementaryDataStructures/LinkedList_Sorted.java) ★  
注意*泛型*，*继承*，*内部类*的语法。  

[链表反转](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap10_ElementaryDataStructures/LinkedList.java)的revert方法 ★★  
有两个边界值的注意点。__如果手写，还是做不到完全不出错__，郁闷~~~~！！！！  

[用单数组表示法实现双向链表](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap10_ElementaryDataStructures/ObjectImplBySingleArray.java) ★★★  
算法思路挺有意思。不算难，稍微有点复杂。  
一个注意点： free_object()方法中，freeIndex的更新要注意。freeIndex变量存的index在array里保存的是下一个可用的index(见构造函数里对freeIndex的初始化)，因此freeIndex始终要保持一种“栈”的感觉，可用的元素通过“栈”连接起来。
我之前只写了freeIndex = keyIndex一条，导致array都清空一次后，再添加第一个object还正确，但是再添加第二个就出错了。 因为freeIndex没有把“栈”的元素连接起来。  

#####第11章 哈希表  
[直接寻址表的实现](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap11_HashTables/DirectAddressTable.java) ★  
题不难，但是从Java语法角度，有些有意思的东西要注意：  
1. Java不支持泛型数组。如果直接写 a = new E[array_size]会报错：'Cannot create a generic array of E'。 注意是如何实现一个__泛型数组__的。参考http://stackoverflow.com/questions/529085/how-to-create-a-generic-array-in-java  
2. 用元素的__hashcode__做key，来做该元素的插入和删除。  
3. 有意思的一点。从直接寻址表删除时，我模仿HashSet的行为。__Set判断两个元素是否相同，即要判断俩元素是否equals，又要判断hashcode是否相等！__  
 	因为是用hashcode做key，如果元素1和元素2有一样的hashcode,如果直接寻址表中存储的是m1，但是执行delete(m2)，那么：  
 		如果m1.equals(m2)，那么应该m1也被删除。（从集合的视角看，这俩元素相同）  
 		如果m1 not equals m2，那么m1不应该被删除。（从集合的视角看，这俩元素不同）  

#####第11章 二叉搜索树  ★★★★
[BinaryTree中序遍历非递归算法](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap12_BinarySearchTree/BinaryTree.java)  
debug写的，还是不够熟练。其中有一个额外需要注意的地方，需要加一个判断条件防止算法走“回头路”。回头路指的是一个节点的左孩子已经遍历过了，别回过头来又遍历，导致死循环。  

