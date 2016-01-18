package chap12_BinarySearchTree;

import java.util.Stack;

/**
 * 二叉树。
 * @author xiuzhu
 * 151217
 * 注意点：
 * 	1. 中序遍历的非递归还是不熟。debug着写的，手写会有问题。
 * 		其中，需要注意加上currentIsPoped一个判断条件，防止“走回头路”陷入死循环。
 *  2. 先序遍历的非递归的一个bug：退出循环的条件，需要加上node.left != null的判断条件。否则当树的root只有左孩子时，除了root，左孩子都遍历不到。
 *  3. 后序遍历非递归，是三种非递归遍历里最复杂的。和中序遍历一样，后序遍历要记录已经访问过的节点，避免走“回头路”。而这里的逻辑比中序遍历更复杂，是主要的难点。
 *  	后序遍历记录节点的访问，需要两个flag分别记录左右子树的访问情况。这里引出了三个另外的注意点。
 */
public class BinaryTree<E> {
	public TreeNode<E> root = null;
	public BinaryTree(TreeNode<E> _root){
		root = _root;
	}
	public BinaryTree(){	//construct an empty tree
	}
	
	/**
	 * 先根遍历二叉树
	 */
	public void traversing_preorder(TreeNode<E> node){
		if(node != null){
			System.out.print(node.key + " ");
			traversing_preorder(node.left);
			traversing_preorder(node.right);
		}
	}
	
	/**
	 * 中根遍历二叉树
	 */
	public void traversing_inorder(TreeNode<E> node){
		if(node != null){
			traversing_inorder(node.left);
			System.out.print(node.key + " ");
			traversing_inorder(node.right);
		}
	}
	
	/**
	 * 后根遍历二叉树
	 */
	public void traversing_postorder(TreeNode<E> node){
		if(node != null){
			traversing_postorder(node.left);
			traversing_postorder(node.right);
			System.out.print(node.key + " ");
		}
	}
	
	/**
	 * 中根遍历二叉树 - 非递归 （习题12.1-3）
	 **/
	public void traversing_inorder_nonrescurive(TreeNode<E> node){
		//！！注意点1：需要加上这个判断条件。如果一个节点是从stack里pop的，说明它的左孩子已经遍历过了。
		//用这个判断条件，防止“走回头路”选入死循环。
		boolean currentIsPoped = false;	
		if(node == null)
			return;
		else{
			Stack<TreeNode<E>> stack = new Stack<TreeNode<E>>();	//stack存放左孩子，以及右孩子。
			stack.push(node);
			while(!stack.isEmpty()){
				if(node.left != null && !currentIsPoped){	//必须加上currentIsPoped这个判断条件！否则会“走回头路”
					node = node.left;
					stack.push(node);
				}else{
					node = stack.pop();
					currentIsPoped = true;
					System.out.print(node.key + " ");
					if(node.right != null){
						node = node.right;
						currentIsPoped = false;
						stack.push(node);
					}
				}
			}
		}
	}
	
	/**
	 * 先根遍历二叉树 - 非递归（习题12.1-4）
	 * 写的时候，比中跟非递归实现的快一些。
	 **/
	public void traversing_preorder_nonrescurive(TreeNode<E> node){
		if(node == null)
			return;
		else{
			System.out.print(node + " ");
			Stack<TreeNode<E>> stack = new Stack<TreeNode<E>>();	//stack只用来存放右孩子
			if(node.right != null)
				stack.push(node.right);
			
			//！！！注意点2，需要加上node.left != null的判断条件。否则当树的root只有左孩子时，除了root，左孩子都遍历不到。
			while(!stack.isEmpty() || node.left != null){
				if(node.left != null){
					node = node.left;
					System.out.print(node + " ");
					if(node.right != null)
						stack.push(node.right);
				}else{
					node = stack.pop();
					System.out.print(node + " ");
					if(node.right != null)
						stack.push(node.right);
				}
			}
		}
	}
	
