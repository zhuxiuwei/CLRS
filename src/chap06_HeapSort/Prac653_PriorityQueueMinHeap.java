package chap06_HeapSort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 利用最小堆实现最小优先队列
 * @author xiuzhu
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class Prac653_PriorityQueueMinHeap<E extends Comparable> {	//Use generics here! 
	
	private ArrayList<E> elems = new ArrayList<E>(12);
	private int last_heap_elem_index = -1;	//last_heap_elem_index
	
	/**
	 * Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
	 * @return
	 */
	public E peek(){
		if(isEmpty())
			return null;
		else
			return elems.get(0);
	}
	
	/**
	 * Retrieves and removes the head of this queue, or returns null if this queue is empty.
	 * @return
	 */
	public E pool(){
		if(isEmpty())
			return null;
		else{
			E rest = elems.get(0);
			elems.set(0, elems.get(last_heap_elem_index --));
			minHeapify(elems, 0, last_heap_elem_index);
			return rest;
		}
	}
	
	/**
	 * Inserts the specified element into this priority queue.
	 * @param elem
	 */
	public void add(E elem){
		last_heap_elem_index ++;
		if(this.last_heap_elem_index == elems.size())
			elems.add(elem);
		else
			elems.set(last_heap_elem_index, elem);
		decrease_key(elem, elem);
	}
	
	/**
	 * Inserts elements inside an iterable into this priority queue.
	 * @param elem
	 */
	public void addRange(Iterable<E> elems){
		for(E elem: elems){
			add(elem);
		}
	}
	
	/**
	 * Decrease the value of given element to newValue. newValue should be less than original value
	 * @param elem	Element in queue
	 * @param newValue	New value of the element
	 * @return	True if succeed, false if failed.
	 */
	public boolean decrease_key(E elem, E newValue){
		if(!elems.contains(elem))
			return false;
		else{
			if(elem.compareTo(newValue) < 0)
				return false;
			int index = elems.indexOf(elem);
			elems.set(index, newValue);
			while(index > 0){
				E parent = elems.get(index/2);
				if(parent.compareTo(newValue) > 0){
					//switch
					E temp = elems.get(index/2);
					elems.set(index/2, newValue);
					elems.set(index, temp);
				}
				index = index / 2;
			}
			return true;
		}
	}
	
	/**
	 * Size of the queue.
	 * @return Size of the queue.
	 */
	public int size(){
		return this.last_heap_elem_index + 1;
	}
	
	/**
	 * If the queue is empty.
	 * @return 
	 */
	public boolean isEmpty(){
		return this.last_heap_elem_index == -1;
	}
	
	/**
	 * Heapify - make elements meets min-heap definition.
	 * Reason why I didn't reuse Prac622_Min_Heapify is that Prac622_Min_Heapify only suits Array pf Integer.
	 * @param a	ArrayList of elements
	 * @param i	Heapify for element at index i.
	 */
	public void minHeapify(ArrayList<E> a , int i, int lastHeapElementIndex){
		if(i > a.size() - 1 || i < 0){
			System.out.println("Invalid input!");
			return;
		}
		int leftIndex = 2 * i + 1;
		int rightIndex = 2 * i + 2;
		int minIndex = i;
		if(leftIndex <= lastHeapElementIndex && a.get(leftIndex).compareTo(a.get(i)) < 0)	//left
			minIndex = leftIndex;
		if(rightIndex <= lastHeapElementIndex && a.get(rightIndex).compareTo(a.get(minIndex)) < 0)	//right
			minIndex = rightIndex;
		if(minIndex != i){	//switch minIndex and i
			E temp = a.get(i);
			a.set(i, a.get(minIndex));
			a.set(minIndex, temp) ;
			minHeapify(a, minIndex, lastHeapElementIndex);	
		}
		
	}
	
	/**
	 * Print elements in queue.
	 */
	public void printElements(){
		System.out.print("size[" + size() + "]: ");
		for (int i = 0; i <= this.last_heap_elem_index; i++) {
			System.out.print(elems.get(i) + " ");
		}
		System.out.println();
	}
	
	//test
	public static void main(String[] args) {
		Prac653_PriorityQueueMinHeap<QueueElem> p = new Prac653_PriorityQueueMinHeap<QueueElem>();
		p.addRange(new ArrayList<QueueElem>(Arrays.asList(new QueueElem[]{new QueueElem(4), new QueueElem(3), new QueueElem(2), new QueueElem(1)})));
		p.printElements();
		System.out.println(p.pool());
		System.out.println(p.pool());
		System.out.println(p.pool());
		System.out.println(p.pool());
		p.addRange(new ArrayList<QueueElem>(Arrays.asList(new QueueElem[]{new QueueElem(1), new QueueElem(2), new QueueElem(3), new QueueElem(4)})));
		p.printElements();
		System.out.println(p.pool());
		System.out.println(p.pool());
		p.printElements();
		p.decrease_key(new QueueElem(4), new QueueElem(1));
		p.printElements();
		p.addRange(new ArrayList<QueueElem>(Arrays.asList(new QueueElem[]{new QueueElem(6), new QueueElem(12), new QueueElem(7), new QueueElem(0)})));
		p.printElements();
		p.decrease_key(new QueueElem(12), new QueueElem(-3));
		p.decrease_key(new QueueElem(3), new QueueElem(-2));
		p.decrease_key(new QueueElem(-2), new QueueElem(-5));
		p.decrease_key(new QueueElem(0), new QueueElem(-7));
		p.printElements();
		System.out.println(p.pool());
		System.out.println(p.pool());
		System.out.println(p.pool());
		System.out.println(p.pool());
		System.out.println(p.pool());
		System.out.println(p.pool());
		System.out.println(p.pool());
	}

}

/**
 * Define element class that can be held by PriorityQueueMinHeap container.
 */
class QueueElem implements Comparable<QueueElem>{	//TYPE SAFE Comparable!!
	public int value;
	public QueueElem(){}
	public QueueElem(int value){
		this.value = value;
	}
	@Override
	public int compareTo(QueueElem other) {		//TYPE SAFE Comparable!!
		if(other.value == this.value)
			return 0;
		else if(other.value < this.value)
			return 1;
		else 
			return -1;
	}
	
	//To facility debug
	@Override
	public String toString(){
		return value + "";
	}
	
	//override below 2 methods so ArrayList contains can work as expected.
	@Override
	public boolean equals(Object o){	
		if(o == this)
			return true;
		else if(o == null)
			return false;
		else if(!(o instanceof QueueElem))
			return false;
		else{
			QueueElem other = (QueueElem)o;
			return this.value == other.value;
		}
	}
	@Override
	public int hashCode(){
		return value;
	}
}