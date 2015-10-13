package PDF;

import java.io.FileOutputStream; 
import java.io.IOException; 
//import api iText import com.lowagie.text.*; 
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter; 
public class PDF { 
	public static void main(String[] args) { 
		// cria��o do objeto documento 
		Document document = new Document(); 
		try { 
			PdfWriter.getInstance(document, new FileOutputStream("C://PDF_LinhaCodigo.pdf")); 
			document.open(); 
			// adicionando um par�grafo ao documento 
			document.add(new Paragraph("Exemplo Gera��o de Arquivo PDF via iText - Java")); 
			}
		
		catch(DocumentException de) { 
			System.err.println(de.getMessage()); 
			} 
		
		catch(IOException ioe) { 
			System.err.println(ioe.getMessage()); 
			} 
		
		document.close(); 
		}		
	}
