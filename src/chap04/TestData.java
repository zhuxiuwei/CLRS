package chap04;

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
		res.add(new int[][]{});	//0
		res.add(new int[][]{});
		res.add(new int[][]{{1}});	//2
		res.add(new int[][]{{2}});
		res.add(new int[][]{{1,2},{3,4}});	//-36
		res.add(new int[][]{{2,3},{4,5}});
		res.add(new int[][]{{1,-1,1},{2,3,4},{1,2,3}});	//76
		res.add(new int[][]{{2,1,2},{-1,1,2},{2,1,3}});
		return res;
	}
}