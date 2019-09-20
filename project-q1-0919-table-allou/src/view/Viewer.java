package view;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import java.io.FileWriter;
import java.net.URL;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import analysis.ProjectAnalyzer;
import model.ModelProvider;
import model.ProgramElement;

public class Viewer {
   private TableViewer viewer;
   private static final Image CHECKED = getImage("checked.gif");
   private static final Image UNCHECKED = getImage("unchecked.gif");

   /**
    * Create contents of the view part.
    */
   @PostConstruct
   public void createControls(Composite parent) {
      GridLayout layout = new GridLayout(2, false);
      parent.setLayout(layout);
      Label searchLabel = new Label(parent, SWT.NONE);
      searchLabel.setText("Search: ");
      final Text searchText = new Text(parent, SWT.BORDER | SWT.SEARCH);
      searchText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
      createViewer(parent);
      createContextMenu(parent);
   }

   private void createContextMenu(Composite parent) {
      Menu contextMenu = new Menu(viewer.getTable());
      viewer.getTable().setMenu(contextMenu);
      createMenuItemAnalyze(contextMenu);
      
   }

   private void createMenuItemAnalyze(Menu contextMenu) {
      final MenuItem menuItem = new MenuItem(contextMenu, SWT.PUSH);
      menuItem.setText("Analyze Program Allou");

      menuItem.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(SelectionEvent e) {
            ModelProvider.INSTANCE.clearProgramElements();
            try {
               ProjectAnalyzer analyzer = new ProjectAnalyzer();
               analyzer.analyze();
            } catch (Exception e2) {
               e2.printStackTrace();
            }
            viewer.setInput(ModelProvider.INSTANCE.getProgramElements());
         }
      });
   }
   
  /* @Execute
   public void execute(Shell shell) {
	    
	   int count = 0;
	   MessageDialog.openInformation(shell,
				"Export", "Hello World!");
				
	       
	        	
	        	 try {
	        		FileWriter write; 
	        	     
	                
	            	write= new FileWriter("C:/Users/jnallou/Desktop/Mod Software Dev Methodologies/workspaceCSCI8710/workspaceCSCI8710-Allou/project-q1-0919-table-allou/output.csv");
	                final Table table = getViewer().getTable();
	                final int[] columnOrder = table.getColumnOrder();
	                for(int columnOrderIndex = 0; columnOrderIndex < columnOrder.length; 
	                        columnOrderIndex++) {
	                    int columnIndex = columnOrder[columnOrderIndex];
	                    TableColumn tableColumn = table.getColumn(columnIndex);
	                    
	                  
	                    write.append(tableColumn.getText());
	                    write.append(",");

	               
	                }
	                write.append("\n");
	              
	                
	                final int itemCount = table.getItemCount();
	                for(int itemIndex = 0; itemIndex < itemCount; itemIndex++) {
	                    TableItem item = table.getItem(itemIndex);
	        
	                    for(int columnOrderIndex = 0; 
	                            columnOrderIndex < columnOrder.length; 
	                            columnOrderIndex++) {
	                        int columnIndex = columnOrder[columnOrderIndex];
	                        if (item.getText(columnIndex) == "")
	                        {
	                        	
	                        	Image a = item.getImage(columnIndex);
	                        	if (a.equals(CHECKED))
	                        	{
	                        		write.append("True");
	                        	}
	                        	else
	                        	{
	                        		write.append("False");
	                        	}
	                        	
	                       
	                        	write.append(",");
	                        	continue;
	                        }
	                       
	        
	                        write.append(item.getText(columnIndex));
	                        write.append(",");
	                        
	                    }
	                    write.append("\n");
	                   count++;
	                }
	                
	                write.flush();
	                write.close();
	          
	             
	        			
	        	 }
	        	 catch(Exception e2)
	        	 {
	        		 e2.printStackTrace();
	        	 }
	        }
	         
	     
   

  */

   private void createViewer(Composite parent) {
      viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
      createProgElemColumns(parent, viewer);
      final Table table = viewer.getTable();
      table.setHeaderVisible(true);
      table.setLinesVisible(true);

      viewer.setContentProvider(ArrayContentProvider.getInstance());
      // get the content for the viewer, setInput will call getElements in the contentProvider
      viewer.setInput(ModelProvider.INSTANCE.getProgramElements());
      // make the selection available to other views
      viewer.addSelectionChangedListener(new ISelectionChangedListener() {
         @Override
         public void selectionChanged(SelectionChangedEvent event) {
            IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
            Object firstElement = selection.getFirstElement();
            System.out.println("Do something with it: " + firstElement);
         }
      });

      // define layout for the viewer
      GridData gridData = new GridData();
      gridData.verticalAlignment = GridData.FILL;
      gridData.horizontalSpan = 2;
      gridData.grabExcessHorizontalSpace = true;
      gridData.grabExcessVerticalSpace = true;
      gridData.horizontalAlignment = GridData.FILL;
      viewer.getControl().setLayoutData(gridData);
   }

   private void createProgElemColumns(final Composite parent, final TableViewer viewer) {
      String[] titles = { "Package name", "Class name", "Method Name", "isReturnVoid", "isPublic" };
      int[] bounds = { 100, 100, 100, 100, 100 };

      TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
      col.setLabelProvider(new ColumnLabelProvider() {
         @Override
         public String getText(Object element) {
            ProgramElement p = (ProgramElement) element;
            return p.getPkgName();
         }
      });

      col = createTableViewerColumn(titles[1], bounds[1], 1);
      col.setLabelProvider(new ColumnLabelProvider() {
         @Override
         public String getText(Object element) {
            ProgramElement p = (ProgramElement) element;
            return p.getClassName();
         }
      });

      col = createTableViewerColumn(titles[2], bounds[2], 2);
      col.setLabelProvider(new ColumnLabelProvider() {
         @Override
         public String getText(Object element) {
            ProgramElement p = (ProgramElement) element;
            return p.getMethodName();
         }
      });

      col = createTableViewerColumn(titles[3], bounds[3], 3);
      col.setLabelProvider(new ColumnLabelProvider() {
         @Override
         public String getText(Object element) {
            return null;
         }

         @Override
         public Image getImage(Object element) {
            if (((ProgramElement) element).isReturnVoid()) {
               return CHECKED;
            } else {
               return UNCHECKED;
            }
         }
      });
      
      
      col = createTableViewerColumn(titles[4], bounds[4], 4);
      col.setLabelProvider(new ColumnLabelProvider() {
         @Override
         public String getText(Object element) {
            return null;
         }

         @Override
         public Image getImage(Object element) {
            if (((ProgramElement) element).isPublic()) {
               return CHECKED;
            } else {
               return UNCHECKED;
            }
         }
      });
      

      
   
      
    
      
      
   }

   private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber) {
      final TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
      final TableColumn column = viewerColumn.getColumn();
      column.setText(title);
      column.setWidth(bound);
      column.setResizable(true);
      column.setMoveable(true);
      return viewerColumn;
   }

   public TableViewer getViewer() {
      return viewer;
   }

   private static Image getImage(String file) {
      Bundle bundle = FrameworkUtil.getBundle(Viewer.class);
      URL url = FileLocator.find(bundle, new Path("icons/" + file), null);
      ImageDescriptor image = ImageDescriptor.createFromURL(url);
      return image.createImage();
   }

   @PreDestroy
   public void dispose() {
   }

   @Focus
   public void setFocus() {
   }
}
