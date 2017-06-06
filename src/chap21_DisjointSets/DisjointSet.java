package chap21_DisjointSets;
/**
 * 使用链表表示和加权合并启发式策略，写出make-set、find-set和union-set的伪代码。
 * @author Zhu Xiuwei
 */
public class DisjointSet<T> {

	private int size;	//size of the set
	private DisjointSetMember<T> head;	//head (representative) member of the set
	private DisjointSetMember<T> tail;	//tail member of the set
	
	//A Disjoint-Set member
	public static class DisjointSetMember<T>{
		DisjointSet<T> set;	//the set the member belongs to.
		T val;	//value of the member
		DisjointSetMember<T> next;	//next member in the set.
		
		public DisjointSetMember(T val){
			this.val = val;
		}
		
		public String toString(){
			return this.val.toString();
		}
	}
	
	/**
	 * make-set
	 * @param value Value of the new member
	 * @return Create a new member and its associated DsjointSet, return the member.
	 */
	public DisjointSetMember<T> makeSet(T value){
		DisjointSet<T> set = new DisjointSet<T>();
		DisjointSetMember<T> member = new DisjointSetMember<T>(value);
		set.head = member;
		set.tail = member;
		set.size = 1;
		member.set = set;
		return member;
	}
	
	/**
	 * find-set
	 * @param member The member which we want to find its associated DisjointSet.
	 * @return The representative of the DisjointSet the member belongs to.
	 */
	public DisjointSetMember<T> findSet(DisjointSetMember<T> member){
		return member.set.head;
	}
	
	/**
	 * Union-set
	 * @param s1 One DisjointSet.
	 * @param s2 Another DisjointSet
	 * @return union of the two DisjointSet
	 */
	public DisjointSet<T> unionSet(DisjointSet<T> s1, DisjointSet<T> s2){
		
		if(s1 == null || s1.head == null || s1.tail == null)
			return s2;
		if(s2 == null || s2.head == null || s2.tail == null)
			return s1;
		
		DisjointSet<T> small = s1.size < s2.size ? s1: s2;
		DisjointSet<T> big = s1.size < s2.size ? s2: s1;
		//append small into big
		big.tail.next = small.head;
		big.tail = small.tail;
		big.size += small.size;
		
		//update set pointer of the members in small set, to point to big set.
		DisjointSetMember<T> member = small.head;
		member.set = big;
		while(member.next != null){
			member = member.next;
			member.set = big;
		}
		return big;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		DisjointSetMember<T> member = head;
		while(member != null){
			sb.append(member).append(",");
			member = member.next;
		}
		sb.append(" size: " + size);
		return sb.toString();
	}
	
	public static void main(String[] args) {
		DisjointSet<Character> sp = new DisjointSet<Character>();
		DisjointSetMember<Character> s1 = sp.makeSet('a');
		System.out.println(s1);	//a
		DisjointSetMember<Character> s2 = sp.makeSet('b');
		System.out.println(s2);	//b
		DisjointSet<Character> s12 = sp.unionSet(s1.set, s2.set);
		System.out.println(s12);	//size: 2. Members:a,b
		System.out.println(sp.findSet(s2));	//a
		DisjointSetMember<Character> s3 = sp.makeSet('c');
		System.out.println(s3);	//c
		DisjointSet<Character> s123 = sp.unionSet(s12, s3.set);
		System.out.println(s123);	//size: 3. Members:a,b,c
		System.out.println(sp.findSet(s3));	//a
		s123 = sp.unionSet(s123, null);
		System.out.println(s123);	//size: 3. Members:a,b,c
	}

}
