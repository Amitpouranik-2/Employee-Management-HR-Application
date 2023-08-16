package com.Employee.Management.hr.dl.dao;
import java.util.*;
import java.io.*;
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

// ****************************** Add Employee *****************************************

public void add(EmployeeDTOInterface employeeDTO)throws DAOException
{
if(employeeDTO==null)throw new DAOException("Invalid information");
String employeeId;
String name=employeeDTO.getName();
if(name==null)throw new DAOException("Invalid name"+name);
name=name.trim();
if(name.length()==0)throw new DAOException("Invalid name"+name);
int designationCode=employeeDTO.getDesignationCode();
if(designationCode<=0)throw new DAOException("Invalid designation Code"+designationCode);
DesignationDAOInterface designationDAO;
designationDAO=new DesignationDAO();
boolean isDesignationCodeValid=designationDAO.CodeExists(designationCode);
if(isDesignationCodeValid==false)throw new DAOException("designatiuonCode is Invalid"+designationCode);
Date DateOfBirth=employeeDTO.getDateOfBirth();
if(DateOfBirth==null)throw new DAOException("Invalid date of birth"+DateOfBirth);
char gender=employeeDTO.getGender();
if(gender == ' ') throw new DAOException(" Gender not set to MALE/FEMALE");
boolean isIndian=employeeDTO.getIsIndian();
BigDecimal basicSalary=employeeDTO.getBasicSalary();
if(basicSalary==null)throw new DAOException("Invalid salary"+basicSalary);
if(basicSalary.signum()==-1)throw new DAOException("basic Salary is not consider negative"+basicSalary);
String panNumber=employeeDTO.getPANNumber();
if(panNumber==null)throw new DAOException("Invalid pan-number"+panNumber);
panNumber=panNumber.trim();
if(panNumber.length()==0)throw new DAOException("length of panNumber is zero"+panNumber);
String aadharCardNumber=employeeDTO.getAadharCardNumber();
if(aadharCardNumber==null)throw new DAOException("Invalid aadharCardNumber"+aadharCardNumber);
if(aadharCardNumber.length()==0)throw new DAOException("length of aadharCardNumber is zero"+aadharCardNumber);
try
{
int lastGeneratedEmployeeId=10000000;
String lastGeneratedEmployeeIdString=" ";
int recordCount=0;
String recordCountString=" ";
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
lastGeneratedEmployeeIdString=String.format("%-10s","10000000");
randomAccessFile.writeBytes(lastGeneratedEmployeeIdString+"\n");
recordCountString=String.format("%-10s","0");
randomAccessFile.writeBytes(recordCountString+"\n");
}
else
{
lastGeneratedEmployeeId=Integer.parseInt(randomAccessFile.readLine().trim());
recordCount=Integer.parseInt(randomAccessFile.readLine().trim());
}
boolean panNumberExists,aadharCardNumberExists;
panNumberExists=false;
aadharCardNumberExists=false;
String fPANNumber;
String fAadharCardNumber;
int x;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
for(x=1;x<=7;x++)randomAccessFile.readLine();
fPANNumber=randomAccessFile.readLine();
fAadharCardNumber=randomAccessFile.readLine();
if(panNumberExists==false && fPANNumber.equalsIgnoreCase(panNumber))
{
panNumberExists=true;
}
if(aadharCardNumberExists==false && fAadharCardNumber.equalsIgnoreCase(aadharCardNumber))
{
aadharCardNumberExists=true;
}
if(panNumberExists && aadharCardNumberExists)break;
}
if(panNumberExists && aadharCardNumberExists)
{
randomAccessFile.close();
throw new DAOException("PANNumber("+panNumber+") and AadharCardNumber("+aadharCardNumber+")exists");
}
if(panNumberExists)
{
randomAccessFile.close();
throw new DAOException("PANNumber is exists"+panNumber);
}
if(aadharCardNumberExists)
{
randomAccessFile.close();
throw new DAOException("AadharCardNumber is exists"+aadharCardNumber);
}
lastGeneratedEmployeeId++;
employeeId= "A" + lastGeneratedEmployeeId;
recordCount++;
randomAccessFile.writeBytes(employeeId+"\n");
randomAccessFile.writeBytes(name+"\n");
randomAccessFile.writeBytes(designationCode+"\n");
SimpleDateFormat sdf;
sdf =  new SimpleDateFormat("dd/MM/yyyy");
randomAccessFile.writeBytes(sdf.format(DateOfBirth) + "\n");
randomAccessFile.writeBytes(gender+"\n");
randomAccessFile.writeBytes(isIndian+"\n");
randomAccessFile.writeBytes(basicSalary.toPlainString()+"\n");
randomAccessFile.writeBytes(panNumber+"\n");
randomAccessFile.writeBytes(aadharCardNumber+"\n");
randomAccessFile.seek(0);
lastGeneratedEmployeeIdString=String.format("%-10d",lastGeneratedEmployeeId);
randomAccessFile.writeBytes(lastGeneratedEmployeeIdString+"\n");
recordCountString=String.format("%-10d",recordCount);
randomAccessFile.writeBytes(recordCountString+"\n");
randomAccessFile.close();
employeeDTO.setEmployeeId(employeeId);
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

