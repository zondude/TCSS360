package View;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Model.Component;
import Model.Field;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;

/**
 * A class to make the component view on the GUI.
 * @author Abraham Lee, Jonathan Kim
 *
 */
public class ComponentView extends JPanel {
	/** Auto-generated UID */
	private static final long serialVersionUID = 1L;
	/** Initializing a text field object. */
	private JTextField textField;
	/** Initializing a list of field view object. */
	private List<FieldView> fieldViewList;
	/** Initializing list of component. object. */
	private List<java.awt.Component> myComp = new ArrayList<>();

	/**
	 * Create the panel.
	 */
	public ComponentView(Component component) {
		fieldViewList = new ArrayList<>();
		
		TitledBorder title;
		title = BorderFactory.createTitledBorder("Component");
		setBorder(title);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel componentPanel = new JPanel();
		componentPanel.setLayout(new BoxLayout(componentPanel, BoxLayout.Y_AXIS));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		
		textField = new JTextField("", 20);
		textField.setText(component.getName());
		add(textField);
		
		for (Field field : component.getFields()) {
			FieldView temp = new FieldView(field);
			fieldViewList.add(temp);
			componentPanel.add(temp);
		}
		add(componentPanel);
		
		JButton btnAddField = new JButton("Add Field");
		btnAddField.setBounds(198, 47, 117, 29);
		btnAddField.addActionListener(theEvent -> {
			FieldView temp = new FieldView(new Field());
			fieldViewList.add(temp);
			componentPanel.add(temp);
			revalidate();
        });
		buttonPanel.add(btnAddField);
		
		JButton btnDeleteField = new JButton("Delete Field");
		btnDeleteField.setBounds(327, 47, 117, 29);
		btnDeleteField.addActionListener(theEvent -> {
			if (fieldViewList.size() > 0) {
				fieldViewList.remove(fieldViewList.size() - 1);
				java.awt.Component[] components = componentPanel.getComponents();
				for (java.awt.Component x : components) {
					myComp.add(x);
				}
				componentPanel.remove(myComp.size() - 1);
				myComp.clear();
				revalidate();
			}

        });
		buttonPanel.add(btnDeleteField);
		add(buttonPanel);
		setVisible(true);
		validate();

	}
	
	/**
	 * a method to get the name for the text field.
	 * @return the text name.
	 */
	public String getName() {
		return textField.getText();
	}
	
	/**
	 * a method to get the list of text field.
	 * @return the textfield list.
	 */
	public List<FieldView> getFieldViewList() {
		return fieldViewList;
	}

}
