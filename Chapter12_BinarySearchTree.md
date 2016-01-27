第12章习题答案
=
#####12.1-1 对关键字集合{1,4,5,10,1,17,21}，分别画出高度2、3、4、5、6的二叉搜索树。  
答：见图：  
![](https://github.com/zhuxiuwei/CLRS/blob/master/Images/12.1-1.png)  

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
1. 若x是其父结点的左子树，则父结点>=x，有成为x后继的资格。然后，父结点的父结点一定不会是x的后继，原因如下
：若父结点是爷爷结点的右子树，说明爷爷结点比x小，不能成为后继；若父节点是爷爷结点的左子树，说明爷爷结点比父节点还大；故后继y一定是父节点。  
2. 若x是其父结点的右子树，父节点<=x，不能成为x的后继。只能再往上找，而且必须“往右找”，即这个祖先结点的左孩子也是祖先结点。（因为往左找是越找越小）。若找不到的话，说明当前结点是树中最大结点（一路向左）。  

#####12.3-1 给出TREE-INSERT过程的一个递归版本。  
代码见[BinarySearchTree_InsertAndDelete](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap12_BinarySearchTree/BinarySearchTree_InsertAndDelete.java)的insert_recursive方法。  
一个__注意点__：插入节点递归算法，找到要插入的地方的条件开始写错了，写成了currentNode.left == null && currentNode.right == null，浪费不少时间。需要先仔细想想。  

#####12.3-2 Suppose that we construct a binary search tree by repeatedly inserting distinct values into the tree. Argue that the number of nodes examined in searching for a value in the tree is one plus the number of nodes examined when the value was first inserted into the tree.  
插入和查找，都是从root向下走到target结点的过程。插入，最后一次比较是和待插入结点的父结点；而查找，最后一次比较的是待查找的结点。查找的比较次数比插入时加一。  

#####12.3-3 利用不断构造一棵二叉搜索树，然后中序遍历的方式来排序的最好最坏时间复杂度。  
最好：树基本是平衡的，O(nlgn)。  
最坏：树只有一个分支，O(n^2)。  

#####12.3-4 delete操作是否是“可交换”的，即先删除x后删除y，和先删除y后删除x，树长得一样？  
（__看的答案。最初我以为是可交换的。__）不是可交换的，如下图：  
![](https://github.com/zhuxiuwei/CLRS/blob/master/Images/12.3-4.png)  

##### 12.3-5 假设为每个结点换一种设计，没有指向x的双亲的属性x.p，而有指向x的后继的属性x.succ。试给出使用这种表示法的二叉搜索树T上SEARCH,INSERT和DELETE操作的伪代码。这些伪代码应在O(h)时间内执行完，其中h为T的高度。(提示：应该设计一个返回某个结点的双亲的子过程。)  
代码见[BinarySearchTree_NodeHasSuccessorPointerButNoParentPointer](https://github.com/zhuxiuwei/CLRS/blob/master/src/chap12_BinarySearchTree/BinarySearchTree_NodeHasSuccessorPointerButNoParentPointer.java)。
完成了插入，查找，删除，以及辅助性的min, predecessor, parent.不算难，比较繁琐。  
