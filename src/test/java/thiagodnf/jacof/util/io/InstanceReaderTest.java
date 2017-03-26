package thiagodnf.jacof.util.io;

import static com.mscharhag.oleaster.matcher.Matchers.expect;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.describe;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.it;

import java.io.File;
import java.util.Arrays;

import org.junit.runner.RunWith;

import com.mscharhag.oleaster.runner.OleasterRunner;

@RunWith(OleasterRunner.class)
public class InstanceReaderTest {
	
	protected File validFile = new File("src/test/resources/instance-reader/valid-file.nrp");
	
	protected File emptyFile = new File("src/test/resources/instance-reader/empty-file.nrp");
	
	protected File differentColumnsFile = new File("src/test/resources/instance-reader/different-columns.nrp");
	
	protected File validMatrix = new File("src/test/resources/instance-reader/valid-matrix.nrp");
	
	protected File matrixWithSpaceFile = new File("src/test/resources/instance-reader/matrix-with-space.nrp");
	
	{

		describe("When initialize an Instance Reader", () -> {

			it("should throw an exception when null is passed", () -> {
				expect(() -> {
					new InstanceReader(null);
				}).toThrow(NullPointerException.class);
			});

			it("should throw an exception when the file is not found", () -> {
				expect(() -> {
					new InstanceReader(new File("no-found.nrp"));
				}).toThrow(IllegalArgumentException.class);
			});

			it("should throw an exception when a directory is passed as filename", () -> {
				expect(() -> {
					new InstanceReader(new File("src/test/resources/instance-reader/"));
				}).toThrow(IllegalArgumentException.class);
			});
			
			it("should return the correct number of read lines", () -> {
				InstanceReader reader = new InstanceReader(validFile);
				expect(reader.getNumberOfLines()).toEqual(18);
			});
			
			it("should return the correct separator", () -> {
				InstanceReader reader = new InstanceReader(validFile);
				reader.setSeparator(",");
				expect(reader.getSeparator()).toEqual(",");
			});
			
			it("should return 1 line with empty file", () -> {
				InstanceReader reader = new InstanceReader(emptyFile);
				expect(reader.getNumberOfLines()).toEqual(1);
			});
		});
		
		describe("When read a file with different columns size", () -> {
			
			InstanceReader reader = new InstanceReader(differentColumnsFile);
			
			it("should throw an exception", () -> {
				expect(() -> {
					reader.readLines(3);
				}).toThrow(IllegalStateException.class);
			});
		});
		
		describe("When read a valid string matrix", () -> {
			
			it("should throw an exception for invalid number of lines", () -> {
				expect(() -> {
					new InstanceReader(validMatrix).readLines(0);
				}).toThrow(IllegalArgumentException.class);
			});
			
			it("should throw an exeception when the number of lines is more than the file has", () -> {
				expect(() -> {
					new InstanceReader(validMatrix).readLines(4);
				}).toThrow(IllegalStateException.class);
			});
			
			it("should return the first array", () -> {
				double[] result = new InstanceReader(validMatrix).readDoubleArray();
				double[] expected = new double[]{2, 3, 5, 6};
				
				expect(Arrays.equals(result, expected)).toBeTrue();
			});
		});
		
		describe("When read a file with space", () -> {
			
			it("should return 0 as space", () -> {
				InstanceReader reader = new InstanceReader(matrixWithSpaceFile);
				
				expect(Arrays.equals(new double[]{2, 3, 5, 6}, reader.readDoubleArray())).toBeTrue();
				expect(Arrays.equals(new double[]{2, 3, 0, 6}, reader.readDoubleArray())).toBeTrue();
				expect(Arrays.equals(new double[]{6, 7, 9, 17}, reader.readDoubleArray())).toBeTrue();
			});
		});
		
		describe("When read a line", () -> {

			InstanceReader reader = new InstanceReader(validFile);
			
			it("should return null when there is no line for reading", () -> {
				
				// Reading all lines before
				for (int i = 0; i < 18; i++) {
					reader.readLine();
				}
				
				expect(reader.readLine()).toBeNull();
			});
		});
		
		describe("When read a valid instance", () -> {
	
			it("should return the correct values", () -> {

				InstanceReader reader = new InstanceReader(validFile);

				expect(reader.readDoubleValue()).toEqual(10);
				expect(reader.readDoubleValue()).toEqual(3);
				
				expect(Arrays.equals(new double[]{3, 4,2},reader.readDoubleArray())).toBeTrue();
				expect(Arrays.equals(new double[]{60, 40, 40, 30, 20, 20, 25, 70, 50, 20},reader.readDoubleArray())).toBeTrue();
				
				double[][] expected = {{10,8,6,5,7,8,6,9,6,10}, {10,10,4,9,7,6,6,8,7,10}, {5,6,8,1,5,2,4,3,5,7}};
				double[][] result = reader.readDoubleMatrix(3);
				
				expect(Arrays.equals(expected[0], result[0])).toBeTrue();
				expect(Arrays.equals(expected[1], result[1])).toBeTrue();
				expect(Arrays.equals(expected[2], result[2])).toBeTrue();
			});
		});
		
	}
}
