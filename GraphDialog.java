// GraphDialog.java
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GraphDialog extends JDialog{
    JSpinner vSpin,eSpin;
    JButton submit = new JButton("Generate Graph");
    JLabel vLab,eLab;
    SpinnerNumberModel vModel,eModel;
    public int vertexSize;
    public int edgeSize;

    public GraphDialog(){
        this.setSize(400,300);
        this.setResizable(false);
        this.setLayout(new GridLayout(3,1));
        // Spinner for submission
        JPanel vPane = new JPanel();
        vLab = new JLabel("Vertices:");
        // Set the model for the JSpinner(controls its possible values)
        vModel = new SpinnerNumberModel();
        vModel.setMinimum(0);
        vSpin = new JSpinner(vModel);
        vSpin.addInputMethodListener(new VchangeListener());
        vPane.add(BorderLayout.WEST,vLab);
        vPane.add(BorderLayout.EAST,vSpin);

        this.add(vPane);
        // Create a place for edge input
        JPanel ePane = new JPanel();
        eLab = new JLabel("Edges:");
        eModel = new SpinnerNumberModel(0,0,0,1);
        eSpin = new JSpinner(eModel);
        eSpin.addInputMethodListener(new EchangeListener());
        ePane.add(BorderLayout.WEST,eLab);
        ePane.add(BorderLayout.EAST,eSpin);
        this.add(ePane);
        this.add(submit);

    } // end GraphDialog

    class EchangeListener implements InputMethodListener{
      public void inputMethodTextChanged(InputMethodEvent e){
          setEdgeSize((int)eModel.getNumber());
      } // end inputMethodTextChanged
      public void caretPositionChanged(InputMethodEvent a){}
    } // end EchangeListener

    class VchangeListener implements InputMethodListener{
      public void inputMethodTextChanged(InputMethodEvent e){
          int currentV = (int)(vModel.getNumber());
          int vMath = ((currentV)*(currentV-1))/2;
          setVertexSize(currentV);
          eModel.setMaximum(vMath);
      } // end inputMethodTextChanged
      public void caretPositionChanged(InputMethodEvent a){}
    } // end VchangeListener

    public void setVertexSize(int vsize){
      this.vertexSize = vsize;
    }

    public void setEdgeSize(int esize){
      this.edgeSize = esize;
    }

    public int getVertexSize(){
      return this.vertexSize;
    } // end getVertexSize

    public int getEdgeSize(){
      return this.edgeSize;
    } // end getEdgeSize


} // end GraphDialog
