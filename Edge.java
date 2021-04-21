// Edge.java
// Each edge has two vertices.

public class Edge implements Cloneable{

	private final Vertex V1;
	private final Vertex V2;

	public static void main(String[] args){


	}

	public Edge clone(){
		Vertex v1Clone = this.V1.clone();
		Vertex v2Clone = this.V2.clone();
		return new Edge(v1Clone,v2Clone);
	} // end clone

	public boolean containsVertex(Vertex v){
		boolean containsVertex=false;
		if(this.getV1.equals(v) || this.getV2.equals(v)){
			containsVertex = true;
		}
		return containsVertex;
	}

	public Edge(Vertex a,Vertex b) throws SameVertex{
		// Ensure that the x and y coordinates of the vertices aren't the same
		if(a.getX()!=b.getX() && a.getY()!=b.getY()){
			this.V1 = a;
			this.V2 = b;
		} // end if

		else{
			throw new SameVertex("Illegal parameters. Edge vertices must have different coordinates");
		} // end else
	}  // end constructor

	private class SameVertex extends Exception{
		public SameVertex(String message){
			super(message);
		}
	}

} // end Edge
