package com.Employee.Management.hr.bl.managers;
import com.Employee.Management.hr.bl.interfaces.managers.*;
import com.Employee.Management.hr.bl.interfaces.pojo.*;
import com.Employee.Management.hr.bl.exceptions.*;
import com.Employee.Management.hr.bl.pojo.*;
import java.util.*;
import java.text.*;
import java.math.*;

import com.Employee.Management.enums.*;
import com.Employee.Management.hr.dl.interfaces.dto.*;
import com.Employee.Management.hr.dl.interfaces.dao.*;
import com.Employee.Management.hr.dl.exceptions.*;
import com.Employee.Management.hr.dl.dao.*;
import com.Employee.Management.hr.dl.dto.*;


public class EmployeeManager  implements  EmployeeManagerInterface
{
private Map< String , EmployeeInterface> employeeIdWiseEmployeeMap;
private Map< String , EmployeeInterface> panNumberWiseEmployeeMap;
private Map< String , EmployeeInterface> aadharCardNumberWiseEmployeeMap;
private Set<EmployeeInterface> employeeSet;
private Map< Integer , Set<EmployeeInterface>> designationCodeWiseEmployeeMap;
private static EmployeeManager employeeManager = null;
private EmployeeManager() throws BLException
{
populateDataStructures();
}

public  void populateDataStructures() throws BLException
{
this.employeeIdWiseEmployeeMap = new HashMap<>();
this.panNumberWiseEmployeeMap = new HashMap<>();
this.aadharCardNumberWiseEmployeeMap = new HashMap<>();
this.employeeSet = new TreeSet<>();
this.designationCodeWiseEmployeeMap = new HashMap<>();
try
{
Set<EmployeeDTOInterface> dlEmployees;
dlEmployees = new EmployeeDAO().getall();
EmployeeInterface employee;
DesignationManagerInterface designationManager;
designationManager = DesignationManager.getDesignationManager();
DesignationInterface designation;
Set<EmployeeInterface>ets;
for( EmployeeDTOInterface dlEmployee : dlEmployees)
{
employee = new Employee();
employee.setEmployeeId( dlEmployee.getEmployeeId());
employee.setName( dlEmployee.getName());
designation = designationManager.getDesignationByCode( dlEmployee.getDesignationCode());
employee.setDesignation(designation);
employee.setDateOfBirth(dlEmployee.getDateOfBirth());
if( dlEmployee.getGender() == 'M')
{
employee.setGender( GENDER.MALE);
}
else
{
employee.setGender( GENDER.FEMALE);
}
employee.setIsIndian( dlEmployee.getIsIndian());
employee.setBasicSalary (dlEmployee.getBasicSalary());
employee.setPANNumber( dlEmployee.getPANNumber());
employee.setAadharCardNumber( dlEmployee.getAadharCardNumber());
this.employeeIdWiseEmployeeMap.put( employee.getEmployeeId(). toUpperCase() , employee);
this.panNumberWiseEmployeeMap.put( employee.getPANNumber().toUpperCase() , employee);
this.aadharCardNumberWiseEmployeeMap.put( employee.getAadharCardNumber(). toUpperCase() , employee);
this.employeeSet.add( employee);
ets = this.designationCodeWiseEmployeeMap.get(designation.getCode());
if( ets == null)
{
ets = new TreeSet<>();
ets.add(employee);
designationCodeWiseEmployeeMap.put(  designation.getCode() , ets);
}
else
{
ets.add(employee);
}
}
}catch( DAOException daoException)
{
BLException blException = new BLException();
blException.setGenericException(blException.getMessage());
throw blException;
}
} 


public static EmployeeManagerInterface getEmployeeManager() throws BLException
{
if( employeeManager == null) employeeManager = new EmployeeManager();
return employeeManager;
}

