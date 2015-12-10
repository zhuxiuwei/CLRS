package chap10_ElementaryDataStructures;

import java.util.Arrays;

/**
 * 说明如何用一个数组A[1..n]来实现两个栈，使得两个栈中的元素总和不到n时，两个都不会发生上溯。注意PUSH和POP操作的时间应为O(1)
 * 思路是一个stack用奇数index,另一个用偶数index,到顶后往回退用另一个数组的。
 * @author xiuzhu
 * 151210
 */
public class TwoStacksInOneArray {
	private int a[]; //Array for two stacks.
	private int head1 = -2;	//head for stack1, will use even index first 偶数
	private int head2 = -1;	//head for stack1, will use odd index first 奇数
	//if full mode is true, the stack begin to consume array position of the other array.
	private boolean stack1FullMode = false;
	private boolean stack2FullMode = false;
	
	public void pushStack1(int ele){
		if(stack1FullMode){
			if(head1 - 2 == head2){
				System.out.println("pushStack1 with " + ele + " failed, stack full. ");
				return;
			}else{
				head1 -= 2;
				a[head1] = ele;
			}
		}else{
			int temp = head1;
			if(head1 + 2 > a.length - 1){	//enable stack1 full mode
				stack1FullMode = true;
				if(head1 < a.length - 1)
					head1 = a.length - 1;
				else
					head1 -= 1;
				if(head1 == head2){
					System.out.println("pushStack1 with " + ele + " failed, stack full. ");
					head1 = temp;	//rollback
					return;
				}else
					a[head1] = ele;
			}else{
				if(head1 + 2 == head2){
					System.out.println("pushStack1 with " + ele + " failed, stack full. ");
					return;
				}else{
					head1 += 2;
					a[head1] = ele;
				}
			}
		}
		System.out.println("push stack 1 with " + ele + ", stack: " + Arrays.toString(a));
	}
	
	public void pushStack2(int ele){
		if(stack2FullMode){
			if(head2 - 2 == head1){
				System.out.println("pushStack2 with " + ele + " failed, stack full. ");
				return;
			}else{
				head2 -= 2;
				a[head2] = ele;
			}
		}else{
			int temp = head2;
			if(head2 + 2 > a.length - 1){	//enable stack2 full mode
				stack2FullMode = true;
				if(head2 < a.length - 1)
					head2 = a.length - 1;
				else
					head2 -= 1;
				if(head1 == head2){
					System.out.println("pushStack2 with " + ele + " failed, stack full. ");
					head2 = temp;	//rollback
					return;
				}else
					a[head2] = ele;
			}else{
				if(head2 + 2 == head1){
					System.out.println("pushStack2 with " + ele + " failed, stack full. ");
					return;
				}else{
					head2 += 2;
					a[head2] = ele;
				}
			}
		}
		System.out.println("push stack 2 with " + ele + ", stack: " + Arrays.toString(a));
	}
	
	public int popStack1(){
		int rest = 0;
		int temp = head1;
		if(head1 - 2  < -2){
			System.out.println("popStack1 failed, stack1 empty.");
			return rest;
		}else{
			rest = a[head1];
			if(stack1FullMode){
				if(head1 == a.length - 1){	//head1 just enters stack1FullMode
					head1 -= 1;
					stack1FullMode = false;
				}else if(head1 == a.length - 2){	//head1 just enters stack1FullMode
					head1 += 1;
					stack1FullMode = false;
				}else
					head1 += 2;
			}else{
				head1 -= 2;
			}
		}
		if(temp >= 0)
			a[temp] = 0;	//No need to reset, just to facility debug.
		System.out.println("pop stack 1 with " + rest + ", stack: " + Arrays.toString(a));
		return rest;
	}
	
	public int popStack2(){
		int rest = 0;
		int temp = head2;
		if(head2 - 2  < -2){
			System.out.println("popStack2 failed, stack2 empty.");
			return rest;
		}else{
			rest = a[head2];
			if(stack2FullMode){
				if(head2 == a.length - 1){	//head1 just enters stack1FullMode
					head2 -= 1;
					stack2FullMode = false;
				}else if(head2 == a.length - 2){	//head1 just enters stack1FullMode
					head2 += 1;
					stack2FullMode = false;
				}else
					head2 += 2;
			}else{
				head2 -= 2;
			}
		}
		if(temp >= 0)
			a[temp] = 0;	//No need to reset, just to facility debug.
		System.out.println("pop stack 2 with " + rest + ", stack: " + Arrays.toString(a));
		return rest;
	}
	
	public TwoStacksInOneArray(int n){	//array size is n.
		a = new int[n];
	}
	
	public static void main(String[] args) {
		TwoStacksInOneArray t = new TwoStacksInOneArray(6);
		t.pushStack1(2);
		t.pushStack1(4);
		t.pushStack1(6);
		t.pushStack1(8);
		t.pushStack1(10);
		t.pushStack2(1);
		t.pushStack2(3);
		t.pushStack1(12);
		t.popStack1();
		t.popStack2();
		t.popStack1();
		t.popStack2();
		t.popStack1();
		t.popStack1();
		t.popStack1();
		t.popStack1();
		System.out.println("----------------");
		t = new TwoStacksInOneArray(6);
		t.pushStack2(1);
		t.pushStack2(3);
		t.pushStack2(5);
		t.pushStack2(7);
		t.pushStack1(2);
		t.pushStack1(4);
		t.pushStack1(6);
		t.popStack1();
		t.popStack2();
		t.popStack1();
		t.popStack2();
		t.popStack2();
		t.popStack2();
		t.popStack1();
		t.popStack2();
	}

}
