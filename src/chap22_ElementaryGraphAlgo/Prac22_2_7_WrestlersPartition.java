package chap22_ElementaryGraphAlgo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 161031
 * @author xiuzhu
 * Practice 22.2-7 职业拳击手划分成好选手、坏选手问题。
 * !!!!! Note Bug: 判断是否符合集合要求时，要在 “if(neighbor.color == COLOR.WHITE){”之前。否则永远返回true。 L67.
 */
public class Prac22_2_7_WrestlersPartition {

	/**
	 * 判断有竞争关系的拳击手，能否划分成两组，让两组内每组内部没有竞争关系。
	 * @param grahp 竞争关系图
	 * @return null if can not be partitioned, else two partitioned sets.
	 */
	public static <E> ResultSets<E> partition(Map<Vertex<E>, List<Vertex<E>>> grahp){
		ResultSets<E> res = new ResultSets<E>();
		if(grahp == null || grahp.isEmpty())
			return res;
		
		while(true){
			Vertex<E> v = getVertexNotInSet(grahp, res);
			if(v == null)
				break;
			
			if(!bfs(grahp, v, res)){
				res.canBePartitioned = false;
				return res;
			}
		}
		
		return res;
	}
	
	/**
	 * Judge during BFS. 
	 * @param grahp
	 * @param s
	 */
	private static <E> boolean bfs(Map<Vertex<E>, List<Vertex<E>>> grahp, Vertex<E> s, ResultSets<E> res){
		//reset all nodes
		for (Vertex<E> v: grahp.keySet()) {
			v.color = COLOR.WHITE;
			v.d = Integer.MAX_VALUE;
			v.π = null;
		}
		if(res.getInWhichSet(s).equals("NONE"))
			res.goodGuys.add(s);	//by default add to good guys.
		s.d = 0;
		Queue<Vertex<E>> q = new LinkedList<Vertex<E>>();
		s.color = COLOR.GREY;
		q.offer(s);
		while(!q.isEmpty()){
			Vertex<E> v = q.poll();
			List<Vertex<E>> neighbors = grahp.get(v);
			for(Vertex<E> neighbor: neighbors){
				
				/*
				 * Judge if still meets the two sets rule.  
				 * !!!!!!!!! Note 1: judge before "if(neighbor.color == COLOR.WHITE){"
				 */
				if(res.getInWhichSet(neighbor).equals(res.getInWhichSet(v)) )	//violated! v and neighbor are in same set.
					return false;
				else if(res.getInWhichSet(v).equals("GOOD"))	//add to a different set with parent.
					res.badGuys.add(neighbor);
				else if(res.getInWhichSet(v).equals("BAD"))	//add to a different set with parent.
					res.goodGuys.add(neighbor);
				
				if(neighbor.color == COLOR.WHITE){
					q.offer(neighbor);
					neighbor.π = v;
					neighbor.d = v.d + 1;
					neighbor.color = COLOR.GREY;
				}
			}
			v.color = COLOR.BLACK;
		}
		return true;
	} 
	
	/**
	 * 判断是否所有的顶点都已经划分到某组
	 * @return null if all vertex are in a group, else return a vertex not in any group yet.
	 */
	private static <E> Vertex<E> getVertexNotInSet(Map<Vertex<E>, List<Vertex<E>>> grahp, ResultSets<E> res){
		for(Vertex<E> v : grahp.keySet()){
			if(res.goodGuys.contains(v) || res.badGuys.contains(v))
				continue;
			else
				return v;
		}
		return null;
	}
	
	//test
	public static void main(String[] args) {
		
		//非连通图。4,7在1 2 3 5 6 的图外。
		Vertex<Integer> v1 = new Vertex<Integer>(1), v2 = new Vertex<Integer>(2), v3 = new Vertex<Integer>(3), v4 = new Vertex<Integer>(4), v5 = new Vertex<Integer>(5), v6 = new Vertex<Integer>(6), v7 = new Vertex<Integer>(7);
		List<Vertex<Integer>> l1 = new LinkedList<Vertex<Integer>>(); l1.add(v5); l1.add(v6);
		
		//test case 1: below is true. Good: 1 2 3 4 bad: 5 6 7
//		List<Vertex<Integer>> l2 = new LinkedList<Vertex<Integer>>(); l2.add(v5); 
//		List<Vertex<Integer>> l3 = new LinkedList<Vertex<Integer>>(); l3.add(v6); 
//		List<Vertex<Integer>> l4 = new LinkedList<Vertex<Integer>>(); l4.add(v7);
		
		//test case 2: below still true, but partition is different from test case 1. Good: 1 2 3 7 bad: 4 5 6
		List<Vertex<Integer>> l2 = new LinkedList<Vertex<Integer>>(); l2.add(v5); 
		List<Vertex<Integer>> l3 = new LinkedList<Vertex<Integer>>(); l3.add(v6); l3.add(v4); 
		List<Vertex<Integer>> l4 = new LinkedList<Vertex<Integer>>(); l4.add(v7);l4.add(v3);
		
		//test case 3: below is false. 2, 3 violated.
//		List<Vertex<Integer>> l2 = new LinkedList<Vertex<Integer>>(); l2.add(v5); l2.add(v3); 
//		List<Vertex<Integer>> l3 = new LinkedList<Vertex<Integer>>(); l3.add(v6); l3.add(v2);
//		List<Vertex<Integer>> l4 = new LinkedList<Vertex<Integer>>(); l4.add(v7);
		
		List<Vertex<Integer>> l5 = new LinkedList<Vertex<Integer>>(); l5.add(v1);l5.add(v2);
		List<Vertex<Integer>> l6 = new LinkedList<Vertex<Integer>>(); l6.add(v1);l6.add(v3);
		List<Vertex<Integer>> l7 = new LinkedList<Vertex<Integer>>(); l7.add(v4);
		Map<Vertex<Integer>, List<Vertex<Integer>>> g = new HashMap<Vertex<Integer>, List<Vertex<Integer>>>();
		g.put(v1, l1); g.put(v2, l2); g.put(v3, l3); g.put(v4, l4); g.put(v5, l5); g.put(v6, l6);g.put(v7, l7);
		
		System.out.println(partition(g));
	}

}

/**
 * Result of Prac22_2_7_WrestlersPartition
 */
class ResultSets<T>{
	public Set<Vertex<T>> goodGuys = new HashSet<Vertex<T>>();	//good guys set
	public Set<Vertex<T>> badGuys = new HashSet<Vertex<T>>();	//bad guys set
	public boolean canBePartitioned = true;	//True if can be partitioned, else false. Default true.
	
	/**
	 * 判断给定的选手被划分到哪个组。
	 * @param v
	 * @return
	 */
	public String getInWhichSet(Vertex<T> v){
		if(goodGuys.contains(v))
			return "GOOD";
		else if(badGuys.contains(v))
			return "BAD";
		else
			return "NONE";
	}
	@Override
	public String toString(){
		if(canBePartitioned){
			return "Can be partitioned, gooGuys: " + goodGuys + ", badGuys:" + badGuys;
		}else{
			return "Can not be partitioned";
		}
	}
}