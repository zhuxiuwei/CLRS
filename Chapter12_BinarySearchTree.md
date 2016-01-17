第12章习题答案
=
#####12.1-1 对关键字集合{1,4,5,10,1,17,21}，分别画出高度2、3、4、5、6的二叉搜索树。  
答：见图：  ![](https://github.com/zhuxiuwei/CLRS/blob/master/Images/12.1-1.png)  

#####12.1-2 二叉搜索树与最小堆性质之间有什么不同？能否使用最小堆性质在O(n)时间内按序输出一课有n个结点树的关键字么？。  
答：二叉搜索树左孩子<=自己，右孩子>=自己。最小堆左右孩子都>=自己。  
二叉搜索树可以不是完全二叉树。最小堆是完全二叉树。  
不能。堆排序也是基于比较的排序，下限是O(nlgn)。  

#####12.1-3 中序遍历的非递归算法。  
代码见[BinaryTree的traversing_inorder_nonrescurive](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap12_BinarySearchTree/BinaryTree.java)  

#####12.1-4 对于有n个节点的树，设计O(n)的先序遍历和后序遍历算法。  
代码见[BinaryTree](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap12_BinarySearchTree/BinaryTree.java)  
递归和非递归都实现了。  

#####12.2-1 哪个不是合法的二叉树查找序列。  
c，911 240 912不对。  
e，347,621,299不对。  

#####12.2-2 Tree-min和tree-max的递归版本  
代码见[BinaryTree的traversing_inorder_nonrescurive](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap12_BinarySearchTree/BinarySearchTree_Search.java)
的tree_min_recursive()和tree_max_recursive()方法  

#####12.2-2 Tree-Predecessor的代码  
代码见[BinaryTree的traversing_inorder_nonrescurive](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap12_BinarySearchTree/BinarySearchTree_Search.java)
的predecessor()方法  
