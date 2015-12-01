package chap08_SortInLinerTime;


/**
 * 习题8.2-4
 * @author xiuzhu
 * 151201
 * Describe an algorithm that, given n integers in the range 0 to k, 
 * preprocesses its input and then answers any query about how many of the n integers fall into a range [a..b] in O(1) time. 
 * Your algorithm should use Θ(n+k) preprocessing time.  
 * 
 * 思路：两个c,一个c[i]存的是i小于等于c[i],一个c2[i]存的是i大于等于c2[i]。
 * 注意点1：写第二步的时候爱出错。
 * 注意点2：如何处理，输入的min max在数组界外。
 * 注意点3：返回的结果的写法。
 */
public class CountNumberInRange {

	private int k = -1;
	/**
	 * @param a	The given array
	 * @param min lower bound of range
	 * @param max upper bound of range
	 * @return
	 */
	public int count(int a[], int min, int max){
		if(min > max){	//illegal input
			return -1;
		}
		
		//get k
		for (int i = 0; i < a.length; i++) 
			if(a[i] > k)
				k = a[i];
		//缩小max,min的范围，以免数组越界。  ！！！！！！！！！！！！！注意点2！！！！！！！！！！！！！
		if(min > k) return 0;
		if(max < 0) return 0;
		if(max>k) max = k;
		if(min < 0) min = 0;
		if(min > max)	min = max;
		
		int[] c = new int[k + 1];	//c[i]存的是i小于等于c[i]
		int[] c2 = new int[k + 1];	//c2[i]存的是i大于等于c2[i]
		
		//第一步：让c[i]为=i的元素的个数
		for (int i = 0; i < a.length; i++) {
			c[a[i]] += 1;
			c2[a[i]] += 1;
		}
		
		//第二步：让c1[i]为<=i的元素的个数. 让c2[i]为>=i的元素的个数。        ！！！！！！！注意点1！！！！！！！
		for (int i = 1; i < c.length; i++) 
			c[i] += c[i-1];
		for (int i = c.length - 1; i >= 1; i--) 
			c2[i-1] += c2[i];                          
		
		//第三步：返回结果
		return c[max] > c2[min]? c2[min]: c[max];	//！！！！！！！注意点3！！！！！！！
	}
	
	public static void main(String[] args) {
		CountNumberInRange c = new CountNumberInRange();
		int a[] = {3,6,9,12};
		System.out.println(c.count(a, -5, 1));	//0
		System.out.println(c.count(a, 0, 8));	//2
		System.out.println(c.count(a, 5, 10));	//3
		System.out.println(c.count(a, 7, 12));	//2
		System.out.println(c.count(a, 5, 18));	//3
		System.out.println(c.count(a, 12, 12));	//1
		System.out.println(c.count(a, 14, 18));	//0
	}

}
