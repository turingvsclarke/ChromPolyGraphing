// GraphNormal.java
// rewriting the entire graph using lists of vertices and edges, rather than an adjacency list

import java.util.ArrayList;

public class GraphNormal implements Cloneable{
    ArrayList Edges = new ArrayList();
    ArrayList Vertices = new ArrayList();

    public static void main(String[] args){
        // Testing creating a new edge and cloning it.
	GraphNormal g = new GraphNormal();
	Vertex v1 = new Vertex(0,0);
	Vertex v2 = new Vertex(1,1);
	Vertex v3 = new Vertex(2,2);
  	Vertex v4 = new Vertex(1,0);

	g.addVertex(v1);
	g.addVertex(v2);
	g.addVertex(v3);
  	g.addVertex(v4);
	g.addEdge(v1,v3);
	g.addEdge(v2,v3);
	g.addEdge(v3,v4);
	g.printVertices();
	g.printEdges();
	System.out.println(g.getChromPoly().getPolyString("x"));
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

    public boolean containsVertex(Vertex v){
	     ArrayList vertices = this.getVertices();
	     boolean containsVertex = vertices.contains(v);
       return containsVertex;

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
		this.getVertices().remove(v);
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
		// This will throw an exception if v1, v2 are the same vertex. Do nothing if they are
		try{
			Edge e = new Edge(v1,v2);
			edges.add(e);
		}catch(Exception a){
      System.out.println("Cannot add edge, vertices are the same");
    }
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
		 // We'll end up removing the currentedge, so don't let the counter increment
		 edge--;
		 Vertex otherV = null;
                 // Get the vertex besides v1
                 if(currentEdge.getV1().equals(v1)){
                    otherV = currentEdge.getV2();
                  } // end if
                  else if (currentEdge.getV2().equals(v1)){
			otherV = currentEdge.getV1();
		  } // end else

                  // Make a new edge with that other edge and v2
                  this.addEdge(otherV,v2);
                  // Remove the old edge that contained v1
                  this.removeEdge(currentEdge);
            } // end if
	      } // end for

        // Once all edges are transferred, remove v1 from graph
        try{
		        this.removeVertex(v1);
        } // end try
        catch(HasEdgeException ex){
		System.out.println("Cannot remove vertex. In an edge");
        	v1.printCoordinates();
	}
    } // end collapseEdge

    public boolean hasEdges(){
        boolean hasEdges = false;
	if(this.getEdges().size()>0){
		hasEdges = true;
        } // end if
        return hasEdges;
    } // end hasEdges

    ///***
    // Chromatic Polynomial Calculations using recursion
    public Polynomial getChromPoly(){
        Polynomial chromPoly = new Polynomial();
        // This checks if the graph has no edges(note that it must have at least two vertices to have an edge)
        if(this.hasEdges()){
	    // Just use the first edge
            // Create two copies of the graph
	    GraphNormal g1 = this.clone();
	    GraphNormal g2 = this.clone();

	    // Get the chromatic polynomial of the graph with the edge removed
            g1.removeEdge(g1.getEdge(0));
            Polynomial rmvEdge = g1.getChromPoly();

	    // Get the chromatic polynomial of the graph with the edge collapsed
            g2.collapseEdge(g2.getEdge(0));
            Polynomial cllpsEdge = g2.getChromPoly();

	    // Subtract the two results
            chromPoly= Polynomial.subtractPolys(rmvEdge,cllpsEdge);
        } // end if

	// If a graph with no edges has n vertices, the ways of coloring it are q^n
	else{
		chromPoly = new Polynomial(this.getVertices().size());
	} // end else

        return chromPoly;
    } // end getChromPoly
    //***/
    /***
    // Chromatic Polynomial Calculations using recursion
    public String getChromPoly(){
        String chromPoly="";
        // This checks if the graph has no edges(note that it must have at least two vertices to have an edge)
        if(this.hasEdges()){
	    // Just use the first edge
            // Create two copies of the graph
	    GraphNormal g1 = this.clone();
	    GraphNormal g2 = this.clone();

	    // Get the chromatic polynomial of the graph with the edge removed
            g1.removeEdge(g1.getEdge(0));
            String rmvEdge = g1.getChromPoly();

	    // Get the chromatic polynomial of the graph with the edge collapsed
            g2.collapseEdge(g2.getEdge(0));
            String cllpsEdge = g2.getChromPoly();

	    // Subtract the two results
            chromPoly= "("+rmvEdge+"-"+cllpsEdge+")";
        } // end if

	// If a graph with no edges has n vertices, the ways of coloring it are q^n
	else{
		chromPoly = "q^" + String.valueOf(this.getVertices().size());
	} // end else

        return chromPoly;
    } // end getChromPoly

    ***/

public class HasEdgeException extends Exception{} // end class def

} // end GraphNormal class def
