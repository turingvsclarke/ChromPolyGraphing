// VertexButton.java
// A template for the buttons(vertices) we're going to use for the project
import javax.swing.*;
import java.awt.*;

public class VertexButton extends JButton{

	public VertexButton(){
		super();
		this.setSize(5,5);
	} // end constructor

	public void paintComponent(Graphics g){
		// paint a red circle on the button
		g.setColor(Color.RED);
		
		int h = this.getHeight();
		int w = this.getWidth();
		Point lCorner = this.getLocation();
		int x = (int)lCorner.getX();
		int y = (int)lCorner.getY();
		g.fillOval(x,y,this.getHeight(),this.getWidth());

	} // end paintComponent



} // end VertexButton
