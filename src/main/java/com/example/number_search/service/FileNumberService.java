package com.example.number_search.service;

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
     * @param n the position of the maximum number to retrieve (1-based index)
     * @return the N-th maximum number found in the file
     * @throws IllegalArgumentException if the file does not exist,
     *                                  contains insufficient numbers,
     *                                  or if {@code n} is invalid
     * @throws RuntimeException if an error occurs while reading the file
     */
    int findNthMax(String filePath, int n);
}
