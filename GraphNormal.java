// GraphNormal.java
// rewriting the entire graph using lists of vertices and edges, rather than an adjacency list

import java.util.ArrayList;

public class GraphNormal implements Cloneable{
    ArrayList Edges = new ArrayList();
    ArrayList Vertices = new ArrayList();

    public static void main(String[] args){
        // Testing creating a new edge and cloning it.
	//	GraphNormal g = new GraphNormal(3,3,100,100);
	g.printVertices();
	g.printEdges();
	g.collapseEdge(g.getEdge(0));
	g.printVertices();
	g.printEdges();
    } // end main

    public GraphNormal(){
        this.Edges = new ArrayList();
        this.Vertices = new ArrayList();
    } // end constructor

    public void testClone(){
	GraphNormal g2 = this.clone();
	this.printEdges();
	g2.printEdges();
	g2.removeEdge(g2.getEdge(0));
	this.printEdges();
	g2.printEdges();
    } // end testClone

    // Generate a random graph of a given size. Obviously, #edges = min[(v-1)!/2,e]
    public GraphNormal(int v,int e, int xrange, int yrange){
	this();	
	for(int i=0;i<v;i++){
		// Get random integers between 0 and 100
		int x1 = (int)(Math.random()*(xrange-1));
		int y1 = (int)(Math.random()*(yrange-1));
		this.addVertex(new Vertex(x1,y1));
	} // end for		
	// Add as many edges as you can to the graph(Pick the 0th up to the (v-1)th vertex)
	for(int i=0;i<e;i++){
		// get a random vertex
		int firstV = (int)(Math.random()*(v-1));
		int nextV = (int)(Math.random()*(v-1));
		Vertex v1 = this.getVertex(firstV);
		Vertex v2 = this.getVertex(nextV);
		this.addEdge(v1,v2);
	} // end for
    } // end random graph constructor

    public GraphNormal clone(){
        GraphNormal g2 = new GraphNormal();
        // Clone each vertex and put it in the edge of its corresponding
        // Clone all your vertices and replace
        for(int vertex=0;vertex<this.Vertices.size();vertex++){
            // Get the next vertex
            Vertex v = (Vertex)this.Vertices.get(vertex);
            // Add a clone of that vertex to the clone of the graph
            Vertex vclone = v.clone();
            g2.addVertex(vclone);
            // make sure g2 has "clones" of the edges
            for(int edge=0;edge<this.Edges.size();edge++){
                // get the next edge
                Edge e = (Edge)this.Edges.get(edge);
                // Check to see if our vertex is in that edge
                if(e.containsVertex(v)){
		    Vertex edgeV1 = e.getV1();
		    Vertex edgeV2 = e.getV2();
		    int v1Index = this.Vertices.indexOf(edgeV1);
		    int v2Index = this.Vertices.indexOf(edgeV2);
                    // if we haven't cloned this vertex's counterpart in the edge, don't do anything. Otherwise, add an edge with both clones
                    if(edgeV2.equals(v) && v1Index<vertex){
			Vertex v1Clone = (Vertex)g2.getVertices().get(v1Index);
                        g2.addEdge(vclone,v1Clone);
                    } // end if
                    else if(edgeV1.equals(v) && v2Index<vertex){
			Vertex v2Clone = (Vertex)g2.getVertices().get(v2Index);
                        g2.addEdge(vclone,v2Clone);
                    } // end else if
                } // end if
            } // end for
        } // end for
        return g2;
    } // end clone

    // TESTING ONLY
    public void printVertices(){
        for(int i=0;i<this.Vertices.size();i++){
                System.out.println("Vertex "+ i + ":");
                Vertex v = (Vertex)this.Vertices.get(i);
                v.printCoordinates();
        }
    } // end printVertices

    public void printEdges(){
	// Go through all the edges and print out which vertices are in each edge
	for(int i=0;i<this.getEdges().size();i++){
		System.out.println("Edge " + i + ":");
		Vertex v1 = this.getEdge(i).getV1();
		Vertex v2 = this.getEdge(i).getV2();
				
		int v1Index = this.getVertices().indexOf(v1);
		int v2Index = this.getVertices().indexOf(v2);		

		System.out.println("Vertex " + v1Index + ", Vertex " + v2Index);
	} // end for
    } // end printEdges
    // End of test methods

    public void addVertex(Vertex v){
        if(this.Vertices.indexOf(v)==-1){
            this.Vertices.add(v);
        } // end if
    } // end addVertex

