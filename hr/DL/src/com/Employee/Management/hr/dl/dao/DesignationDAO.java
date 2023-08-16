package com.Employee.Management.hr.dl.dao;
import com.Employee.Management.hr.dl.dto.*;
import com.Employee.Management.hr.dl.interfaces.dto.*;
import com.Employee.Management.hr.dl.interfaces.dao.*;
import com.Employee.Management.hr.dl.exceptions.*;
import java.util.*;
import java.io.*;

public class DesignationDAO implements DesignationDAOInterface
{
private final static String FILE_NAME = "Designation.data";

// ****************************** Add Designation *****************************************


public void add(DesignationDTOInterface designationDTO) throws DAOException
{
if(designationDTO == null) throw new DAOException("Designation is NULL");
String title = designationDTO.getTitle();
if(title == null) throw new DAOException("Designation is null");
title = title.trim();
if(title.length() == 0) throw new  DAOException(" Length of Designation is zero ");
try
{
File file = new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile( file , "rw");
int LastGeneratedCode = 0;
int RecordCount = 0;
String LastGeneratedCodeString = "";
String RecordCountString = "";
if(randomAccessFile.length() == 0)
{
LastGeneratedCodeString = "0";
while(LastGeneratedCodeString.length() < 10) LastGeneratedCodeString += " ";
RecordCountString = "0";
while(RecordCountString.length() < 10) RecordCountString += " ";
randomAccessFile.writeBytes(LastGeneratedCodeString);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(RecordCountString);
randomAccessFile.writeBytes("\n");
}
else
{
LastGeneratedCodeString = randomAccessFile.readLine().trim();
RecordCountString = randomAccessFile.readLine().trim();
LastGeneratedCode = Integer.parseInt(LastGeneratedCodeString);
RecordCount = Integer.parseInt(RecordCountString);
}
int fcode;
String fTitle;
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
fcode = Integer.parseInt(randomAccessFile.readLine());
fTitle = randomAccessFile.readLine();
if(fTitle.equalsIgnoreCase(title))
{
randomAccessFile.close();
throw new DAOException(" Designation  : " + title  + "Exists");
}
}
int code = LastGeneratedCode +1;
randomAccessFile.writeBytes( String.valueOf(code));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(title);
randomAccessFile.writeBytes("\n");
designationDTO.setCode(code);
LastGeneratedCode++;
RecordCount++;
LastGeneratedCodeString = String.valueOf(LastGeneratedCode);
while(LastGeneratedCodeString.length() < 10) LastGeneratedCodeString += " ";
RecordCountString = String.valueOf(RecordCount);
while(RecordCountString.length() < 10) RecordCountString += " ";
randomAccessFile.seek(0);
randomAccessFile.writeBytes( LastGeneratedCodeString);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes( RecordCountString);
randomAccessFile.writeBytes("\n");
randomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException( ioException.getMessage());
} 
}


// ****************************** Update Designation *****************************************


public void update(DesignationDTOInterface designationDTO) throws DAOException
{
if(designationDTO == null) throw new DAOException(" Designation is null ");
int code = designationDTO.getCode();
if(code <= 0) throw new DAOException(" Invalid Code :" + code);
String Title = designationDTO.getTitle();
if(Title == null) throw new DAOException(" Designation is null ");
Title = Title.trim();
if(Title.length() == 0) throw new DAOException( " length of designation is  zero  ");
try
{
File file = new File(FILE_NAME);
if(!file.exists()) throw new DAOException(" Invalid code :" + code);
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file , "rw");
if(randomAccessFile.length() == 0)
{
randomAccessFile.close();
throw new DAOException(" Invalid Code "+ code);
}
int fcode;
String fTitle;
randomAccessFile.readLine();
randomAccessFile.readLine();
boolean found = false;
while( randomAccessFile.getFilePointer() < randomAccessFile.length())
{
fcode = Integer.parseInt(randomAccessFile.readLine());
if(fcode == code)
{
found = true;
break;
}
randomAccessFile.readLine();
}
if(found == false)
{
randomAccessFile.close();
throw new DAOException(" Invalid Code  :" + code);
}
randomAccessFile.seek(0);
randomAccessFile.readLine();
randomAccessFile.readLine();
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
fcode = Integer.parseInt(randomAccessFile.readLine());
fTitle = randomAccessFile.readLine();
if(fcode != code && Title.equalsIgnoreCase(fTitle) ==  true)
{
randomAccessFile.close();
throw new DAOException(" title : " + Title + " Exists");
}
}
File tmpfile = new File("tmp.data");
if(tmpfile.exists()) tmpfile.delete();
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile = new RandomAccessFile(tmpfile , "rw");
randomAccessFile.seek(0);
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine());
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine());
tmpRandomAccessFile.writeBytes("\n");
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
fcode = Integer.parseInt( randomAccessFile.readLine());
fTitle = randomAccessFile.readLine();
if(code != fcode)
{
tmpRandomAccessFile.writeBytes(String.valueOf(fcode));
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(fTitle);
tmpRandomAccessFile.writeBytes("\n");
}
else
{
tmpRandomAccessFile.writeBytes(String.valueOf(code));
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(Title);
tmpRandomAccessFile.writeBytes("\n");
}
}
randomAccessFile.seek(0);
tmpRandomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer() < tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine());
randomAccessFile.writeBytes("\n");
}
randomAccessFile.setLength(tmpRandomAccessFile.length());
tmpRandomAccessFile.setLength(0);
randomAccessFile.close();
tmpRandomAccessFile.close();
} catch( IOException ioException)
{
throw new DAOException( ioException.getMessage());
}
}

