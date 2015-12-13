package chap10_ElementaryDataStructures;
/**
 * 习题10.2-2 链表实现栈。push pop仍然是O(1)
 * @author xiuzhu
 * 151213
 */
public class Stack_ByLinkedList {

	private LinkedList<Integer> list = new LinkedList<Integer>();
	
	public void push(int i){
		list.insert(i);
		System.out.println("push " + i);
	}
	
	public int pop(){
		if(list.isEmpty()){
			System.out.println("Pop failed due to satck empty!");
			return 0;
		}else{
			int res = list.head.key;
			list.delete(res);
			System.out.println("pop " + res);
			return res;
		}
	}
	
	public static void main(String[] args) {
		Stack_ByLinkedList s = new Stack_ByLinkedList();
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
