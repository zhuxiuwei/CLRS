package chap04;

import java.util.Arrays;
import java.util.List;

/**
 * 矩阵乘法的原始算法。
 * @author xiuzhu  151008
 * BUG: 加和公式写错了，导致结果不对，花费1小时时间。写的太慢！
 * 【教训】应该在纸上先列下式子，再写代码。代码上来就写导致出错。
 */

public class MatrixMultipleOriginal extends MatrixMultipleParamChecker{
	/**
	 * @param a	Source matrix a.
	 * @param b	Source matrix b.
	 * @return result matrix of a*b. It's also a n*n matrix
	 */
	public int[][] matrixMultiple(int[][] a, int[][] b){
		if(!checkParam(a, b)){
			System.out.println("Invlid parameter!");
			return null;
		}
		int[][] res = new int[a.length][a.length];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b.length; j++) {
				for (int k = 0; k < a.length; k++) {
					res[i][j] += a[i][k] * b[k][j];	//【---BUG FIX---】加和公式要仔细想想再写。
				}
			}
		}
		return res;
	}

	public static void main(String[] args) {
		MatrixMultipleOriginal m = new MatrixMultipleOriginal();
		List<int[][]> data = TestData.MatrixMultipleTestData();
		for(int i = 0; i < data.size(); i ++){
			System.out.println("a: " + Arrays.deepToString(data.get(i)) + 
					", b: " + Arrays.deepToString(data.get(i + 1)));
			if(i % 2 == 0){
				System.out.println(Arrays.deepToString(m.matrixMultiple(data.get(i), data.get(++i))));
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
		int sizeA = a.length, sizeB = b.length;
		if(sizeA != sizeB)
			return false;
		else{
			for(int[] in: a){
				if(in.length != sizeA)
					return false;
			}
			for(int[] in: a){
				if(in.length != sizeA)
					return false;
			}
		}
		return true;
	}
}