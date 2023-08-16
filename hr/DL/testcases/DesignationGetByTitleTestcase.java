import com.Employee.Management.hr.dl.exceptions.*;
import com.Employee.Management.hr.dl.interfaces.dto.*;
import com.Employee.Management.hr.dl.interfaces.dao.*;
import com.Employee.Management.hr.dl.dao.*;
import com.Employee.Management.hr.dl.dto.*;

public class DesignationGetByTitleTestcase
{
public static void main (String gg[])
{
String Title = gg[0];
try
{
DesignationDTOInterface designationDTO;
DesignationDAOInterface designationDAO;

designationDAO = new DesignationDAO();
designationDTO = designationDAO.getByTitle(Title);
System.out.printf(" Code : %d  , Title : %s \n" , designationDTO.getCode() , designationDTO.getTitle());
}catch( DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}
