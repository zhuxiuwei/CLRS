package chap04;
/**
 * 最大子数组暴力解法。Θ(n^2)
 * @author xiuzhu
 *
 */
public class MaxSubArray_Violence {

	public MaxSubArrayBean findMaxSubbArray(int[] src){
		MaxSubArrayBean rest = new MaxSubArrayBean();
		
		if(src.length < 2){
			System.out.println("Error: Array length must be >= 2!");
			return null;
		}
		
		int sum = Integer.MIN_VALUE, tempSum = 0;
		for(int i = 0; i < src.length ; i++){
			for(int j = i; j < src.length; j++){
				tempSum += src[j];
				if(sum < tempSum){
					sum = tempSum;
					rest.startIndex = i;
					rest.endIndex = j;
					rest.sumValue = sum;
				}
			}
			tempSum = 0;
		}
		System.out.println(rest);
		return rest;
	}
	
	public static void main(String[] args) {
		int[] a1 = {};
		int[] a2 = {1};
		int[] a3 = {1, -4, 3, -4};
		int[] a4 = {13,-3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
		int[] a5 = {-5, -3, -1, -1, -1};
		int[] a6 = {-1, -2, -3};
		
		MaxSubArray_Violence m = new MaxSubArray_Violence();
		m.findMaxSubbArray(a1);
		m.findMaxSubbArray(a2);
		m.findMaxSubbArray(a3);
		m.findMaxSubbArray(a4);
		m.findMaxSubbArray(a5);
		m.findMaxSubbArray(a6);
	}

}
