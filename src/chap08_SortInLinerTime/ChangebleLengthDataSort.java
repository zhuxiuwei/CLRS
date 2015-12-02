package chap08_SortInLinerTime;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * 习题8-3 变长数据项排序
 * @author xiuzhu
 * #####8-3 变长数据项的排序  

 */
public class ChangebleLengthDataSort {

	/**
	 * 数组中所有整数包含的数字位数总数为n，时间复杂度为O(n)的排序。
	 * 思路：类似桶排序，把长度作为桶放进去。
	 * @param a
	 */
	public void sortIntArray(int a[]){
		//统计总的数字数量
		LinkedHashMap<Integer, LinkedList<Integer>> map = new LinkedHashMap<Integer, LinkedList<Integer>>();
		
		//初始化n
		int n = 0;
		for (int i = 0; i < a.length; i++) 
			n +=  (a[i] + "").length();
		
		//初始化桶
		for (int i = 1; i <= n; i++) 
			map.put(i, new LinkedList<Integer>());
			
		
		//把各个数字放入各个桶
		for (int i = 0; i < a.length; i++) {
			int nNow = (a[i] + "").length();
			map.get(nNow).add(a[i]);
		}
		
		//调用Java默认的merge sort对各个桶内排序，并把排序好的结果直接连接到result，即是最终结果。
		int index = 0;
		for (int i = 1; i <= n; i++) {
			LinkedList<Integer> li = map.get(i);
			li.sort(Comparator.naturalOrder());
			Iterator<Integer> it = li.iterator();
			while(it.hasNext())
				a[index++] = it.next();
		}
		System.out.println("result: " + Arrays.toString(a));
	}
	
	public static void main(String[] args) {
		ChangebleLengthDataSort c = new ChangebleLengthDataSort();
		c.sortIntArray(new int[]{22,3,14,142,31});
	}

}
