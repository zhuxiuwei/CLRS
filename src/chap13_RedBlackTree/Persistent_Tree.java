package chap13_RedBlackTree;

import java.util.Random;

/**
 * 13-1持久树（即Immutable tree）。
 * @author xiuzhu 
 * 160303
 */
public class Persistent_Tree {
	private boolean debug = true;
	TreeNode root;

	public Persistent_Tree(){}
	public Persistent_Tree(TreeNode root){
		this.root = root;
	}
	
	/**
	 * Insert a new node into a Persistent_Tree
	 * @param newNode
	 */
	public void insert(TreeNode newNode){
		if(root == null){	//empty tree
			root = newNode;
		}else{
			//Create a new root node
			TreeNode oldRoot = root;
			TreeNode newRoot = new TreeNode(root.key);
			this.root = newRoot;
			newRoot.left = oldRoot.left;
			newRoot.right = oldRoot.right;
			
			TreeNode y = root;	//parent
			TreeNode x = root;	//place to insert new node
			while(x != null){
				if(newNode.key < y.key){
					x = y.left;
					if(x != null){
						TreeNode copyNode = new TreeNode(x.key);	//new a node
						y.left = copyNode;
						copyNode.left = x.left;
						copyNode.right = x.right;
						x = copyNode;
						y = x;
					}else
						y.left = newNode;
				}else{
					x = y.right;
					if(x != null){
						TreeNode copyNode = new TreeNode(x.key);	//new a node
						y.right = copyNode;
						copyNode.left = x.left;
						copyNode.right = x.right;
						x = copyNode;
						y = x;
					}
					else
						y.right = newNode;
				}
			}
			//每次插入时，打印旧的树和新的树来debug。
			if(debug){
				System.out.println("old tree:");
				traversing_preorder(oldRoot);System.out.println("");
				traversing_inorder(oldRoot);System.out.println("");
				traversing_postorder(oldRoot);System.out.println("");
				System.out.println("new tree:");
				traversing_preorder(newRoot);System.out.println("");
				traversing_inorder(newRoot);System.out.println("");
				traversing_postorder(newRoot);System.out.println("");
			}
		}
	}
	
	/**
	 * 先根遍历二叉树
	 */
	public void traversing_preorder(TreeNode node){
		if(node != null){
			System.out.print(node + " ");
			traversing_preorder(node.left);
			traversing_preorder(node.right);
		}
	}
	
	/**
	 * 中根遍历二叉树
	 */
	public void traversing_inorder(TreeNode node){
		if(node != null){
			traversing_inorder(node.left);
			System.out.print(node + " ");
			traversing_inorder(node.right);
		}
	}
	
	/**
	 * 后根遍历二叉树
	 */
	public void traversing_postorder(TreeNode node){
		if(node != null){
			traversing_postorder(node.left);
			traversing_postorder(node.right);
			System.out.print(node + " ");
		}
	}
	
	public static void main(String[] args) {
		Persistent_Tree t = new Persistent_Tree();//构建图13.8中的二叉树
		t.insert(new TreeNode(4));
		t.insert(new TreeNode(3));
		t.insert(new TreeNode(2));
		t.insert(new TreeNode(8));
		t.insert(new TreeNode(10));
		t.insert(new TreeNode(7));
		t.insert(new TreeNode(5));
	}

}

//No parent node.
class TreeNode{
	int key;
	int id;
	TreeNode left = null;
	TreeNode right = null;
	public TreeNode(int val){
		key = val;
		id = new Random().nextInt(10000);	//id用来方便debug:两个相同value的node实际不是同一个。
	}
	@Override
	public String toString(){
		return key + ":" + id;
	}
}