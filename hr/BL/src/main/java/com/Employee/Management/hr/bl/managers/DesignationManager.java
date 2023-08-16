package com.Employee.Management.hr.bl.managers;

import com.Employee.Management.hr.bl.interfaces.pojo.*;
import com.Employee.Management.hr.bl.interfaces.managers.*;
import com.Employee.Management.hr.bl.exceptions.*;
import com.Employee.Management.hr.bl.pojo.*;
import com.Employee.Management.hr.dl.interfaces.dto.*;
import com.Employee.Management.hr.dl.interfaces.dao.*;
import com.Employee.Management.hr.dl.exceptions.*;
import com.Employee.Management.hr.dl.dao.*;
import com.Employee.Management.hr.dl.dto.*;
import java.util.*;

public class DesignationManager implements DesignationManagerInterface
{
private Map<Integer , DesignationInterface >codeWiseDesignationMap;
private Map<String , DesignationInterface> titleWiseDesignationMap;
private Set<DesignationInterface> designationSet;
private static DesignationManager designationManager  = null;
private DesignationManager() throws BLException
{
PopulateDataStructures();
}
private void PopulateDataStructures() throws BLException
{
this.codeWiseDesignationMap = new HashMap<>();
this.titleWiseDesignationMap = new HashMap<>();
this.designationSet =  new TreeSet<>();

try
{
Set<DesignationDTOInterface> dlDesignations;
dlDesignations = new DesignationDAO().getall();
DesignationInterface designation;
for(  DesignationDTOInterface dlDesignation : dlDesignations)
{
designation = new Designation();
designation.setCode( dlDesignation.getCode());
designation.setTitle(dlDesignation.getTitle());
this.codeWiseDesignationMap.put(designation.getCode()  , designation);
this.titleWiseDesignationMap.put(designation.getTitle().toUpperCase() , designation);
this.designationSet.add(designation);
}
}catch(DAOException daoexception)
{
BLException blException = new BLException();
blException.setGenericException(daoexception.getMessage());
throw blException;
}
}

public static DesignationManagerInterface getDesignationManager() throws BLException
{
if(designationManager == null ) designationManager = new DesignationManager();
return designationManager;
}


//**********************************Add Designation******************************************


public void addDesignation( DesignationInterface designation) throws BLException
{
BLException blException;
blException = new BLException();
if(designation == null)
{
blException.setGenericException(" Designation is null");
throw blException;
}
int code = designation.getCode();
String title = designation.getTitle();
if(code != 0)
{
blException.addException( "code" , " Code should be zero");
}
if(title == null)
{
blException.addException(  " title ", " Title is null");
title = " ";
}
else
{
title  = title.trim();
if(title.length() == 0)
{
blException.addException( "title "," title length is zero");
}
}
if(title.length() > 0)
{
if(this.titleWiseDesignationMap.containsKey(title.toUpperCase()))
{
blException.addException( "title", "Designation :" + title + "Exists.");
}
}
if(blException.hasExceptions())
{
throw blException;
}
try
{
DesignationDTOInterface designationDTO;
designationDTO = new DesignationDTO();
designationDTO.setTitle(title);
DesignationDAOInterface designationDAO;
designationDAO = new DesignationDAO();
designationDAO.add(designationDTO);
code = designationDTO.getCode();
designation.setCode(code);
Designation dsDesignations;
dsDesignations = new Designation();
dsDesignations.setCode(code);
dsDesignations.setTitle(title);
codeWiseDesignationMap.put(code , dsDesignations);
titleWiseDesignationMap.put(title.toUpperCase() , dsDesignations);
designationSet.add(dsDesignations);
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
}
}



// //**********************************Update  Designation******************************************



public void updateDesignation( DesignationInterface designation) throws BLException
{
BLException blException = new BLException();
if(designation == null)
{
blException.setGenericException("Designation Required");
throw blException;
}
int code = designation.getCode();
String title = designation.getTitle();
if(code <= 0)
{
blException.addException("code" , " Invalid Code :" + code);
}
if( code > 0)
{
if(this.codeWiseDesignationMap.containsKey(code) == false)
{
blException.addException("code " , "Invalid Code :" + code);
throw blException;
}
}
if(title == null)
{
blException.addException("Title" , "Title Required ");
title = "";
}
else
{
title = title.trim();
if(title.length() == 0)
{
blException.addException("title" , "Title Required " );
}
}
if(title.length() > 0 )
{
DesignationInterface d;
d = titleWiseDesignationMap.get(title.toUpperCase());
if( d != null && d.getCode() != code)
{
blException.addException("title" , " Designation :" + title + "Exists");
}
}
if(blException.hasExceptions())
{
throw blException;
}
try
{
DesignationInterface dsDesignation = codeWiseDesignationMap.get(code);
DesignationDTOInterface dlDesignation;
dlDesignation = new DesignationDTO();
dlDesignation.setCode(code);
dlDesignation.setTitle(title);
new DesignationDAO().update(dlDesignation);


// remove old data from Data Structure

codeWiseDesignationMap.remove(code);
titleWiseDesignationMap.remove(dsDesignation.getTitle().toUpperCase());
designationSet.remove(dsDesignation);
dsDesignation.setTitle(title);
codeWiseDesignationMap.put(code ,dsDesignation);
titleWiseDesignationMap.put(title.toUpperCase() , dsDesignation);
designationSet.add(dsDesignation);
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
}
}


//**********************************Remove Designation******************************************


public void removeDesignation( int code) throws BLException
{
BLException blException = new BLException();
if(code <= 0)
{
blException.addException("code" , " Invalid Code :" + code );
}
if( code > 0)
{
if(this.codeWiseDesignationMap.containsKey(code) == false)
{
blException.addException("code" , " Code : " + code + "Exists.");
throw blException;
}
}
try
{
DesignationInterface dsDesignation = codeWiseDesignationMap.get(code);
new DesignationDAO().delete(code);
codeWiseDesignationMap.remove(code);
titleWiseDesignationMap.remove(dsDesignation. getTitle(). toUpperCase());
designationSet.remove(dsDesignation);
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
throw blException;
}
}


