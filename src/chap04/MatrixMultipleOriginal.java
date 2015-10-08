package chap04;

import java.util.Arrays;
import java.util.List;

/**
 * 矩阵乘法的原始算法。
 * @author xiuzhu  151008
 *
 */

public class MatrixMultipleOriginal extends MatrixMultipleParamChecker{
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
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b.length; j++) {
				for (int k = 0; k < b.length; k++) {
					sum += a[i][j] * b[j][k];
				}
			}
		}
		return sum;
	}

	public static void main(String[] args) {
		MatrixMultipleOriginal m = new MatrixMultipleOriginal();
		List<int[][]> data = TestData.MatrixMultipleTestData();
		for(int i = 0; i < data.size(); i ++){
			System.out.println("a: " + Arrays.deepToString(data.get(i)) + ", b: " + Arrays.deepToString(data.get(i + 1)));
			if(i % 2 == 0){
				System.out.println(m.matrixMultiple(data.get(i), data.get(++i)));
			}
		}
	}

}

class MatrixMultipleParamChecker{
	/**
	 * 检查矩阵两个输入是否都是n*n的数组。
	 * @param a
	 * @param b
	 * @return
	 */
	public boolean checkParam(int[][] a, int[][] b){
		boolean result = true;
		int sizeA = a.length;
		int sizeB = b.length;
		if(sizeA != sizeB)
			return false;
		else{
			for(int[] in: a){
				int sizeIn = in.length;
				if(sizeIn != sizeA)
					return false;
			}
			for(int[] in: a){
				int sizeIn = in.length;
				if(sizeIn != sizeA)
					return false;
			}
		}
		return result;
	}
}