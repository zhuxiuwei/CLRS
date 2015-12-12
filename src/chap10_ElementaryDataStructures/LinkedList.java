package chap10_ElementaryDataStructures;
/**
 * 链表的实现
 * @author xiuzhu
 * 151212
 */

public class LinkedList<E> {
	/**
	 * Items in LinkedList
	 */
	class Item{
		public Item next = null;
		public Item prev = null;
		E key = null;
		public Item(E val){
			key = val;
		}
	}
	
	public Item head = null;
	
	public void insert(E ele){
		Item i = new Item(ele);
		Item first = head;
		if(first == null){
			
		}else{
			
		}
	}
	
	private boolean printDebug = false;	//if print debug info
	public void turnOnDebug(){
		this.printDebug = true;
	}
	public void turnOffDebug(){
		this.printDebug = false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
