package Ficha02;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;

public class LocalDateArray {
    private int numElems;
    private LocalDate[] DateArray;


    public LocalDateArray(int size) {
        this.numElems = 0;
        this.DateArray = new LocalDate[size];
    }

    public void insereData(LocalDate data) {
        if(this.numElems < this.DateArray.length) {
            this.DateArray[this.numElems++] = data;
        }
    }

    public LocalDate dataMaisProxima(LocalDate data){
        LocalDate dataproxima = this.DateArray[0];
        int dias = 0; //mesmo dia
        for(LocalDate date : this.DateArray) {
            int temp = (int) dataproxima.until(date,DAYS);
            if(dataproxima.until(date,DAYS) < dias) {
                dias = temp;
                dataproxima = date;
            }        
        }
        return dataproxima;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("");
        for(LocalDate date : this.DateArray) {
            sb.append(date.toString()).append("\n");
        }        
        return sb.toString();
    }
    
}
