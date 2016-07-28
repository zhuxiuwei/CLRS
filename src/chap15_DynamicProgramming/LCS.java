package chap15_DynamicProgramming;

import java.util.Arrays;

/**
 * 最长公共子序列问题。3个注意点。
 * @author xiuzhu
 * 160726
 */
public class LCS {
	
	private boolean debug = false;
	
	/**
	 * 自底向上版本。
	 */
	public int lcs_length_bottomup_sentinel(String xS, String yS){
		xS = "|" + xS;	yS = "|" + yS; //add sentinel 加上哨兵
		char[] x = xS.toCharArray(), y = yS.toCharArray();
		int[][] c = new int[x.length][y.length];	//记录cij记录x[i] y[j] LCS长度
		char[][] b = new char[x.length][y.length];	//用来帮助构造最优解
		int res = lcs_length_bottomup_sentinel_helper(x, y, c, b);	//求最长LCS的长度
		print_bottomup_solution(x, y, c, b);	//打印solution
		print_bottomup_solution_withOutB(x, y, c);
		if(debug){
			System.out.println(Arrays.deepToString(c));
			System.out.println(Arrays.deepToString(b));
		}
		return res;
	}
	private int lcs_length_bottomup_sentinel_helper(char[] x, char[] y, int[][] c, char[][] b){
		for (int i = 1; i < x.length; i++) {
			for (int j = 1; j < y.length; j++) {
				if(x[i] == y[j]){
					c[i][j] = c[i - 1][j - 1] + 1;
					b[i][j] = '↖';
				}else if(c[i][j - 1] > c[i - 1][j] ){
					c[i][j] = c[i][j - 1];
					b[i][j] = '←';
				}else {
					c[i][j] = c[i - 1][j];
					b[i][j] = '↑';
				}
			}
		}
		return c[x.length - 1][y.length - 1];
	}
	//print LCS solution
	private void print_bottomup_solution(char[] x, char[] y, int[][] c, char[][] b){
		StringBuilder sb = new StringBuilder();
		int i = x.length - 1, j = y.length - 1;
		while(i >= 1 && j >= 1){
			if(b[i][j] == '↖'){
				sb.append(x[i]);
				i--;
				j--;
			}
			else if(b[i][j] == '↑')
				i --;
			else
				j --;
		}
		System.out.println("solution: " + sb.reverse());
	}
	
	/**
	 * 习题15.4-2. Print LCS solution without assistant array b.
	 * ！！注意点2：不是很好想，思路走了一些弯路。花了些时间才写对。
	 */
	private void print_bottomup_solution_withOutB(char[] x, char[] y, int[][] c){
		System.out.println(Arrays.deepToString(c));
		StringBuilder sb = new StringBuilder();
		int i = x.length - 1, j = y.length - 1;
		while(i >= 1 && j >= 1){	//因为此条件，不一定适用不带哨兵的LCS算法。
			if(x[i] == y[j]){
				sb.append(x[i]);
				i--;
				j--;
			}
			else{
				//从上，左邻居中随便选取一个c[]值最大的。左上邻居不可能是最大的，不用考虑。
				if(c[i - 1][j] >= c[i][j - 1])
					i --;
				else
					j--;
			}
		}
		System.out.println("solution(withOutBArray): " + sb.reverse());
	}

	
	/**
	 * 习题15.4-3，自顶向下带备忘录版本。 -- 有哨兵版本。
	 * @return
	 */
	public int lcs_length_upbottom_memorized_sentinel(String aS, String bS){
		aS = "|" + aS;	bS = "|" + bS; //add sentinel 加上哨兵
		char[] a = aS.toCharArray(), b = bS.toCharArray();
		int[][] memorized = new int[a.length][b.length]; 
		for (int i = 0; i < memorized.length; i++) 
			for (int j = 0; j < memorized[i].length; j++) 
				memorized[i][j] = Integer.MAX_VALUE;
		int res = lcs_length_upbottom_memorized_sentinel_helper(memorized, a, b, a.length - 1, b.length - 1);
		print_bottomup_solution_withOutB(a, b, memorized);	//！！注意点3: 习题15.4-2的打印solution，也适用于自顶向下带哨兵。自己想想不到。
		return res;
	}
	private int lcs_length_upbottom_memorized_sentinel_helper(int[][] m, char[] a, char[] b, int i, int j){
		if(debug) System.out.println("cal: " + i + ", " + j);
		if(i == 0 || j == 0)
			return 0;
		else if(a[i] == b[j]){
			if(debug) System.out.println(a[i] + ", " + b[j] + ", " + i + ", " + j);
			if( m[i-1][j-1] == Integer.MAX_VALUE)
				m[i-1][j-1] = lcs_length_upbottom_memorized_sentinel_helper(m, a, b, i - 1, j - 1);
			return m[i-1][j-1] + 1;
		}
		else{
			if(m[i-1][j] == Integer.MAX_VALUE)
				m[i-1][j] = lcs_length_upbottom_memorized_sentinel_helper(m, a, b, i - 1, j);
			int lcs_a = m[i-1][j];
			if(m[i][j-1] == Integer.MAX_VALUE)
				m[i][j-1] = lcs_length_upbottom_memorized_sentinel_helper(m, a, b, i, j - 1);
			int lcs_b = m[i][j-1];
			return Math.max(lcs_a, lcs_b);
		}
	}
	
