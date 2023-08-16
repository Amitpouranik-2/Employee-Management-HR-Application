package com.Employee.Management.hr.bl.interfaces.pojo;
import com.Employee.Management.enums.*;
import java.math.*;
import java.util.*;


public interface EmployeeInterface extends Comparable<EmployeeInterface> , java.io.Serializable
{

public void setEmployeeId( String EmployeeId);
public String getEmployeeId();

public void setName(String name);
public String getName();


public void setDesignation( DesignationInterface designation );
public DesignationInterface  getDesignation();

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
 