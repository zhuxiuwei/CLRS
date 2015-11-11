package chap06;
/**
 * 利用最小堆实现最小优先队列
 * @author xiuzhu
 * @param <T>
 */
public class Prac653_PriorityQueueMinHeap<E extends Puttable> {
	/**
	 * Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
	 * @return
	 */
	public E peek(){
		return null;
	}
	
	/**
	 * Retrieves and removes the head of this queue, or returns null if this queue is empty.
	 * @return
	 */
	public E pool(){
		return null;
	}
	
	/**
	 * Inserts the specified element into this priority queue.
	 * @param elem
	 */
	public void add(E elem){
	}
	
	/**
	 * Decrease the value of given element to newValue. newValue should be less than original value
	 * @param elem	Element in queue
	 * @param newValue	New value of the element
	 * @return	True if succeed, false if failed.
	 */
	public boolean decrease_key(E elem, int newValue){
		return false;
	}
	
	public static void main(String[] args) {
		Prac653_PriorityQueueMinHeap<QueueElem> p = new Prac653_PriorityQueueMinHeap<QueueElem>();
		QueueElem e1 = new QueueElem(0);
		QueueElem e2 = new QueueElem(1);
		System.out.println(e1.compareTo(e2));
		
		QueueElem e3 = new QueueElem();
		System.out.println(e3.value);
	}

}

/**
 * Define element class that can be held by PriorityQueueMinHeap container.
 */
abstract class Puttable implements Comparable<QueueElem>{int value = 100;}
class QueueElem extends Puttable {
	public QueueElem(){}
	public QueueElem(int value){
		this.value = value;
	}
	@Override
	public int compareTo(QueueElem o) {
		if(o.value == this.value)
			return 0;
		else if(o.value < this.value)
			return 1;
		else 
			return -1;
	}
}