runGUI: FinalProjectGUI.class
	java FinalProjectGUI

runGraph: Graph.class
	java Graph
debugGraph: Graph.class
	jdb Graph

Graph.class: Graph.java Vertex.class
	javac -g Graph.java

Vertex.class: Vertex.java
	javac Vertex.java

FinalProjectGUI.class: FinalProjectGUI.java BasicSwing.class
	javac FinalProjectGUI.java

BasicSwing.class: BasicSwing.java
	javac BasicSwing.java

clean: 
	rm *.class
