package chap12_BinarySearchTree;
/**
 * 习题12.3-5 没有父亲指针，而有后继指针的结点构成的二叉搜索树的插入，查找和删除。
 * @author xiuzhu
 * 注意点：
 * 	1. 插入时，一个情况需要注意更新successor。就是待插入的结点比当前结点大，但是当前结点的右孩子已经被占据了，此时可能需要更新当前结点的successor。见test中插入9时，7的successor从13更新成9.
 *  2. 查找时，待查找结点比当前结点大，不能写成current = current.successor，还是和传统的查找一样找右子树（我开始还以为这是个比传统二叉搜索树的改进。。。），因为可能陷入死循环。见test中查找5.就会6-3-4-6-3-4...死循环
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
	 * Search min value
	 * @return minimum node
	 */
	public TreeNodeForBSearchTree minNode(){
		TreeNodeForBSearchTree node = tree.root;
		while(node.left != null){
			node = node.left;
		}
		return node;
	}
	
	/**
	 * Scan tree from min to max. (just to help verify)
	 */
	public void goOverTree(){
		TreeNodeForBSearchTree node = minNode();
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
		inAndDel.insert(14);
		inAndDel.insert(16);	//not included in image 12-2, just test a bug fix for 注意点1
		inAndDel.goOverTree();
		
		//test search
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

