/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpolate;
import java.io.File;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Date;
/**
 *
 * @author andrew
 */
public class DataSet implements Iterable<Data>{
    File file;
    int domain;
    Date start;
    public DataSet(File f, int dn){
        file = f;
        domain = dn;
    }
    public int getDomain() {
        return domain;
    }

    public void setDomain(int domain){
        this.domain = domain;
    }
    
    public void setDomain(String domain) {
        if( domain.equals("day") ) this.domain = Interpolate.DAY;
        else if( domain.equals("month") ) this.domain = Interpolate.MONTH;
        else if( domain.equals("quarter") ) this.domain = Interpolate.QUARTER;
        else if( domain.equals("year") ) this.domain = Interpolate.YEAR;
        else this.domain = Interpolate.DAY;
    }
    
    public Date getDateStart(){
        if( start == null ){
            Date lowest = new Date();
            try{
                DataList list = new DataList(this, true);
                while( list.hasNext() ){
                    // compare
                    Data d = list.next();
                    if( lowest.compareTo(d.getDate()) > 0 ) lowest = d.getDate();
                }
            }
            catch(Exception e){
                System.err.println(e);
            }
            start = lowest;
        }
        return start;
    }
    
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
    
    public String toString(){
        return this.file.getName();
    }
    
    public Iterator<Data> iterator(){
        try{
            return new DataList(this);
        }
        catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null,
                "Could not open file: " + this.file,
                "File Error",
                javax.swing.JOptionPane.ERROR_MESSAGE);
            System.err.println(e);
        }
        return null;
    }
}