    public void removeVertex(Vertex v) throws HasEdgeException{
	boolean inEdge = false;
	// Check each edge for the vertex
	for(int i=0;i<this.Edges.size();i++){
		Edge e = this.getEdge(i);
		// Stop looking if the edge has that vertex
		if(e.containsVertex(v)){
			inEdge = true;
			break;
		} // end if
	} // end for
	if(inEdge){
		throw new HasEdgeException();
	}
	else{
		this.Vertices.remove(v);
	}
    } // end removeVertex

    public boolean containsVertex(Vertex v){
	ArrayList vertices = this.getVertices();
	boolean containsVertex = vertices.contains(v); 
	return containsVertex;

    } // end containsVertex

    public void addEdge(Vertex v1, Vertex v2){
	// Make sure the edge isn't already there
	boolean addEdge = true;
	ArrayList edges = this.getEdges();
	for(int i=0;i<edges.size();i++){
		Edge edge = this.getEdge(i); 
		if(edge.containsVertex(v1) && edge.containsVertex(v2)){
			i = edges.size();
			addEdge = false;
		} // end if
	}
	if(addEdge){
		try{
			Edge e = new Edge(v1,v2);
			edges.add(e);	
		}catch(Exception a){}
    	} // end if
    } // end addEdge

    public Vertex getVertex(int i){
		return (Vertex)this.getVertices().get(i);
	} // end getVertex

    public Edge getEdge(int i){
		return (Edge)this.getEdges().get(i);
	} // end getEdge
    
    public ArrayList getEdges(){
		return this.Edges;
	} // end getEdges

    public ArrayList getVertices(){
		return this.Vertices;
	} // end getVertices

    public void removeEdge(Edge e){
	this.getEdges().remove(e);
	
    } // end removeEdge

    public void collapseEdge(Edge e){
        // Make v1 and v2 the same point(so transfer any neighbors you have to)
        // First remove the edge between v1 and v2
        Vertex v1 = e.getV1();
	Vertex v2 = e.getV2();
	this.removeEdge(e);
	
        // Look through all the edges and reassign any that contain v1 to v2
       	for(int edge=0;edge<this.getEdges().size();edge++){
		Edge currentEdge = this.getEdge(edge);
		if(currentEdge.containsVertex(v1)){
			Vertex otherV = null;
			// Get the vertex besides v1
			if(currentEdge.getV1()==v1){
				otherV = currentEdge.getV2();
			} // end if			
			else{
				otherV = currentEdge.getV1();
			} // end else
			// Make a new edge with that other edge and v2 
			this.addEdge(otherV,v2);
		} // end if
	} // end for
	// Once all edges are transferred, remove v1 from graph
        try{
		this.removeVertex(v1);
	} // end try
	catch(Exception ex){
		System.out.println("Cannot remove edge. Contains vertex");
	}
    } // end collapseEdge
    /**
    public boolean hasEdges(){
        boolean hasEdges = false;
        // check if all the list of neighbors are empty
        for(int vertex=0;vertex<this..size();vertex++){
            Vertex v = (Vertex)this.get(vertex);
            // if the current vertex's list of neighbors is empty, return false
            if(v.hasNeighbors()){
                hasEdges = true;
                vertex = this.size()+1;
            }
        } // end for

        return hasEdges;

    } // end hasEdges
    /**
    // Chromatic Polynomial Calculations using recursion
    public String getChromPoly(){
        String chromPoly="";
        // This checks if the graph has no edges(note that it must have at least two vertices to have an edge)
        if(!(this.hasEdges())){
            chromPoly = "q^" + String.valueOf(this.size());
        } // end if
         // If there are still edges, compute the polynomial of them
        else{

            boolean getEdge=true;
            int vertex = 0;
            Vertex v1=null;
            Vertex v2=null;
            // Look for the first edge
            // We know that we HAVE edges, but we still need to FIND them
            while(getEdge){
                // Use the first vertex and its first neighbor for the edge
                v1 = (Vertex)this.get(vertex);
                if(v1.hasNeighbors()){
                    v2 = (Vertex)v1.firstNeighbor();
                    getEdge = false;
                }
                else{
                    // If the first vertex has no neighbors, ask the second one for neighbors
                    vertex++;
                }
            } // end while

            this.removeEdge(v1,v2);
            g1 = (Graph)this.clone();
            rmvEdge = g1.getChromPoly();

            this.collapseEdge(v1,v2);
            g2 = (Graph)this.clone();
            cllpsEdge = g2.getChromPoly();

            chromPoly= "("+rmvEdge+"-"+cllpsEdge+")";
        } // end else

        return chromPoly;
    } // end getChromPoly
**/

public class HasEdgeException extends Exception{} // end class def



} // end class def
