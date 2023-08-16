oimport com.Employee.Management.hr.bl.exceptions.*;
import com.Employee.Management.hr.bl.interfaces.managers.*;
import com.Employee.Management.hr.bl.managers.*;
import com.Employee.Management.hr.bl.interfaces.pojo.*;
import com.Employee.Management.hr.bl.pojo.*;
import java.util.*;

class DesignationManagerGetCountTestcase
{
public static void main (String gg[] )
{

try
{

DesignationManagerInterface designationManager;
designationManager = DesignationManager.getDesignationManager();

System.out.printf(" Number Of Designation :" + designationManager.getDesignationCount());

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