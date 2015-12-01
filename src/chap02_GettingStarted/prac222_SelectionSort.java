package chap02_GettingStarted;

import java.util.Arrays;

/**
 * 选择排序
 * @author xiuzhu
 * 150911
 * 
 * 流利度有待提高。应该先画图分析再写代码。不是上来就写。
 */
public class prac222_SelectionSort {

	public void selectionSort(int[] src){
		int min = Integer.MAX_VALUE;
		int minIndex = -1;
		for(int i = 0; i <= src.length - 2; i ++){
			for(int j = i + 1; j < src.length; j ++){
				if(src[j] < min){
					minIndex = j;
					min = src[j];
				}
			}
			//交换非有序区第一个元素和非有序区最小元素
			int temp = src[i];
			if(temp > min){
				src[i] = src[minIndex];
				src[minIndex] = temp;
			}
			min = Integer.MAX_VALUE;
		}
	}
	
	public static void main(String[] args) {
		prac222_SelectionSort t = new prac222_SelectionSort();
		
		int e[] = {8,7,2,5,9,8,5};
		t.selectionSort(e);
		System.out.println(Arrays.toString(e));
		
		int a[] = {8,7,2,4,9,8};
		t.selectionSort(a);
		System.out.println(Arrays.toString(a));
		
		int b[] = {8, 7, 6};
		t.selectionSort(b);
		System.out.println(Arrays.toString(b));
		
		int c[] = {8};
		t.selectionSort(c);
		System.out.println(Arrays.toString(c));
		
		int d[] = {};
		t.selectionSort(d);
		System.out.println(Arrays.toString(d));
		
		int f[] = {8,7,2,110,99, 898};
		t.selectionSort(f);
		System.out.println(Arrays.toString(f));
	}

}
