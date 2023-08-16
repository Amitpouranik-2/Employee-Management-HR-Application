import com.Employee.Management.enums.*;
import com.Employee.Management.hr.bl.managers.*;
import com.Employee.Management.hr.bl.interfaces.managers.*;
import com.Employee.Management.hr.bl.interfaces.pojo.*;
import com.Employee.Management.hr.bl.pojo.*;
import com.Employee.Management.hr.bl.exceptions.*;
import java.util.*;
import java.text.*;
import java.math.*;

class EmployeeManagerAddTestcase
{

public static void main ( String gg[])
{
try
{

String name = " jitendra joshi";
DesignationInterface designation = new Designation();
designation.setCode(5);
boolean isIndian = false;

Date dateOfBirth = new Date();
BigDecimal basicSalary = new BigDecimal( "5120");

String PANNumber = "AAAFE123";
String AadharCardNumber  = "4123212s0569078";


EmployeeInterface employee;
employee = new Employee();

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

employeeManager.addEmployee( employee);

System.out.printf(" Employee Added with Employee id %s\n " , employee.getEmployeeId());


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

