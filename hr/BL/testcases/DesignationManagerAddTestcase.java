import com.Employee.Management.hr.bl.managers.*;
import com.Employee.Management.hr.bl.interfaces.managers.*;
import com.Employee.Management.hr.bl.interfaces.pojo.*;
import com.Employee.Management.hr.bl.pojo.*;
import com.Employee.Management.hr.bl.exceptions.*;
import java.util.*;
public class DesignationManagerAddTestcase
{
public static void main (String gg[])
{

DesignationInterface designation = new Designation();

designation.setTitle("data analyst");

try
{
DesignationManagerInterface designationManager;
designationManager = DesignationManager.getDesignationManager();
designationManager.addDesignation(designation);

System.out.println(" Designation added with code as :" + designation.getCode());

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