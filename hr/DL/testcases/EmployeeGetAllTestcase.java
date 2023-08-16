import com.Employee.Management.hr.dl.interfaces.dto.*;
import com.Employee.Management.hr.dl.interfaces.dao.*;
import com.Employee.Management.hr.dl.exceptions.*;
import com.Employee.Management.hr.dl.dto.*;
import com.Employee.Management.hr.dl.dao.*;
import java.util.*;
import java.math.*;
import java.text.*;
public class  EmployeeGetAllTestcase
{
public static void main(String gg[])
{
try
{

EmployeeDAOInterface employeeDAO;
employeeDAO = new EmployeeDAO();

Set<EmployeeDTOInterface>employees;

employees = employeeDAO.getall();

SimpleDateFormat simpleDateFormat;
simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

for(EmployeeDTOInterface employeeDTO : employees)
{
System.out.println("employee Id :" + employeeDTO.getEmployeeId());
System.out.println(" Name :" + employeeDTO.getName());
System.out.println(" Designation Code :"+ employeeDTO.getDesignationCode());
System.out.println("Date Of Birth :" + simpleDateFormat.format(employeeDTO.getDateOfBirth()));
System.out.println(" Gender( M/F) :" + employeeDTO.getGender());
System.out.println(" Is Indian(true/false) :" + employeeDTO.getIsIndian());
System.out.println("Basic Salary :" + employeeDTO.getBasicSalary().toPlainString());
System.out.println(" PAN Number :" + employeeDTO.getPANNumber());
System.out.println("Aadhar Card Number :" + employeeDTO.getAadharCardNumber());

System.out.println("**************************************************************");
}
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}
