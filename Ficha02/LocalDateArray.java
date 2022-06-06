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


    public LocalDate dataMaisProxima(LocalDate data) {
        long dataMaisProx_dias = Math.abs(DAYS.between(data, this.DateArray[0]));
        LocalDate dataMaisProx = this.DateArray[0];
        for(int i = 1; i < this.numElems; i++) {
            LocalDate dataTemp = this.DateArray[i];
            long daysTemp = Math.abs(DAYS.between(data, dataTemp));
            if(daysTemp < dataMaisProx_dias) {
                dataMaisProx = dataTemp;
                dataMaisProx_dias = daysTemp;
            }
        }
        return dataMaisProx;
    }


    public String toString() {
        StringBuffer sb = new StringBuffer("");
        for(LocalDate date : this.DateArray) {
            sb.append(date.toString()).append("\n");
        }        
        return sb.toString();
    }
    
}
