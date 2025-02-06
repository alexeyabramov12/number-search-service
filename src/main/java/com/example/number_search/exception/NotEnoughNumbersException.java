package com.example.number_search.exception;

/**
 * Exception thrown when there are not enough numbers in a list
 * to determine the N-th maximum value.
 * <p>
 * This exception is used to indicate that the requested N-th maximum
 * cannot be determined because the number of elements in the provided list
 * is less than the specified value of N.
 * </p>
 */
public class NotEnoughNumbersException extends RuntimeException {

    /**
     * Constructs a new NotEnoughNumbersException with a formatted message
     * indicating the required N-th maximum value that could not be found.
     *
     * @param n The N-th maximum number that was requested but could not be determined.
     */
    public NotEnoughNumbersException(int n) {
        super(String.format("Not enough numbers to find the %d-th maximum.", n));
    }
}
