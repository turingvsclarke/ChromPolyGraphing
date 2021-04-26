// Vertex.java
// This class will be a class of all our vertices.
import java.util.*;
import java.io.*;

public class Vertex implements Cloneable{

	private int x;
	private int y;
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args){
		Vertex v = new Vertex(0,1);
		v.testCloning();
	}

	public void testCloning(){
		Vertex v2 = this.clone();
		if(this.equals(v2)){
			System.out.println("Cloning did not work!");
		} // end if
		else{
			System.out.println("Cloning worked!!");
		} // end else

	} // end testCloning

	public Vertex(int x,int y){
		this.setCoordinates(x,y);
	} // end constructor

	public Vertex clone(){
		Vertex clone = new Vertex(this.getX(),this.getY());
	     	return clone;
	} // end clone

	public int getX(){
		return x;
	} // end getX

	public int getY(){
		return y;
	} // end getY

	public void setCoordinates(int x,int y){
		this.x = x;
		this.y = y;
	} // end setCoordinates
	
	public void printCoordinates(){
		int x = this.getX();
		int y = this.getY();
		System.out.println("("+x+","+y+")");
	} // end printCoordinates

	public void testVertexClass(){
		// Testing that we can give our class some vertices
		ArrayList vertices = new ArrayList();
		for(int i=0;i<4;i++){
			System.out.println("Create a new vertex");
			System.out.println("Provide an x coordinate");
			String inputString = input.nextLine();
			int xcoordinate = Integer.parseInt(inputString);
			System.out.println("Provide a y coordinate");
			inputString = input.nextLine();
			int ycoordinate = Integer.parseInt(inputString);
			vertices.add(new Vertex(xcoordinate,ycoordinate));
		} // end for

		System.out.println("Printing the coordinates...");
		
		for(int a=0;a<vertices.size();a++){
			System.out.print("Vertex " + a + ": ");
			((Vertex)vertices.get(a)).printCoordinates();
		} // end for
	} // end testVertexClass


}   // end class def
