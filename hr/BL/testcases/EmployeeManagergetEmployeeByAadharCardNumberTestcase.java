import com.Employee.Management.enums.*;
import com.Employee.Management.hr.bl.managers.*;
import com.Employee.Management.hr.bl.interfaces.managers.*;
import com.Employee.Management.hr.bl.interfaces.pojo.*;
import com.Employee.Management.hr.bl.pojo.*;
import com.Employee.Management.hr.bl.exceptions.*;
import java.util.*;
import java.text.*;
import java.math.*;

class  EmployeeManagergetEmployeeByAadharCardNumberTestcase
{

public static void main ( String gg[])
{
try
{
String AadharCardNumber = "41232056078";

EmployeeManagerInterface employeeManager;
employeeManager = EmployeeManager.getEmployeeManager();



EmployeeInterface employee;
employee =  employeeManager. getEmployeeByAadharCardNumber( AadharCardNumber );

System.out.println(" EmployeeId : " + employee.getEmployeeId());
System.out.println("  name :" + employee.getName());
System.out.println( " Designation :  " + employee.getDesignation().getCode());

System.out.println(" Date Of Birth :" + employee.getDateOfBirth());
System.out.println(" Gender :  " + employee.getGender());
System.out.println(" Is Indian :" +  employee.getIsIndian());
System.out.println(" Basic Salary : " + employee.getBasicSalary());
System.out.println(" PAN Number :" + employee.getPANNumber());
System.out.println(" Aadhar Card Number :" + employee.getAadharCardNumber());







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


