package chap22_ElementaryGraphAlgo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


/**
 * BFS。adjacent list.
 * 161029
 * !!!!!! Note: BFS每次初始化的时候，d的值要设置为∞，而不是0.
 */
public class BFS {

	/**
	 * practice 22.2-1
	 */
	public static void prac_22_2_1(){
		//Graph of image 22-2(a).
		Vertex<Integer> v1 = new Vertex<Integer>(1), v2 = new Vertex<Integer>(2), v3 = new Vertex<Integer>(3), v4 = new Vertex<Integer>(4), v5 = new Vertex<Integer>(5), v6 = new Vertex<Integer>(6);
		List<Vertex<Integer>> l1 = new LinkedList<Vertex<Integer>>(); l1.add(v2); l1.add(v4);
		List<Vertex<Integer>> l2 = new LinkedList<Vertex<Integer>>(); l2.add(v5);
		List<Vertex<Integer>> l3 = new LinkedList<Vertex<Integer>>(); l3.add(v6); l3.add(v5);
		List<Vertex<Integer>> l4 = new LinkedList<Vertex<Integer>>(); l4.add(v2);
		List<Vertex<Integer>> l5 = new LinkedList<Vertex<Integer>>(); l5.add(v4);
		List<Vertex<Integer>> l6 = new LinkedList<Vertex<Integer>>(); l6.add(v6);
		Map<Vertex<Integer>, List<Vertex<Integer>>> g = new HashMap<Vertex<Integer>, List<Vertex<Integer>>>();
		g.put(v1, l1); g.put(v2, l2); g.put(v3, l3); g.put(v4, l4); g.put(v5, l5); g.put(v6, l6);
		System.out.println("=========== prac_22_2_1: After BFS: ==============");
		bfs(g, v3);
		for(Vertex<Integer> key: g.keySet())
			System.out.println(key.formatString());
	}
	
	/**
	 * practice 22.2-2
	 */
	public static void prac_22_2_2(){
		//Graph of image 22-3(a).
		Vertex<Character> r = new Vertex<Character>('r'), s = new Vertex<Character>('s'), t = new Vertex<Character>('t'), u = new Vertex<Character>('u'), v = new Vertex<Character>('v'), w = new Vertex<Character>('w'), x = new Vertex<Character>('x'), y = new Vertex<Character>('y');
		List<Vertex<Character>> rl = new LinkedList<Vertex<Character>>(); rl.add(s); rl.add(v);
		List<Vertex<Character>> sl = new LinkedList<Vertex<Character>>(); sl.add(r); sl.add(w);
		List<Vertex<Character>> tl = new LinkedList<Vertex<Character>>(); tl.add(u); tl.add(w); tl.add(x);
		List<Vertex<Character>> ul = new LinkedList<Vertex<Character>>(); ul.add(t); ul.add(x); ul.add(y);
		List<Vertex<Character>> vl = new LinkedList<Vertex<Character>>(); vl.add(r); 
		List<Vertex<Character>> wl = new LinkedList<Vertex<Character>>(); wl.add(s); wl.add(t); wl.add(x);
		List<Vertex<Character>> xl = new LinkedList<Vertex<Character>>(); xl.add(t); xl.add(u); xl.add(w); xl.add(y);
		List<Vertex<Character>> yl = new LinkedList<Vertex<Character>>(); yl.add(u); yl.add(x);
		Map<Vertex<Character>, List<Vertex<Character>>> g = new HashMap<Vertex<Character>, List<Vertex<Character>>>();
		g.put(r, rl); g.put(s, sl); g.put(t, tl); g.put(u, ul); g.put(v, vl); g.put(w, wl); g.put(x, xl); g.put(y, yl);
		System.out.println("=========== prac_22_2_2: After BFS: ==============");
		bfs(g, s);
		for(Vertex<Character> key: g.keySet())
			System.out.println(key.formatString());
	}
	
	/**
	 * practice 22.2-5
	 */
	public static void prac_22_2_5(){
		//Graph of image 22-3(a). Note order in adjacent list is different from 22.2-2.
		Vertex<Character> r = new Vertex<Character>('r'), s = new Vertex<Character>('s'), t = new Vertex<Character>('t'), u = new Vertex<Character>('u'), v = new Vertex<Character>('v'), w = new Vertex<Character>('w'), x = new Vertex<Character>('x'), y = new Vertex<Character>('y');
		List<Vertex<Character>> rl = new LinkedList<Vertex<Character>>(); rl.add(v); rl.add(s); 
		List<Vertex<Character>> sl = new LinkedList<Vertex<Character>>(); sl.add(w); sl.add(r); 
		List<Vertex<Character>> tl = new LinkedList<Vertex<Character>>(); tl.add(x); tl.add(w); tl.add(u);
		List<Vertex<Character>> ul = new LinkedList<Vertex<Character>>(); ul.add(y); ul.add(x); ul.add(t); 
		List<Vertex<Character>> vl = new LinkedList<Vertex<Character>>(); vl.add(r); 
		List<Vertex<Character>> wl = new LinkedList<Vertex<Character>>(); wl.add(x); wl.add(t); wl.add(s); 
		List<Vertex<Character>> xl = new LinkedList<Vertex<Character>>(); xl.add(y); xl.add(w); xl.add(u); xl.add(t); 
		List<Vertex<Character>> yl = new LinkedList<Vertex<Character>>(); yl.add(x); yl.add(u); 
		Map<Vertex<Character>, List<Vertex<Character>>> g = new HashMap<Vertex<Character>, List<Vertex<Character>>>();
		g.put(r, rl); g.put(s, sl); g.put(t, tl); g.put(u, ul); g.put(v, vl); g.put(w, wl); g.put(x, xl); g.put(y, yl);
		System.out.println("=========== prac_22_2_5: After BFS: ==============");
		bfs(g, s);
		for(Vertex<Character> key: g.keySet())
			System.out.println(key.formatString());
	}
	
