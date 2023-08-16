

import com.Employee.Management.hr.dl.interfaces.dto.*;
import com.Employee.Management.hr.dl.interfaces.dao.*;
import com.Employee.Management.hr.dl.exceptions.*;
import com.Employee.Management.hr.dl.dto.*;
import com.Employee.Management.hr.dl.dao.*;
import com.Employee.Management.enums.*;
import java.util.*;
import java.math.*;
import java.text.*;
public class  EmployeeAddTestcase
{
public static void main(String gg[])
{
String name=gg[0];
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
int designationCode=Integer.parseInt(gg[1]);
Date dateOfBirth=null;
try{
dateOfBirth=sdf.parse(gg[2]);
}catch(ParseException pe)
{
System.out.println(pe.getMessage());
return;
}
BigDecimal basicSalary=new BigDecimal(gg[3]);

char gender=gg[4].charAt(0);
boolean isIndian=Boolean.parseBoolean(gg[5]);
String panNumber=gg[6];
String aadharCardNumber=gg[7];
try
{
EmployeeDTOInterface employeeDTO;
employeeDTO=new EmployeeDTO();
employeeDTO.setName(name);
employeeDTO.setDesignationCode(designationCode);
employeeDTO.setDateOfBirth(dateOfBirth);

if(gender == 'M')
{
employeeDTO.setGender(GENDER.MALE);
}
if( gender == 'F')
{
employeeDTO.setGender(GENDER.FEMALE);
}


employeeDTO.setIsIndian(isIndian);
employeeDTO.setBasicSalary(basicSalary);
employeeDTO.setPANNumber(panNumber);
employeeDTO.setAadharCardNumber(aadharCardNumber);
EmployeeDAOInterface employeeDAO;
employeeDAO=new EmployeeDAO();
employeeDAO.add(employeeDTO);
System.out.println("Employee added with employeeId as:"+employeeDTO.getEmployeeId());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}
