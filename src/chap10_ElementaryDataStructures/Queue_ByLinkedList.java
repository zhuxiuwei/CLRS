package chap10_ElementaryDataStructures;
/**
 * 习题10.2-3 链表实现队列。入队出队操作仍然是O(1)
 * @author xiuzhu
 * 151213
 */
public class Queue_ByLinkedList {
	private LinkedList<Integer> list = new LinkedList<Integer>();
	
	public void enqueue(int e){
		System.out.println("enquque: " + e);
		list.insert(e);
	}
	
	//注意：要实现O(1)级别的dequeue，需要LinkedList提供O(1)removeLast()方法。
	public int dequeue() {
		if(list.isEmpty()){
			System.out.println("failed to dequeue due to queue empty!!!");
			return 0;
		}else{
			int rest = list.removeLast();
			System.out.println("dequque: " + rest);
			return rest;
		}
	}
	
	//test
	public static void main(String[] args) {
		Queue_ByLinkedList q = new Queue_ByLinkedList();
		q.dequeue();
		q.enqueue(1);
		q.dequeue();
		q.dequeue();
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		q.enqueue(4);
		q.dequeue();
		q.enqueue(5);
		q.dequeue();
		q.dequeue();
		q.enqueue(6);
		q.enqueue(7);
		q.enqueue(8);
		q.dequeue();
		q.dequeue();
		q.dequeue();
		q.dequeue();
		q.dequeue();
		q.dequeue();
	}

}
