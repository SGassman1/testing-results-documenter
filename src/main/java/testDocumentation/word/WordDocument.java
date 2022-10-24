package testDocumentation.word;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WordDocument {
	private String workingDirectory;
	private String folderName;
	private String subFolderName;
	private List<String> labels;
	private String docExtension;
	private String filePath;
	
	public WordDocument() {
		workingDirectory = null;
		folderName = null;
		subFolderName = "";
		labels = new ArrayList<String>();
		docExtension = ".docx";
		
	}
	
	public WordDocument(String documentExtension) {
		workingDirectory = null;
		folderName = null;
		subFolderName = "";
		labels = new ArrayList<String>();
		docExtension = documentExtension;
	}
	
	public void printDetails() {
		System.out.println("workingDir = " + workingDirectory);
		System.out.println("folderName = " + folderName);
		System.out.println("subFolderName = " + subFolderName);
	}
	
	public String getWorkingDirectory() {
		return workingDirectory;
	}
	public void setWorkingDirectory(String wd) {
		workingDirectory = wd;
	}
	
	public String getFolderName() {
		return folderName;
	}
	public void setFolderName(String fn) {
		folderName = fn;
	}
	
	public String getSubFolderName() {
		return subFolderName;
	}
	public void setSubFolderName(String sfn) {
		subFolderName = sfn;
	}
	
	public Object[] getLabels() {
		return labels.toArray();
	}
	public void addLabel(String label) {
		labels.add(label);
	}
	public void addLabels(String[] newLabels) {
		for(String label : newLabels) {
			labels.add(label);
		}
	}
	
	public String getDocExtension() {
		return docExtension;
	}
	
	public String getFilePath() {
		return workingDirectory + 
				File.separatorChar + 
				folderName + 
				File.separatorChar + 
				folderName + 
				docExtension;
	}
	
	public String getImageFolderPath() {
		return workingDirectory + 
				File.separatorChar + 
				folderName + 
				File.separatorChar + 
				subFolderName + 
				File.separatorChar;
	}
}
