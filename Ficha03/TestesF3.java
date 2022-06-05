package Ficha03;

import java.util.Scanner;

public class TestesF3 {
    public static void main (String[] args) {
        Scanner inputReader = new Scanner(System.in);
        System.out.println("Introduz o número da pergunta: ");
        int question = inputReader.nextInt();
        switch (question) {
            case 1:
            Circulo Circulo1 = new Circulo();
            System.out.println("Círculo predefinido, valores devem ser x = 0.0, y = 0.0 e raio = 0.0.");
            System.out.println(Circulo1.toString() + "\n");

            double x = 3.1;
            double y = -6.9;
            double radius = 5;
            Circulo Circulo2 = new Circulo(x, y, radius);
            System.out.printf("Círculo com valores iniciais x = %f, y = %f e raio = %f.\n", x, y, radius);
            System.out.println(Circulo2.toString() + "\n");

            Circulo Circulo3 = new Circulo(Circulo2);
            System.out.println("Círculo obtido por cópia do círculo anterior");
            System.out.println(Circulo3.toString() + "\n");

            Circulo3.setX(-4.2);
            Circulo3.setY(1);
            Circulo2.setRaio(0.1);
            Circulo1.alteraCentro(2,3);
            System.out.println("Alteração dos valores x e y do círculo 3 para -4.2 e 1, respetivamente.");
            System.out.println(Circulo3.toString());
            System.out.println("Alteração do raio do círculo 2 para 0.1.");
            System.out.println(Circulo2.toString());
            System.out.println("Alteração do centro do círculo 1 para x = 2.0 e y = 3.0.");
            System.out.println(Circulo1.toString() + "\n");

            System.out.printf("Área do círculo 2 (deve dar cerca de 0.03): %f\n", Circulo2.calculaArea());
            System.out.printf("Perímetro do círculo 3 (deve dar cerca de 31.42): %f\n", Circulo3.calculaPerimetro());
            System.out.print(Circulo1.equals(Circulo2));
            Circulo2 = Circulo1.clone();
            System.out.print(Circulo1.equals(Circulo2));
            break;
        }
    }
}