	/**
	 * practice 22.2-8: diameter Of a Tree.
	 * Call BFS for each vertex. O(V*(V + E))
	 */
	public static <E> int diameterOfATree(Map<Vertex<E>, List<Vertex<E>>> grahp){
		int diameter = 0;
		for (Vertex<E> v: grahp.keySet()) {
			bfs(grahp, v);
			for (Vertex<E> v2: grahp.keySet())
				if(v2.d > diameter && v2.d != Integer.MAX_VALUE)
					diameter =v2.d;
		}
		return diameter;
	}
	
	/**
	 * BFS. p344.
	 * @param grahp The graph.
	 * @param s Source vertex.
	 */
	public static <E> void bfs(Map<Vertex<E>, List<Vertex<E>>> grahp, Vertex<E> s){
		//reset all nodes
		for (Vertex<E> v: grahp.keySet()) {
			v.color = COLOR.WHITE;
			v.d = Integer.MAX_VALUE;	//!!!!!! Note: BFS每次初始化的时候，d的值要设置为∞，而不是0.
			v.π = null;
		}
		
		Queue<Vertex<E>> q = new LinkedList<Vertex<E>>();
		s.d = 0;
		s.color = COLOR.GREY;
		q.offer(s);
		while(!q.isEmpty()){
			Vertex<E> v = q.poll();
			List<Vertex<E>> neighbors = grahp.get(v);
			for(Vertex<E> neighbor: neighbors){
				if(neighbor.color == COLOR.WHITE){
					q.offer(neighbor);
					neighbor.π = v;
					neighbor.d = v.d + 1;
					neighbor.color = COLOR.GREY;
				}
			}
			v.color = COLOR.BLACK;
		}
	}
	
	public static void main(String[] args) {
//		prac_22_2_1();
//		prac_22_2_2();
//		prac_22_2_5();
		
		//test 22.2-8
		//Graph of image 22-3(a).
//		Vertex<Character> r = new Vertex<Character>('r'), s = new Vertex<Character>('s'), t = new Vertex<Character>('t'), u = new Vertex<Character>('u'), v = new Vertex<Character>('v'), w = new Vertex<Character>('w'), x = new Vertex<Character>('x'), y = new Vertex<Character>('y');
//		List<Vertex<Character>> rl = new LinkedList<Vertex<Character>>(); rl.add(s); rl.add(v);
//		List<Vertex<Character>> sl = new LinkedList<Vertex<Character>>(); sl.add(r); sl.add(w);
//		List<Vertex<Character>> tl = new LinkedList<Vertex<Character>>(); tl.add(u); tl.add(w); tl.add(x);
//		List<Vertex<Character>> ul = new LinkedList<Vertex<Character>>(); ul.add(t); ul.add(x); ul.add(y);
//		List<Vertex<Character>> vl = new LinkedList<Vertex<Character>>(); vl.add(r); 
//		List<Vertex<Character>> wl = new LinkedList<Vertex<Character>>(); wl.add(s); wl.add(t); wl.add(x);
//		List<Vertex<Character>> xl = new LinkedList<Vertex<Character>>(); xl.add(t); xl.add(u); xl.add(w); xl.add(y);
//		List<Vertex<Character>> yl = new LinkedList<Vertex<Character>>(); yl.add(u); yl.add(x);
//		Map<Vertex<Character>, List<Vertex<Character>>> g = new HashMap<Vertex<Character>, List<Vertex<Character>>>();
//		g.put(r, rl); g.put(s, sl); g.put(t, tl); g.put(u, ul); g.put(v, vl); g.put(w, wl); g.put(x, xl); g.put(y, yl);
//		System.out.println(diameterOfATree(g));
//		//Graph of image 22-2(a).
//		Vertex<Integer> v1 = new Vertex<Integer>(1), v2 = new Vertex<Integer>(2), v3 = new Vertex<Integer>(3), v4 = new Vertex<Integer>(4), v5 = new Vertex<Integer>(5), v6 = new Vertex<Integer>(6);
//		List<Vertex<Integer>> l1 = new LinkedList<Vertex<Integer>>(); l1.add(v2); l1.add(v4);
//		List<Vertex<Integer>> l2 = new LinkedList<Vertex<Integer>>(); l2.add(v5);
//		List<Vertex<Integer>> l3 = new LinkedList<Vertex<Integer>>(); l3.add(v6); l3.add(v5);
//		List<Vertex<Integer>> l4 = new LinkedList<Vertex<Integer>>(); l4.add(v2);
//		List<Vertex<Integer>> l5 = new LinkedList<Vertex<Integer>>(); l5.add(v4);
//		List<Vertex<Integer>> l6 = new LinkedList<Vertex<Integer>>(); l6.add(v6);
//		Map<Vertex<Integer>, List<Vertex<Integer>>> g2 = new HashMap<Vertex<Integer>, List<Vertex<Integer>>>();
//		g2.put(v1, l1); g2.put(v2, l2); g2.put(v3, l3); g2.put(v4, l4); g2.put(v5, l5); g2.put(v6, l6);
//		System.out.println(diameterOfATree(g2));
	}

}