// ****************************** Update Employee *****************************************



public void update( EmployeeDTOInterface employeeDTO) throws DAOException
{
if(employeeDTO==null)throw new DAOException("In valid information");
String employeeId = employeeDTO.getEmployeeId();
if(employeeId == null) throw new DAOException(" Employee Id is NULL");
String name=employeeDTO.getName();
if(name==null)throw new DAOException("Invalid name"+name);
name=name.trim();
if(name.length()==0)throw new DAOException("Invalid name"+name);
int designationCode=employeeDTO.getDesignationCode();
if(designationCode<=0)throw new DAOException("Invalid designation Code"+designationCode);
DesignationDAOInterface designationDAO;
designationDAO=new DesignationDAO();
boolean isDesignationCodeValid=designationDAO.CodeExists(designationCode);
if(isDesignationCodeValid==false)throw new DAOException("designatiuonCode is Invalid"+designationCode);
Date DateOfBirth=employeeDTO.getDateOfBirth();
if(DateOfBirth==null)throw new DAOException("Invalid date of birth"+DateOfBirth);
char gender=employeeDTO.getGender();
if(gender == ' ') throw new DAOException(" Gender not set to MALE/FEMALE");
boolean isIndian=employeeDTO.getIsIndian();
BigDecimal basicSalary=employeeDTO.getBasicSalary();
if(basicSalary==null)throw new DAOException("Invalid salary"+basicSalary);
if(basicSalary.signum()==-1)throw new DAOException("basic Salary is not consider negative"+basicSalary);
String PANNumber=employeeDTO.getPANNumber();
if(PANNumber==null)throw new DAOException("Invalid pan-number"+PANNumber);
PANNumber=PANNumber.trim();
if(PANNumber.length()==0)throw new DAOException("length of panNumber is zero"+ PANNumber);
String AadharCardNumber=employeeDTO.getAadharCardNumber();
if(AadharCardNumber==null)throw new DAOException("Invalid Aadhar Card Number"+AadharCardNumber);
if(AadharCardNumber.length()==0)throw new DAOException("length of Aadhar Card Number is zero"+AadharCardNumber);

try
{
File file  = new File(FILE_NAME);
if(file.exists() == false) throw new DAOException(" Invalid Employee ID :  " + employeeId);
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file , "rw");
if(randomAccessFile.length() == 0)
{
randomAccessFile.close();
throw new DAOException(" Invalid Employee Id :" + employeeId);
}
randomAccessFile.readLine();
randomAccessFile.readLine();
SimpleDateFormat simpleDateFormat;
simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
String fEmployeeId;
String fName;
int fDesignationcode;
Date fDateOfBirth;
char fGender;
boolean fIsIndian;
BigDecimal fBasicSalary;
String fPANNumber;
String fAadharCardNumber;
int x;
boolean EmployeeIdFound = false;
boolean PANNumberFound = false;
boolean AadharCardNumberFound = false;
String 	PANNumberFoundAgainstEmployeeId = "";
String AadharCarNumberFoundAgainstEmployeeId = "";
long foundAt = 0;
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
if(EmployeeIdFound == false) 
foundAt = randomAccessFile.getFilePointer();
fEmployeeId = randomAccessFile.readLine();
for(x=1; x<=6; x++) randomAccessFile.readLine();
fPANNumber = randomAccessFile.readLine();
fAadharCardNumber = randomAccessFile.readLine();
if( EmployeeIdFound== false && fEmployeeId.equalsIgnoreCase(employeeId))
{
EmployeeIdFound =true;
}
if(PANNumberFound == false && fPANNumber.equalsIgnoreCase(PANNumber))
{
PANNumberFound = true;
PANNumberFoundAgainstEmployeeId = fEmployeeId;
}
if(AadharCardNumberFound == false && fAadharCardNumber.equalsIgnoreCase(AadharCardNumber))
{
AadharCardNumberFound = true;
AadharCarNumberFoundAgainstEmployeeId = fEmployeeId;
}
if(EmployeeIdFound && PANNumberFound && AadharCardNumberFound) break;
}
if(EmployeeIdFound == false)
{
randomAccessFile.close();
throw new DAOException(" Invaild Employee Id: " + employeeId);
}
boolean PANNumberExists = false;
if(PANNumberFound &&  PANNumberFoundAgainstEmployeeId.equalsIgnoreCase(employeeId) == false )
{
PANNumberExists = true;
}
boolean AadharCardNumberExists = false;
if( AadharCardNumberFound && AadharCarNumberFoundAgainstEmployeeId.equalsIgnoreCase(employeeId)== false)
{
AadharCardNumberExists = true;
}
if(PANNumberExists && AadharCardNumberExists)
{
randomAccessFile.close();
throw new DAOException("PAN Number . ("+PANNumber+") And Aadhar Card Number.  (" + AadharCardNumber +") Exists ");
}
if(PANNumberExists)
{
randomAccessFile.close();
throw new DAOException("PANNumber is exists"+ PANNumber);
}
if(AadharCardNumberExists)
{
randomAccessFile.close();
throw new DAOException("AadharCardNumber is exists "+ AadharCardNumber);
}
randomAccessFile.seek(foundAt);
for( x=1; x<=9; x++) randomAccessFile.readLine();
File tmpFile = new File("tmp.tmp");
if(tmpFile.exists()) tmpFile.delete();
RandomAccessFile   tmpRandomAccessFile;
tmpRandomAccessFile = new RandomAccessFile(tmpFile , "rw");
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine()+ "\n");
}
randomAccessFile.seek(foundAt);
randomAccessFile.writeBytes(employeeId+"\n");
randomAccessFile.writeBytes(name+"\n");
randomAccessFile.writeBytes(designationCode+"\n");
SimpleDateFormat sdf;
sdf =  new SimpleDateFormat("dd/MM/yyyy");
randomAccessFile.writeBytes(sdf.format(DateOfBirth) + "\n");
randomAccessFile.writeBytes(gender+"\n");
randomAccessFile.writeBytes(isIndian+"\n");
randomAccessFile.writeBytes(basicSalary.toPlainString()+"\n");
randomAccessFile.writeBytes(PANNumber+"\n");
randomAccessFile.writeBytes(AadharCardNumber+"\n");
tmpRandomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer() < tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine() + "\n");
}
randomAccessFile.setLength(randomAccessFile.getFilePointer());
tmpRandomAccessFile.setLength(0);
randomAccessFile.close();
tmpRandomAccessFile.close();
}catch( IOException ioexception)
{
throw new DAOException(ioexception.getMessage());
}
}

