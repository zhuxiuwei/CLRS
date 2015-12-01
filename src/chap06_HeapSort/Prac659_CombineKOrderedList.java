package chap06_HeapSort;
/**
 * 合并k个有序链表为1个有序链表，k个连边一共有n个元素，则时间复杂度O(n * lgk)
 * 151112
 * 花费时间略长。大概2h+。难点在于，如何表达，从n个list里取“下一个”的代码逻辑。
 * 注意点：1个
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

@SuppressWarnings("unchecked")
public class Prac659_CombineKOrderedList {

	private LinkedList<Integer> result = new LinkedList<Integer>();	//store final result
	private ArrayList<ValueListMapper> a;	//Min-heap container.
	private Prac653_PriorityQueueMinHeap<ValueListMapper> heapify 
		= new Prac653_PriorityQueueMinHeap<ValueListMapper>();	//will leverage its minHeapify method.
	private HashSet<Iterator<Integer>> listsIteratorSet = new HashSet<Iterator<Integer>>();	//use this to store Iterators of all lists that still has next.
	private int lastHeapElemIndex;	//used by heapify
	
	/**
	 * Combine the final ordered result of the given several ordered list.
	 * @param lists Several ordered list
	 * @return List of combined result.
	 */
	public LinkedList<Integer> combineLists(LinkedList<Integer> ... lists){
		int k = lists.length;
		lastHeapElemIndex = k - 1;
		a = new ArrayList<ValueListMapper>();
		for (int i = 0; i < lists.length; i++) {	//add 1st elements of all Lists.
			Iterator<Integer> it = lists[i].iterator();
			listsIteratorSet.add(it);
			a.add(new ValueListMapper(it.next(), it));
		}
		
		while(!listsIteratorSet.isEmpty()){
			//heapfiy it to get smallest element in O(lgk)
			heapify.minHeapify(a, 0, lastHeapElemIndex);
			
			//add smallest to result
			result.add(a.get(0).value);
			
			//add next element to a
			Iterator<Integer> it = a.get(0).it;
			if(it.hasNext()){	//next element is from the same list as of previous heap root.
				a.set(0, new ValueListMapper(it.next(), it));
			}else{	//next element is from another random list.
				listsIteratorSet.remove(it);
				while(!listsIteratorSet.isEmpty()){
					it = listsIteratorSet.iterator().next();
					if(it.hasNext()){
						a.set(0, new ValueListMapper(it.next(), it));
						break;
					}
					else
						listsIteratorSet.remove(it);
				}
			}
		}
		a.set(0, a.get(lastHeapElemIndex--));	//注意点1: 当全部iterator都走完后，最后一个算出元素要从a里skip掉。否则这个元素会计算2次。
		
		//process the last k-1 elements in a.
		for (int i = 0; i < k - 1 ; i++) {
			heapify.minHeapify(a, 0, lastHeapElemIndex);
			//switch
			result.add(a.get(0).value);
			ValueListMapper temp = a.get(0);
			a.set(0, a.get(lastHeapElemIndex));
			lastHeapElemIndex --;
		}
		return result;
	}
	
	
	public static void main(String[] args) {
		Prac659_CombineKOrderedList p = new Prac659_CombineKOrderedList();
		LinkedList<Integer> a1 = new LinkedList<Integer>(); a1.add(7);a1.add(8);a1.add(9);
		LinkedList<Integer> a2 = new LinkedList<Integer>(); a2.add(4);a2.add(5);a2.add(6);
		LinkedList<Integer> a3 = new LinkedList<Integer>(); a3.add(1);a3.add(2);a3.add(3);
		System.out.println(p.combineLists(a1, a2, a3));
		
		p = new Prac659_CombineKOrderedList();
		LinkedList<Integer> a4 = new LinkedList<Integer>(); a4.add(12);
		LinkedList<Integer> a5 = new LinkedList<Integer>(); a5.add(10);a5.add(11);a5.add(13);a5.add(14);a5.add(15);
		LinkedList<Integer> a6 = new LinkedList<Integer>(); a6.add(0);a6.add(10);
		System.out.println(p.combineLists(a3, a6, a2, a1, a5, a4));
	}

}

/**
 * Mapping the relationship between a value and its related array.
 * This class is to facility get next element from lists.
 * @author xiuzhu
 */
class ValueListMapper implements Comparable<ValueListMapper>{
	public int value;
	public Iterator<Integer> it;
	
	public ValueListMapper(int value, Iterator<Integer> it){
		this.value = value;
		this.it = it;
	}
	@Override
	public int compareTo(ValueListMapper other) {
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
}