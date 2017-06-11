package chap21_DisjointSets;

import java.util.Stack;

/**
 * 21.3-1: Write code for MAKE-SET, FIND-SET, and UNION using a disjoint-set forest with union by rank and path compression heuristic strategies.  
 * @author Zhu Xiuwei
 * 170611
 */
public class DisjointSetForest<T> {

	/**
	 * Define member class.
	 * @author Zhu Xiuwei
	 */
	public static class DisjointSetForestMember<T>{
		int rank;	//秩
		T val;	//值
		DisjointSetForestMember<T> p; //父节点
		public DisjointSetForestMember(T val){
			this.val = val;
		}
		public String toString(){
			return String.format("[val: %s rank: %d, p: %s]", val.toString(), rank, p.val);
		}
	}
	
	public DisjointSetForestMember<T> makeSet(T val){
		DisjointSetForestMember<T> m = new DisjointSetForestMember<T>(val);
		m.p = m;
		return m;
	}
	
	public DisjointSetForestMember<T> findSet(DisjointSetForestMember<T> member){
		if(member.p == member)
			return member;
		member.p = findSet(member.p);
		return member.p;
	}
	
	/*
	 * Practice 21.3-2: Find-set non-recursive version.
	 */
	public DisjointSetForestMember<T> findSet_nonRecursive(DisjointSetForestMember<T> member){
		if(member.p == member)
			return member;

		Stack<DisjointSetForestMember<T>> stack = new Stack<DisjointSetForestMember<T>>();
		while(member != member.p){
			stack.push(member);
		}
		while(!stack.isEmpty()){
			stack.pop().p = member;
		}
		
		return member;
	}
	
	public DisjointSetForestMember<T> unionSet(DisjointSetForestMember<T> x, DisjointSetForestMember<T> y){
		DisjointSetForestMember<T> res = null;
		DisjointSetForestMember<T> xp = findSet_nonRecursive(x);
		DisjointSetForestMember<T> yp = findSet(y);
		if(xp.rank < yp.rank){
			xp.p = yp;
			res = yp;
		}
		else if(xp.rank > yp.rank){
			yp.p = xp;
			res = xp;
		}
		else{
			xp.p = yp;
			yp.rank += 1;
			res = yp;
		}
		return res;
	}
	
	
	public static void main(String[] args) {
		DisjointSetForest<Character> sp = new DisjointSetForest<Character>();
		DisjointSetForestMember<Character> s1 = sp.makeSet('a');
		System.out.println(s1);	//[val: a rank: 0, p: a]
		DisjointSetForestMember<Character> s2 = sp.makeSet('b');
		System.out.println(s2);	//[val: b rank: 0, p: b]
		DisjointSetForestMember<Character> s12 = sp.unionSet(s1, s2);
		System.out.println(s12);	//[val: b rank: 1, p: b]
		System.out.println(s1);	//[val: a rank: 0, p: b]
		System.out.println(sp.findSet(s1));	//[val: b rank: 1, p: b]
		DisjointSetForestMember<Character> s3 = sp.makeSet('c');
		DisjointSetForestMember<Character> s123 = sp.unionSet(s12, s3);
		System.out.println(s123);	//[val: b rank: 1, p: b]
		System.out.println(s1);	//[val: a rank: 0, p: b]
		System.out.println(s3);	//[val: c rank: 0, p: b]
		System.out.println(sp.findSet(s3));	//a
		
		DisjointSetForestMember<Character> s4 = sp.makeSet('d');
		DisjointSetForestMember<Character> s5 = sp.makeSet('e');
		DisjointSetForestMember<Character> s45 = sp.unionSet(s4, s5);
		System.out.println(s45); //[val: e rank: 1, p: e]
		
		//union s45 and s123
		System.out.println("\nunion s45 and s123");
		DisjointSetForestMember<Character> s1_5 = sp.unionSet(s45, s123);
		System.out.println(s1_5); //[val: b rank: 2, p: b]
		System.out.println(s4);	//[val: d rank: 0, p: e]， 注意此时parent还是e。
		
		System.out.println(sp.findSet(s4));	//[val: b rank: 2, p: b]
		System.out.println(s4);	//[val: d rank: 0, p: b]， 注意因为掉用过findSet，parent从e更新到b了。
	}

}
