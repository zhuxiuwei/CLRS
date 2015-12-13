package chap10_ElementaryDataStructures;
/**
 * 双向链表的实现 -- 每个链表元素都有prev和next。
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
		public boolean hasNext(){
			return next != null;
		}
		public boolean hasPrev(){
			return prev != null;
		}
		@Override
		public String toString(){
			return key + " ";
		}
		@Override
		public boolean equals(Object o){
			if(o == null)
				return false;
			//return o.key.equals(this.key);
			if(o instanceof LinkedList.Item){
				Item other = (Item) o;
				return other.key.equals(this.key);
			}else
			return false;
		}
		@Override
		public int hashCode(){
			return this.key.hashCode();
		}
	}
	
	public Item head = null;
	
	/**
	 * Insert Element to LinkedList
	 * @param ele
	 */
	public void insert(E ele){
		Item i = new Item(ele);
		if(head == null){	//empty list
			head = i;
		}else{
			i.next = head;
			head.prev = i;
			head = i;
		}
	}
	
	/**
	 * find first matched element in linkedlist
	 * @param ele Find this element
	 * @return	Matched Item with given element. return null if can't find.
	 */
	public Item find(E ele){
		Item i = head;
		while(i != null){
			if(i.key.equals(ele))	//found
				break;
			i = i.next;
		}
		return i;
	}
	
	/**
	 * Delete first matched item with given element in linkedlist
	 * @param ele Delete this element
	 * @return	true if delete succeed, false if not.
	 */
	public boolean delete(E ele){
		Item i = find(ele);
		if(i == null)
			return false;
		else{
			if(head == i)	//delete 1st element
				head = i.next;
			else
				i.prev.next = i.next;
			if(i.next != null)
				i.next.prev = i.prev;
			return true;
		}
	}
	
	/**
	 * Print LinkedList
	 */
	public void printList(){
		System.out.print("head -> ");
		Item i = head;
		while(i != null){
			System.out.print(i + "-> ");
			i = i.next;
		}
		System.out.println("null");
	}
	
	//test
	public static void main(String[] args) {
		LinkedList<Integer> l = new LinkedList<Integer>();
		l.insert(1);
		l.insert(2);
		l.insert(3);
		l.insert(2);
		l.printList();
		System.out.println(l.find(1));
		System.out.println(l.find(2));
		System.out.println(l.find(3));
		System.out.println(l.find(4));
		System.out.println(l.delete(4));
		l.printList();
		System.out.println(l.delete(2));
		l.printList();
		System.out.println(l.delete(1));
		l.printList();
		System.out.println(l.delete(3));
		l.printList();
		System.out.println(l.delete(2));
		l.printList();
		
		l = new LinkedList<Integer>();
		l.insert(5);
		l.printList();
		System.out.println(l.delete(2));
		l.printList();
		System.out.println(l.delete(5));
		l.printList();
	}

}
