/**
 * Class that represents a modulator.
 *
 * @author Tehmoor Hussain
 * @version 1.0
 */

public class Modulator{

	protected double alpha[] = {0, 0, 0, 0};
  protected double deltaTime, alphaT, wave;
	protected double a = 1, f = 1;

	public Modulator(double deltaTimeIn, double lossIn[]){
		deltaTime = deltaTimeIn;

		for (int i = 0; i < 4; i++){
			alpha[i] = lossIn[i];
		}
	}

	public void lossFunction(double timeIn){
		alphaT = 0;
		wave = Math.sin(f*timeIn);

		if (wave > 0){
			alpha[3] = Math.pow(Math.sin(f*timeIn), 2);
		}

		if (wave < 0){
			alpha[3] = 0;
		}

		for (int j = 0; j < 4; j++){
			alphaT += alpha[j];
		}
	}

	public double returnAlpha(){
		return alphaT;
	}
}
