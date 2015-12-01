package chap02_GettingStarted;

import java.util.Arrays;

/**
 * 递归方式的插入排序。
 * @author xiuzhu
 * 150909
 */
public class prac234_InsertSort_Recursive {

	public void insertSort_Recursive(int[] src, int end){
		if(end <= 0)
			return;	//也可以把这个判断条件改成 : if(end > 0) 加到下面的“insertSort_Recursive(src, end - 1);"前面
					
		insertSort_Recursive(src, end - 1);
		merge(src, end);
	}
	
	//新增加出来的方法，一个数字前面的数组中的元素都有序，将这个数字放到数组中合适的位置
	public void merge(int[] src, int end){
		if(src.length == 0 || src.length == 1)
			return;
		
		int key = src[end];
		int i = end - 1;
		while(i >=0 && src[i] > key)
			src[i+1] = src[i--];
		src[i + 1] = key;
	}
	
	public static void main(String[] args) {
		prac234_InsertSort_Recursive t = new prac234_InsertSort_Recursive();
		
		int e[] = {8,7,2,5,9,8,5};
		t.insertSort_Recursive(e, e.length - 1);
		System.out.println(Arrays.toString(e));
		
		int a[] = {8,7,2,5,9,8};
		t.insertSort_Recursive(a, a.length - 1);
		System.out.println(Arrays.toString(a));
		
		int b[] = {8,7};
		t.insertSort_Recursive(b, b.length - 1);
		System.out.println(Arrays.toString(b));
		
		int c[] = {8};
		t.insertSort_Recursive(c, c.length - 1);
		System.out.println(Arrays.toString(c));
		
		int d[] = {};
		t.insertSort_Recursive(d, d.length - 1);
		System.out.println(Arrays.toString(d));
		
		int f[] = {8,7,2,110,99, 898};
		t.insertSort_Recursive(f, f.length - 1);
		System.out.println(Arrays.toString(f));
	}

}
