package chap10_ElementaryDataStructures;
/**
 * 说明如何用一个数组A[1..n]来实现两个栈，使得两个栈中的元素总和不到n时，两个都不会发生上溯。注意PUSH和POP操作的时间应为O(1)
 * 思路是一个stack用奇数index,另一个用偶数index,到顶后往回退用另一个数组的。
 * @author xiuzhu
 * 151210
 */
public class TwoStacksInOneArray {
	private int a[]; //Array for two stacks.
	private int head1 = -1;	//head for stack1, will use even index first
	private int head2 = 0;	//head for stack1, will use odd index first
	private boolean isFull = false;
	//if full mode is true, the stack begin to consume array position of the other array.
	private boolean stack1FullMode = false;
	private boolean stack2FullMode = false;
	
//	//If both stacks are full. (Total elements count reach n.)
//	public boolean isFull(){
//		if(head1 == head2)
//			isFull = true;
//		else
//			isFull 
//		return isFull;
//	}
	
	public void pushStack1(int ele){
		if(head1 == a.length - 1){	//enable stack1 full mode
			
		}else{
			
		}
	}
	
	public void pushStack2(int ele){
		
	}
	
	public int popStack1(int ele){
		return 0;
	}
	
	public int popStack2(int ele){
		return 0;
	}
	
	public TwoStacksInOneArray(int n){	//array size is n.
		a = new int[n];
	}
	
	public static void main(String[] args) {
	}

}
