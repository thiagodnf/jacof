package thiagodnf.jacof.util;

import java.util.Arrays;
import java.util.stream.Stream;

import com.google.common.base.Preconditions;

public class ConvertUtils {
	
	private ConvertUtils(){
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

}
