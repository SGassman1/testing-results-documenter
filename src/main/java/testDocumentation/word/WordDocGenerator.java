package testDocumentation.word;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import com.spire.doc.documents.BuiltinStyle;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.documents.ParagraphStyle;
import com.spire.doc.documents.TextWrappingStyle;
import com.spire.doc.fields.DocPicture;

public class WordDocGenerator {
	
	Document document;
	WordDocument wordDoc;
	
	public WordDocGenerator(WordDocument wordDocument) {
		document = new Document();
		wordDoc = wordDocument;
	}
	
	
	public void createDocument() {
		deleteOldDoc();
		System.out.println("Building new word document..");
        addText();
        addImages();
        saveDoc();
	}
	
	private void deleteOldDoc() {
		System.out.println("Checking for old document..");
		File oldFile = new File(wordDoc.getFilePath());
		if(oldFile.delete()) {
			System.out.println("Old document deleted");
		} else {
			System.out.println("No old document found");
		}
	}
	
	private void addImages() {
		System.out.println("Adding images to document..");
    
        //Create an object of DocPicture class
        DocPicture picture = new DocPicture(document);

        //Load an image from disk
        picture.loadImage(wordDoc.getImageFolderPath() + "1.Screenshot.PNG");
        
        
        //Set the size of the image
        int width = 500;
        picture.setWidth(width);
        picture.setHeight((int)(getImageRatio() * width));

        //Set the text wrapping style of the image as Square
        picture.setTextWrappingStyle( TextWrappingStyle.Inline);

        //Insert the image at the start of the second paragraph
        document.getSections().get(0).getParagraphs().get(4).getChildObjects().insert(0,picture);
	}
	private double getImageRatio() {
		double ratio = 1;
		try {
			BufferedImage bimg = ImageIO.read(new File(wordDoc.getImageFolderPath() + "1.Screenshot.PNG"));
			double width = bimg.getWidth();
			double height = bimg.getHeight();
			ratio = height / width;
			System.out.println("Image width: " + width);
			System.out.println("Image height: " + height);
			System.out.println("Image ratio: " + ratio);
			
		}catch(IOException e) {
			System.out.println("Error: could not get image ratio");
		}
		
		return ratio;
	}
	
	private void saveDoc() {
		System.out.println("Saving word document..");
        
        // Save the document
        document.saveToFile(wordDoc.getFilePath(), FileFormat.Docx);
        
        File newFile = new File(wordDoc.getFilePath());
        if(newFile.exists()) {
        	System.out.println("Success: Word document created");        	
        } else {
        	System.out.println("Failure: Word document was not created"); 
        }
	}
	
	private void addText() {
		System.out.println("Adding text to document..");
        
        // Add a section
        Section section = document.addSection();
 
        // Add a heading
        Paragraph heading = section.addParagraph();
        heading.appendText("Java");
 
        // Add a subheading
        Paragraph subheading_1 = section.addParagraph();
        subheading_1.appendText("What's Java");
 
        // Adding sub-headings
        // two paragraph under the first subheading
 
        // Adding paragraph 1
        Paragraph para_1 = section.addParagraph();
        para_1.appendText(
            "Java is a general purpose, high-level programming language developed by Sun Microsystems."
            + " The Java programming language was developed by a small team of engineers, "
            + "known as the Green Team, who initiated the language in 1991.");
 
        // Adding paragraph 2
        Paragraph para_2 = section.addParagraph();
        para_2.appendText(
            "Originally called OAK, the Java language was designed for handheld devices and set-top boxes. "
            + "Oak was unsuccessful and in 1995 Sun changed the name to Java and modified the language to take "
            + "advantage of the burgeoning World Wide Web. ");
 
        // Adding another subheading
        Paragraph subheading_2 = section.addParagraph();
        subheading_2.appendText("Java Today");
 
        // Adding one paragraph under the second subheading
        Paragraph para_3 = section.addParagraph();
        para_3.appendText(
            "Today the Java platform is a commonly used foundation for developing and delivering content "
            + "on the web. According to Oracle, there are more than 9 million Java developers worldwide and more "
            + "than 3 billion mobile phones run Java.");
 
        // Apply built-in style to heading and subheadings
        // so that it is easily distinguishable
        heading.applyStyle(BuiltinStyle.Title);
        subheading_1.applyStyle(BuiltinStyle.Heading_3);
        subheading_2.applyStyle(BuiltinStyle.Heading_3);
 
        // Customize a paragraph style
        ParagraphStyle style = new ParagraphStyle(document);
 
        // Paragraph name
        style.setName("paraStyle");
        // Paragraph format
        style.getCharacterFormat().setFontName("Arial");
        // Paragraph font size
        style.getCharacterFormat().setFontSize(11f);
        // Adding styles using inbuilt method
        document.getStyles().add(style);
 
        // Apply the style to other paragraphs
        para_1.applyStyle("paraStyle");
        para_2.applyStyle("paraStyle");
        para_3.applyStyle("paraStyle");
 
        // Iteration for white spaces
        for (int i = 0;
             i < section.getParagraphs().getCount(); i++) {
 
            // Automatically add whitespaces
            // to every paragraph in the file
            section.getParagraphs()
                .get(i)
                .getFormat()
                .setAfterAutoSpacing(true);
        }
	}
}
