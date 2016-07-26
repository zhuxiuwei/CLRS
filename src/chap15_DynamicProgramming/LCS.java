package chap15_DynamicProgramming;

/**
 * 最长公共子序列问题。
 * @author xiuzhu
 * 160726
 */
public class LCS {
	
	private boolean debug = false;
	private String s = "";
	
	/**
	 * 自顶向下带备忘录版本。
	 * ！！注意点： 由于递归退出条件是-1，需要处理小心数组向下越界的情况。
	 * @return
	 */
	public int lcs_length_upbottom_memorized(char[] a, char[] b){
		int[][] memorized = new int[a.length][b.length]; 
		for (int i = 0; i < memorized.length; i++) 
			for (int j = 0; j < memorized[i].length; j++) 
				memorized[i][j] = Integer.MAX_VALUE;
		int res = lcs_length_upbottom_memorized_helper(memorized, a, b, a.length - 1, b.length - 1);
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
		String a = "BACE";
		String b = "ABCBDAE";
		System.out.println(l.lcs_length_upbottom_memorized(a.toCharArray(), b.toCharArray()) + " " + l.s);
	}

}
