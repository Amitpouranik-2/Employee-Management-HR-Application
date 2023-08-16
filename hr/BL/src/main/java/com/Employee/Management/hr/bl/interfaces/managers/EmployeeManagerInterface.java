package com.Employee.Management.hr.bl.interfaces.managers;
import com.Employee.Management.hr.bl.interfaces.pojo.*;
import com.Employee.Management.hr.bl.exceptions.*;
import java.util.*;

public interface EmployeeManagerInterface
{
public void addEmployee( EmployeeInterface employee) throws BLException;
public void updateEmployee( EmployeeInterface employee) throws BLException;
public void removeEmployee( String employeeId ) throws BLException;

public EmployeeInterface getEmployeeByEmployeeId ( String employeeId) throws BLException;
public EmployeeInterface getEmployeeByPANNumber ( String PANNumber ) throws BLException;
public EmployeeInterface getEmployeeByAadharCardNumber ( String AadharCardNumber ) throws BLException;

public int getEmployeeCount() ;
public int getEmployeeCountByDesignationCode ( int designationCode) throws BLException;

public Boolean employeeIdExists ( String employeeId );
public Boolean employeePANNumberExists ( String PANNumber );
public Boolean employeeAadharCardNumberExists ( String AadharCardNumber );
public Boolean designationAlloted( int designationCode ) throws BLException;


public Set<EmployeeInterface> getEmployees();
public Set<EmployeeInterface> getEmployeesByDesignationCode( int designationCode) throws BLException;

}