// ****************************** Delete Employee *****************************************

public void delete(String EmployeeId) throws DAOException
{
if(EmployeeId == null) throw new DAOException(" EmployeeId Is NULL");
if(EmployeeId.length() == 0) throw new DAOException("Employee Id Length is Zero");
try
{
File file = new File(FILE_NAME);
if(file.exists() == false) throw new DAOException(" Invalid Employee Id :" + EmployeeId);
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile( file , "rw");
if(randomAccessFile.length() == 0)
{
randomAccessFile.close();
throw new DAOException(" Invalid Employee Id :" + EmployeeId);
}
randomAccessFile.readLine();
int recordCount = Integer.parseInt(randomAccessFile.readLine().trim());
String fEmployeeId;
int x;
boolean employeeIdFound = false;
long foundAt = 0;
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
foundAt = randomAccessFile.getFilePointer();
fEmployeeId = randomAccessFile.readLine();
for(x=1; x<=8; x++) randomAccessFile.readLine();
if(fEmployeeId.equalsIgnoreCase(EmployeeId))
{
employeeIdFound =true;
break;
}
}
if(employeeIdFound == false)
{
randomAccessFile.close();
throw new DAOException(" Invalid Employee Id. :" + EmployeeId);
}
File tmpFile = new File("tmp.tmp");
if(tmpFile.exists() == false) tmpFile.delete();
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile = new RandomAccessFile( tmpFile  ,"rw");
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine() + "\n");
}
randomAccessFile.seek(foundAt);
tmpRandomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer() < tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+ "\n");
}
randomAccessFile.setLength(randomAccessFile.getFilePointer());
recordCount--;
String recordCountString = String.format("%-10d" , recordCount);
randomAccessFile.seek(0);
randomAccessFile.readLine();
randomAccessFile.writeBytes( recordCountString + "\n");
randomAccessFile.close();
tmpRandomAccessFile.setLength(0);
tmpRandomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

