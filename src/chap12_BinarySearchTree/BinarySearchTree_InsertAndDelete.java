package chap12_BinarySearchTree;

/**
 * 12.2 二叉搜索树的插入和删除
 * @author xiuzhu
 * 注意点1：插入节点递归算法，找到要插入的地方的条件开始写错了，写成了currentNode.left == null && currentNode.right == null，浪费不少时间。需要先仔细想想。
 * **注意点2(160126)**： 删除结点时，如果被删除的结点是root，需要特殊考虑！为此抽象出来了updateParentPointToNewChild()方法。
 * 160118
 */
public class BinarySearchTree_InsertAndDelete {

	private BinaryTree<Integer> tree;
	
	public BinarySearchTree_InsertAndDelete(){
		this.tree = new BinaryTree<Integer>();
	}
	public BinarySearchTree_InsertAndDelete(BinaryTree<Integer> tree){
		this.tree = tree;
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
		//注意点1：找到要插入的地方，开始写错了，写成了currentNode.left == null && currentNode.right == null，浪费不少时间
		if(currentNode.left == null && currentNode.key >= newNode.key){	//到了要插入的地方了
			currentNode.left = newNode;
			newNode.parent = currentNode;
			return;
		}else if(currentNode.right == null && currentNode.key <= newNode.key){	//到了要插入的地方了
			currentNode.right = newNode;
			newNode.parent = currentNode;
			return;
		}
		else{	//递归
			if(currentNode.key < newNode.key){
				insert_recursive(newNode, currentNode.right);
			}
			else{
				insert_recursive(newNode, currentNode.left);
			}
		}
	}
	
	/**
	 * 删除节点。
	 * @param key 待删除的元素
	 * @return true if deleted, false if not.
	 */
	public boolean delete(int key){
		//先查找。
		BinarySearchTree_Search search = new BinarySearchTree_Search(tree);
		TreeNode<Integer> target = search.tree_search(tree.root, key);
		if(target == null){
			return false;	//key not in tree, return false;
		}else{
			if(target.left == null && target.right == null){ //scenario 1: target is leaf
				updateParentPointToNewChild(target, null, tree); //update parent's point. If deleted is root, got to update tree's root pointer.
				disconnectNode(target);	//disconnect target node from the tree.
			}else if(target.left == null && target.right != null){	//scenario 2.1: target has only right child
				target.right.parent = target.parent;
				updateParentPointToNewChild(target, target.right, tree);
				disconnectNode(target);
			}else if(target.right == null && target.left != null){	//scenario 2.2: target has only left child
				target.left.parent = target.parent;
				updateParentPointToNewChild(target, target.left, tree);
				disconnectNode(target);
			}else{	//scenario 3: target has both left and right nodes.
				TreeNode<Integer> successor = search.successor(key);	//find successor
				if(target.right == successor){	//scenario 3.1, successor is right child
					updateParentPointToNewChild(target, successor, tree);
					successor.left = target.left;
					successor.parent = target.parent;
					disconnectNode(target);
				}else{	//scenario 3.2, successor is not right child, but in right side tree.
					target.key = successor.key;	//replace target with successor.
					
					//then delete successor
					BinaryTree<Integer> tree2 = new BinaryTree<Integer>();
					tree2.root = target.right;
					BinarySearchTree_InsertAndDelete del = new BinarySearchTree_InsertAndDelete(tree2);
					del.delete(successor.key);
				}
			}
			return true;
		}
	}
	
	/**
	 * Update target's parent's pointer which previously points to target. Make it point to newChild now.
	 * Note that, if target is root, consider parent to be root pointer of the tree.
	 * @param target Change the target's parent's pointer which previously points to target.
	 * @param newChild Make the pointer of parent points to a new child. 
	 * @param tree The tree where the target nodes lives. (We need the tree reference when target is root node)
	 */
	private void updateParentPointToNewChild(TreeNode<Integer> target, TreeNode<Integer> newChild, BinaryTree<Integer> tree){
		if(target.parent != null){	//not root
			if(target.parent.left == target)
				target.parent.left = newChild;
			else
				target.parent.right = newChild;
		}else	//is root
			tree.root = newChild;	//！！！注意点2：如果被删除的结点是root，必须特殊考虑！否则会出现bug！！！
	}
	
	/**
	 * After target is deleted from tree, make target node's left, right, parent all point to null to disconnect it from the tree so GC can recycle it later.
	 */
	private void disconnectNode(TreeNode<Integer> target){
		target.parent = null;
		target.left = null;
		target.right = null;
	}
	
	public static void main(String[] args) {
		BinarySearchTree_InsertAndDelete inAndDel = new BinarySearchTree_InsertAndDelete();
		//test insert. 
		/***************************
		 * Below will create tree in p163 image 12-2：
		 *       15
		 *      /  \
		 *     6    18
		 *    / \   / \
		 *   3   7 17 20
		 *  /\    \      
		 * 2  4   13
		 *        /
		 *       9    
		 ********Image 12-2*********/
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
		
		//test delete
		inAndDel.delete(9);
		inAndDel.delete(7);
		inAndDel.delete(6);
		inAndDel.delete(20);
		inAndDel.delete(17);
		inAndDel.delete(15);
		inAndDel.delete(18);
		System.out.print("\r\ntest delete, pre-order:");inAndDel.tree.traversing_preorder(inAndDel.tree.root);
		System.out.print("\r\ntest delete, in-order:");inAndDel.tree.traversing_inorder(inAndDel.tree.root);
		System.out.print("\r\ntest delete, post-order:");inAndDel.tree.traversing_postorder(inAndDel.tree.root);
		
	}
}
