package chap16_GreedyAlgorithms;

import java.util.Arrays;

/**
 * 活动选择问题
 * @author xiuzhu
 * 160810
 */
public class ActivitySelector {

	/**
	 * 活动选择问题的迭代贪心算法.p241
	 * @param s 活动开始时间
	 * @param f 活动结束时间
	 */
	public static void selector_greedy(int[] s, int f[]){
		StringBuilder solution = new StringBuilder();
		if(s.length == 0)
			return;
		int lastEnd = f[0];
		for (int i = 1; i < s.length; i++) {
			if(s[i] >= lastEnd){	//next activity found
				lastEnd = f[i];
				if(solution.length() != 0) solution.append(",");
				solution.append(i);
			}
		}
		System.out.println("Max compatibile solution: " + solution.toString());
	}
	
	/**
	 * 活动选择问题的动态规划算法.UpBottom. 16.1-1。 ！！！！ NOT WORKING@ 结果不对！！。
	 * @param s 活动开始时间
	 * @param f 活动结束时间
	 */
	public static int selector_dynamicProgramming(int[] s, int f[]){
		int c[][] = new int[s.length][s.length];
		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < c.length; j++) {
				c[i][j] = Integer.MIN_VALUE;
			}
		}
		int res = selector_dynamicProgramming_helper(s, f, 0, c.length - 1, c);
		System.out.println(Arrays.deepToString(c));
		return res;
	}
	private static int selector_dynamicProgramming_helper(int[] s, int f[], int start, int end, int[][] c){
		if(c[start][end] != Integer.MIN_VALUE)
			return c[start][end];
		else{
			System.out.println("calculate: " + start + ", " + end);
			if(start >= end){
				c[start][end] = 0;
				return c[start][end];
			}
			if(start + 1 == end){
				if(s[end] >= f[start])
					c[start][end] = c[start][end] = 2;
				else
					c[start][end] = 0;
				return c[start][end];
			}
			for (int k = start + 1; k <= end ; k++) {
				
				//calculate left
				int left = k, right = k;
				for (int i = k - 1; i >= start; i--) {
					if(f[i] <= s[k]){
						left = i;
						break;
					}
				}
				int l = selector_dynamicProgramming_helper(s, f, start, left, c);
				
				//calculate right
				for (int i = k + 1; i <= end; i++) {
					if(f[k] <= s[i]){
						right = i;
						break;
					}
				}
				int r = selector_dynamicProgramming_helper(s, f, right, end, c);
				int temp = l + r + 1;
				if(temp > c[start][end])
					c[start][end] = temp;
			}
			return c[start][end];
		}
	}
	
	public static void main(String[] args) {
		int s[] = {0,1,3,0,5,3,5,6,8,8,2,12};	//开始的0是虚拟活动，类似哨兵的作用。
		int f[] = {0,4,5,6,7,9,9,10,11,12,14,16};
		ActivitySelector.selector_greedy(s, f);
		System.out.println(selector_dynamicProgramming(s, f));
	}

}
