package chap04_DivideAndConquer;


/**
 * 矩阵乘法的原始算法。
 * @author xiuzhu  151008
 * BUG: 加和公式写错了，导致结果不对，花费1小时时间。写的太慢！
 * 【教训】应该在纸上先列下式子，再写代码。代码上来就写导致出错。
 * 【改进 151012】矩阵相乘不是必须都是方阵，满足a的列数=b的行数就行。
 */

public class MatrixMultipleOriginal extends MatrixMultipleBase{
	/**
	 * @param a	Source matrix a.
	 * @param b	Source matrix b.
	 * @return result matrix of a*b. Its size is: row=a.row, column=b.column 
	 */
	public int[][] matrixMultiple(int[][] a, int[][] b){
		int bColumn = checkParam(a, b);
		if(bColumn == -1){
			return null;
		}
		int[][] res = new int[a.length][bColumn];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < bColumn; j++) {	//【---改进151012---】不是方阵后，j，k的终止条件需要相应修改。
				for (int k = 0; k < b.length; k++) {
					res[i][j] += a[i][k] * b[k][j];	//【---BUG FIX---】加和公式要仔细想想再写。
				}
			}
		}
		return res;
	}

	public static void main(String[] args) {
		MatrixMultipleOriginal m = new MatrixMultipleOriginal();
		m.runTest();
	}

}
