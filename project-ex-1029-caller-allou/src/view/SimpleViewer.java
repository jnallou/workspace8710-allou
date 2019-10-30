/**
 * @file SimpleViewer.java
 */
package view;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import handler.HandlerSearchMethodCaller;
import util.SWTResourceManager;

/**
 * @since JavaSE-1.8
 */
public class SimpleViewer {

   @Inject
   private EPartService epartService;
   public final static String VIEWID = "simplebindingproject.partdescriptor.simplecodesearchview";
   private StyledText styledText;
   private Text searchText;

   public SimpleViewer() {
   }

   @PostConstruct
   public void createControls(Composite parent) {
      GridLayout layout = new GridLayout(2, false);
      parent.setLayout(layout);
      createSearchTextV1(parent);

      // Composite composite = new Composite(parent, SWT.NONE);
      // composite.setLayout(new FillLayout(SWT.HORIZONTAL));
      styledText = new StyledText(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
      styledText.setFont(SWTResourceManager.getFont("Fixedsys", 12, SWT.NORMAL));

      GridData gridData = new GridData();
      gridData.verticalAlignment = GridData.FILL;
      gridData.horizontalSpan = 2;
      gridData.grabExcessHorizontalSpace = true;
      gridData.grabExcessVerticalSpace = true;
      gridData.horizontalAlignment = GridData.FILL;
      styledText.setLayoutData(gridData);
   }

   public StyledText getStyledText() {
      return styledText;
   }

   public void appendText(String s) {
      this.styledText.append(s);
   }

   public void reset() {
      this.styledText.setText("");
   }

   @PreDestroy
   public void dispose() {
   }

   @Focus
   public void setFocus() {
      this.searchText.setFocus();
   }

   private void createSearchTextV1(Composite parent) {
      Label searchLabel = new Label(parent, SWT.NONE);
      searchLabel.setText("Method Caller Reference Search: ");

      Composite container = new Composite(parent, SWT.NONE);
      GridLayout layout = new GridLayout(1, true);
      container.setLayout(layout);
      GridData gridData = new GridData();
      gridData.horizontalAlignment = GridData.FILL;
      container.setLayoutData(gridData);

      searchText = new Text(container, SWT.BORDER | SWT.SEARCH);
      searchText.setText("foo");
      searchText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));

      searchText.addListener(SWT.KeyDown, new Listener() {
         public void handleEvent(Event e) {
            if (e.keyCode == SWT.CR) {
               reset();
               HandlerSearchMethodCaller caller = new HandlerSearchMethodCaller();
               caller.execute(epartService);
            }
         }
      });
   }

   public void display(List<Map<IMethod, IMethod[]>> calleeCallers) {
      for (Map<IMethod, IMethod[]> iCalleeCaller : calleeCallers) {
         for (Entry<IMethod, IMethod[]> entry : iCalleeCaller.entrySet()) {
            IMethod callee = entry.getKey();
            if(!callee.getElementName().equals(searchText.getText()))continue;
            IMethod[] callers = entry.getValue();
            display(callee, callers);
         }
      }
   }

   private void display(IMethod callee, IMethod[] callers) {
      for (IMethod iCaller : callers) {
    	  String type = callee.getDeclaringType().getFullyQualifiedName();
    	  String calleeName = type + "." + callee.getElementName();
    	  this.styledText.append("'" + calleeName + // 
    	  "' CALLED FROM '" + iCaller.getElementName() + "'\n");
    	  
        
      }
   }

}
