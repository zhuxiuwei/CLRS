package chap06;

import java.util.Arrays;

/**
 * 用循环替代递归的最大堆维护算法。
 * @author xiuzhu
 * 20151110
 */
public class Pac625_Max_Heapify_NoRescursive {

	/**
	 * 对数组A的元素i进行最大堆调整。用循环替代递归
	 * @param a
	 * @param i
	 */
	public void maxHeapify_NoRescursive(int[] a , int i){
		if(i > a.length - 1 || i < 0){
			System.out.println("Invalid input!");
			return;
		}
		while(true){
			int leftIndex = 2 * i + 1;
			int rightIndex = 2 * i + 2;
			int maxIndex = i;
			if(leftIndex <= a.length - 1 && a[leftIndex] > a[i])	//left
				maxIndex = leftIndex;
			if(rightIndex <= a.length - 1 && a[rightIndex] > a[maxIndex])	//right
				maxIndex = rightIndex;
			if(maxIndex != i){	//switch minIndex and i
				int temp = a[i];
				a[i] = a[maxIndex];
				a[maxIndex] = temp;
			}
			//judge if break
			i = maxIndex;
			int tempRight = 2 * i + 2 <= a.length - 1 ? a[2 * i + 2]: Integer.MIN_VALUE;
			int tempLeft = 2 * i + 1 <= a.length - 1 ? a[2 * i + 1]: Integer.MIN_VALUE;
			if(a[maxIndex] >= tempRight && a[maxIndex] >= tempLeft)
				break;
		}
	}
	
	public static void main(String[] args) {
		Pac625_Max_Heapify_NoRescursive m = new Pac625_Max_Heapify_NoRescursive();
		int a[] = {27, 17, 3, 10, 13, 10};
		m.maxHeapify_NoRescursive(a,1);
		System.out.println(Arrays.toString(a));
		a = new int[]{27, 17, 3, 10, 13, 10};
		m.maxHeapify_NoRescursive(a,2);
		System.out.println(Arrays.toString(a));
		a = new int[]{27, 17, 3, 10, 13, 10};
		m.maxHeapify_NoRescursive(a,0);
		System.out.println(Arrays.toString(a));
		a = new int[]{27, 17, 3, 10, 13, 10};
		m.maxHeapify_NoRescursive(a,3);
		System.out.println(Arrays.toString(a));
		a = new int[]{27, 17, 3, 16, 13, 10, 1, 5, 7, 12, 4, 8, 9, 0};
		m.maxHeapify_NoRescursive(a,2);
		System.out.println(Arrays.toString(a));
		a = new int[]{27, 17, 3, 10, 13, 10};
		m.maxHeapify_NoRescursive(a,6);
	}

}
