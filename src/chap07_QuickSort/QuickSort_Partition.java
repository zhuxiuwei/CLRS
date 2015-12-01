package chap07_QuickSort;

import java.util.Arrays;

/**
 * Partition procedure of QuickSort.
 * @author xiuzhu
 * 151118
 */
public class QuickSort_Partition {

	//Partition in CLRS
	public int partition(int a[], int start, int end){
		int index = -1;
		if(a == null || a.length <= 1)
			return index;
		int i = -1, r = a[end];
		for (int j = 0; j < a.length; j++) {
			if(a[j] <= r){
				int temp = a[j];
				a[j] = a[++i];
				a[i] = temp;
				index = i;
			}
		}
		return index;
	}
	
	//problem 7.1-4，partition in DESC.
	public void partition_desc(int a[], int start, int end){
		if(a == null || a.length <= 1)
			return;
		int i = -1, r = a[end];
		for (int j = 0; j < a.length; j++) {
			if(a[j] >= r){
				int temp = a[j];
				a[j] = a[++i];
				a[i] = temp;
			}
		}
	}
	
	//traditional partition
	public int partition_traditional(int a[], int start, int end){
		if(a == null || a.length <= 1)
			return -1;
		
		int r = a[end];
		int i = 0, j = end;
		while(i < j){
			while(a[i] <= r && i < j)
				i++;
			while(a[j] >= r && i < j)
				j--;
			int temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
		
		if(i == j){
			int temp = a[i];
			a[i] = a[end];
			a[end] = temp;
		}
		return i;
	}
	
	//call all 3 methods above. --- Just to facility test.
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
		int a3[] = {1 ,2, 3};
		int a4[] = {3, 2, 1};
		int a5[] = {3, 1, 2};
		int a6[] = {13,19,9,5,12,8,7,4,21,2,6,11};
		p.call_all_partition(a0, 0, a0.length - 1);
		p.call_all_partition(a1, 0, a1.length - 1);
		p.call_all_partition(a2, 0, a2.length - 1);
		p.call_all_partition(a3, 0, a3.length - 1);
		p.call_all_partition(a4, 0, a4.length - 1);
		p.call_all_partition(a5, 0, a5.length - 1);
		p.call_all_partition(a6, 0, a6.length - 1);
	}

}
