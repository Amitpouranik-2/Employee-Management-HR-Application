import com.Employee.Management.hr.dl.interfaces.dto.*;
import com.Employee.Management.hr.dl.interfaces.dao.*;
import com.Employee.Management.hr.dl.exceptions.*;
import com.Employee.Management.hr.dl.dto.*;
import com.Employee.Management.hr.dl.dao.*;
import java.util.*;
import java.math.*;
import java.text.*;
public class  EmployeeGetByPANNumberTestcase
{
public static void main(String gg[])
{
String PANNumber = (gg[0]);
try
{

EmployeeDAOInterface employeeDAO;
employeeDAO = new EmployeeDAO();


EmployeeDTOInterface employeeDTO;
employeeDTO = employeeDAO.getByPANNumber(PANNumber);

SimpleDateFormat simpleDateFormat;
simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

System.out.println("Employee Id :" + employeeDTO.getEmployeeId());
System.out.println("Name :" +employeeDTO.getName());
System.out.println("Designation Code :" + employeeDTO.getDesignationCode());
System.out.println("Date of Birth :"+ simpleDateFormat.format(employeeDTO.getDateOfBirth()));
System.out.println("Gender :" + employeeDTO.getGender());
System.out.println(" Basic Salary :" + employeeDTO.getBasicSalary());
System.out.println("PAN Number :" + employeeDTO.getPANNumber());
System.out.println("Aadhar Card Number :" + employeeDTO.getAadharCardNumber());

System.out.println("**************************************************************");

}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}