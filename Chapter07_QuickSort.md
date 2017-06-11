第7章习题答案
=
#### 7.1-1 快速排序的partition在数组A=⟨13,19,9,5,12,8,7,4,21,2,6,11⟩.上的操作过程  
操作过程见下图：  
![](https://github.com/zhuxiuwei/CLRS/blob/master/Images/7.1-1.png)  

#### 7.1-2 当数组A[p..r]中的元素都相同时，PARTITION返回的Q值是什么？  
如下图所示，返回的就是r:    
![](https://github.com/zhuxiuwei/CLRS/blob/master/Images/7.1-2.png)  

#### 7.1-4 Quick PARTITION的非递增排序  
代码见[QuickSort_Partition](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap07_QuickSort/QuickSort_Partition.java)的**partition_desc**方法。  

### -------- 思考题 ----------  
#### 7-1 Hoare划分（原版的quick sort）  
代码见[QuickSort_Partition](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap07_QuickSort/QuickSort_Partition.java)的**partition_traditional**方法。  

#### 7-2针对相同元素的快速排序  
a.时间复杂度是？ O(n^2)，因为每次划分为n-1和0.  

#### 7-3快速排序的栈深度  
代码见[QuickSort_TailRecursive](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap07_QuickSort/QuickSort_TailRecursive.java)  
b. （何时QuickSort_TailRecursive的栈深度是n?） 即**最坏情况**，当数组元素有序时。期望应该是lgN  
