/**
* A class to represent a four level system
*
* @author Tehmoor Hussain
* @version 1.5
*/

public class Gas{

	protected double hv = 1, sigma = 1, rate = 1;
	protected double pump = 1, a = 1, f = 1;
	protected double t[][] = {{1, 1, 1, 1},
                          {1, 1, 1, 1},
												  {1, 1, 1, 1},
												  {1, 1, 1, 1}};
	protected double p[] = {0, 0, 0};
	protected double nextN[] = {0, 0, 0, 0, 0, 0};
	protected double lastN[] = {0, 0, 0, 0, 0, 0};
	protected double deltaTime, totalN, wave;

	public Gas(double deltaTimeIn, double lastNIn[], double pressureIn[]){
		deltaTime = deltaTimeIn;

		for (int i = 0; i < 6; i++){
			lastN[i] = lastNIn[i];
		}

		for (int j = 0; j < 3; j++){
			p[j] = pressureIn[j];
		}
	}

	public void calculateParameters(){
		t[0][0] = 1/((1.4e5)*(0.46*p[0] + p[1] + 0.46*p[2]));
		t[1][1] = 1/(100*(8*p[0] + 6*p[1] + 27*p[2]));
		t[2][2] = 1/(110*p[0] + 85*p[1] + 365*p[2]);
		t[3][3] = 1/(40*p[0] + 4000*p[1] + 200*p[2]);
	}

	public void calculatePopulation(double IIn){

		nextN[0] = (lastN[1]/t[1])*deltaTime +
		lastN[0];

		nextN[1] = (lastN[2]/t[2] - lastN[1]/t[1])*deltaTime +
		lastN[1];

		nextN[2] = ((sigma*IIn/hv)*(lastN[3] - lastN[2]) + lastN[3]/t[3] -
		lastN[2]/t[2])*deltaTime + lastN[2];

		nextN[3] = (rate*(lastN[5]*lastN[0] - lastN[3]*lastN[4]) -
		(sigma*IIn/hv)*(lastN[3] - lastN[2]) - lastN[3]/t[3])*deltaTime + lastN[3];

		nextN[4] = (-pump*(lastN[4] - lastN[5]))*deltaTime + lastN[4];

		nextN[5] = (pump*(lastN[4] - lastN[5]) - rate*(lastN[5]*lastN[0] -
		lastN[3]*lastN[4]))*deltaTime + lastN[5];

		for (int k = 0; k < 6; k++){
			lastN[k] = nextN[k];
		}
	}

	public void rfPump(double timeIn){
		wave = Math.sin(f*timeIn);

		if (wave > 0){
			pump = a;
		}

		if (wave < 0){
			pump = 0;
		}
	}

	public double returnN3(){
		return nextN[2];
	}

	public double returnN4(){
		return nextN[3];
	}

	public String toString(){
		double deltaN = nextN[3] - nextN[2];

		String textN = String.valueOf(deltaN);
		return textN;
	}
}
