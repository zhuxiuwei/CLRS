package chap06_HeapSort;
/**
 * 将节点i从堆删除。如果有n个节点，时间复杂度要是O(lgn)
 * 思想：交换节点i和堆中最后一个节点，然后调用heapify(a, i)
 * @author xiuzhu
 * 151111
 *
 */
public class Prac658_Max_Heap_Delete {

	private int lastHeapEleIndex = -2;
	private Prac625_Max_Heapify_NoRescursive heapify = new Prac625_Max_Heapify_NoRescursive();
	public void heap_delete(int[]a, int i){
		if(lastHeapEleIndex == -2)
			lastHeapEleIndex = a.length - 1;	//initialize lastHeapEleIndex
		if(i > lastHeapEleIndex){
			System.out.println("Invalid location. Last available index: " + lastHeapEleIndex + ", i: " + i);
			return;
		}
		
		//switch
		int temp = a[i];
		a[i] = a[lastHeapEleIndex];
		a[lastHeapEleIndex--] = temp;
		
		//adjust
		heapify.maxHeapify_NoRescursive(a, i, lastHeapEleIndex);
		
		for (int j = 0; j <= lastHeapEleIndex; j++) {
			System.out.print(a[j] + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Prac658_Max_Heap_Delete p = new Prac658_Max_Heap_Delete();
		int a[] = new int[]{16, 14, 10, 8, 7, 9, 3, 2, 4, 1};
		p.heap_delete(a, 0);
		p.heap_delete(a, 0);
		p.heap_delete(a, 7);
		p.heap_delete(a, 7);
		p.heap_delete(a, 4);
		p.heap_delete(a, 2);
		p.heap_delete(a, 3);
	}

}
