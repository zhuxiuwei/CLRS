第21章 用于不相交集合的数据结构
=
##21.1 不相交集合的操作  
#####21.1-3 During the execution of CONNECTED-COMPONENTS on an undirected graph G = (V, E) with k connected components, how many times is FIND-SET called? How many times is UNION called? Express your answers in terms of |V|, |E|, and k.  
find-set: 2*|E|  
union: |V| - K  

##21.2 不相交集合的链表操作  
#####21.2-1 Write pseudocode for MAKE-SET, FIND-SET, and UNION using the linked-list representation and the weighted-union heuristic. Assume that each object x has an attribute rep[x] pointing to the representative of the set containing x and that each set S has attributes head[S], tail[S], and size[S] (which equals the length of the list).  
See [code](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap21_DisjointSets/DisjointSet.java).  


