package chap04;


/**
 * 矩阵乘法的递归算法。
 * @author xiuzhu  151012
 * 【TODO:】没做完，矩阵的partition麻烦。
 */

public class MatrixMultipleRecursive extends MatrixMultipleBase{
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
		return matrixMultipleRecursive(a,b);
	}
	
	/**
	 * Actual Recursive Method.
	 */
	private int[][] matrixMultipleRecursive(int[][] a, int[][] b){
		int[][] res = new int[0][0];
		//TODO:做矩阵的partition比较麻烦，先不做了。
		return res;
	}

	public static void main(String[] args) {
		MatrixMultipleRecursive m = new MatrixMultipleRecursive();
		m.runTest();
	}

}
