package com.Employee.Management.hr.bl.interfaces.managers;
import com.Employee.Management.hr.bl.interfaces.pojo.*;
import com.Employee.Management.hr.bl.exceptions.*;
import java.util.*;

public interface DesignationManagerInterface
{

public void addDesignation( DesignationInterface designation) throws BLException;
public void updateDesignation( DesignationInterface designation) throws BLException;

public void removeDesignation( int code) throws BLException;

public DesignationInterface getDesignationByCode( int code) throws BLException;
public DesignationInterface getDesignationByTitle(String Title) throws BLException;

public int  getDesignationCount();

public boolean DesignationCodeExists( int code);
public boolean DesignationTitleExists( String title);

public Set<DesignationInterface> getDesignation();
}