simport com.Employee.Management.hr.dl.exceptions.*;
import com.Employee.Management.hr.dl.interfaces.dto.*;
import com.Employee.Management.hr.dl.interfaces.dao.*;
import com.Employee.Management.hr.dl.dao.*;
import com.Employee.Management.hr.dl.dto.*;

public class DesignationGetCountTestcase
{

public static void main (String gg[])
{

try
{

DesignationDAOInterface designationDAO;
designationDAO = new DesignationDAO();

System.out.printf("Designation Count :" + designationDAO.getCount());
} catch( DAOException daoException)
{
System.out.println(daoException.getMessage());
}













}}
