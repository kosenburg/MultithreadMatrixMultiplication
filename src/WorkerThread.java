import java.util.Arrays;

/**
 * Created by Kevin on 4/29/2017.
 */
public class WorkerThread implements Runnable{
    private static Matrix results;
    private Matrix matrix;
    private int[] row;
    private int rowNumber;

    public WorkerThread(int[] row, Matrix matrix, int rowNumber) {
        this.rowNumber = rowNumber;
        this.row = row;
        this.matrix = matrix;
    }

    public static void setResults(int row, int col) {
        results = new Matrix(row, col);
    }

    @Override
    public void run() {
        for (int currentCol = 0; currentCol < matrix.getNumberOfColumns(); currentCol++) {
            System.out.println("Performing dot product in thread: " + rowNumber + " on: " + Arrays.toString(row) + " * " + Arrays.toString(matrix.getColumn(currentCol)));
            int sum = dotProduct(row, matrix.getColumn(currentCol));
            System.out.println("Result in thread: " + rowNumber + " : " + sum);
            results.set(sum, rowNumber, currentCol);
        }
    }

    private int dotProduct(int[] v1, int[] v2) {
        int sum = 0;
        for (int j = 0; j < row.length; j++) {
            sum += v1[j] * v2[j];
        }
        return sum;
    }

    public static Matrix getResults() {
        return results;
    }
}
