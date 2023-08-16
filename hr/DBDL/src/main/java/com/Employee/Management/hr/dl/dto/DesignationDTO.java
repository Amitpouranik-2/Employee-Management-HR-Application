package com.Employee.Management.hr.dl.dto;
import com.Employee.Management.hr.dl.interfaces.dto.*;

public class DesignationDTO implements DesignationDTOInterface
{
private int code;
private String Title;
public DesignationDTO()
{
this.code = 0;
this.Title = "";
}
public void setCode(int code)
{
this.code = code;
}
public int getCode()
{
return this.code;
}
public void setTitle(String Title)
{
this.Title = Title;
}
public String getTitle()
{
return this.Title;
}

public boolean equals( Object other)
{
if(!(other instanceof DesignationDTOInterface)) return false;
DesignationDTOInterface designationDTO;
designationDTO = (DesignationDTO)other;
return this.code == designationDTO.getCode();
}
public int compareTo(DesignationDTOInterface designationDTO)
{
return this.code - designationDTO.getCode();
}
public int hashCode()
{
return code;
}
}