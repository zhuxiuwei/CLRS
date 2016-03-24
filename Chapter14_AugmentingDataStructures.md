第14章习题答案
=
##14.1动态顺序统计   
#####14.1-1 Show how OS-SELECT(T.root, 10) operates on the red-black tree T of Figure 14.1  
第1轮: x=26, r=13, i=10<r, 做 OS-SELECT(17, 10)  
第2轮: x=17, r=8, i=10>r，做 OS-SELECT(21, 10-8=2)  
第3轮: x=21, r=3, i=2<r, 做OS-SELECT(19, 2)  
第4轮: x=19, r=1(x.left是叶子节点), i=2>r, 做OS-SELECT(20, 2-1)  
第5轮: x=20，r=1(x.left是叶子节点)，i=1, i=r找到了。结果就是20。  

#####14.1-2 Show how OS-RANK(T, x) operates on the red-black tree T of Figure 14.1 and the node x with x.key=35.  
初始化： r=1(x.left是叶子节点)， y=35。  
循环1： y=38;  
循环2： r=1+1+1=3, y=30;  
循环3： y=41;  
循环4： r=3+12+1=16, y=26，是root;  
退出循环，结果为r=16.  

#####14.1-3 Write a nonrecursive version of OS-SELECT  
```Java
OS-SELECT-NonRecursive(x, i)  
	do  
	{  
		r = x.left.size + 1  
		if i == r  
			return x  
		else if i < r  
			x = x.left;  
		else  
			x = x.right;  
			i = i - r;  
	}while(i != r)  
```
#####14.1-4 Write a recursive procedure OS-KEY-RANK(T, k) that takes as input an order-statistic tree T and a key k and returns the rank of k in the dynamic set represented by T . Assume that the keys of T are distinct.  
（和OS-RANK(T, k)不一样，这里输入的k是key，而OS-RANK的输入是一个node。）  
非递归版本：  
```Java
OS-KEY-RANK-NonRecursive(T, k)
	x = T.root;
	y = T.root;
	int r = 0;
	while( x! =null )
		y = x;
		if(x.key == k)	//found
			r = r + x.left.size +1;
			break;
		else if(x.key > k)
			x = x.left;
		else
			x = x.right;
			r = r + y.left.size + 1
	if(x  == null)	//not found k
		return 0;
	else
		return r;
```
递归版本：  
```Java
bool found = true;
OS-KEY-RANK(T, k)
	result = OS-KEY-RANK(T.root, k)
	if(found)
		return result;
	else
		return 0;	//k not found

OS-KEY-RANK(node, k)
	if(node == null)	//k not found
		found = false;
		return;
	else if(node.key == k)
		return node.left.size + 1;
	else  if(node.key > k)
		return OS-KEY-RANK(node.left, k)
	else
		return OS-KEY-RANK(node.right, k) + node.left.size + 1;
```
#####14.1-5 确定元素x的第i个后继，时间为log(n)  
```Java
OS-FIND-I-SECCEED(x, i)
	int rOfx = OS-RANK(T, x);	//先调用OS-RANK计算x的秩
	return OS-SELECT(T.root, rOfx + i); //再调用OS-SELECT，查找x第i个后继结点
```
#####14.1-7 用顺序统计树计算大小为n的数组中的逆序对。  
大致思路：记数组a[n]有n个元素，需要迭代构建n次顺序统计树。构建时，为元素a[i]构建的树中包括元素a[i]本身到数组中最后一个元素(不包括数组中在它之前的元素！)。每次构建时间复杂度是O(lgn)。  
每次构建好后，可以用O(lgn)时间算出元素a[i]的秩r(i)，则元素i的逆序对为r(i) - 1；  
这样一次计算好了每个元素的逆序对值，把这些值加起来，就是最后整个素组的逆序对个数。所用时间复杂度为nx2lg(n)=nlg(n)  
例子： a[6] = {41, 38, 31, 12, 19, 8}  
对41及其后面元素构建的树，r[41]=6，41的逆序对为6-1=5；  
对38及其后面元素构建的树，r[38]=5，38的逆序对为5-1=4；  
对31及其后面元素构建的树，r[31]=4，31的逆序对为4-1=3；  
对12及其后面元素构建的树，r[12]=2，12的逆序对为2-1=1；  
对19及其后面元素构建的树，r[19]=2，19的逆序对为2-1=1；  
对8及其后面元素构建的树，r[8]=1，8的逆序对为1-1=0；  
最终结果为5+4+3+1+1+0=14  

