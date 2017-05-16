package chap22_ElementaryGraphAlgo;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 161106 CLRS 22.4 - Topological sort - for directed acyclic graph. 
 * @author Zhu Xiuwei
 */
public class Topological_sort<T> {

	private static int time = 0;
	/**
	 * topologicalSort
	 * @param graph The graph
	 * @return graph after topological sort
	 */
	public static <E> List<Vertex<E>> topologicalSort(Map<Vertex<E>, List<Vertex<E>>> graph){
		List<Vertex<E>> res = new LinkedList<Vertex<E>>();
		time = 0;
		Stack<Vertex<E>> stack = new Stack<Vertex<E>>();
		for (Vertex<E> u: graph.keySet()) {
			stack.clear();
			if(u.color == COLOR.WHITE){
				u.color = COLOR.GREY;
				u.d = ++ time;
				stack.push(u);
				while(!stack.isEmpty()){
					Vertex<E> next = getAWhiteNeighbor(graph, stack.peek());
					if(next != null){	//keep on search Neighbor.
						next.π = stack.peek();
						next.d = ++ time;
						next.color = COLOR.GREY;
						stack.push(next);
					}else{	//no more Neighbor, go back.
						next = stack.pop();
						next.color = COLOR.BLACK;
						next.f = ++ time;
						res.add(0, next);
					}
				}
			}
		}
		return res;
	}
	//get a white Neighbor of a give vertex. If no white Neighbor, return null.
	private static <E> Vertex<E> getAWhiteNeighbor (Map<Vertex<E>, List<Vertex<E>>> graph, Vertex<E> u){
		for (Vertex<E> neighbor: graph.get(u)) 
			if(neighbor.color == COLOR.WHITE)
				return neighbor;
		return null;
	}
	
	/**
	 * Practice 22.4-1
	 */
	public static void prac_22_4_1(){
		//create graph 22-8
		Vertex<Character> m = new Vertex<Character>('m'), n = new Vertex<Character>('n'), o = new Vertex<Character>('o'), p = new Vertex<Character>('p'),  q = new Vertex<Character>('q'), r = new Vertex<Character>('r'), s = new Vertex<Character>('s'), t = new Vertex<Character>('t'), u = new Vertex<Character>('u'), v = new Vertex<Character>('v'), w = new Vertex<Character>('w'), x = new Vertex<Character>('x'), y = new Vertex<Character>('y'), z = new Vertex<Character>('z');
		List<Vertex<Character>> ml = new LinkedList<Vertex<Character>>(); ml.add(q); ml.add(r); ml.add(x);
		List<Vertex<Character>> nl = new LinkedList<Vertex<Character>>(); nl.add(o); nl.add(q); nl.add(u); 
		List<Vertex<Character>> ol = new LinkedList<Vertex<Character>>(); ol.add(r); ol.add(s); ol.add(v);
		List<Vertex<Character>> pl = new LinkedList<Vertex<Character>>(); pl.add(o); pl.add(s); pl.add(z);
		List<Vertex<Character>> ql = new LinkedList<Vertex<Character>>(); ql.add(t);
		List<Vertex<Character>> rl = new LinkedList<Vertex<Character>>(); rl.add(u); rl.add(y); 
		List<Vertex<Character>> sl = new LinkedList<Vertex<Character>>(); sl.add(r);
		List<Vertex<Character>> tl = new LinkedList<Vertex<Character>>(); 
		List<Vertex<Character>> ul = new LinkedList<Vertex<Character>>(); ul.add(t); 
		List<Vertex<Character>> vl = new LinkedList<Vertex<Character>>(); vl.add(w); vl.add(x); 
		List<Vertex<Character>> wl = new LinkedList<Vertex<Character>>(); wl.add(z); 
		List<Vertex<Character>> xl = new LinkedList<Vertex<Character>>(); 
		List<Vertex<Character>> yl = new LinkedList<Vertex<Character>>(); yl.add(v);
		List<Vertex<Character>> zl = new LinkedList<Vertex<Character>>(); 
		Map<Vertex<Character>, List<Vertex<Character>>> g = new LinkedHashMap<Vertex<Character>, List<Vertex<Character>>>();
		g.put(m, ml); g.put(n, nl); g.put(o, ol); g.put(p, pl); g.put(q, ql); g.put(r, rl); g.put(s, sl); g.put(t, tl); g.put(u, ul); g.put(v, vl); g.put(w, wl); g.put(x, xl); g.put(y, yl); g.put(z, zl);
		List<Vertex<Character>> graphSorted = topologicalSort(g);
		for (Vertex<Character> ver: graphSorted) {
			System.out.print(String.format("%s (%s, %s) ->", ver.value, ver.d, ver.f));
		}
	}
	
	public static void main(String[] args) {
		prac_22_4_1();
	}

}
