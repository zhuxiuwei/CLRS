package chap22_ElementaryGraphAlgo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/**
 * 161103
 * @author Zhu Xiuwei
 * 三个注意点：
 * 	DFS遍历有2个注意点，一个是关于timer累加的时间。
 *  打印边的类型，有一个注意点。
 * @param <T>
 */
public class DFS<T> {

	private static int time = 0;
	/**
	 * DFS
	 * @param grahp The graph
	 */
	public static <E> void dfs(Map<Vertex<E>, List<Vertex<E>>> grahp){
		time = 0;
		for (Vertex<E> v: grahp.keySet()) {
			if(v.color == COLOR.WHITE)
				dfs_travel(grahp, v);
		}
	}
	/**
	 * DFS helper
	 * @param grahp The graph
	 * @param u start point
	 */
	public static <E> void dfs_travel(Map<Vertex<E>, List<Vertex<E>>> graph, Vertex<E> u){
		time ++;
		u.d = time;
		u.color = COLOR.GREY;
		for (Vertex<E> neighbor: graph.get(u)) {
			if(neighbor.color == COLOR.WHITE){		//!!!!!!!!!Note1 : don't forget to judge this!
				//!!!!!!!!!!!! Note2: DO NOT need to add time here. Note only add time add start and end of this recursive-method.
				neighbor.π = u;
				dfs_travel(graph, neighbor);
			}
		}
		u.color = COLOR.BLACK;
		time ++;
		u.t = time;
	}
	
	/**
	 * Practice 22.3-2 - DFS on image 22.6
	 */
	public static void dfs_Image22_6(){
		Vertex<Character> q = new Vertex<Character>('q'), r = new Vertex<Character>('r'), s = new Vertex<Character>('s'), t = new Vertex<Character>('t'), u = new Vertex<Character>('u'), v = new Vertex<Character>('v'), w = new Vertex<Character>('w'), x = new Vertex<Character>('x'), y = new Vertex<Character>('y'), z = new Vertex<Character>('z');
		List<Vertex<Character>> ql = new LinkedList<Vertex<Character>>(); ql.add(s); ql.add(t); ql.add(w);
		List<Vertex<Character>> rl = new LinkedList<Vertex<Character>>(); rl.add(u); rl.add(y); 
		List<Vertex<Character>> sl = new LinkedList<Vertex<Character>>(); sl.add(v);
		List<Vertex<Character>> tl = new LinkedList<Vertex<Character>>(); tl.add(x); tl.add(y);
		List<Vertex<Character>> ul = new LinkedList<Vertex<Character>>(); ul.add(y); 
		List<Vertex<Character>> vl = new LinkedList<Vertex<Character>>(); vl.add(w); 
		List<Vertex<Character>> wl = new LinkedList<Vertex<Character>>(); wl.add(s); 
		List<Vertex<Character>> xl = new LinkedList<Vertex<Character>>(); xl.add(z);
		List<Vertex<Character>> yl = new LinkedList<Vertex<Character>>(); yl.add(q);
		List<Vertex<Character>> zl = new LinkedList<Vertex<Character>>(); zl.add(x);
		Map<Vertex<Character>, List<Vertex<Character>>> g = new HashMap<Vertex<Character>, List<Vertex<Character>>>();
		g.put(q, ql); g.put(r, rl); g.put(s, sl); g.put(t, tl); g.put(u, ul); g.put(v, vl); g.put(w, wl); g.put(x, xl); g.put(y, yl); g.put(z, zl);
		dfs(g);
		
		//print d and t.
		for (Vertex<Character> ver: g.keySet()) {
			System.out.println(ver);
		}
		
		//print edge types
		for (Vertex<Character> uu: g.keySet()) {
			for (Vertex<Character> vv: g.get(uu)) {
				System.out.println(String.format("%s -> %s, %s", uu.value, vv.value, getEdgeType(uu, vv)));
			}
		}
	}
	
	/**
	 * Given two edges, judge the edge type.
	 * @return
	 */
	public static <E> char getEdgeType(Vertex<E> u, Vertex<E> v){
		if(v.π == u)	//树边	//!!!!!!Note bug： 不能写成if(v.π == u || u.π == v)，否则以图22.6为例，z -> x会返回T树边，实际应该返回B后向边。
			return 'T';
		if(u.t < v.d || v.t < u.d)	//横向边
			return 'C';
		if(u.d < v.d && u.t > v.t)	//前向边
			return 'F';
		if(u.d > v.d && u.t < v.t)	//后向边
			return 'B';
		return ' ';		//unreachable
	}
	
	//DFS on image 22.4
	public static void dfs_Image22_4(){
		Vertex<Character> u = new Vertex<Character>('u'), v = new Vertex<Character>('v'), w = new Vertex<Character>('w'), x = new Vertex<Character>('x'), y = new Vertex<Character>('y'), z = new Vertex<Character>('z');
		List<Vertex<Character>> ul = new LinkedList<Vertex<Character>>(); ul.add(v); ul.add(x);
		List<Vertex<Character>> vl = new LinkedList<Vertex<Character>>(); vl.add(y); 
		List<Vertex<Character>> wl = new LinkedList<Vertex<Character>>(); wl.add(y); wl.add(z);
		List<Vertex<Character>> xl = new LinkedList<Vertex<Character>>(); xl.add(v);
		List<Vertex<Character>> yl = new LinkedList<Vertex<Character>>(); yl.add(x);
		List<Vertex<Character>> zl = new LinkedList<Vertex<Character>>(); zl.add(z);
		Map<Vertex<Character>, List<Vertex<Character>>> g = new HashMap<Vertex<Character>, List<Vertex<Character>>>();
		g.put(u, ul); g.put(v, vl); g.put(w, wl); g.put(x, xl); g.put(y, yl); g.put(z, zl);
		dfs(g);
		for (Vertex<Character> ver: g.keySet()) {
			System.out.println(ver);
		}
	}
	
	//test
	public static void main(String[] args) {
		//Practice 22.3-2
		dfs_Image22_6();
	}

}
