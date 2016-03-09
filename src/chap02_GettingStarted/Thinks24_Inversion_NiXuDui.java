package chap02_GettingStarted;

/**
 * p24 统计逆序对个数。如（2,3,8,6,1）有如下5个逆序对：2,1  3,1  8,1  6,1  8,6
 * 则输出5.
 * @author xiuzhu
 * 150917
 * 
 * 注意点：
 * 	1. 写的不是很顺畅，第一次写的时候还寻思着不用真正排序数组，只统计就行。后来发现必须数组一次次逐渐有序了，算法才是对的，也就是说本算法完全是在merge sort算法上面做加法，而不是做改动。
 *  2. 递归算法里，初始化和收尾时的处理。 
 */
public class Thinks24_Inversion_NiXuDui {
	
	int count = 0;	//全局变量记载最终count结果
	/**
	 *@return: 逆序对的个数
	 */
	public int calculate(int a[], int begin, int end){
		if(begin == 0 && end == a.length - 1)	//注意点2 - 递归开始	
			count = 0;	//递归开始的时候重置count值。
		
		int mid = (begin + end)/2;
		if(begin < mid)
			 calculate(a, begin, mid);
		if(mid + 1 < end)
			 calculate(a, mid + 1, end);
		
		count(a, begin, mid, end);
		if(begin == 0 && end == a.length - 1){	//注意点2 - 递归结束
			return count;	//递归结束的打印和return最终有意义的count值
		}
		return 0;	//随便return个东西， 不影响最终结果。
	}
	
	/**
	 * 思路：模仿merge sort中的merge过程，当两个数组merge时，如果先进入result数组的是右边的数组中的某个数字，
	 * 那么左边数组中的所有剩下的数字都可以和右边数组的这个数字组成一个逆序对 -- 因为merge数组的时候，左右数组都是有序数组
	 */
	public void count(int a[], int begin, int mid, int end){
		if(a.length == 0)
			return;
		
		//初始化左右两个子数组，并且让数组末尾是“哨兵”
		int[] left = new int[mid - begin + 2];
		int[] right = new int[end - mid + 1];
		for(int i = 0; i < left.length - 1; i ++){
			left[i] = a[begin + i];
		}
		left[left.length - 1]  = Integer.MAX_VALUE;	
		for(int i = 0; i < right.length - 1; i ++){
			right[i] = a[mid + i + 1];
		}
		right[right.length - 1]  = Integer.MAX_VALUE;
		
		int l = 0,r = 0;
		for(int i = begin; i <= end; i++){
			if(right[r] < left[l]){
				a[i] = right[r++];	//注意点1 - 这个算法也真的是要排序的。
				count += left.length - l - 1;	//比merge sort多了这一步。
			}else{
				a[i] = left[l++];
			}
		}
	}
	
	public static void main(String[] args) {
		Thinks24_Inversion_NiXuDui t = new Thinks24_Inversion_NiXuDui();
		 
		int e[] = {8,7,2,5,9,8,5};	//10
		System.out.println(t.calculate(e, 0, e.length - 1));
		
		int a[] = {8,7,2,5,9,8};	//6
		System.out.println(t.calculate(a, 0, a.length - 1));
		
		int b[] = {8,7};	//1
		System.out.println(t.calculate(b, 0, b.length - 1));
		
		int c[] = {8};	//0
		System.out.println(t.calculate(c, 0, c.length - 1));
		
		int d[] = {};	//0
		System.out.println(t.calculate(d, 0, d.length - 1));

		int f[] = {8,7,2,10, 100, 99, 80, 98};	//8
		System.out.println(t.calculate(f, 0, f.length - 1));
		
	}
}
