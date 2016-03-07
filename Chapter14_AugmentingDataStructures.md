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
```C
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
非递归：  
```C
OS-KEY-RANK-NonRecursive(T, k)
	x = T.root;
	y = T.root;
	int r = 0;
	while( x! =null )
		y = x;
		if(x.key == k)
			r = r + x.left.size +1;
		elseif(x.key < k)
			x = x.left;
		else
			x = x.right;
			r = r + y.left.size + 1
	if(x  == null)	//not found k
		return 0;
	else
		return r;
```