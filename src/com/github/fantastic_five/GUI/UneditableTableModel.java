package com.github.fantastic_five.GUI;

/**
 * @author Fantastic Five (Jose Stovall)
 * This is a child class of DefaultTableModel that does not allow cell editing. Avoids constant creation of anonymous classes
 */

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