// ****************************** Get All Employee *****************************************


public Set<EmployeeDTOInterface>getall() throws DAOException
{
Set<EmployeeDTOInterface>employees = new TreeSet<>();
try
{
File file = new File(FILE_NAME);
if(file.exists() == false) return employees;
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file , "rw");
if(randomAccessFile.length() == 0)
{
randomAccessFile.close();
return employees;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
EmployeeDTOInterface employeeDTO;
SimpleDateFormat simpleDateFormat;
simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
char fGender;
while( randomAccessFile.getFilePointer() < randomAccessFile.length())
{
employeeDTO = new EmployeeDTO();
employeeDTO.setEmployeeId(randomAccessFile.readLine());
employeeDTO.setName(randomAccessFile.readLine());
employeeDTO.setDesignationCode( Integer.parseInt(randomAccessFile.readLine()));
try
{
employeeDTO.setDateOfBirth(simpleDateFormat.parse(randomAccessFile.readLine()));
}catch(ParseException pe)
{
// do nothing
}
fGender = randomAccessFile.readLine().charAt(0);
if(fGender ==  'M')
{
employeeDTO.setGender(GENDER.MALE);
}
if(fGender == 'F')
{
employeeDTO.setGender(GENDER.FEMALE);
}
employeeDTO.setIsIndian(Boolean.parseBoolean(randomAccessFile.readLine()));
employeeDTO.setBasicSalary( new BigDecimal (randomAccessFile.readLine()));
employeeDTO.setPANNumber(randomAccessFile.readLine());
employeeDTO.setAadharCardNumber(randomAccessFile.readLine());
employees.add(employeeDTO);
}
randomAccessFile.close();
return employees;
}catch(IOException ioexception)
{
throw new DAOException(ioexception.getMessage());
}
}

 // ****************************** Get By Designation Code  *****************************************


public Set<EmployeeDTOInterface>getByDesignationcode( int Designationcode) throws  DAOException
{
Set<EmployeeDTOInterface>employees = new TreeSet<>();
if( new DesignationDAO().CodeExists(Designationcode) == false)
{
throw new DAOException(" Invalid Designation Code :" + Designationcode);
}
try
{
File file = new File(FILE_NAME);
if(file.exists() == false)
{
return employees;
}
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file , "rw");
if(randomAccessFile.length() == 0)
{
randomAccessFile.close();
return employees;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
EmployeeDTOInterface employeeDTO;
SimpleDateFormat simpleDateFormat;
simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
int x;
String femployeeId;
String fname;
int fdesignationCode;
char fGender;
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
femployeeId = randomAccessFile.readLine();
fname = randomAccessFile.readLine();
fdesignationCode = Integer.parseInt(randomAccessFile.readLine());
if(fdesignationCode!= Designationcode)
{
for(x=1; x<=6; x++) randomAccessFile.readLine();
continue;
}
employeeDTO = new EmployeeDTO();
employeeDTO.setEmployeeId(femployeeId );
employeeDTO.setName(fname);
employeeDTO.setDesignationCode(fdesignationCode);
try
{
employeeDTO.setDateOfBirth(simpleDateFormat.parse(randomAccessFile.readLine()));
}catch( ParseException pe)
{
// do nothing
}
fGender = randomAccessFile.readLine().charAt(0);
if(fGender == 'M')
{
employeeDTO.setGender(GENDER.MALE);
}
if(fGender == 'F')
{
employeeDTO.setGender(GENDER.FEMALE);
}
employeeDTO.setIsIndian(Boolean.parseBoolean(randomAccessFile.readLine()));
employeeDTO.setBasicSalary( new BigDecimal (randomAccessFile.readLine()));
employeeDTO.setPANNumber(randomAccessFile.readLine());
employeeDTO.setAadharCardNumber(randomAccessFile.readLine());
employees.add(employeeDTO);
}
randomAccessFile.close();
return employees;
}catch( IOException ioexception )
{
throw new DAOException(ioexception.getMessage());
}
}

