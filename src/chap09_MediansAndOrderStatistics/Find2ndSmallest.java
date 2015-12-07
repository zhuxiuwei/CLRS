package chap09_MediansAndOrderStatistics;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 习题9.1-1
 * 9.1-1 找到n个元素中第二小的元素，最坏情况下需要n+⌈lgn⌉-1次比较的算法？  
 * @author xiuzhu
 * 思路： We can compare the elements in a tournament fashion - we split them into pairs, compare each pair and then proceed to compare the winners in the same fashion. We need to keep track of each "match" the potential winners have participated in.
We select a winner in n−1 matches. At this point, we know that the second smallest element is one of the lgn elements that lost to the smallest ­ each of them is smaller than the ones it has been compared to, prior to losing. 
In another ⌈lgn⌉−1 comparisons we can find the smallest element out of those. This is the answer we are looking for.  
 */
public class Find2ndSmallest {

	private int smallest = 0;
	private int compareCount = 0;
	//'losers' when compare with the smallest. should be ⌈lgn⌉  losers.
	private ArrayList<Integer> compareTrace = new ArrayList<Integer>();
	
	public int callFind(int a[]) throws Exception{
		if(a == null || a.length <= 1)
			throw new Exception("Illegal parameter");
		smallest = findSmallest(a);
		
		
	}
	
	/**
	 * find smallest in array of n elements. (n-1 compare)
	 * @param a
	 * @return
	 */
	private int findSmallest(int a[]){
		int temp = a[0];
		for (int i = 1; i < a.length; i++) {
			if(a[i] < temp){
				temp = a[i];
				compareCount ++;	//trace compare count
			}
		}
		return temp;
	}
	
	public static void main(String[] args) {

	}

}
