package thiagodnf.jacof.util;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Stream;

import com.google.common.base.Preconditions;

public class Convert {
	
	private Convert(){
		throw new UnsupportedOperationException();
	}
				
	/**
	 * Remove the space before and after for each element in array
	 * 
	 * @param array array that should be removed the space
	 * @return an array with no spaces
	 */
	public static String[] trim(String[] array) {

		Preconditions.checkNotNull(array, "The array cannot be null");
		Preconditions.checkArgument(array.length != 0, "The array cannot be empty");

		Stream<String> stream = Arrays.stream(array).map(String::trim);

		return stream.toArray(size -> new String[size]);
	}
	
	/**
	 * Convert from String[] to double[]
	 * 
	 * @param array the array that should be converted
	 * @return a double array
	 */
	public static double[] toDoubleArray(String[] array) {
		return toDoubleMatrix(new String[][] { array })[0];
	}

	/**
	 * Convert from String[][] to double[][]
	 * 
	 * @param matrix the matrix that should be converted
	 * @return a double matrix
	 */
	public static double[][] toDoubleMatrix(String[][] matrix) {

		Preconditions.checkNotNull(matrix, "The matrix cannot be null");
		
		double[][] result = new double[matrix.length][];

		for (int i = 0; i < matrix.length; i++) {
			
			result[i] = new double[matrix[i].length];

			for (int j = 0; j < matrix[i].length; j++) {				

				if (matrix[i][j] == null || matrix[i][j].isEmpty()) {
					result[i][j] = 0.0;
				} else {
					result[i][j] = Double.valueOf(matrix[i][j].trim());
				}
			}
		}

		return result;
	}
	
	/**
	 * Convert from String[] to int[]
	 * 
	 * @param array the array that should be converted
	 * @return an integer array
	 */
	public static int[] toIntegerArray(String[] array) {
		return toIntegerMatrix(new String[][] { array })[0];
	}
	
	/**
	 * Convert from String[][] to double[][]
	 * 
	 * @param matrix the matrix that should be converted
	 * @return a double matrix
	 */
	public static int[][] toIntegerMatrix(String[][] matrix) {

		Preconditions.checkNotNull(matrix, "The matrix cannot be null");
		
		int[][] result = new int[matrix.length][];

		for (int i = 0; i < matrix.length; i++) {
			
			result[i] = new int[matrix[i].length];

			for (int j = 0; j < matrix[i].length; j++) {				

				if (matrix[i][j] == null || matrix[i][j].isEmpty()) {
					result[i][j] = 0;
				} else {
					result[i][j] = Integer.valueOf(matrix[i][j].trim());
				}
			}
		}

		return result;
	}
	
	/**
	 * Convert a matrix to dot string to use in Graphviz
	 * 
	 * @param matrix Matrix will be converted
	 * @return The string formatted in Dot
	 */
	public static String toDot(double[][] matrix) {
		
		Preconditions.checkNotNull(matrix, "The matrix cannot be null");
		Preconditions.checkArgument(matrix.length > 0, "The matrix cannot be zero");

		StringBuffer buffer = new StringBuffer();

		buffer.append("graph G {\n");
		buffer.append("layout=\"circo\";\n");

		Locale.setDefault(new Locale("en", "US"));
		
		DecimalFormat df = new DecimalFormat("0.000000000000000");

		for (int i = 0; i < matrix.length; i++) {
			for (int j = i; j < matrix.length; j++) {
				if (i != j) {
					buffer.append(i + " -- " + j + " [penwidth=" + df.format(matrix[i][j]) + "]\n");
				}
			}
		}

		buffer.append("}");

		return buffer.toString();
	}

}
