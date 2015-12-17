package chap10_ElementaryDataStructures;
/**
 * 用单数组表示法实现双向链表。
 * @author xiuzhu
 * 151217
 */
public class ObjectImplBySingleArray {

	private int a[];	//存储链表实现的单数组。
	private int free = 0;	//free初始值
	private int head = -1;	//链表的head。-1表示为空。
	
	/**
	 * @param maxElememtCount 最多存储的元素个数。
	 */
	public ObjectImplBySingleArray(int maxElememtCount){
		a = new int[3 * maxElememtCount];
	}
	
	public static void main(String[] args) {
		ObjectImplBySingleArray o = new ObjectImplBySingleArray(8);
	}

}
