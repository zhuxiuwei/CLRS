package chap12_BinarySearchTree;
/**
 * 习题12.3-5 没有父亲指针，而有后继指针的结点构成的二叉搜索树的插入，查找和删除。
 * @author xiuzhu
 * 注意点：
 * 	1. 插入时，一个情况需要注意更新successor。就是待插入的结点比当前结点大，但是当前结点的右孩子已经被占据了，此时可能需要更新当前结点的successor。见test中插入9时，7的successor从13更新成9.
 *  2. 查找时，待查找结点比当前结点大，不能写成current = current.successor，还是和传统的查找一样找右子树（我开始还以为这是个比传统二叉搜索树的改进。。。），因为可能陷入死循环。见test中查找5.就会6-3-4-6-3-4...死循环
 *  删除方法，做完了普通二叉搜索树的删除再做这个，不难，但是比较繁琐。
 */
public class BinarySearchTree_NodeHasSuccessorPointerButNoParentPointer {
	
	private BSearchTreeSpecial tree = new BSearchTreeSpecial();
	
	/**
	 * Insert key.
	 * @param key
	 */
	public void insert(int key){
		TreeNodeForBSearchTree newNode = new TreeNodeForBSearchTree(key);
		if(tree.root == null){	//empty tree
			tree.root = newNode;
			return;
		}else{
			TreeNodeForBSearchTree cur = tree.root;
			while(true){
				if(cur.key < key){
					if(cur.right == null){	//找到插入位置
						cur.right = newNode;
						newNode.successor = cur.successor;
						cur.successor = newNode;
						break;
					}else{
						if(cur.successor.key > newNode.key)
							cur.successor = newNode;	//！！！注意点1：这是更新successor要注意，就是插入9的时候，7原来的successor要从13改成9. (而且要注意更新的条件，cur.successor.key > newNode.key，不是cur.right.key > newNode.key.在test最后在多加insert(12)试试。)
						cur = cur.right;
					}
				}else{
					if(cur.left == null){	//找到插入位置
						cur.left = newNode;
						newNode.successor = cur;
						break;
					}else{
						cur = cur.left;
					}
				}
			}
		}
	}
	
	/**
	 * Search a key.
	 * @param key
	 * @return
	 */
	public TreeNodeForBSearchTree search(int key){
		TreeNodeForBSearchTree current = tree.root;
		while(current != null){
			if(key == current.key)	//found
				return current;
			else if(key < current.key)
				current = current.left;
			else
				current = current.right;	//注意点2：不能写成current = current.successor，这样可能会因此死循环！如test中查找5.就会6-3-4-6-3-4...死循环。
		}
		return current;
	}
	
	/**
	 * 删除节点。
	 * @param key 待删除的元素
	 * @return true if deleted, false if not.
	 */
	public boolean delete(int key){
		//先查找。
		TreeNodeForBSearchTree target = search(key);
		if(target == null){
			return false;	//key not in tree, return false;
		}else{
			if(target.left == null && target.right == null){ //scenario 1: target is leaf
				updatePredecessorPointToNewSuccessor(target);
				updateParentPointToNewChild(target, null);
				disconnect(target);
			}else if(target.left == null && target.right != null){	//scenario 2.1: target has only right child
				updatePredecessorPointToNewSuccessor(target);
				updateParentPointToNewChild(target, target.right);
				disconnect(target);
			}else if(target.right == null && target.left != null){	//scenario 2.2: target has only left child
				updatePredecessorPointToNewSuccessor(target);
				updateParentPointToNewChild(target, target.left);
				disconnect(target);
			}
			else{	//scenario 3: target has both left and right nodes.
				if(target.right == target.successor){	//scenario 3.1, successor is right child
					updatePredecessorPointToNewSuccessor(target);
					updateParentPointToNewChild(target, target.successor);
					target.successor.left = target.left;
					disconnect(target);
				}else{	//scenario 3.2, successor is not right child, but in right side tree.
					target.key = target.successor.key;	//replace target with successor.
					TreeNodeForBSearchTree newSuccessor = target.successor.successor;
					
					//then delete successor
					BSearchTreeSpecial tree2 = new BSearchTreeSpecial(target.right);
					BinarySearchTree_NodeHasSuccessorPointerButNoParentPointer del = 
							new BinarySearchTree_NodeHasSuccessorPointerButNoParentPointer();
					del.tree = tree2;
					del.delete(target.successor.key);
					
					//update successor
					target.successor = newSuccessor;
				}
			}
			return true;
		}
	}
	private void updateParentPointToNewChild(TreeNodeForBSearchTree target, TreeNodeForBSearchTree newChild){
		TreeNodeForBSearchTree parent = parent(target.key);	//find parent
		if(parent != null){	//not root
			if(parent.left == target)
				parent.left = newChild;
			else
				parent.right = newChild;
		}else	//is root
			tree.root = newChild;
	}
	private void updatePredecessorPointToNewSuccessor(TreeNodeForBSearchTree target){
		TreeNodeForBSearchTree predecessor = predecessor(target.key);	//find predecessor
		if(predecessor != null)	//not null
			predecessor.successor = target.successor;
	}
	private void disconnect(TreeNodeForBSearchTree target){
		target.left = null; target.right = null; target.successor = null;
	}
	
