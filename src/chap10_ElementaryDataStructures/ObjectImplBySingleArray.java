package chap10_ElementaryDataStructures;

import java.util.Arrays;

/**
 * 用单数组表示法实现双向链表。
 * @author xiuzhu
 * 151217
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
		
		//初始化free
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
		if(find(key) == -1){
			System.out.println("Free object failed as can not find object: " + key);
			return;
		}
		
		
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
	
	public void printList(){
		int cur = headIndex;
		System.out.print("head -> ");
		while(cur != -1){
			System.out.print(a[cur] + " -> ");
			cur = a[cur + 1];
		}
		System.out.print("null, freeIndex: " + freeIndex + ", headIndex: " + headIndex + ", array: " + Arrays.toString(a) + "\r\n");
	}
	
	public static void main(String[] args) {
		ObjectImplBySingleArray o = new ObjectImplBySingleArray(3);
		o.allocate_object(1);
		o.allocate_object(2);
		o.allocate_object(2);
		o.printList();
		o.free_object(3);
	}

}
