package chap22_ElementaryGraphAlgo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 161108
 * @author Zhu xiuwei
 * Practice 22.4-3, judge if a Undirected Graph is cyclic within O(V) time.
 */
public class IsUndirectedGraphCyclic {
	
	/**
	 * judge if a Undirected Graph is cyclic
	 * @param graph
	 * @return true if cyclic, false if acyclic.
	 */
	public static <E> boolean judge(Map<Vertex<E>, List<Vertex<E>>> graph){
		Map<Vertex<E>, Integer> neighborsHasCheckedIndex = new HashMap<Vertex<E>, Integer>();	//record index of next neighbor to visit.
		//initialize neighborsHasChecked map
		for (Vertex<E> ver: graph.keySet()) {
			ver.reset();
			neighborsHasCheckedIndex.put(ver, 0);
		}
		Stack<Vertex<E>> stack = new Stack<Vertex<E>>();
		for (Vertex<E> u: graph.keySet()) {
			stack.clear();
			if(u.color == COLOR.WHITE){
				u.color = COLOR.GREY;
				stack.push(u);
				while(!stack.isEmpty()){
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
							if(stack.peek().equals(neighbor) //special case: when there is an edge point to self.
									|| ! stack.peek().π.equals(neighbor))	//Not an edge connect descendant to ancestor
								return true;	//got a NEW neighbor which is grey, Graph is cyclic.
							neighborsHasCheckedIndex.put(stack.peek(), ++ neighborIndex);
						}
					}
					
					if(next != null){	//keep on search Neighbor.
						next.π = stack.peek();
						next.color = COLOR.GREY;
						stack.push(next);
					}else{	//no more Neighbor, go back.
						next = stack.pop();
						next.color = COLOR.BLACK;
					}
				}
			}
		}
		return false;
	}

	//test
	public static void main(String[] args) {
		Vertex<Character> m = new Vertex<Character>('m'), n = new Vertex<Character>('n'), o = new Vertex<Character>('o'), p = new Vertex<Character>('p'),  q = new Vertex<Character>('q'), r = new Vertex<Character>('r');
		List<Vertex<Character>> ml = new LinkedList<Vertex<Character>>(); ml.add(n); ml.add(o);
		List<Vertex<Character>> nl = new LinkedList<Vertex<Character>>(); nl.add(m); nl.add(o); 
		List<Vertex<Character>> ol = new LinkedList<Vertex<Character>>(); ol.add(m); ol.add(n);
		Map<Vertex<Character>, List<Vertex<Character>>> g1 = new LinkedHashMap<Vertex<Character>, List<Vertex<Character>>>();
		g1.put(m, ml); g1.put(n, nl); g1.put(o, ol);
		System.out.println(judge(g1));	//true

		List<Vertex<Character>> pl = new LinkedList<Vertex<Character>>(); pl.add(q); pl.add(r);
		List<Vertex<Character>> ql = new LinkedList<Vertex<Character>>(); ql.add(p);
		List<Vertex<Character>> rl = new LinkedList<Vertex<Character>>(); rl.add(p);
		Map<Vertex<Character>, List<Vertex<Character>>> g2 = new LinkedHashMap<Vertex<Character>, List<Vertex<Character>>>();
		g2.put(p, pl); g2.put(q, ql); g2.put(r, rl);
		System.out.println(judge(g2));	//false
	}

}
