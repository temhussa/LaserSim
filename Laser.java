/**
* Creates an instance of a CO2 laser.
*
* @author Tehmoor Hussain
* @version 1.0
*/

import java.lang.Math;
import java.util.*;
import java.io.*;

public class Laser{

  public static void main (String[] args) throws FileNotFoundException{

    double pressure[] = {30, 30, 30};
    double alpha[] = {0.05, 0.05, 0.05, 0};
    double N[] = {1, 0.2, 0.1, 0.01, 1, 0.01};
    double dutyCycle[] = {2, 5, 1};

    double initialI = 1e-3;
    double currentTime = 0, finalTime = 30, timeStep = 1e-3;

    Waveguide planar = new Waveguide(timeStep, initialI);
    Gas co2 = new Gas(timeStep, N, pressure);
    Modulator aom =  new Modulator(timeStep, alpha);

    PrintWriter writer = new PrintWriter("SimData.txt");

    while(currentTime <= finalTime){
      co2.rfPump(currentTime);
      aom.lossFunction(currentTime);
      planar.calculateI(co2, aom);

      writer.println(currentTime + " " + planar.toString());
      currentTime += timeStep;
    }

    writer.close();
  }
}
