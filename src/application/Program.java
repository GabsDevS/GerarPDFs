package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

import model.PDFMerger;

public class Program {

	public static void main(String[] args) throws Exception {
		

		List<InputStream> inputPdfList = new ArrayList<InputStream>();
		
		String pasta = "C:\\Users\\Gabriel\\Downloads\\Teste de Formatação\\";
		File file = new File(pasta);
		
		
		
		for (String arq : file.list()) {
			
		}
		
		// Percorre minha lista de arquivos
		for (String name : file.list()) {
			
			// Verifica o que é apenas PDF
			if (name.indexOf(".pdf") >= 0) {
				
				Document document = new Document(PageSize.A4, 20, 20, 210, 0);
				try {
					
					String capa = "C:\\Users\\Gabriel\\Downloads\\Teste de Formatação\\Capas\\Capa - " + String.format(name);

					PdfWriter.getInstance(document, new FileOutputStream(capa)); // criar o pdf
					
					inputPdfList.add(new FileInputStream(capa));
					System.out.println(capa);
					inputPdfList.add(new FileInputStream(pasta + name));
					System.out.println(pasta + name);

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
		
		OutputStream outputStream = new FileOutputStream("C:\\Users\\Gabriel\\Downloads\\PDF\\Merger.pdf");
		PDFMerger.mergePdfFiles(inputPdfList, outputStream);
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
	