 //**********************************Add Employee******************************************


public void addEmployee( EmployeeInterface employee) throws BLException
{
BLException blException = new BLException();
String employeeId = employee.getEmployeeId();
String name = employee.getName();
DesignationInterface designation = employee.getDesignation();
int designationCode = 0;
Date dateOfBirth = employee.getDateOfBirth();
char gender = employee.getGender();
boolean isIndian = employee.getIsIndian();
BigDecimal basicSalary = employee.getBasicSalary();
String panNumber = employee.getPANNumber();
String aadharCardNumber = employee.getAadharCardNumber();


if( employeeId != null)
{
employeeId = employeeId.trim();
if( employeeId.length() > 0)
{
blException.addException(" EmployeeId" , "  EmployeeId should be nil/ Empty");
}
}
if( name == null)
{
blException.addException( " name " , "  Invalid name ");
} 
else
{
name = name.trim();
if( name.length() == 0) blException.addException(" name " , " name Required");
}
DesignationManagerInterface designationManager;
designationManager =  DesignationManager.getDesignationManager();
if( designation == null)
{
blException.addException(" Designation " , " Designation Required");
}
else
{
designationCode = designation.getCode();
if( designationManager.DesignationCodeExists( designation.getCode()) == false)
{
blException.addException(" Designation " , " designation Required");
}
}
if( dateOfBirth == null)
{
blException.addException(" dateOfBirth" , " date of Birth Required");
}
if( gender == ' ')
{
blException.addException(" Gender " , " Gender is Required");
}
if( basicSalary == null)
{
blException.addException(" basicSalary" , " Basic Salary is Required");
}
else
{
if(basicSalary.signum() == -1)
{
blException.addException(" basicSalary" , " basic Salary can not be Negative");
}
}
if( panNumber == null)
{
blException.addException(" panNumber" , " PANNumber is Required");
}
else
{
panNumber  = panNumber.trim();
if(panNumber.length() == 0)
{
blException.addException(" panNumber" , " PANNumber is Required");
}
}
if( aadharCardNumber == null)
{
blException.addException( " aadharCardNumber" , " Aadhar Card Number is Required");
}
else
{
aadharCardNumber = aadharCardNumber.trim();
if( aadharCardNumber.length() == 0)
{
blException.addException( " aadharCardNumber " , " Aadhar Card Number is Required" );
}
}
if( panNumber != null && panNumber.length() > 0)
{
if( panNumberWiseEmployeeMap.containsKey(panNumber.toUpperCase()))
{
blException.addException(" panNumber" , " PANNumber : " + panNumber + " Exists .");
}
}
if( aadharCardNumber != null && aadharCardNumber.length() > 0)
{
if( aadharCardNumberWiseEmployeeMap.containsKey( aadharCardNumber) )
{
blException.addException( " aadharCardNumber " , " Aadhar Card Number :"+ aadharCardNumber + "Exists,");
}
}
if(blException.hasExceptions())
{
throw blException;
}
try
{
EmployeeDAOInterface employeeDAO;
employeeDAO = new EmployeeDAO();
EmployeeDTOInterface dlEmployee;
dlEmployee = new EmployeeDTO();
dlEmployee.setName( name );
dlEmployee.setDesignationCode( designation.getCode());
dlEmployee.setDateOfBirth( dateOfBirth);
dlEmployee.setGender(( gender == 'M')? GENDER.MALE : GENDER.FEMALE);
dlEmployee.setIsIndian( isIndian);
dlEmployee.setBasicSalary( basicSalary);
dlEmployee.setPANNumber( panNumber);
dlEmployee.setAadharCardNumber( aadharCardNumber);
employeeDAO.add(dlEmployee);
employee.setEmployeeId( dlEmployee.getEmployeeId());
EmployeeInterface dsEmployee = new Employee();
dsEmployee.setEmployeeId( employee.getEmployeeId());
dsEmployee.setName( employee.getName() );
dsEmployee.setDesignation(((DesignationManager) designationManager).getDSDesignationByCode(designation.getCode()));
dsEmployee.setDateOfBirth( (Date)dateOfBirth.clone() );
dsEmployee.setGender( (gender == 'M') ? GENDER.MALE: GENDER.FEMALE);
dsEmployee.setIsIndian( isIndian);
dsEmployee.setBasicSalary( basicSalary);
dsEmployee.setPANNumber( panNumber);
dsEmployee.setAadharCardNumber( aadharCardNumber);
employeeSet.add( dsEmployee );
employeeIdWiseEmployeeMap.put( dsEmployee.getEmployeeId().toUpperCase() , dsEmployee);
panNumberWiseEmployeeMap.put( panNumber.toUpperCase() , dsEmployee);
aadharCardNumberWiseEmployeeMap.put( aadharCardNumber.toUpperCase() , dsEmployee);
Set<EmployeeInterface> ets;
ets = this.designationCodeWiseEmployeeMap.get( dsEmployee.getDesignation().getCode());
if( ets == null)
{
ets = new TreeSet<>();
ets.add(dsEmployee);
designationCodeWiseEmployeeMap.put(  dsEmployee.getDesignation().getCode() , ets);
}
else
{
ets.add(dsEmployee);
}
}catch( DAOException daoException)
{
blException.setGenericException( daoException.getMessage());
throw blException;
}
}

