package chap10_ElementaryDataStructures;

import java.util.Arrays;
/**
 * Queue代码。要能处理上溢和下溢 
 * 注意点：
 *  1. 队满时的判断。注意不要还有明明一个剩余空间，就告知满了。
 *  2. 出队的时候，也要判断head是否需要轮转回到0。
 * @author xiuzhu
 * 151211
 */
public class Queue {
	private int a[];
	private int head = 0;
	private int tail = 0;
	public boolean isFull = false;
	public boolean isEmpty = true;

	public Queue(int n) {
		this.a = new int[n];
	}

	// 出队
	public int dequeue() {
		isFull = false;
		if(isEmpty){
			System.out.println("failed to dequeue due to queue empty!!!"); // handle queue empty - 下溢
			return 0;
		}
		int rest = a[head];
		a[head] = 0;	//not necessary. just to facility debug.
		if (head == a.length - 1)
			head = 0;
		else
			head += 1;
		if (tail == head) { // queue is empty
			isEmpty = true;
		}
		System.out.println("Dequeue " + rest + ", queue: " + Arrays.toString(a) + ", head: " + head + ", tail: " + tail);
		return rest;
	}

	// 入队
	public void enqueue(int ele) {
		isEmpty = false;
		if (isFull) {
			System.out.println("failed to enqueue " + ele + ", queue full!!!"); // handle queue full - 上溢
			return;
		}
		a[tail] = ele;
		if (tail == a.length - 1)
			tail = 0;
		else
			tail += 1;
		if (tail == head) { // queue will be full
			isFull = true;
		}
		System.out.println("Enquqe " + ele + ", queue: " + Arrays.toString(a) + ", head: " + head + ", tail: " + tail);
	}

	public static void main(String[] args) {
		Queue q = new Queue(4);
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
	}

}
