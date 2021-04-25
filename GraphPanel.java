// GraphPanel.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GraphPanel extends JPanel{
    int height;
    int width;
    GraphNormal graph;

    public GraphPanel(){
	// create a random graph with 10 vertices and 3 edges
	this.graph = new GraphNormal(100,100);
	this.height = this.getHeight();
	this.width = this.getWidth();
	this.setLayout(null);
	this.repaint();
    } // end constructor

    public Vertex getVertexAtPoint(int x,int y){
	GraphNormal g = this.getGraph();
	int v = g.getVertices().size();
	Vertex vertex=null;
	// Check each vertex to find out if it's "near" that point
	for(int i=0;i<v;i++){
		Vertex thisVertex = g.getVertex(i);
		int vX = thisVertex.getX();
		int vY = thisVertex.getY();
		// Each vertex circle has center vX,vY, radius 2.5, so see if the point is in such a circle
		if((Math.abs(vX-x)<=2.5)&&(Math.abs(vY-y)<=2.5)){	
			// if its in that range, this is the vertex the user clicked
			vertex=thisVertex;
			break;
		} // end if
	} // end for
	return vertex;
    } // end getVertexAtPoint
    
    // Make sure the panel is ready for a new vertex
    public void addNewVertexListener(){
	this.addMouseListener(new newVertexClickListener());
    } // end addNewVertexListener

    // This listener adds a new vertex to the graph when clicked on. It is a one-use-only listener
    class newVertexClickListener extends MouseAdapter{
	boolean notFired = true;
	// When the action is performed, 
	public void MouseClicked(MouseEvent e){
		// Add a new vertex at the point
		if(notFired){
			// Add a new vertex
			int x = e.getX();
			int y = e.getY();
			graph.addVertex(new Vertex(x,y));
			repaint();
		} // end if
		// Draw the new vertex
		// Make sure that more vertices aren't added
		notFired = false;
	} // end MouseClicked
    }

    // Make sure the panel is ready for a new edge
    public void addEdgeListener(){
	this.addMouseListener(new FirstVertexListener());	
    } // end addEdgeListener

    // this listener waits for a click indicating the first vertex of the new edge being added
    class FirstVertexListener extends MouseAdapter{
	// It's super important that this isn't fired again, since we will be getting a second vertex
	boolean notFired=true;
	public void MouseClicked(MouseEvent e){
		if(notFired){
			// get the location of the event
			int x = e.getX();
			int y = e.getY();
			// Get the vertex near that point
			Vertex v1 = getVertexAtPoint(x,y);
			if(v1!=null){
				SecondVertexListener l = new SecondVertexListener(v1);
				// Add the listener to the panel	
				// make sure its never fired again
				notFired = false;
			} // end if
		} // end if
	} // end MouseClicked
    } // end FirstVertexListener

    // This listener will actually add the edge once a second vertex has been clicked
    class SecondVertexListener extends MouseAdapter{
	// The second vertex needs to have a first vertex in order to add the edge
	Vertex v1;
	boolean notFired=true;
	public SecondVertexListener(Vertex v){
		this.v1 = v;		
	} // end constructor

	public void MouseClicked(MouseEvent e){
		if(notFired){
			int x = e.getX();
			int y = e.getY();
			Vertex v2 = getVertexAtPoint(x,y);
			if(v2!=null){
				// get the vertex that's "near" that point
				// add the complete edge to the graph
				graph.addEdge(v1,v2);
				// repaint the panel
				repaint();
				// ensure that the same listener never does anything again
				notFired = false;
			} // end if
		} // end if
	} // end MouseClicked
    } // end SecondVertexListener
	
    public void paintComponent(Graphics g){
	// paint the graph
	// Draw all the vertices and an edge if it exists between them
	for(int i=0;i<graph.getEdges().size();i++){
		// Clear all previous drawings by painting over the whole panel
		g.setColor(this.getBackground());
		g.fillRect(0,0,this.getWidth(),this.getHeight());

		Edge edge = graph.getEdge(i);
		Vertex V1 = edge.getV1();
		Vertex V2 = edge.getV2();
		// Paint a circle at each vertex and a line for each edge 
		int vX1 = V1.getX();
		int vX2 = V2.getX();
		int vY1 = V1.getY();
		int vY2 = V2.getY();
		g.setColor(Color.RED);
		g.fillOval(vX1-5,vY1-5,10,10);
		g.fillOval(vX2-5,vY2-5,10,10);
		g.setColor(Color.BLACK);
		g.drawLine(vX1,vY1,vX2,vY2);
	} // end for
	// For erasing use g.setColor(this.getBackgroundColor).
    } // end paintComponent

    // getter
    public GraphNormal getGraph(){
	return this.graph;
	} // end getter


    // Old method for when vertices were buttons
    /***
    public void paintVertices(){
	// Add all the vertices to the graph
	GraphNormal g = this.graph;
	int v = g.getVertices().size();
	for(int i=0;i<1;i++){	
		VertexButton b = new VertexButton();
		Vertex currentV = g.getVertex(i);
		int vX = currentV.getX();
		int vY = currentV.getY();
		int w = b.getWidth();
		int h = b.getHeight();
		// Have the button be centered at the vertex's coordinates
		b.setBounds(500,500,100,100);
		this.add(b);
	} // end for

    } // end paintVertices
    ***/
} // end GraphPanel
