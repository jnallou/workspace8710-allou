package model.progelement;

import java.util.ArrayList;
import java.util.List;

public class ProgramElement {
   List<ProgramElement> listChildren = new ArrayList<ProgramElement>();
   ProgramElement parent = null;
   String name = null;
   int position;
   boolean isPrivate;
   

   public ProgramElement(String name) {
      this.name = name;
      parent = this;
   }

   public ProgramElement(String name, ProgramElement parent) {
      this.name = name;
      this.parent = parent;
   }

   public ProgramElement[] list() {
      ProgramElement[] l = new ProgramElement[listChildren.size()];
      return listChildren.toArray(l);
   }

   public void add(ProgramElement p) {
      listChildren.add(p);
   }

   public ProgramElement getParent() {
      return this.parent;
   }

   public boolean hasChildren() {
      return !this.listChildren.isEmpty();
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String toString() {
      return this.name;
   }
   public int getPosition()
   {
	   return position;
   }
   
   public void setPosition(int position)
   {
	   this.position = position;
   }
   public boolean isPrivate() {
	      return isPrivate;
}

public void setPrivate(boolean isPrivate) { 
	   this.isPrivate = isPrivate;   
}

}
