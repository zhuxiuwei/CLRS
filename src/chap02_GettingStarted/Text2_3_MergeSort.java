package chap02_GettingStarted;

import java.util.Arrays;

/**
 * 1. 归并排序源代码 p17
 * 2. 练习题2.3-2 （方法new_merge） p22
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
		
		/**
		 * 带“哨兵”的merge
		 */
		merge(src, begin, mid, end);
		
		/**
		 * 练习题2.3-2不带哨兵的merge
		 */
		//new_merge(src, begin, mid, end);
	}
	
	/**
	 * 带“哨兵”的原生态merge
	 */
	private void merge(int[] src, int begin, int mid, int end){
		
		if(src.length == 0 || src.length == 1)	//in case src is empty
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
	
	/**
	 * 练习题2.3-2， 新merge --- 不使用哨兵， 而是当一个数组走完了，直接把另一个数组中剩下的写到原始数组。
	 */
	private void new_merge(int[] src, int begin, int mid, int end){
		
		if(src.length == 0 || src.length == 1)	//in case src is empty
			return;
		
		//初始化左右两个子数组
		int[] left = new int[mid - begin + 1];
		int[] right = new int[end - mid];
		for(int i = 0; i < left.length; i ++)
			left[i] = src[begin + i];
		for(int i = 0; i < right.length; i ++)
			right[i] = src[mid + i + 1];
		
		//开始merge
		int temp1 = 0;
		int temp2 = 0;
out:	for(int i = begin; i <= end; i ++){
			if(left[temp1] <= right[temp2]){
				src[i] = left[temp1++];
				if(temp1 == left.length){	//left结束了，把剩下的right填满src
					for(int j = temp2; j < right.length; j ++)
						src[++i] = right[j];
					break out;
				}
			}else{
				src[i] = right[temp2++];
				if(temp2 == right.length){	//right结束了，把剩下的left填满src
					for(int j = temp1; j < left.length; j ++)
						src[++i] = left[j];
					break out;
				}
			}
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
		
		int f[] = {8,7,2,10, 100, 99, 80, 98};
		t.mergeSort(f, 0, f.length - 1);
		System.out.println(Arrays.toString(f));
	}

}
