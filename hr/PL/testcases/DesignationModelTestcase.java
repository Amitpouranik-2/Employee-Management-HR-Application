import java.awt.*;
import javax.swing.*;
import com.Employee.Management.hr.pl.model.*;
import com.Employee.Management.hr.bl.exceptions.*;

class DesignationModelTestcase extends JFrame
{
private JTable tb;
private DesignationModel designationModel;
private Container container;
private JScrollPane jsp;



DesignationModelTestcase()
{
designationModel = new DesignationModel();
tb  = new JTable( designationModel);
jsp = new  JScrollPane( tb , ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS , ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

container = getContentPane();
container.setLayout( new BorderLayout());

container.add(jsp);

setLocation( 100 , 200 );
setSize( 500 , 400);
setVisible( true);



}

public static void main ( String gg[])
{
 
DesignationModelTestcase dmtc = new DesignationModelTestcase();



}
}