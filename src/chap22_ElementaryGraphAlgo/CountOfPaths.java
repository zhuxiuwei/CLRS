package chap22_ElementaryGraphAlgo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * 161107 Practice 22.4-2 有向无环图，两个结点之间简单路径的数量。
 * @author xiuzhu
 * !!!! Note: 花了比较多的时间。关键是在扫描邻居的时候，为了不要扫描已经扫描过的，需要加一个map neighborsHasCheckedIndex 来记录。
 */
public class CountOfPaths {
	
	/**
	 * Get the count of paths between two vertexes. 
	 * @param graph The graph
	 * @param s Start vertex
	 * @param t End vertex
	 * @return count of paths between s and t
	 */
	public static <E> int countPathsBetweenVertexs(Map<Vertex<E>, List<Vertex<E>>> graph, Vertex<E> s, Vertex<E> t){
		int count = 0;
		if(!graph.keySet().contains(s) || !graph.keySet().contains(t))
			return count;
		Stack<Vertex<E>> stack = new Stack<Vertex<E>>();
		Set<Vertex<E>> visiting = new HashSet<Vertex<E>>();
		Set<Vertex<E>> hasPathToT = new HashSet<Vertex<E>>();
		Map<Vertex<E>, Integer> neighborsHasCheckedIndex = new HashMap<Vertex<E>, Integer>();	//Note, need add this to avoid go back and double counted.
		//initialize neighborsHasChecked map
		for (Vertex<E> ver: graph.keySet()) {
			ver.reset();
			neighborsHasCheckedIndex.put(ver, 0);
		}
		s.color = COLOR.GREY;
		stack.push(s);
		while(s.color != COLOR.BLACK){
			Vertex<E> next = null;
			
			int neighborIndex = neighborsHasCheckedIndex.get(stack.peek());
			while(neighborIndex < graph.get(stack.peek()).size()){
				Vertex<E> neighbor = graph.get(stack.peek()).get(neighborIndex);
				if(neighbor.color == COLOR.WHITE){
					neighborsHasCheckedIndex.put(stack.peek(), ++ neighborIndex);
					next = neighbor;
					break;
				}
				else{
					if(neighbor.equals(t) || hasPathToT.contains(neighbor)){
						hasPathToT.addAll(visiting);
						count ++;	//when T is already grey.
					}
				}
				neighborsHasCheckedIndex.put(stack.peek(), ++ neighborIndex);
			}
			
			
			if(next != null && !next.equals(t)){
				next.π = stack.peek();
				next.color = COLOR.GREY;
				stack.push(next);
				visiting.add(next);
			}else{	
				if(next == null){
					next = stack.pop();
				}else{
					hasPathToT.addAll(visiting);
					count ++;	//when T was white.
				}
				next.color = COLOR.BLACK;
				visiting.remove(next);
			}
		}
		
		return count;
	}
	
	//test
	public static void main(String[] args) {
		
		//test on image 22.8
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
		System.out.println(countPathsBetweenVertexs(g, p, v));	//4 pov, poryv, posryv, psryv
		System.out.println(countPathsBetweenVertexs(g, p, z));	//5 povwz, poryvwz, posryvwz, psryvwz, pz
		System.out.println(countPathsBetweenVertexs(g, n, t));	//4 nqt, nut, norut, nosrut
		System.out.println(countPathsBetweenVertexs(g, p, r));	//3 por, psr, posr
	}

}
