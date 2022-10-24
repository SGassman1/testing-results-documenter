package testDocumentation.main;
import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import com.spire.doc.documents.BuiltinStyle;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.documents.ParagraphStyle;
import com.spire.doc.documents.TextWrappingStyle;
import com.spire.doc.fields.DocPicture;

import testDocumentation.dialog.MainDialog;

import java.io.File;
import javax.swing.*;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainDialog mainDialog = new MainDialog();
		mainDialog.generateMainDialog();
	}

}
