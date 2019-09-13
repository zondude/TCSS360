package View;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Side view that displays all projects and allows us to open.
 * Only files in the 'projects' folder are shown.
 * 
 * @author Abraham Lee, Jonathan Kim
 *
 */
public class FileList extends JList {
	
	/** Auto-generated UID */
	private static final long serialVersionUID = 1L;
	/** Initializing a list of string file names. */
	private List<String> fileNames;
	/** Initializing a property change support object. */
	private PropertyChangeSupport myPcs;
	
	/**
	 * Initializes the FileList class.
	 * @param thePcs Property Change Support
	 */
	public FileList(PropertyChangeSupport thePcs) {
		fileNames = new ArrayList<>();
		myPcs = thePcs;
		addFiles();
	}
	
	/**
	 * Adds files to JList to display and be clicked on.
	 */
	public void addFiles() {
		File folder = new File("./projects");
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
		  String fileName = listOfFiles[i].getName();
		  if (listOfFiles[i].isFile() && 
			  (fileName.substring(fileName.length() - 4)).equals(".txt")) {
			fileNames.add(listOfFiles[i].getName());
		  }
		}
		this.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent evt) {
            	String s = "./projects/" + (String) getSelectedValue();
            	myPcs.firePropertyChange("FILE", -1, s);
                System.out.println(s);
            }
        });
		setListData(fileNames.toArray());
	}
	
}
