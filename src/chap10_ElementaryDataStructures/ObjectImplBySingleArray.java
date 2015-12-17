package chap10_ElementaryDataStructures;

import java.util.Arrays;

/**
 * 用单数组表示法实现双向链表。
 * @author xiuzhu
 * 151217
 * 算法思路挺有意思。不算难，稍微有点复杂。
 * 注意点：
 * 	1. free_object时，freeIndex的更新需要注意。
 */
public class ObjectImplBySingleArray {

	private int a[];	//存储链表实现的单数组。
	public int freeIndex = 0;	//free初始值
	public int headIndex = -1;	//链表的head。-1表示为空。
	
	/**
	 * @param maxElememtCount 最多存储的元素个数。
	 */
	public ObjectImplBySingleArray(int maxElememtCount){
		maxElememtCount = maxElememtCount <= 0? 1: maxElememtCount;
		a = new int[3 * maxElememtCount];
		
		//初始化freeIndex
		for (int i = freeIndex; i + 3 <= a.length; i += 3) {
			if(i + 3 == a.length)
				a[i] = -1;
			else
				a[i] = i + 3;
		}
	}
	
	//对象的分配
	public void allocate_object(int key){
		if(isFull()){
			System.out.println("can not allocate object as full memory!");
			return;
		}
		int nextFreeIndex = a[freeIndex];
		int oldHeadIndex = headIndex;
		headIndex = freeIndex;
		freeIndex = nextFreeIndex;
		
		//update new first elemen
		a[headIndex] = key;	//value
		a[headIndex + 1] = oldHeadIndex;	//next. -1 mean null.
		a[headIndex + 2] = -1;	//prev. -1 means null.
		
		//also got to update old first element, which is 2nd element now.
		if(oldHeadIndex != -1)
			a[a[headIndex + 1] + 2] = headIndex;	//update 2nd element prev to point to current head.
	}
	
	//对象的释放
	public void free_object(int key){
		int keyIndex = find(key);
		if(keyIndex == -1){
			System.out.println("Free object failed as can not find object: " + key);
			return;
		}
		if(keyIndex == headIndex)
			headIndex = a[keyIndex + 1];
		if(a[keyIndex + 1] != -1)
			a[a[keyIndex + 1] + 2] = a[keyIndex + 2];	//update prev of next element.
		if(a[keyIndex + 2] != -1)
			a[a[keyIndex + 2] + 1] = a[keyIndex + 1];	//update next of prev element.
		
		//update freeIndex
		//注意点1： free_object时，freeIndex的更新要注意。freeIndex对应的index在array里保存的是下一个可用的index，因此freeIndex始终要保持一种“栈”的感觉，可用的元素通过“栈”连接起来。
		//我之前只写了freeIndex = keyIndex一条，导致array都清空一次后，在添加第一个object还正确，但是再添加第二个就出错了。 因为freeIndex没有把“栈”的元素连接起来。
		int olfFreeIndex = freeIndex;
		freeIndex = keyIndex;
		a[freeIndex] = olfFreeIndex;
	}
	
	/**
	 * 在链表中查找对象
	 * @param key
	 * @return 找到的话返回对象在数组中的index。找不到返回-1.
	 */
	public int find(int key){
		int cur = headIndex;
		while(cur != -1){
			if(a[cur] == key){	//found
				return cur;
			}else{
				cur = a[cur + 1];
			}
		}
		return -1;	//not found
	}
	
	public boolean isFull(){
		return freeIndex == -1;
	}
	public boolean isEmpty(){
		return headIndex == -1;
	}
	
	
	public void printList(){
		int cur = headIndex;
		System.out.print("head -> ");
		while(cur != -1){
			System.out.print(a[cur] + " -> ");
			cur = a[cur + 1];
		}
		System.out.print("null, freeIndex: " + freeIndex + ", headIndex: " + headIndex +  ", array: " + Arrays.toString(a) 
				+ ", isFull:" + isFull() + ", isEmpty:" + isEmpty() + "\r\n");
	}
	
	public static void main(String[] args) {
		ObjectImplBySingleArray o = new ObjectImplBySingleArray(3);
		System.out.println("//test allocate");
		o.allocate_object(10);
		o.allocate_object(11);
		o.allocate_object(12);
		o.printList();
		
		System.out.println("//test free");
		o.free_object(3);
		o.free_object(11);
		o.printList();
		
		System.out.println("//test allocate and free");
		o.allocate_object(13);
		o.printList();
		o.free_object(10);
		o.printList();
		o.free_object(13);
		o.printList();
		o.free_object(12);
		o.printList();
		o.free_object(12);
		
		System.out.println("//test re-add after array is cleaned to double confirm freeIndex is correct.");
		o.allocate_object(14);
		o.printList();
		o.allocate_object(15);
		o.printList();
		o.allocate_object(16);
		o.printList();
		o.free_object(14);
		o.printList();
		o.free_object(16);
		o.printList();
		o.free_object(15);
		o.printList();
		o.free_object(6);o.free_object(15);
		o.allocate_object(16);o.allocate_object(15);o.allocate_object(14);
		o.printList();
	}

}
