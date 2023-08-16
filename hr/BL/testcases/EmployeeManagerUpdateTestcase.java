import com.Employee.Management.enums.*;
import com.Employee.Management.hr.bl.managers.*;
import com.Employee.Management.hr.bl.interfaces.managers.*;
import com.Employee.Management.hr.bl.interfaces.pojo.*;
import com.Employee.Management.hr.bl.pojo.*;
import com.Employee.Management.hr.bl.exceptions.*;
import java.util.*;
import java.text.*;
import java.math.*;

class EmployeeManagerUpdateTestcase
{s

public static void main ( String gg[])
{
try
{
String employeeId = "A10000001";
String name = " rajesh sharma";
DesignationInterface designation = new Designation();
designation.setCode(4);
boolean isIndian = false;

Date dateOfBirth = new Date();
BigDecimal basicSalary = new BigDecimal( "300");

String PANNumber = "FAWE1235";
String AadharCardNumber  = "41232056078";


EmployeeInterface employee;
employee = new Employee();
employee.setEmployeeId( employeeId);
employee.setName(name);
employee.setDesignation(designation);
employee.setDateOfBirth( dateOfBirth);
employee.setGender(GENDER.MALE);
employee.setIsIndian(isIndian);
employee.setBasicSalary( basicSalary);
employee.setPANNumber( PANNumber);
employee.setAadharCardNumber( AadharCardNumber);

EmployeeManagerInterface employeeManager;
employeeManager = EmployeeManager.getEmployeeManager();

employeeManager.updateEmployee( employee);

System.out.println(" Employee Updated ");

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


