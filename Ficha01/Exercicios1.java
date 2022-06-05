package Ficha01;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

public class Exercicios1 {
    public static void main (String[] agrgs) {
        String ex1 = E1();
        System.out.print(ex1 + "\n");
    }


    public static String E1() {
        int day, mes, ano, totaldays;
        
        Scanner scanner = new Scanner(System.in);
        LocalDate date = null;
        
        boolean isdatevalid = false;
        do {
            System.out.println("Insira o dia: ");
            day = scanner.nextInt();

            System.out.println("Insira o mes: ");
            mes = scanner.nextInt();

            System.out.println("Insira o ano: ");
            ano = scanner.nextInt();

            try {date = LocalDate.of(ano, mes, day); isdatevalid = true;}
            catch(DateTimeException e) {System.out.print(e + "\n");}

        } while(!isdatevalid);

        System.out.println(date.toString());

        totaldays = (ano - 1900) * 365;
        totaldays += (ano-1900) / 4;
        if(ano%4 == 0 && (mes == 1 || mes == 2)) ano--;

        for(int i=mes; mes > 0; mes--) {
            if(mes==2) totaldays += 28;
            else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) totaldays += 30;
            else totaldays += 31;
        }
        
        switch (totaldays%7) {
            case 0: 
                return "Domingo";
            case 1: 
                return "Segunda";
            case 2: 
                return "Terça";
            case 3: 
                return "Quarta";
            case 4: 
                return "Quinta";
            case 5: 
                return "Sexta";
            case 6: 
                return "Sábado";
            default:
                return "error";
        }
            
    }
}