// ****************************** Delete/ Remove Designation *****************************************


public void delete( int code ) throws DAOException
{
if(code <= 0) throw new DAOException(" Invalid Code :" + code);
try
{
String fTitle = "";
File file = new File(FILE_NAME);
if(!file.exists()) throw new DAOException(" Invalid Code :" + code);
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file , "rw");
if(randomAccessFile.length() == 0)
{
randomAccessFile.close();
throw new DAOException(" Invalid Code " + code);
}
int fcode;
randomAccessFile.readLine();
int recordcount = Integer.parseInt( randomAccessFile.readLine().trim());
boolean found = false;
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
fcode = Integer.parseInt(randomAccessFile.readLine());
fTitle = randomAccessFile.readLine();
if(fcode == code)
{
found = true;
break;
}
}
if(found == false)
{
randomAccessFile.close();
throw new DAOException(" Invalid Code :" + code);
}
if(new EmployeeDAO().isDesignationAlloted(code))
{
randomAccessFile.close();
throw new DAOException("Employee Exist with Title : " + fTitle);
}
File tmpfile = new File("tmp.data");
if(tmpfile.exists()) tmpfile.delete();
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile = new RandomAccessFile( tmpfile , "rw");
randomAccessFile.seek(0);
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine());
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine());
tmpRandomAccessFile.writeBytes("\n");
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
fcode = Integer.parseInt(randomAccessFile.readLine());
fTitle = randomAccessFile.readLine();
if(code != fcode)
{
tmpRandomAccessFile.writeBytes(String.valueOf(fcode));
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(fTitle);
tmpRandomAccessFile.writeBytes("\n");
}
}
randomAccessFile.seek(0);
tmpRandomAccessFile.seek(0);
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine());
randomAccessFile.writeBytes("\n");
tmpRandomAccessFile.readLine();
String recordcountString = String.valueOf(recordcount -1);
while(recordcountString.length() < 10) recordcountString += " ";
randomAccessFile.writeBytes(recordcountString);
randomAccessFile.writeBytes("\n");
while(tmpRandomAccessFile.getFilePointer() < tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine());
randomAccessFile.writeBytes("\n");
}
randomAccessFile.setLength(tmpRandomAccessFile.length());
tmpRandomAccessFile.setLength(0);
randomAccessFile.close();
tmpRandomAccessFile.close();
}catch( IOException ioexception)
{
throw new DAOException(ioexception.getMessage());
}
}

// ****************************** Get All Designation *****************************************


public Set<DesignationDTOInterface> getall() throws DAOException
{
Set<DesignationDTOInterface> designations;
designations = new TreeSet<>();
try
{
File file = new File(FILE_NAME);
if(!file.exists()) return designations;
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile( file , "rw");
if(randomAccessFile.length() == 0) 
{
randomAccessFile.close();
return designations;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
DesignationDTOInterface designationDTO;
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
designationDTO = new DesignationDTO();
designationDTO.setCode(Integer.parseInt(randomAccessFile.readLine()));
designationDTO.setTitle(randomAccessFile.readLine());
designations.add(designationDTO);
}
randomAccessFile.close();
return designations;
}catch( IOException ioexception)
{
throw new DAOException(ioexception.getMessage());
}
}

// ****************************** Designation Get By Code  *****************************************

