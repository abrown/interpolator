/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpolate;

/**
 *
 * @author andrew
 */
public class DataNeighbor implements Comparable<DataNeighbor>{
    double distance;
    Data data;
    public DataNeighbor(double distance, Data data) {
        this.distance = distance;
        this.data = data;
    }
    public Data getData() {
        return data;
    }
    public void setData(Data data) {
        this.data = data;
    }
    public double getDistance() {
        return distance;
    }
    public void setDistance(double distance) {
        this.distance = distance;
    }
    public int compareTo(DataNeighbor d){
        Double _t = this.getDistance();        
        Double _d = d.getDistance();
        return _t.compareTo(_d);
    }
}
