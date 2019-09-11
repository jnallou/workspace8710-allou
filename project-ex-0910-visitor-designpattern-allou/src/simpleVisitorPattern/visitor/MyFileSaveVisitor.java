package simpleVisitorPattern.visitor;


import java.io.FileWriter;
import java.io.IOException;

import simpleVisitorPattern.part.Body;
import simpleVisitorPattern.part.Brake;
import simpleVisitorPattern.part.Engine;
import simpleVisitorPattern.part.Wheel;


public class MyFileSaveVisitor extends CartPartVisitor {
	FileWriter write;
	

	public void visit(Wheel part) {
		
		try {
			write = new FileWriter("outputdata.csv");
			write.append(part.getName());
			write.append(",");
			write.append(part.getModelNumberWheel());
			write.append(",");
			write.append(part.getModelYearWheel());
			write.append("\n");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	public void visit(Engine part) {
	
		try {
		
			write.append(part.getName());
			write.append(",");
			write.append(part.getModelNumberEngine());
			write.append(",");
			write.append(part.getModelYearEngine());
			write.append("\n");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void visit(Body part) {
	
		try {
			
			write.append(part.getName());
			write.append(",");
			write.append(part.getModelNumberBody());
			write.append(",");
			write.append(part.getModelYearBody());
			write.append("\n");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void visit(Brake part) {
		
		try {
			
			write.append(part.getName());
			write.append(",");
			write.append(part.getModelNumberBrake());
			write.append(",");
			write.append(part.getModelYearBrake());
			write.append("\n");
			write.flush();
			write.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}