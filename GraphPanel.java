// GraphPanel.java
import javax.swing.*;
import java.awt.*;
public class GraphPanel extends JPanel{

    GraphNormal graph;

    public static void main(String[] args) {

    }

    public GraphPanel(){
	this.graph = new GraphNormal();
    } // end constructor

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

		g.drawLine(vX1,vY1,vX2,vY2);
	} // end for

    } // end paintComponent

} // end GraphPanel
