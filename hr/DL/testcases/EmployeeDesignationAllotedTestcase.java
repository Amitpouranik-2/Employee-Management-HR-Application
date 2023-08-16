import com.Employee.Management.hr.dl.interfaces.dto.*;
import com.Employee.Management.hr.dl.interfaces.dao.*;
import com.Employee.Management.hr.dl.exceptions.*;
import com.Employee.Management.hr.dl.dto.*;
import com.Employee.Management.hr.dl.dao.*;
import java.util.*;
import java.math.*;
import java.text.*;
public class  EmployeeDesignationAllotedTestcase
{
public static void main(String gg[])
{
int DesignationCode = Integer.parseInt(gg[0]);

try
{

EmployeeDAOInterface employeeDAO;
employeeDAO = new EmployeeDAO();
System.out.println(" Employee with Designation Code" + DesignationCode + "Exists :" + employeeDAO.isDesignationAlloted(DesignationCode));




}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}
