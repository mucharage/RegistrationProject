import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A component that lets the user enter a number, using a button pad labeled with digits.
 */
@SuppressWarnings("serial")
public class KeyPad extends JPanel
{
	private JPanel buttonPanel;
	private JButton clearButton;
	private JTextField display;

	/**
	 * Constructs the keypad panel.
	 */
	public KeyPad()
	{
		setLayout(new BorderLayout());

		// Add display field

		display = new JTextField();
		// Prevents the display from being edited - the user doesn't ever need to do this directly, use the buttons!
		display.setEditable(false);
		add(display, "North");

		// Make button panel

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4, 3));

		// Add digit buttons

		addButton("7");
		addButton("8");
		addButton("9");
		addButton("4");
		addButton("5");
		addButton("6");
		addButton("1");
		addButton("2");
		addButton("3");
		addButton("0");
		addButton(".");

		// Add clear entry button

		clearButton = new JButton("CE");
		buttonPanel.add(clearButton);

		class ClearButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				display.setText("");
			}
		}
		clearButton.addActionListener(new ClearButtonListener());

		add(buttonPanel, "Center");
	}

	/**
	 * Adds a button to the button panel
	 * 
	 * @param label
	 *            the button label
	 */
	private void addButton(final String label)
	{
		class DigitButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{

				// Don't add two decimal points
				if (label.equals(".") && display.getText().indexOf(".") != -1)
					return;

				// Append label text to button
				display.setText(display.getText() + label);
			}
		}
		String filename = "rsc/" + label + ".png";
		JButton button;
		if (label.equals("."))
		{
			button = new JButton(label);
		}
		else
		{
			// Tries to bind the Klingon font image to the button
			button = new JButton();
			try
			{
				Image img = ImageIO.read(getClass().getResource(filename));
				button.setIcon(new ImageIcon(img));
			}
			catch (IOException ex)
			{
				System.out.println(ex.getMessage());
			}
		}

		buttonPanel.add(button);
		ActionListener listener = new DigitButtonListener();
		button.addActionListener(listener);
	}

	/**
	 * Gets the value that the user entered.
	 * 
	 * @return the value in the text field of the keypad
	 */
	public double getValue()
	{
		return Double.parseDouble(display.getText());
	}

	/**
	 * Clears the display.
	 */
	public void clear()
	{
		display.setText("");
	}
}
