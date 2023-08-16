import com.Employee.Management.hr.dl.interfaces.dto.*;
import com.Employee.Management.hr.dl.interfaces.dao.*;
import com.Employee.Management.hr.dl.exceptions.*;
import com.Employee.Management.hr.dl.dto.*;
import com.Employee.Management.hr.dl.dao.*;
import com.Employee.Management.enums.*;
import java.util.*;
import java.math.*;
import java.text.*;
public class  EmployeeDeleteTestcase
{
public static void main(String gg[])
{
String EmployeeId = gg[0];
try
{

EmployeeDAOInterface employeeDAO;
employeeDAO = new EmployeeDAO();

employeeDAO.delete(EmployeeId);

System.out.println("Employee Deleted ...................");

}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}
