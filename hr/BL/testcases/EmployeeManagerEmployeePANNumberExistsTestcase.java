import com.Employee.Management.enums.*;
import com.Employee.Management.hr.bl.managers.*;
import com.Employee.Management.hr.bl.interfaces.managers.*;
import com.Employee.Management.hr.bl.interfaces.pojo.*;
import com.Employee.Management.hr.bl.pojo.*;
import com.Employee.Management.hr.bl.exceptions.*;
import java.util.*;
import java.text.*;
import java.math.*;

class  EmployeeManagerEmployeePANNumberExistsTestcase
{

public static void main ( String gg[])
{

String PANNumber = "FAWE1235"; 

try
{
EmployeeManagerInterface employeeManager;
employeeManager = EmployeeManager.getEmployeeManager();

System.out.println(" PANNumber Exists : :" + employeeManager.employeePANNumberExists( PANNumber));




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


