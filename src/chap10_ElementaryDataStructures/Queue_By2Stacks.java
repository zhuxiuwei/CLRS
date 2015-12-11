package chap10_ElementaryDataStructures;

import java.util.Stack;
/**
 * 两个栈实现队列，并分析相关运行时间
 * @author xiuzhu
 * 151211
 */
public class Queue_By2Stacks {

	private Stack<Integer> stack1 = new Stack<Integer>();	//用来入队
	private Stack<Integer> stack2 = new Stack<Integer>();	//用来出队
	
	public void enqueue(int ele){
		stack1.push(ele);
	}
	
	public int dequeue(){
		if(stack1.isEmpty() && stack2.isEmpty()){
			System.out.println("failed to dequeue due to queue empty!!!");
			return 0;
		}
		int res = 0;
		if(!stack2.isEmpty()){
			res = stack2.pop();
		}else{
			while(!stack1.isEmpty()){
				stack2.push(stack1.pop());
			}
			res = stack2.pop();
		}
		System.out.println("Dequeue: " + res);
		return res;
	}
	
	public static void main(String[] args) {
		Queue_By2Stacks q = new Queue_By2Stacks();
		q.dequeue();
		q.enqueue(1);
		q.dequeue();
		q.dequeue();
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		q.enqueue(4);
		q.dequeue();
		q.enqueue(5);
		q.dequeue();
		q.dequeue();
		q.enqueue(6);
		q.enqueue(7);
		q.enqueue(8);
		q.dequeue();
		q.dequeue();
		q.dequeue();
		q.dequeue();
		q.dequeue();
		q.dequeue();
	}

}
