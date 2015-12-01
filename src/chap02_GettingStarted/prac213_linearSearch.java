package chap02_GettingStarted;

public class prac213_linearSearch {

	/**
	 * p12 线性查找问题
	 * @param src  源数组
	 * @param target  查找这个数字
	 * @return  下标result使得 target=src[result]，找不到返回Null
	 */
	public static Integer search(int[] src, int target){
		Integer result = null;
		for(int i = 0; i < src.length; i++){
			if(src[i] == target)
				result = i;
		}
		return result;
	}
	
	public static void main(String[] args) {
		int a[] = {1,3,4,3};
		int b[] = {};
		int t1 = 3;
		int t2 = 100;
		System.out.println(prac213_linearSearch.search(a, t1));
		System.out.println(prac213_linearSearch.search(a, t2));
		System.out.println(prac213_linearSearch.search(b, t1));
		System.out.println(prac213_linearSearch.search(b, t2));
	}

}
