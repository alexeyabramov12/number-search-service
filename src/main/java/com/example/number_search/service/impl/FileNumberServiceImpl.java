package com.example.number_search.service.impl;

import com.example.number_search.exception.FileParsingException;
import com.example.number_search.exception.NotEnoughNumbersException;
import com.example.number_search.service.FileNumberService;
import com.example.number_search.service.FileParser;
import com.example.number_search.service.NthMaxFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileNumberServiceImpl implements FileNumberService {

    private final FileParser fileParser;
    private final NthMaxFinder nthMaxFinder;

    @Override
    public int findNthMax(String filePath, int n) throws FileParsingException, NotEnoughNumbersException {
        List<Integer> numbers = fileParser.parse(filePath);
        return nthMaxFinder.findNthMax(numbers, n);
    }
}
