import com.Employee.Management.enums.*;
import com.Employee.Management.hr.bl.managers.*;
import com.Employee.Management.hr.bl.interfaces.managers.*;
import com.Employee.Management.hr.bl.interfaces.pojo.*;
import com.Employee.Management.hr.bl.pojo.*;
import com.Employee.Management.hr.bl.exceptions.*;
import java.util.*;
import java.text.*;
import java.math.*;

class  EmployeeManagerEmployeeIdExistsTestcase
{

public static void main ( String gg[])
{

String employeeId = "A10000001"; 

try
{
EmployeeManagerInterface employeeManager;
employeeManager = EmployeeManager.getEmployeeManager();

System.out.println(" Number of Employee Count :" + employeeManager.employeeIdExists( employeeId));




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


