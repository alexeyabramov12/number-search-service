package com.example.number_search.service.impl;

import com.example.number_search.service.FileNumberService;
import com.example.number_search.service.FileParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileNumberServiceImpl implements FileNumberService {

    private final FileParser fileParser;

    @Override
    public int findNthMax(String filePath, int n) {
        List<Integer> numbers = fileParser.parse(filePath);
        if (numbers.size() < n) {
            return 0;
        }
        return 0;
    }
}