// ****************************** Is Designation Alloted *****************************************


public boolean isDesignationAlloted( int Designationcode) throws DAOException
{
if( new DesignationDAO().CodeExists(Designationcode) == false)
{
throw new DAOException(" Invalid Designation Code :" + Designationcode);
}
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
randomAccessFile.readLine();
int x;
int fdesignationCode;
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
randomAccessFile.readLine();
randomAccessFile.readLine();
fdesignationCode = Integer.parseInt(randomAccessFile.readLine());
if(fdesignationCode == Designationcode)
{
randomAccessFile.close();
return true;
}
for(x=1; x<=6; x++) randomAccessFile.readLine();
}
randomAccessFile.close();
return false;
}catch( IOException ioexception )
{
throw new DAOException(ioexception.getMessage());
}
}

// ****************************** Get By Employee Id *****************************************


public EmployeeDTOInterface getByEmployeeId( String EmployeeId) throws DAOException
{
if(EmployeeId == null) throw new DAOException(" Invalid Employee Id :" + EmployeeId);
EmployeeId = EmployeeId.trim();
if(EmployeeId.length() == 0) throw new DAOException(" Invalid Length of EmployeeId :" + EmployeeId);
try
{
File file = new File(FILE_NAME);
if(file.exists() == false) throw new DAOException(" Invalid id :" + EmployeeId);
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file , "rw");
if(randomAccessFile.length() == 0)
{
randomAccessFile.close();
throw new DAOException(" Invalid employeeId : " + EmployeeId);
}
randomAccessFile.readLine();
randomAccessFile.readLine();
String femployeeId;
int x;
SimpleDateFormat simpleDateFormat;
simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
EmployeeDTOInterface employeeDTO;
char fGender;
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
femployeeId = randomAccessFile.readLine();
if(femployeeId.equalsIgnoreCase(EmployeeId))
{
employeeDTO = new EmployeeDTO();
employeeDTO.setEmployeeId(femployeeId );
employeeDTO.setName(randomAccessFile.readLine());
employeeDTO.setDesignationCode(Integer.parseInt(randomAccessFile.readLine()));
try
{
employeeDTO.setDateOfBirth(simpleDateFormat.parse(randomAccessFile.readLine()));
}catch( ParseException pe)
{
// do nothing
}
employeeDTO.setGender((randomAccessFile.readLine().charAt(0) == 'M')? GENDER.MALE : GENDER.FEMALE);
employeeDTO.setIsIndian(Boolean.parseBoolean(randomAccessFile.readLine()));
employeeDTO.setBasicSalary( new BigDecimal (randomAccessFile.readLine()));
employeeDTO.setPANNumber(randomAccessFile.readLine());
employeeDTO.setAadharCardNumber(randomAccessFile.readLine());
randomAccessFile.close();
return employeeDTO;
}
for(x=1; x<=8; x++) randomAccessFile.readLine();
}
randomAccessFile.close();
throw new DAOException(" Invalid Employee ID :" + EmployeeId );
}catch( IOException ioexception )
{
throw new DAOException(ioexception.getMessage());
}
}

// ****************************** Get By Aadhar Card Number   *****************************************



