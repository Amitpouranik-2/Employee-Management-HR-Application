import com.thinking.machines.enums.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.exceptions.*;
import java.util.*;
import java.text.*;
import java.math.*;

class EmployeeManagerGetByPANNumberTestcase
{

public static void main ( String gg[])
{
try
{
String PANNumber = "FAWE1235";

EmployeeManagerInterface employeeManager;
employeeManager = EmployeeManager.getEmployeeManager();



EmployeeInterface employee;
employee =  employeeManager.getEmployeeByPANNumber ( PANNumber );

DesignationManagerInterface designationManager;
designationManager = DesignationManager.getDesignationManager();

DesignationInterface designation;



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


