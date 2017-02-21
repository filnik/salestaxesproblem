package filnik.salestaxesproblem.model;

/**
 * Created by fil on 21/02/17.
 */

public class Util {
    private static final double ROUND = 0.05F;

    public static double roundTo005(double x){
        x = Math.ceil(x / ROUND) * ROUND;
        return x;
    }
}
