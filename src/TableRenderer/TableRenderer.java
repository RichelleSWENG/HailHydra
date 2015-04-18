package TableRenderer;

import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;
import javax.swing.JTable;
import static javax.swing.SwingConstants.RIGHT;
import javax.swing.table.DefaultTableCellRenderer;

public class TableRenderer extends DefaultTableCellRenderer
{

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean selected, boolean focused, int row, int column)
	{
		super.getTableCellRendererComponent(table, value, selected, focused,
				row, column);
		setBackground(Color.white);
		DecimalFormat df;
		if (value instanceof Float)
		{
			df = new DecimalFormat("#0.00");
			if (value != null)
			{
				this.setText(df.format(Double.parseDouble(value.toString())));
			} else
			{
				this.setText("");
			}
			this.setHorizontalAlignment(RIGHT);
		}
		return this;

	}

	public void changeBackground(Color color)
	{
		this.setBackground(color);
	}

}
