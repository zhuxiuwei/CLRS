package chap06_HeapSort;

import java.util.Arrays;

/**
 * @author xiuzhu
 * 20151109
 * 一个注意点
 */
public class Prac622_Min_Heapify {

	/**
	 * 对数组A的元素i进行最小堆调整。
	 * @param a
	 * @param i
	 */
	public void minHeapify(int[] a , int i){
		if(i > a.length - 1 || i < 0){
			System.out.println("Invalid input!");
			return;
		}
		
		int leftIndex = 2 * i + 1;
		int rightIndex = 2 * i + 2;
		int minIndex = i;
		if(leftIndex <= a.length - 1 && a[leftIndex] < a[i])	//left
			minIndex = leftIndex;
		if(rightIndex <= a.length - 1 && a[rightIndex] < a[minIndex])	//right
			minIndex = rightIndex;
		if(minIndex != i){	//switch minIndex and i
			int temp = a[i];
			a[i] = a[minIndex];
			a[minIndex] = temp;
			minHeapify(a , minIndex);	//注意点1： 注意递归的位置。我第一次写在if外面，整个方法最后了。导致无限递归栈溢出
		}
		
	}
	
	public static void main(String[] args) {
		Prac622_Min_Heapify m = new Prac622_Min_Heapify();
		int a[] = {27, 17, 3, 10, 13, 10};
		m.minHeapify(a,1);
		System.out.println(Arrays.toString(a));
		a = new int[]{27, 17, 3, 10, 13, 10};
		m.minHeapify(a,2);
		System.out.println(Arrays.toString(a));
		a = new int[]{27, 17, 3, 10, 13, 10};
		m.minHeapify(a,0);
		System.out.println(Arrays.toString(a));
		a = new int[]{27, 17, 3, 10, 13, 10};
		m.minHeapify(a,3);
		System.out.println(Arrays.toString(a));
		a = new int[]{27, 17, 3, 10, 13, 10};
		m.minHeapify(a,6);
	}

}
