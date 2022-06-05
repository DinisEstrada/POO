package Ficha06;
/*********************************************************************************/
/** DISCLAIMER: Este código foi criado e alterado durante as aulas práticas      */
/** de POO. Representa uma solução em construção, com base na matéria leccionada */ 
/** até ao momento da sua elaboração, e resulta da discussão e experimentação    */
/** durante as aulas. Como tal, não deverá ser visto como uma solução canónica,  */
/** ou mesmo acabada. É disponibilizado para auxiliar o processo de estudo.      */
/** Os alunos são encorajados a testar adequadamente o código fornecido e a      */
/** procurar soluções alternativas, à medida que forem adquirindo mais           */
/** conhecimentos de POO.                                                        */
/*********************************************************************************/



import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Objects;

public class Corrida extends Atividade implements FazMetros{
    private double distancia;
    private double altimetria;
    private String precurso;
    private int pontosPorMetro;

    public Corrida(){
        super();
        this.distancia = 0;
        this.altimetria = 0;
        this.precurso = "";
    }

    public Corrida(String codigo, String descricao, LocalDate data, int duracao, double distancia, double altimetria, String precurso) {
        super(codigo, descricao, data, duracao);
        this.distancia = distancia;
        this.altimetria = altimetria;
        this.precurso = precurso;
    }

    public Corrida(Corrida outro) {
        super(outro);
        this.distancia = outro.getDistancia();
        this.altimetria = outro.getAltimetria();
        this.precurso = outro.getPrecurso();
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public double getAltimetria() {
        return altimetria;
    }

    public void setAltimetria(double altimetria) {
        this.altimetria = altimetria;
    }

    public String getPrecurso() {
        return precurso;
    }

    public void setPrecurso(String precurso) {
        this.precurso = precurso;
    }

    public double calorias() {
        long idade =  ChronoUnit.YEARS.between(LocalDate.now(),
                getUser().getDataDeNascimento());
        double calorias =distancia*getUser().getPeso()*getDuracao()*idade/50;
        return calorias;
    }


    public Atividade clone() {
        return new Corrida(this);
    }


    public String toString() {
        return "Corrida{" +
                super.toString()+
                "distancia=" + distancia +
                ", altimetria=" + altimetria +
                ", precurso='" + precurso + '\'' +
                '}';
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Corrida corrida = (Corrida) o;
        return Double.compare(corrida.getDistancia(), getDistancia()) == 0
                && Double.compare(corrida.getAltimetria(), getAltimetria()) == 0
                && this.precurso.equals(corrida.getPrecurso());
    }

    @Override
    public void setPontosPorMetro(int pontos) {
        this.pontosPorMetro = pontos;
    }

    @Override
    public int getPontosPorMetro() {
        return this.pontosPorMetro;
    }

    @Override
    public int totalPontos() {
        return this.pontosPorMetro * ((int) this.distancia);
    }

}
