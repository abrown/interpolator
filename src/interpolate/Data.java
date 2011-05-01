/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpolate;
import java.util.Calendar;
import java.util.Date;
/**
 *
 * @author andrew
 */
public class Data {
    
    int domain;
    Date start;
    String[] header;
    String[] fields;
    
    public Data(String[] fields, String[] header) {
        this.fields = fields;
    }
    
    public Data(String[] fields, String[] header, int domain, java.util.Date start) {
        this.fields = fields;
        this.header = header;
        this.domain = domain;
        this.start = start;
    }
    
    public String[] getHeader() {
        return header;
    }
    
    public void setHeader(String[] header) {
        this.header = header;
    }
    
    public String getField(String name){
        if( header.length == 0 ) return null;
        for(int i=0; i<this.header.length; i++){
            if( name.equals(this.header[i]) ) return fields[i];
        }
        return null;
    }
    
    public int getTime(){
        if(domain == Interpolate.DAY){
            double time = (this.getDate().getTime() - start.getTime()) / 86400000;
            return (int) java.lang.Math.floor(time);
        }
        else if(domain == Interpolate.DAY){
            int years = this.getDate().getYear() - start.getYear();
            return years * 12 + (this.getDate().getMonth() - start.getMonth());
        }
        //if(domain == Interpolate.QUARTER) time = interval/86400000;
        else if(domain == Interpolate.YEAR){
            return this.getDate().getYear() - start.getYear();
        }
        else{
            return 0;
        }
    }
    
    public Date getDate(){
        Calendar c = Calendar.getInstance();
        c.clear();
        // year
        if( !this.getField("year").equals(null) ) 
            c.set( Calendar.YEAR, Integer.parseInt(this.getField("year")) );
        else 
            c.set( Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR) );
        // month
        if( !this.getField("month").equals(null) )
            c.set( Calendar.MONTH, Integer.parseInt(this.getField("month")) );
        else 
            c.set( Calendar.MONTH, Calendar.getInstance().get(Calendar.MONTH) );
        // day
        if( !this.getField("day").equals(null) )
            c.set( Calendar.DAY_OF_MONTH, Integer.parseInt(this.getField("day")) );
        else 
            c.set( Calendar.DAY_OF_MONTH, Calendar.getInstance().get(Calendar.DAY_OF_MONTH) );
        // return
        return c.getTime();
    }
    
    public String toString(){
        return "[id="+this.getField("id")+", x="+this.getField("x")+", y="+this.getField("y")+"]";
    }
}
