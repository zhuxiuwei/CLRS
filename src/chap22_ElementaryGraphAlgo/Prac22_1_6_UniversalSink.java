package chap22_ElementaryGraphAlgo;

/**
 * 161019
 * @author xiuzhu
 * 22.1-6 如果我们用邻接矩阵来存储图，那么绝大多数图算法的运行时间都是Ω(|V|^2)（V为一个图的顶点集），但还是有些例外。比如，给定一个有向图Ｇ的邻接矩阵Ａ，
 * 我们可以在Ο(|V|)时间内判断图G是否包含一个**通用汇点(universal sink)**，即一个入度为|V|-1出度为0的顶点。请给出这样的算法。    
 */
public class Prac22_1_6_UniversalSink {

	/**
	 * 判断是否存在通用汇点的**O(V)**算法。
	 * @param g
	 * @return
	 */
	public boolean isUniversalSinkExisted(int[][] g){
		//find potential point. O(V)
		int down = 0, right = 0;
		while(down < g.length && right < g.length){
			if(g[down][right] == 0)
				right++;
			else
				down++;
		}
		
		//check if potential point meets universal sink definition. O(V)
		for (int i = 0; i < g.length; i++)	//check if out degree is 0. 
			if(g[down][i] != 0)
				return false;
		for (int i = 0; i < g.length; i++) {	//check if in degree is V-1. 
			if(i != down && g[i][down] != 1)
				return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		Prac22_1_6_UniversalSink p = new Prac22_1_6_UniversalSink();
		System.out.println(p.isUniversalSinkExisted(new int[][]{{0,1},{0,0}}));	//true
		System.out.println(p.isUniversalSinkExisted(new int[][]{{0,1,1},{0,0,1},{0,0,0}}));	//true
		System.out.println(p.isUniversalSinkExisted(new int[][]{{0,0,0},{1,0,0},{1,0,0}}));	//true
		System.out.println(p.isUniversalSinkExisted(new int[][]{{0,1},{1,0}}));	//false
		System.out.println(p.isUniversalSinkExisted(new int[][]{{0,1,0},{1,0,0},{0,1,0}}));	//false

	}

}
