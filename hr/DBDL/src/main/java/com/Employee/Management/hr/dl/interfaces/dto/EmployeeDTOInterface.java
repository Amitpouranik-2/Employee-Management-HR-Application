package com.Employee.Management.hr.dl.interfaces.dto;
import java.math.*;
import java.util.*;
import com.Employee.Management.enums.*;


public interface EmployeeDTOInterface extends Comparable<EmployeeDTOInterface> , java.io.Serializable
{

public void setEmployeeId( String EmployeeId);
public String getEmployeeId();

public void setName(String name);
public String getName();


public void setDesignationCode( int Designationcode);
public int getDesignationCode();

public void setDateOfBirth( Date DateOfBirth);
public Date getDateOfBirth();


public void setGender( GENDER  gender);
public char getGender();

public void setIsIndian( boolean isIndian);
public boolean getIsIndian();

public void setBasicSalary( BigDecimal  BasicSalary);
public BigDecimal getBasicSalary();

public void setPANNumber(String PANNumber);
public String getPANNumber();

public void setAadharCardNumber( String AadharCardNumber);
public String getAadharCardNumber();

}
 