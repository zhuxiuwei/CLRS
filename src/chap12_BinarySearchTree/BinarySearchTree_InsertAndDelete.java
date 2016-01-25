package chap12_BinarySearchTree;

/**
 * 12.2 二叉搜索树的插入和删除
 * @author xiuzhu
 * 160118
 */
public class BinarySearchTree_InsertAndDelete {

	private BinaryTree<Integer> tree;
	
	public BinarySearchTree_InsertAndDelete(){
		this.tree = new BinaryTree<Integer>();
	}
	
	/**
	 * 插入结点
	 * @param newNode 新的要插入的结点
	 */
	public void insert(TreeNode<Integer> newNode){
		if(tree.root == null)	//empty tree
			tree.root = newNode;
		else{
			TreeNode<Integer> cur = tree.root;
			TreeNode<Integer> p = tree.root;
			while(cur != null){
				p = cur;
				if(cur.key < newNode.key)
					cur = cur.right;
				else 
					cur = cur.left;
			}
			if(p != null){
				if(p.key < newNode.key)
					p.right = newNode;
				else
					p.left = newNode;
				newNode.parent = p;
			}
		}
	}
	
	/**
	 * 插入结点递归算法
	 * @param newNode 新的要插入的结点
	 */
	public void insert_recursive(TreeNode<Integer> newNode){
		if(tree.root == null){	//empty tree
			tree.root = newNode;
			return;
		}
		insert_recursive(newNode, tree.root);
	}
	private void insert_recursive(TreeNode<Integer> newNode, TreeNode<Integer> currentNode){
		if(currentNode.left == null && currentNode.right == null){	//到了要插入的地方了
			if(currentNode.key < newNode.key)
				currentNode.right = newNode;
			else
				currentNode.left = newNode;
			newNode.parent = currentNode;
		}else{	//递归
			if(currentNode.key < newNode.key){
				if(currentNode.right == null){
					currentNode.right = new TreeNode<Integer>(0);	//new a node
					currentNode.right.parent = currentNode;
				}
				insert_recursive(newNode, currentNode);
			}
			else{
				if(currentNode.left == null){
					currentNode.left = new TreeNode<Integer>(0);	//new a node
					currentNode.left.parent = currentNode;
				}
				insert_recursive(newNode, currentNode);
			}
		}
	}
	
	/**
	 * 删除节点
	 */
	
	
	public static void main(String[] args) {
		BinarySearchTree_InsertAndDelete inAndDel = new BinarySearchTree_InsertAndDelete();
		//test insert. Below will create tree in p163 image 12-2
		inAndDel.insert(new TreeNode<Integer>(15));
		inAndDel.insert(new TreeNode<Integer>(6));
		inAndDel.insert(new TreeNode<Integer>(18));
		inAndDel.insert(new TreeNode<Integer>(3));
		inAndDel.insert(new TreeNode<Integer>(2));
		inAndDel.insert(new TreeNode<Integer>(4));
		inAndDel.insert(new TreeNode<Integer>(7));
		inAndDel.insert(new TreeNode<Integer>(13));
		inAndDel.insert(new TreeNode<Integer>(9));
		inAndDel.insert(new TreeNode<Integer>(17));
		inAndDel.insert(new TreeNode<Integer>(20));
		System.out.print("test insert, pre-order:");inAndDel.tree.traversing_preorder(inAndDel.tree.root);
		System.out.print("\r\ntest insert, in-order:");inAndDel.tree.traversing_inorder(inAndDel.tree.root);
		System.out.print("\r\ntest insert, post-order:");inAndDel.tree.traversing_postorder(inAndDel.tree.root);
		
		BinarySearchTree_InsertAndDelete inAndDel2 = new BinarySearchTree_InsertAndDelete();
		//test insert_recursive recursive. Below will create tree in p163 image 12-2
		inAndDel2.insert_recursive(new TreeNode<Integer>(15));
		inAndDel2.insert_recursive(new TreeNode<Integer>(6));
		inAndDel2.insert_recursive(new TreeNode<Integer>(18));
		inAndDel2.insert_recursive(new TreeNode<Integer>(3));
		inAndDel2.insert_recursive(new TreeNode<Integer>(2));
		inAndDel2.insert_recursive(new TreeNode<Integer>(4));
		inAndDel2.insert_recursive(new TreeNode<Integer>(7));
		inAndDel2.insert_recursive(new TreeNode<Integer>(13));
		inAndDel2.insert_recursive(new TreeNode<Integer>(9));
		inAndDel2.insert_recursive(new TreeNode<Integer>(17));
		inAndDel2.insert_recursive(new TreeNode<Integer>(20));
		System.out.print("\r\ntest insert_recursive, pre-order:");inAndDel2.tree.traversing_preorder(inAndDel2.tree.root);
		System.out.print("\r\ntest insert_recursive, in-order:");inAndDel2.tree.traversing_inorder(inAndDel2.tree.root);
		System.out.print("\r\ntest insert_recursive, post-order:");inAndDel2.tree.traversing_postorder(inAndDel2.tree.root);
		
	}
}
