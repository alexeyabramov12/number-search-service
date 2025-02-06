package com.example.number_search.service.impl;

import com.example.number_search.exception.NotEnoughNumbersException;
import com.example.number_search.service.NthMaxFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NthMaxFinderImplTest {

    private NthMaxFinder nthMaxFinder;

    @BeforeEach
    void setUp() {
        nthMaxFinder = new NthMaxFinderImpl();
    }

    @Test
    @DisplayName("Should return the 3rd maximum number from a valid list")
    void givenValidList_whenFindNthMax_thenReturnCorrectValue() {
        List<Integer> numbers = List.of(10, 5, 3, 8, 15, 7);
        int result = nthMaxFinder.findNthMax(numbers, 3);
        assertEquals(8, result, "The 3rd maximum number should be 8");
    }

    @Test
    @DisplayName("Should return the maximum number when N = 1")
    void givenValidList_whenFind1stMax_thenReturnMaxValue() {
        List<Integer> numbers = List.of(10, 5, 3, 8, 15, 7);
        int result = nthMaxFinder.findNthMax(numbers, 1);
        assertEquals(15, result, "The maximum number should be 15");
    }

    @Test
    @DisplayName("Should throw NotEnoughNumbersException when the list is empty")
    void givenEmptyList_whenFindNthMax_thenThrowException() {
        List<Integer> numbers = List.of();
        assertThrows(NotEnoughNumbersException.class, () -> nthMaxFinder.findNthMax(numbers, 2),
                "Should throw NotEnoughNumbersException when list is empty");
    }

    @Test
    @DisplayName("Should throw NotEnoughNumbersException when the list has fewer than N elements")
    void givenSmallList_whenFindNthMax_thenThrowException() {
        List<Integer> numbers = List.of(5, 2);
        assertThrows(NotEnoughNumbersException.class, () -> nthMaxFinder.findNthMax(numbers, 3),
                "Should throw NotEnoughNumbersException when N is larger than list size");
    }

    @Test
    @DisplayName("Should correctly handle lists with duplicate values")
    void givenListWithDuplicates_whenFindNthMax_thenReturnCorrectValue() {
        List<Integer> numbers = List.of(10, 10, 5, 8, 8, 3);
        int result = nthMaxFinder.findNthMax(numbers, 3);
        assertEquals(5, result, "The 3rd maximum number should be 5 despite duplicates");
    }

    @Test
    @DisplayName("Should handle a list where all numbers are the same")
    void givenListWithSameNumbers_whenFindNthMax_thenReturnSameNumber() {
        List<Integer> numbers = List.of(7, 7, 7, 7, 7);
        int result = nthMaxFinder.findNthMax(numbers, 3);
        assertEquals(7, result, "The 3rd maximum number should be 7");
    }
}
