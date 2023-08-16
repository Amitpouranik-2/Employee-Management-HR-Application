import com.Employee.Management.hr.dl.exceptions.*;
import com.Employee.Management.hr.dl.interfaces.dto.*;
import com.Employee.Management.hr.dl.interfaces.dao.*;
import com.Employee.Management.hr.dl.dao.*;
import com.Employee.Management.hr.dl.dto.*;

public class DesignationCodeExistsTestcase
{
public static void main (String gg[])
{

int code = Integer.parseInt(gg[0]);


try
{


DesignationDAOInterface designationDAO;
designationDAO = new DesignationDAO();

System.out.printf( code + "  -  Exists :" + designationDAO.CodeExists(code));

}catch( DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}
