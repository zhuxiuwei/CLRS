package chap16_GreedyAlgorithms;

/**
 * Activity selector problem
 * @author xiuzhu
 * 160810
 * ！！注意1： DP memorized 写法，开始花了很多时间也写不对，子问题划分的有些问题。最后参考了网上文章，改了划分方法成功了。
 */
public class ActivitySelector {

	/**
	 * Greedy ActivitySelector.p241
	 * @param s 活动开始时间
	 * @param f 活动结束时间
	 */
	public static void selector_greedy(int[] s, int f[]){
		StringBuilder solution = new StringBuilder();
		if(s.length == 0)
			return;
		int lastEnd = f[0];
		for (int i = 1; i < s.length; i++) {
			if(s[i] >= lastEnd){	//next activity found
				lastEnd = f[i];
				if(solution.length() != 0) solution.append(",");
				solution.append(i);
			}
		}
		System.out.println("Max compatibile solution GREEDY: " + solution.toString());
	}
	
	/**
	 * dynamic programming ActivitySelector - UpBottom memorized. 16.1-1
	 * @param s 活动开始时间
	 * @param f 活动结束时间
	 */
	public static int selector_DP_memorized(int[] s, int f[]){
		int c[] = new int[s.length];
		for (int i = 0; i < c.length; i++) {
			c[i]= Integer.MIN_VALUE;
		}
		int res = selector_DP_memorized_helper(s, f, c.length - 1, c);
		//System.out.println(Arrays.toString(c));
		printSolution(c);
		return res;
	}
	private static int selector_DP_memorized_helper(int[] s, int[] f, int end, int[] c){
		if(end == 0)
			c[end] = 0;
		else
		{
			int left = 0;
			if(c[end] == Integer.MIN_VALUE){
				//find the biggest compatible index j, where j < end, assign to left.
				for (int i = end - 1; i >= 0; i--) {
					if(f[i] <= s[end]){
						left = i;
						break;
					}
				}
				int temp1 = selector_DP_memorized_helper(s, f, left, c) + 1;	//in case index end is in optimal set.
				int temp2 = selector_DP_memorized_helper(s, f, end - 1, c);		//in case index end is NOT in optimal set.
				c[end] = Math.max(temp1, temp2);
				//System.out.println("c " + end + "=" + c[end] + " " + temp1 + " " + temp2);
			}
		}
		return c[end];
	}
	private static void printSolution(int c[]){
		StringBuilder solution = new StringBuilder();
		for (int i = 1; i < c.length; i++) {
			if(c[i] > c[i-1]){
				if(solution.length() != 0) solution.append(",");
				solution.append(i);
			}
		}
		System.out.println("Max compatibile solution DP memorized: " + solution.toString());
	}
	
	/**
	 * Below function does not work correctly, just for record purpose.
	 */
	private static int selector_DP_memorized_helper_NoWork(int[] s, int f[], int start, int end, int[][] c){
		if(c[start][end] != Integer.MIN_VALUE)
			return c[start][end];
		else{
			System.out.println("calculate: " + start + ", " + end);
			if(start >= end){
				c[start][end] = 0;
				return c[start][end];
			}
			if(start + 1 == end){
				if(s[end] >= f[start])
					c[start][end] = c[start][end] = 2;
				else
					c[start][end] = 0;
				return c[start][end];
			}
			for (int k = start + 1; k <= end ; k++) {
				
				//calculate left
				int left = k, right = k;
				for (int i = k - 1; i >= start; i--) {
					if(f[i] <= s[k]){
						left = i;
						break;
					}
				}
				int l = selector_DP_memorized_helper_NoWork(s, f, start, left, c);
				
				//calculate right
				for (int i = k + 1; i <= end; i++) {
					if(f[k] <= s[i]){
						right = i;
						break;
					}
				}
				int r = selector_DP_memorized_helper_NoWork(s, f, right, end, c);
				int temp = l + r + 1;
				if(temp > c[start][end])
					c[start][end] = temp;
			}
			return c[start][end];
		}
	}
	
	public static void main(String[] args) {
		int s[] = {0,1,3,0,5,3,5,6,8,8,2,12,1};	//开始的0是虚拟活动，类似哨兵的作用。
		int f[] = {0,4,5,6,7,9,9,10,11,12,14,16,19};
		ActivitySelector.selector_greedy(s, f);
		ActivitySelector.selector_DP_memorized(s, f);
	}

}
