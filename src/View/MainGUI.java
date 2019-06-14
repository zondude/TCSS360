package View;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;

import Model.Project;

/**
 * This class is a main to run the GUI.
 * 
 * @author Abraham & Jonathan
 *
 */
public class MainGUI extends JFrame implements PropertyChangeListener {

	/** Auto-generated UID */
	private static final long serialVersionUID = 1L;


	/** File opener to browse directory. */
    private final JFileChooser myFileChooser;
    /** Initializing a project object. */
    private Project project;
    /** Initializing a project view object. */
    private ProjectView projectView;
    /** Initializing a project scroll object. */
    private JScrollPane projectScroll;
    /** Initializing a File object. */
    private File selectedFile;
    /** Initializing a property change object. */
    private PropertyChangeSupport myPcs;

	/**
	 * Create the application.
	 */
	public MainGUI() {
		myFileChooser = new JFileChooser("."); 
		initialize();
	}

	/**
	 * Initialize the contents of the frame with menu bar and buttons.
	 */
	private void initialize() {
		projectView = null;
		projectScroll = null;
		selectedFile = null;
		
		myPcs = new PropertyChangeSupport(this);
		myPcs.addPropertyChangeListener("FILE", this);
		
		setLayout(new BorderLayout());
		
		setBounds(200, 100, 900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewProject = new JMenuItem("New Project");
		mntmNewProject.addActionListener(theEvent -> {
			selectedFile = null;
			openNewProject(new ProjectView(new Project()));
        });
		mnFile.add(mntmNewProject);
		
		JMenuItem mntmOpenProject = new JMenuItem("Open Project");
		mntmOpenProject.addActionListener(theEvent -> {
			project = fileLoad();
			openNewProject(new ProjectView(project));
        });
		mnFile.add(mntmOpenProject);
		
		JMenuItem mntmSaveAs = new JMenuItem("Save As");
		mntmSaveAs.addActionListener(theEvent -> {
			int rVal = myFileChooser.showSaveDialog(MainGUI.this);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				selectedFile = myFileChooser.getSelectedFile();
			}
			saveProject();
        });
		mnFile.add(mntmSaveAs);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(theEvent -> {
			if (selectedFile == null) {
				int rVal = myFileChooser.showSaveDialog(MainGUI.this);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					selectedFile = myFileChooser.getSelectedFile();
				}
			}
			saveProject();
        });
		mnFile.add(mntmSave);
		
		JMenu mnSettings = new JMenu("Settings");
		menuBar.add(mnSettings);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(theEvent -> {
			try {
				Double version = getVersion();
				JOptionPane.showMessageDialog(null,
                        "Abraham, Joel, Jonathan, Patrick\n"
                        + "Ver: " + version.toString());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
        });
		mnSettings.add(mntmAbout);
		
		add(new FileList(myPcs), BorderLayout.WEST);
		add(new CalculatorView(), BorderLayout.SOUTH);
	}
	
	/**
	 * Method to save the project.
	 */
	public void saveProject() {
		String source = projectView.getName() + "\n";
		for (ComponentView componentView : projectView.getCompViewList()) {
			source += "C " + componentView.getName() + "\n";
			for (FieldView fieldView : componentView.getFieldViewList()) {
				String name = fieldView.getName();
				if (name.equals("")) {
					name = "none";
				}
				String unit = fieldView.getUnit();
				if (unit.equals("")) {
					unit = "none";
				}
				source += "F " + name + ", " + fieldView.getData() + ", " + unit + "\n";
			}
		}
		FileWriter f2;
		try {
		    f2 = new FileWriter(selectedFile);
		    f2.write(source);
		    f2.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}    
	}
	
	/**
     * Gets version number from txt file.
     * @return version number
     * @throws FileNotFoundException
     */
    public Double getVersion() throws FileNotFoundException {
        File file = new File("txt/version.txt");
        Scanner sc = new Scanner(file);
        double version = Double.parseDouble(sc.nextLine());
        sc.close();
        return version;
    }
    
    /**
     * Loads the file and checks for bad file format.
     */
    private Project fileLoad() {
        final int returnValue = myFileChooser.showOpenDialog(null);
        Project temp = null;
        if (returnValue == JFileChooser.APPROVE_OPTION) {     
            selectedFile = myFileChooser.getSelectedFile();
            try {
            	temp = new Project(new Scanner(selectedFile));
            } catch (final IOException error) {
                JOptionPane.showMessageDialog(null, error.getMessage(),
                                              "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
        return temp;
    }
    
    /**
     * Method to open the project from a text file.
     * @param theProjectView the project view object.
     */
    private void openNewProject(ProjectView theProjectView) {
    	if (projectView != null && projectScroll != null) {
			remove(projectView);
			remove(projectScroll);
			revalidate();
			repaint();
		}
		projectView = theProjectView;
		projectView.setVisible(true);
		add(projectView, BorderLayout.EAST);
		projectScroll = new JScrollPane(projectView);
		add(projectScroll);
		revalidate();
		repaint();
    }

	@Override
	public void propertyChange(PropertyChangeEvent theEvent) {
		 if ("FILE".equals(theEvent.getPropertyName())) {
			 try {
				selectedFile = new File((String) theEvent.getNewValue());
				project = new Project(new Scanner(selectedFile));
				openNewProject(new ProjectView(project));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		 }
	}
	
	/**
	 * Method to delete the project to start a new one.
	 */
	public void deleteProject() {
		removeAll();
    	revalidate();
    	repaint();
    }
}