 //**********************************Update Designation******************************************


public void updateEmployee( EmployeeInterface employee) throws BLException
{
BLException blException = new BLException();

String employeeId = employee.getEmployeeId();
String name = employee.getName();
DesignationInterface designation = employee.getDesignation();
int designationCode =0;
Date dateOfBirth = employee.getDateOfBirth();
char gender = employee.getGender();
boolean isIndian = employee.getIsIndian();
BigDecimal basicSalary = employee.getBasicSalary();
String panNumber = employee.getPANNumber();
String aadharCardNumber = employee.getAadharCardNumber();
if( employeeId == null)
{
blException.addException( "employeeId" , " EmployeeId is Required");
}
else
{
employeeId = employeeId.trim();
if( employeeId.length() == 0)
{
blException.addException(" employeeId " , " EmployeeId is Required");
}
else
{
if(employeeIdWiseEmployeeMap.containsKey( employeeId.toUpperCase())== false)
{
blException.addException( " EmployeeId "  , " Invalid EmployeeId" + employeeId);
throw blException;
}
}
}
if( name == null)
{
blException.addException( " name " , "  Invalid name ");
} 
else
{
name = name.trim();
if( name.length() == 0) blException.addException(" name " , " name Required");
}
DesignationManagerInterface designationManager;
designationManager =  DesignationManager.getDesignationManager();
if( designation == null)
{
blException.addException(" Designation " , " Designation Required");
}
else
{
designationCode = designation.getCode();
if( designationManager.DesignationCodeExists( designation.getCode()) == false)
{
blException.addException(" Designation " , " designation Required");
}
}
if( dateOfBirth == null)
{
blException.addException(" dateOfBirth" , " date of Birth Required");
}
if( gender == ' ')
{
blException.addException(" Gender " , " Gender is Required");
}
if( basicSalary == null)
{
blException.addException(" basicSalary" , " Basic Salary is Required");
}
else
{
if(basicSalary.signum() == -1)
{
blException.addException(" basicSalary" , " basic Salary can not be Negative");
}
}
if( panNumber != null && panNumber.length() > 0)
{
EmployeeInterface ee = panNumberWiseEmployeeMap.get( panNumber.toUpperCase());
if( ee != null && ee.getEmployeeId().equalsIgnoreCase( employeeId) == false)
{
blException.addException( " panNumber" , " PANNmber : " + panNumber + "  Exists");
}
}
if( aadharCardNumber != null && aadharCardNumber.length() > 0)
{
EmployeeInterface ee = aadharCardNumberWiseEmployeeMap.get( aadharCardNumber.toUpperCase());
if( ee != null && ee.getEmployeeId().equalsIgnoreCase( employeeId) == false)
{
blException.addException( " aadharCardNumber " , " Aadhar Card Number : " + aadharCardNumber + " Exists");
}
}
if( blException.hasExceptions())
{
throw blException;
}
try
{
EmployeeInterface dsEmployee;
dsEmployee = employeeIdWiseEmployeeMap.get( employeeId.toUpperCase());
String oldPANNumber = dsEmployee.getPANNumber();
String oldAadharCardNumber = dsEmployee.getAadharCardNumber();
int oldDesignationCode = dsEmployee.getDesignation().getCode();
EmployeeDAOInterface employeeDAO;
employeeDAO = new EmployeeDAO();
EmployeeDTOInterface dlEmployee;
dlEmployee = new EmployeeDTO();
dlEmployee.setEmployeeId( dsEmployee.getEmployeeId());
dlEmployee.setName( dsEmployee.getName());
dlEmployee.setDesignationCode( designation.getCode());
dlEmployee.setDateOfBirth( dateOfBirth);
dlEmployee.setGender((gender == 'M') ? GENDER.MALE : GENDER.FEMALE);
dlEmployee.setIsIndian( isIndian);
dlEmployee.setBasicSalary( basicSalary);
dlEmployee.setPANNumber( panNumber);
dlEmployee.setAadharCardNumber( aadharCardNumber);

employeeDAO.update(dlEmployee);
dsEmployee.setName( employee.getName() );
dsEmployee.setDesignation(((DesignationManager) designationManager).getDSDesignationByCode(designation.getCode()));
dsEmployee.setDateOfBirth( (Date)dateOfBirth.clone() );
dsEmployee.setGender( (gender == 'M') ? GENDER.MALE: GENDER.FEMALE);
dsEmployee.setIsIndian( isIndian);
dsEmployee.setBasicSalary( basicSalary);
dsEmployee.setPANNumber( panNumber);
dsEmployee.setAadharCardNumber( aadharCardNumber);

employeeSet.remove( dsEmployee);
employeeIdWiseEmployeeMap.remove( employeeId.toUpperCase());
panNumberWiseEmployeeMap.remove( oldPANNumber. toUpperCase());
aadharCardNumberWiseEmployeeMap.remove( oldAadharCardNumber. toUpperCase());

employeeSet.add(dsEmployee);
employeeIdWiseEmployeeMap.put( dsEmployee.getEmployeeId().toUpperCase() , dsEmployee);
panNumberWiseEmployeeMap.put( panNumber.toUpperCase() , dsEmployee);
aadharCardNumberWiseEmployeeMap.put( aadharCardNumber.toUpperCase() , dsEmployee);
if( oldDesignationCode != dsEmployee.getDesignation().getCode());
{
Set<EmployeeInterface>ets;
ets = this.designationCodeWiseEmployeeMap.get( oldDesignationCode);
ets.remove( dsEmployee);
ets = this.designationCodeWiseEmployeeMap.get( dsEmployee.getDesignation().getCode());
if( ets == null)
{
ets = new TreeSet<>();
ets.add( dsEmployee);
designationCodeWiseEmployeeMap.put( dsEmployee.getDesignation().getCode() , ets);
}
else
{
ets.add(dsEmployee);
}
}
}catch( DAOException daoException)
{
blException.setGenericException( daoException.getMessage());
throw blException;
}
}


//******************************************Remove Employee******************************************************

public void removeEmployee( String employeeId ) throws BLException
{
if( employeeId == null)
{
BLException blException = new BLException();
blException.addException(" employeeId" , " EmployeeId is invalid ");
throw blException;
}
else
{
if( employeeId.length() == 0)
{
BLException blException = new BLException();
blException.addException("employeeId "  , " Employee Id is Invalid " + employeeId );
throw blException;
}
else
{
if( employeeIdWiseEmployeeMap.containsKey( employeeId.toUpperCase()) == false)
{
BLException blException = new BLException() ;
blException.addException(" employeeid " ,  " employee Id : " + employeeId + " Exists." );
throw blException;
}
}
}

try
{
EmployeeInterface dsEmployee;
dsEmployee = employeeIdWiseEmployeeMap.get( employeeId.toUpperCase());
EmployeeDAOInterface employeeDAO;
employeeDAO = new EmployeeDAO();
employeeDAO.delete( dsEmployee.getEmployeeId());
employeeSet.remove(dsEmployee);
employeeIdWiseEmployeeMap.remove( employeeId.toUpperCase());
panNumberWiseEmployeeMap.remove( dsEmployee.getPANNumber().toUpperCase());
aadharCardNumberWiseEmployeeMap.remove( dsEmployee.getAadharCardNumber().toUpperCase());
Set<EmployeeInterface> ets;
ets = this.designationCodeWiseEmployeeMap.get( dsEmployee.getDesignation().getCode());
ets.remove( dsEmployee);
}catch( DAOException daoException)
{
BLException blException = new BLException();
blException.setGenericException( daoException.getMessage());
throw blException;
}
}


//******************************************Get Employee By Id******************************************************


public EmployeeInterface getEmployeeByEmployeeId ( String employeeId) throws BLException
{
EmployeeInterface dsEmployee = employeeIdWiseEmployeeMap.get( employeeId.toUpperCase());
if(dsEmployee == null)
{
BLException blException = new BLException();
blException.addException(" employeeId" , " employeeId is inValid :" + employeeId);
throw blException;
}
EmployeeInterface employee = new Employee();
employee.setEmployeeId( dsEmployee.getEmployeeId());
employee.setName( dsEmployee.getName());
DesignationInterface dsDesignation = dsEmployee.getDesignation();
DesignationInterface designation = new Designation();
designation.setCode( dsDesignation.getCode());
designation.setTitle( dsDesignation.getTitle());
employee.setDesignation( designation);
employee.setDateOfBirth( (Date)dsEmployee.getDateOfBirth().clone());
employee.setGender( (dsEmployee.getGender()== 'M') ? GENDER.MALE: GENDER.FEMALE);
employee.setIsIndian( dsEmployee.getIsIndian());
employee.setBasicSalary( dsEmployee.getBasicSalary());
employee.setPANNumber( dsEmployee.getPANNumber());
employee.setAadharCardNumber( dsEmployee.getAadharCardNumber());
return employee;
}


//******************************************Get Employee By PanNumber******************************************************


public EmployeeInterface getEmployeeByPANNumber ( String PANNumber ) throws BLException
{
EmployeeInterface dsEmployee = panNumberWiseEmployeeMap.get( PANNumber.toUpperCase());
if( dsEmployee == null)
{
BLException blException = new BLException();
blException.addException(" PANNumber " , " invalid PANNumber :" + PANNumber);
throw blException;
}
EmployeeInterface employee = new Employee();

employee.setEmployeeId( dsEmployee.getEmployeeId());
employee.setName( dsEmployee.getName());
DesignationInterface dsDesignation = dsEmployee.getDesignation();
DesignationInterface designation = new Designation();
designation.setCode( dsDesignation.getCode());
designation.setTitle( dsDesignation.getTitle());
employee.setDesignation( designation);
employee.setDateOfBirth( (Date)dsEmployee.getDateOfBirth().clone());
employee.setGender( (dsEmployee.getGender()== 'M') ? GENDER.MALE: GENDER.FEMALE);
employee.setIsIndian( dsEmployee.getIsIndian());
employee.setBasicSalary( dsEmployee.getBasicSalary());
employee.setPANNumber( dsEmployee.getPANNumber());
employee.setAadharCardNumber( dsEmployee.getAadharCardNumber());
return employee;
}


//******************************************Get Employee By AadharCard Number********************************************



public EmployeeInterface getEmployeeByAadharCardNumber ( String AadharCardNumber ) throws BLException
{
EmployeeInterface dsEmployee = aadharCardNumberWiseEmployeeMap.get( AadharCardNumber.toUpperCase());
if( dsEmployee == null)
{
BLException blException = new BLException();
blException.addException(" AadharCard Number  " , " invalid AadharCard Number  :" + AadharCardNumber);
throw blException;
}
EmployeeInterface employee = new Employee();
employee.setEmployeeId( dsEmployee.getEmployeeId());
employee.setName( dsEmployee.getName());
DesignationInterface dsDesignation = dsEmployee.getDesignation();
DesignationInterface designation = new Designation();
designation.setCode( dsDesignation.getCode());
designation.setTitle( dsDesignation.getTitle());
employee.setDesignation( designation);
employee.setDateOfBirth( (Date)dsEmployee.getDateOfBirth().clone());
employee.setGender( (dsEmployee.getGender()== 'M') ? GENDER.MALE: GENDER.FEMALE);
employee.setIsIndian( dsEmployee.getIsIndian());
employee.setBasicSalary( dsEmployee.getBasicSalary());
employee.setPANNumber( dsEmployee.getPANNumber());
employee.setAadharCardNumber( dsEmployee.getAadharCardNumber());
return employee;
}





//****************************************** Employee Count******************************************************



public int getEmployeeCount() 
{
return  employeeSet.size();
}

//******************************************Employee Id Exist******************************************************

public Boolean employeeIdExists ( String employeeId )
{
return employeeIdWiseEmployeeMap.containsKey( employeeId.toUpperCase());
}

//******************************************Employee PAN Number Exist******************************************************

public Boolean employeePANNumberExists ( String PANNumber )
{
return panNumberWiseEmployeeMap.containsKey( PANNumber.toUpperCase());
}

//******************************************Employee AadharCard Number Exists***********************************************

public Boolean employeeAadharCardNumberExists ( String AadharCardNumber ) 
{
return aadharCardNumberWiseEmployeeMap.containsKey( AadharCardNumber.toUpperCase()) ;
}


//******************************************Get Employee******************************************************

public Set<EmployeeInterface> getEmployees() 
{
Set<EmployeeInterface> employees = new TreeSet<>();
EmployeeInterface employee;
DesignationInterface dsDesignation;
DesignationInterface designation = new Designation();
for( EmployeeInterface dsEmployee : employeeSet)
{
employee = new Employee();
employee.setEmployeeId( dsEmployee.getEmployeeId());
employee.setName( dsEmployee.getName());
dsDesignation = dsEmployee.getDesignation();
designation.setCode( dsDesignation.getCode());
designation.setTitle( dsDesignation.getTitle());
employee.setDesignation( designation);
employee.setDateOfBirth( (Date)dsEmployee.getDateOfBirth().clone());
employee.setGender( (dsEmployee.getGender()== 'M') ? GENDER.MALE: GENDER.FEMALE);
employee.setIsIndian( dsEmployee.getIsIndian());
employee.setBasicSalary( dsEmployee.getBasicSalary());
employee.setPANNumber( dsEmployee.getPANNumber());
employee.setAadharCardNumber( dsEmployee.getAadharCardNumber());
}
return employees;
}

//******************************************Get Employee By Designation code***********************************************

public Set<EmployeeInterface> getEmployeesByDesignationCode( int designationCode) throws BLException
{
DesignationManagerInterface designationManager;
designationManager = DesignationManager.getDesignationManager();
if( designationManager.DesignationCodeExists( designationCode) == false )
{
BLException blException;
blException = new BLException();
blException.setGenericException( " Invalid Designation Code " + designationCode);
}
Set<EmployeeInterface> employees = new TreeSet<>();
Set<EmployeeInterface>ets;
ets = designationCodeWiseEmployeeMap.get( designationCode);
if( ets == null)
{
return null;
}
EmployeeInterface employee;
DesignationInterface dsDesignation;
DesignationInterface designation = new Designation();
for( EmployeeInterface dsEmployee : employeeSet)
{
employee = new Employee();
employee.setEmployeeId( dsEmployee.getEmployeeId());
employee.setName( dsEmployee.getName());
dsDesignation = dsEmployee.getDesignation();
designation.setCode( dsDesignation.getCode());
designation.setTitle( dsDesignation.getTitle());
employee.setDesignation( designation);
employee.setDateOfBirth( (Date)dsEmployee.getDateOfBirth().clone());
employee.setGender( (dsEmployee.getGender()== 'M') ? GENDER.MALE: GENDER.FEMALE);
employee.setIsIndian( dsEmployee.getIsIndian());
employee.setBasicSalary( dsEmployee.getBasicSalary());
employee.setPANNumber( dsEmployee.getPANNumber());
employee.setAadharCardNumber( dsEmployee.getAadharCardNumber());
}
return employees;
}

//******************************************Employee Count By Designation***************************************************


public int getEmployeeCountByDesignationCode ( int designationCode) throws BLException
{
Set<EmployeeInterface> ets;
ets = this.designationCodeWiseEmployeeMap.get( designationCode);
if( ets == null) return 0;
else return ets.size();
}

//******************************************Employee Alloted Designation***************************************************


public Boolean designationAlloted( int designationCode ) throws BLException
{ 
return this.designationCodeWiseEmployeeMap.containsKey( designationCode);
}


}