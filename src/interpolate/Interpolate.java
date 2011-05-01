/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpolate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
/**
 *
 * @author andrew
 */
public class Interpolate{
    
    public final static int DAY = 0;
    public final static int MONTH = 1;
    public final static int QUARTER = 2;
    public final static int YEAR = 3;
    
    public static ArrayList<Data> on(DataSet from, DataSet with, int n, float exp){
        for(Data d:with){
            // find neighbors
            ArrayList<DataNeighbor> neighbors = new ArrayList<DataNeighbor>(n);
            for(Data e:from){
                double distance = Interpolate.getDistance(d, e);
                neighbors.add( new DataNeighbor(distance, e) );
            }
            java.util.Collections.sort(neighbors);
            System.out.println(neighbors);
        }
        return new ArrayList<Data>();
    }
    
    public static double getDistance(Data a, Data b){
        if( a.getField("x") == null || a.getField("y") == null || b.getField("x") == null || b.getField("y") == null ){
            System.err.println("Null Data: "+a+", "+b);
            return Double.MAX_VALUE;
        }
        double a_x = Double.parseDouble(a.getField("x"));
        double a_y = Double.parseDouble(a.getField("y"));
        double b_x = Double.parseDouble(b.getField("x"));
        double b_y = Double.parseDouble(b.getField("y"));
        double x = Math.pow(a_x - b_x, 2);
        double y = Math.pow(a_y - b_y, 2);
        double z = Math.sqrt( x + y );
        return z;
    }
}
