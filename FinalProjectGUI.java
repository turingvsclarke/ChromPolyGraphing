// finalProjectGUI.java
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FinalProjectGUI extends BasicSwing{
	GraphPanel gpanel = null;
	JButton addVertex, addEdge, getChromPoly, simplifyChromPoly;
	
	public static void main(String[] args){
		FinalProjectGUI myGUI = new FinalProjectGUI();		
	} // end main
	
	public FinalProjectGUI(){
		super("Chromatic Polynomial Simulation");
		this.contentArea = this.getContentPane();
	
		// Add a panel onto the frame

		gpanel = new GraphPanel();
		gpanel.setSize(300,400);
		this.contentArea.add(BorderLayout.CENTER,gpanel);

		// Add the panel will all control buttons on to the left side of the screen
		ButtonPanel bpanel = new ButtonPanel();
		this.contentArea.add(BorderLayout.WEST,bpanel);
	} // end constructor

	// Create a panel with all the buttons for the GUI
	class ButtonPanel extends JPanel{
		public ButtonPanel(){
			// Add a button onto the frame for adding vertices and add something to listen for it
			addVertex = new JButton("Add new vertex");
			addVertex.addActionListener(new newVertexListener());
			this.add(addVertex);

			// Add a button for adding edges and add a listener for it
			addEdge = new JButton("Add a new edge to the graph");
			addEdge.addActionListener(new newEdgeListener());
			this.add(addEdge);

		} // end constructor
	} // end ButtonPanel class 

	// This listener waits for the addVertex button to be clicked, then tells the graphPanel to get ready for a new vertex
	class newVertexListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			// add a new listener to the graphPanel
			gpanel.addNewVertexListener();
		} // end actionPerformed
	} // end newVertexListener

	// This listener waits for the addEdge button to be clicked, then tells the graphPanel to get ready for a new edge
	class newEdgeListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			// add a new listener to the graphPanel
			gpanel.addEdgeListener();
		} // end ActionPerformed
	} // end newEdgeListener

	// Create a panel to add on to the bottom of the GUI for displaying the polynomial
	class PolyPanel{



	} // end PolyPanel


} // end finalProjectGUI
