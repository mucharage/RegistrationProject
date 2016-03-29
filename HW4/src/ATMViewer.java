	import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * A graphical simulation of an automatic teller machine.
 */
public class ATMViewer
{
	public static void main(String[] args)
	{
		ATM theATM;

		try
		{
			Bank theBank = new Bank();
			theBank.readCustomers("src/customers.txt");
			theATM = new ATM(theBank);
		}
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(null, "Error opening accounts file.");
			return;
		}

		JFrame frame = new ATMFrame(theATM);
		// Changed this to reflect our group
		frame.setTitle("First National Bank of Group 5");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Set this window non-resizable
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		// This tries to make the GUI look more like Windows / OSX / Linux instead of using default Java button style
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
		{
			System.out.println("Could not use default System GUI feel");
			System.out.println(e.getMessage());
		}
	}
}
