package chap04_DivideAndConquer;

import java.util.ArrayList;
import java.util.List;

//"Data Driven" Test.
//Put test data here to make them reusable and reduce Maintenance effort.
public class TestData {
	
	//Test data for MaxSubArray algorithms
	public static List<int[]> MaxSubArrayTestData(){
		List<int[]> res = new ArrayList<int[]>();
		res.add(new int[]{});
		res.add(new int[]{1});
		res.add(new int[]{1, -4, 3, -4});
		res.add(new int[]{13,-3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7});
		res.add(new int[]{-5, -3, -1, -1, -1});
		res.add(new int[]{-1, -2, -3});
		//below for MaxSubArray_Lineary Bug.
		res.add(new int[]{13,-3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7, 100});		//Bug 1
		res.add(new int[]{5, -3, 2, -3, 1, 2, -1, 100, -1, 2});	//Bug 2
		res.add(new int[]{5, -3, 2, -3, 1, 2, -1, 100});	//Bug 2'
		res.add(new int[]{5, -3, 2, -3, 1, 2, -10, 100});	//Bug 1, 2， 2'
		res.add(new int[]{5, -3, 2, -3, 1, 2, -10, 100, -1});	//Bug 2, 2'
		return res;
	}
	
	//Test data for MaxSubArray algorithms. Every two arrays is a combined input of a MatrixMultiple method.
	public static List<int[][]> MatrixMultipleTestData(){
		List<int[][]> res = new ArrayList<int[][]>();
		res.add(new int[][]{{1}});	//invalid input
		res.add(new int[][]{{1},{2}});
		res.add(new int[][]{{1},{2}}); //[[1][2]]
		res.add(new int[][]{{1}});	
		res.add(new int[][]{});	//[]
		res.add(new int[][]{});
		res.add(new int[][]{{1}});	//[[2]]
		res.add(new int[][]{{2}});
		res.add(new int[][]{{1,2},{3,4}});	//[[10, 13], [22, 29]]
		res.add(new int[][]{{2,3},{4,5}});
		res.add(new int[][]{{1,-1,1},{2,3,4},{1,2,3}});	//[[5, 1, 3], [9, 9, 22], [6, 6, 15]]
		res.add(new int[][]{{2,1,2},{-1,1,2},{2,1,3}});
		//for 矩阵递归算法 - 长宽是2 n次幂的。
		res.add(new int[][]{{1,-1,1,2},{2,3,4,1},{1,2,3,0}, {-2,0,1,-1}});	//11 5 3 -11 | 12 11 22 -7 | 6 6 15 -3 | -5 -3 -1 3 
		res.add(new int[][]{{2,1,2,-1},{-1,1,2,2},{2,1,3,-2}, {3,2,0,-3}});
		res.add(new int[][]{{1,-1,1,2,2,3,4,1},{1,2,3,0,-2,0,1,-1},{5,3,8,5,-3,5,7,-3}
			,{9,3,8,12,-3,-6,-8,3},{8,-4,-8,3,6,-8,3,2},{12,4,7,3,2,-24,7,3},{2,3,81,-3,-51,4,7,9},{-9,3,6,3,-3,7,9,3}});	
		res.add(new int[][]{{2,1,2,-1,-1,1,2,2},{2,1,3,-2,3,2,0,-3},{4,22,-7,9,4,-30,8,4}
			,{2,34,-2,7,91,3,-2,7},{54,-12,3,3,65,2-3,4,8},{3,7,4,2-3,54,4,5,-21},{23,67,17,1,-45,8,3,-22},{12,3,5,7,-2,3,4,60}});
		return res;
	}
}