	/**
	 * 找指定结点的父节点。这个类特有的。（delete依赖于这个方法。）
	 * @param key
	 * @return
	 */
	public TreeNodeForBSearchTree parent(Integer key){
		TreeNodeForBSearchTree result = null;
		if(tree.root == null || tree.root.key == key)	//empty tree return null; or root is the key, root has no parent also return null;
			return null;
		else{
			TreeNodeForBSearchTree cur = tree.root;
			TreeNodeForBSearchTree p = cur;
			while(p != null){
				if(p.key < key) 
					cur = p.right;
				else
					cur = p.left;
				
				if(cur != null && cur.key == key){	//parent found.
					result = p;
					break;
				}
				p = cur;	//keep searching
			}
			return result;
		}
	}
	
	/**
	 * 找前驱结点。和普通的二叉搜索树也不一样。（delete依赖于这个方法。）
	 * @param key
	 * @return  数组key结点的前驱。如果找不到返回Null.
	 */
	public TreeNodeForBSearchTree predecessor(Integer key){
		//search key first
		TreeNodeForBSearchTree node = this.search(key);
		TreeNodeForBSearchTree result = null;
		if(node == null)
			return null;
		
		if(node.left != null)	//如果有左子树，前驱就是左子树中最大的。（从上往下找）
			return maxNode(node.left);
		else{	//没有左子树，前驱是其“最左边”的父节点。如果有parent指针应该从下往上找。因为没有parent指针，只能从上往下找。
			if(tree.root.successor == node)	//root is predecessor
				return tree.root;
			TreeNodeForBSearchTree cur = tree.root;
			TreeNodeForBSearchTree p = cur;
			while(p != null){
				if(p.key < key) 
					cur = p.right;
				else
					cur = p.left;
				
				if(cur != null && cur.successor == node){	//predecessor found.
					result = cur;
					break;
				}
				p = cur;	//keep searching
			}
			return result;
		}
	}
	
	/**
	 * Search min value, start from given node.
	 * @return minimum node
	 */
	public TreeNodeForBSearchTree minNode(TreeNodeForBSearchTree node){
		if(node == null)
			return null;
		while(node.left != null){
			node = node.left;
		}
		return node;
	}
	
	/**
	 * Search max value, start from given node.
	 * predecessor() depends on this method.
	 * @return maximum node
	 */
	public TreeNodeForBSearchTree maxNode(TreeNodeForBSearchTree node){
		if(node == null)
			return null;
		while(node.right != null){
			node = node.right;
		}
		return node;
	}
	
	/**
	 * Scan tree from min to max. (just to help verify)
	 */
	public void goOverTree(){
		System.out.println("Go over tree, root: " + tree.root);
		TreeNodeForBSearchTree node = minNode(tree.root);
		while(node != null){
			System.out.println(node.details());
			node = node.successor;
		}
	}
	
