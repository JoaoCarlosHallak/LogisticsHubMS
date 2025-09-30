package com.hallak.shared_libraries.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PdfGenerationException extends RuntimeException{

    public PdfGenerationException(String message) {
        super(message);
    }

    public PdfGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
