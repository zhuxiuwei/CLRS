package chap08_SortInLinerTime;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * 思考题8-2.
 * 8-2 数组n个元素，都是0或1。对这样的数组排序算法可能有以下三种特性的一部分：  
	1. 时间复杂度O(n)。   
	2. 算法是稳定的。  
	3. 算法是原地排序。除了输入数组外，算法只需要固定的额外存储空间。  
	a.给出满足1,2的算法。  
	b 给出满足1,3的算法。  
	c.给出满足2,3的算法。  
 * @author xiuzhu
 * 151202
 */
public class SortArrayOfZeroOne {
	private int[] a;	//array to be sort
	private boolean inputIsValid = true;	//if input array is valid
	
	public SortArrayOfZeroOne(int[] _a){
		//check if input is valid.
		for (int i = 0; i < _a.length; i++) {
			if(!(_a[i] == 0 || _a[i] == 1)){
				inputIsValid = false;
				break;
			}
		}
		a = _a;
	}
	
	/**
	 * O(n)，稳定的算法
	 * 用类似桶排序的思想。
	 */
	public void sortA(){
		if(inputIsValid){
			LinkedList<Integer> list0 = new LinkedList<Integer>();	//定义2个桶
			LinkedList<Integer> list1 = new LinkedList<Integer>();
			for (int i = 0; i < a.length; i++) {	//入桶
				if(a[i] == 0)
					list0.add(a[i]);
				else
					list1.add(a[i]);
			}
			//合并桶
			Iterator<Integer> it = list0.iterator();
			int index = 0;
			while(it.hasNext()) a[index++] = it.next();
			it = list1.iterator();
			while(it.hasNext()) a[index++] = it.next();
			System.out.println("算法a: " + Arrays.toString(a));
		}
	}
	
	/**
	 * O(n)，原址排序的算法
	 * 思想类似记数排序，但是不保证稳定性。
	 */
	public void sortB(){
		if(inputIsValid){
			int count0 = 0, count1 = 0;	//记数用的额外数据结构，只需要两个int
			for (int i = 0; i < a.length; i++) {	//记数
				if(a[i] == 0) count0++;
				else count1++;
			}
			for (int i = 0; i < a.length; i++) {	//根据记数结果排序
				if(i < count0) a[i] = 0;
				else a[i]=1;
			}
			System.out.println("算法b: " + Arrays.toString(a));
		}
	}
	
	/**
	 * 稳定，原址排序的算法
	 * 冒泡排序即可
	 */
	public void sortC(){
		if(inputIsValid){
			for (int i = 1; i < a.length; i++) {
				for (int j = a.length - 1; j > i; j--) {
					if(a[j - 1] > a[j]){
						int temp = a[j - 1];
						a[j - 1] = a[j];
						a[j] = temp;
					}
				}
			}
			System.out.println("算法c: " + Arrays.toString(a));
		}
	}
	
	public static void main(String[] args) {
		SortArrayOfZeroOne s = new SortArrayOfZeroOne(new int[]{1,1,1,0,1,1,0,0,1});
		s.sortA();
		s.sortB();
		s.sortC();
	}

}
