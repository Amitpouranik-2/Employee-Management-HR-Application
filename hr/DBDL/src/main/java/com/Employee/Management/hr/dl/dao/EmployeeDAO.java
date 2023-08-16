package com.Employee.Management.hr.dl.dao;
import java.util.*;
import java.io.*;
import java.sql.*;
import java.text.*;
import java.math.*;
import com.Employee.Management.hr.dl.interfaces.dao.*;
import com.Employee.Management.hr.dl.exceptions.*;
import com.Employee.Management.hr.dl.interfaces.dto.*;
import com.Employee.Management.hr.dl.dto.*;
import com.Employee.Management.enums.*;

public class EmployeeDAO implements EmployeeDAOInterface
{

private static  final String FILE_NAME = "employee.data";

// ***************************Add Employee*****************************************

public void add(EmployeeDTOInterface employeeDTO)throws DAOException
{

if( employeeDTO == null) throw new DAOException(" Emoployee is null .");

String employeeId;
String name = employeeDTO.getName();
if( name == null ) throw new DAOException(" Name is null");
name = name.trim();
if( name.length() == 0) throw new DAOException(" Name length is zero");
int designationCode = employeeDTO.getDesignationCode();
if( designationCode <= 0) throw new DAOException("Invalid designation Code : " + designationCode);
Connection connection = null;
PreparedStatement preparedStatement;
ResultSet resultSet;
try
{
connection = DAOConnection.getConnection();
preparedStatement = connection.prepareStatement(" select code from designation where code = ?");
preparedStatement.setInt( 1 , designationCode);
resultSet = preparedStatement.executeQuery();
if( resultSet.next() == false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException(" Invalid Designation code " + designationCode);
}
resultSet.close();
preparedStatement.close();
}catch( SQLException sqlException)
{
throw new DAOException( sqlException.getMessage());
}
java.util.Date dateOfBirth = employeeDTO.getDateOfBirth();
if( dateOfBirth == null) 
{
try
{
connection.close();
}catch( SQLException sqlException)
{
throw new DAOException( sqlException.getMessage());
}
throw new DAOException( " Date of Birth is null");
}
char gender = employeeDTO.getGender();
if( gender == ' ') 
{
try
{
connection.close();
}catch( SQLException sqlException)
{
throw new DAOException( sqlException.getMessage());
}
throw new DAOException(" Gender not Set to Male / Female");
}
boolean isIndian = employeeDTO.getIsIndian();
BigDecimal basicSalary = employeeDTO.getBasicSalary();
if( basicSalary == null) 
{
try
{
connection.close();
}catch( SQLException sqlException)
{
throw new DAOException( sqlException.getMessage());
}
throw new DAOException(" Basic Salary is null");
}
if( basicSalary.signum() == -1)
{
try
{
connection.close();
}catch( SQLException sqlException)
{
throw new DAOException( sqlException.getMessage());
}
throw new DAOException(" Basic Salary is Negative");
}
String panNumber = employeeDTO.getPANNumber();
if( panNumber == null)
{
try
{
connection.close();
}catch( SQLException sqlException)
{
throw new DAOException( sqlException.getMessage());
}
throw new DAOException(" PANNumber is NULL");
}
panNumber = panNumber.trim();
if( panNumber.length() == 0)
{
try
{
connection.close();
}catch( SQLException sqlException)
{
throw new DAOException( sqlException.getMessage());
}
throw new DAOException(" Length of Pan Number  is zero");
}
String aadharCardNumber = employeeDTO.getAadharCardNumber();
if( aadharCardNumber == null) 
{
try
{
connection.close();
}catch( SQLException sqlException)
{
throw new DAOException( sqlException.getMessage());
}
throw new DAOException(" Aadhar Card Number is NULL");
}
aadharCardNumber = aadharCardNumber.trim();
if( aadharCardNumber.length() == 0) 
{
try
{
connection.close();
}catch( SQLException sqlException)
{
throw new DAOException( sqlException.getMessage());
}
throw new DAOException("  length of Aadhar Card Number  is Zero");
}
try
{
boolean panNumberExists;
preparedStatement = connection.prepareStatement(" select gender from employee where pan_number = ?");
preparedStatement.setString( 1 , panNumber);
resultSet = preparedStatement.executeQuery();
panNumberExists = resultSet.next();
resultSet.close();
preparedStatement.close();
boolean aadharCardNumberExists;
preparedStatement = connection.prepareStatement(" select gender from employee where  aadhar_card_number = ?");
preparedStatement.setString( 1 , aadharCardNumber);
resultSet = preparedStatement.executeQuery();
aadharCardNumberExists = resultSet.next();
resultSet.close();
preparedStatement.close();
if( panNumberExists && aadharCardNumberExists )
{
try
{
connection.close();
}catch( SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
throw new DAOException(" PAN Number (" + panNumber + " ) and Aadhar Card Number (" + aadharCardNumber + ") Exists. ");
}
if( panNumberExists)
{
try
{
connection.close();
}catch( SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
throw new DAOException(" PANNumber  ( " + panNumber + " ) exists.");
}
if( aadharCardNumberExists)
{
try
{
connection.close();
}catch( SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
throw new DAOException(" Aadhar Card Number  ( " + aadharCardNumber + " ) exists.");
}

preparedStatement = connection.prepareStatement(" insert into employee (name , designation_code , date_of_birth , basic_salary , gender , is_indian , pan_number , aadhar_card_number )  values ( ? ,?, ?, ?, ?, ?, ?, ?)" , Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString( 1 , name);
preparedStatement.setInt( 2 , designationCode);
java.sql.Date sqlDateOfBirth = new java.sql.Date( dateOfBirth.getYear()  , dateOfBirth.getMonth() , dateOfBirth.getDate());
preparedStatement.setDate( 3 , sqlDateOfBirth);
preparedStatement.setBigDecimal( 4 , basicSalary);
preparedStatement.setString( 5 , String.valueOf(gender));
preparedStatement.setBoolean( 6 , isIndian);
preparedStatement.setString( 7 , panNumber);
preparedStatement.setString( 8 , aadharCardNumber);
preparedStatement.executeUpdate();
resultSet = preparedStatement.getGeneratedKeys();
resultSet.next();
int generatedEmployeeId = resultSet.getInt(1);
resultSet.close();
preparedStatement.close();
connection.close();
employeeDTO.setEmployeeId("A" + ( 1000000 + generatedEmployeeId ));
}catch( SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}

// ***************************Update Employee*****************************************

public void update( EmployeeDTOInterface employeeDTO) throws DAOException
{

if( employeeDTO == null) throw new DAOException(" Employee is Null");
String employeeid;
employeeid = employeeDTO.getEmployeeId();
if( employeeid == null) throw new DAOException(" Employee Id is null");
employeeid = employeeid.trim();
if( employeeid.length() == 0) throw new DAOException(" length of employee id is zero");
int actualEmployeeId;
try
{
actualEmployeeId = Integer.parseInt( employeeid.substring(1))- 1000000;
}catch( Exception exception)
{
throw new DAOException(" Invalid Employee Id");
}
String name = employeeDTO.getName();
if( name == null) throw new DAOException(" Name is null");
name = name.trim();
if( name.length() == 0) throw new DAOException(" length of name is  Zero ");
int designationCode = employeeDTO.getDesignationCode();
if( designationCode <= 0) throw new DAOException(" Invalid Designation Code :" + designationCode);
Connection connection = null;
PreparedStatement preparedStatement;
ResultSet resultSet;
try
{
connection = DAOConnection.getConnection();
preparedStatement = connection.prepareStatement(" select code from designation where code = ?");
preparedStatement.setInt( 1 , designationCode);
resultSet = preparedStatement.executeQuery();
if( resultSet.next() == false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException(" Invalid Designation code " + designationCode);
}
resultSet.close();
preparedStatement.close();
}catch( SQLException sqlException)
{
throw new DAOException( sqlException.getMessage());
}
java.util.Date dateOfBirth = employeeDTO.getDateOfBirth();
if( dateOfBirth == null) 
{
try
{
connection.close();
}catch( SQLException sqlException)
{
throw new DAOException( sqlException.getMessage());
}
throw new DAOException( " Date of Birth is null");
}
char gender = employeeDTO.getGender();
if( gender == ' ') 
{
try
{
connection.close();
}catch( SQLException sqlException)
{
throw new DAOException( sqlException.getMessage());
}
throw new DAOException(" Gender not Set to Male / Female");
}
boolean isIndian = employeeDTO.getIsIndian();
BigDecimal basicSalary = employeeDTO.getBasicSalary();
if( basicSalary == null) 
{
try
{
connection.close();
}catch( SQLException sqlException)
{
throw new DAOException( sqlException.getMessage());
}
throw new DAOException(" Basic Salary is null");
}
if( basicSalary.signum() == -1)
{
try
{
connection.close();
}catch( SQLException sqlException)
{
throw new DAOException( sqlException.getMessage());
}
throw new DAOException(" Basic Salary is Negative");
}
String panNumber = employeeDTO.getPANNumber();
if( panNumber == null)
{
try
{
connection.close();
}catch( SQLException sqlException)
{
throw new DAOException( sqlException.getMessage());
}
throw new DAOException(" PANNumber is NULL");
}
panNumber = panNumber.trim();
if( panNumber.length() == 0)
{
try
{
connection.close();
}catch( SQLException sqlException)
{
throw new DAOException( sqlException.getMessage());
}
throw new DAOException(" Length of Pan Number  is zero");
}
String aadharCardNumber = employeeDTO.getAadharCardNumber();
if( aadharCardNumber == null) 
{
try
{
connection.close();
}catch( SQLException sqlException)
{
throw new DAOException( sqlException.getMessage());
}
throw new DAOException(" Aadhar Card Number is NULL");
}
aadharCardNumber = aadharCardNumber.trim();
if( aadharCardNumber.length() == 0) 
{
try
{
connection.close();
}catch( SQLException sqlException)
{
throw new DAOException( sqlException.getMessage());
}
throw new DAOException("  length of Aadhar Card Number  is Zero");
}
try
{
boolean panNumberExists;
preparedStatement = connection.prepareStatement(" select gender from employee where pan_number = ? and emoployee_id <> ?");
preparedStatement.setString( 1 , panNumber);
preparedStatement.setInt( 2 , actualEmployeeId);
resultSet = preparedStatement.executeQuery();
panNumberExists = resultSet.next();
resultSet.close();
preparedStatement.close();
boolean aadharCardNumberExists;
preparedStatement = connection.prepareStatement(" select gender from employee where aadhar_card_number = ? and employee_id <> ?");
preparedStatement.setString( 1 , aadharCardNumber);
preparedStatement.setInt( 2 , actualEmployeeId);
resultSet = preparedStatement.executeQuery();
aadharCardNumberExists = resultSet.next();
resultSet.close();
preparedStatement.close();
if( panNumberExists && aadharCardNumberExists )
{
try
{
connection.close();
}catch( SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
throw new DAOException(" PAN Number (" + panNumber + " ) and Aadhar Card Number (" + aadharCardNumber + ") Exists. ");
}
if( panNumberExists)
{
try
{
connection.close();
}catch( SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
throw new DAOException(" PANNumber  ( " + panNumber + " ) exists.");
}
if( aadharCardNumberExists)
{
try
{
connection.close();
}catch( SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
throw new DAOException(" Aadhar Card Number  ( " + aadharCardNumber + " ) exists.");
}
preparedStatement = connection.prepareStatement(" update employee set name = ? , designation_code = ? , date_of_birth = ? ,  basic_salary = ? , gender = ? , is_indian = ? , pan_number = ? , aadhar_card_number = ? where employee_id = ?");
preparedStatement.setString( 1 , name);
preparedStatement.setInt( 2 , designationCode);
java.sql.Date sqlDateOfBirth = new java.sql.Date( dateOfBirth.getYear()  , dateOfBirth.getMonth() , dateOfBirth.getDate());
preparedStatement.setDate( 3 , sqlDateOfBirth);
preparedStatement.setBigDecimal( 4 , basicSalary);
preparedStatement.setString( 5 , String.valueOf(gender));
preparedStatement.setBoolean( 6 , isIndian);
preparedStatement.setString( 7 , panNumber);
preparedStatement.setString( 8 , aadharCardNumber);
preparedStatement.setInt( 9 , actualEmployeeId);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch( SQLException sqlexception)
{
throw new DAOException(sqlexception.getMessage());
}
}

// ***************************Delete Employee*****************************************

public void delete(String EmployeeId) throws DAOException
{
if( EmployeeId == null) throw new DAOException(" employee id is null ");
EmployeeId = EmployeeId.trim();
if( EmployeeId.length() == 0) throw new DAOException("length of employee id is zero");
int actualEmployeeId;
try
{
actualEmployeeId = Integer.parseInt( EmployeeId.substring(1))- 1000000;
}catch( Exception exception)
{
throw new DAOException(" Invalid Employee Id");
}
Connection connection = DAOConnection.getConnection();
PreparedStatement preparedStatement;
ResultSet resultSet;
try
{
preparedStatement = connection.prepareStatement(" select gender from employee where employee_id = ?");
preparedStatement.setInt( 1 , actualEmployeeId);
resultSet = preparedStatement.executeQuery();
if( resultSet.next() == false)
{
resultSet.close();
preparedStatement.close();
connection.close();
}
resultSet.close();
preparedStatement.close();
preparedStatement = connection.prepareStatement(" delete from employee where employee_id = ? ");
preparedStatement.setInt( 1 , actualEmployeeId);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch( SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}

// ***************************Get All Employee*****************************************

public Set<EmployeeDTOInterface>getall() throws DAOException
{
Set<EmployeeDTOInterface>employees = new TreeSet<>();
EmployeeDTOInterface employeeDTO = null;
try
{
Connection connection = DAOConnection.getConnection();
Statement statement;
statement =  connection.createStatement();
ResultSet resultSet = statement.executeQuery(" select * from employee");
String gender;
java.util.Date  utilDateOfBirth;
java.sql.Date  sqlDateOfBirth;
while(resultSet.next())
{
employeeDTO = new EmployeeDTO();
employeeDTO.setEmployeeId( "A" + ( 1000000 + resultSet.getInt("employee_id")));
employeeDTO.setName(resultSet.getString("name").trim());
employeeDTO.setDesignationCode( resultSet.getInt("designation_code"));
sqlDateOfBirth = resultSet.getDate("date_of_birth");
utilDateOfBirth = new java.util.Date( sqlDateOfBirth.getYear() , sqlDateOfBirth.getMonth() , sqlDateOfBirth.getDate());
employeeDTO.setDateOfBirth(sqlDateOfBirth);
employeeDTO.setBasicSalary(resultSet.getBigDecimal("basic_salary"));
gender = resultSet.getString("gender");
if( gender.equals("M"))
{
employeeDTO.setGender(GENDER.MALE);
}
if( gender.equals("F"))
{
employeeDTO.setGender(GENDER.FEMALE);
}
employeeDTO.setIsIndian(resultSet.getBoolean("is_indian"));
employeeDTO.setPANNumber( resultSet.getString("pan_number").trim());
employeeDTO.setAadharCardNumber( resultSet.getString("aadhar_card_number").trim());
employees.add(employeeDTO);
}
resultSet.close();
statement.close();
connection.close();
}catch( SQLException sqlexception)
{
throw new DAOException(sqlexception.getMessage());
}
return employees;
}


// ***************************Get By Designation Code*****************************************

public Set<EmployeeDTOInterface>getByDesignationcode( int Designationcode) throws  DAOException
{
Set<EmployeeDTOInterface>employees = new TreeSet<>();
EmployeeDTOInterface employeeDTO = null;
try
{
Connection connection = DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement = connection.prepareStatement(" select code from designation where code = ?");
preparedStatement.setInt( 1 , Designationcode);
ResultSet resultSet;
resultSet = preparedStatement.executeQuery();
if( resultSet.next() == false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid designation Code : "+ Designationcode);
}
resultSet.close();
preparedStatement.close();
preparedStatement = connection.prepareStatement(" select * from employee where designation_code = ?");
preparedStatement.setInt( 1 , Designationcode);
resultSet = preparedStatement.executeQuery();
String gender;
java.util.Date utilDateOfBirth;
java.sql.Date sqlDateOfBirth;
while( resultSet.next())
{
employeeDTO = new EmployeeDTO();
employeeDTO.setEmployeeId( "A" + ( 1000000 + resultSet.getInt("employee_id")));
employeeDTO.setName( resultSet.getString("name").trim());
employeeDTO.setDesignationCode( resultSet.getInt("designation_code"));
sqlDateOfBirth = resultSet.getDate("date_of_birth");
utilDateOfBirth = new java.util.Date( sqlDateOfBirth.getYear() , sqlDateOfBirth.getMonth() , sqlDateOfBirth.getDate());
employeeDTO.setDateOfBirth( utilDateOfBirth);
employeeDTO.setBasicSalary( resultSet.getBigDecimal("basic_salary"));
gender = resultSet.getString("gender");
if( gender.equals("M"))
{
employeeDTO.setGender(GENDER.MALE);
}
if(gender.equals("F"))
{
employeeDTO.setGender(GENDER.FEMALE);
}
employeeDTO.setIsIndian(resultSet.getBoolean("is_indian"));
employeeDTO.setPANNumber( resultSet.getString("pan_number").trim());
employeeDTO.setAadharCardNumber( resultSet.getString("aadhar_card_number").trim());
employees.add(employeeDTO);
}
}catch( SQLException sqlexception)
{
throw new DAOException( sqlexception.getMessage());
}
return employees;
}

// ***************************Is Designation Alloted*****************************************

public boolean isDesignationAlloted( int Designationcode) throws DAOException
{
boolean designationAlloted = false;
try
{
Connection connection = DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement = connection.prepareStatement(" select code from designation where  code = ?");
preparedStatement.setInt( 1, Designationcode);
ResultSet resultSet = preparedStatement.executeQuery();
if(resultSet.next() == false )
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException(" Invalid designation Code :" + Designationcode);
}
resultSet.close();
preparedStatement.close();
preparedStatement = connection.prepareStatement(" select gender from employee where designation_code = ?");
preparedStatement.setInt( 1 , Designationcode);
resultSet = preparedStatement.executeQuery();
designationAlloted = resultSet.next();
resultSet.close();
preparedStatement.close();
connection.close();
}catch(  SQLException sqlexception)
{
throw new DAOException( sqlexception.getMessage());
}
return designationAlloted;
}

// ***************************Get By Employee Id*****************************************

public EmployeeDTOInterface getByEmployeeId( String EmployeeId) throws DAOException
{
if(EmployeeId == null) throw new DAOException(" Invalid Employee Id :" + EmployeeId);
EmployeeId = EmployeeId.trim();
if(EmployeeId.length() == 0) throw new DAOException(" Invalid Length of EmployeeId :" + EmployeeId);
int actualEmployeeId = 0;
try
{
actualEmployeeId = Integer.parseInt( EmployeeId.substring(1))- 1000000;
}catch( Exception exception)
{
throw new DAOException( exception.getMessage());
}
EmployeeDTOInterface  employeeDTO;
try
{
Connection connection = DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement = connection.prepareStatement(" select * from employee where employee_id = ?");
preparedStatement.setInt( 1 , actualEmployeeId);
ResultSet resultSet = preparedStatement.executeQuery();
if( resultSet.next() == false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException(" Invalid Employee Id :" + EmployeeId);
}
java.util.Date utilDateOfBirth;
java.sql.Date sqlDateOfBirth;
String gender;
employeeDTO = new EmployeeDTO();
employeeDTO.setEmployeeId( "A" + ( 1000000 + resultSet.getInt("employee_id")));
employeeDTO.setName( resultSet.getString("name").trim());
employeeDTO.setDesignationCode( resultSet.getInt("designation_code"));
sqlDateOfBirth = resultSet.getDate("date_of_birth");
utilDateOfBirth = new java.util.Date( sqlDateOfBirth.getYear() , sqlDateOfBirth.getMonth() , sqlDateOfBirth.getDate());
employeeDTO.setDateOfBirth( utilDateOfBirth);
employeeDTO.setBasicSalary( resultSet.getBigDecimal("basic_salary"));
gender = resultSet.getString("gender");
if( gender.equals("M"))
{
employeeDTO.setGender(GENDER.MALE);
}
if( gender.equals("F"))
{
employeeDTO.setGender(GENDER.FEMALE);
}
employeeDTO.setIsIndian( resultSet.getBoolean("is_indian"));
employeeDTO.setPANNumber( resultSet.getString("pan_number").trim());
employeeDTO.setAadharCardNumber( resultSet.getString("aadhar_card_number").trim());
resultSet.close();
preparedStatement.close();
connection.close();
}catch( SQLException sqlexception )
{
throw new DAOException(sqlexception.getMessage());
}
return employeeDTO;
}

// ***************************Get By Aadhar Card Number *****************************************

public EmployeeDTOInterface getByAadharCard( String AadharCardNumber ) throws DAOException
{
if( AadharCardNumber == null) throw new DAOException(" Invalid Aadhar Card Number :" + AadharCardNumber);
AadharCardNumber = AadharCardNumber.trim();
if(AadharCardNumber.length() == 0)
{
throw new DAOException(" Invalid Aadhar Card Number :" + AadharCardNumber);
}
EmployeeDTOInterface employeeDTO = null;
try
{
Connection connection = DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement = connection.prepareStatement(" select * from employee where aadhar_card_number = ?");
preparedStatement.setString( 1 , AadharCardNumber);
ResultSet resultSet = preparedStatement.executeQuery();
if( resultSet.next() == false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException(" Invalid Aadhar Card Number :" + AadharCardNumber );
}
java.util.Date utilDateOfBirth;
java.sql.Date sqlDateOfBirth;
String gender;
employeeDTO = new EmployeeDTO();
employeeDTO.setEmployeeId( "A" + ( 1000000 + resultSet.getInt("employee_id")));
employeeDTO.setName( resultSet.getString("name").trim());
employeeDTO.setDesignationCode( resultSet.getInt("designation_code"));
sqlDateOfBirth = resultSet.getDate("date_of_birth");
utilDateOfBirth = new java.util.Date( sqlDateOfBirth.getYear() , sqlDateOfBirth.getMonth() , sqlDateOfBirth.getDate());
employeeDTO.setDateOfBirth( utilDateOfBirth);
employeeDTO.setBasicSalary( resultSet.getBigDecimal("basic_salary"));
gender = resultSet.getString("gender");
if( gender.equals("M"))
{
employeeDTO.setGender(GENDER.MALE);
}
if( gender.equals("F"))
{
employeeDTO.setGender(GENDER.FEMALE);
}
employeeDTO.setIsIndian( resultSet.getBoolean("is_indian"));
employeeDTO.setPANNumber( resultSet.getString("pan_number").trim());
employeeDTO.setAadharCardNumber( resultSet.getString("aadhar_card_number").trim());
resultSet.close();
preparedStatement.close();
connection.close();
}catch( SQLException sqlexception)
{
throw new DAOException(sqlexception.getMessage());
}
return employeeDTO;
}




// *************************** Get By PAN Number*****************************************



public EmployeeDTOInterface getByPANNumber( String PANNumber) throws DAOException
{
if(PANNumber == null) throw new DAOException(" Invalid PAN Number :" + PANNumber);
PANNumber = PANNumber.trim();
if(PANNumber.length() == 0)
{
throw new DAOException(" Invalid PAN Number :" + PANNumber);
}
EmployeeDTOInterface employeeDTO;
try
{
Connection connection = DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement = connection.prepareStatement(" select * from employee where pan_number = ?");
preparedStatement.setString( 1 , PANNumber);
ResultSet resultSet = preparedStatement.executeQuery();
if(resultSet.next() == false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid PAN Number :" + PANNumber);
}
java.util.Date utilDateOfBirth;
java.sql.Date sqlDateOfBirth;
String gender;
employeeDTO = new EmployeeDTO();
employeeDTO.setEmployeeId( "A" + ( 1000000 + resultSet.getInt("employee_id")));
employeeDTO.setName( resultSet.getString("name").trim());
employeeDTO.setDesignationCode( resultSet.getInt("designation_code"));
sqlDateOfBirth = resultSet.getDate("date_of_birth");
utilDateOfBirth = new java.util.Date( sqlDateOfBirth.getYear() , sqlDateOfBirth.getMonth() , sqlDateOfBirth.getDate());
employeeDTO.setDateOfBirth( utilDateOfBirth);
employeeDTO.setBasicSalary( resultSet.getBigDecimal("basic_salary"));
gender = resultSet.getString("gender");
if( gender.equals("M"))
{
employeeDTO.setGender(GENDER.MALE);
}
if( gender.equals("F"))
{
employeeDTO.setGender(GENDER.FEMALE);
}
employeeDTO.setIsIndian( resultSet.getBoolean("is_indian"));
employeeDTO.setPANNumber( resultSet.getString("pan_number").trim());
employeeDTO.setAadharCardNumber( resultSet.getString("aadhar_card_number").trim());
resultSet.close();
preparedStatement.close();
connection.close();
}catch( SQLException sqlexception)
{
throw new DAOException(sqlexception.getMessage());
}
return employeeDTO;
}

// ***************************Employee ID Exists*****************************************


public boolean EmployeeIDExists( String EmployeeId) throws DAOException
{
if( EmployeeId == null) return false;
EmployeeId =  EmployeeId.trim();
if( EmployeeId.length() == 0 ) return false;
boolean exists = false;
int actualEmployeeId =0;
try
{
actualEmployeeId = Integer.parseInt(EmployeeId.substring(1))- 1000000;
}catch( Exception exception )
{
throw new DAOException(" Invalid Employee ID :" + EmployeeId);
}
try
{
Connection connection = DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement = connection.prepareStatement(" select gender from employee where employee_id = ?");
preparedStatement.setInt( 1 , actualEmployeeId);
ResultSet resultSet ;
resultSet = preparedStatement.executeQuery();
exists = resultSet.next();
resultSet.close();
preparedStatement.close();
connection.close();
}catch( SQLException sqlexception)
{
throw new DAOException(sqlexception.getMessage());
}
return exists;
}


// ***************************PAN Number Exists*****************************************

public boolean PANNumberExists( String PANNumber) throws DAOException
{
if(PANNumber == null)  return false;
PANNumber = PANNumber.trim();
if(PANNumber.length() == 0) return false;
boolean exists = false;
try
{
Connection connection = DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement = connection.prepareStatement(" select gender from employee where pan_number = ? ");
preparedStatement.setString( 1 , PANNumber);
ResultSet resultSet;
resultSet = preparedStatement.executeQuery();
exists = resultSet.next();
resultSet.close();
preparedStatement.close();
connection.close();
}catch( SQLException sqlexception)
{
throw new DAOException( sqlexception.getMessage());
}
return exists;
}

// ***************************Aadhar Card Number Exists*****************************************

public boolean AadharCardNumberExists( String AadharCardNumber) throws DAOException
{
if(AadharCardNumber == null)  return false;
AadharCardNumber= AadharCardNumber.trim();
if(AadharCardNumber.length() == 0) return false;
boolean exists = false;
try
{
Connection connection = DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement  = connection.prepareStatement(" select gender from employee where aadhar_card_number = ?");
preparedStatement.setString( 1 , AadharCardNumber);
ResultSet resultSet;
resultSet = preparedStatement.executeQuery();
exists = resultSet.next();
resultSet.close();
preparedStatement.close();
connection.close();
}catch( SQLException sqlexception)
{
throw new DAOException( sqlexception.getMessage());
}
return exists;
}


// ***************************Get Count*****************************************

public int getcount() throws DAOException
{
int count =0;
try
{
Connection connection = DAOConnection.getConnection();
Statement statement = connection.createStatement();
ResultSet resultSet = statement.executeQuery(" select count(*) as cnt from employee");
resultSet.next();
count = resultSet.getInt("cnt");
resultSet.close();
statement.close();
connection.close();
}catch( SQLException sqlexception)
{
throw new DAOException( sqlexception.getMessage());
}
return count;
}

// ***************************Get Count By Designation*****************************************


public int getcountByDesignation( int Designationcode) throws DAOException
{
int count = 0;
try
{
Connection connection = DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement = connection.prepareStatement(" select count(*) as cnt from employee where designation_code = ?");
preparedStatement.setInt( 1 , Designationcode);
ResultSet resultSet = preparedStatement.executeQuery();
resultSet.next();
count = resultSet.getInt("cnt");
resultSet.close();
preparedStatement.close();
connection.close();
}catch( SQLException sqlexception)
{
throw new DAOException( sqlexception.getMessage());
}
return count;
}
}

