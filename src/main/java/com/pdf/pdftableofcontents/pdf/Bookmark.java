package com.pdf.pdftableofcontents.pdf;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
class Bookmark {
  
  private int pageNumber;
  private String message;
  
}

