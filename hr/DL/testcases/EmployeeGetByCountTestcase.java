import com.Employee.Management.hr.dl.interfaces.dto.*;
import com.Employee.Management.hr.dl.interfaces.dao.*;
import com.Employee.Management.hr.dl.exceptions.*;
import com.Employee.Management.hr.dl.dto.*;
import com.Employee.Management.hr.dl.dao.*;
import java.util.*;
import java.math.*;
import java.text.*;
public class  EmployeeGetByCountTestcase
{
public static void main(String gg[])
{
try
{

System.out.println("Number of Employees :" + new EmployeeDAO().getcount());

}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}