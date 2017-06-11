需要注意的的题目总结
=
## 第2章 算法基础  
[二分查找递归非递归算法](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap02_GettingStarted/prac235_BinarySearch.java) ★★★  
不是很顺畅，退出情况要思考清楚，这个地方花了些时间，  

[统计逆序对个数](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap02_GettingStarted/Thinks24_Inversion_NiXuDui.java) ★★★  
开始算法思路有问题。而且书上不提示的话，估计想不到用归并排序思想。  

## 第4章 分治策略  
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

## 第6章 堆排序  
[最小堆实现的最小优先队列](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap06_HeapSort/Prac653_PriorityQueueMinHeap.java) ★★  
不算难，但是比较值得关注。用到的Java知识点挺多的。  

[将k个有序链表合并成一个有序链表的O(lgk x n)算法](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap06_HeapSort/Prac659_CombineKOrderedList.java) ★★☆  
花费时间略长。大概2h+。难点在于，如何表达，从n个list里取“下一个”的代码逻辑。逻辑略复杂，手写会有问题。  

## 第8章 线性时间排序  
[CountNumberInRange](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap08_SortInLinerTime/CountNumberInRange.java) ★★★  
[8.2-4]查找0..k之间n个数的数组，落在区间[a..b]内的数字的个数。思路不难，但写的时候有三个地儿出错，如果手写会有问题。  

## 第9章 中位数和顺序统计量  
[Find2ndSmallest](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap09_MediansAndOrderStatistics/Find2ndSmallest.java) ★★★  
[9.1-1] 找到n个不同元素的数组中第二小的元素，最坏情况下需要n+⌈lgn⌉-1次比较的算法？  
花费时间较长，纸上写写不完。开始的时候思路不清晰。  
	1. 数组越界问题。  
	2. 算法思路问题。没想到这种两两比较的快速的算法思路，看的网上的答案。  

## 第10章 基本数据结构  
[Queue](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap10_ElementaryDataStructures/Queue.java) ★★☆  
不算难，有两个【注意点】：   
1. 队满时的判断。注意不要还有明明一个剩余空间，就告知满了。  
2. 出队的时候，也要判断head是否需要轮转回到0。  

[字典序的单链表](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap10_ElementaryDataStructures/LinkedList_Sorted.java) ★  
注意*泛型*，*继承*，*内部类*的语法。  

[链表反转](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap10_ElementaryDataStructures/LinkedList.java)的revert方法 ★★  
有两个边界值的注意点。**如果手写，还是做不到完全不出错**，郁闷~~~~！！！！  

[用单数组表示法实现双向链表](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap10_ElementaryDataStructures/ObjectImplBySingleArray.java) ★★★  
算法思路挺有意思。不算难，稍微有点复杂。  
一个注意点： free_object()方法中，freeIndex的更新要注意。freeIndex变量存的index在array里保存的是下一个可用的index(见构造函数里对freeIndex的初始化)，因此freeIndex始终要保持一种“栈”的感觉，可用的元素通过“栈”连接起来。
我之前只写了freeIndex = keyIndex一条，导致array都清空一次后，再添加第一个object还正确，但是再添加第二个就出错了。 因为freeIndex没有把“栈”的元素连接起来。  

## 第11章 哈希表  
[直接寻址表的实现](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap11_HashTables/DirectAddressTable.java) ★  
题不难，但是从Java语法角度，有些有意思的东西要注意：  
1. Java不支持泛型数组。如果直接写 a = new E[array_size]会报错：'Cannot create a generic array of E'。 注意是如何实现一个**泛型数组**的。参考http://stackoverflow.com/questions/529085/how-to-create-a-generic-array-in-java  
2. 用元素的**hashcode**做key，来做该元素的插入和删除。  
3. 有意思的一点。从直接寻址表删除时，我模仿HashSet的行为。**Set判断两个元素是否相同，即要判断俩元素是否equals，又要判断hashcode是否相等！**  
 	因为是用hashcode做key，如果元素1和元素2有一样的hashcode,如果直接寻址表中存储的是m1，但是执行delete(m2)，那么：  
 		如果m1.equals(m2)，那么应该m1也被删除。（从集合的视角看，这俩元素相同）  
 		如果m1 not equals m2，那么m1不应该被删除。（从集合的视角看，这俩元素不同）  

## 第11章 二叉搜索树  
[BinaryTree非递归算法](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap12_BinarySearchTree/BinaryTree.java)★★★★  
1. 中序遍历的非递归，debug写的，纸上写肯定还是不熟练。其中有一个额外需要注意的地方，需要加一个判断条件防止算法走“回头路”。回头路指的是一个节点的左孩子已经遍历过了，别回过头来又遍历，导致死循环。  
2. 先序遍历的非递归的一个bug：退出循环的条件，需要加上node.left != null的判断条件。否则当树的root只有左孩子时，除了root，左孩子都遍历不到。
3. 后续遍历的非递归，是三者之中**最复杂**的。和中序遍历一样，后序遍历要记录已经访问过的节点，避免走“回头路”。而这里的逻辑比中序遍历更复杂，是主要的难点。 
后序遍历记录节点的访问，需要两个flag分别记录左右子树的访问情况。这里引出了三个另外的注意点。  

