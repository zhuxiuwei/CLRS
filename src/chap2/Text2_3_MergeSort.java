package chap2;

import java.util.Arrays;

/**
 * 归并排序 p17
 * @author xiuzhu
 * 2015-09-09
 */
public class Text2_3_MergeSort {

	public void mergeSort(int[] src, int begin, int end){
		int mid = (end + begin)/2;
		if(begin < mid)	//1. 不要忘了这个条件，否则死递归退不出来。
			mergeSort(src, begin, mid);
		if(mid + 1 < end)	//同1
			mergeSort(src, mid + 1, end);
		merge(src, begin, mid, end);
	}
	
	private void merge(int[] src, int begin, int mid, int end){
		
		if(src.length == 0)	//in case src is empty
			return;
		
		//初始化左右两个子数组，并且让数组末尾是“哨兵”
		int[] left = new int[mid - begin + 2];
		int[] right = new int[end - mid + 1];
		for(int i = 0; i < left.length - 1; i ++){
			left[i] = src[begin + i];
		}
		left[left.length - 1]  = Integer.MAX_VALUE;		//2. 哨兵是个技巧，省略了每次判断是否左右数组越界的麻烦。
		for(int i = 0; i < right.length - 1; i ++){
			right[i] = src[mid + i + 1];
		}
		right[right.length - 1]  = Integer.MAX_VALUE;
		
		//开始merge
		int temp1 = 0;
		int temp2 = 0;
		for(int i = begin; i <= end; i ++){
			if(left[temp1] <= right[temp2])
				src[i] = left[temp1++];
			else
				src[i] = right[temp2++];
		}
	}
	
	public static void main(String[] args) {
		Text2_3_MergeSort t = new Text2_3_MergeSort();
		
		int e[] = {8,7,2,5,9,8,5};
		t.mergeSort(e, 0, e.length - 1);
		System.out.println(Arrays.toString(e));
		
		int a[] = {8,7,2,5,9,8};
		t.mergeSort(a, 0, a.length - 1);
		System.out.println(Arrays.toString(a));
		
		int b[] = {8,7};
		t.mergeSort(b, 0, b.length - 1);
		System.out.println(Arrays.toString(b));
		
		int c[] = {8};
		t.mergeSort(c, 0, c.length - 1);
		System.out.println(Arrays.toString(c));
		
		int d[] = {};
		t.mergeSort(d, 0, d.length - 1);
		System.out.println(Arrays.toString(d));
	}

}
