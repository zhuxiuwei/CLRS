package chap10_ElementaryDataStructures;

/**
 * 10.2-5 字典序的单链表INSERT, DELETE和SEARCH。并分析时间
 * @author xiuzhu
 * 151213
 * 注意点：
 * 	注意泛型+继承时的语法。
 */
public class LinkedList_Sorted<E extends Comparable<E>> extends LinkedList<E>{	//注意点1：！！注意泛型应用在继承时的语法！！

	@Override
	public void insert(E i){
		Item item = new Item(i);
		if(isEmpty()){
			head = item;
			tail = item;
			return;
		}
		if(i.compareTo(tail.key) > 0){ //insert to last, O(1)
			tail.next = item;
			item.prev = tail;
			tail = item;
		}else if(i.compareTo(head.key) < 0){	//insert to head, O(1)
			item.next = head;
			head.prev = item;
			head = item;
		}else{	//insert to middle, worst O(n)
			Item current = head;
			while(current.key.compareTo(i) < 0)
				current = current.next;
			item.next = current;
			item.prev = current.prev;
			current.prev = item;
			item.prev.next = item;
		}
	}
	
	@Override
	public Item find(E i){
		if(i.compareTo(head.key) < 0 || i.compareTo(tail.key) > 0){	//在字典区间范围外的找不到，O(1)
			return null;
		}
		return super.find(i);	//直接调用父类的查找，在字典区间内顺序查找。找到找不到最坏都是O(n)
	}
	
	//删除直接调用父类的delete即可。因为关键的逻辑在find里面。
	@Override
	public boolean delete(E ele){
		return super.delete(ele);
	}
	
	public static void main(String[] args) {
		LinkedList_Sorted<Integer> l = new LinkedList_Sorted<Integer>();
		
		//test insert
		l.insert(5);
		l.insert(10);
		l.insert(4);
		l.insert(12);
		l.insert(6);
		l.insert(6);
		l.printList();
		
		//test search
		System.out.println(l.find(2));
		System.out.println(l.find(8));
		System.out.println(l.find(12));
		System.out.println(l.find(100));
		
		//test delete
		l.delete(1);
		l.delete(4);
		l.delete(12);
		l.printList();
	}

}
