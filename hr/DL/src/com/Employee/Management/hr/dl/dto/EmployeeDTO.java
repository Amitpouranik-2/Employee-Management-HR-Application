package com.Employee.Management.hr.dl.dto;
import com.Employee.Management.hr.dl.interfaces.dto.*;
import java.util.*;
import java.math.*;
import com.Employee.Management.enums.*;

public class EmployeeDTO implements EmployeeDTOInterface
{
private String EmployeeId;
private String name;
private int Designationcode;
private Date DateOfBirth;
private char gender;
private boolean isIndian;
private BigDecimal BasicSalary;
private String PANNumber;
private String AadharCardNumber;

public EmployeeDTO()
{
this.EmployeeId = " ";
this.name = " ";
this.Designationcode = 0;
this.DateOfBirth = null;
this.gender = ' ';
this.isIndian = false;
this.BasicSalary = null;
this.PANNumber = " ";
this.AadharCardNumber  = " ";
}


public void setEmployeeId( String EmployeeId)
{
this.EmployeeId = EmployeeId;
}
public String getEmployeeId()
{
return this.EmployeeId;
}

public void setName(String name)
{
this.name = name;
}
public String getName()
{
return this.name;
}


public void setDesignationCode( int Designationcode)
{
this.Designationcode = Designationcode;
}
public int getDesignationCode()
{
return this.Designationcode;
}

public void setDateOfBirth( Date DateOfBirth)
{
this.DateOfBirth = DateOfBirth;
}
public Date getDateOfBirth()
{
return this.DateOfBirth;
}

public void setGender(GENDER  gender)
{
if(gender == GENDER.MALE ) this.gender = 'M';
else this.gender = 'F';
}
public char getGender()
{
return this.gender;
}

public void setIsIndian( boolean isIndian)
{
this.isIndian = isIndian;
}
public boolean getIsIndian()
{
return this.isIndian;
}

public void setBasicSalary( BigDecimal  BasicSalary)
{
this.BasicSalary = BasicSalary;
}
public BigDecimal getBasicSalary()
{
return this.BasicSalary;
}

public void setPANNumber(String PANNumber)
{
this.PANNumber = PANNumber;
}
public String getPANNumber()
{
return this.PANNumber;
}

public void setAadharCardNumber( String AadharCardNumber)
{
this.AadharCardNumber = AadharCardNumber;
}
public String getAadharCardNumber()
{
return this.AadharCardNumber;
}


public boolean equals(Object other)
{
if(! (other instanceof EmployeeDTOInterface)) return false;
EmployeeDTOInterface EmployeeDTO = (EmployeeDTOInterface)other;
return this.EmployeeId.equalsIgnoreCase(EmployeeDTO.getEmployeeId());
}

public int compareTo( EmployeeDTOInterface other)
{
return this.EmployeeId.compareToIgnoreCase( other.getEmployeeId());
}


public int hashCode()
{
return this.EmployeeId.toUpperCase().hashCode();
}
}