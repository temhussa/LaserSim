/**
 * Class that represents a resonator.
 *
 * @author Tehmoor Hussain
 * @version 1.5
 */

public class Resonator{

  protected double g0 = 0.65, k = 5.93e5, alpha0 = 0.0125, ISat = 0.5;
	protected double Ix, x0, deltaX, IPlus, IMinus;
  protected double newIx, newIPlus, newIMinus, dIdx;

	public Resonator(double deltaXIn, double IPlusIn, double IMinusIn, double x0In){
		deltaX = deltaXIn;
    IPlus = IPlusIn;
    IMinus = IMinusIn;
		x0 = x0In;
	}

	public void calculateIx(double xIn){
    newIPlus = (g0/(1 + (IPlus + IMinus)/ISat) - alpha0)*IPlus*deltaX - IPlus*(2*deltaX/(xIn + x0) - 1);
    newIMinus = -(g0/(1 + (IPlus + IMinus)/ISat) - alpha0)*IMinus*deltaX + IMinus;
  	newIx = IPlus + IMinus - 2*Math.sqrt(IPlus*IMinus)*Math.cos(k*xIn);

    IMinus = newIMinus;
    IPlus = newIPlus;
	}

  public void calculatedIdx(){
    dIdx = (newIx - Ix)/deltaX;
    Ix = newIx;
  }

  public double returndIdx()
  {
    return dIdx;
  }

  public double returnIx()
  {
    return newIx;
  }

  public String toString()
  {
    String textI = String.valueOf(newIx);
    return textI;
  }
}
