// finalProjectGUI.java
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FinalProjectGUI extends BasicSwing{

	public static void main(String[] args){
		FinalProjectGUI myGUI = new FinalProjectGUI();		
	} // end main
	
	public FinalProjectGUI(){
		super("Chromatic Polynomial Simulation");
		this.contentArea = this.getContentPane();
	
		// Add a panel onto the frame

		GraphPanel gpanel = new GraphPanel();
		gpanel.setSize(300,400);
		this.contentArea.add(BorderLayout.CENTER,gpanel);
	} // end FinalProjectGUI

} // end finalProjectGUI
