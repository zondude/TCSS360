package View;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Model.Field;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

public class FieldView extends JPanel {
	/** Auto-generated UID */
	private static final long serialVersionUID = 1L;
	/** Initializing a text field name object. */
	private JTextField name;
	/** Initializing a data text field object. */
	private JTextField data;
	/** Initializing a unit text field object. */
	private JTextField unit;

	/**
	 * Create the panel.
	 */
	public FieldView(Field field) {
		TitledBorder title;
		title = BorderFactory.createTitledBorder("Field");
		setBorder(title);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		name = new JTextField("", 20);
//		name.setPreferredSize(6, 6, 130, 26);
		name.setText(field.getName());
		fieldPanel.add(name);
		
		data = new JTextField("", 20);
//		data.setBounds(148, 6, 130, 26);
		data.setText(Double.toString(field.getData()));
		fieldPanel.add(data);
		
		unit = new JTextField("", 20);
//		unit.setBounds(290, 6, 130, 26);
		unit.setText(field.getUnit());
		fieldPanel.add(unit);
		add(fieldPanel);
		
		setVisible(true);
		validate();

	}
	
	/**
	 * a method to get the name for the text field.
	 * @return the text name.
	 */
	public String getName() {
		return name.getText();
	}
	
	/**
	 * a method to get the data for the text field.
	 * @return the data name.
	 */
	public Double getData() {
		return Double.parseDouble(data.getText());
	}
	
	/**
	 * a method to get the unit name for the text field.
	 * @return the unit name.
	 */
	public String getUnit() {
		return unit.getText();
	}
}
