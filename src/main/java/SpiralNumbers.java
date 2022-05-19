/**
 * Class that generates an Ulam's spiral as a matrix
 */
public class SpiralNumbers {
    private int dimensionSize;
    private int[][] matrix;

    public SpiralNumbers(int dimensionSize) throws Exception {
        if (dimensionSize % 2 == 0) throw new Exception("Dimension size can't be even");
        this.dimensionSize = dimensionSize;
        matrix = new int[dimensionSize][dimensionSize];
        // Fill it with the ulam's spiral
        generateSpiral(matrix, dimensionSize);
    }

    public int getDimensionSize() {
        return dimensionSize;
    }

    public int[][] getUlamSpiral() {
        return matrix;
    }

    private void generateSpiral(int[][] matrix, int dimensionSize) {
        // Get the initial position
        int initRow = dimensionSize / 2;
        int initCol = dimensionSize / 2;
        // Number of steps before taking a turn, starts with 1 and as it turns it grows
        int stepsBefTurn = 1;
        // Direction to take the turn can be 1 out of 4
        int direction = 0;
        // Number of turns taken so far, this helps keep track of when to increment the stepsBefTurn
        int numberTurns = 0;
        // For every single cell in the matrix [Starts with one bc otherwise the modulo operator gets confused and starts turning incorrectly]
        for (int step = 1; step <= dimensionSize * dimensionSize; step++) {
            // Fill up the current cell with the current value
            if (isPrime(step)) matrix[initRow][initCol] = step;
            switch (direction) {
                case 0 -> initCol++; // Turn right
                case 1 -> initRow--; // Turn upwards
                case 2 -> initCol--; // Turn left
                case 3 -> initRow++; // Turn downwards
            }
            // Before traversing to the next direction check if we have to turn in the next iteration
            // bc the step starts in 1 it'll always take the correct turn at the correct time
            if (step % stepsBefTurn == 0) {
                direction = (direction + 1) % 4;
                // Increment the number of turns taken so far, if it's pair (i.e. 2 before the last turn) increment it
                numberTurns++;
                if (numberTurns % 2 == 0) {
                    stepsBefTurn++;
                }
            }
        }
    }

    private boolean isPrime(int number) {
        if (number < 2) return false;
        int numberSqrt = (int) Math.sqrt(number);
        for(int candidate = 2; candidate <= numberSqrt; candidate++) {
            if (number%candidate == 0) {
                return false;
            }
        }
        return true;
    }

    private String matrixToString(int[][] matrix) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                result.append(matrix[i][j]).append(" | ");
            }
            result.append("\n");
        }
        return result.toString();
    }

    @Override
    public String toString() {
        return matrixToString(matrix);
    }
}

























