// Vertex.java
// This class will be a class of all our vertices.
import java.util.*;
import java.io.*;
import java.util.ArrayList;

public class Vertex implements Cloneable{

	private final int x;
	private final int y;

	public ArrayList<Vertex> neighbors = new ArrayList<Vertex>();

	static Scanner input = new Scanner(System.in);

	public static void main(String[] args){
		Vertex v1 = new Vertex(0,1);
		Vertex v2 = new Vertex(1,1);
		Vertex v3 = new Vertex(2,2);
		v1.addNeighbor(v2);
		v1.addNeighbor(v3);
		v2.addNeighbor(v3);
		v1.updateNeighbors();
		v2.updateNeighbors();
		v3.updateNeighbors();
		Vertex v4 = v1.clone();

		/***
		Vertex V1 = new Vertex(0,1);
		Vertex V2 = new Vertex(1,2);
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
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
		***/
	} // end main

	public Vertex clone(){
		try{
		// Start with a shallow copy of yourself(the list of neighbors will be the same)
		Vertex v2 = (Vertex)super.clone();

		// Make the clone's array of neighbors a shallow copy of your array of neighbors
		ArrayList neighbors = (ArrayList)this.getNeighbors().clone();
		v2.neighbors = neighbors;

		// Remove yourself from everyone else's list of neighbors
		for(int i=0;i<neighbors.size();i++){
			Vertex neighbor = (Vertex)neighbors.get(i);
			neighbor.removeNeighbor(this);
		}
		// Clone all your neighbors and add them to your clone's list of neighbors
		for(int vertex = 0; vertex<neighbors.size();vertex++){
			// check to see if you've already cloned your neighbor
			// pick the next neighbor on your list
			Vertex neighbor = (Vertex)neighbors.get(vertex);
			// check to see if any previous neighbors had that as a neighbor

				// if they do have that neighbor, grab your clone of that neighbor and transfer the neighbor
				if(previousNeighbor contains(neighbor)){

				}


			// clone THAT neighbor
			Vertex v = (Vertex)neighbor.clone();
			v2.getNeighbors().set(vertex,v);
		} // end while

		v2.updateNeighbors();

		return v2;
		}catch(Exception e){
			return null;
		}
	} // end clone


	public Vertex(){
		x = 0;
		y = 0;
		// for testing purposes only
	} // end null constructor

	public Vertex(int x,int y){
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

	public ArrayList<Vertex> getNeighbors(){
		return this.neighbors;
	}

	public Vertex firstNeighbor(){
		neighbors = this.getNeighbors();

		if(neighbors.size()>0){
			return (Vertex)neighbors.get(0);
		}
		else{
			return null;
		}

	} // end firstNeighbor

	// make sure all your neighbors count you as a neighbor
	public void updateNeighbors(){
		ArrayList neighbors = this.getNeighbors();
		for(int i=0;i<neighbors.size();i++){
			Vertex neighbor = (Vertex)neighbors.get(i);
			// add yourself to that neighbor's list of neighbors
			neighbor.addNeighbor(this);
		}
	} // end updateNeighbors

	public void addNeighbor(Vertex v){
		// Add a new neighbor if it isn't the current vertex or already a neighbor
		if((!this.equals(v)) && neighbors.indexOf(v)==-1){
			neighbors.add(v);
		}
	} // end addNeighbor

	public void removeNeighbor(Vertex v){
		this.neighbors.remove(v);
	} // end removeNeighbor

	public boolean hasNeighbors(){
		boolean hasNeighbors = true;
		if(this.firstNeighbor()==null){
			hasNeighbors = false;
		}
		return hasNeighbors;
	}

}   // end class def
