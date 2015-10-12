package chap04;

/**
 * @author xiuzhu 151012
 *	数组相乘算法的base类。
 */
public class MatrixMultipleBase {
	/**
	 * 看两个矩阵的size是否合法。合法的标准：a的列数=b的行数，即a是i*j, 则b应该是j*k的。
	 * @param a 矩阵a
	 * @param b 矩阵b
	 * @return if valid, return b column. else return -1;
	 */
	public int checkParam(int[][] a, int[][] b){
		int aRow = a.length, bRow = b.length;
		int aColumn = aRow > 0 ? a[0].length: 0;
		int bColumn = bRow > 0 ? b[0].length: 0;
		
		//check a column
		for(int[] in: a){
			if(in.length != aColumn)
				return -1;
		}
		for(int[] in: b){
			if(in.length != bColumn)
				return -1;
		}
		System.out.print("a: " + aRow + "x" + aColumn + ", b: " + bRow + "x" + bColumn);
		if(aColumn == bRow){
			System.out.println(", Valid!");
			return bColumn;
		}
		else{
			System.out.println(", Invalid!");
			return -1;
		}
	}
	
	/**
	 * 打印数组
	 * @param a
	 * @param b
	 */
	public void printMatrix(int[][] a){
		if(a != null)
			for (int i = 0; i < a.length; i++) {
				int[] temp = a[i];
				for (int j = 0; j < temp.length; j++) {
					System.out.print(temp[j] + " ");
				}
				System.out.println("");
			}
	}
}
