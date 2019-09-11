package simpleVisitorPattern.visitor;

import simpleVisitorPattern.part.Body;
import simpleVisitorPattern.part.Brake;
import simpleVisitorPattern.part.Engine;
import simpleVisitorPattern.part.Wheel;

public class MyReverseVisitor extends CartPartVisitor {
	public void visit(Wheel part) {
		  String[] tokens1 = part.getName().split(" ");
		  String[] tokens2 = part.getModelNumberWheel().split(" ");
		  String[] tokens3 = part.getModelYearWheel().split(" ");
		  

		  StringBuilder newName = new StringBuilder();
		  
		  for (int i = tokens1.length-1; i >= 0; i--)
		  {
			  newName.append(tokens1[i] + " ");  
		  }
		
		  newName = newName.reverse();
		  part.setName(newName.toString().trim());
		  
		  if (part.getModelNumberWheel() != null && part.getModelYearWheel() != null)
		  {
			  StringBuilder newModelNumberWheel = new StringBuilder();
			  StringBuilder newModelYearWheel = new StringBuilder();
			  
			  for (int i = tokens2.length-1; i >= 0; i--)
			  {
				  newModelNumberWheel.append(tokens2[i] + " ");  
			  }
			  for (int i = tokens3.length-1; i >= 0; i--)
			  {
				  newModelYearWheel.append(tokens3[i] + " ");  
			  }
			  
			  newModelNumberWheel=  newModelNumberWheel.reverse();
			  part.setModelNumberWheel( newModelNumberWheel.toString().trim());
			  
			  newModelYearWheel = newModelYearWheel.reverse();
			  part.setModelYearWheel(newModelYearWheel.toString().trim());
		     
		  }
		  

	      

	   }

	   public void visit(Engine part) {
		   String[] tokens1 = part.getName().split(" ");
			  String[] tokens2 = part.getModelNumberEngine().split(" ");
			  String[] tokens3 = part.getModelYearEngine().split(" ");
			  

			  StringBuilder newName = new StringBuilder();
			  
			  for (int i = tokens1.length-1; i >= 0; i--)
			  {
				  newName.append(tokens1[i] + " ");  
			  }
			
			  newName = newName.reverse();
			  part.setName(newName.toString().trim());
			  
			  if (part.getModelNumberEngine() != null && part.getModelYearEngine() != null)
			  {
				  StringBuilder newModelNumberEngine = new StringBuilder();
				  StringBuilder newModelYearEngine = new StringBuilder();
				  
				  for (int i = tokens2.length-1; i >= 0; i--)
				  {
					  newModelNumberEngine.append(tokens2[i] + " ");  
				  }
				  for (int i = tokens3.length-1; i >= 0; i--)
				  {
					  newModelYearEngine.append(tokens3[i] + " ");  
				  }
				  
				  newModelNumberEngine=  newModelNumberEngine.reverse();
				  part.setModelNumberEngine( newModelNumberEngine.toString().trim());
				  
				  newModelYearEngine = newModelYearEngine.reverse();
				  part.setModelYearEngine(newModelYearEngine.toString().trim());
			     
			  }
		     
	   }

	   public void visit(Body part) {
		   String[] tokens1 = part.getName().split(" ");
			  String[] tokens2 = part.getModelNumberBody().split(" ");
			  String[] tokens3 = part.getModelYearBody().split(" ");
			  

			  StringBuilder newName = new StringBuilder();
			  
			  for (int i = tokens1.length-1; i >= 0; i--)
			  {
				  newName.append(tokens1[i] + " ");  
			  }
			
			  newName = newName.reverse();
			  part.setName(newName.toString().trim());
			  
			  if (part.getModelNumberBody() != null && part.getModelYearBody() != null)
			  {
				  StringBuilder newModelNumberBody = new StringBuilder();
				  StringBuilder newModelYearBody = new StringBuilder();
				  
				  for (int i = tokens2.length-1; i >= 0; i--)
				  {
					  newModelNumberBody.append(tokens2[i] + " ");  
				  }
				  for (int i = tokens3.length-1; i >= 0; i--)
				  {
					  newModelYearBody.append(tokens3[i] + " ");  
				  }
				  
				  newModelNumberBody=  newModelNumberBody.reverse();
				  part.setModelNumberBody( newModelNumberBody.toString().trim());
				  
				  newModelYearBody = newModelYearBody.reverse();
				  part.setModelYearBody(newModelYearBody.toString().trim());
			     
			  }
		     
	   }
	   
	   public void visit(Brake part) {
		   String[] tokens1 = part.getName().split(" ");
			  String[] tokens2 = part.getModelNumberBrake().split(" ");
			  String[] tokens3 = part.getModelYearBrake().split(" ");
			  

			  StringBuilder newName = new StringBuilder();
			  
			  for (int i = tokens1.length-1; i >= 0; i--)
			  {
				  newName.append(tokens1[i] + " ");  
			  }
			
			  newName = newName.reverse();
			  part.setName(newName.toString().trim());
			  
			  if (part.getModelNumberBrake() != null && part.getModelYearBrake() != null)
			  {
				  StringBuilder newModelNumberBrake = new StringBuilder();
				  StringBuilder newModelYearBrake = new StringBuilder();
				  
				  for (int i = tokens2.length-1; i >= 0; i--)
				  {
					  newModelNumberBrake.append(tokens2[i] + " ");  
				  }
				  for (int i = tokens3.length-1; i >= 0; i--)
				  {
					  newModelYearBrake.append(tokens3[i] + " ");  
				  }
				  
				  newModelNumberBrake=  newModelNumberBrake.reverse();
				  part.setModelNumberBrake( newModelNumberBrake.toString().trim());
				  
				  newModelYearBrake = newModelYearBrake.reverse();
				  part.setModelYearBrake(newModelYearBrake.toString().trim());
			     
			  }
	   }


}