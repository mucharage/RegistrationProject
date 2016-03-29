package com.github.fantastic_five.GUIStudent;
/**
 * @author Alay Patel (leader)
 * Group 5 
 */

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUIRemove
{
	private JFrame frame;

	/**
	 * This window displays warning if the user is certain to make confirmation on the changes. 
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					GUIRemove window = new GUIRemove();
					window.frame.setVisible(true);
				}//end of try
				catch (Exception e)
				{
					e.printStackTrace();
				}//end of catch
			}//end of tun
		});
	}//end of invokelater

	/**
	 * Create the application.
	 */
	public GUIRemove()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 307, 117);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		JLabel txtpnAreYouSure = new JLabel();
		txtpnAreYouSure.setText("Are you sure?");
		txtpnAreYouSure.setForeground(Color.RED);
		txtpnAreYouSure.setFont(new Font("Verdana", Font.BOLD, 16));
		txtpnAreYouSure.setBounds(86, 11, 127, 20);
		frame.getContentPane().add(txtpnAreYouSure);

		JButton btnYes = new JButton("Yes");
		btnYes.setBounds(10, 44, 89, 23);
		frame.getContentPane().add(btnYes);

		JButton btnNo = new JButton("No");
		btnNo.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				frame.dispose();
			}//end of mouseClicked
		});//end of mouseListener
		btnNo.setBounds(192, 42, 89, 23);
		frame.getContentPane().add(btnNo);
	}//end of main
}//end of GUIremove
