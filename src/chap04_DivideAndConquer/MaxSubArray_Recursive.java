package chap04_DivideAndConquer;

import java.util.Arrays;

/**
 * 4.1-3 最大子数组递归解法。Θ(n*lgn)
 * @author xiuzhu
 * 20151006
 * 一个注意点。
 */
public class MaxSubArray_Recursive {

	public MaxSubArrayBean findMaxSubbArray(int[] src){
		if(src.length <= 2){
			System.out.println("Error: Array length must be >= 2!");
			return null;
		}
		MaxSubArrayBean m = findMaxSubbArray(src, 0, src.length - 1);
		System.out.println(m);
		return m;
	}
	
	//递归程序。主要是分解和合并部分。
	private MaxSubArrayBean findMaxSubbArray(int[] src, int start, int end){
		if(start == end){
			return new MaxSubArrayBean(start, end, src[start]);
		}
		
		int mid = (start + end)/2;
		MaxSubArrayBean leftMax = findMaxSubbArray(src, start, mid);
		MaxSubArrayBean rightMax = findMaxSubbArray(src, mid + 1, end);
		MaxSubArrayBean crossMidMax = findMaxSubbArrayCrossMid(src, start, end);
		
		if(leftMax.sumValue >= rightMax.sumValue && leftMax.sumValue >= crossMidMax.sumValue)
			return leftMax;
		else if (rightMax.sumValue >= leftMax.sumValue && rightMax.sumValue >= crossMidMax.sumValue)
			return rightMax;
		else
			return crossMidMax;
	}
	
	//处理部分，这部分是核心逻辑。这部分的时间复杂度应该是Θ(n)
	//注意点：  (1)rightSum写成Integer.MAX_VALUE，返工一次。(2)left right写反了，导致start end是反的，返工一次。
	private MaxSubArrayBean findMaxSubbArrayCrossMid(int[] src, int start, int end){
		int mid = (start + end)/2;
		int leftSumTemp =0, rightSumTemp = 0, leftSum = Integer.MIN_VALUE, rightSum = Integer.MIN_VALUE, leftIndex = 0, rightIndex = 0; 
		//从mid往前最大
		for(int i = mid; i >= start ; i--){
			leftSumTemp += src[i];
			if(leftSum < leftSumTemp){
				leftSum = leftSumTemp;
				leftIndex = i;
			}
		}
		
		//从mid往后最大
		for(int i = mid; i <= end ; i++){
			rightSumTemp += src[i];
			if(rightSum < rightSumTemp){
				rightSum = rightSumTemp;
				rightIndex = i;
			}
		}
		
		return new MaxSubArrayBean(leftIndex, rightIndex, leftSum + rightSum - src[mid]);
	}
	
	public static void main(String[] args) {
		MaxSubArray_Recursive m = new MaxSubArray_Recursive();
		for(int[] testData: TestData.MaxSubArrayTestData()){
			System.out.println("Test: " + Arrays.toString(testData));
			m.findMaxSubbArray(testData);
		}
	}

}
