package chap10_ElementaryDataStructures;
/**
 * 双向链表的实现 -- 每个链表元素都有prev和next。
 * @author xiuzhu
 * 151212
 * 注意点：
 * 	revert方法有一个注意点。
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
	
	public Item head = null; 	//point to first item.
	public Item tail = null;	//point to last item.
	
	/**
	 * Insert Element to LinkedList
	 * @param ele
	 */
	public void insert(E ele){
		Item i = new Item(ele);
		if(head == null){	//empty list
			head = i;
			tail = i;
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
			if(head == i)
				head = i.next;
			if(tail == i)
				tail = i.prev;
			if(i.prev != null)
				i.prev.next = i.next;
			if(i.next != null)
				i.next.prev = i.prev;
			return true;
		}
	}
	
	/**
	 * Union two linkedlist. Other follow this.
	 * @param other
	 * @return this + follow
	 */
	public LinkedList<E> union(LinkedList<E> other){
		if(other == null || other.isEmpty()){
			;//do nothing. just return this.
		}
		else if(this.isEmpty()){	//'this' is empty, make 'this' = other.
			this.head = other.head;
			this.tail = other.tail;
		}else{	//both not empty, combine two.
			this.tail.next = other.head;
			other.head.prev = this.tail;
			this.tail = other.tail;
		}
		return this;
	}
	
	/**
	 * Revert the linkedlist
	 */
	public void revert(){
		if(isEmpty())
			return;
		else{
			Item cur = head;
			Item next = cur.next;
			do{
				//just switch prev and next for each element.
				cur.next = cur.prev;
				cur.prev = next;
				cur = next;
				if(cur != null)		//！！！注意点2：必须加这个，不然到了最后一个元素会空指针异常。
					next = cur.next;
			}while(cur != null);	//！！！注意点1：注意退出条件。如果写成next != null，最后一个元素就不能正常翻转。
			
			//Then switch head and tail;
			Item temp = head;
			head = tail;
			tail = temp;
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
		System.out.println("null. isEmpty: " + isEmpty() + ", head: " + head + ", tail: "+ tail);
	}
	
	/**
	 * If linkedList is empty
	 */
	public boolean isEmpty(){
		return head == null;
	}
	
	/**
	 * Get last item
	 * @return
	 */
	public E getLast(){
		return tail == null? null: tail.key;
	}
	
	/**
	 * remove the last element in linkedlist
	 * @return last element
	 */
	public E removeLast(){
		E rest = null;
		if(tail == null)	//empty list
			return null;
		else{
			rest = tail.key;
			if(tail == head){	//only one element
				head = null;
				tail = null;
			}
			else
			{
				tail.prev.next = null;
				tail = tail.prev;
			}
		}
		return rest;
	}
	
	//test
	public static void main(String[] args) {
		LinkedList<Integer> l = new LinkedList<Integer>();
		l.insert(1);
		l.insert(2);
		l.insert(3);
		l.insert(2);
		l.printList();
		System.out.println("find: " + l.find(1));
		System.out.println("find: " + l.find(2));
		System.out.println("find: " + l.find(3));
		System.out.println("find: " + l.find(4));
		System.out.println("delete: " + l.delete(4));
		l.printList();
		System.out.println("delete: " + l.delete(2));
		l.printList();
		System.out.println("delete: " + l.delete(1));
		l.printList();
		System.out.println("delete: " + l.delete(3));
		l.printList();
		System.out.println("delete: " + l.delete(2));
		l.printList();
		
		l = new LinkedList<Integer>();
		l.insert(5);
		l.printList();
		System.out.println("delete: " + l.delete(2));
		l.printList();
		System.out.println("delete: " + l.delete(5));
		l.printList();
		System.out.println("last: " + l.getLast());
		
		System.out.println("\r\n--test removeLast--");
		l = new LinkedList<Integer>();
		l.insert(5);
		System.out.println("remove last: " + l.removeLast());
		l.printList();
		l.insert(1);
		l.insert(2);
		l.printList();
		System.out.println("remove last: " + l.removeLast());
		l.printList();
		System.out.println("remove last: " + l.removeLast());
		l.printList();
		System.out.println("remove last: " + l.removeLast());
		l.printList();
		
		System.out.println("\r\n--test union--");
		LinkedList<Integer> other = new LinkedList<Integer>();
		l.union(other);
		l.printList();
		other.union(l);
		other.printList();
		other.insert(1); other.insert(2); other.insert(3);
		l.union(other);
		l.printList();
		other = new LinkedList<Integer>();
		other.insert(4); other.insert(5); other.insert(6);
		other.union(l);
		other.printList();
		
		System.out.println("\r\n--test revert--");
		other.revert();
		other.printList();
		l = new LinkedList<Integer>();
		l.insert(1);
		l.revert();
		l.printList();
		l.insert(2);
		l.printList();
		l.revert();
		l.printList();
	}

}
