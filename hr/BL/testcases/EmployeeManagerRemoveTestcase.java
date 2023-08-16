import com.Employee.Management.enums.*;
import com.Employee.Management.hr.bl.managers.*;
import com.Employee.Management.hr.bl.interfaces.managers.*;
import com.Employee.Management.hr.bl.interfaces.pojo.*;
import com.Employee.Management.hr.bl.pojo.*;
import com.Employee.Management.hr.bl.exceptions.*;
import java.util.*;
import java.text.*;
import java.math.*;

class EmployeeManagerRemoveTestcase
{

public static void main ( String gg[])
{


try
{
String employeeId = "A10000003";

EmployeeManagerInterface employeeManager;
employeeManager = EmployeeManager.getEmployeeManager();



employeeManager.removeEmployee( employeeId );



System.out.println(" Employee remove ");

}catch( BLException blException)
{

if( blException.hasGenericExceptions()) System.out.println(blException.getGenericException());

List<String> properties =  blException.getProperties();

for( String property : properties)
{

System.out.println( blException.getException(property));
}
}
}
}

