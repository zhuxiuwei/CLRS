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
		res.add(new int[]{13,-3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7, 100});
		res.add(new int[]{5, -3, 2, -3, 1, 2, -1, 100, -1, 2});
		res.add(new int[]{5, -3, 2, -3, 1, 2, -1, 100});
		res.add(new int[]{5, -3, 2, -3, 1, 2, -10, 100});
		res.add(new int[]{5, -3, 2, -3, 1, 2, -10, 100, -1});
		return res;
	}
}