runGUI: FinalProjectGUI.class
	java FinalProjectGUI

runGraph: Graph.class
	java Graph

Graph.class: Graph.java Vertex.class
	javac Graph.java

Vertex.class: Vertex.java
	javac Vertex.java

FinalProjectGUI.class: FinalProjectGUI.java BasicSwing.class
	javac FinalProjectGUI.java

BasicSwing.class: BasicSwing.java
	javac BasicSwing.java

