package thiagodnf.jacof.util;

import static com.mscharhag.oleaster.matcher.Matchers.expect;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.describe;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.it;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import org.junit.runner.RunWith;

import com.mscharhag.oleaster.runner.OleasterRunner;

@RunWith(OleasterRunner.class)
public class ConvertUtilsTest {{
	
	describe("When create an instance of this class", () -> {
		
		it("should throw an exception when the constructor is called", () -> {
			expect(() -> {
				Constructor<Convert> c = Convert.class.getDeclaredConstructor();
				c.setAccessible(true);
				c.newInstance();
			}).toThrow(InvocationTargetException.class);
		});	
	});
	
	describe("When trim an array", () -> {
		
		it("should throw an exception when null is passed", () -> {
			expect(() -> {
				Convert.trim(null);
			}).toThrow(NullPointerException.class);
		});

		it("should throw an exception when empty is passed", () -> {
			expect(() -> {
				Convert.trim(new String[]{});
			}).toThrow(IllegalArgumentException.class);
		});
		
		it("should remove correctly the spaces", () -> {
			String[] result = Convert.trim(new String[]{"A ", " B", " C "});
			String[] expected = new String[]{"A", "B", "C"};
			expect(Arrays.equals(result, expected)).toBeTrue();
		});
	});
		
	describe("When convert from a string array to a double array", () -> {
		
		it("should throw an exception when null is passed", () -> {
			expect(() -> {
				Convert.toDoubleArray(null);
			}).toThrow(NullPointerException.class);
		});
		
		it("should convert the array with the same size", () -> {
			double[] result = Convert.toDoubleArray(new String[]{"12","123","12"});
			expect(result.length).toEqual(3);
		});
		
		it("should convert correctly the value ['12.3','123.7','12.9'] to [255]", () -> {
			
			double[] result = Convert.toDoubleArray(new String[]{"12.3","123.7","12.9"});
			double[] expected = new double[] { 12.3, 123.7, 12.9 };
			
			expect(Arrays.equals(expected,result)).toBeTrue();		
		});
		
		it("should return an empty array", () -> {

			double[] result = Convert.toDoubleArray(new String[] {});
			double[] expected = new double[] {};

			expect(Arrays.equals(expected, result)).toBeTrue();						
		});
		
	});
	
	describe("When convert from string matrix to a double matrix", () -> {
		
		it("should throw an exception when null is passed", () -> {
			expect(() -> {
				Convert.toDoubleMatrix(null);
			}).toThrow(NullPointerException.class);
		});
		
		it("should return 0.0 when some value is null", () -> {
			
			String[][] input = new String[][] { { "1", "2", null }, { "1", "2", "3" } };

			double[][] result = Convert.toDoubleMatrix(input);
			double[][] expected = new double[][] { { 1, 2, 0 }, { 1, 2, 3 } };

			expect(Arrays.deepEquals(result, expected)).toBeTrue();
		});
		
		it("should return 0.0 when some value is empty", () -> {
			
			String[][] input = new String[][] { { "1", "2", "" }, { "1", "2", "3" } };

			double[][] result = Convert.toDoubleMatrix(input);
			double[][] expected = new double[][] { { 1, 2, 0 }, { 1, 2, 3 } };

			expect(Arrays.deepEquals(result, expected)).toBeTrue();
		});
	});	
}}
