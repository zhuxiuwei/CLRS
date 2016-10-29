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
		this.d = 0;
		this.color = COLOR.WHITE;
	}
	
	@Override
	public String toString(){
		if(π != null)
			return "Vertex: " + value + ", d:" + d + ", π:" + π.value;
		else
			return "Vertex: " + value + ", d:" + d + ", π: null";

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
