package chap07_QuickSort;

import java.util.Arrays;
/**
 * Quick sort that leverages QuickSort_Partition
 * @author xiuzhu
 * 151124
 */
public class QuickSort {

	QuickSort_Partition partition = new QuickSort_Partition();
	
	public void sort(int a[], int start, int end){
		int mid = partition.partition(a, start, end);
		//int mid = partition.partition_traditional(a, start, end);		//this also works fine
		if(mid + 1 < end)
			sort(a, mid + 1, end);
		if(mid > start + 1)
			sort(a, start, mid - 1);
	}
	
	public static void main(String[] args) {
		QuickSort s = new QuickSort();
		int a0[] = {};
		int a1[] = {1};
		int a2[] = {2,1};
		int a3[] = {1 ,2, 3};
		int a4[] = {3, 2, 1};
		int a5[] = {3, 1, 2};
		int a6[] = {13,19,9,5,12,8,7,4,21,2,6,11};
		s.sort(a0, 0, a0.length - 1);
		s.sort(a1, 0, a1.length - 1);
		s.sort(a2, 0, a2.length - 1);
		s.sort(a3, 0, a3.length - 1);
		s.sort(a4, 0, a4.length - 1);
		s.sort(a5, 0, a5.length - 1);
		s.sort(a6, 0, a6.length - 1);
		System.out.println(Arrays.toString(a0));
		System.out.println(Arrays.toString(a1));
		System.out.println(Arrays.toString(a2));
		System.out.println(Arrays.toString(a3));
		System.out.println(Arrays.toString(a4));
		System.out.println(Arrays.toString(a5));
		System.out.println(Arrays.toString(a6));
	}

}
