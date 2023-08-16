import com.Employee.Management.enums.*;
import com.Employee.Management.hr.bl.managers.*;
import com.Employee.Management.hr.bl.interfaces.managers.*;
import com.Employee.Management.hr.bl.interfaces.pojo.*;
import com.Employee.Management.hr.bl.pojo.*;
import com.Employee.Management.hr.bl.exceptions.*;
import java.util.*;
import java.text.*;
import java.math.*;

class EmployeeManagerByDesignationCodeTestcase
{
public static void main ( String gg[])
{

int code = Integer.parseInt( gg[0]);

EmployeeManagerInterface employeeManager;

Set<EmployeeInterface>ets = new TreeSet<>();

DesignationInterface designation = new Designation();

try
{


employeeManager = EmployeeManager.getEmployeeManager();

ets = employeeManager.getEmployeesByDesignationCode(code);


for( EmployeeInterface e : ets)

{




designation = e.getDesignation();

System.out.println( " Employee ID :" + e.getEmployeeId());
System.out.println( " Name : " + e.getName());
System.out.println( "  Designation Code : " + designation.getCode() + " Designation Title :" + designation.getTitle());
System.out.println(" Date of Birth : " + e.getDateOfBirth());
System.out.println(" Gender :" + e.getGender());
System.out.println(" Is Indian :" + e.getIsIndian());
System.out.println(" Basic Salary :" + e.getBasicSalary());
System.out.println(" PAN Number :" + e.getPANNumber());
System.out.println(" AadharCard Number : " + e.getAadharCardNumber());

}


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


