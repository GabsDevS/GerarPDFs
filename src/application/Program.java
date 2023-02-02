package application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class Program {

	public static void main(String[] args) {
		

		File file = new File("C:\\Users\\Gabriel\\Downloads\\34001108");
		
		// Percorre minha lista de arquivos
		for (String name : file.list()) {
			
			// Verifica o que é apenas PDF
			if (name.indexOf(".pdf") >= 0) {
				
				Document document = new Document(PageSize.A4, 20, 20, 210, 0);
				try {

					PdfWriter.getInstance(document,
							new FileOutputStream("C:\\Users\\Gabriel\\Downloads\\34001108\\Capas\\CAPA - " + name)); // criar
					// o
					// pdf
					document.open();

					// adicionando um parágrafo no documento

					Paragraph texto = new Paragraph(tratar(name.replace(".pdf", "")),
							new Font(FontFamily.TIMES_ROMAN, 45, Font.BOLD));
					texto.setAlignment(Element.ALIGN_CENTER);

					document.add(texto);

				} catch (DocumentException de) {
					System.err.println(de.getMessage());
				} catch (IOException ioe) {
					System.err.println(ioe.getMessage());
				}
				document.close();
			}
		}
	}

	public static String tratar(String str) {
		str = str.replace("–", "-").replace("  ", " ");

		String[] words = str.split(" ");
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < words.length; i++) {

			sb.append(words[i].substring(0, 1).toUpperCase() + words[i].substring(1));
			if (words[i].equals("-")) {
				sb.append(String.format("%n"));
			}

			sb.append(" ");

		}

		return sb.toString().replace("  ", " ").replace(" -", "");

	}
	
}
	
