package com.example.number_search.service;

import com.example.number_search.exception.NotEnoughNumbersException;

import java.util.List;

/**
 * Service interface for finding the N-th maximum number in a list.
 * <p>
 * This interface defines a method to determine the N-th largest number
 * from a given list of integers. Implementations of this interface should
 * provide an efficient algorithm to achieve this.
 * </p>
 */
public interface NthMaxFinder {

    /**
     * Finds the N-th maximum number in a given list of integers.
     *
     * @param numbers The list of integers from which to find the N-th maximum value.
     * @param n       The position of the maximum number to find (e.g., 1st max, 2nd max, etc.).
     * @return The N-th maximum number in the list.
     * @throws NotEnoughNumbersException If the list contains fewer than N elements.
     */
    int findNthMax(List<Integer> numbers, int n) throws NotEnoughNumbersException;
}
