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
import java.util.Objects;

public abstract class Atividade {
    private String codigo;
    private String descricao;
    private LocalDate data;
    private int duracao;
    private Utilizador user;

    public Atividade() {
        this.codigo = "";
        this.descricao = "";
        this.data = LocalDate.EPOCH;
        this.duracao = 0;
        this.user = new Utilizador();
    }

    public Atividade(String codigo, String descricao, LocalDate data, int duracao) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.data = data;
        this.duracao = duracao;
    }

    public Atividade(Atividade outro){
        this.codigo = outro.getCodigo();
        this.descricao = outro.getDescricao();
        this.data = outro.getData();
        this.duracao = outro.getDuracao();
    }
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String toString() {
        return "Atividade{" +
                "codigo='" + codigo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", data=" + data +
                ", duracao=" + duracao +
                '}';
    }

    public Utilizador getUser() {
        return user;
    }

    public void setUser(Utilizador user) {
        this.user = user;
    }



    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atividade atividade = (Atividade) o;
        return this.duracao == atividade.getDuracao()
                && this.codigo.equals(atividade.getCodigo())
                && this.descricao.equals(atividade.getDescricao())
                && this.data.equals(atividade.getData());
    }


    public abstract double calorias();
    public  abstract  Atividade clone( );
}
