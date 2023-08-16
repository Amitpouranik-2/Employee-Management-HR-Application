package com.Employee.Management.hr.pl.ui;
import com.Employee.Management.hr.pl.model.*;
import com.Employee.Management.hr.bl.exceptions.*;
import com.Employee.Management.hr.bl.interfaces.pojo.*;
import com.Employee.Management.hr.bl.pojo.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.io.*;


public class DesignationUI extends JFrame  implements DocumentListener , ListSelectionListener
{
private JLabel titleLabel;
private JLabel searchLabel;
private JTextField searchTextField; 
private JButton clearSearchTextFieldButton;
private JLabel searchErrorLabel;
private JTable designationTable;
private DesignationModel designationModel;
private Container container;
private JScrollPane scrollPane;
private DesignationPanel designationPanel;
private enum MODE { VIEW , ADD , EDIT , DELETE , EXPORT_TO_PDF};
private MODE mode;
private ImageIcon logoIcon;
private ImageIcon addIcon;
private ImageIcon editIcon;
private ImageIcon deleteIcon;
private ImageIcon cancelIcon;
private ImageIcon pdfIcon;
private ImageIcon saveIcon;
private ImageIcon crossIcon;


public DesignationUI()
{
initComponents();
setAppearance();
addListeners(); 
setViewMode();
designationPanel.setViewMode();
}

private void initComponents()
{
logoIcon = new ImageIcon( this.getClass().getResource("/icons/logo.png"));
addIcon = new ImageIcon(this.getClass().getResource("/icons/add.png"));
editIcon = new ImageIcon( this.getClass().getResource("/icons/edit.png"));
deleteIcon = new ImageIcon( this.getClass().getResource("/icons/delete.png"));
cancelIcon = new ImageIcon( this.getClass().getResource("/icons/cancel.png"));
pdfIcon = new ImageIcon( this.getClass().getResource("/icons/pdf.png"));
saveIcon = new ImageIcon( this.getClass().getResource("/icons/save.png"));
crossIcon = new ImageIcon( this.getClass().getResource("/icons/cross.png"));

setIconImage(logoIcon.getImage());
designationModel = new DesignationModel();
titleLabel = new JLabel("Designations");
searchLabel = new JLabel("Search");
searchTextField = new JTextField();
clearSearchTextFieldButton = new JButton(crossIcon);
searchErrorLabel =  new JLabel("");  
designationTable = new JTable( designationModel);
scrollPane = new  JScrollPane( designationTable , ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS , ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
container = getContentPane();
}


public void setAppearance()
{
Font titleFont = new Font( "Verdana" , Font.BOLD , 18);
Font captionFont = new Font("Verdana" , Font.BOLD , 16);
Font dataFont = new Font("Verdana" , Font.PLAIN, 16);
Font searchErrorFont = new Font("Verdana" , Font.BOLD , 12 );
Font columnHeaderFont = new Font( "Verdana" , Font.PLAIN , 16);

titleLabel.setFont( titleFont);
searchLabel.setFont( captionFont);
clearSearchTextFieldButton.setFont( dataFont);
searchErrorLabel.setFont( searchErrorFont);
searchErrorLabel.setForeground( Color.red);
designationTable.setFont( dataFont);
designationTable.setRowHeight(35);
designationTable.getColumnModel().getColumn(0).setPreferredWidth(20);  
designationTable.getColumnModel().getColumn(1).setPreferredWidth(400);  
JTableHeader header = designationTable.getTableHeader();
header.setFont( columnHeaderFont);
header.setReorderingAllowed(false);
header.setResizingAllowed(false);

designationTable.setRowSelectionAllowed( true);
designationTable.setSelectionMode( ListSelectionModel.SINGLE_SELECTION);
designationPanel = new DesignationPanel();

container.setLayout(null);
int lm , tm;
lm =0;
tm =0;

titleLabel.setBounds(lm+10 , tm+10 , 200 , 40);
searchErrorLabel.setBounds( lm+10+100+400+10-75 , tm+10+20+10 , 100   ,20 );
searchLabel.setBounds( lm+10 , tm+10+40+10 , 100 , 30);
searchTextField.setBounds( lm+10+100+5 , tm+10+40 +10, 400 , 30 );
clearSearchTextFieldButton.setBounds( lm+10+100+400+10 , tm+10+40+10 , 30 , 30);
scrollPane.setBounds( lm+10 , tm+10+40+10+30+10 , 565, 300);
designationPanel.setBounds( lm+10 , tm+10+40+10+30+300+20 , 562 , 180 );

container.add(titleLabel);
container.add(searchLabel);
container.add(searchErrorLabel);
container.add(searchTextField);
container.add(clearSearchTextFieldButton);
container.add(scrollPane);
container.add(designationPanel);
int w,h;
w = 600;
h = 600;
setSize( w , h);
Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
setLocation((d.width/2)-(w/2) , (d.height/2) - ( h/2));
}


private void addListeners()
{
searchTextField.getDocument().addDocumentListener(this);
clearSearchTextFieldButton.addActionListener( new  ActionListener()
{
public void actionPerformed( ActionEvent ev)
{
searchTextField.setText("");
searchTextField.requestFocus();
}
});
designationTable.getSelectionModel().addListSelectionListener(this);
}




public void searchDesignation()
{
searchErrorLabel.setText("");
String title = searchTextField.getText().trim();
int rowIndex;
if( title.length() == 0) return;
try
{
rowIndex = designationModel.indexOfTitle( title , true);
Rectangle rectangle = designationTable.getCellRect( rowIndex , 0 , true);
designationTable.scrollRectToVisible(rectangle);
}catch( BLException blexception)
{
searchErrorLabel.setText(" Not Found");
return;
}
designationTable.setRowSelectionInterval(rowIndex , rowIndex);
}



public void changedUpdate( DocumentEvent de)
{
searchDesignation();
}
public void insertUpdate( DocumentEvent de)
{
searchDesignation();
}
public void removeUpdate( DocumentEvent de)
{
searchDesignation();
}
public void valueChanged( ListSelectionEvent ev)
{
int selectedRowIndex = designationTable.getSelectedRow();
try
{
DesignationInterface designation = designationModel.getDesignationAt( selectedRowIndex);
designationPanel.setDesignation( designation);
}catch( BLException blException)
{
designationPanel.clearDesignation();
}
}



private void setViewMode()
{
this.mode = MODE.VIEW;
if( designationModel.getRowCount() ==  0 )
{
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled( false);
designationTable.setEnabled( false);
}
else
{
searchTextField.setEnabled(true);
 clearSearchTextFieldButton.setEnabled( true);
designationTable.setEnabled( true);
}
}



private void setAddMode()
{
this.mode = MODE.ADD;
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled( false);
designationTable.setEnabled( false);
}

private void setEditMode()
{
this.mode = MODE.EDIT;
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled( false);
designationTable.setEnabled( false);
}


private void setDeleteMode()
{
this.mode = MODE.DELETE;
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled( false);
designationTable.setEnabled( false);
}

private void setExportToPDFMode()
{
this.mode = MODE.EXPORT_TO_PDF;
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled( false);
designationTable.setEnabled( false);
}







class DesignationPanel extends JPanel
{
private JLabel titleCaptionLabel;
private JLabel titleLabel;
private JTextField titleTextField;
private JButton cleartitleTextFieldButton;
private JButton addButton;
private JButton editButton;
private JButton cancelButton;
private JButton deleteButton;
private JButton exportToPDFButton;
private JPanel  buttonPanel;
private DesignationInterface designation;
DesignationPanel()
{
setBorder( BorderFactory.createLineBorder( new Color( 175 , 175, 175)));
initComponents();
setAppearance();
addListeners(); 
}
public void setDesignation( DesignationInterface designation)
{
this.designation = designation;
titleLabel.setText( designation.getTitle());
} 
public void clearDesignation()
{
this.designation = null;
titleLabel.setText("");
}
private void initComponents()
{
designation = null;
titleCaptionLabel = new JLabel("Designation");
titleLabel = new JLabel("");
titleTextField = new JTextField();
cleartitleTextFieldButton = new JButton(crossIcon);
buttonPanel = new JPanel();
addButton = new JButton(addIcon);
editButton = new JButton(editIcon);
cancelButton = new JButton(cancelIcon);
deleteButton = new JButton(deleteIcon);
exportToPDFButton = new JButton(pdfIcon);
}
private void setAppearance()
{
Font captionFont = new Font( "Verdana" , Font.BOLD , 16 );
Font dataFont = new Font(" Verdana" , Font.PLAIN , 16);
titleCaptionLabel.setFont( captionFont);
titleLabel.setFont( dataFont);
titleTextField.setFont( dataFont);
setLayout(null);
int lm , tm;
lm = 0;
tm = 0;
titleCaptionLabel.setBounds( lm + 3  , tm+20 , 110 , 30);
titleLabel.setBounds( lm +110+5 , tm+20 , 400 , 30);
titleTextField.setBounds( lm+10+110+5 , tm+20 , 350 , 30);
cleartitleTextFieldButton.setBounds( lm+10+110+5+350+5 , tm+ 20,  30 , 30 );
buttonPanel.setBounds( 50 , tm+20+30+30 , 465, 75);
buttonPanel.setBorder( BorderFactory.createLineBorder( new Color ( 165 , 165 , 165 )));

addButton.setBounds( 70, 12 , 50 , 50);
editButton.setBounds(70+50+20 , 12 , 50 , 50);
cancelButton.setBounds( 70+50+20+50+20 , 12 , 50 , 50);
deleteButton.setBounds( 70+50+20+50+20+50+20 , 12 , 50 , 50 );
exportToPDFButton.setBounds( 70+50+20+50+20+50+20+50+20 , 12 , 50 , 50);

buttonPanel.setLayout(null);
buttonPanel.add( addButton);
buttonPanel.add( editButton);
buttonPanel.add( cancelButton);
buttonPanel.add( deleteButton);
buttonPanel.add( exportToPDFButton);

add( titleCaptionLabel);
add( titleTextField);
add( titleLabel);
add( cleartitleTextFieldButton);
add( buttonPanel);
}

private boolean addDesignation()
{
String title = titleTextField.getText().trim();
if( title.length() == 0)
{
JOptionPane.showMessageDialog( this , "Designation Required " );
titleTextField.requestFocus();
return false;
}
DesignationInterface d = new Designation();
d.setTitle( title);
try
{
designationModel.add(d); 
int rowIndex = 0;
try
{
rowIndex = designationModel.indexOfDesignation(d);
}catch(  BLException blException)
{
// do nothing
}
designationTable.setRowSelectionInterval( rowIndex , rowIndex);
Rectangle rectangle = designationTable.getCellRect( rowIndex , 0 , true);
designationTable.scrollRectToVisible( rectangle);
return true;
}catch( BLException blException)
{
if(blException.hasGenericExceptions())
{
JOptionPane.showMessageDialog(this , blException.getGenericException());
}
else
{
if( blException.hasExceptions("title"))
{
JOptionPane.showMessageDialog(this , blException.getException("title"));
}
}
titleTextField.requestFocus();
return false;
}
}

private boolean updateDesignation()
{
String title = titleTextField.getText().trim();

if( title.length() == 0)
{
JOptionPane.showMessageDialog( this , "Designation Required " );
titleTextField.requestFocus();

return false;
}
DesignationInterface d = new Designation();
d.setCode(this.designation.getCode());

d.setTitle( title);
try
{
designationModel.update(d);
int rowIndex = 0;
try
{
rowIndex = designationModel.indexOfDesignation(d);
}catch(  BLException blException)
{
// do nothing
}
designationTable.setRowSelectionInterval( rowIndex , rowIndex);
Rectangle rectangle = designationTable.getCellRect( rowIndex , 0 , true);
designationTable.scrollRectToVisible( rectangle);
return true;
}catch( BLException blException)
{
if(blException.hasGenericExceptions())
{
JOptionPane.showMessageDialog(this , blException.getGenericException());
}
else
{
if( blException.hasExceptions("title"))
{
JOptionPane.showMessageDialog(this , blException.getException("title"));
}
}
titleTextField.requestFocus();
return false;
}
}


private void removeDesignation()
{
try
{
String title = this.designation.getTitle();
int selectedOption = JOptionPane.showConfirmDialog( this , " Delete  "+  title  + "?" , "  Confirmation " ,JOptionPane.YES_NO_OPTION);
if( selectedOption == JOptionPane.NO_OPTION ) return;
designationModel.remove( this.designation.getCode());
JOptionPane.showMessageDialog( this ,   title  + " deleted");
}catch( BLException blException)
{
if(blException.hasGenericExceptions())
{
JOptionPane.showMessageDialog(this , blException.getGenericException());
}
else
{
if( blException.hasExceptions("title"))
{
JOptionPane.showMessageDialog(this , blException.getException("title"));
}
}
}
}



private void addListeners()
{
this.addButton.addActionListener( new ActionListener()
{
public void actionPerformed( ActionEvent ev)
{
if( mode == MODE.VIEW)
{
setAddMode();
}
else
{
if( addDesignation())
{
setViewMode();
}
}
}
});

this.editButton.addActionListener( new ActionListener(
)
{
public void actionPerformed( ActionEvent ev)
{
if( mode == MODE.VIEW)
{
setEditMode();
}
else
{
if(updateDesignation())
{
setViewMode();
}
}
}
});

this.cancelButton.addActionListener( new ActionListener() {

public void actionPerformed( ActionEvent ev)
{
setViewMode();
}
});



this.deleteButton.addActionListener( new ActionListener ()
{

public void actionPerformed( ActionEvent ev)
{
setDeleteMode();
}
});



this.exportToPDFButton.addActionListener(  new ActionListener()
{
public void actionPerformed( ActionEvent ev)
{
JFileChooser jfc = new JFileChooser();
jfc.setCurrentDirectory( new  File ("."));
int selectedOption = jfc.showSaveDialog(DesignationUI.this);
if( selectedOption == JFileChooser.APPROVE_OPTION)
{
try
{
File selectedFile = jfc.getSelectedFile();
String pdfFile = selectedFile.getAbsolutePath();
if( pdfFile.endsWith(".")) pdfFile += "pdf";
else if( pdfFile.endsWith(".pdf") == false ) pdfFile += ".pdf";

File file = new File(pdfFile);
File parent = new File(file.getParent());
if( parent.exists() == false || parent.isDirectory() == false)
{
JOptionPane.showMessageDialog( DesignationUI.this , " Incorrect path :" + file.getAbsolutePath());
return;
}
designationModel.exportToPDF(file);
JOptionPane.showMessageDialog( DesignationUI.this , " Data Exported to  :" + file.getAbsolutePath());
}catch ( Exception e)
{
// do nothing
}
}
}
});

} // add listener braces

void setViewMode()
{
DesignationUI.this.setViewMode();
this.addButton.setIcon(addIcon);
this.editButton.setIcon(editIcon);
this.titleTextField.setVisible( false);
this.titleLabel.setVisible( true);
this.addButton.setEnabled( true);
this.cancelButton.setEnabled( false);
this.cleartitleTextFieldButton.setVisible(false);
if( designationModel.getRowCount() > 0)
{
this.editButton.setEnabled(true);
this.deleteButton.setEnabled(true);
this.exportToPDFButton.setEnabled(true);
}
else
{
this.editButton.setEnabled(false);
this.deleteButton.setEnabled(false);
this.exportToPDFButton.setEnabled(false);
}
}

void setAddMode()
{
DesignationUI.this.setAddMode();
this.titleTextField.setText("");
this.titleLabel.setVisible( false);
this.cleartitleTextFieldButton.setVisible( true);
this.titleTextField.setVisible( true);
addButton.setIcon(saveIcon);
editButton.setEnabled(false);
cancelButton.setEnabled( true);
deleteButton.setEnabled( false);
exportToPDFButton.setEnabled(false);
}

void setEditMode()
{
if( designationTable.getSelectedRow() < 0 || designationTable.getSelectedRow() >= designationModel.getRowCount())
{
JOptionPane.showMessageDialog( this , " Select Designation to Edit");
return;
}
DesignationUI.this.setEditMode();
this.titleTextField.setText( this.designation.getTitle());
this.titleLabel.setVisible( false);
this.titleTextField.setVisible( true);
this.cleartitleTextFieldButton.setVisible(false);
addButton.setEnabled( false);
cancelButton.setEnabled( true);
deleteButton.setEnabled( false);
exportToPDFButton.setEnabled(false);
editButton.setIcon(saveIcon);
}


void setDeleteMode()
{
if( designationTable.getSelectedRow() < 0 || designationTable.getSelectedRow() >= designationModel.getRowCount())
{
JOptionPane.showMessageDialog( this , " Select Designation to delete ");
return;
}
DesignationUI.this.setDeleteMode();
addButton.setEnabled( false);
editButton.setEnabled( false);
cancelButton.setEnabled( false);
deleteButton.setEnabled( false);
exportToPDFButton.setEnabled(false);
removeDesignation();
DesignationUI.this.setViewMode();
this.setViewMode();
}

void setExportToPDFMode()
{
DesignationUI.this.setExportToPDFMode();
addButton.setEnabled( false);
editButton.setEnabled( false);
cancelButton.setEnabled( false);
deleteButton.setEnabled( false);
exportToPDFButton.setEnabled(false);
}
}
}
