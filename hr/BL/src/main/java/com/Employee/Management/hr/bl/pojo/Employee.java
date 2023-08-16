package com.Employee.Management.hr.bl.pojo;
import com.Employee.Management.hr.bl.interfaces.pojo.*;
import java.util.*;
import java.math.*;
import com.Employee.Management.enums.*;

public class Employee implements EmployeeInterface
{

private String EmployeeId;
private String name;
private DesignationInterface designation;
private Date DateOfBirth;
private char gender;
private boolean isIndian;
private BigDecimal BasicSalary;
private String PANNumber;
private String AadharCardNumber;

public Employee()
{
this.EmployeeId = " ";
this.name = " ";
this.designation = null;
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


public void setDesignation( DesignationInterface designation)
{
this.designation = designation;
}

public DesignationInterface getDesignation()
{
return this.designation;
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
if(! (other instanceof EmployeeInterface)) return false;
EmployeeInterface Employee = (EmployeeInterface)other;
return this.EmployeeId.equalsIgnoreCase(Employee.getEmployeeId());
}

public int compareTo( EmployeeInterface other)
{
return this.EmployeeId.compareToIgnoreCase( other.getEmployeeId());
}

public int hashCode()
{
return this.EmployeeId.toUpperCase().hashCode();
}
}