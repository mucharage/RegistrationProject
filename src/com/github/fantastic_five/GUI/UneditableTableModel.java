package com.github.fantastic_five.GUI;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class UneditableTableModel extends DefaultTableModel
{
	public UneditableTableModel(Object[][] tableContents, String[] headers)
	{
		super(tableContents, headers);
	}

	@Override
	public boolean isCellEditable(int row, int column)
	{
		return false;
	}
}