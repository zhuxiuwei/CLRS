package chap07;

import java.util.Arrays;

/**
 * Partition procedure of QuickSort.
 * @author xiuzhu
 * 151118
 */
public class QuickSort_Partition {

	//Partition in CLRS
	public void partition(int a[], int start, int end){
		
	}
	
	//problem 7.1-4，partition in DESC.
	public void partition_desc(int a[], int start, int end){
		
	}
	
	//traditional partition
	public void partition_traditional(int a[], int start, int end){
		
	}
	
	//call all 3 methods above to facility test.
	public void call_all_partition(int a[], int start, int end){
		System.out.println("Original Array: " + Arrays.toString(a));
		int a1[] = a.clone();
		int a2[] = a.clone();
		partition(a, start, end);
		partition_traditional(a1, start, end);
		partition_desc(a2, start, end);
		System.out.println("Partition in CLRS: " + Arrays.toString(a));
		System.out.println("Partition in traditional: " + Arrays.toString(a1));
		System.out.println("Partition in CLRS desc: " + Arrays.toString(a2));
		System.out.println("----------------------------------");
	}
	
	//test
	public static void main(String[] args) {
		QuickSort_Partition p = new QuickSort_Partition();
		System.out.println("----------------------------------");
		int a0[] = {};
		int a1[] = {1};
		int a2[] = {2,1};
		int a3[] = {2,1,3};
		int a4[] = {13,19,9,5,12,8,7,4,21,2,6,11};
		p.call_all_partition(a0, 0, a0.length - 1);
		p.call_all_partition(a1, 0, a1.length - 1);
		p.call_all_partition(a2, 0, a2.length - 1);
		p.call_all_partition(a3, 0, a3.length - 1);
		p.call_all_partition(a4, 0, a4.length - 1);
	}

}
