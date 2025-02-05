package com.example.number_search.controller;

import com.example.number_search.service.FileNumberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/file")
@Tag(name = "File Processing", description = "API for processing files and extracting numbers")
public class FileNumberController {

    private final FileNumberService fileNumberService;

    @Operation(
            summary = "Find the N-th maximum number in a file",
            description = "Extracts numbers from the given file and returns the N-th largest number."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully found the N-th maximum number"),
            @ApiResponse(responseCode = "400", description = "Bad request: invalid input parameters"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/nth-max")
    public ResponseEntity<?> getNthMax(
            @RequestParam @NotBlank(message = "File path must not be empty")
            @Parameter(description = "Full path to the file", example = "/data/numbers.xlsx") String filePath,

            @RequestParam @Min(value = 1, message = "N must be greater than 0")
            @Parameter(description = "The position of the maximum number to find", example = "3") int n
    ) {
        return ResponseEntity.ok(fileNumberService.findNthMax(filePath, n));
    }
}
