package chap09_MediansAndOrderStatistics;

import chap07_QuickSort.QuickSort_Randomized;

public class Randomnized_Select {

	private QuickSort_Randomized qs = new QuickSort_Randomized();
	
	/**
	 * 找数组中第i小的元素，数组中的元素都是互异的。时间复杂度O(n)。 i∈[1, a.length]
	 * @param a
	 * @param start
	 * @param end
	 * @param i	第i小
	 * @return 
	 * @throws Exception 
	 */
	public int randomnized_select(int[] a, int start, int end, int i) throws Exception{
		if(i > a.length || i <= 0)
			throw new Exception("Invalid i!");
		
		if(a.length == 1)	//!!!注意点1：必须加上这个判断，否则a只有一个元素时会陷入无限递归
			return a[0];
		
		int mid = qs.partition_Randomized(a, start, end);
		if(mid + 1 == i)
			return a[mid];
		else if(mid + 1 < i)	//search right
			return randomnized_select(a, mid + 1, end, i);
		else	//search left
			return randomnized_select(a, start, mid - 1, i);
	}
	
	/**
	 * 上述算法的非递归循环版本
	 */
	public int randomnized_select_loop(int[] a, int start, int end, int i) throws Exception{
		if(i > a.length || i <= 0)
			throw new Exception("Invalid i!");
		
		if(a.length == 1)	//!!!注意点1：必须加上这个判断，否则a只有一个元素时会陷入死循环
			return a[0];
		
		int mid = qs.partition_Randomized(a, start, end);
		while(mid + 1 != i){
			if(mid + 1 < i)	//search right
				mid = qs.partition_Randomized(a, mid + 1, end);
			else	//search left
				mid = qs.partition_Randomized(a, start, mid - 1);
		}
		return a[mid];
	}
	
	public static void main(String[] args) throws Exception {
		Randomnized_Select rs = new Randomnized_Select();
		int[] a = {2,3,4,87,38,143,87,32,7};
//		System.out.println(rs.randomnized_select(a, 0, a.length - 1, 0));
		System.out.println(rs.randomnized_select(a, 0, a.length - 1, 1));
		System.out.println(rs.randomnized_select(a, 0, a.length - 1, 5));
		System.out.println(rs.randomnized_select(a, 0, a.length - 1, 9));
//		System.out.println(rs.randomnized_select(a, 0, a.length - 1, 10));
		int[] b = {2};
		System.out.println(rs.randomnized_select(b, 0, b.length - 1, 1));
		System.out.println("-------------------");
		int[] c = {2,3,4,87,38,143,87,32,7};
		System.out.println(rs.randomnized_select_loop(c, 0, c.length - 1, 1));
		System.out.println(rs.randomnized_select_loop(c, 0, c.length - 1, 5));
		System.out.println(rs.randomnized_select_loop(c, 0, c.length - 1, 9));
		System.out.println(rs.randomnized_select_loop(b, 0, b.length - 1, 1));
	}

}
