import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.pojo.*;
import java.util.*;

class DesignationManagerGetDesignationTestcase
{
public static void main (String gg[] )
{
try
{
DesignationManagerInterface designationManager;
designationManager = DesignationManager.getDesignationManager();

Set<DesignationInterface> designations;

designations = designationManager.getDesignation();


designations.forEach((designation)->{

System.out.printf(" Code %d , Title %s\n" , designation.getCode() , designation.getTitle());
});

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