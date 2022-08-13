package com.interseguro.test.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interseguro.test.services.MatrixService;

/**
 * Matrix endpoint
 */
@RestController
@RequestMapping("/matrix")
public class MatrixController {
    @Autowired
    MatrixService matrixService;

    @PostMapping()
    public int[][] rotateMatrix(@RequestBody int[][] matrix) {
        return matrixService.rotateMatrix(matrix);
    }
}