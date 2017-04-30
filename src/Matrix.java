import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Kevin on 4/29/2017.
 */
public class Matrix {
    private int[][] matrix;

    public Matrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public Matrix(int row, int col) {
        matrix = new int[col][row];
    }

    public void set(int element, int row, int col) {
        matrix[col][row] = element;
    }

    public int[] getRow(int i) {
        int[] temp = new int[getNumberOfColumns()];
        int j = 0;
        for (int[] col: matrix) {
            temp[j] = col[i];
            j++;
        }
        return temp;
    }

    public int getNumberOfRows() {
        return matrix[0].length;
    }

    public int getNumberOfColumns() {
        return matrix.length;
    }

    public int[] getColumn(int i) {
        return matrix[i];
    }

    public void displayMatrix() {
        for (int j = 0; j <  getNumberOfRows(); j++) {
            for (int i = 0; i < getNumberOfColumns(); i++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }


    }

    public static Matrix multiply(Matrix matrix1, Matrix matrix2) throws InterruptedException {
        WorkerThread.setResults(matrix1.getNumberOfRows(), matrix2.getNumberOfColumns());

        ExecutorService executorService = Executors.newFixedThreadPool(matrix1.getNumberOfRows());

        for (int i = 0; i < matrix1.getNumberOfRows(); i++) {
            int[] currentRow = matrix1.getRow(i);
            Runnable workerThread = new WorkerThread(currentRow, matrix2, i);
            System.out.println("Starting thread: " + i);
            executorService.execute(workerThread);
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);


        return WorkerThread.getResults();
    }
}
