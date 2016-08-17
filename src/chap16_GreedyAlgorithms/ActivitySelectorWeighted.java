package chap16_GreedyAlgorithms;

import java.util.LinkedList;
import java.util.List;

/**
 * 16.1-5 weighted activity selector problem. 
 * @author xiuzhu
 * 160817
 * 
 * ！！！！注意点1： printSolution，比ActivitySelector的printSolution要复杂一些。
 */
public class ActivitySelectorWeighted {
	
	/**
	 * dynamic programming ActivitySelector - .UpBottom memorized. 16.1-1
	 * @param s 活动开始时间
	 * @param f 活动结束时间
	 * @param values values
	 */
	public static int selector_DP_memorized(int[] s, int f[], int[] values){
		int c[] = new int[s.length];
		for (int i = 0; i < c.length; i++) {
			c[i]= Integer.MIN_VALUE;
		}
		int res = selector_DP_memorized_helper(s, f, values, c.length - 1, c);
		printSolution(c, values);
		return res;
	}
	private static int selector_DP_memorized_helper(int[] s, int[] f, int[] values, int end, int[] c){
		if(end == 0)
			c[end] = 0;
		else
		{
			int left = 0;
			if(c[end] == Integer.MIN_VALUE){
				//find the biggest compatible index j, where j < end;
				for (int i = end - 1; i >= 0; i--) {
					if(f[i] <= s[end]){
						left = i;
						break;
					}
				}
				int temp1 = selector_DP_memorized_helper(s, f, values, left, c) + values[end];	//in case index end is in optimal set.
				int temp2 = selector_DP_memorized_helper(s, f, values, end - 1, c);				//in case index end is NOT in optimal set.
				c[end] = Math.max(temp1, temp2);
			}
		}
		return c[end];
	}
	//！！！！注意点1： printSolution，比ActivitySelector的printSolution要复杂一些。
	private static void printSolution(int c[], int values[]){
		List<Integer> solution = new LinkedList<Integer>();
		if(c.length >= 0){
			int last = c[c.length - 1];
			for (int i = c.length - 1; i > 0; i--) {
				if(c[i - 1] == last)
					continue;
				else{
					if(c[i] == last)
						solution.add(0, i);	//append to head of solution
					last = last - values[i];
				}
			}
			System.out.print("Max value: " + c[c.length - 1]);
			System.out.println(", solution: " + solution.toString());
		}
	}
	
	public static void main(String[] args) {
		int s[] = {0,1,3,0,5,3,5,6,8,8,2,12,1};	//开始的0是虚拟活动，类似哨兵的作用。
		int f[] = {0,4,5,6,7,9,9,10,11,12,14,16,17};
        
		int[] values = new int[]{0,3,2,4,8,2,5,6,10,7,4,5,2};  
		ActivitySelectorWeighted.selector_DP_memorized(s, f, values);	//Max value: 26, solution: [1, 4, 8, 11]
		
		values = new int[]{0,3,2,4,8,2,5,6,10,7,4,5,100};  
		ActivitySelectorWeighted.selector_DP_memorized(s, f, values);	//Max value: 100, solution: [12]
		
		values = new int[]{0,1,1,1,1,1,1,1,1,1,1,1,1};  //Normal active selector problem
		ActivitySelectorWeighted.selector_DP_memorized(s, f, values);	//Max value: 4, solution: [1, 4, 8, 11]
	}

}
