
import com.Employee.Management.hr.bl.exceptions.*;
import com.Employee.Management.hr.bl.interfaces.managers.*;
import com.Employee.Management.hr.bl.managers.*;
import com.Employee.Management.hr.bl.interfaces.pojo.*;
import com.Employee.Management.hr.bl.pojo.*;
import java.util.*;

class DesignationManagerGetDesignationByTitleTestcase
{
public static void main (String gg[] )
{

String title = gg[0];
try
{

DesignationManagerInterface designationManager;
designationManager = DesignationManager.getDesignationManager();

DesignationInterface designations;

designations = designationManager.getDesignationByTitle(title);
System.out.printf(" code :  %d , Title : %s " , designations.getCode() , designations.getTitle() );

}catch(BLException blException)
{
if(blException.hasGenericExceptions())
{
System.out.println(blException.getGenericException());
}
List<String> properties = blException.getProperties();
for(String property : properties)
{
System.out.println(blException.getException(property));
}

}







}

}