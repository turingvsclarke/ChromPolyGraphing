runGUI: FinalProjectGUI.class
	java FinalProjectGUI

FinalProjectGUI.class: FinalProjectGUI.java BasicSwing.class
	javac FinalProjectGUI.java

BasicSwing.class: BasicSwing.java
	javac BasicSwing.java
