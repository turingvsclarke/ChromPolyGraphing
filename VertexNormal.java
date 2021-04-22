// Vertex.java
// This class will be a class of all our vertices.
import java.util.*;
import java.io.*;
import java.util.ArrayList;

public class VertexNormal implements Cloneable{

	private final int x;
	private final int y;

	public ArrayList neighbors = new ArrayList();

	static Scanner input = new Scanner(System.in);

	public static void main(String[] args){

		Vertex V1 = new Vertex(0,1);
		Vertex V2 = new Vertex(1,2);
		ArrayList vertices = new ArrayList();
		// Testing that we can give our class some vertices
		for(int i=0;i<4;i++){
			System.out.println("Create a new vertex");
			System.out.println("Provide an x coordinate");
			String inputString = input.nextLine();
			int xcoordinate = Integer.parseInt(inputString);
			System.out.println("Provide a y coordinate");
			inputString = input.nextLine();
			int ycoordinate = Integer.parseInt(inputString);
			vertices.add(new Vertex(xcoordinate,ycoordinate));
		}

		System.out.println("Printing the coordinates...");
		for(int a=0;a<vertices.size();a++){
			System.out.print("Vertex " + a + ": ");
			((Vertex)vertices.get(a)).printCoordinates();
		}
	} // end main


	public VertexNormal(){
		x = 0;
		y = 0;
		// for testing purposes only
	} // end null constructor

	public VertexNormal(int x,int y){
		this.x = x;
		this.y = y;
	} // end all constructor

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public void printCoordinates(){
		int x = this.getX();
		int y = this.getY();
		System.out.println("("+x+","+y+")");
	}


}   // end class def
