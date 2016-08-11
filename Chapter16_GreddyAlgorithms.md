第16章贪心算法
=
##16.1 活动选择问题  
#####16.1-1 活动选择问题的动态规划算法以及运行时间。  
花了很多时间，__代码还是不对__：[CutRodWithCost](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap16_GreedyAlgorithms/ActivitySelector.java#L37)  
时间应该是O(n^3)  

#####16.1-2 不是选择最早结束，而是选择最晚开始时间活动的贪心算法。  
思路和选择最早结束类似。先把s和f数组，按照s数组倒序排序。然后代码最早结束类似。  
证明思路和定理16.1证明类似： 令Ak为Sk的一个最大活动兼容子集。aj是Ak中最晚开始的活动。am是Sk中最晚开始的活动。用am替代Ak中的aj，获得集合Ak'。Ak'中的活动都是不相交的，因为：Ak中的活动都是不相交的，aj是Ak中最晚开始的活动，而am开始时间比aj还晚。所以|Ak|=|Ak'|，即Ak'也是Sk的一个最大活动兼容子集。  
