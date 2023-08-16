import com.Employee.Management.hr.bl.exceptions.*;
import com.Employee.Management.hr.bl.interfaces.managers.*;
import com.Employee.Management.hr.bl.managers.*;
import com.Employee.Management.hr.bl.interfaces.pojo.*;
import com.Employee.Management.hr.bl.pojo.*;
import java.util.*;

class DesignationManagerUpdateTestcase 
{
public static void main (String gg[] )
{

DesignationInterface designation = new Designation();

designation.setCode(3);
designation.setTitle("clerk");

try
{

DesignationManagerInterface designationManager;
designationManager =  DesignationManager.getDesignationManager();
designationManager.updateDesignation(designation);

System.out.println("Designation Updated .........");






}catch(BLException blException)
{
if(blException.hasGenericExceptions())
{
System.out.println(blException.getGenericException());
}
List<String> properties = blException.getProperties();
for(String property : properties)
{
System.out.println(blException.getException(property));
}

}







}

}