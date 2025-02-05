package com.example.number_search.service;

import java.util.List;

/**
 * Interface for parsing files and extracting numerical data.
 * <p>
 * Implementations of this interface should provide a mechanism
 * to read a file from the given path and extract a list of integers.
 * </p>
 *
 * @author [Alexey Abramov]
 */
public interface FileParser {

    /**
     * Parses a file and extracts a list of integers.
     * <p>
     * The method reads the specified file, processes its content,
     * and returns a list of integers found in the file.
     * Implementations should handle different file formats and potential
     * parsing exceptions.
     * </p>
     *
     * @param filePath the absolute or relative path to the file
     * @return a list of integers extracted from the file
     * @throws RuntimeException if an error occurs while reading or parsing the file
     */
    List<Integer> parse(String filePath);
}
