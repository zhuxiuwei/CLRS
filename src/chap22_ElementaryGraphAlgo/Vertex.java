package chap22_ElementaryGraphAlgo;
/**
 * Vertex of graph. 160929
 * Used by BFS, DSF, etc.
 */
public class Vertex<T> {
	public T value;
	public Vertex<T> π;
	public int d;	//
	public COLOR color;
	
	public Vertex(T value){
		this.value = value;
		this.π = null;
		this.d = Integer.MAX_VALUE;
		this.color = COLOR.WHITE;
	}
	
	@Override
	public String toString(){
		String dString = d == Integer.MAX_VALUE ? "∞": d + "";
		String πString = π == null ? "null": π.value.toString();
		return "Vertex: " + value + ", d:" + dString + ", π:" + πString;

	}
	
	@Override
	public boolean equals(Object obj){
		if(obj == null)
			return false;
		
		if(!(obj instanceof Vertex))
			return false;
		
		@SuppressWarnings({ "rawtypes" })
		Vertex o = (Vertex)obj;
		return o.value == this.value;	//Assume value is unique in a Graph.
	}
	
	@Override
	public int hashCode(){
		int res = 17;
		res = res * 31 + value.hashCode();
		return res;
	}
}

enum COLOR{WHITE, GREY, BLACK}
