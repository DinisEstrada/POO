package Ficha04;

import java.util.Set;

public class TesteCasaInteligente {
    public static void main (String[] args) {
        Lampada l1 = new Lampada(Lampada.Modo.ON, 20, 20, 20, 20);
        //System.out.print(l1.toString() + "\n");
        Lampada l2 = new Lampada(Lampada.Modo.ON, 50, 50, 50, 50);
        //System.out.print(l2.toString() + "\n");
        Lampada l3 = new Lampada(Lampada.Modo.ON, 40, 40, 40, 40);
        //System.out.print(l3.toString() + "\n");
        Lampada l4 = new Lampada(Lampada.Modo.ON, 60, 60, 60, 60);
        //System.out.print(l4.toString() + "\n");
        //System.out.print("\n\n");
        
        CasaInteligente casa = new CasaInteligente();
        casa.addLampada(l1);
        casa.addLampada(l2);
        casa.addLampada(l3);
        casa.addLampada(l4);
        
        //Lampada result = casa.maisGastadora();
        //System.out.print(result.toString());

        Set<Lampada> top = casa.podiumEconomia();
        System.out.print(top.toString());

    }
}
