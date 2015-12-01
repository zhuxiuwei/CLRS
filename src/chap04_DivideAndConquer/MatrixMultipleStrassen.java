package chap04_DivideAndConquer;

/**
 * 矩阵乘法的Strassen算法。
 * @author xiuzhu  151016
 * 难度和矩阵乘法递归类似。因为有先前经验，这次还算顺利，虽然也花了不少时间（1小时+），主要是因为式子太多，一不小心就会写错~~更多像是耐心活
 */

public class MatrixMultipleStrassen extends MatrixMultipleBase{
	
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
			res = matrixMultipleStrassen(a, b, 
					0, a.length - 1, 0, a.length - 1, 
					0, b.length - 1, 0, b.length - 1);
		return res;
	}
	
	private int[][] matrixMultipleStrassen(int[][] a, int[][] b, 
			int rowStarta, int rowEnda, int colStarta, int colEnda,
			int rowStartb, int rowEndb, int colStartb, int colEndb){
		int[][] res = new int[rowEnda - rowStarta + 1][rowEnda - rowStarta + 1];
		if(rowStarta == rowEnda){
			res[0][0] = a[rowStarta][rowEnda] * b[rowStartb][rowEndb];
			return  res;	//both only 1 elements
		}
		else{
			//Step 1: divide origin matrix.
			int rowMida = (rowStarta + rowEnda) / 2;
			int colMida = (colStarta + colEnda) / 2;
			int rowMidb = (rowStartb + rowEndb) / 2;
			int colMidb = (colStartb + colEndb) / 2;
			int rowMidres = (0 + res.length - 1) / 2;
			int colMidres = (0 + res.length - 1) / 2; 
			
			//Step2: Get 10 matrixes by add/sub divided matrixes in step 1.
			int count = rowMida - rowStarta + 1;
			int[][] s1 = addOrSubTwoMatrix(b, rowStartb, colMidb + 1, b, rowMidb + 1, colMidb + 1, count, false);	//b12 - b22
			int[][] s2 = addOrSubTwoMatrix(a, rowStarta, colStarta, a, rowStarta, colMida + 1, count, true);	//a11 + a12
			int[][] s3 = addOrSubTwoMatrix(a, rowMida + 1, colStarta, a, rowMida + 1, colMida + 1, count, true);	//a21 + a22
			int[][] s4 = addOrSubTwoMatrix(b, rowMidb + 1, colStartb, b, rowStartb, colStartb, count, false);	//b21 - b11
			int[][] s5 = addOrSubTwoMatrix(a, rowStarta, colStarta, a, rowMida + 1, colMida + 1, count, true);	//a11 + a22
			int[][] s6 = addOrSubTwoMatrix(b, rowStartb, colStartb, b, rowMidb + 1, colMidb + 1, count, true);	//b11 + b22
			int[][] s7 = addOrSubTwoMatrix(a, rowStarta, colMida + 1, a, rowMida + 1, colMida + 1, count, false);	//a12 - a22
			int[][] s8 = addOrSubTwoMatrix(b, rowMidb + 1, colStartb, b, rowMidb + 1, colMidb + 1, count, true);	//b21 + b22
			int[][] s9 = addOrSubTwoMatrix(a, rowStarta, colStarta, a, rowMida + 1, colStarta, count, false);	//a11 - a21
			int[][] s10 = addOrSubTwoMatrix(b, rowStartb, colStartb, b, rowStartb, colMidb + 1, count, true);	//b11 + b12

			//step3: run matrix multiple 7 times recursively
			int length2 = s1.length - 1;
			int[][] p1 = matrixMultipleStrassen(a, s1, rowStarta, rowMida, colStarta, colMida, 0, length2, 0, length2);	//a11 x s1
			int[][] p2 = matrixMultipleStrassen(s2, b, 0, length2, 0, length2, rowMidb + 1, rowEndb, colMidb + 1, colEndb);	//s2 x b22
			int[][] p3 = matrixMultipleStrassen(s3, b, 0, length2, 0, length2, rowStartb, rowMidb, colStartb, colMidb);	//s3 x b11
			int[][] p4 = matrixMultipleStrassen(a, s4, rowMida + 1, rowEnda, colMida + 1, colEnda, 0, length2, 0, length2);	//a22 x s4
			int[][] p5 = matrixMultipleStrassen(s5, s6, 0, length2, 0, length2, 0, length2, 0, length2);	//s5 x s6
			int[][] p6 = matrixMultipleStrassen(s7, s8, 0, length2, 0, length2, 0, length2, 0, length2);	//s7 x s8
			int[][] p7 = matrixMultipleStrassen(s9, s10, 0, length2, 0, length2, 0, length2, 0, length2);	//s9 x s10
			
			//step4: get final result
			int length3 = length2 + 1;
			int[][] temp1 = addOrSubTwoMatrix(p5, 0, 0, p4, 0, 0, length3, true);
			int[][] temp2 = addOrSubTwoMatrix(temp1, 0, 0, p2, 0, 0, length3, false);
			int[][] c11 = addOrSubTwoMatrix(temp2, 0, 0, p6, 0, 0, length3, true);
			int[][] c12 = addOrSubTwoMatrix(p1, 0, 0, p2, 0, 0, length3, true);
			int[][] c21 = addOrSubTwoMatrix(p3, 0, 0, p4, 0, 0, length3, true);
			temp1 = addOrSubTwoMatrix(p5, 0, 0, p1, 0, 0, length3, true);
			temp2 = addOrSubTwoMatrix(temp1, 0, 0, p3, 0, 0, length3, false);
			int[][] c22 = addOrSubTwoMatrix(temp2, 0, 0, p7, 0, 0, length3, false);
			for (int i = 0; i < res.length; i++) {
				for (int j = 0; j < res.length; j++) {
					if(i <= rowMidres && j <= colMidres)	//assign c11
						res[i][j] = c11[i][j];
					else if(i <= rowMidres && j > colMidres)	//assign c12
						res[i][j] = c12[i][j - colMidres - 1];
					else if(i > rowMidres && j <= colMidres)	//assign c21
						res[i][j] = c21[i - colMidres - 1][j];
					else if(i > rowMidres && j > colMidres)	//assign c22
						res[i][j] = c22[i - colMidres - 1][j - colMidres - 1];
				}
			}
		}
		
		return  res;
	}
	
	/**
	 * Add or sub two matrixes.
	 * @param a	Matrix a
	 * @param b	Matrix b
	 * @param isAdd	If true, return a+b. else retuern a-b
	 * @param count Counts of elements in a row/column to add.
	 * @return	result of a+b or a-b.
	 */
	private int[][] addOrSubTwoMatrix(int[][]a, int rowStarta, int colStartA, 
			int b[][], int rowStartb, int colStartb, 
			int count, boolean isAdd){
		int[][] res = new int[count][count];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				if(isAdd)
					res[i][j] = a[i + rowStarta][j + colStartA] + b[i + rowStartb][j + colStartb];
				else
					res[i][j] = a[i + rowStarta][j + colStartA] - b[i + rowStartb][j + colStartb];
			}
		}
		return res;
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
		MatrixMultipleStrassen m = new MatrixMultipleStrassen();
		m.runTest();
	}

}
