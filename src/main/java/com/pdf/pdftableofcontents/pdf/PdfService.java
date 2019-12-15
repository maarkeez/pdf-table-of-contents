package com.pdf.pdftableofcontents.pdf;

import static com.itextpdf.kernel.pdf.navigation.PdfExplicitDestination.createXYZ;
import static java.nio.file.Files.delete;
import static java.nio.file.Paths.get;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfOutline;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor( onConstructor = @__( @Autowired ) )
public class PdfService {
  
  private final BookmarkProvider bookmarkProvider;
  
  @SneakyThrows
  @PostConstruct
  public void createTableOfContents () {
    
    val inputPath = get("input.pdf").toAbsolutePath();
    val outputPath = get("output.pdf").toAbsolutePath();
    
    if (outputPath.toFile()
                  .exists()) {
      log.info("Deleting: {}", outputPath);
      delete(outputPath);
    }
    
    log.info("Input file: {}", inputPath);
    log.info("Output file: {}", outputPath);
    
    try (val reader = new PdfReader(inputPath.toFile());
         val inPdfDocument = new PdfDocument(reader);
         val writer = new PdfWriter(outputPath.toFile());
         val outPdfDocument = new PdfDocument(writer)) {
      
      clonePdf(inPdfDocument, outPdfDocument);
      createBookmarks(outPdfDocument, bookmarkProvider.getBookmarks());
      
    }
    
  }
  
  private void clonePdf ( PdfDocument from, PdfDocument to ) {
    from.copyPagesTo(1, from.getNumberOfPages(), to);
  }
  
  private void createBookmarks ( PdfDocument pdf, List<Bookmark> bookmarks ) {
    val outlines = pdf.getOutlines(true);
    bookmarks.forEach(bookmark -> createBookmark(pdf, outlines, bookmark));
  }
  
  private void createBookmark ( PdfDocument pdf, PdfOutline outlines, Bookmark bookmark ) {
    val page = pdf.getPage(bookmark.getPageNumber());
    val dest = createXYZ(page, 0, 0, 0);
    val outLine = outlines.addOutline(bookmark.getMessage());
    outLine.addDestination(dest);
  }
  
  
}