	/**
	 * 习题15.4-3，自顶向下带备忘录版本。没有哨兵。
	 * ！！注意点1： 没有哨兵时，由于递归退出条件是-1，需要处理小心数组向下越界的情况。比有哨兵的处理复杂。
	 * @return
	 */
	public int lcs_length_upbottom_memorized(char[] a, char[] b){
		int[][] memorized = new int[a.length][b.length]; 
		for (int i = 0; i < memorized.length; i++) 
			for (int j = 0; j < memorized[i].length; j++) 
				memorized[i][j] = Integer.MAX_VALUE;
		int res = lcs_length_upbottom_memorized_helper(memorized, a, b, a.length - 1, b.length - 1);
		print_bottomup_solution_withOutB(a, b, memorized);	//！！注意点3: 习题15.4-2的打印solution，不太适用于自顶向下不带哨兵。能看到第一个相等的字符串，如果恰好是第一位时会打不出来(因为while(i >= 1 && j >= 1))。
		return res;
	}
	private int lcs_length_upbottom_memorized_helper(int[][] m, char[] a, char[] b, int i, int j){
		if(debug) System.out.println("cal: " + i + ", " + j);
		if(i == -1 || j == -1)
			return 0;
		else if(a[i] == b[j]){
			if(debug) System.out.println(a[i] + ", " + b[j] + ", " + i + ", " + j);
			if( i > 0 && j > 0 && m[i-1][j-1] == Integer.MAX_VALUE)
				m[i-1][j-1] = lcs_length_upbottom_memorized_helper(m, a, b, i - 1, j - 1);
			return i > 0 && j > 0 ? m[i-1][j-1] + 1: 1;
		}
		else{
			if( i > 0 && m[i-1][j] == Integer.MAX_VALUE)
				m[i-1][j] = lcs_length_upbottom_memorized_helper(m, a, b, i - 1, j);
			int lcs_a = i == 0 ? Integer.MIN_VALUE: m[i-1][j];
			if( j > 0 && m[i][j-1] == Integer.MAX_VALUE)
				m[i][j-1] = lcs_length_upbottom_memorized_helper(m, a, b, i, j - 1);
			int lcs_b = j == 0? Integer.MIN_VALUE: m[i][j-1];
			return Math.max(0, Math.max(lcs_a, lcs_b));
		}
	}
	
	public static void main(String[] args) {
		LCS l = new LCS();
		String a = "ABCBDAE";
		String b = "B";	
		String c = "KL";
		String com = "BDCABA";
		
		System.out.println("---bottom up--- ");
		System.out.println(l.lcs_length_bottomup_sentinel(a, com));	
		System.out.println(l.lcs_length_bottomup_sentinel(b, com));	//1
		System.out.println(l.lcs_length_bottomup_sentinel(c, com));	//0
		
		System.out.println("\r\n---upbottom memorized---");
		System.out.println(l.lcs_length_upbottom_memorized(a.toCharArray(), com.toCharArray()));	
		System.out.println(l.lcs_length_upbottom_memorized(b.toCharArray(), com.toCharArray()));	//1
		System.out.println(l.lcs_length_upbottom_memorized(c.toCharArray(), com.toCharArray()));	//0
		
		System.out.println("\r\n---upbottom memorized with sentinel---");
		System.out.println(l.lcs_length_upbottom_memorized_sentinel(a, com));	
		System.out.println(l.lcs_length_upbottom_memorized_sentinel(b, com));	//1
		System.out.println(l.lcs_length_upbottom_memorized_sentinel(c, com));	//0

	}

}
