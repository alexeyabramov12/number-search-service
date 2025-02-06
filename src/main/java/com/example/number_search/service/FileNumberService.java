package com.example.number_search.service;

import com.example.number_search.exception.FileParsingException;
import com.example.number_search.exception.NotEnoughNumbersException;

/**
 * Service interface for processing numerical data from a file.
 * <p>
 * This interface defines a method to find the N-th maximum number
 * from a given file. Implementations should handle file reading,
 * data extraction, and the selection of the N-th maximum value.
 * </p>
 *
 * @author [Alexey Abramov]
 */
public interface FileNumberService {

    /**
     * Finds the N-th maximum number in a file.
     * <p>
     * The method reads a file from the specified path, extracts numerical data,
     * and returns the N-th largest number found in the file.
     * </p>
     *
     * @param filePath the absolute or relative path to the file
     * @param n        the position of the maximum number to retrieve (1-based index)
     * @return the N-th maximum number found in the file
     * @throws FileParsingException      if an error occurs while reading or parsing the file
     *                                   (e.g., file not found, corrupted format, I/O error)
     * @throws NotEnoughNumbersException if the file contains fewer than N numbers,
     *                                   making it impossible to determine the requested maximum value
     */
    int findNthMax(String filePath, int n) throws FileParsingException, NotEnoughNumbersException;
}
