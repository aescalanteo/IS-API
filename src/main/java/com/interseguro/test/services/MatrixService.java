package com.interseguro.test.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.interseguro.test.repositories.MatrixRepository;

@Service
public class MatrixService {

    @Autowired
    MatrixRepository matrixRepository;

    public int[][] rotateMatrix(int[][] matrix) {
        if (matrixRepository.validate(matrix)) {
            try {
                matrix = matrixRepository.rotateFunctional(matrix);
                return matrix;
            } catch (Error err) {
                throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "There was an unexpected error");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Matrix must be NxN");

        }
    }

}
