// finalProjectGUI.java
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FinalProjectGUI extends BasicSwing{
	GraphPanel gpanel = null;
	JButton addVertex, addEdge, getChromPoly, getPoly, simplifyChromPoly, generateGraph, clearGraph, solvePolyNomial;
	JLabel polyDisplay,solution;
	SpinnerNumberModel sModel=new SpinnerNumberModel();
	GraphDialog d=new GraphDialog();
	SolveDialog s = new SolveDialog();

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
		pPanel.setSize(this.getWidth(),200);
		this.repaint();
	} // end constructor

	// Create a panel with all the buttons for the GUI
	class ButtonPanel extends JPanel{
		public ButtonPanel(){
			super();

			this.setLayout(new GridLayout(5,1));
			// this.setLayout(new GridLayout(6,1));
			// Add a button onto the frame for adding vertices and add something to listen for it
			addVertex = new JButton("Add new vertex");
			addVertex.addActionListener(new newVertexListener());
			this.add(addVertex);

			// Add a button for adding edges and add a listener for it
			addEdge = new JButton("Add a new edge to the graph");
			addEdge.addActionListener(new newEdgeListener());
			this.add(addEdge);
			/***
			JButton storeGraph = new JButton("Save Graph");
			storeGraph.addActionListener(new GraphSaveListener());
			this.add(storeGraph);
			***/
			generateGraph = new JButton("Generate a random graph");
			generateGraph.addActionListener(new randomGraphListener());
			this.add(generateGraph);
			this.setVisible(true);
			this.repaint();

			clearGraph = new JButton("Clear Screen");
			clearGraph.addActionListener(new graphEraseListener());
			this.add(clearGraph);

			solvePolyNomial = new JButton("Solve Polynomial");
			solvePolyNomial.addActionListener(new makeSolveDialog());
			this.add(solvePolyNomial);
		} // end constructor

	} // end ButtonPanel class

	// Create a panel to add on to the bottom of the GUI for displaying the polynomial
	class PolyPanel extends JPanel{
		JButton getPoly;

		public PolyPanel(){
			// Put a border on it
			this.setLayout(new GridLayout(1,3));
			// Put a border on it
			this.setBorder(BorderFactory.createLineBorder(Color.black));
			// Put a button on the left side

			getPoly = new JButton("Display Chromatic Polynomial");
			getPoly.addActionListener(new GetPolyListener());
			this.add(BorderLayout.WEST,getPoly);

			polyDisplay = new JLabel();
			polyDisplay.setSize(300,200);
			polyDisplay.setBorder(BorderFactory.createLineBorder(Color.black));
			this.add(BorderLayout.CENTER,polyDisplay);
			this.setVisible(true);
			this.repaint();
		} // end constructor

	} // end PolyPanel class def

	class SolveDialog extends JDialog{
			public SolveDialog(){
				this.setSize(400,300);
        this.setResizable(false);
				this.setLayout(new FlowLayout());
				JSpinner input = new JSpinner(sModel);
				solution = new JLabel();
				this.add(input);
				this.add(solution);
				JButton getSolution = new JButton("Solve");
				getSolution.addActionListener(new SolvePolynomialListener());
				this.add(getSolution);
		} // end constructor
	} // end solveDialog



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

	class randomGraphListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			d.submit.addActionListener(new randomGraphGeneration());
			d.setVisible(true);

		} // end actionPerformed
	} // end randomGraphListener

	class randomGraphGeneration implements ActionListener{
		public void actionPerformed(ActionEvent e){
				// Get the graph dialog values and send them to the gpanel
				int vertexSize = d.getVertexSize();
				int edgeSize = d.getEdgeSize();
				gpanel.generateGraph(vertexSize,edgeSize);
				d.setVisible(false);
		} // end actionPerformed
	} // end randomGraphGeneration

	class graphEraseListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			// Reset the graph
			gpanel.generateGraph(0,0);
		} // end actionPerformed
	} // end graphEraseListener

	class GraphSaveListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			// Reset the graph
			gpanel.storeGraph();
		}
	} // end GraphLoadListener

	class GetPolyListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				// Convert the polynomial from the graph into a string
				String polynomial = gpanel.getGraph().getChromPoly().getPolyString("q");
				polyDisplay.setText(polynomial);
			} // end actionPerformed
	} // end GetPolyListener

	class makeSolveDialog implements ActionListener{
		public void actionPerformed(ActionEvent e){
			s.setVisible(true);
		}
	} // end makeSolveDialog

	class SolvePolynomialListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				int sol = gpanel.getGraph().getChromPoly().solvePoly((int)sModel.getNumber());
				solution.setText("Solution: " + sol);
			}  // end actionPerformed
	} // end SolvePolynomialListener



} // end finalProjectGUI
