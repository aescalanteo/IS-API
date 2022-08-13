package com.interseguro.test.repositories;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.springframework.stereotype.Repository;

@Repository
public class MatrixRepository {

    /**
     * Validates if the given matrix is square (NxN).
     * 
     * @param matrix the integer matrix to validate
     * @return if the matrix is valid or not
     */
    public boolean validate(int[][] matrix) {
        for (int[] row : matrix) {
            if (row.length != matrix.length)
                return false;
        }
        return true;
    }

    /**
     * Rotates an input matrix 90 degrees counterclockwise.
     * 
     * @param matrix the integer matrix to rotate
     * @return the rotated matrix
     * @deprecated  Replaced by {@link #rotateFunctional(int[][])}
     */
    @Deprecated
    public int[][] rotate(int[][] matrix) {
        int matrixLength = matrix.length;

        // For trasposing columns to rows (like a diagonal mirror)
        // Ex: 1 2 -> 1 3
        //     3 4 -> 2 4
        for (int i = 0; i < matrixLength; i++) {
            for (int j = i; j < matrixLength; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // To sort columns backwards (bubble sort)
        // Ex: 1 3 -> 2 4
        //     2 4 -> 1 3
        for (int i = 0; i < matrixLength; i++) {
            int lower = 0;
            int higher = matrixLength - 1;
            while (lower < higher) {
                int temp = matrix[lower][i];
                matrix[lower][i] = matrix[higher][i];
                matrix[higher][i] = temp;
                lower++;
                higher--;
            }
        }

        return matrix;
    }

    /**
     * Rotates an input matrix 90 degrees counterclockwise.
     * 
     * @param matrix the int matrix to rotate
     * @return the rotated matrix
     */
    public int[][] rotateFunctional(final int[][] matrix) {

        // To sort rows reverse
        // Ex: 1 2 -> 2 1
        //     3 4 -> 4 3
        int[][] reorderedMatrix = Stream.of(matrix).map(row -> sortReverseArray(row)).toArray(int[][]::new);

        // For trasposing columns to rows (like a diagonal mirror)
        // Ex: 2 1 -> 2 4
        //     4 3 -> 1 3
        return IntStream.range(0, reorderedMatrix[0].length)
                .mapToObj(i -> Stream.of(reorderedMatrix)
                        .mapToInt(row -> row[i])
                        .toArray())
                .toArray(int[][]::new);
    }

    /**
     * To reverse the natural order of the given int array.
     * 
     * @param array the int array to sort
     * @return the reverse sorted array
     */
    private int[] sortReverseArray(int[] array) {
        Integer[] converted = new Integer[array.length];
        Arrays.setAll(converted, i -> array[i]);
        Arrays.sort(converted, Comparator.reverseOrder());
        Arrays.setAll(array, i -> converted[i]);
        return array;
    }
}
