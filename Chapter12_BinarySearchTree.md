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
代码见[BinaryTree](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap12_BinarySearchTree/BinarySearchTree_Search.java)
的tree_min_recursive()和tree_max_recursive()方法  

#####12.2-2 Tree-Predecessor的代码  
代码见[BinaryTree](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap12_BinarySearchTree/BinarySearchTree_Search.java)
的predecessor()方法  

12.2-4 Professor Bunyan thinks he has discovered a remarkable property of binary search trees. 
Suppose that the search for key k in a binary search tree ends up in a leaf. 
Consider three sets: 
A, the keys to the left of the search path; B, the keys on the search path; and C, the keys to the right of the search path.
Professor Bunyan claims that any three keys a ∈ A, b ∈ B,and c ∈ C must satisfy a ≤ b ≤ c. 
Give a smallest possible counterexample to the professors claim.  
答：考虑以下二叉树：  
![](https://github.com/zhuxiuwei/CLRS/blob/master/Images/12.2-4.png)  
查找节点20，则集合A={17}, 集合B={15,18,29},集合C={φ}，并不满足集合A中的任意元素<=B中的。（17 v.s. 15）  

#####12.2-5 证明一棵二叉搜索树的一个节点有两个孩子，则它的后继没有左孩子，它的前驱没有右孩子。  
答：如果有两个节点，则：  
后继一定是右子树中最小的。最小的节点一定是最左的节点，所以它的后继一定没有左孩子。  
前驱一定是左子树中最大的。最大的节点一定是最右的节点，所以它的前驱一定没有右孩子。  

#####12.2-6 考虑一棵关键字各不相同的二叉查找树T。证明：如果T中某个结点x的右子树为空，且x有一个后继y，那么y就是x的最低祖先，且其左孩子也是x的祖先。（注意每个结点都是它自己的祖先。）  
答：（这个题就是[BinaryTree](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap12_BinarySearchTree/BinarySearchTree_Search.java)中predecessor()方法的注意点2）  
首先x有后继并且右子树为空，因为x的后继不可能在左子树中（都比x小），所以y一定是x的祖先；  
其次，x的父节点又分为两种情况：x是其父结点的左子树，x是其父结点的右子树。  
1. 若x是其父结点的左子树，则父结点>=x，有成为x后继的资格。然后，父结点的父结点）一定不会是x的后继，原因如下
：若父结点是爷爷结点的右子树，说明爷爷结点比x小，不能成为后继；若父节点是爷爷结点的左子树，说明爷爷结点比父节点还大；故后继y一定是父节点。  
2. 若x是其父结点的右子树，父节点<=x，不能成为x的后继。只能再往上找，而且必须“往右找”，即这个祖先结点的左孩子也是祖先结点。（因为往左找是越找越小）。若找不到的话，说明当前结点是树中最大结点（一路向左）。  
