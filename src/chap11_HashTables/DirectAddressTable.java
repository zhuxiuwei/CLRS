package chap11_HashTables;

import java.util.HashSet;
import java.util.Set;

/**
 * 11.1-3 直接寻址表的实现。
 * @author xiuzhu
 * 151221
 * 注意点：
 * 	1. Java不支持泛型数组。注意是如何实现一个泛型数组的。参考http://stackoverflow.com/questions/529085/how-to-create-a-generic-array-in-java
 *  2. 用元素的hashcode做key，来做该元素的插入和删除。
 *  3. 有意思的一点。删除时，我模仿HashSet的行为。Set判断两个元素是否相同，判断的两个元素是否equals，而不是判断hashcode！
 *  	因为是用hashcode做key，如果元素1和元素2有一样的hashcode,如果直接寻址表中存储的是m1，但是执行delete(m2)，那么：
 *  		如果元素1.equals(元素2)，那么应该m1也被删除。（从集合的视角看，这俩元素相同）
 *  		如果元素1 not equals 元素2，那么m1不应该被删除。（从集合的视角看，这俩元素不同）
 *  	
 */
public class DirectAddressTable<E> {

	private E[] a;	//数组，直接寻址表T[0...m-1]

	/**
	 * @param m 能表示的关键字的范围。
	 */
	public DirectAddressTable(int m){
		a =  (E[])new Object[m];	//！！！注意点1:泛型数组的写法。如果直接写 a = new E[m] 会报错：'Cannot create a generic array of E'
	}
	
	public void insert(E ele){	//！！！注意点2：用hashcode做key，来做插入和删除。
		if(ele.hashCode() < 0 || ele.hashCode() > a.length - 1)
			return;	
		a[ele.hashCode()] = ele; 
	}
	
	public E search(int key){
		if(key < 0 || key > a.length - 1)
			return null;
		return a[key];
	}
	
	public boolean delete(E ele){
		if(ele.hashCode() < 0 || ele.hashCode() > a.length - 1)
			return false;
		else{
			if(ele.equals(a[ele.hashCode()])){
				a[ele.hashCode()] = null;
				return true;
			}
			else
				return false;
		}
	}
	
	public static void main(String[] args) {
		MyData m1 = new MyData(1, "haha");
		MyData m2 = new MyData(1, "hehe");
		
		DirectAddressTable<MyData> d = new DirectAddressTable<MyData>(100);	//max contain element 100.
		System.out.println("---test insert---");
		d.insert(m1);
		d.insert(m2);
		
		System.out.println("---test search---");
		System.out.println(d.search(1));
		System.out.println(d.search(2));
		
		System.out.println("---test delete---");
		System.out.println("delete " + m1 + ": " + d.delete(m1));	//！！！注意点3:如果m1 equals m2，返回true。否则返回false
		System.out.println(d.search(1));
		System.out.println("delete " + m2 + ": " + d.delete(m2));
		System.out.println(d.search(1));
		System.out.println(m1);	//验证删除的是指针，不是m1 m2.(即m1 m2不是null)
		System.out.println(m2);
		
		//用HashSet，对注意点3的验证
		System.out.println("---test HashSet behavior---");
		Set<MyData> set = new HashSet<MyData>();
		set.add(m1);
		System.out.println("remove from set succeed:" + set.remove(m2));
		System.out.println("set contains: " + set.contains(m1));
	}

}

class MyData{
	private int key;
	private String data;
	public MyData(int key, String data){
		this.key = key;
		this.data = data;
	}
	@Override
	public int hashCode(){
		return key;
	}
	
	/**
	 * ！！！注意点3：equals是否override，影响Set对两个元素是否相等的判断。可以把注释加上、去掉，观察结果的不同。
	 */
//	@Override
//	public boolean equals(Object o){
//		if(o == null)
//			return false;
//		else if(!(o instanceof MyData)){
//			return false;
//		}else{
//			MyData other = (MyData)o;
//			return this.key == other.key;
//		}
//	}
	public String toString(){
		return key + "," + data;
	}
}
