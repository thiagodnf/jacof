package thiagodnf.jacof.aco.rule.globalupdate.deposit;

import static com.mscharhag.oleaster.matcher.Matchers.expect;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.describe;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.it;

import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.mscharhag.oleaster.runner.OleasterRunner;

import thiagodnf.jacof.aco.ACO;

@RunWith(OleasterRunner.class)
public class FullDepositTest {{

	ACO aco = Mockito.mock(ACO.class);
	
	describe("When create an instance of this class", () -> {

		it("should throw an exception when null aco is passed", () -> {
			expect(() -> {
				new FullDeposit(null);
			}).toThrow(NullPointerException.class);
		});
		
		it("should return a valid instance of this class", () -> {
			expect(new FullDeposit(aco)).toBeNotNull();
		});
	});
	
	describe("When call the toString method", () -> {

		it("should return a valid string", () -> {
			expect(new FullDeposit(aco).toString()).toBeNotNull();
		});
	});
}}
