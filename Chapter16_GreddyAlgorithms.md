第16章贪心算法
=
##16.1 活动选择问题  
#####16.1-1 活动选择问题的动态规划算法以及运行时间。  
DP memorized版本的代码见：[ActivitySelector](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap16_GreedyAlgorithms/ActivitySelector.java#L36)  
开始花了很多时间也写不对，子问题划分的有些问题。最后参考了[文章](http://www.cs.princeton.edu/~wayne/cs423/lectures/dynamic-programming-4up.pdf)，改了划分方法成功了。__失败的代码__：[ActivitySelector](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap16_GreedyAlgorithms/ActivitySelector.java#L82)  
时间应该是O(NlogN)  

#####16.1-2 不是选择最早结束，而是选择最晚开始时间活动的贪心算法。  
思路和选择最早结束类似。先把s和f数组，按照s数组倒序排序。然后代码最早结束类似。  
证明思路和定理16.1证明类似： 令Ak为Sk的一个最大活动兼容子集。aj是Ak中最晚开始的活动。am是Sk中最晚开始的活动。用am替代Ak中的aj，获得集合Ak'。Ak'中的活动都是不相交的，因为：Ak中的活动都是不相交的，aj是Ak中最晚开始的活动，而am开始时间比aj还晚。所以|Ak|=|Ak'|，即Ak'也是Sk的一个最大活动兼容子集。  

#####16.1-3 Not just any greedy approach to the activity-selection problem produces a maximum-size set of mutually compatible activities. Give an example to show that
the approach of selecting the activity of least duration from among those that are compatible with previously selected activities does not work. Do the same for the approaches of always selecting the compatible activity that overlaps the fewest other remaining activities and always selecting the compatible remaining activity with the earliest start time.  
持续时间最短者反例，会错误的选择第二个活动。正确答案应该是活动1,3： 
s: 1  9 11  
f: 10 12 15  
重叠最少者反例，活动序列形如下图：  
![](https://github.com/zhuxiuwei/CLRS/blob/master/Images/16.1-3.png)  
最少重叠者是第一行中间的活动，而正确答案是第二行的活动。  
最早开始时间反例，会错误第选择活动1.正确答案是活动2,3：  
s: 3 4 1  
f: 4 5 9  

#####16.1-4 假设要用很多个教室对一组活动进行调度。我们希望使用尽可能少的教室来调度所有的活动。请给出一个有效的贪心算法，来确定哪一个活动应使用哪一个教室。(这个问题也被成为区间图着色(interval-graph coloring)问题。我们可作出一个区间图，其顶点为已知的活动，其边连接着不兼容的活动。为使任两个相邻结点的颜色均不相同，所需的最少颜色对应于找出调度给定的所有活动所需的最少教室数。)  

#####16.1-5 （带权重的活动选择问题）每个活动a[i]除了起止时间，还有一个价值（权重）v[i]。求价值最大的兼容活动子集，要求多项式时间。  
书中活动选择问题是此问题的所有活动权重=1的特例。此问题是DP可解的。和16.1-1中的方案类似。代码见：[ActivitySelector](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap16_GreedyAlgorithms/ActivitySelectorWeighted.java#L21)  

##16.1 贪心算法原理  
16.2-2 动态规划算法求解0-1背包问题。  
代码见[0-1背包问题的动态规划算法](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap16_GreedyAlgorithms/KnapsackProblem.java)  
