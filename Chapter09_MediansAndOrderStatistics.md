第9章习题答案
=
#####9.1-1 找到n个元素中第二小的元素，最坏情况下需要n+⌈lgn⌉-2次比较的算法？    
思路不会，看的[答案](http://clrs.skanev.com/09/01/01.html)。思路是两对儿两对儿的比：  
(1)两两地比，最后找出来最小的。比的时候，要记录下每个winner都和哪些loser比过，和1比过的loser见红圈。本轮一共发生n-1次对比。  
(2)上一步比完了，得到了当前最小的数，以及所有和最小的数比输过的数。第二小的，一定是这些和最小的数比输的数中之一（红圈中的那些数，共⌈lgn⌉个），再从这几个数中选出最小的，就是第二小的数，这一轮一共要比⌈lgn⌉-1次。  
过程如下图：  
![](https://github.com/zhuxiuwei/CLRS/blob/master/Images/9.1-1.png)  
代码自己已实现，见[Find2ndSmallest](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap09_MediansAndOrderStatistics/Find2ndSmallest.java)。  