	/**
	 * 后遍历二叉树 - 非递归（习题12.1-4）
	 **/
	public void traversing_postorder_nonrescurive(TreeNode<E> node){
		if(node == null)
			return;
		else{
			Stack<TreeNode<E>> stack = new Stack<TreeNode<E>>();	
			stack.push(node);
			boolean isLeftVisisted = false;		//注意点3.1  左右是否访问过都要记录。左右访问记录的赋值是个难点。
			boolean isRightVisisted = false;
			while(!stack.isEmpty()){
				if(node.left != null && !isLeftVisisted){
					stack.push(node.left);
					node = node.left;
					isLeftVisisted = false;
				}else{
					node = stack.peek();
					if(node.right != null && !isRightVisisted){	//if true, left child is visited(or not exist), now visit right child.
						stack.push(node.right);
						node = node.right;
						isLeftVisisted = false;		//注意点3.2 这里需要对访问记录“归位”，否则会出现某些叶节点访问不到的bug。
						isRightVisisted = false;
					}else{	//left&right children all visited(or not exist), now visit self
						TreeNode<E> childNode = stack.pop();
						System.out.print(childNode + " ");
						if(!stack.isEmpty()){	//if stack is empty, it's root node, and can exist loop now.
							node = stack.peek();	//go back to parent node
							if(node.left == childNode){
								isLeftVisisted = true;
								isRightVisisted = false;
							}
							else if(node.right == childNode){
								isRightVisisted = true;		//注意点3.3 后根遍历如果右孩子访问过了，那么左孩子一定也访问过。所以isLeftVisisted = true
								isLeftVisisted = true;
							}
						}
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		//create tree。
		/***************************
		 * 下面的代码构造如下的多叉树：
		 *        1
		 *      /   \
		 *     2     3
		 *    / \     \
		 *   4   5     6
		 *  /\   /\    /  
		 * 7 8  9 10  11   
		 ***************************/
		TreeNode<Integer> root = new TreeNode<Integer>(1);
		BinaryTree<Integer> t = new BinaryTree<Integer>(root);
		
		//可以comment out 左/右孩子，做只有右孩子/左孩子的测试。
		root.addLeftChild(new TreeNode<Integer>(2)
				.addLeftChild(new TreeNode<Integer>(4)
						.addLeftChild(new TreeNode<Integer>(7)).addRightChild(new TreeNode<Integer>(8)))
				.addRightChild(new TreeNode<Integer>(5)
						.addLeftChild(new TreeNode<Integer>(9)).addRightChild(new TreeNode<Integer>(10))));
		root.addRightChild(new TreeNode<Integer>(3)
				.addRightChild(new TreeNode<Integer>(6)
						.addLeftChild(new TreeNode<Integer>(11))));
		
		System.out.print("先根:");
		t.traversing_preorder(t.root);
		System.out.print("\r\n先根非递归:");
		t.traversing_preorder_nonrescurive(t.root);
		System.out.print("\r\n中根:");
		t.traversing_inorder(t.root);
		System.out.print("\r\n中根非递归:");
		t.traversing_inorder_nonrescurive(t.root);
		System.out.print("\r\n后根:");
		t.traversing_postorder(t.root);
		System.out.print("\r\n后根非递归:");
		t.traversing_postorder_nonrescurive(t.root);
	}

}
/**
 * 表示TreeNode的类。
 * @author xiuzhu
 * @param <E> Type of key.
 */
class TreeNode<E>{
	E key = null;
	TreeNode<E> left = null;
	TreeNode<E> right = null;
	TreeNode<E> parent = null;
	public TreeNode(E val){
		key = val;
	}
	@Override
	public String toString(){
		return key + "";
	}
	
	/**
	 * Add left  child for this node. Used to create tree.
	 * @param child	Newly added child.
	 * @return this
	 */
	public TreeNode<E> addLeftChild(TreeNode<E> child){
		left = child;
		child.parent = this;
		return this;
	}
	
	/**
	 * Add right  child for this node. Used to create tree.
	 * @param child	Newly added child.
	 * @return this
	 */
	public TreeNode<E> addRightChild(TreeNode<E> child){
		right = child;
		child.parent = this;
		return this;
	}
}
