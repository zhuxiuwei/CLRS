package chap15_DynamicProgramming;

import java.util.Arrays;

/**
 * 习题15.1-4。修改MEMOIZED-CUT-ROD，使之不止返回最大收益，还返回切割方案
 * @author xiuzhu
 * 160406
 */
public class CutRodMemorizedExtended {

	/**
	 * bottom Up cut rod. 不止计算最大收益，还记录最优解对应的第一段钢条的切割长度.  P209
	 * @param prices 价格数组
	 * @param length 长度为length的钢条
	 * @return 二维数组，第一个数组记录各个长度最优解，第二个数组记录最优解对应的第一段钢条切割长度
	 */
	public int[][] call_cutRod_UpBottom_Memorized(int[] prices, int length){
		int[] r = new int[prices.length];	//store memorized result.
		int[] s = new int[prices.length];	//记录最优解对应的第一段钢条切割长度 
		int[][] res = {r,s};
		for (int i = 0; i < r.length; i++) 
			r[i] = Integer.MIN_VALUE;
		cutRod_UpBottom_Memorized(prices, length, r, s);
		
		//print info
		System.out.println("各个长度最优解: " + Arrays.toString(r));
		System.out.println("最优解对应的第一段钢条切割长度: " + Arrays.toString(s));
		System.out.println(length + "的最优解是： " + r[length]);
		System.out.print(length + "的切割方案是： "); 
		int n = length;
		while(n > 0){
			System.out.print(s[n] + " ");
			n = n - s[n];
		}
		
		return res;
	}
	private int cutRod_UpBottom_Memorized(int[] prices, int length, int[] r, int[] s){
		if(length < 0 || length > prices.length - 1)	//illegal input, out of prices range
			return Integer.MIN_VALUE;

		if(r[length] > Integer.MIN_VALUE) 
			return r[length];
		
		int result = 0;
		for (int i = 1; i <= length; i++) {
			int reminderPrice = r[length - i];
			if(reminderPrice == Integer.MIN_VALUE){
				reminderPrice = cutRod_UpBottom_Memorized(prices, length - i, r, s);
				r[length - i] = reminderPrice;
			}
			int newResult = prices[i] + reminderPrice;
			if(result < newResult){
				result = newResult;
				s[length] = i;
			}
			r[length] = result;
		}
		return result;
	}
	
	public static void main(String[] args) {
		int prices[] = {0,1,5,8,9,10,17,17,20,24,30};
		CutRodMemorizedExtended c = new CutRodMemorizedExtended();
		c.call_cutRod_UpBottom_Memorized(prices, 8);
	}

}
