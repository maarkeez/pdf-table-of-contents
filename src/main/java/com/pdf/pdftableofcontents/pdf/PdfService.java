package com.pdf.pdftableofcontents.pdf;

import static java.nio.file.Files.delete;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.navigation.PdfExplicitDestination;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Link;
import com.itextpdf.layout.element.Paragraph;
import java.nio.file.Paths;
import javax.annotation.PostConstruct;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PdfService {
  
  @SneakyThrows
  @PostConstruct
  public void createTableOfContents () {
    
    val inputPath = Paths.get("input.pdf")
                         .toAbsolutePath();
    val outputPath = Paths.get("output.pdf")
                          .toAbsolutePath();
    
    if (outputPath.toFile()
                  .exists()) {
      delete(outputPath);
    }
    
    log.info("Input file: {}", inputPath);
    log.info("Output file: {}", outputPath);
    
    try (val reader = new PdfReader(inputPath.toFile()); val inPdfDocument = new PdfDocument(reader); val writer = new PdfWriter(outputPath.toFile());
         val outPdfDocument = new PdfDocument(writer); val outDocument = new Document(outPdfDocument)) {
      
      inPdfDocument.copyPagesTo(1, inPdfDocument.getNumberOfPages(), outPdfDocument);
      
      outDocument.add(new AreaBreak());
      
      val firstPage = outPdfDocument.getFirstPage();
      val dest = PdfExplicitDestination.createXYZ(firstPage, 0, 0, 0);
      val outlines = outPdfDocument.getOutlines(true);
      val outLine = outlines.addOutline("Mi primer marcador");
      outLine.addDestination(dest);
      Link c = new Link("", dest);
      outDocument.add(new Paragraph(c));
      
    }
    
  }
}