	public static void main(String[] args) {
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
		System.out.println("----------test insert-------------");
		BinarySearchTree_NodeHasSuccessorPointerButNoParentPointer inAndDel = new BinarySearchTree_NodeHasSuccessorPointerButNoParentPointer();
		inAndDel.insert(15);
		inAndDel.insert(6);
		inAndDel.insert(18);
		inAndDel.insert(3);
		inAndDel.insert(2);
		inAndDel.insert(4);
		inAndDel.insert(7);
		inAndDel.insert(13);
		inAndDel.insert(9);
		inAndDel.insert(17);
		inAndDel.insert(20);
		inAndDel.insert(16);	//not included in image 12-2, just test a bug fix for 注意点1
		inAndDel.goOverTree();
		
		//test min, max, predecessor
		System.out.println("----------test min, max, predecessor, parent-------------");
		System.out.println("min: " + inAndDel.minNode(inAndDel.tree.root));
		System.out.println("max: " + inAndDel.maxNode(inAndDel.tree.root));
		System.out.println("predecessor of 15: " + inAndDel.predecessor(15));
		System.out.println("predecessor of 9: " + inAndDel.predecessor(9));
		System.out.println("predecessor of 16: " + inAndDel.predecessor(16));
		System.out.println("predecessor of 100: " + inAndDel.predecessor(100));
		System.out.println("predecessor of 2: " + inAndDel.predecessor(2));
		System.out.println("parent of 15: " + inAndDel.parent(15));
		System.out.println("parent of 6: " + inAndDel.parent(6));
		System.out.println("parent of 18: " + inAndDel.parent(18));
		System.out.println("parent of 17: " + inAndDel.parent(17));
		System.out.println("parent of 9: " + inAndDel.parent(9));
		System.out.println("parent of 5: " + inAndDel.parent(5));
		System.out.println("parent of 4: " + inAndDel.parent(4));
		System.out.println("parent of 100: " + inAndDel.parent(100));
		
		//test search
		System.out.println("----------test search-------------");
		System.out.println(inAndDel.search(4).details());
		System.out.println(inAndDel.search(15).details());
		System.out.println(inAndDel.search(9).details());
		System.out.println(inAndDel.search(17).details());
		System.out.println(inAndDel.search(18).details());
		try {
			System.out.println(inAndDel.search(5).details());
		} catch (Exception e) {
			System.out.println("not found!");
		}
		
		//test delete
		System.out.println("----------test delete-------------");
		inAndDel.delete(16);
		System.out.println("delete 16");
		inAndDel.delete(7);
		System.out.println("delete 7");
		inAndDel.delete(2);
		System.out.println("delete 2");
		inAndDel.delete(100);
		System.out.println("delete 100");
		inAndDel.delete(18);
		System.out.println("delete 18");
		inAndDel.delete(6);
		System.out.println("delete 6");
		inAndDel.delete(15);
		System.out.println("delete 15");
		inAndDel.delete(20);
		System.out.println("delete 20");
		inAndDel.delete(17);
		System.out.println("delete 17");
		inAndDel.goOverTree();
	}
	
	/**
	 * Binary search tree uses special tree node: TreeNodeForBSearchTree
	 * @author xiuzhu
	 */
	class BSearchTreeSpecial extends BinaryTree<Integer>{
		public TreeNodeForBSearchTree root = null;
		public BSearchTreeSpecial(TreeNodeForBSearchTree _root){	//construct for a tree with a node
			root = _root;
		}
		public BSearchTreeSpecial(){	//construct an empty tree
		}
	}
	/**
	 * Special tree node, has successor but no parent pointer.
	 * @author xiuzhu
	 */
	class TreeNodeForBSearchTree{
		Integer key = null;
		TreeNodeForBSearchTree left = null;
		TreeNodeForBSearchTree right = null;
		TreeNodeForBSearchTree successor = null;
		public TreeNodeForBSearchTree(int val){
			key = val;
		}
		@Override
		public String toString(){
			return key + "";
		}
		
		public String details(){
			return "key: " + key + ", left:" + left + ", right:" + right + ", successor: " + successor;
		}
	}
}

