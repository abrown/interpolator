/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpolate;
import java.util.Iterator;
import au.com.bytecode.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author andrew
 */
public class DataList implements Iterator<Data> {
    DataSet data;
    CSVReader reader;
    String[] header;
    Data next;
    boolean minimal = false;
    public DataList(DataSet d) throws Exception{
        data = d;
        reader = new CSVReader(new FileReader(d.file), '\t');
        header = reader.readNext();       
        next = new Data(reader.readNext(), header, data.getDomain(), data.getDateStart());
    }
   public DataList(DataSet d, boolean minimal) throws Exception{
        this.data = d;
        this.reader = new CSVReader(new FileReader(d.file));
        this.header = reader.readNext();           
        if( minimal ) this.next = new Data(reader.readNext(), this.header);
        this.minimal = minimal;
    }
    public boolean hasNext(){
        if( !next.equals(null) ) return true;
        else return false;
    }
    public Data next(){
        Data out = next;
        try{
            if( minimal ) next = new Data(reader.readNext(), this.header);
            else next = new Data(reader.readNext(), this.header, data.getDomain(), data.getDateStart());
        }
        catch(IOException e){
            next = null;
        }
        return out;
    }
    public void remove(){
        
    }
}
