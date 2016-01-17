package chap12_BinarySearchTree;

/**
 * 12.2 二叉搜索树的查找
 * 注意点1：Search的非递归算法，循环的条件要注意。
 * @author xiuzhu
 * 160117
 */
public class BinarySearchTree_Search {

	private BinaryTree<Integer> tree;
	
	public BinarySearchTree_Search(BinaryTree<Integer> tree){
		this.tree = tree;
	}
	
	/**
	 * Search BinarySearchTree recursive.
	 */
	public TreeNode<Integer> tree_search(TreeNode<Integer> node, int key){
		if(node == null || node.key.equals(key))
			return node;
		else{
			if(key < node.key)
				return tree_search(node.left, key);
			else
				return tree_search(node.right, key);
		}
	}
	
	/**
	 * Search BinarySearchTree non-recursive.
	 */
	public TreeNode<Integer> tree_search_nonrecursive(TreeNode<Integer> node, int key){
		TreeNode<Integer> result = node;
		while(result != null && result.key != key){	//注意点1： 循环条件是&&，不是||。否则找到后会仍然进入循环，导致NullPointerException
			if(result.key < key)
				result = result.right;
			else
				result = result.left;
		}
		return result;
	}
	
	/**
	 * Search min in tree
	 */
	public TreeNode<Integer> tree_min(TreeNode<Integer> node){
		TreeNode<Integer> result = node;
		while(result != null){
			if(result.left == null)
				break;
			else 
				result = result.left;
		}
		return result;
	}
	
	/**
	 * Search max in tree
	 */
	public TreeNode<Integer> tree_max(TreeNode<Integer> node){
		TreeNode<Integer> result = node;
		while(result != null){
			if(result.right == null)
				break;
			else 
				result = result.right;
		}
		return result;
	}
	
	/**
	 * Search min in tree - recursive
	 */
	public TreeNode<Integer> tree_min_recursive(TreeNode<Integer> node){
		if(node == null || node.left == null)
			return node;
		else
			return tree_min_recursive(node.left);
	}
	
	/**
	 * Search max in tree - recursive
	 */
	public TreeNode<Integer> tree_max_recursive(TreeNode<Integer> node){
		return node == null || node.right == null ? node: tree_max_recursive(node.right);
	}
	
	
	public static void main(String[] args) {
		TreeNode<Integer> root = new TreeNode<Integer>(15);
		BinaryTree<Integer> tree = new BinaryTree<Integer>(root);
		root.addLeftChild(new TreeNode<Integer>(6)
				.addLeftChild(new TreeNode<Integer>(3)
						.addLeftChild(new TreeNode<Integer>(2))
						.addRightChild(new TreeNode<Integer>(4)))
				.addRightChild(new TreeNode<Integer>(7)
						.addRightChild(new TreeNode<Integer>(13)
								.addLeftChild(new TreeNode<Integer>(9)))));
		root.addRightChild(new TreeNode<Integer>(18)
				.addLeftChild(new TreeNode<Integer>(17))
				.addRightChild(new TreeNode<Integer>(20)));
		
		BinarySearchTree_Search bSearch = new BinarySearchTree_Search(tree);
		
		//test search
		System.out.println("Search recursive: " + bSearch.tree_search(tree.root, 8));
		System.out.println("Search non_recursive: " + bSearch.tree_search_nonrecursive(tree.root, 8));
		System.out.println("min: " + bSearch.tree_min(tree.root));
		System.out.println("max: " + bSearch.tree_max(tree.root));
		System.out.println("min recursive: " + bSearch.tree_min_recursive(tree.root));
		System.out.println("max recursive: " + bSearch.tree_max_recursive(tree.root));
	}

}
