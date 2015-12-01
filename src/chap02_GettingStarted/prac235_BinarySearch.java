package chap02_GettingStarted;

/**
 * 二分查找 
 * @author xiuzhu
 * 150917
 * 
 * 注意点：
 * 1 - 退出情况要思考清楚，这个地方花了些时间。
 * 	测试用例10能cover到特殊情况 - 如果不加上条件begin > end, 10会导致递归退不出最后栈溢出。
 * 2 - 但是在非递归版本中，不需要加上begin > end这个判断条件。因为非递归版本有while循环，这个循环自然保证了这个退出条件。
 * 	因此，注意点2那行代码，target=10的时候执行不到。但是其他几个not found的test case就能执行到。
 */
public class prac235_BinarySearch {

	/**
	 * 二分查找 - 非递归
	 * @param a
	 * @param target
	 * @param begin
	 * @param end
	 */
	public boolean bSearch(int a[], int target, int begin, int end){
		boolean found = false;
		int mid = (begin + end)/2;
		int i = a[mid];
		
		while(begin <= end){
			if(begin == end && a[begin] != target){	
				System.out.println("   能执行到这一行的Not Found target： " + target);	//注意点2:
				found = false;
				break;
			}
			if(i == target){	//found
				found = true;
				break;
			}else if(target < i){	//search left
				end = mid - 1;
				mid = (begin + end)/2;
				i = a[mid];
			}else if(target > i){	//search right
				begin = mid + 1;
				mid = (begin + end)/2;
				i = a[mid];
			}
		}
		return found;
	}
	
	/**
	 * 二分查找 - 递归
	 * @param a
	 * @param target
	 * @param begin
	 * @param end
	 * @return
	 */
	public boolean bSearch_recursive(int a[], int target, int begin, int end){
		int mid = (begin + end)/2;
		int i = a[mid];
		
		if(begin > end || (begin == end && a[begin] != target)){	//not found		//注意点1
			return false;
		}
		if(i == target){	//found
			return true;
		}else if(target < i){	//search left
			end = mid - 1;
			return bSearch_recursive(a, target, begin, end);
		}else{//search right
			begin = mid + 1;
			return bSearch_recursive(a, target, begin, end);
		}
	}
	
	public static void main(String[] args) {
		int a[] = {1,3,5,7,9,11,13,15,17,19};
		prac235_BinarySearch p = new prac235_BinarySearch();
		int t1 = 0;
		int t2 = 1;
		int t3 = 9;
		int t4 = 10;	//这个用例好。
		int t5 = 19;
		int t6 = 20;
		
		System.out.println("---- 非递归算法---- ");
		System.out.println(p.bSearch(a, t1, 0, a.length - 1));
		System.out.println(p.bSearch(a, t2, 0, a.length - 1));
		System.out.println(p.bSearch(a, t3, 0, a.length - 1));
		System.out.println(p.bSearch(a, t4, 0, a.length - 1));
		System.out.println(p.bSearch(a, t5, 0, a.length - 1));
		System.out.println(p.bSearch(a, t6, 0, a.length - 1));
		
		System.out.println("\r\n---- 递归算法---- ");
		System.out.println(p.bSearch_recursive(a, t1, 0, a.length - 1));
		System.out.println(p.bSearch_recursive(a, t2, 0, a.length - 1));
		System.out.println(p.bSearch_recursive(a, t3, 0, a.length - 1));
		System.out.println(p.bSearch_recursive(a, t4, 0, a.length - 1));
		System.out.println(p.bSearch_recursive(a, t5, 0, a.length - 1));
		System.out.println(p.bSearch_recursive(a, t6, 0, a.length - 1));
	}

}
