package com.Employee.Management.hr.dl.dao;
import com.Employee.Management.hr.dl.dto.*;
import com.Employee.Management.hr.dl.interfaces.dto.*;
import com.Employee.Management.hr.dl.interfaces.dao.*;
import com.Employee.Management.hr.dl.exceptions.*;

import java.util.*;
import java.sql.*;

public class DesignationDAO implements DesignationDAOInterface
{
private final static String FILE_NAME = "Designation.data";

//**********************Add Designation*****************************************


public void add(DesignationDTOInterface designationDTO) throws DAOException
{
if( designationDTO == null) throw new DAOException(" Designation is null");
String title = designationDTO.getTitle();
if( title == null) throw new DAOException(" Designation is null");
title = title.trim();
if( title.length() == 0) throw new DAOException(" Length of Title is zero");

try
{
Connection connection = DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement = connection.prepareStatement("select code from designation where title =?");
preparedStatement.setString( 1 , title);
ResultSet resultSet;
resultSet = preparedStatement.executeQuery();
if( resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException(" Designation :" + title + " Exists.");
}
resultSet.close();
preparedStatement.close();
preparedStatement = connection.prepareStatement(" insert into designation (title) values (?)" , Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString( 1 , title);
preparedStatement.executeUpdate();
resultSet = preparedStatement.getGeneratedKeys();
resultSet.next();
int code = resultSet.getInt(1);
resultSet.close();
preparedStatement.close();
connection.close();
designationDTO.setCode(code);
}catch( SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}


//********************** Update Designation*****************************************


public void update(DesignationDTOInterface designationDTO) throws DAOException
{
if(designationDTO == null) throw new DAOException(" Designation is null ");
int code = designationDTO.getCode();
if(code <= 0) throw new DAOException(" Invalid Code :" + code);
String title = designationDTO.getTitle();
if(title == null) throw new DAOException(" Designation is null ");
title = title.trim();
if(title.length() == 0) throw new DAOException( " length of designation is  zero  ");

try
{
Connection connection = DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement = connection.prepareStatement(" select code from designation where code = ?");
preparedStatement.setInt( 1 ,code);
ResultSet resultSet = preparedStatement.executeQuery();
if( resultSet.next() == false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException(" code :" + code + " does not Exist.");
}
preparedStatement = connection.prepareStatement(" select code from designation where title = ? and code <> ?");
preparedStatement.setString(1 , title);
preparedStatement.setInt( 2 , code);
resultSet = preparedStatement.executeQuery();
if( resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("  Designation title :" + title + " Exists.");
}
resultSet.close();
preparedStatement.close();
preparedStatement = connection.prepareStatement( " update designation set title = ? where code  = ?");
preparedStatement.setString( 1 , title);
preparedStatement.setInt( 2 , code);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
} catch( SQLException sqlException)
{
throw new DAOException( sqlException.getMessage());
}
}


//**********************Delete Designation*****************************************


public void delete( int code ) throws DAOException
{
if(code <= 0) throw new DAOException(" Invalid Code :" + code);
try
{
Connection connection = DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement = connection.prepareStatement(" select * from designation where code = ?");
preparedStatement.setInt( 1 , code);
ResultSet resultSet = preparedStatement.executeQuery();
if( resultSet.next() == false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException(" code :" + code + " does not exist.");
}
String title = resultSet.getString("title").trim();
resultSet.close();
preparedStatement.close();
preparedStatement = connection.prepareStatement(" select gender from employee where designation_code = ?");
preparedStatement.setInt( 1 , code);
resultSet = preparedStatement.executeQuery();
if( resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException(" cannot Delete designation :" + title + "  as it has been alloted to employee(s)");
}
resultSet.close();
preparedStatement.close();
preparedStatement = connection.prepareStatement(" delete from designation where code = ?");
preparedStatement.setInt( 1 , code);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch( SQLException sqlexception)
{
throw new DAOException( sqlexception.getMessage());
}
}


//**********************Get All Designation*****************************************


public Set<DesignationDTOInterface> getall() throws DAOException
{
Set<DesignationDTOInterface> designations;
designations = new TreeSet<>();

try
{
Connection connection = DAOConnection.getConnection();
Statement statement = connection.createStatement();
ResultSet resultSet = statement.executeQuery(" select * from designation");
DesignationDTOInterface designationDTO;
while( resultSet.next())
{
designationDTO = new DesignationDTO();
designationDTO.setCode(resultSet.getInt("code"));
designationDTO.setTitle(resultSet.getString("title").trim());
designations.add(designationDTO);
}
resultSet.close();
statement.close();
connection.close();
return designations;
}catch( SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}

//**********************Get By Code Designation*****************************************


public DesignationDTOInterface getByCode( int code ) throws DAOException
{
if(code <= 0 ) throw new DAOException(" Invalid Code :"  + code);

try
{
Connection connection = DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement = connection.prepareStatement(" select code from designation where code = ?");
preparedStatement.setInt( 1 , code);
ResultSet resultSet = preparedStatement.executeQuery();
if( resultSet.next() == false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException(" code :" + code + " does not exist");
}
DesignationDTOInterface designationDTO = new DesignationDTO();
designationDTO.setCode(code);
designationDTO.setTitle(resultSet.getString("title").trim());
resultSet.close();
preparedStatement.close();
connection.close();
return designationDTO;
}catch( SQLException sqlExcpetion )
{
throw new DAOException(  sqlExcpetion.getMessage());
}
}


//**********************Get By Title Designation*****************************************


public DesignationDTOInterface getByTitle( String Title) throws DAOException
{
if(Title == null || Title.trim().length() == 0 ) throw new DAOException(" Invalid Title :" + Title );
Title = Title.trim();

try
{
Connection connection = DAOConnection.getConnection();
PreparedStatement  preparedStatement;
preparedStatement = connection.prepareStatement(" select * from designation where Title = ?");
preparedStatement.setString( 1 , Title);
ResultSet resultSet = preparedStatement.executeQuery();
if( resultSet.next() == false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException(" Designation Title :" + Title + " does not exist.");
}
DesignationDTOInterface designationDTO = new DesignationDTO();
designationDTO.setCode( resultSet.getInt("code"));
designationDTO.setTitle( resultSet.getString("Title").trim());
resultSet.close();
preparedStatement.close();
connection.close();
return designationDTO;
} catch( SQLException sqlException )
{
throw new  DAOException(sqlException.getMessage());
}
}

//**********************Code Exists*****************************************


public boolean CodeExists( int code ) throws DAOException
{
if(code <=0) return false;

try
{
boolean exists;
Connection connection = DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement = connection.prepareStatement(" select code from designation where code = ?");
preparedStatement.setInt( 1 , code);
ResultSet resultSet = preparedStatement.executeQuery();
exists = resultSet.next();
resultSet.close();
preparedStatement.close();
connection.close();
return exists;
} catch( SQLException sqlException)
{
throw new DAOException( sqlException.getMessage());
}
}

//********************** Title Exists*****************************************


public boolean TitleExists( String Title) throws DAOException
{
if(Title == null || Title.trim().length() ==  0) return false;
Title = Title.trim();

try
{
boolean exists;
Connection connection = DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement = connection.prepareStatement(" select code from designation where title = ?");
preparedStatement.setString( 1 ,Title);
ResultSet  resultSet = preparedStatement.executeQuery();
exists = resultSet.next();
resultSet.close();
preparedStatement.close();
connection.close();
return exists;
}catch( SQLException sqlException )
{
throw new DAOException( sqlException.getMessage());
}
}

//**********************Count Designation*****************************************


public int getCount() throws DAOException
{

try
{
Connection connection = DAOConnection.getConnection();
Statement statement;
statement = connection.createStatement();
ResultSet resultSet;
resultSet  = statement.executeQuery(" select count (*) as ent from designation");
resultSet.next();
int count = resultSet.getInt("code");
resultSet.close();
statement.close();
connection.close();
return count;
}catch( SQLException sqlException)
{
throw new DAOException( sqlException.getMessage());
}
}
}