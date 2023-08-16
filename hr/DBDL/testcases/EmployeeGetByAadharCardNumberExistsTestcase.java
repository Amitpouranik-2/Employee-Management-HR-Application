import com.Employee.Management.hr.dl.interfaces.dto.*;
import com.Employee.Management.hr.dl.interfaces.dao.*;
import com.Employee.Management.hr.dl.exceptions.*;
import com.Employee.Management.hr.dl.dto.*;
import com.Employee.Management.hr.dl.dao.*;
import java.util.*;
import java.math.*;
import java.text.*;
public class  EmployeeGetByAadharCardNumberExistsTestcase
{
public static void main(String gg[])
{
String AadharCardNumber = (gg[0]);
try
{

System.out.println(" AadharCardNumber :" + AadharCardNumber + "Exists :" + new  EmployeeDAO().AadharCardNumberExists(AadharCardNumber));


}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}