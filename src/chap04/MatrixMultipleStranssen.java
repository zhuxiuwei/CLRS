package chap04;
/**
 * 矩阵乘法的Stranssen算法。
 * @author xiuzhu  151008
 *
 */

public class MatrixMultipleStranssen extends MatrixMultipleBase{
	/**
	 * @param a	Source matrix a.
	 * @param b	Source matrix b.
	 * @return result matrix of a*b. Its size is: row=a.row, column=b.column 
	 */
	public  int[][] matrixMultiple(int[][] a, int[][] b){
		if(checkParam(a, b) == -1){
			System.out.println("Invlid parameter!");
			return null;
		}
		return null;
	}

	public static void main(String[] args) {
	}

}
