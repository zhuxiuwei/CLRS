package chap15_DynamicProgramming;
/**
 * 15.1-5. O(n)time dynamic-programming algorithm to compute the nth Fibonacci number.
 * @author xiuzhu
 *
 */
public class FibonacciDynamicProgramming {

	/**
	 * BottomUp(dynamic-programming) O(n) algorithm.
	 * @param n
	 * @return Fibonacci(n)
	 */
	public int getFibonacciBottomUp(int n){
		int r[] = new int[n + 1];
		if(n > 0) r[1] = 1;
		for (int i = 2; i <= n ; i++) {
			r[i] = r[i - 1] + r[i - 2];
		}
		return r[n];
	}
	
	public static void main(String[] args) {
		FibonacciDynamicProgramming f = new FibonacciDynamicProgramming();
		System.out.println(f.getFibonacciBottomUp(10));
	}

}
