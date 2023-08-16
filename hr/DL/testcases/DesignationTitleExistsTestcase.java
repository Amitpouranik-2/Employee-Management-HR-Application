import com.Employee.Management.hr.dl.exceptions.*;
import com.Employee.Management.hr.dl.interfaces.dto.*;
import com.Employee.Management.hr.dl.interfaces.dao.*;
import com.Employee.Management.hr.dl.dao.*;
import com.Employee.Management.hr.dl.dto.*;

public class DesignationTitleExistsTestcase
{
public static void main (String gg[])
{

String Title = gg[0];
try
{


DesignationDAOInterface designationDAO;
designationDAO = new DesignationDAO();

System.out.printf(  Title +
 "   Exists   : " + designationDAO.TitleExists(Title));


}catch( DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}
