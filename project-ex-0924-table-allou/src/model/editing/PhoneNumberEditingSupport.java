package model.editing;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.ViewerCell;

import model.Person;

public class PhoneNumberEditingSupport extends EditingSupport {
   private TableViewer viewer;
   private TextCellEditor editor;

   public PhoneNumberEditingSupport(TableViewer viewer) {
      super(viewer);
      this.viewer = viewer;
      this.editor = new TextCellEditor(viewer.getTable());
   }

   @Override
   protected CellEditor getCellEditor(Object element) {
      return this.editor;
   }

   @Override
   protected boolean canEdit(Object element) {
      return true;
   }

   @Override
   protected Object getValue(Object element) {
      return ((Person) element).getPhoneNumber();
   }

   @Override
   protected void setValue(Object element, Object value) {
      ((Person) element).setPhoneNumber(String.valueOf(value));
      this.viewer.update(element, null);
   }

   @Override
   protected void saveCellEditorValue(CellEditor cellEditor, ViewerCell cell) {
      super.saveCellEditorValue(cellEditor, cell);
   }
}
