package com.Employee.Management.hr.dl.interfaces.dao;
import com.Employee.Management.hr.dl.exceptions.*;
import com.Employee.Management.hr.dl.interfaces.dto.*;
import java.util.*;

public interface EmployeeDAOInterface
{
public void add( EmployeeDTOInterface EmployeeDTO) throws DAOException;

public void update( EmployeeDTOInterface employeeDTO) throws DAOException;

public void delete( String EmployeeId ) throws DAOException;

public Set<EmployeeDTOInterface>getall() throws DAOException;

public Set<EmployeeDTOInterface>getByDesignationcode( int Designationcode) throws  DAOException;

public boolean isDesignationAlloted( int Designationcode) throws DAOException;

public EmployeeDTOInterface getByEmployeeId( String EmployeeId) throws DAOException;

public EmployeeDTOInterface getByAadharCard( String AadharCardNumber ) throws DAOException;

public EmployeeDTOInterface getByPANNumber( String PANNumber) throws DAOException;

public boolean EmployeeIDExists( String EmployeeId) throws DAOException;

public boolean PANNumberExists( String PANNumber) throws DAOException;

public boolean AadharCardNumberExists( String AadharCardNumber) throws DAOException;

public int getcount() throws DAOException;

public int getcountByDesignation( int Designationcode) throws DAOException;

}