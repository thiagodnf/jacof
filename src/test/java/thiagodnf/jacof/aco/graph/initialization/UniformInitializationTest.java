package thiagodnf.jacof.aco.graph.initialization;

import static com.mscharhag.oleaster.matcher.Matchers.expect;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.describe;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.it;

import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.mscharhag.oleaster.runner.OleasterRunner;

import thiagodnf.jacof.aco.ACO;

@RunWith(OleasterRunner.class)
public class UniformInitializationTest {{
	
	ACO aco = Mockito.mock(ACO.class);
	
	describe("When create an instance of this class", () -> {

		it("should throw an exception when null aco is passed", () -> {
			expect(() -> {
				new UniformInitialization(null);
			}).toThrow(NullPointerException.class);
		});
		
		it("should throw an minValue > maxValue", () -> {
			expect(() -> {
				new UniformInitialization(aco, 1.0, 0.0);
			}).toThrow(IllegalArgumentException.class);
		});
	});
	
	describe("When default range is used", () -> {
		
		it("should return a number between 0.0 and 1.0", () -> {
			expect(new UniformInitialization(aco).getT0()).toBeBetween(0.0, 1.0);
		});
	});
	
	describe("When a range is specified", () -> {
		
		it("should return a number between 2.0 and 4.0", () -> {
			expect(new UniformInitialization(aco, 2.0, 4.0).getT0()).toBeBetween(2.0, 4.0);
		});
	});
	
	describe("When call the toString method", () -> {

		it("should return the correct string", () -> {
			expect(new UniformInitialization(aco).toString()).toEqual("UniformInitialization [0.0:1.0]");
		});
	});
}}
