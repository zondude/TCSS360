package View;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Model.Field;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

public class FieldView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField name;
	private JTextField data;
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
	
	public String getName() {
		return name.getText();
	}
	
	public Double getData() {
		return Double.parseDouble(data.getText());
	}
	
	public String getUnit() {
		return unit.getText();
	}
}
