package chap16_GreedyAlgorithms;

import java.util.Arrays;
/**
 * @author Xiuwei Zhu
 *	160819
 */
public class KnapsackProblem {

	static boolean debug = false;
	/**
	 * 16.2-2: 0-1 Knapsack Problem, using DP solution. 
	 * @param w	weight
	 * @param v value
	 * @param W Knapsack available size
	 * @return Max value in knapsack
	 */
	public static int zeroOneKnapsackProblem(int[] w, int[] v, int W){
		int c[][] = new int[w.length][W + 1];
		for (int i = 0; i < c.length; i++)
			for (int j = 0; j < c[i].length; j++) {
				c[i][j] = i == 0 ? 0 : Integer.MIN_VALUE;
			}
		int res = zeroOneKnapsackProblem_helper(w, v, c, w.length - 1, W);
		if(debug)	
			System.out.println(Arrays.deepToString(c));
		
		//print result
		System.out.print("Max value: " + c[w.length - 1][W]);
		System.out.print(". Included item index: ");
		printSolution(w, v, c, w.length - 1, W);
		System.out.println();
		
		return res;
	}
	/**
	 * @param c Table to store results. c[i][j] means, max value in knapsack when consider first i items while knapsack available size=j.
	 * @param j When only consider first j items
	 * @return Max value in knapsack when consider first j items while knapsack available size=W.
	 */
	private static int zeroOneKnapsackProblem_helper(int[] w, int[] v, int c[][], int j, int W){
		if(W <= 0 || j <= 0)
			return 0;
		else{
			for (int currentW = W; currentW >= 0; currentW--) {
				if( c[j][currentW] == Integer.MIN_VALUE){
					if(debug) System.out.println("calculate " + j + " " + currentW);
					int temp1 = W - w[j] > 0 ? zeroOneKnapsackProblem_helper(w, v, c, j - 1, W - w[j]) + v[j] : 0;	//in case optimal solution contains item j
					int temp2 = zeroOneKnapsackProblem_helper(w, v, c, j - 1, W);	//in case optimal solution NOT contains item j
					c[j][currentW] = Math.max(temp1, temp2);
					if(debug) System.out.println("c " + j + " " + currentW + ": " + c[j][currentW]);
				}
			}
			return c[j][W];
		}
	}
	//print solution helper.
	private static void printSolution(int[] w, int[] v, int c[][], int j, int W){
		if(W - w[j] < 0 || j < 1)
			return;
		if(c[j - 1][W - w[j]] == c[j][W] - v[j]){	//current item included
			System.out.print(j + " ");
			printSolution(w, v, c, j - 1, W - w[j]);
		}else{
			printSolution(w, v, c, --j, W);
		}
	}
	
	public static void main(String[] args) {
		
		//Max value: 15. Included item index: 5 2 1 
		int[] w = {0, 2, 2, 6, 5, 4};	//weight. 开始的0是哨兵。
		int[] v = {0, 6, 3, 5, 4, 6 };	//value.  
		int W = 10;	//package size
		zeroOneKnapsackProblem(w, v, W);
		
		//Max value: 900. Included item index: 3 2 
		int[] w2 = {0, 20, 10, 30};
		int[] v2 = {0, 120, 300,600 };
		int W2 = 50;	//package size
		zeroOneKnapsackProblem(w2, v2, W2);

	}

}
