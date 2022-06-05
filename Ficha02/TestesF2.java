package Ficha02;
import java.time.LocalDate;
import java.util.Scanner;

public class TestesF2 {
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Indique o número da questão: ");
        int opcao = input.nextInt();

        switch (opcao) {
            case 3:
                LocalDateArray a = new LocalDateArray(3);
                a.insereData(LocalDate.of(2022, 6, 22));
                System.out.print("2022-6-22");
                a.insereData(LocalDate.of(2022, 6, 12));
                System.out.print("2022-6-12");
                a.insereData(LocalDate.of(2022, 6, 15));
                System.out.print("2022-6-15");
                LocalDate r = a.dataMaisProxima(LocalDate.of(2022, 6, 1));
                System.out.print(r.toString() + "\n");
                System.out.print(a.toString());
                break;
            default:
                break;

            case 7:
                Euromilhoes key = new Euromilhoes();
                System.out.print(key.toString() + "\n");
                int[] estrelas = new int[2];
                int[] nums = new int[5];
                System.out.print("Introduza 5 números (1-50): \n");
                int i;
                for(i=0; i<5; i++) {
                    nums[i] = input.nextInt();
                }
                System.out.print("Introduza 2 estrelas (1-9): \n");
                
                for(i=0; i<2; i++) {
                    estrelas[i] = input.nextInt();
                }
                Euromilhoes guess = new Euromilhoes(nums,estrelas);
                      
                if (key.equals(guess)) {
                    for(i=0; i<50; i++) {
                        System.out.print(key.toString() + "  ");
                    }
                }

                System.out.print(key.toString() + "\n" + guess.toString());
            break; 
        }
    }
}