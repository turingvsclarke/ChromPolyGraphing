// GraphPanel.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class GraphPanel extends JPanel{
    int height;
    int width;
    GraphNormal graph;

    public GraphPanel(){
	// Add a graph to the panel
	this.graph = new GraphNormal();
	this.addMouseListener(new DragVertexListener());
	this.setLayout(null);
	this.setBorder(BorderFactory.createLineBorder(Color.black));
  this.setSize(300,400);
	this.setVisible(true);
  this.repaint();
    } // end constructor

    public void generateGraph(int v,int e){
      this.graph = new GraphNormal(v,e,this.getWidth(),this.getHeight());
      repaint();
    } // end generateGraph

    public Vertex getVertexAtPoint(int x,int y){
	GraphNormal g = this.getGraph();
	int v = g.getVertices().size();
	Vertex vertex=null;
	// Check each vertex to find out if it's "near" that point
	for(int i=0;i<v;i++){
		Vertex thisVertex = g.getVertex(i);
		int vX = thisVertex.getX();
		int vY = thisVertex.getY();
		// Each vertex circle has center vX,vY, radius 5, so see if the point is in such a circle
		if((Math.abs(vX-x)<=5)&&(Math.abs(vY-y)<=5)){
			// if its in that range, this is the vertex the user clicked
			vertex=thisVertex;
			break;
		} // end if
	} // end for
	return vertex;
    } // end getVertexAtPoint

    // Make sure the panel is ready for a new vertex
    public void addNewVertexListener(){
	System.out.println("adding new vertex listener...");
	this.addMouseListener(new NewVertexClickListener());
    } // end addNewVertexListener

    // Make sure the panel is ready for a new edge
    public void addEdgeListener(){
	System.out.println("adding new edge listener...");
	this.addMouseListener(new FirstVertexListener());
    } // end addEdgeListener

    public void addSecondVertexListener(Vertex v){
	System.out.println("Adding a listener for the second vertex...");
	this.addMouseListener(new SecondVertexListener(v));
    }

    // This listener adds a new vertex to the graph when clicked on. It is a one-use-only listener
    class NewVertexClickListener extends MouseAdapter{
	boolean notFired = true;
	// When clicked, a vertex should appear
	public void mouseClicked(MouseEvent e){
		// Add a new vertex at the point
		if(notFired){
			System.out.println("Adding a vertex!!");
			// Add a new vertex
			int x = e.getX();
			int y = e.getY();
			graph.addVertex(new Vertex(x,y));
			// Draw the new vertex
			repaint();
		} // end if
		// Make sure that more vertices aren't added
		notFired = false;
	} // end mouseClicked
    }

    // this listener waits for a click indicating the first vertex of the new edge being added
    class FirstVertexListener extends MouseAdapter{
	// It's super important that this isn't fired again, since we will be getting a second vertex
	boolean notFired=true;
	public void mouseClicked(MouseEvent e){
		if(notFired){
			// get the location of the event
			int x = e.getX();
			int y = e.getY();
			// Get the vertex near that point
			Vertex v1 = getVertexAtPoint(x,y);
			if(v1!=null){
				System.out.println("First vertex of new edge selected");
				// Add the second listener to the panel by invoking a method of the panel, since we can't access an instance directly
				addSecondVertexListener(v1);
				// make sure its never fired again
				notFired = false;
			} // end if
		} // end if
	} // end mouseClicked
    } // end FirstVertexListener

    // This listener will actually add the edge once a second vertex has been clicked
    class SecondVertexListener extends MouseAdapter{
	// The second vertex needs to have a first vertex in order to add the edge
	Vertex v1;
	boolean notFired=true;
	public SecondVertexListener(Vertex v){
		super();
		this.v1 = v;
	} // end constructor

	public void mouseClicked(MouseEvent e){
		if(notFired){
			int x = e.getX();
			int y = e.getY();
			Vertex v2 = getVertexAtPoint(x,y);
			if(v2!=null){
				System.out.println("Second vertex of new edge selected");
				// get the vertex that's "near" that point
				// add the complete edge to the graph
				graph.addEdge(v1,v2);
				// repaint the panel
				repaint();
				// ensure that the same listener never does anything again
				notFired = false;
			} // end if
		} // end if
	} // end mouseClicked
    } // end SecondVertexListener

    class DragVertexListener extends MouseAdapter{
	Vertex currentVertex = null;
	public void mousePressed(MouseEvent e){
		// Get the vertex at the point where they clicked on a vertex
		currentVertex = getVertexAtPoint(e.getX(),e.getY());
		updateVertex(e);
	} // end mousePressed

	public void mouseDragged(MouseEvent e){
		updateVertex(e);
	} // end mouseDragged

	public void mouseReleased(MouseEvent e){
		updateVertex(e);
	} // end mouseReleased

	public void updateVertex(MouseEvent e){
		if(currentVertex!=null){
			currentVertex.setCoordinates(e.getX(),e.getY());
			repaint();
		} // end if
	} // end updateVertex
    } // end dragVertexListener

    public void paintComponent(Graphics g){
	// paint the graph
	// First clear the canvas by painting it all with the background color
	g.setColor(this.getBackground());
	g.fillRect(0,0,this.getWidth(),this.getHeight());
	GraphNormal graph = this.getGraph();

	// Now draw all the vertices
	for(int i=0;i<graph.getVertices().size();i++){
		Vertex currentV = graph.getVertex(i);
		g.setColor(Color.RED);
		int vX = currentV.getX();
		int vY = currentV.getY();
		g.fillOval(vX-5,vY-5,10,10);

	} // end for

	// Draw all the edges
	for(int i=0;i<graph.getEdges().size();i++){

		Edge edge = graph.getEdge(i);
		Vertex V1 = edge.getV1();
		Vertex V2 = edge.getV2();
		// Paint a circle at each vertex and a line for each edge
		int vX1 = V1.getX();
		int vX2 = V2.getX();
		int vY1 = V1.getY();
		int vY2 = V2.getY();

		g.setColor(Color.BLACK);
		g.drawLine(vX1,vY1,vX2,vY2);
	} // end for
    } // end paintComponent

    // getter
    public GraphNormal getGraph(){
	return this.graph;
	} // end getter

  // FILE IO
  // Store a graph
  public void storeGraph(){
    // Put the current graph in a file
    try{
      FileOutputStream file = new FileOutputStream("Graphs.dat");
      ObjectOutputStream objectFile = new ObjectOutputStream(file);
      objectFile.writeObject(this.getGraph());

    }catch(Exception e){
      System.out.println(e.getMessage());
      System.out.println("Saving data failed");
    } // end catch

  } // end storeGraph
/***
  public void loadGraph(){
      try{
        // ArrayList savedGraphs = new ArrayList();
        FileInputStream file = new FileInputStream("Graphs.dat");
        while(objectFile.hasNext())

      }catch(Exception e){


      } // end catch


  } // end loadGraph
***/

    // Old method for when vertices were buttons
    /***
    public void paintVertices(){
	// Add all the vertices to the graph
	GraphNormal g = this.graph;

    } // end paintVertices
    ***/
} // end GraphPanel