public EmployeeDTOInterface getByAadharCard( String AadharCardNumber ) throws DAOException
{
if( AadharCardNumber == null) throw new DAOException(" Invalid Aadhar Card Number :" + AadharCardNumber);
AadharCardNumber = AadharCardNumber.trim();
if(AadharCardNumber.length() == 0)
{
throw new DAOException(" Invalid Aadhar Card Number :" + AadharCardNumber);
}
try
{
File file = new File(FILE_NAME);
if(file.exists() == false) throw new DAOException(" Invalid Aadhar Card Number :" + AadharCardNumber);
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file , "rw");
if(randomAccessFile.length() == 0)
{
randomAccessFile.close();
throw new DAOException(" Invalid Aadhar Card Number : " + AadharCardNumber);
}
randomAccessFile.readLine();
randomAccessFile.readLine();
EmployeeDTOInterface employeeDTO;
String fEmployeeId;
String fName;
int fDesignationcode;
Date fDateOfBirth;
char fGender;
boolean fIsIndian;
BigDecimal fBasicSalary;
String fPANNumber;
String fAadharCardNumber;
SimpleDateFormat simpleDateFormat;
simpleDateFormat  = new SimpleDateFormat("dd/MM/yyyy");
int x;
fDateOfBirth = null;
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
fEmployeeId = randomAccessFile.readLine();
fName = randomAccessFile.readLine();
fDesignationcode = Integer.parseInt(randomAccessFile.readLine());
try
{
fDateOfBirth = simpleDateFormat.parse(randomAccessFile.readLine());
}catch(ParseException pe)
{
// do nothing
}
fGender   = randomAccessFile.readLine().charAt(0);
fIsIndian = Boolean.parseBoolean(randomAccessFile.readLine());
fBasicSalary = new BigDecimal(randomAccessFile.readLine());
fPANNumber = randomAccessFile.readLine();
fAadharCardNumber = randomAccessFile.readLine();
if(fAadharCardNumber.equalsIgnoreCase(AadharCardNumber))
{
employeeDTO = new EmployeeDTO();
employeeDTO.setEmployeeId(fEmployeeId);
employeeDTO.setName(fName);
employeeDTO.setDesignationCode(fDesignationcode);
employeeDTO.setDateOfBirth(fDateOfBirth);
employeeDTO.setGender((fGender== 'M')? GENDER.MALE : GENDER.FEMALE);
employeeDTO.setIsIndian(fIsIndian);
employeeDTO.setBasicSalary(fBasicSalary);
employeeDTO.setPANNumber(fPANNumber);
employeeDTO.setAadharCardNumber(fAadharCardNumber);
randomAccessFile.close();
return employeeDTO;
}
}
randomAccessFile.close();
throw new DAOException(" Invalid Aadhar Card Number :" + AadharCardNumber);
}catch(IOException ioexception)
{
throw new DAOException(ioexception.getMessage());
}
}

// ****************************** Get By PAN Card Number *****************************************


public EmployeeDTOInterface getByPANNumber( String PANNumber) throws DAOException
{
if(PANNumber == null) throw new DAOException(" Invalid PAN Number :" + PANNumber);
PANNumber = PANNumber.trim();
if(PANNumber.length() == 0)
{
throw new DAOException(" Invalid PAN Number :" + PANNumber);
}
try
{
File file = new File(FILE_NAME);
if(file.exists() == false) throw new DAOException(" Invalid PAN Number :" + PANNumber);
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(file , "rw");
if(randomAccessFile.length() == 0)
{
randomAccessFile.close();
throw new DAOException(" Invalid PAN Number : " + PANNumber);
}
randomAccessFile.readLine();
randomAccessFile.readLine();
EmployeeDTOInterface employeeDTO;
String fEmployeeId;
String fName;
int fDesignationcode;
Date fDateOfBirth;
char fGender;
boolean fIsIndian;
BigDecimal fBasicSalary;
String fPANNumber;
String fAadharCardNumber;
SimpleDateFormat simpleDateFormat;
simpleDateFormat  = new SimpleDateFormat("dd/MM/yyyy");
int x;
fDateOfBirth = null;
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
fEmployeeId = randomAccessFile.readLine();
fName = randomAccessFile.readLine();
fDesignationcode = Integer.parseInt(randomAccessFile.readLine());
try
{
fDateOfBirth = simpleDateFormat.parse(randomAccessFile.readLine());
}catch(ParseException pe)
{
// do nothin
}
fGender   = randomAccessFile.readLine().charAt(0);
fIsIndian = Boolean.parseBoolean(randomAccessFile.readLine());
fBasicSalary = new BigDecimal(randomAccessFile.readLine());
fPANNumber = randomAccessFile.readLine();
fAadharCardNumber = randomAccessFile.readLine();
if(fPANNumber.equalsIgnoreCase(PANNumber))
{
employeeDTO = new EmployeeDTO();
employeeDTO.setEmployeeId(fEmployeeId);
employeeDTO.setName(fName);
employeeDTO.setDesignationCode(fDesignationcode);
employeeDTO.setDateOfBirth(fDateOfBirth);
employeeDTO.setGender((fGender== 'M')? GENDER.MALE : GENDER.FEMALE);
employeeDTO.setIsIndian(fIsIndian);
employeeDTO.setBasicSalary(fBasicSalary);
employeeDTO.setPANNumber(fPANNumber);
employeeDTO.setAadharCardNumber(fAadharCardNumber);
randomAccessFile.close();
return employeeDTO;
}
}
randomAccessFile.close();
throw new DAOException(" Invalid PANNumber :" + PANNumber);
}catch(IOException ioexception)
{
throw new DAOException(ioexception.getMessage());
}
}

