import com.Employee.Management.hr.dl.interfaces.dto.*;
import com.Employee.Management.hr.dl.interfaces.dao.*;
import com.Employee.Management.hr.dl.exceptions.*;
import com.Employee.Management.hr.dl.dto.*;
import com.Employee.Management.hr.dl.dao.*;
import java.util.*;
import java.math.*;
import java.text.*;
public class   EmployeeGetbyDesignationcodeCountTestcase
{
public static void main(String gg[])
{

int DesignationCode = Integer.parseInt(gg[0]);

try
{

System.out.println("Number of Employees Whose designation Code is  :" + new EmployeeDAO().getcountByDesignation(DesignationCode));

}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}