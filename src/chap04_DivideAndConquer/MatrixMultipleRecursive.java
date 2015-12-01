package chap04_DivideAndConquer;

/**
 * 矩阵乘法的递归算法。注意：为了方便，目前只适用于长宽是2的幂的方阵相乘。（矩阵由数组构成，不是方阵时矩阵分割有困难。）
 * @author xiuzhu  151014
 * 【TODO:】适应长宽不是2的幂的矩阵。（这样的矩阵分割比较麻烦）
 * 【难度】 不是很熟练，在纸上写会有问题。难在建模 --- 怎么给函数定义，怎么定义矩阵的加法（后来发现实际“不用”做）。建好模后，写起来还比较顺畅。
 */

public class MatrixMultipleRecursive extends MatrixMultipleBase{
	/**
	 * @param a	Source matrix a.
	 * @param b	Source matrix b.
	 * @return result matrix of a*b. Its size is: row=a.row, column=b.column 
	 */
	public int[][] matrixMultiple(int[][] a, int[][] b){
		int[][] res = new int[a.length][a.length];
		boolean ok = checkParamIsPowerOf2(a, b);
		if(!ok){ 
			return null;
		}
		if(a.length == 0){
			return res;
		}else
			res = matrixMultipleRecursive(a, b, res,
					0, a.length - 1, 0, a.length - 1, 
					0, b.length - 1, 0, b.length - 1, 
					0, res.length - 1, 0, res.length - 1);
		return res;
	}
	
	/***
	 *  Actual Recursive Method.
	 * @param a	Source matrix a
	 * @param b Source matrix b
	 * @param c Result matrix c
	 * @param rowStarta
	 * @param rowEnda
	 * @param colStarta
	 * @param colEnda
	 * @param rowStartb
	 * @param rowEndb
	 * @param colStartb
	 * @param colEndb
	 * @param rowStartc
	 * @param rowEndc
	 * @param colStartc
	 * @param colEndc
	 * @return Matrix c -> result of a*b
	 */
	private int[][] matrixMultipleRecursive(int[][] a, int[][] b, int[][] c, 
			int rowStarta, int rowEnda, int colStarta, int colEnda,
			int rowStartb, int rowEndb, int colStartb, int colEndb,
			int rowStartc, int rowEndc, int colStartc, int colEndc){
		if(rowStarta == rowEnda)
			c[rowStarta][rowEnda] +=  a[rowStarta][rowEnda] + b[rowStartb][rowEndb] ;	//both only 1 elements
		else if(rowStarta + 1 == rowEnda){	//both have 4 elements
			c[rowStartc][colStartc] += a[rowStarta][colStarta] * b[rowStartb][colStartb] + a[rowStarta][colEnda] * b[rowEndb][colStartb];	//c00
			c[rowStartc][colEndc] += a[rowStarta][colStarta] * b[rowStartb][colEndb] + a[rowStarta][colEnda] * b[rowEndb][colEndb];	//c01
			c[rowEndc][colStartc] += a[rowEnda][colStarta] * b[rowStartb][colStartb] + a[rowEnda][colEnda] * b[rowEndb][colStartb];	//c10
			c[rowEndc][colEndc] += a[rowEnda][colStarta] * b[rowStartb][colEndb] + a[rowEnda][colEnda] * b[rowEndb][colEndb];	//c11
		}
		else{
			//divide matrix c and recursive.
			int rowMida = (rowStarta + rowEnda) / 2;
			int colMida = (colStarta + colEnda) / 2;
			int rowMidb = (rowStartb + rowEndb) / 2;
			int colMidb = (colStartb + colEndb) / 2;
			int rowMidc = (rowStartc + rowEndc) / 2;
			int colMidc = (colStartc + colEndc) / 2;
			matrixMultipleRecursive(a, b, c, 
					rowStarta, rowMida, colStarta, colMida, 
					rowStartb, rowMidb, colStartb, colMidb, 
					rowStartc, rowMidc, colStartc, colMidc);	//c00
			matrixMultipleRecursive(a, b, c, 
					rowStarta, rowMida, colMida + 1, colEnda, 
					rowMidb + 1, rowEndb, colStartb, colMidb, 
					rowStartc, rowMidc, colStartc, colMidc);
			
			matrixMultipleRecursive(a, b, c, 
					rowStarta, rowMida, colStarta, colMida, 
					rowStartb, rowMidb, colMidb + 1, colEndb, 
					rowStartc, rowMidc, colMidc + 1, colEndc);	//c01
			matrixMultipleRecursive(a, b, c, 
					rowStarta, rowMida, colMida + 1, colEnda, 
					rowMidb + 1, rowEndb, colMidb + 1, colEndb, 
					rowStartc, rowMidc, colMidc + 1, colEndc);
			
			matrixMultipleRecursive(a, b, c, 
					rowMida + 1, rowEnda, colStarta, colMida, 
					rowStartb, rowMidb, colStartb, colMidb, 
					rowMidc + 1, rowEndc, colStartc, colMidc);	//c10
			matrixMultipleRecursive(a, b, c, 
					rowMida + 1, rowEnda, colMida + 1, colEnda, 
					rowMidb + 1, rowEndb, colStartb, colMidb, 
					rowMidc + 1, rowEndc, colStartc, colMidc);
			
			matrixMultipleRecursive(a, b, c, 
					rowMida + 1, rowEnda, colStarta, colMida, 
					rowStartb, rowMidb, colMidb + 1, colEndb, 
					rowMidc + 1, rowEndc, colMidc + 1, colEndc);	//c11
			matrixMultipleRecursive(a, b, c, 
					rowMida + 1, rowEnda, colMida + 1, colEnda, 
					rowMidb + 1, rowEndb, colMidb + 1, colEndb, 
					rowMidc + 1, rowEndc, colMidc + 1, colEndc);
		}
		return c;
	}
	
	/**
	 * 判断矩阵AB是否都是长宽都是2的次幂的方阵。并且AB的长宽相等。
	 * @return
	 */
	private boolean checkParamIsPowerOf2(int[][] a, int[][] b){
		boolean res = true;
		//a和b行数相等
		if(a.length != b.length)
			res = false;
		//判断各行的列数
		for(int[] in: a)
			if(in.length != a.length)
				res = false;
		for(int[] in: b)
			if(in.length != b.length)
				res = false;
		//判断行列是否是2的次幂
		int n = a.length;
		while(n > 1){
			if(n % 2 != 0)
				res = false;
			n = n/2;
		}
		if(res)
			System.out.println(" Valid checkParamIsPowerOf2!");
		else
			System.out.println(" Invalid checkParamIsPowerOf2!");
		return res;
	}

	public static void main(String[] args) {
		MatrixMultipleRecursive m = new MatrixMultipleRecursive();
		m.runTest();
	}

}
