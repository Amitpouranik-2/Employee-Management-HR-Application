import com.Employee.Management.hr.dl.exceptions.*;
import com.Employee.Management.hr.dl.interfaces.dto.*;
import com.Employee.Management.hr.dl.interfaces.dao.*;
import com.Employee.Management.hr.dl.dao.*;
import com.Employee.Management.hr.dl.dto.*;

public class DesignationAddTestcases
{

public static void main (String gg[])
{

String title = gg[0];

try
{
DesignationDTOInterface designationDTO;
designationDTO = new DesignationDTO();

designationDTO.setTitle(title);

DesignationDAOInterface designationDAO;
designationDAO = new DesignationDAO();

designationDAO.add(designationDTO);

}catch( DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}