public DesignationDTOInterface getByCode( int code ) throws DAOException
{
if(code <= 0 ) throw new DAOException(" Invalid Code :"  + code);
try
{
File file = new File(FILE_NAME);
if(file.exists() == false) throw new DAOException(" Invalid code :" + code);
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file , "rw");
if(randomAccessFile.length() == 0)
{
randomAccessFile.close();
throw new DAOException(" Invalid Code : " + code);
}
randomAccessFile.readLine();
int recordcount ;
recordcount = Integer.parseInt(randomAccessFile.readLine().trim());
if(recordcount == 0)
{
randomAccessFile.close();
throw new DAOException("Invalid code :" + code);
}
int fcode;
String fTitle;
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
fcode = Integer.parseInt(randomAccessFile.readLine());
fTitle = randomAccessFile.readLine();
if(fcode == code)
{
randomAccessFile.close();
DesignationDTOInterface designationDTO;
designationDTO = new DesignationDTO();
designationDTO.setCode(fcode);
designationDTO.setTitle(fTitle);
return designationDTO;
}
}
randomAccessFile.close();
throw new DAOException(" Invalid Code :" + code);
}catch( IOException ioExcpetion )
{
throw new DAOException(  ioExcpetion.getMessage());
}
}

// ****************************** Designation Get By Title *****************************************


public DesignationDTOInterface getByTitle( String Title) throws DAOException
{
if(Title == null || Title.trim().length() == 0 ) throw new DAOException(" Invalid Title :" + Title );
Title = Title.trim();
try
{
File file = new File(FILE_NAME);
if(file.exists() == false) throw new DAOException(" Invalid Title :" + Title);
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile( file , "rw");
if(randomAccessFile.length() == 0)
{
randomAccessFile.close();
throw new DAOException( " Invalid Title :" + Title);
}
randomAccessFile.readLine();
int recordcount = Integer.parseInt(randomAccessFile.readLine().trim());
if(recordcount == 0)
{
randomAccessFile.close();
throw new DAOException(" Invalid Title :" + Title);
}
int fcode;
String fTitle;
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
fcode = Integer.parseInt( randomAccessFile.readLine());
fTitle = randomAccessFile.readLine();
if(Title.equalsIgnoreCase(fTitle))
{
randomAccessFile.close();
DesignationDTOInterface designationDTO;
designationDTO = new DesignationDTO();
designationDTO.setCode(fcode);
designationDTO.setTitle(fTitle);
return designationDTO;
}
}
randomAccessFile.close();
throw new DAOException( " Invalid Title :" + Title);
} catch( IOException ioException )
{
throw new  DAOException(ioException.getMessage());
}
}

// ****************************** Code Exists *****************************************


public boolean CodeExists( int code ) throws DAOException
{
if(code <=0) return false;
try
{
File file = new File(FILE_NAME);
if(file.exists() == false) return false;
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file , "rw");
if(randomAccessFile.length() == 0) 
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
int recordcount = Integer.parseInt(randomAccessFile.readLine().trim());
if(recordcount == 0)
{
randomAccessFile.close();
return false;
}
int fcode;
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
fcode = Integer.parseInt(randomAccessFile.readLine());
if(fcode == code)
{
randomAccessFile.close();
return true;
}
randomAccessFile.readLine();
}
randomAccessFile.close();
return false;
} catch( IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

// ****************************** Title Exists *****************************************

public boolean TitleExists( String Title) throws DAOException
{
if(Title == null || Title.trim().length() ==  0) return false;
Title = Title.trim();
try
{
File file  = new File(FILE_NAME);
if(file.exists() == false) return false;
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile( file  , "rw");
if(randomAccessFile.length() == 0)
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
int recordcount = Integer.parseInt(randomAccessFile.readLine().trim());
if(recordcount == 0)
{
randomAccessFile.close();
return false;
}
String fTitle;
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
randomAccessFile.readLine();
fTitle = randomAccessFile.readLine();
if(Title.equalsIgnoreCase(fTitle))
{
randomAccessFile.close();
return true;
}
}
randomAccessFile.close();
return false;
}catch( IOException ioException )
{
throw new DAOException(ioException.getMessage());
}
}


// ****************************** Designation Count *****************************************


public int getCount() throws DAOException
{
try
{
File file = new File(FILE_NAME);
if(file.exists() == false) return 0;
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file , "rw");
if(randomAccessFile.length() == 0)
{
randomAccessFile.close();
return 0;
}
randomAccessFile.readLine();
int recordcount;
recordcount = Integer.parseInt(randomAccessFile.readLine().trim());
randomAccessFile.close();
return recordcount;
}catch( IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
}

