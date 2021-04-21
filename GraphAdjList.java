// Graph.java
import java.util.ArrayList;

public class Graph extends ArrayList{
    String chromPoly="";
    Graph g1;
    Graph g2;
    String rmvEdge;
    String cllpsEdge;

    public static void main(String[] args){
        Graph g = new Graph();
        Vertex v1 = new Vertex(0,1);
        Vertex v2 = new Vertex(1,1);
        Vertex v3 = new Vertex(2,3);
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        Graph g2 = (Graph)g.clone();
        g2.printVertices();
        g.addEdge(v1,v2);
        g.addEdge(v1,v3);
        g.addEdge(v2,v3);

        System.out.println(g.getChromPoly());
    }

    public Graph clone(){
        Graph g2 = (Graph)super.clone();
        for(int vertex=0;vertex<this.size();vertex++){
            Vertex v = (Vertex)this.get(vertex);
            Vertex copy = v.clone();
            g2.set(vertex,copy);
        }
        return g2;
        // Deep clone(clone all the vertices of the graph)
    }

    // TESTING ONLY
    public void printVertices(){
        for(int i=0;i<this.size();i++){
                System.out.println("Vertex:");
                Vertex v = (Vertex)this.get(i);
                v.printCoordinates();
        }
    }
    public void setChromPoly(String poly){
        this.chromPoly = poly;
    }

    public void addVertex(Vertex v){
        if(this.indexOf(v)==-1){
            this.add(v);
        } // end if
    } // end addVertex

    public void removeVertex(Vertex v){
        this.remove(v);
    }

    public void addEdge(Vertex v1, Vertex v2){
        v1.addNeighbor(v2);
        v2.addNeighbor(v1);
    } // end addEdge

    public void removeEdge(Vertex v1, Vertex v2){
        // remove ech vertex from each other's list of neighbors
        v1.removeNeighbor(v2);
        v2.removeNeighbor(v1);
    } // end removeEdge

    public void collapseEdge(Vertex v1, Vertex v2){
        // Make v1 and v2 the same point(so transfer any neighbors you have to)

        // First remove the edge between v1 and v2
        this.removeEdge(v1,v2);

        // Do stuff with every neighbor of v1
        while(v1.hasNeighbors()){

            // Remove all the edges to v1 and merge them with v2
            Vertex v1Neighbor = v1.firstNeighbor();

            // Note that this removes that neighbor from the list of v1's neighbors
            this.removeEdge(v1Neighbor,v1);
            this.addEdge(v1Neighbor,v2);

        } // end for

        // once all the neighbors have been transferred, remove v1 from the graph
        this.remove(v1);

    } // end collapseEdge

    public boolean hasEdges(){
        boolean hasEdges = false;
        // check if all the list of neighbors are empty
        for(int vertex=0;vertex<this.size();vertex++){
            Vertex v = (Vertex)this.get(vertex);
            // if the current vertex's list of neighbors is empty, return false
            if(v.hasNeighbors()){
                hasEdges = true;
                vertex = this.size()+1;
            }
        } // end for

        return hasEdges;

    } // end hasEdges

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

} // end class def
