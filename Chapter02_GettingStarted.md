第二章习题答案
=
#### 习题2.2-2 写出选择算法的代码，循环不定式，为什么只对前n-1个元素而不是前n个元素运行，用θ表示最好和最坏运行时间  
1. 代码见[prac222_SelectionSort](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap02_GettingStarted/prac222_SelectionSort.java)  
2. 代码中，i=0开始第一次循环，i表示无序区中第一个元素，依次循环过后这个元素称为有序曲最后一个元素，则：  
	初始化： 当i=0时，有序区还没有元素，没有元素自然也可以认为是有序的，循环不变式成立；  
	保持： 假设数组A[1...j]当前是有序的，那么A[1...j]中的元素一定比数组中剩下的元素都小（不大于），下次迭代将会使A[j+1]交换到剩下元素中最小的元素，那么a[1...j+1]还是有序的，循环不定式成立  
	终止： 研究循环终止时发生什么。循环终止时，原数组中前n-1个元素都在有序曲，只有一个元素在无序区，而按照算法的特性无序区中的元素都大于等于有序区中的元素，故整个数组依然有序。算法成立。  
3. 因为根据算法特性，有序曲中的元素都小于等于无序区中的元素，只对前n-1个元素运行即可以保证整个数组有序。  
4. 不论最坏情况还是最好情况：  
	都需要进行n-1轮元素比较，每轮比较的次数为： n, n-1, ..., 2，总的比较次数为(n-1)\*(n+2)/2，最好最坏都是：θ(n^2)  
  
#### 习题2.2-3 线性查找相关
代码见[prac213_linearSearch](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap02_GettingStarted/prac213_linearSearch.java)  
假设要查找的元素等可能地为数组中的元素，则:  
1. 平均查找多少个元素？  -- (1 + 2 + ... + n)/n = (n+1)/2个。  
2. 最坏呢？   -- n个  
3. 都是θ(n)  

#### 习题 2.2.4 应该如何修改任意一个算法，才能使之具有良好的最好情况运行时间？  
不太明白习题的意思。  
英文答案：Modify the algorithm so it tests whether the input satisﬁes some special-case condition and, if it does, output a pre-computed answer. 
The best-case running time is generally not a good measure of an algorithm.
  
#### 习题2.3-2 不使用哨兵的merge  
见[Text2_3_MergeSort.java之new_merge()方法](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap02_GettingStarted/Text2_3_MergeSort.java)  
  
#### 习题2.3-3  用数学归纳法证明等式成立。  
1. 当k=1时， n=2^k=2，等式成立。  
2. 假设k=i时，  
		T(n)=T(2^i) = 2T(2^i/2) + 2^i = 2^i x lg2^i   成立  
3. 则k=i+1时  
		T(n)=T(2^(i+1))=2xT(2^(i+1)/2) + 2^(i+1)  
			=2 x T(2^i) + 2^i x 2		//把等式2 T(2^i0 = 2^i x lg2^i代入  
			=2 x 2^i x lg2^i  +  2^i x 2  
			=2^(i+1) x (lg2^i + 1)  
			= 2^(i+1) x (lg2^i + lg2)  
			=2^(i+1) x log2^(i+1)  
	证毕。    
	
#### 习题2.3-4 插入排序最坏情况的递归式  
1. 插入排序递归写法见 [prac234_InsertSort_Recursive](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap02_GettingStarted/prac234_InsertSort_Recursive.java)  
2. 递归式：  
	1. n=1, 为常量c\*1；
	2. n>1时，我们把递归过程分为分解、处理、合并三部分。
		其中分解、合并部分都是n的线性函数，记为c1\*n, c2\*n, 可以合并为 c\*n
		处理部分，假设规模为n,当i=1...n时，最坏情况下的比较次数分别为
		i  			1	2	3  。。。n
		比较次数		0	1	2 ... n-1
		故全部比较次数为 0 + 1 + 2 + ... + n-1 = (n-1)(n-2)/2
	         可以记为(n-1)(n-2)/2 + c\*n
	 综上，递归式为：  
	T(n) = c (n=1)  
	T(n) = (n-1)(n-2)/2 + c\*n    (n>1)  
	
#### 习题2.3-5 二分查找 	   
 代码见：[prac235_BinarySearch](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap02_GettingStarted/prac235_BinarySearch.java)  
 
#### 习题2.3-6 （答案不确定） 插入排序的while顺序查找部分改成二分查找时，性能能否提高到O(nlgn)？  
相当于处理部分从(n-1)(n-2)/2 + cn 变为 lg1+lg2+...+lg(n-1) +c\*n，忽略c\*n  
即证明 lg1+lg2+...+lg(n-1) 是否等于nlgn  
nlgn可以写成lgn + lgn + lgn  
而lg1<lgn, lg2<lgn...  
故能收敛到O(nlgn)  
	  
#### 习题2.3-7  
可以。  
思想：  
先用O(nlgn)算法对集合排序。  
然后调用二分查找。  
（当然用HashSet辅助的话，更快。）  
代码： [prac237_FindTwoElementsSum](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap02_GettingStarted/prac237_FindTwoElementsSum.java)
	
###-------- 思考题 ----------
#####思考题2.4 逆序对
代码：  [Thinks24_Inversion_NiXuDui](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap02_GettingStarted/Thinks24_Inversion_NiXuDui.java)

