package chap10_ElementaryDataStructures;
/**
 * 左孩子右兄弟表示法的树。
 * @author xiuzhu
 * 151217
 * 注意点：
 * 	1. 注意在构造树时，是如何方便地添加TreeNode的。想做到这点，必须在TreeNode里定义添加Node的方法，而不是在Tree里定义这个方法。
 *  2. 添加右孩子的逻辑要注意。出错过。包括cur初始化/cur循环部分，以及child.parent的指向。
 */
public class Tree_LefrChildRightSibling<E> {
	public TreeNode<E> root = null;
	public Tree_LefrChildRightSibling(TreeNode<E> _root){
		root = _root;
	}
	
	/**
	 * 层次遍历左孩子右兄弟树
	 */
	public void traversing_levelorder(){
		Queue_ByLinkedList<TreeNode<E>> q = new Queue_ByLinkedList<TreeNode<E>>();
		if(root == null)
			return;		//empty tree
		q.enqueue(root);
		while(!q.isEmpty()){
			TreeNode<E> cur = q.dequeue();
			System.out.print(cur + " ");
			if(cur.left_child != null)
				q.enqueue(cur.left_child);
			while(cur.right_sibling != null){
				cur = cur.right_sibling;
				System.out.print(cur + " ");
				if(cur.left_child != null)
					q.enqueue(cur.left_child);
			}
		}
	}
	
	/**
	 * 先根遍历左孩子右兄弟树
	 */
	public void traversing_preorder(TreeNode<E> node){
		if(node == null)
			return;
		else{
			System.out.print(node + " ");	//遍历自己
			traversing_preorder(node.left_child);	//遍历左孩子
			if(node.left_child != null){
				TreeNode<E> r = node.left_child.right_sibling;	//遍历右兄弟
				while(r != null){
					traversing_preorder(r);
					r = r.right_sibling;
				}
			}
		}
	}
	
	/**
	 * 中根遍历左孩子右兄弟树
	 * 注意，这里多叉树的所谓中根遍历，是先遍历左子树，然后自己，然后右兄弟们。
	 */
	public void traversing_inorder(TreeNode<E> node){
		if(node == null)
			return;
		else{
			traversing_inorder(node.left_child);	//遍历左孩子
			System.out.print(node + " ");	//遍历自己
			if(node.left_child != null){
				TreeNode<E> r = node.left_child.right_sibling;	//遍历右兄弟
				while(r != null){
					traversing_inorder(r);
					r = r.right_sibling;
				}
			}
		}
	}
	
	/**
	 * 后根遍历左孩子右兄弟树
	 */
	public void traversing_postorder(TreeNode<E> node){
		if(node == null)
			return;
		else{
			traversing_postorder(node.left_child);	//遍历左孩子
			if(node.left_child != null){
				TreeNode<E> r = node.left_child.right_sibling;	//遍历右兄弟
				while(r != null){
					traversing_postorder(r);
					r = r.right_sibling;
				}
			}
			System.out.print(node + " ");	//遍历自己
		}
	}
	
	public static void main(String[] args) {
		//create tree。
		//！！！注意点1：是如何方便地构造这个树的。
		/***************************
		 * 下面的代码构造如下的多叉树：
		 *        1
		 *      / |  \
		 *     2  3   4
		 *    /      /|\\
		 *   5      6 7 8 9
		 *  / \      /|\  
		 * 10 11   12 13 14  
		 ***************************/
		TreeNode<Integer> root = new TreeNode<Integer>(1);
		Tree_LefrChildRightSibling<Integer> t = new Tree_LefrChildRightSibling<Integer>(root);
		root.addChild(new TreeNode<Integer>(2)
					.addChild(new TreeNode<Integer>(5)
							.addChild(new TreeNode<Integer>(10))
							.addChild(new TreeNode<Integer>(11))))
			.addChild(new TreeNode<Integer>(3))
			.addChild(new TreeNode<Integer>(4)
					.addChild(new TreeNode<Integer>(6))
					.addChild(new TreeNode<Integer>(7)
							.addChild(new TreeNode<Integer>(12))
							.addChild(new TreeNode<Integer>(13))
							.addChild(new TreeNode<Integer>(14)))
					.addChild(new TreeNode<Integer>(8))
					.addChild(new TreeNode<Integer>(9)));
		
		System.out.print("先根:");
		t.traversing_preorder(t.root);
		System.out.print("\r\n中根:");
		t.traversing_inorder(t.root);
		System.out.print("\r\n后根:");
		t.traversing_postorder(t.root);
		System.out.print("\r\n层次:");
		t.traversing_levelorder();
	}

}
/**
 * 表示TreeNode的类。
 * @author xiuzhu
 * @param <E> Type of key.
 */
class TreeNode<E>{
	E key = null;
	TreeNode<E> left_child = null;
	TreeNode<E> right_sibling = null;
	TreeNode<E> parent = null;
	public TreeNode(E val){
		key = val;
	}
	@Override
	public String toString(){
		return key + "";
	}
	/**
	 * Add child for this node. Used to create tree.
	 * @param child	Newly added child.
	 * @return this
	 */
	public TreeNode<E> addChild(TreeNode<E> child){
//		System.out.println("parent: " + this + ", child: " + child);
		if(left_child == null){	//add to left_chile
			left_child = child;
			child.parent = this;
		}else{	//add to right_sibling
			//！！！注意点2：添加右孩子的逻辑要注意。出错过。包括cur初始化/cur循环部分，以及child.parent的指向。
			TreeNode<E> cur = left_child;
			while(cur.right_sibling != null)
				cur = cur.right_sibling;
			cur.right_sibling = child;
			child.parent = this;
		}
		return this;
	}
}
