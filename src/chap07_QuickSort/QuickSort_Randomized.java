package chap07_QuickSort;

import java.util.Arrays;
import java.util.Random;

/**
 * 7.3 快速排序的随机化版本
 * @author xiuzhu
 * 151124
 */
public class QuickSort_Randomized {
	
	Random r = new Random();

	//Randomized Partition
	public int partition_Randomized(int a[], int start, int end){
		int index = -1;
		if(a == null || a.length <= 1)
			return index;
		
		randomnizedArray(a, start, end);	//和普通版本的区别 -- 将数组随机化
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
	
	//Sort. 和普通版本的没有区别。
	public void sort_Randomized(int a[], int start, int end){
		int mid = partition_Randomized(a, start, end);
		if(mid + 1 < end)
			sort_Randomized(a, mid + 1, end);
		if(mid > start + 1)
			sort_Randomized(a, start, mid - 1);
	}
	
	/**
	 * 将数组随机化，就是把end和start（包括）...end（不包括）...之间的一个数随机交换。
	 */
	public void randomnizedArray(int a[], int start, int end){
		if(start == end)
			return;
		//产生一个随机的index，end>index>=start
		int randamIndex = -1;
		while(randamIndex < start){
			randamIndex = r.nextInt(end);
		}
		//交换randamIndex和end元素
		int temp = a[randamIndex];
		a[randamIndex] = a[end];
		a[end] = temp;
	}
	
	//test
	public static void main(String[] args) {
		QuickSort_Randomized s = new QuickSort_Randomized();
		int a0[] = {};
		int a1[] = {1};
		int a2[] = {2,1};
		int a3[] = {1,2, 3, 4, 5};
		int a4[] = {5,4, 3, 2, 1};
		int a5[] = {3, 1, 2};
		int a6[] = {13,19,9,5,12,8,7,4,21,2,6,11};
		s.sort_Randomized(a0, 0, a0.length - 1);
		s.sort_Randomized(a1, 0, a1.length - 1);
		s.sort_Randomized(a2, 0, a2.length - 1);
		s.sort_Randomized(a3, 0, a3.length - 1);
		s.sort_Randomized(a4, 0, a4.length - 1);
		s.sort_Randomized(a5, 0, a5.length - 1);
		s.sort_Randomized(a6, 0, a6.length - 1);
		System.out.println(Arrays.toString(a0));
		System.out.println(Arrays.toString(a1));
		System.out.println(Arrays.toString(a2));
		System.out.println(Arrays.toString(a3));
		System.out.println(Arrays.toString(a4));
		System.out.println(Arrays.toString(a5));
		System.out.println(Arrays.toString(a6));
	}

}
