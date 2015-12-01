package chap02_GettingStarted;

import java.util.Arrays;

/**
 * O(nlgn)的算法，给定n个整数的集合和一个整数x，算出来集合中是否有两个其和刚好为x的元素。
 * @author xiuzhu
 * 150917
 */
public class prac237_FindTwoElementsSum {
	private Text2_3_MergeSort t = new Text2_3_MergeSort();
	private prac235_BinarySearch b = new prac235_BinarySearch();

	public void find(int a[], int x){
		boolean found = false;
		System.out.println("Search x=" + x + " in " + Arrays.toString(a));
		
		//sort the array first, θ(nlgn) 
		t.mergeSort(a, 0, a.length - 1);
		
		//binary search now， n*lgn, still θ(nlgn). So total complexity is θ(nlgn) + θ(nlgn) , still θ(nlgn).
		for(int i=0; i < a.length - 1; i ++){
			if(b.bSearch_recursive(a, x-a[i], i, a.length - 1)){ //set start = i, only search backward to avoid deplicate result.
				System.out.println("	Found " + a[i] + ", " + (x-a[i]));
				found = true;
			}
		}
		if(!found)
			System.out.println("	Not Found!");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a1 = {2, 4, 3, 2, -1, 0, 9, 7};
		int[] a2 = {2,1};
		int[] a3 = {1};
		int[] a4 = {};
		
		int x = 3;
		prac237_FindTwoElementsSum p = new prac237_FindTwoElementsSum();
		p.find(a1, x);
		p.find(a2, x);
		p.find(a3, x);
		p.find(a4, x);
	}

}
