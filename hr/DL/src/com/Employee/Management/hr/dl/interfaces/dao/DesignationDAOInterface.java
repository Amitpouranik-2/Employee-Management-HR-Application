package com.Employee.Management.hr.dl.interfaces.dao;
import java.util.*;
import com.Employee.Management.hr.dl.exceptions.*;
import com.Employee.Management.hr.dl.interfaces.dto.*;


public interface DesignationDAOInterface
{
public void add(DesignationDTOInterface designationDTO) throws DAOException;
public void update(DesignationDTOInterface designationDTO) throws DAOException;

public void delete( int code ) throws DAOException;
public Set<DesignationDTOInterface> getall() throws DAOException;

public DesignationDTOInterface getByCode( int code ) throws DAOException;
public DesignationDTOInterface getByTitle( String Title) throws DAOException;

public boolean CodeExists( int code ) throws DAOException;
public boolean TitleExists( String Title) throws DAOException;

public int getCount() throws DAOException;
}