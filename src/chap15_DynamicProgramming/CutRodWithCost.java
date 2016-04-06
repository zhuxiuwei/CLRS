package chap15_DynamicProgramming;

import java.util.Arrays;

/**
 * 习题15.1-3。每次切割还要付出成本的钢条切割动态规划方案。
 * @author xiuzhu
 * 160406
 */
public class CutRodWithCost {

	private boolean debug = false;
	
	/**
	 * 习题15.1-3。每次切割还要付出成本的钢条切割动态规划方案。
	 * @param prices 价格数组
	 * @param length 长度为length的钢条
	 * @param cost 每次切割固定的cost
	 * @return 二维数组，第一个数组记录各个长度最优解，第二个数组记录最优解对应的第一段钢条切割长度
	 */
	public int cutRod_BottomUp_WithCost(int[] prices, int length, int cost){
		if(length < 0 || length > prices.length - 1)	//illegal input, out of prices range
			return 0;
 		int[] r = new int[prices.length];	//记录各个长度最优解
		for (int i = 0; i <= length; i++) {
			int q = 0;
			for (int j = 1; j <= i; j++) {
				int temp = i == j ? prices[j]: prices[j] + r[i - j] - cost;		//only difference with normal BottomUp CutRod
				if(q < temp){
					q = temp;
					r[i] = q;
				}
			}
		}
		if(debug)	System.out.println(Arrays.toString(r));
		return r[length];
	}
	
	public static void main(String[] args) {
		int prices[] = {0,1,5,8,9,10,17,17,20,24,30};
		int cost = 1;
		CutRodWithCost c = new CutRodWithCost();
		for (int i = 0; i < prices.length; i++) {
			System.out.println( "i=" + i + ", result:" + c.cutRod_BottomUp_WithCost(prices, i, cost));
		}
	}

}
