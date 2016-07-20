package chap15_DynamicProgramming;

import java.util.Arrays;

/**
 * 矩阵链乘法。160720
 * @author xiuzhu
 */
public class MatrixMultiplyChain {
	
	boolean debug = false;
	
	/**
	 * 计算Ai..j矩阵标量乘法的最小次数 - 自顶向下递归，效率低。
	 * @param p 乘法矩阵中各个矩阵维度的序列。对于矩阵Ai，Ai的大小为pi-1*pi
	 */
	public int matrixChain_UpBottom(int p[]){
		if(p.length <= 1)
			return 0;
		int m[][] = new int[p.length][p.length];
		for (int i = 0; i < m.length; i++) 
			for (int j = 0; j < m.length; j++) 
				m[i][j] = i == j ? 0: Integer.MAX_VALUE;
		int s[][] = new int[p.length][p.length];
		int res = matrixChain_UpBottom_helper(m, s, p, 1, p.length - 1);
		if(debug){
			System.out.println("array m: " + Arrays.deepToString(m));
			System.out.println("array s: " + Arrays.deepToString(s));
		}
		System.out.print("Minimum multiply count： " + res + ", ");
		printSolution(s);
		return res;
	}
	/*
	 * matrixChain_UpBottom的helper class。
	 * @param m 存储Ai....j的最小乘法次数。
	 * @param s 存储Ai....j的最小乘法的分割点。
	 * @param p 乘法矩阵中各个矩阵维度的序列
	 * @param i 从矩阵Ai起
	 * @param j 到矩阵Aj止
	 * @return
	 */
	private int matrixChain_UpBottom_helper(int m[][], int s[][], int[] p, int i, int j){
		if(debug) System.out.println("call: " + i + " " + j);
		if(i == j )
			return 0;
		for (int k = i; k < j; k++) {
			int temp = matrixChain_UpBottom_helper(m, s, p, i, k) 
					+ matrixChain_UpBottom_helper(m, s, p, k + 1, j)
					+p[i -1]*p[k]*p[j];
			if(m[i][j] > temp){
				m[i][j] = temp;
				s[i][j] = k;
			}
		}
		return m[i][j];
	}
	
	/**
	 * 计算Ai..j矩阵标量乘法的最小次数 - 带备忘录的自顶向下。
	 * @param p 乘法矩阵中各个矩阵维度的序列。对于矩阵Ai，Ai的大小为pi-1*pi
	 */
	public int matrixChain_UpBottom_Memorized(int p[]){
		if(p.length <= 1)
			return 0;
		int m[][] = new int[p.length][p.length];
		for (int i = 0; i < m.length; i++) 
			for (int j = 0; j < m.length; j++) 
				m[i][j] = i == j ? 0: Integer.MAX_VALUE;
		int s[][] = new int[p.length][p.length];
		int res = matrixChain_UpBottom_Memorized_helper(m, s, p, 1, p.length - 1);
		if(debug){
			System.out.println("array m: " + Arrays.deepToString(m));
			System.out.println("array s: " + Arrays.deepToString(s));
		}
		System.out.print("Minimum multiply count： " + res + ", ");
		printSolution(s);
		return res;
	}
	/*
	 * matrixChain_UpBottom_Memorized的helper class。
	 * @param m 存储Ai....j的最小乘法次数。
	 * @param s 存储Ai....j的最小乘法的分割点。
	 * @param p 乘法矩阵中各个矩阵维度的序列
	 * @param i 从矩阵Ai起
	 * @param j 到矩阵Aj止
	 * @return
	 */
	private int matrixChain_UpBottom_Memorized_helper(int m[][], int s[][], int[] p, int i, int j){
		if(m[i][j] == Integer.MAX_VALUE){
			if(debug) System.out.println("call: " + i + " " + j);
			if(i == j )
				return 0;
			for (int k = i; k < j; k++) {
				int temp = matrixChain_UpBottom_Memorized_helper(m, s, p, i, k) 
						+ matrixChain_UpBottom_Memorized_helper(m, s, p, k + 1, j)
						+p[i -1]*p[k]*p[j];
				if(m[i][j] > temp){
					m[i][j] = temp;
					s[i][j] = k;
				}
			}
		}else
			if(debug) System.out.println("call cached: " + i + " " + j);
		return m[i][j];
	}
	
	/**
	 * 根据是s[][]，打印分割方案。
	 * @param s 记录Ai....j的最小乘法的分割点。
	 */
	public void printSolution(int s[][]){
		/*
		 * parentheses array：记录各个矩阵需要打印的左右括号的个数
		 * parentheses[i][0]: Ai left side parenthesis count
		 * parentheses[i][1]: Ai right side parenthesis count
		 */
		int[][] parentheses = new int[s.length][2];
		//整个矩阵列最左右的括号，默认一定要打印。所以先加1.
		parentheses[1][0] = 1;
		parentheses[s.length - 1][1] = 1;
		
		//calculate parentheses array. 
		printSolution_helper(s, parentheses, 1, s.length - 1);
		if(debug) System.out.println("parentheses array: " + Arrays.deepToString(parentheses));
		
		//print solution based on parentheses array.
		System.out.print("Solution: ");
		for (int i = 1; i < parentheses.length; i++) {
			for (int j = 0; j < parentheses[i][0]; j++)	//print left parenthesis
				System.out.print("(");
			System.out.print("A" + i);
			for (int j = 0; j < parentheses[i][1]; j++)	//print right parenthesis.
				System.out.print(")");
		}
		System.out.println();
	}
	private void printSolution_helper(int s[][], int[][] p, int i, int j){
		if(i == j || i + 1 == j) return;
		int splitAt = s[i][j];
		if(i != splitAt){
			p[i][0] ++;
			p[splitAt][1] ++;
		}
		if(splitAt + 1 != j){
			p[splitAt + 1][0] ++;
			p[j][1] ++;
		}
		printSolution_helper(s, p, i, splitAt);
		printSolution_helper(s, p, splitAt + 1, j);
	}
	
	
	public static void main(String[] args) {
		MatrixMultiplyChain m = new MatrixMultiplyChain();
		System.out.println("---Up bottom---");
		m.matrixChain_UpBottom(new int[]{30, 35, 15, 5, 10, 20, 25});
		m.matrixChain_UpBottom(new int[]{10, 100, 5, 50});
		System.out.println("\r\n---Up bottom memorized---");
		m.matrixChain_UpBottom_Memorized(new int[]{30, 35, 15, 5, 10, 20, 25});
		m.matrixChain_UpBottom_Memorized(new int[]{10, 100, 5, 50});
	}

}
