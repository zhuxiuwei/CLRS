第9章习题答案
=
#####9.1-1 找到n个元素中第二小的元素，最坏情况下需要n+⌈lgn⌉-2次比较的算法？  
不会，看的[答案](http://clrs.skanev.com/09/01/01.html)。思路是两对儿两对儿的比：  
We can compare the elements in a tournament fashion - we split them into pairs, compare each pair and then proceed to compare the winners in the same fashion. We need to keep track of each "match" the potential winners have participated in.
We select a winner in n−1 matches. At this point, we know that the second smallest element is one of the lgn elements that lost to the smallest ­ each of them is smaller than the ones it has been compared to, prior to losing. In another ⌈lgn⌉−1 comparisons we can find the smallest element out of those. This is the answer we are looking for.  
代码见[Find2ndSmallest](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap09_MediansAndOrderStatistics/Find2ndSmallest.java)。  
过程如下图：  
![](https://github.com/zhuxiuwei/CLRS/blob/master/Images/9.1-1.jpg)  