#####14.3-1 Write pseudocode for LEFT-ROTATE that operates on nodes in an interval tree and updates the max attributes in O(1) time.  
在区间树的LEFT-ROTATE操作中，需要修改max属性的只有参与旋转的两个node。因此，区间树的LEFT-ROTATE操作，除了常规红黑树的LEFT-ROTATE外，需要加上以下两行code:  
```Java
	x.max = max(x.left.max, x.right.max, x.int.high)
	y.max = max(y.left.max, y.right.max, y.int.high)
```

#####14.3-2 Rewrite the code for INTERVAL-SEARCH so that it works properly when all intervals are assumed to be open.  
```Java
INTERVAL-SEARCH-OPEN(T, i)
	x = T.root
 	while x != T.nil and i does not overlap x.int
		if x.left != T.nil and x.left.max > i.low
			x = x.left
		else x = x.right
	return x  
```

#####14.3-3 Describe an efficient algorithm that, given an interval i, returns an interval overlapping i that has the minimum low endpoint, or T.nil if no such interval exists.  
```Java
INTERVAL-SEARCH-MINIMUM(T, i)
	x = T.root
	result = T.nil;
 	while x != T.nil
 		if i overlap x.int
 			if(result == T.nil || result.low < x.low)
 				result = x;
		if x.left != T.nil and x.left.max >= i.low
			x = x.left
		else x = x.right
	return result  
```

#####14.3-4 Given an interval tree T and an interval i, describe how to list all intervals in T that overlap i in O(min(n, k lgn)) time, where k is the number of intervals in the output list. (Hint: One simple method makes several queries, modifying the tree between queries. A slightly more complicated method does not modify the tree.)  
simple method: 每找到一个overlap的interval，把这个interval从interval tree删除掉，然后重新query。  
more complicated method: 思路是，即使x.left满足条件时，也要递归search右子树。找右子树的时候，可以判断i和当前x的后继y的int，若y.int.low <= i.int.high的话，继续search i'=(y.int.min, i.int.high)，代码：  
```Java
Node[] result;
INTERVAL-SEARCH-ALL(T, i)
	x = T.root
	result = T.nil;
 	while x != T.nil
 		if i overlap x.int
 			result.add(x)
		if x.left != T.nil and x.left.max >= i.int.low
			y = x.successor
			if(y != T.Nil && y.int.low <= i.int.high)
 				i' = (y.int.low, i.int.high)
 				INTERVAL-SEARCH-ALL(T, i')
			x = x.left
		else x = x.right
```

#####14.3-5 INTERVAL-SEARCH-EXACTLY(T, i)，精确查找。O(lgn)。  
思路就是二叉树搜索。（假设结点的int.low都是不同的。相同的话还要麻烦些）。    
```Java
INTERVAL-SEARCH-EXACTLY(T, i)
	x = T.root
 	while x != T.nil
 		if i.int.low == x.int.low and i.int.high == x.int.high
 			return x
		if x.int.low >= i.low
			x = x.left
		else x = x.right
```

#####14.3-6 Show how to maintain a dynamic set Q of numbers that supports the operation MIN-GAP, which gives the magnitude of the difference of the two closest numbers in Q. For example, if Q={1,5,9,15,18,22}, then MIN-GAP returns 18-15=3, since 15 and 18 are the two closest numbers in Q. Make the operations INSERT, DELETE, SEARCH, and MIN-GAP as efficient as possible, and analyze their running times.  
显然，最直接的方法 -- 每插入一个数字时线性计算的时间复杂度是O(n)，这个算法希望得到的更优的复杂度是O(lgn)。  
可以使用如下图的红黑树扩展的数据结构：  
![](https://github.com/zhuxiuwei/CLRS/blob/master/Images/14.3-6.png)  
每个节点额外记录两个属性gap_predecessor和GAP_MIN。分别记录和前驱的gap，以及子树（包括自己）中最小的gap。  
###-------- 思考题 ----------  
14.1 最大重叠点  

14.2 Josephus排列  
