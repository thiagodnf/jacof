package thiagodnf.jacof.util.io;

import static com.mscharhag.oleaster.matcher.Matchers.expect;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.describe;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.it;

import java.io.File;
import java.util.Arrays;

import org.junit.runner.RunWith;

import com.mscharhag.oleaster.runner.OleasterRunner;

@RunWith(OleasterRunner.class)
public class TSPLIBReaderTest {
	
	protected static String dir = "src#test#resources#tsplibreader".replace("#", File.separator);

	protected File validWithSeveralSpaces = new File(dir + File.separator + "valid-with-several-spaces.tsp");
	
	protected File valid = new File(dir + File.separator + "valid.tsp");
	
	{

		describe("When initialize an TSPLIB Reader", () -> {

			it("should throw an exception when null is passed", () -> {
				expect(() -> {
					new TSPLIBReader(null);
				}).toThrow(NullPointerException.class);
			});			
		});
		
		describe("When read a tsp valid file", () -> {
			
			TSPLIBReader reader = new TSPLIBReader(new InstanceReader(valid));
			
			it("should return the right dimensions", () -> {
				expect(reader.getDimension()).toEqual(4);
			});
			
			it("should return the right distance matrix", () -> {
				expect(Arrays.equals(reader.getDistance()[0], new double[] {0.0, 0.0, 4.0, 2.8284271247461903}));
				expect(Arrays.equals(reader.getDistance()[0], new double[] {4.0, 0.0, 5.656854249492381, 4.0}));
				expect(Arrays.equals(reader.getDistance()[0], new double[] {4.0, 5.656854249492381, 0.0, 4.0}));
				expect(Arrays.equals(reader.getDistance()[0], new double[] {5.656854249492381, 4.0, 4.0, 0.0}));
			});
		});
		
		describe("When read a tsp valid file with several spaces", () -> {
			
			TSPLIBReader reader = new TSPLIBReader(new InstanceReader(validWithSeveralSpaces));
			
			it("should return the right dimensions", () -> {
				expect(reader.getDimension()).toEqual(4);
			});
			
			it("should return the right distance matrix", () -> {
				expect(Arrays.equals(reader.getDistance()[0], new double[] {0.0, 0.0, 4.0, 2.8284271247461903}));
				expect(Arrays.equals(reader.getDistance()[0], new double[] {4.0, 0.0, 5.656854249492381, 4.0}));
				expect(Arrays.equals(reader.getDistance()[0], new double[] {4.0, 5.656854249492381, 0.0, 4.0}));
				expect(Arrays.equals(reader.getDistance()[0], new double[] {5.656854249492381, 4.0, 4.0, 0.0}));
			});
		});
		
	}
}
