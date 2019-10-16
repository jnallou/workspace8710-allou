package model.provider;

import org.eclipse.jface.viewers.StyledString;

import model.progelement.MethodElement;
import model.provider.LabelProviderProgElem;


public class LabelProviderMethodParameter extends LabelProviderProgElem {

	   @Override
	   public StyledString getStyledText(Object element) {
	      if (element instanceof MethodElement) {
				return new StyledString(((MethodElement) element).getParameterStr());
	      }
	      return new StyledString(""); // super.getStyledText(element);
	   }
	}
