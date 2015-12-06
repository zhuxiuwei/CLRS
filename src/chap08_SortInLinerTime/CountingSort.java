package chap08_SortInLinerTime;

import java.util.Arrays;

/**
 * 记数排序 p108
 * @author xiuzhu
 * 151201
 */
public class CountingSort {

	private int k = -1;	//数组中的元素都是0到K区间的整数
	
	public int[] sort(int a[]){
		if(checkInput(a))
			System.out.println(Arrays.toString(a) + ": 输入合法,  k=" +  k);
		else{
			System.out.println(Arrays.toString(a) + ": 输入不合法。" );
			return null;
		}
		//result
		int b[] = new int[a.length];
		
		//step1: create and initialize c[], make c[i] contains numbers of elements equals to i.
		int[] c = new int[k + 1];
		for (int i = 0; i < a.length; i++)
			c[a[i]] += 1;
		
		//step2: update c, make c[i] contains numbers of elements <= to i.
		for (int i = 1; i < c.length; i++)
			c[i] += c[i-1];
		
		//step3: update b[] based on c[].
		for (int i = b.length - 1; i >= 0; i--) {
			int currntA = a[i];
			int numberOfElementLE = c[currntA];
			c[currntA] --;
			b[numberOfElementLE - 1] = currntA;
			System.out.println("c=" + Arrays.toString(c)); 
		}
		
		return b;
	}
	
	/**
	 * 就地排序的counting sort。习题8-2 e
	 */
	public int[] sort_in_place(int a[]){
		if(checkInput(a))
			System.out.println(Arrays.toString(a) + ": 输入合法,  k=" +  k);
		else{
			System.out.println(Arrays.toString(a) + ": 输入不合法。" );
			return null;
		}
		
		//step1: create and initialize c[], make c[i] contains numbers of elements equals to i.
		int[] c = new int[k + 1];
		for (int i = 0; i < a.length; i++)
			c[a[i]] += 1;
		
		//step2: update a[] based on c[].
		for (int i = 0, j = 0; i < a.length;) {
			while(c[j] != 0){
				a[i++] = j;
				c[j]--;
			}
			j ++;
		}
		
		return a;
	}
	
	/**
	 * 检查输入是否符合CountingSort的要求：k=O(n)，顺便求出k。
	 * @param a
	 * @return
	 */
	private boolean checkInput(int a[]){
		int n = a.length;
		int tempMax = -1;
		for (int i = 0; i < a.length; i++) {
			if(a[i] > tempMax)
				tempMax = a[i];
			if(a[i] > n)
				return false;
		}
		k = tempMax;
		return true;
	}
	
	public static void main(String[] args) {
		CountingSort cs = new CountingSort();
		int[] a3 = {2,5,3,9,2,3,0,3};	//illegal input: 9
		System.out.println("result: " + Arrays.toString(cs.sort(a3)));
		System.out.println("result2: " + Arrays.toString(cs.sort_in_place(a3)) + "\r\n");
		int[] a4 = {};	//legal
		System.out.println("result: " + Arrays.toString(cs.sort(a4)));
		System.out.println("result2: " + Arrays.toString(cs.sort_in_place(a4)) + "\r\n");
		int[] a5 = {0};	//legal
		System.out.println("result: " + Arrays.toString(cs.sort(a5)));
		System.out.println("result2: " + Arrays.toString(cs.sort_in_place(a5)) + "\r\n");
		int[] a1 = {2,5,3,0,2,3,0,3};	//legal
		System.out.println("result: " + Arrays.toString(cs.sort(a1)));
		System.out.println("result2: " + Arrays.toString(cs.sort_in_place(a1)) + "\r\n");
		int[] a2 = {6,0,2,0,1,3,4,6,1,3,2};	//legal
		System.out.println("result: " + Arrays.toString(cs.sort(a2)));
		System.out.println("result2: " + Arrays.toString(cs.sort_in_place(a2)) + "\r\n");
	}

}
