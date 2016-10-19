package chap22_ElementaryGraphAlgo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 161018
 * @author xiuzhu
 * 22.1-5 有向图G = (V, E)的**平方图**是图G2 = (V, E2)，该图满足下列条件：(u, w)属于E2当且仅当v属于V，有(u, v)属于E，且(v, w)属于E。
 * 亦即，如果图G中顶点u和w之间存在着一条恰包含两条边的路径时，则G2必包含该边(u, w)。针对图G邻接表和邻接矩阵两种表示，分别写出根据G产生G2的有效算法，并分析所给出算法的运行时间。  
 */
public class Prac22_1_5_SquareGraph {

	/**
	 * @param g 有向图G的邻接矩阵表示. O(V^3)
	 * @return 平方图G2的邻接矩阵表示
	 */
	public int[][] transform(int[][] g){
		if(g == null)
			return null;
		
		int[][] g2 = new int[g.length][g.length];
		for (int i = 0; i < g.length; i++) {
			for (int j = 0; j < g.length; j++) {
				if(g[j][i] == 1){
					for (int k = 0; k < g.length; k++) {
						if(g[i][k] == 1)
							g2[j][k] = 1;
					}
				}
			}
		}
		return g2;
	}
	
	/**
	 * @param g 有向图G的邻接表表示. O(V^3)
	 * @return 平方图G2的邻接表表示
	 */
	public Map<Integer, Set<Integer>> transform(Map<Integer, Set<Integer>> g){
		if(g == null)
			return null;
		
		Map<Integer, Set<Integer>> g2 = new HashMap<Integer, Set<Integer>>();
		
		for (Integer k: g.keySet()) {
			Set<Integer> neighbors = g.get(k);
			if(neighbors != null){
				for (int neighbor: neighbors) {
					Set<Integer> neighborsOfNeighbor = g.get(neighbor);
					if(neighborsOfNeighbor != null){
						for (int neighborOfNeighbor: neighborsOfNeighbor) {
							Set<Integer> newNeighbors = g2.containsKey(k) ? g2.get(k): new HashSet<Integer>();
							newNeighbors.add(neighborOfNeighbor);
							g2.put(k, newNeighbors);
						}
					}
				}
			}
		}
		return g2;
	}
	
	public static void main(String[] args) {
		Prac22_1_5_SquareGraph p = new Prac22_1_5_SquareGraph();
		System.out.println(Arrays.deepToString(p.transform(new int[][]{{0,1,1},{0,0,1},{0,0,0}})));	//[[0, 0, 1], [0, 0, 0], [0, 0, 0]]
		System.out.println(Arrays.deepToString(p.transform(new int[][]{{0,1,1},{0,0,1},{1,0,0}})));	//[[1, 0, 1], [1, 0, 0], [0, 1, 1]]
		
		Map<Integer, Set<Integer>> g = new HashMap<Integer, Set<Integer>>();
		Set<Integer> s1 = new HashSet<Integer>();
		s1.add(2);
		s1.add(3);
		g.put(1, s1);
		Set<Integer> s2 = new HashSet<Integer>();
		s2.add(3);
		g.put(2, s2);
		System.out.println(p.transform(g));	//{1=[3]}
		Set<Integer> s3 = new HashSet<Integer>();
		s3.add(1);
		g.put(3, s3);
		System.out.println(p.transform(g));	//{1=[1, 3], 2=[1], 3=[2, 3]}

	}

}
