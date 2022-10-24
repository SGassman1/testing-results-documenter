package testDocumentation.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import testDocumentation.word.WordDocument;
import testDocumentation.word.WordDocGenerator;

public class MainDialog extends JComponent implements ActionListener {
	private MainDialog mainDialog;
	public WordDocument wordDoc;
	
	public MainDialog() {
		mainDialog = this;
		wordDoc = new WordDocument();
		getWorkspaceSettings();
	}
	private MainDialog getMainDialog() {
		return mainDialog;
	}
	
	public void generateMainDialog() {
		JFrame frame = new JFrame("Test Documentation");
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       frame.setSize(1000,700);
	       //GridBagLayout layout = new GridBagLayout();
	       //GridBagConstraints c = new GridBagConstraints();
	       //c.weightx = c.weighty = 10.0;
	       //c.fill = c.BOTH;
	       //c.gridwidth = c.REMAINDER;
	       //frame.getContentPane().setLayout(layout);
	       frame.add(mainDialog);
	       frame.getContentPane().add(getContentPanel(), BorderLayout.CENTER);
	       frame.setVisible(true);
	}
	
	private JPanel contentPanel;
	private JPanel getContentPanel() {
		if(contentPanel == null) {
			contentPanel = new JPanel();
		}
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		//c.gridheight = 3;
		
		
		contentPanel.setLayout(new BorderLayout());
		
		contentPanel.add(getMainPanel(), BorderLayout.CENTER);
		contentPanel.add(getBottomPanel(), BorderLayout.SOUTH);
		
		return contentPanel;
	}
	
	private JPanel mainPanel;
	private JPanel getMainPanel() {
		if(mainPanel == null) {
			mainPanel = new JPanel();
		}
		
		mainPanel.add(getFormPanel());
		mainPanel.add(getLabelsPanel());
		
		return mainPanel;
	}
	
	private JPanel bottomPanel;
	private JPanel getBottomPanel() {
		if(bottomPanel == null) {
			bottomPanel = new JPanel();
		}
		
		bottomPanel.add(getCreateDocButton());
		
		return bottomPanel;
	}
	
	private JPanel formPanel;
	private JTextField workingDirInput;
	private JTextField folderNameInput;
	private JTextField subFolderNameInput;
	private JTextField labelInput;
	private JPanel getFormPanel() {
		if(formPanel == null) {
			formPanel = new JPanel();
			workingDirInput = textFieldBuilder(wordDoc.getWorkingDirectory(), "workingDirInput");
			folderNameInput = textFieldBuilder(wordDoc.getFolderName(), "folderNameInput");
			subFolderNameInput = textFieldBuilder(wordDoc.getSubFolderName(), "subFolderNameInput");
			labelInput = new JTextField();
		}
		
		workingDirInput.setColumns(30);
		folderNameInput.setColumns(30);
		subFolderNameInput.setColumns(30);
		labelInput.setColumns(30);
		
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = 1;
		//c.fill = GridBagConstraints.VERTICAL;
		formPanel.setBackground(Color.GRAY);
		//c.anchor = GridBagConstraints.WEST;
		c.gridy = 0;
		formPanel.setLayout(new GridBagLayout());
		formPanel.add(workingDirInput, c);
		c.gridy = 1;
		formPanel.add(folderNameInput, c);
		c.gridy = 2;
		formPanel.add(subFolderNameInput, c);
		c.gridy = 3;
		formPanel.add(labelInput, c);
		
		return formPanel;
	}
	
	private JPanel labelsPanel;
	private JPanel getLabelsPanel() {
		if(labelsPanel == null) {
			labelsPanel = new JPanel();
		}
		labelsPanel.setLayout(new GridLayout(6,6));
		buildLabels();
		
		return labelsPanel;
	}
	
	private void buildLabels() {
		Object[] labels = wordDoc.getLabels();
		if(labels.length > 0) {
			for(Object label : labels) {
				JButton labelObj = new JButton((String)label);
				labelsPanel.add(labelObj);
			}
		}
	}
	
	
	private JButton createDocButton;
	private JButton getCreateDocButton() {
		if(createDocButton == null) {
			createDocButton = buttonBuilder("Create Document", "createDoc");
		}
		return createDocButton;
	}
	private void createDoc() {
		WordDocGenerator docGenerator = new WordDocGenerator(wordDoc);
		docGenerator.createDocument();
	}
	
	
	private JButton buttonBuilder(String label, String actionCommand) {
		JButton button = new JButton(label);
		button.setActionCommand(actionCommand);
		button.addActionListener(getMainDialog());
		
		return button;
	}
	
	private JTextField textFieldBuilder(String label, String actionCommand) {
		JTextField textField = new JTextField(label);
		textField.setActionCommand(actionCommand);
		textField.addActionListener(getMainDialog());
		
		return textField;
	}
	
	private void updateAll() {
		wordDoc.setWorkingDirectory(workingDirInput.getText());
		wordDoc.setFolderName(folderNameInput.getText());
		wordDoc.setSubFolderName(subFolderNameInput.getText());
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch(command) {
        case "createDoc": updateAll(); createDoc();
        	break;
        case "workingDirInput": wordDoc.setWorkingDirectory(workingDirInput.getText());
        	break;
        case "folderNameInput": wordDoc.setFolderName(folderNameInput.getText());
        	System.out.println(wordDoc.getFolderName());
        	break;
        case "subFolderNameInput": wordDoc.setSubFolderName(subFolderNameInput.getText());
        	break;
        }

    }
	
	private void getWorkspaceSettings() {
		try {
			File settingsFile = new File("C:\\Users\\Sam\\Documents\\NationwideWork\\TestAutoDocSettings\\settings.txt");
			Scanner reader = new Scanner(settingsFile);
			
			while(reader.hasNextLine()) {
				String data = reader.nextLine();
				String[] setting = data.split("=");
				
				if(setting.length > 1) {
					switch(setting[0]) {
					case "workingDir": wordDoc.setWorkingDirectory(setting[1]);
					break;
					case "folderName": wordDoc.setFolderName(setting[1]);
					break;
					case "subFolderName": wordDoc.setSubFolderName(setting[1]);
					break;
					case "labels": wordDoc.addLabels(setting[1].split(","));
					break;
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Settings file not found");
			e.printStackTrace();
		}
		wordDoc.printDetails();
	}
}
