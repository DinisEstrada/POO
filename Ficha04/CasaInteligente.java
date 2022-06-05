package Ficha04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CasaInteligente {
    private ArrayList<Lampada> lamps;

    public CasaInteligente() {
        this.lamps = new ArrayList<Lampada>();
    }

    public CasaInteligente(ArrayList<Lampada> lamps) {
        this.lamps = lamps;
    }

    public CasaInteligente(CasaInteligente e) {
        this.lamps = e.getLamps();
    }

    public ArrayList<Lampada> getLamps() {
        return (ArrayList<Lampada>) this.lamps.stream()
                .map(Lampada::clone)
                .collect(Collectors.toList());
    }

    public void setLamps(ArrayList<Lampada> lamps) {
        this.lamps = new ArrayList<Lampada>();
        //this.lamps = (ArrayList<Lampada>) lamps.stream().map(Lampada::clone).collect(Collectors.toList());

        for(Lampada l : lamps)
            this.lamps.add(l.clone());
    }

    public CasaInteligente clone() {
        return new CasaInteligente(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("CasaInteligente{");
        for (Lampada l : this.lamps) {
            sb.append(l.toString()).append("\n");
        }
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(CasaInteligente o) {
        if (this == o) return true;
        else if (o == null || this.getClass() != o.getClass()) return false;
        else return this.lamps.equals(o.getLamps());
    }

    public void addLampada(Lampada l) {
        this.lamps.add(l.clone());
    }

    public void ligaLampadaNormal(int index) {
        this.lamps.get(index).lampON();
    }

    public void ligaLampadaEco(int index) {
        this.lamps.get(index).lampECO();
    }

    public int qtEmEco() {
        return this.lamps.stream().
                filter(l -> (l.getModo() == Lampada.Modo.ECO))
                .collect(Collectors.toList())
                .size();
    }

    public void removeLampada(int index) {
        this.lamps.remove(index);
    }

    public void ligaTodasEco() {
        this.lamps.forEach(l -> l.lampECO());
    }

    public void ligaTodasMax() {
        this.lamps.forEach(l -> l.lampON());
    }

    public double consumoTotal() {
        return this.lamps.stream().mapToDouble(Lampada::totalConsumo).sum();
    }

    public double consumoTotal2() {
        double cT = 0;
        Iterator<Lampada> it = this.lamps.iterator();
        while(it.hasNext()) 
            cT += it.next().totalConsumo();
        return cT;
    }

    public double consumoTotal3() {
        double cT = 0;
        for(Lampada l : this.lamps) 
            cT += l.totalConsumo();
        return cT;
    }

    public Lampada maisGastadora() {
        Comparator<Lampada> comp = (o1, o2) -> {
            if (o1.totalConsumo()  < o2.totalConsumo() ) return -1;
            else if (o1.totalConsumo() > o2.totalConsumo()) return 1;
            return 0;
        };

        return this.lamps.stream().sorted(comp).collect(Collectors.toList()).get(this.lamps.size()-1);
    }

    public void reset() {
        this.lamps.forEach(l -> l.setConsumoTotal(0));
    }

    public Set<Lampada> podiumEconomia() {
        
        Comparator<Lampada> comp = (o1, o2) -> {
             return (int) o1.getConsumoTotal() - (int) o2.getConsumoTotal();
        };
        /*
        Set<Lampada> lista = this.lamps.stream()
                            .sorted(comp)
                            .limit(3)
                            .collect(Collectors.toSet());
        
        
        TreeSet<Lampada> podium = new TreeSet<Lampada>(comp);

        for(Lampada L : lista) 
            podium.add(L);
        
        return podium;
        */


        return this.lamps.stream()
                .sorted(comp)
                .limit(3)
                .collect(Collectors.toSet());
    }
}             
