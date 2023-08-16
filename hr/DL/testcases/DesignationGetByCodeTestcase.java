import com.Employee.Management.hr.dl.exceptions.*;
import com.Employee.Management.hr.dl.interfaces.dto.*;
import com.Employee.Management.hr.dl.interfaces.dao.*;
import com.Employee.Management.hr.dl.dao.*;
import com.Employee.Management.hr.dl.dto.*;

public class DesignationGetByCodeTestcase
{
public static void main (String gg[])
{
int code = Integer.parseInt(gg[0]);
try
{
DesignationDTOInterface designationDTO;
DesignationDAOInterface designationDAO;

designationDAO = new DesignationDAO();
designationDTO = designationDAO.getByCode(code);
System.out.printf(" Code : %d  , Title : %s \n" , designationDTO.getCode() , designationDTO.getTitle());

}catch( DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}
