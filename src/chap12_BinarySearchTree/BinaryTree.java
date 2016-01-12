package chap12_BinarySearchTree;

import java.util.Stack;

/**
 * 二叉树。
 * @author xiuzhu
 * 151217
 * 注意点：
 * 	1. 中序遍历的非递归还是不熟。debug着写的，手写会有问题。
 * 		其中，需要注意加上currentIsPoped一个判断条件，防止“走回头路”陷入死循环。
 */
public class BinaryTree<E> {
	public TreeNode<E> root = null;
	public BinaryTree(TreeNode<E> _root){
		root = _root;
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
			Stack<TreeNode<E>> stack = new Stack<TreeNode<E>>();
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
	 **/
	public void traversing_preorder_nonrescurive(TreeNode<E> node){
		if(node == null)
			return;
		else{
			System.out.print(node + " ");
			Stack<TreeNode<E>> stack = new Stack<TreeNode<E>>();
			if(node.right != null)
				stack.push(node.right);
			
			while(!stack.isEmpty()){
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
	
	public static void main(String[] args) {
		//create tree。
		/***************************
		 * 下面的代码构造如下的多叉树：
		 *        1
		 *      /   \
		 *     2     3
		 *    /       \
		 *   4         5
		 *  / \       / \  
		 * 6   7     8   9  
		 ***************************/
		TreeNode<Integer> root = new TreeNode<Integer>(1);
		BinaryTree<Integer> t = new BinaryTree<Integer>(root);
		root.addLeftChild(new TreeNode<Integer>(2)
				.addLeftChild(new TreeNode<Integer>(4)
						.addLeftChild(new TreeNode<Integer>(6)).addRightChild(new TreeNode<Integer>(7))));
		root.addRightChild(new TreeNode<Integer>(3)
				.addRightChild(new TreeNode<Integer>(5)
						.addLeftChild(new TreeNode<Integer>(8)).addRightChild(new TreeNode<Integer>(9))));
		
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
