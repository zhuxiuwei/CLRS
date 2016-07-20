package chap15_DynamicProgramming;

import java.util.Arrays;

/**
 * cut rod problem。
 * @author xiuzhu
 * 160405
 * 注意点：
 *  1. *** 注意记录结果的方式，是返回数组中的一个元素。看起来用动态规划算法这是一个很好用的方案，习题15.1-5斐波那契数列的返回结果用这种表示法，形式也很简单。 ***
 *  2. 注意j循环的起始值。
 */
public class CutRod {

	/**
	 * up bottom cut rod. O(2^n)。 P206
	 * @param prices 价格数组
	 * @param length 长度为length的钢条
	 * @return 长度为length的钢条切割最大收益
	 */
	public int cutRod_UpBottom(int[] prices, int length){
		if(length < 0 || length > prices.length - 1)	//illegal input, out of prices range
			return Integer.MIN_VALUE;
		
		System.out.println("calculate: " + length);   //you can see for a given length n, it will be calculated 2^n times.
		int result = 0;
		for (int i = 1; i <= length; i++) {
			int temp = prices[i] + cutRod_UpBottom(prices, length - i);
			result = result < temp? temp: result;
		}
		return result;
	}
	
	/**
	 * Memorized up bottom cut rod. O(n^2)。 P207
	 * @param prices 价格数组
	 * @param length 长度为length的钢条
	 * @return 长度为length的钢条切割最大收益
	 */
	public int call_cutRod_UpBottom_Memorized(int[] prices, int length){
		int[] r = new int[prices.length];	//store memorized result.
		for (int i = 0; i < r.length; i++) 
			r[i] = Integer.MIN_VALUE;
		return cutRod_UpBottom_Memorized(prices, length, r);
	}
	private int cutRod_UpBottom_Memorized(int[] prices, int length, int[] r){
		if(length < 0 || length > prices.length - 1)	//illegal input, out of prices range
			return Integer.MIN_VALUE;

		System.out.println("calculate: " + length);   //you can see for a given length n, it will be calculated 1 time only, and from n to 0
		
		if(r[length] > Integer.MIN_VALUE) 
			return r[length];
		
		int result = 0;
		for (int i = 1; i <= length; i++) {
			int reminderPrice = r[length - i];
			if(reminderPrice == Integer.MIN_VALUE){
				reminderPrice = cutRod_UpBottom_Memorized(prices, length - i, r);
				r[length - i] = reminderPrice;
			}
				
			int temp = prices[i] + reminderPrice;
			result = result < temp? temp: result;
		}
		
		return result;
	}
	
	/**
	 * bottom Up cut rod. O(n^2)。 P208
	 * @param prices 价格数组
	 * @param length 长度为length的钢条
	 * @return 长度为length的钢条切割最大收益
	 */
	public int cutRod_BottomUp(int[] prices, int length){
		if(length < 0 || length > prices.length - 1)	//illegal input, out of prices range
			return 0;
		
		int r[] = new int[prices.length];
		for (int i = 0; i <= length; i++) {
			int q = 0;
			for (int j = 1; j <= i; j++) 	//！！！！注意2. 注意j循环的起始值。
				q = Math.max(q, prices[j] + r[i - j]);
			System.out.println("Calculate: " + i);    //you can see for a given length n, it will be calculated 1 time only, and from 0 to n.
			r[i] = q;
		}
		return r[length];  //！！！注意1. 注意记录结果的方式，是返回数组中的一个元素
	}
	
	public static void main(String[] args) {
		int prices[] = {0,1,5,8,9,10,17,17,20,24,30};
		CutRod c = new CutRod();
		
		System.out.println(c.cutRod_UpBottom(prices, 9));
		System.out.println("------------------------------------");
		System.out.println(c.call_cutRod_UpBottom_Memorized(prices, 10));
		System.out.println("------------------------------------");
		System.out.println(c.cutRod_BottomUp(prices, 10));
		
	}

}
