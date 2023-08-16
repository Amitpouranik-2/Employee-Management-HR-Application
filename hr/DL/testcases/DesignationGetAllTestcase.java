import com.Employee.Management.hr.dl.exceptions.*;
import com.Employee.Management.hr.dl.interfaces.dto.*;
import com.Employee.Management.hr.dl.interfaces.dao.*;
import com.Employee.Management.hr.dl.dao.*;
import com.Employee.Management.hr.dl.dto.*;
import java.util.*;

public class DesignationGetAllTestcase
{
public static void main (String gg[])
{
try
{
DesignationDAOInterface designationDAO;
designationDAO = new DesignationDAO();


Set<DesignationDTOInterface> designations;
designations = designationDAO.getall();


designations.forEach((designationDTO)->
{
System.out.printf(" Code : %d ,  Title : %s\n "  , designationDTO.getCode() , designationDTO.getTitle());
});

}catch( DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}