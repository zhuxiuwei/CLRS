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
		
	}
	
	/**
	 * 先根遍历左孩子右兄弟树
	 */
	public void traversing_preorder(){
		
	}
	
	public static void main(String[] args) {
		//create tree。
		//！！！注意点1：是如何方便地构造这个树的。
		TreeNode<Integer> root = new TreeNode<Integer>(1);
		Tree_LefrChildRightSibling<Integer> t = new Tree_LefrChildRightSibling<Integer>(root);
		root.addChild(new TreeNode<Integer>(2))
			.addChild(new TreeNode<Integer>(3)
					.addChild(new TreeNode<Integer>(5)))
			.addChild(new TreeNode<Integer>(4)
					.addChild(new TreeNode<Integer>(6))
					.addChild(new TreeNode<Integer>(7)
							.addChild(new TreeNode<Integer>(10))
							.addChild(new TreeNode<Integer>(11))
							.addChild(new TreeNode<Integer>(12)))
					.addChild(new TreeNode<Integer>(8))
					.addChild(new TreeNode<Integer>(9))
		);
		
		t.traversing_levelorder();
		t.traversing_preorder();
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
		System.out.println("this: " + this + ", child" + child);
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
