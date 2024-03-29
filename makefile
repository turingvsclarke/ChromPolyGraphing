jar: FinalProjectGUI.class
	jar cvfm ChromPolyGraph.jar manifest.txt *.class

runGUI: FinalProjectGUI.class
	java FinalProjectGUI
	make clean

runGraph: GraphNormal.class
	java GraphNormal
	make clean

debugGraph: GraphNormal.class
	jdb GraphNormal

GraphNormal.class: GraphNormal.java Vertex.class Edge.class Polynomial.class
	javac -g GraphNormal.java

Polynomial.class: Polynomial.java
	javac -g Polynomial.java

Vertex.class: Vertex.java
	javac -g Vertex.java

Edge.class: Edge.java
	javac -g Edge.java

FinalProjectGUI.class: FinalProjectGUI.java BasicSwing.class GraphPanel.class
	javac FinalProjectGUI.java

BasicSwing.class: BasicSwing.java
	javac -g BasicSwing.java

GraphPanel.class: GraphPanel.java
	javac -g GraphPanel.java

clean: 
	rm *.class