[二叉搜索树的插入和删除](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap12_BinarySearchTree/BinarySearchTree_InsertAndDelete.java)★★  
有两个注意点，其中第二个更需要注意：  
1：插入节点递归算法，找到要插入的地方的条件开始写错了，写成了currentNode.left == null && currentNode.right == null，浪费不少时间。需要先仔细想想。  
2： 删除结点时，**如果被删除的结点是root，需要特殊考虑！** （比如它没有parent，因此不能直接修改parent的指针，会NullPointerException；它的parent可以理解成tree的root指针）为此抽象出来了updateParentPointToNewChild()方法。  

[有successor没有parent指针的二叉搜索树](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap12_BinarySearchTree/BinarySearchTree_NodeHasSuccessorPointerButNoParentPointer.java)★★  
 注意点：  
1. 插入时，一个情况需要注意更新successor。就是待插入的结点比当前结点大，但是当前结点的右孩子已经被占据了，此时可能需要更新当前结点的successor。见test中插入9时，7的successor从13更新成9.  
2. 查找时，待查找结点比当前结点大，不能写成current = current.successor，还是和传统的查找一样找右子树（我开始还以为这是个比传统二叉搜索树的改进。。。），因为可能陷入死循环。见test中查找5.就会6-3-4-6-3-4...死循环  
删除方法，做完了普通二叉搜索树的删除再做这个，不难，但是比较繁琐。  

## 第15章 动态规划  
[钢管切割的bottom oUp方法](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap15_DynamicProgramming/CutRod.java),
[斐波那契数列](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap15_DynamicProgramming/FibonacciDynamicProgramming.java)  
两者有一个共同点：用**辅助数组r记录各个子问题结果，然后n的最优解返回r[n]**。看起来用动态规划Bottom Up算法这是一个很好用的思路！  

[LCS最长公共子序列](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap15_DynamicProgramming/LCS.java)  
使用哨兵可以简化问题。有三个注意点。其中打印solution用到的辅助数组b的思路挺有意思。  

## 第16章 贪心算法  
活动选择问题的[动态规划算法](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap16_GreedyAlgorithms/ActivitySelector.java#L36) ★★★★★  
开始花了很多时间也写不对，子问题划分的有些问题。最后参考了[文章](http://www.cs.princeton.edu/~wayne/cs423/lectures/dynamic-programming-4up.pdf)，改了划分方法成功了。**失败的代码**：[ActivitySelector](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap16_GreedyAlgorithms/ActivitySelector.java#L82)  

[0-1背包问题动态规划算法](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap16_GreedyAlgorithms/KnapsackProblem.java) ★★★★★  
花的时间也多。调试很多次。  

[Professor Water supplement problem](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap16_GreedyAlgorithms/ProfessorWaterSupplement.java) ★★  
本身思路不难，但是在处理最后一个节点的时候（edge case）开始没想好就写，浪费了差不多1小时才都调对。。。。。  

## 第22章 基本的图算法  
[图通用汇点的O(V)算法](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap22_ElementaryGraphAlgo/Prac22_1_6_UniversalSink.java) ★★  
O(V^2)的算法非常简单， 但是**O(V)**算法的思路还是挺有意思的，不好想。[参考](https://github.com/zhuxiuwei/CLRS-1/blob/master/C22-Elementary-Graph-Algorithms/22.1.md)  

[BFS](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap22_ElementaryGraphAlgo/BFS.java) ★★  
需要注意BFS在初始化顶点的时候，顶点的d的值（除了源顶点）应该初始化为无穷大，而不是0.  

[DFS](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap22_ElementaryGraphAlgo/DFS.java) ★★  
三个注意点.  
两个是[关于DFS](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap22_ElementaryGraphAlgo/DFS.java#L39)的。对一个顶点是否调用递归函数，要先判断是否是WHITE(BFS处理一个节点时也要判断)； 另一个是timer累加的时机注意一下。  
一个是[关于打印节点类型](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap22_ElementaryGraphAlgo/DFS.java#L87)的。  

[有向无环图两个节点间简单路径的数量](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap22_ElementaryGraphAlgo/CountOfPaths.java)。★★★★  
花费了比较长的时间才获取正确结果，感觉自己写的方案并不简洁，希望能有更好的方案。  
注意如何记录一个结点已经访问过的邻居结点的 - 用一个map [key=Vertex, value=Integer> neighborsHasCheckedIndex](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap22_ElementaryGraphAlgo/CountOfPaths.java#L33)。**最开始我这个map的value是Set of Vertex，后来发现用Integer记录访问过的index**，能达到同样的效果，而且比Set of Vertex更省空间。  