// ****************************** Employee Id Exists *****************************************


public boolean EmployeeIDExists( String EmployeeId) throws DAOException
{
if( EmployeeId == null) return false;
EmployeeId =  EmployeeId.trim();
if( EmployeeId.length() == 0 ) return false;
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
randomAccessFile.readLine();
String fEmployeeId;
int x;
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
fEmployeeId = randomAccessFile.readLine();
if(fEmployeeId.equalsIgnoreCase(EmployeeId))
{
randomAccessFile.close();
return true;
}
}
for(x=1; x<=8; x++) randomAccessFile.readLine();
randomAccessFile.close();
return false;
}catch( IOException ioexception)
{
throw new DAOException(ioexception.getMessage());
} 
}

// ******************************  PAN Number Exists*****************************************


public boolean PANNumberExists( String PANNumber) throws DAOException
{
if(PANNumber == null)  return false;
PANNumber = PANNumber.trim();
if(PANNumber.length() == 0) return false;
try
{
File file = new File(FILE_NAME);
if(file.exists() == false) return false;
RandomAccessFile randomAccessFile;
randomAccessFile  = new RandomAccessFile(file , "rw");
if(randomAccessFile.length() == 0 )
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
EmployeeDTOInterface employeeDTO;
String fPANNumber;
int x;
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
for(x=1; x<=7; x++) randomAccessFile.readLine();
fPANNumber = randomAccessFile.readLine();
randomAccessFile.readLine();
if(fPANNumber.equalsIgnoreCase(PANNumber))
{
randomAccessFile.close();
return true;
}
}
randomAccessFile.close();
return false;
}catch( IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

// ****************************** Aadhar Card Number Exists *****************************************


public boolean AadharCardNumberExists( String AadharCardNumber) throws DAOException
{
if(AadharCardNumber == null)  return false;
AadharCardNumber= AadharCardNumber.trim();
if(AadharCardNumber.length() == 0) return false;
try
{
File file = new File(FILE_NAME);
if(file.exists() == false) return false;
RandomAccessFile randomAccessFile;
randomAccessFile  = new RandomAccessFile(file , "rw");
if(randomAccessFile.length() == 0 )
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
EmployeeDTOInterface employeeDTO;
String fAadharCardNumber;
int x;
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
for(x=1; x<=8; x++) randomAccessFile.readLine();
fAadharCardNumber = randomAccessFile.readLine();
if(fAadharCardNumber.equalsIgnoreCase(AadharCardNumber))
{
randomAccessFile.close();
return true;
}
}
randomAccessFile.close();
return false;
}catch( IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

// ****************************** Get Count of Employee *****************************************


public int getcount() throws DAOException
{
try
{
File file = new File(FILE_NAME);
if(file.exists() == false) return 0;
RandomAccessFile randomAccessFile;
randomAccessFile = new RandomAccessFile(  file , "rw");
if(randomAccessFile.length() == 0)
{
randomAccessFile.close();
return 0;
}
randomAccessFile.readLine();
int recordCount = Integer.parseInt(randomAccessFile.readLine().trim());
randomAccessFile.close();
return recordCount;
}catch( IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

// ****************************** Get Count By Designation *****************************************


public int getcountByDesignation( int Designationcode) throws DAOException
{
if( new DesignationDAO().CodeExists(Designationcode) == false)
{
throw new DAOException( " Invalid Designation code :" + Designationcode);
}
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
randomAccessFile.readLine();
int count =0;
int fDesignationcode;
int x;
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
randomAccessFile.readLine();
randomAccessFile.readLine();
fDesignationcode = Integer.parseInt(randomAccessFile.readLine());
if(fDesignationcode == Designationcode) count++;
for(x=1; x<=6; x++) randomAccessFile.readLine();
}
randomAccessFile.close();
return count;
}catch( IOException ioException)
{
throw new DAOException( ioException.getMessage());
}
}
}

