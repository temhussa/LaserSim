/**
 * Class that represents a waveguide.
 *
 * @author Tehmoor Hussain
 * @version 1.5
 */

public class Waveguide{

	 protected double c = 1, sigma = 1;
	 protected double N[] = {0, 0};
	 protected double deltaTime, lastI, nextI, alpha;

	public Waveguide(double deltaTimeIn, double lastIIn){
		deltaTime = deltaTimeIn;
		lastI = lastIIn;
	}

	public void calculateI(Gas co2In, Modulator aomIn){
		co2In.calculatePopulation(lastI);

		alpha = aomIn.returnAlpha();
		N[0] = co2In.returnN3();
		N[1] = co2In.returnN4();

		nextI = (c*sigma*(N[1] - N[0]) - alpha)*lastI*deltaTime + lastI;
		lastI = nextI;
	}

	public double returnI(){
		return nextI;
	}

	public String toString(){
		String textI = String.valueOf(nextI);
		return textI;
	}

}
