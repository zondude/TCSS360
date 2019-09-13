package View;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Model.Calculator;

/**
 * View that allows users to calculate costs from project.
 * 
 * @author Abraham Lee
 *
 */
public class CalculatorView extends JPanel {

	/** Auto-generated UID */
	private static final long serialVersionUID = 1L;
	
	/** List of operations possible */
	private static final String[] OPERATIONS = {"+", "-", "*", "/"};
	
	/**
	 * Create the panel.
	 */
	public CalculatorView() {
		TitledBorder title;
		title = BorderFactory.createTitledBorder("Calculator");
		setBorder(title);
		addButtons();
	}
	
	/**
	 * Creates the buttons and their actions required for calculating.
	 */
	private void addButtons() {
		JPanel numbsContainer = new JPanel();
		numbsContainer.setLayout(new BoxLayout(numbsContainer, BoxLayout.Y_AXIS));
		JPanel opsContainer = new JPanel();
		opsContainer.setLayout(new BoxLayout(opsContainer, BoxLayout.Y_AXIS));
		Calculator calculator = new Calculator();
		JTextField input = new JTextField("", 10);
		input.setHorizontalAlignment(JTextField.RIGHT);
		numbsContainer.add(input);

		
		for (int i = 10; i > 0; i-=3) {
			JPanel numbRow = new JPanel();
			for (int j = i - 3; j < i; j++) {
				if (j >= 0) {
					JButton currNumb = new JButton(Integer.toString(j));
					currNumb.addActionListener(theEvent -> {
						input.setText(input.getText() + currNumb.getText());
						revalidate();
			        });
					numbRow.add(currNumb);
				}
			}
			numbsContainer.add(numbRow);
		}
		
		for (int i = 0; i < OPERATIONS.length; i++) {
			JButton currOp = new JButton(OPERATIONS[i]);
			currOp.addActionListener(theEvent -> {
				input.setText(input.getText() + currOp.getText());
				revalidate();
	        });
			opsContainer.add(currOp);
		}
		
		JButton equalButton = new JButton("=");
		equalButton.addActionListener(theEvent -> {
			calculator.userButtons("AC");
			String toSend = input.getText();
			for (int i = 0; i < toSend.length(); i++) {
				char numbOp = toSend.charAt(i);
				if (numbOp >= '*' && numbOp <= '9') {
					calculator.userButtons(Character.toString(numbOp));
				}
			}
			calculator.userButtons(equalButton.getText());
			input.setText(calculator.getTotal());
			revalidate();
        });
		opsContainer.add(equalButton);
		
		JButton allClearButton = new JButton("AC");
		allClearButton.addActionListener(theEvent -> {
			calculator.userButtons(allClearButton.getText());
			input.setText("");
			revalidate();
        });
		opsContainer.add(allClearButton);
		
		add(numbsContainer);
		add(opsContainer);
	}
}
