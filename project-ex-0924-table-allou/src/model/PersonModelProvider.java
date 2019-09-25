package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public enum PersonModelProvider {
   INSTANCE;

   private List<Person> persons;

   private PersonModelProvider() {
      persons = new ArrayList<Person>();
      String file = "C:/Users/jnallou/Desktop/Mod Software Dev Methodologies/workspaceCSCI8710/workspaceCSCI8710-Allou/project-ex-0924-table-allou/input_init.csv"; 
      	try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = "";
            while ((line = br.readLine()) != null) {
            	String[] personsDetails = line.split(",");
            	if (personsDetails.length > 0)
            	{
            		//Save details into persons object
            		persons.add(new Person(personsDetails[0],personsDetails[1], personsDetails[2], personsDetails[3]));
            	
            	}
              
            }
            br.close();
        } catch (Exception e) {
          //Some error logging
        }
   }

   public List<Person> getPersons() {
      return persons;
   }
}
