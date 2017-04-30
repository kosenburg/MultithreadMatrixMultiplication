import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    private static final String pathToMatrix1 = "enter matrix 1";
    private static final String pathToMatrix2 = "enter matrix 2";

    public static void main(String[] args) throws IOException, InterruptedException {
        Matrix matrix1 = new Matrix(readMatrix(pathToMatrix1, 2, 3));

        System.out.println("\nX\n");
        Matrix matrix2 = new Matrix(readMatrix(pathToMatrix2,3,1));



        Matrix results = Matrix.multiply(matrix1, matrix2);

        System.out.println();
        matrix1.displayMatrix();
        System.out.println("\nX\n");
        matrix2.displayMatrix();
        System.out.println("________\n");
        results.displayMatrix();
    }


    private static int[][] readMatrix(String path, int row, int col) throws IOException {
        BufferedReader inputStream = new BufferedReader(new FileReader(path));
        int[][] tempMatrix = new int[col][row];


        String line;
        int rowCounter = 0;
        while((line = inputStream.readLine()) != null) {
            String[] temp = line.split(" ");
            for (int i = 0; i < temp.length; i++) {
                tempMatrix[i][rowCounter] = Integer.parseInt(temp[i]);
            }
            rowCounter++;
        }
        return tempMatrix;
    }
}

