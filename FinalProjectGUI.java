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

		// Add the panel with all control buttons on to the left side of the screen
		ButtonPanel bpanel = new ButtonPanel();
		this.contentArea.add(BorderLayout.WEST,bpanel);

		// Add the panel handling the chromatic polynomial to the bottom of the screen
		PolyPanel pPanel = new PolyPanel();
		this.contentArea.add(BorderLayout.SOUTH,pPanel);
		
		
	} // end constructor

	// Create a panel with all the buttons for the GUI
	class ButtonPanel extends JPanel{
		public ButtonPanel(){
			super();
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

	// Create a panel to add on to the bottom of the GUI for displaying the polynomial
	class PolyPanel extends JPanel{
		JLabel polyDisplay;
		JButton getPoly;	
	
		public PolyPanel(){
			// Put a border on it
			this.setBorder(BorderFactory.createLineBorder(Color.black));
			// Put a button on the left side
			JButton getPoly = new JButton("Display Chromatic Polynomial");
			getPoly.addActionListener(new GetPolyListener());			

			// put a panel on the right that shows the polynomial
			String polynomial = gpanel.getGraph().getChromPoly();
			polyDisplay = new JLabel();
			polyDisplay.setSize(300,100);
			polyDisplay.setBorder(BorderFactory.createLineBorder(Color.black));
			this.add(BorderLayout.WEST, getPoly);
			this.add(BorderLayout.CENTER, polyDisplay);
		} // end constructor		

		class GetPolyListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				String polynomial = gpanel.getGraph().getChromPoly();
				polyDisplay.setText(polynomial);
			} // end ActionPerformed
		} // end GetPolyListener class def	
	} // end PolyPanel class def

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
} // end finalProjectGUI
