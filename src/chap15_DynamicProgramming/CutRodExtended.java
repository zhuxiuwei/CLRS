package chap15_DynamicProgramming;

import java.util.Arrays;

/**
 * cut rod extended。不止计算最大收益，还返回切割方案。
 * @author xiuzhu
 * 160406
 */
public class CutRodExtended {

	/**
	 * bottom Up cut rod. 不止计算最大收益，还记录最优解对应的第一段钢条的切割长度.  P209
	 * @param prices 价格数组
	 * @param length 长度为length的钢条
	 * @return 二维数组，第一个数组记录各个长度最优解，第二个数组记录最优解对应的第一段钢条切割长度
	 */
	public int[][] cutRod_BottomUp_extended(int[] prices, int length){
		if(length < 0 || length > prices.length - 1)	//illegal input, out of prices range
			return null;
		else{
			int[] r = new int[prices.length];	//记录各个长度最优解
			int[] s = new int[prices.length];	//记录最优解对应的第一段钢条切割长度
			int result[][] = {r,s};		//返回结果
			
			for (int i = 0; i <= length; i++) {
				int q = 0;
				for (int j = 1; j <= i; j++) {
					int temp = prices[j] + r[i - j];
					if(q < temp){
						q = temp;
						r[i] = q;
						s[i] = j;
					}
				}
			}
			System.out.println("各个长度最优解: " + Arrays.toString(r));
			System.out.println("最优解对应的第一段钢条切割长度: " + Arrays.toString(s));
			return result;
		}
		
	}
	
	/**
	 * 打印cutRod_BottomUp_extended记录的最优切割长度。 P210
	 */
	public void print_cutRod_BottomUp_extended(int prices[], int length){
		int res[][] = cutRod_BottomUp_extended(prices, length);
		int[] r = res[0];	//record optimum solution of each length
		int[] s = res[1];	//记录最优解对应的第一段钢条切割长度 
		
		//
		System.out.println(length + "的最优解是： " + r[length]);
		System.out.print(length + "的切割方案是： "); 
		int n = length;
		while(n > 0){
			System.out.print(s[n] + " ");
			n = n - s[n];
		}
	}
	
	public static void main(String[] args) {
		int prices[] = {0,1,5,8,9,10,17,17,20,24,30};
		CutRodExtended c = new CutRodExtended();
		c.print_cutRod_BottomUp_extended(prices, 8);
	}

}
