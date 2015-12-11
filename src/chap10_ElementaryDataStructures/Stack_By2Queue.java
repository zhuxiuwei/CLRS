package chap10_ElementaryDataStructures;
/**
 * 两个队列实现栈，并分析相关运行时间
 * @author xiuzhu
 * 151211
 */
public class Stack_By2Queue {

	private Queue q1 = new Queue(20);	//和两个栈实现队列里的两个栈各司其职不一样，这里的两个队列功能是一样的，轮换上阵。
	private Queue q2 = new Queue(20);
	private boolean queue1InUse = true;
	
	public void push(int ele){
		if((queue1InUse && q1.isFull) || (!queue1InUse && q2.isFull)){
			System.out.println("-failed to push due to full stack!!!");
			return;
		}
		System.out.println("-Push: " + ele);
		if(queue1InUse)
			q1.enqueue(ele);
		else
			q2.enqueue(ele);
	}
	
	public int pop(){
		if(q1.isEmpty && q2.isEmpty){
			System.out.println("-failed to pop due to queue stack!!!"); 
			return 0;
		}
		int rest = 0;
		if(queue1InUse){
			while(!q1.isEmpty){
				int t = q1.dequeue();
				if(!q1.isEmpty)
					q2.enqueue(t);
				else
					rest = t;
			}
			queue1InUse = false;
		}
		else{
			while(!q2.isEmpty){
				int t = q2.dequeue();
				if(!q2.isEmpty)
					q1.enqueue(t);
				else
					rest = t;
			}
			queue1InUse = true;
		}
		System.out.println("-Pop: " + rest);
		return rest;
	}
	
	public static void main(String[] args) {
		Stack_By2Queue s = new Stack_By2Queue();
		s.push(1);
		s.pop();
		s.pop();
		s.push(2);
		s.push(3);
		s.push(4);
		s.pop();
		s.pop();
		s.push(5);
		s.pop();
		s.pop();
		s.pop();
	}

}
