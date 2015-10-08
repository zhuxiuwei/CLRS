package chap04;
/**
 * 矩阵乘法的Stranssen算法。
 * @author xiuzhu  151008
 *
 */

public class MatrixMultipleStranssen extends MatrixMultipleParamChecker{
	/**
	 * @param a	Source matrix a.
	 * @param b	Source matrix b.
	 * @return result matrix of a*b.
	 */
	public Integer matrixMultiple(int[][] a, int[][] b){
		if(!checkParam(a, b)){
			System.out.println("Invlid parameter!");
			return null;
		}
		int sum = 0;
		return sum;
	}

	public static void main(String[] args) {
	}

}
