package model.progelement;

import java.util.List;

public class MethodElement extends ProgramElement {
   private String pkgName;
   private String className;
   private List<?> parameters;
   private int position;
   private boolean isPrivate;

   public MethodElement(String name, ProgramElement parent) {
      super(name, parent);
   }

   public void setParameters(List<?> parameters) {
      this.parameters = parameters;
   }

   public List<?> getParameters() {
      return parameters;
   }

   public boolean isPrivate() {
	      return isPrivate;
}

public void setPrivate(boolean isPrivate) { 
	   this.isPrivate = isPrivate;   
}

   public String getParameterStr() {
      if (parameters.isEmpty()) {
         return "Void";
      }
      StringBuilder buf = new StringBuilder();
      for (Object t : parameters) {
         buf.append(t.toString() + ", ");
      }
      return buf.toString().trim().substring(0, buf.toString().trim().length() - 1);
   }

   public String getMethodName() {
      return name;
   }

   public Integer getParameterSize() {
      return parameters.size();
   }

   public String getPkgName() {
      return pkgName;
   }

   public void setPkgName(String pkgName) {
      this.pkgName = pkgName;
   }

   public String getClassName() {
      return className;
   }

   public void setClassName(String className) {
      this.className = className;
   }

   public void setMethodName(String name) {
      this.name = name;
   }
   public int getPosition()
   {
	   return position;
   }
   
   public void setPosition(int position)
   {
	   this.position = position;
   }
}
