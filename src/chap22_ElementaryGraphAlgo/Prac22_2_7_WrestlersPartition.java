package chap22_ElementaryGraphAlgo;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 161031
 * @author xiuzhu
 * Practice 22.2-7 职业拳击手划分成好选手、坏选手问题。
 */
public class Prac22_2_7_WrestlersPartition {

	/**
	 * Result of Prac22_2_7_WrestlersPartition
	 */
	static class ResultSets<T>{
		Set<Vertex<T>> goodGuys = new HashSet<Vertex<T>>();	//good guys set
		Set<Vertex<T>> badGuys = new HashSet<Vertex<T>>();	//bad guys set
		boolean canBePartitioned = false;	//True if can be partitioned, else false. Default false.
		@Override
		public String toString(){
			if(canBePartitioned){
				return "Can be partitioned, gooGuys: " + goodGuys + ", badGuys:" + badGuys;
			}else{
				return "Can not be partitioned";
			}
		}
	}
	
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
			
		}
		
		return res;
	}
	
	
	public static void main(String[] args) {
		
	}

}
