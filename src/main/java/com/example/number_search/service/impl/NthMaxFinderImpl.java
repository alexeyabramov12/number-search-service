package com.example.number_search.service.impl;

import com.example.number_search.exception.NotEnoughNumbersException;
import com.example.number_search.service.NthMaxFinder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

@Service
public class NthMaxFinderImpl implements NthMaxFinder {

    @Override
    public int findNthMax(List<Integer> numbers, int n) throws NotEnoughNumbersException {
        if (numbers.size() < n || numbers.isEmpty()) {
            throw new NotEnoughNumbersException(n);
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(n);
        Set<Integer> uniqueNumbers = new HashSet<>();
        int maxNumber = Integer.MIN_VALUE;

        for (int num : numbers) {
            maxNumber = Math.max(maxNumber, num);

            if (uniqueNumbers.contains(num)) {
                continue;
            }

            uniqueNumbers.add(num);

            if (minHeap.size() < n) {
                minHeap.offer(num);
            } else if (num > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(num);
            }
        }

        if (uniqueNumbers.size() < n) {
            return maxNumber;
        }

        return minHeap.peek();
    }
}
