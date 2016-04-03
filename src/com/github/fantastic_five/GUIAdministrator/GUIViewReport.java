package com.github.fantastic_five.GUIAdministrator;

/**
 * @author Fantastic Five (Jose Stovall)
 * A JPanel displaying all teachers in the university
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
//Alay
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.github.fantastic_five.StudentRegistrationMain;
import com.github.fantastic_five.GUIMisc.GUILoggedIn;

@SuppressWarnings("serial")
public class GUIViewReport extends JPanel
{
	private JTable table;

	/**
	 * Create the panel.
	 * 
	 * @param previousPanel
	 *            The panel that needs to be shown when the "Back" button is hit
	 * @return JPanel with contents for a table containing available classes
	 * 
	 */
	@SuppressWarnings({ "resource", "rawtypes", "null" })
	public GUIViewReport(JPanel previousPanel)
	{
		setBounds(0, 0, 618, 434);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 64, 587, 311);
		add(scrollPane);		
		
		Vector<String> data = null;
		String Line;
		Vector<String> listing = new Vector<String>();
		
		try{
			FileInputStream fs = new FileInputStream("course.dat");
			BufferedReader br = new BufferedReader(new InputStreamReader(fs));
			StringTokenizer st = new StringTokenizer(fs.toString(),"_");	
			while(st.hasMoreTokens())
			{
				listing.addElement(st.nextToken());
			}
			while ((Line = st.toString()) != null)
			{
				StringTokenizer st1 = new StringTokenizer(Line, "_");
				while(st1.hasMoreTokens())
				{
					data.addElement(st1.nextToken());
				}
				br.close();
			}
		}	
                
             
		catch (Exception e) {
			
		}

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null,
						null },
				{ null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null }, }, new String[]
		{ "CRN", "Class", "Capacity", "Remaining", "Teacher", "Time", "Room" }));
		scrollPane.setViewportView(table);

		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				StudentRegistrationMain.replaceMainWindowContents(previousPanel);
			}
		});
		btnBack.setBounds(10, 386, 128, 23);
		add(btnBack);

		JLabel lblCourseRemoval = new JLabel("View Report");
		lblCourseRemoval.setForeground(Color.GRAY);
		lblCourseRemoval.setFont(new Font("Verdana", Font.BOLD, 16));
		lblCourseRemoval.setHorizontalAlignment(SwingConstants.CENTER);
		lblCourseRemoval.setBounds(179, 21, 243, 23);
		add(lblCourseRemoval);

		JPanel loginPanel = new GUILoggedIn();
		loginPanel.setBounds(0, 0, 618, 24);
		add(loginPanel);

	}
}
