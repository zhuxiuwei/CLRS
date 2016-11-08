package chap22_ElementaryGraphAlgo;
/**
 * Vertex of graph. 160929
 * Used by BFS, DSF, etc.
 */
public class Vertex<T> {
	public T value;
	public Vertex<T> π;
	public int d;	//for BFS means depth, for DFS means start time.
	public int f;	//
	public COLOR color;
	
	public Vertex(T value){
		this.value = value;
		this.π = null;
		this.d = Integer.MAX_VALUE;
		this.f = Integer.MAX_VALUE;
		this.color = COLOR.WHITE;
	}
	
	@Override
	public String toString(){
		return value.toString();

	}
	
	public String formatString(){
		String dString = d == Integer.MAX_VALUE ? "∞": d + "";
		String tString = f == Integer.MAX_VALUE ? "∞": f + "";
		String πString = π == null ? "null": π.value.toString();
		return String.format("Vertex: %2s, π: %4s, d: %2s, f: %2s", value, πString, dString, tString);
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
	
	public void reset(){
		this.π = null;
		this.d = Integer.MAX_VALUE;
		this.f = Integer.MAX_VALUE;
		this.color = COLOR.WHITE;
	}
}

enum COLOR{WHITE, GREY, BLACK}
