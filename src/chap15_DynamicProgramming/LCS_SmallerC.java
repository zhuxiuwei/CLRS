package chap15_DynamicProgramming;
/**
 * 习题15.4-4，如何用一行多一点的空间来计算LCS长度。（不要求打印solution，因为没有足够信息。）
 * @author xiuzhu
 * 160728
 */
public class LCS_SmallerC {

	/**
	 * 使用2行+1的存储空间
	 */
	public int lcs_smallerC_twoRows(String x, String y){
		char a[] = ("|" + x).toCharArray();
		char b[] = ("|" + y).toCharArray();
		if(a.length <= b.length){
			int c[][] = new int[2][b.length];	//c只有两行。  一行记录上一行的结果，一行记录当前行的结果。
			return lcs_smallerC_twoRows_helper(a, b, c);
		}else{
			int c[][] = new int[2][a.length];
			return lcs_smallerC_twoRows_helper(b, a, c);
		}
	}
	private int lcs_smallerC_twoRows_helper(char[] x, char[] y, int[][] c){
		boolean flag = true;	//1个额外的存储空间。 true: c row 1是上一行， row 2是当前行。
		for (int i = 1; i < x.length; i++) {
			for (int j = 1; j < y.length; j++) {
				if(flag){
					if(x[i] == y[j]){
						c[1][j] = c[0][j - 1] + 1;
					}else if(c[1][j - 1] > c[0][j] ){
						c[1][j] = c[1][j - 1];
					}else {
						c[1][j] = c[0][j];
					}
				}else{
					if(x[i] == y[j]){
						c[0][j] = c[1][j - 1] + 1;
					}else if(c[0][j - 1] > c[1][j] ){
						c[0][j] = c[0][j - 1];
					}else {            
						c[0][j] = c[1][j];
					}
				}
			}
			flag = !flag;
		}
		flag = !flag;
		return flag ? c[1][y.length - 1]: c[0][y.length - 1];
	}
	
	
	/**
	 * 使用1行+1的存储空间
	 */
	public int lcs_smallerC_oneRow(String x, String y){
		char a[] = ("|" + x).toCharArray();
		char b[] = ("|" + y).toCharArray();
		if(a.length <= b.length){
			int c[] = new int[b.length];	//c只有1行。
			return lcs_smallerC_oneRow_helper(a, b, c);
		}else{
			int c[] = new int[a.length];
			return lcs_smallerC_oneRow_helper(b, a, c);
		}
	}
	private int lcs_smallerC_oneRow_helper(char[] x, char[] y, int[] c){
		int currentLeftUp = 0;	//1个额外的存储空间。记录当前左上的结果。
		for (int i = 1; i < x.length; i++) {
			for (int j = 1; j < y.length; j++) {
				currentLeftUp = c[j];
				if(x[i] == y[j]){
					c[j] = currentLeftUp + 1;
				}else if(c[j - 1] > c[j] ){
					c[j] = c[j - 1];
				}
			}
		}
		return c[y.length - 1];
	}
	
	
	public static void main(String[] args) {
		LCS_SmallerC l = new LCS_SmallerC();
		String a = "ABCBDAE";
		String b = "B";	
		String c = "KL";
		String com = "BDCABA";
		
		System.out.println(l.lcs_smallerC_twoRows(a, com));	//4
		System.out.println(l.lcs_smallerC_twoRows(b, com));	//1
		System.out.println(l.lcs_smallerC_twoRows(c, com));	//0
		
		System.out.println(l.lcs_smallerC_oneRow(a, com));	//4
		System.out.println(l.lcs_smallerC_oneRow(b, com));	//1
		System.out.println(l.lcs_smallerC_oneRow(c, com));	//0
	}

}
