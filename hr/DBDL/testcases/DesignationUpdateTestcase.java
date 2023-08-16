import com.Employee.Management.hr.dl.exceptions.*;
import com.Employee.Management.hr.dl.interfaces.dto.*;
import com.Employee.Management.hr.dl.interfaces.dao.*;
import com.Employee.Management.hr.dl.dao.*;
import com.Employee.Management.hr.dl.dto.*;

public class DesignationUpdateTestcase
{

public static void main (String gg[])
{
int code = Integer.parseInt(gg[0]);
String Title = gg[1];

try
{
DesignationDTOInterface designationDTO;
designationDTO = new DesignationDTO();

designationDTO.setCode(code);
designationDTO.setTitle(Title);

DesignationDAOInterface designationDAO;
designationDAO = new DesignationDAO();

designationDAO.update(designationDTO);
System.out.println("Designation Updated");


}catch( DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}