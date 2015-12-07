package chap09_MediansAndOrderStatistics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 习题9.1-1
 * 9.1-1 找到n个不同元素中第二小的元素，最坏情况下需要n+⌈lgn⌉-1次比较的算法？  
 * @author xiuzhu
 * 思路： We can compare the elements in a tournament fashion - we split them into pairs, compare each pair and then proceed to compare the winners in the same fashion. We need to keep track of each "match" the potential winners have participated in.
We select a winner in n−1 matches. At this point, we know that the second smallest element is one of the lgn elements that lost to the smallest ­ each of them is smaller than the ones it has been compared to, prior to losing. 
In another ⌈lgn⌉−1 comparisons we can find the smallest element out of those. This is the answer we are looking for.  
	
	注意点：
	1. 数组越界问题。
	2. 算法思路问题。没想到这种两两比较的快速的算法思路，看的网上的答案。
 */
public class Find2ndSmallest {

	private int smallest = 0;
	private HashMap<Integer, ArrayList<Integer>> compareTrace = new HashMap<Integer, ArrayList<Integer>>();
	
	/**
	 * 算法入口。
	 */
	public int findSecondSmallest(Integer a[]) throws Exception{
		if(a == null || a.length <= 1)
			throw new Exception("Illegal parameter");
		
		//find smallest, meanwhile compareTrace is ready. (n - 1) compare.
		int smallest = find(a);
		
		//find smallest in compareTrace. ⌈lgn⌉−1 compare.
		int secondSmallest = findSmallest(compareTrace.get(smallest));
		
		System.out.println(Arrays.toString(a) + " 2nd smallest: " + secondSmallest);
		
		return secondSmallest;
	}
	
	/**
	 * 主要算法逻辑。是递归调用的。
	 */
	private int find(Integer a[]){
		ArrayList<Integer> tempArray = new ArrayList<Integer>();
		for (int i = 0; i < a.length - 1; i += 2) {	//！！！注意点：i的终止条件。写成i < a.length的话在奇数个元素的数组会数组越界
			int smaller = 0, bigger = 0;
			if(a[i] < a[i+1]){	//compare a[i] and a[i+1]
				smaller = a[i];
				bigger = a[i+1];
			}else{
				smaller = a[i+1];
				bigger = a[i];
			}
			
			if(a.length == 2){	//champion found, quit recursive.
				return smaller;
			}
			
			//add to compareTrace
			if(!compareTrace.containsKey(smaller)){
				ArrayList<Integer> t = new ArrayList<Integer>();
				t.add(bigger);
				compareTrace.put(smaller, t);
			}else{
				ArrayList<Integer> t = compareTrace.get(smaller);
				t.add(bigger);
				compareTrace.put(smaller, t);
			}
			//add smaller to tempArray for next compare
			tempArray.add(a[i]);
		}
		if(a.length % 2 > 0)	//如果数组是奇数个数，最后一个数没有参与本轮比较，直接加入下轮比较
			tempArray.add(a[a.length - 1]);
		return find(tempArray.toArray(new Integer[(int)Math.ceil((double)a.length/2)]));
	}
	
	/**
	 * find smallest in array. (n-1 compare)
	 * @param a
	 * @return
	 */
	private int findSmallest(int a[]){
		int temp = a[0];
		for (int i = 1; i < a.length; i++) {
			if(a[i] < temp)
				temp = a[i];
		}
		return temp;
	}
	private int findSmallest(ArrayList<Integer> arrayList){
		int a[] = new int[arrayList.size()];
		for (int i = 0; i < arrayList.size(); i++) {
			a[i] = arrayList.get(i);
		}
		return findSmallest(a);
	}
	
	/**
	 * get value of ⌈lgn⌉
	 */
	private int log2N(int n){
		//Java没有log2N算法，提供了logeN的。下面的算法，利用log2N=logeN/loge2来计算。
		return (int)Math.ceil((Math.log(n)/Math.log(2)));
	}
	
	public static void main(String[] args) throws Exception {
		Find2ndSmallest f = new Find2ndSmallest();
		f.findSecondSmallest(new Integer[]{1, 2, 100, 98, 8, 7, 6 ,9});
		f.findSecondSmallest(new Integer[]{1, 2, 100, 98, 8, 7, 6 ,9, 10});
		f.findSecondSmallest(new Integer[]{1, 3, 100, 98, 8, 7, 6 ,9, 2});
	}

}
