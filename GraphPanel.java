// GraphPanel.java
import javax.swing.*;
import java.awt.*;
public class GraphPanel extends JPanel{
    int height;
    int width;
    GraphNormal graph;
    public static void main(String[] args) {

    }

    public GraphPanel(){
	// create a random graph with 10 vertices and 3 edges
	this.graph = new GraphNormal(100,100);
	this.height = this.getHeight();
	this.width = this.getWidth();
	this.setLayout(null);
	this.paintVertices();
    } // end constructor

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

	
    public void paintComponent(Graphics g){
	// paint the graph

	// Draw all the edges
	for(int i=0;i<graph.getEdges().size();i++){
		Edge edge = graph.getEdge(i);
		Vertex V1 = edge.getV1();
		Vertex V2 = edge.getV2();

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

} // end GraphPanel
