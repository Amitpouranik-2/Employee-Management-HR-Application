import com.Employee.Management.hr.dl.interfaces.dto.*;
import com.Employee.Management.hr.dl.interfaces.dao.*;
import com.Employee.Management.hr.dl.exceptions.*;
import com.Employee.Management.hr.dl.dto.*;
import com.Employee.Management.hr.dl.dao.*;
import java.util.*;
import java.math.*;
import java.text.*;
public class  EmployeeGetByPANNumberExistsTestcase
{
public static void main(String gg[])
{
String PANNumber = (gg[0]);
try
{

System.out.println(" PANNumber :" + PANNumber + "Exists :" + new  EmployeeDAO().PANNumberExists(PANNumber));


}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}