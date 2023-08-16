import java.awt.*;
import javax.swing.*;
import com.Employee.Management.hr.pl.model.*;
import com.Employee.Management.hr.bl.exceptions.*;

class eg1 extends JFrame
{
private JTable tb;
private DesignationModel designationModel;
private Container container;
private JScrollPane jsp;



eg1()
{
designationModel = new DesignationModel();
tb  = new JTable( designationModel);
Font font = new Font(" Times New Roman " , Font.PLAIN , 24);
tb.setFont( font);
tb.setRowHeight(30);

Color c = new Color( 100 , 255 , 208 );
tb.setBackground(c);


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