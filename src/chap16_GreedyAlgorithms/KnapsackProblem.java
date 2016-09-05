package chap16_GreedyAlgorithms;

import java.util.Arrays;
/**
 * @author xiuzhu
 *	160819
 */
public class KnapsackProblem {

	/**
	 * 16.2-2: 0-1 Knapsack Problem, using DP solution. 
	 * Note: Assumption of Precondition: v is already ordered. 
	 * @param w	weight
	 * @param v value
	 * @param W Knapsack available size
	 */
	public static int zeroOneKnapsackProblem(int[] w, int[] v, int W){
		int c[] = new int[w.length];
		for (int i = 0; i < c.length; i++)
			c[i] = Integer.MIN_VALUE;
		int res = zeroOneKnapsackProblem_helper(w, v, c, w.length - 1, W);
		System.out.println(Arrays.toString(c));
		return res;
	}
	public static int zeroOneKnapsackProblem_helper(int[] w, int[] v, int c[], int j, int W){
		if(j == 0 || W <= 0)
			return 0;
		if(c[j] == Integer.MIN_VALUE){
			System.out.println("calculate " + j + " " + W);
			int temp1 = zeroOneKnapsackProblem_helper(w, v, c, j - 1, W - w[j]) + v[j];	//in case optimal solution contains item j
			int temp2 = zeroOneKnapsackProblem_helper(w, v, c, j - 1, W);	//in case optimal solution NOT contains item j
			c[j] = Math.max(temp1, temp2);
			System.out.println("c" + j + ": " + c[j] + ", " + temp1 + "," + temp2);
		}
		return c[j];
	}
	
	public static void main(String[] args) {
		int[] w = {0, 20, 10, 30};	//weight. 开始的0是哨兵。
		int[] v = {0, 120, 300,600 };	//value.   Note: Assumption of Precondition: v is ordered. 
		int W = 50;	//package size
		zeroOneKnapsackProblem(w, v, W);
	}

}
