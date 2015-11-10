package chap06;

import java.util.Arrays;

/**
 * 堆排序算法
 * @author xiuzhu
 */
public class Text64_HeapSort {

	Prac625_Max_Heapify_NoRescursive heapify = new Prac625_Max_Heapify_NoRescursive();	//调整堆
	Text63_build_max_heap buildMaxHeap = new Text63_build_max_heap();	//建堆
	
	public void heap_sort(int a[]){
		buildMaxHeap.buildMaxHeap(a);
		
		System.out.println("Sort result: " + Arrays.toString(a));
	}
	
	public static void main(String[] args) {
		int[] a = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
		Text64_HeapSort sort = new Text64_HeapSort();
		sort.heap_sort(a);
	}

}
