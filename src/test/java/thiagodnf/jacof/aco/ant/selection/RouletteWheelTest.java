package thiagodnf.jacof.aco.ant.selection;

import static com.mscharhag.oleaster.matcher.Matchers.expect;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.describe;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.it;

import org.junit.runner.RunWith;

import com.mscharhag.oleaster.runner.OleasterRunner;

@RunWith(OleasterRunner.class)
public class RouletteWheelTest {{

	describe("When select an element", () -> {

		it("should throw an exception when null probability is passed", () -> {
			expect(() -> {
				new RouletteWheel().select(null, 1.0);
			}).toThrow(NullPointerException.class);
		});
		
		it("should return an element when an element is passed", () -> {
			expect(new RouletteWheel().select(new double[]{1.0}, 1.0)).toEqual(0);;
		});
		
		it("should return an element when an element has 0 as probability", () -> {
			expect(new RouletteWheel().select(new double[]{0.0, 1.0}, 1.0)).toEqual(1);
		});
	});
	
	describe("When call the toString method", () -> {

		it("should return a valid string", () -> {
			expect(new RouletteWheel().toString()).toBeNotNull();
		});
	});
}}
