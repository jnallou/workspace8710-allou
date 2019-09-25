
package handler;

import java.io.BufferedReader;
import java.io.FileReader;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;

import model.Person;
import model.PersonModelProvider;
import view.MyTableViewer;


public class AddPersonHandler {


   @Inject
   @Named(IServiceConstants.ACTIVE_SHELL)
   Shell shell;

   @Execute
   public void execute(EPartService epartService) {
      //MsgUtil.openWarning("Hint", "Class Exercise!!");
	     MPart findPart = epartService.findPart(MyTableViewer.ID);
	      Object findPartObj = findPart.getObject();
	      MyTableViewer v = (MyTableViewer) findPartObj;
	   
	      String file = "C:/Users/jnallou/Desktop/Mod Software Dev Methodologies/workspaceCSCI8710/workspaceCSCI8710-Allou/project-ex-0924-table-allou/input_add.csv"; 
	      	try(BufferedReader br = new BufferedReader(new FileReader(file))) {
	            String line = "";
	            while ((line = br.readLine()) != null) {
	            	String[] personsDetails = line.split(",");
	            	if (personsDetails.length > 0)
	            	{
	            		 PersonModelProvider.INSTANCE.getPersons().add(new Person(personsDetails[0],personsDetails[1], personsDetails[2], personsDetails[3]));
	            		 
	            	}
	            	v.refresh();
	            }
	            br.close();
	        } catch (Exception e) {
	          System.out.println ("Error");
	        }
	      	
	     
   }
}

/*
PersonModelProvider personInstance = PersonModelProvider.INSTANCE;
AddPersonDialog dialog = new AddPersonDialog(shell);
dialog.open();
if (dialog.getPerson() != null) {
   personInstance.getPersons().add(dialog.getPerson());
   MPart findPart = epartService.findPart(MyTableViewer.ID);
   Object findPartObj = findPart.getObject();

   if (findPartObj instanceof MyTableViewer) {
      MyTableViewer v = (MyTableViewer) findPartObj;
      v.refresh();
   }
}
*/
