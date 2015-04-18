package TableRenderer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class ComboTableRenderer extends AbstractCellEditor implements
		TableCellRenderer, TableCellEditor, ListCellRenderer
{

	private JPanel panel = new JPanel();
	private JComboBox cmbPartNumber = new JComboBox();
	private Object val;
	private DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
	private DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean focused, int row, int column)
	{
		cmbPartNumber.addItem("1");
		cmbPartNumber.addItem("2");
		cmbPartNumber.setEditable(true);
		panel.setBackground(Color.WHITE);

		/*
		 * tableRenderer = (DefaultTableCellRenderer)
		 * tableRenderer.getTableCellRendererComponent(table, value, isSelected,
		 * focused, row, column); configureRenderer(tableRenderer, value);
		 * return tableRenderer;
		 */

		if (column == 1)
		{
			JPanel panel = new JPanel();
			panel.add(cmbPartNumber);
			if (value != null)
				cmbPartNumber.setSelectedItem(value);
			return panel;
		}
		return panel;
	}

	@Override
	public Object getCellEditorValue()
	{
		// TODO Auto-generated method stub
		return val;
	}

	public boolean cellEditable()
	{
		return false;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column)
	{
		// TODO Auto-generated method stub
		try
		{
			return this.getTableCellRendererComponent(table, value, isSelected,
					true, row, column);
		} catch (Exception e)
		{
			return null;
		}
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus)
	{
		// TODO Auto-generated method stub
		listRenderer = (DefaultListCellRenderer) listRenderer
				.getListCellRendererComponent(list, value, index, isSelected,
						cellHasFocus);
		configureRenderer(listRenderer, value);
		return listRenderer;
	}

	private void configureRenderer(JLabel renderer, Object value)
	{
		if ((value != null) && (value instanceof Color))
		{
			renderer.setText(value.toString());
			renderer.setBackground((Color) value);
		} else
		{
			renderer.setText((String) value);
		}
	}
}
