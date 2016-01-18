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
		inAndDel.tree.traversing_preorder(inAndDel.tree.root);System.out.println();
		inAndDel.tree.traversing_inorder(inAndDel.tree.root);System.out.println();
		inAndDel.tree.traversing_postorder(inAndDel.tree.root);System.out.println();
		
		
	}
}
