import com.Employee.Management.hr.bl.exceptions.*;
import com.Employee.Management.hr.bl.interfaces.managers.*;
import com.Employee.Management.hr.bl.managers.*;
import com.Employee.Management.hr.bl.interfaces.pojo.*;
import com.Employee.Management.hr.bl.pojo.*;
import java.util.*;

class DesignationManagerTitleExistsTestcase
{
public static void main (String gg[] )
{

String title = gg[0];

try
{

DesignationManagerInterface designationManager;
designationManager = DesignationManager.getDesignationManager();
System.out.println( title + "  Exists. " + designationManager.DesignationTitleExists(title.toUpperCase()));

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