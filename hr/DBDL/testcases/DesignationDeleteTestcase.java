import com.Employee.Management.hr.dl.exceptions.*;
import com.Employee.Management.hr.dl.interfaces.dao.*;
import com.Employee.Management.hr.dl.dao.*;
public class DesignationDeleteTestcase
{
public static void main (String gg[])
{
int code = Integer.parseInt(gg[0]);
try
{

DesignationDAOInterface designationDAO;
designationDAO = new DesignationDAO();

designationDAO.delete(code);
System.out.println("Designation Delete ");


}catch( DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}