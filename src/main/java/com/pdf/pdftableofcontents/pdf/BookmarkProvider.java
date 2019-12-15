package com.pdf.pdftableofcontents.pdf;

import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties
class BookmarkProvider {
  
  private List<Bookmark> bookmarks;
  
}