 //**********************************Get Designation By Code******************************************


public DesignationInterface getDesignationByCode( int code) throws BLException
{
DesignationInterface designations;
designations = codeWiseDesignationMap.get(code);
if( designations == null)
{
BLException blException = new BLException();
blException.addException("Code" , " Invalid Code :" + code);
throw blException;
}
DesignationInterface d = new Designation();
d.setCode(designations.getCode());
d.setTitle(designations.getTitle());
return d;
}


//**********************************Get Designation By Title ******************************************


public DesignationInterface getDesignationByTitle(String Title) throws BLException
{

DesignationInterface designations;
designations = titleWiseDesignationMap.get(Title.toUpperCase());
if(designations == null)
{
BLException blException;
blException = new BLException();
blException.addException("Title" , " Title Required :" + Title);
throw blException;
}
DesignationInterface d  = new Designation();
d.setCode( designations.getCode());
d.setTitle( designations.getTitle());
return d;
}


//**********************************Get (DS) Designation By Code******************************************

DesignationInterface getDSDesignationByCode( int code ) throws BLException
{
DesignationInterface designations;
designations  =  codeWiseDesignationMap.get(code);

if( designations == null)
{
BLException blException;
blException = new BLException();
blException.addException(" code  " , " Invalid Code :" + code );
throw blException;
}
return designations;
}


//**********************************Get Designation Count******************************************

public int  getDesignationCount() 
{
return designationSet.size();
}

//********************************** Designation Code Exists******************************************

public boolean DesignationCodeExists( int code) 
{
return codeWiseDesignationMap.containsKey(code);
}

//**********************************Designation Title Exists******************************************

public boolean DesignationTitleExists( String title)
{
return titleWiseDesignationMap.containsKey(title);
}

//**********************************Get Designation******************************************

public Set<DesignationInterface> getDesignation() 
{
Set<DesignationInterface> designations;
designations  = new TreeSet<>(); 
designationSet.forEach((designation)->{
DesignationInterface d;
d = new Designation();
d.setCode(designation.getCode());
d.setTitle(designation.getTitle());
designations.add(d);
});
return designations;
}
}