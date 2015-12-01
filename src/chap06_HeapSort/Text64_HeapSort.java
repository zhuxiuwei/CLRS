package chap06_HeapSort;

import java.util.Arrays;

/**
 * 堆排序算法
 * @author xiuzhu
 * 151110
 */
public class Text64_HeapSort {

	Prac625_Max_Heapify_NoRescursive heapify = new Prac625_Max_Heapify_NoRescursive();	//调整堆
	Text63_build_max_heap buildMaxHeap = new Text63_build_max_heap();	//建堆
	
	public void heap_sort(int a[]){
		int lastHeapEleIndex = a.length - 1;
		buildMaxHeap.buildMaxHeap(a);
		//exchange a[0] and the last element in heap
		int temp = a[0];
		a[0] = a[lastHeapEleIndex];
		a[lastHeapEleIndex] = temp;
		for (int i = 0; i <= a.length - 2; i++) {
			lastHeapEleIndex --;
			heapify.maxHeapify_NoRescursive(a, 0, lastHeapEleIndex);
			//exchange a[0] and the last element in heap
			temp = a[0];
			a[0] = a[lastHeapEleIndex];
			a[lastHeapEleIndex] = temp;
		}
		System.out.println("Sort result: " + Arrays.toString(a));
	}
	
	public static void main(String[] args) {
		Text64_HeapSort sort = new Text64_HeapSort();
		int[] a = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
		sort.heap_sort(a);
		a = new int[]{4, 1};
		sort.heap_sort(a);
		a = new int[]{4};
		sort.heap_sort(a);
	}

}
