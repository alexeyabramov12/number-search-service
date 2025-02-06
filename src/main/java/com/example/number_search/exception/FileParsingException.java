package com.example.number_search.exception;

/**
 * Exception thrown when an error occurs while reading an file.
 * <p>
 * This exception is used to indicate that an issue occurred during file parsing,
 * such as a missing file, corrupted format, or an I/O error.
 * </p>
 */
public class FileParsingException extends RuntimeException {

    /**
     * Constructs a new FileParsingException with a formatted message indicating
     * the file path and the underlying cause of the error.
     *
     * @param filePath The path of the file that could not be read.
     * @param cause    The underlying exception that caused this parsing error.
     */
    public FileParsingException(String filePath, Throwable cause) {
        super(String.format("Error reading file: %s. Cause: %s", filePath, cause.getMessage()), cause);
    }
}
