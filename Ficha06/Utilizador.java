package Ficha06;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Utilizador {

    private String email;
    private String password;
    private String nome;
    private String genero;
    private Double altura; // em Metros
    private Double peso; // em Kg
    private LocalDate data_de_nascimento;
    private String desporto_favorito;
    private List<Atividade> atividades_realizadas;

    public Utilizador() {
        this.email = "";
        this.password = "";
        this.nome = "";
        this.genero = "";
        this.altura = 0.0;
        this.peso = 0.0;
        this.data_de_nascimento = LocalDate.of(0, 0, 0);
        this.desporto_favorito = null;
        this.atividades_realizadas = new ArrayList<Atividade>();
    }

    public Utilizador(String email, String password, String nome, String genero, Double altura, Double peso, LocalDate data, String des) {
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.genero = genero;
        this.altura = altura;
        this.peso = peso;
        this.data_de_nascimento = data;
        this.desporto_favorito = des;
        this.atividades_realizadas = new ArrayList<Atividade>();
    }

    public Utilizador(Utilizador u) {
        this.email = u.getEmail();
        this.password = u.getPassword();
        this.nome = u.getNome();
        this.genero = u.getGenero();
        this.altura = u.getAltura();
        this.peso = u.getPeso();
        this.data_de_nascimento = u.getDataDeNascimento();
        this.desporto_favorito = u.getDesportoFav();
        this.atividades_realizadas = u.getAtividadesRealizadas();
    }

    public Utilizador clone() {
        return new Utilizador(this);
    }

    public List<Atividade> getAtividadesRealizadas() {
        return this.atividades_realizadas.stream()
                .map(Atividade::clone)
                .collect(Collectors.toList());
    }

    public void setAtividadesRealizadas(List<Atividade> atividades) {
        this.atividades_realizadas = atividades.stream()
                                        .map(Atividade::clone)
                                        .collect(Collectors.toList());
    }

    public String getDesportoFav() {
        return this.desporto_favorito;
    }

    public void setDesportoFav(String desp) {
        this.desporto_favorito = desp;
    }

    public LocalDate getDataDeNascimento() {
        return this.data_de_nascimento;
    }

    public void setDataDeNascimento(LocalDate data) {
        this.data_de_nascimento = data;
    }

    public Double getPeso() {
        return this.peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return this.altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Utilizador={ ");
        sb.append("Email: ").append(this.email).append(", ");
        sb.append("Password: ").append(this.password).append(", ");
        sb.append("Nome: ").append(this.nome).append(", ");
        sb.append("Genero: ").append(this.genero).append(", ");
        sb.append("Altura: ").append(this.altura.toString()).append(", ");
        sb.append("Peso: ").append(this.peso.toString()).append(", ");
        sb.append("Data de Nascimento: ").append(this.data_de_nascimento.toString()).append(", ");
        sb.append("Desporto Fav: ").append(this.desporto_favorito).append(", ");
        sb.append("Lista de Atividades={ ");
        for(Atividade a : this.atividades_realizadas) {
            sb.append(a.toString()).append("  ");
        }
        sb.append("}}");
        return sb.toString();
    }

    public void escreveEmFicheiroTexto(String nomeFicheiro) throws IOException {
        PrintWriter p = new PrintWriter(nomeFicheiro);
        p.println(this.atividades_realizadas.toString());
        p.flush();
        p.close();
    }


    public void addAtividade(Atividade atividade) {
        this.atividades_realizadas.add(atividade);
    }

    public void addAtividade(Set<Atividade> atividades) {
        for(Atividade a : atividades)
            this.atividades_realizadas.add(a.clone());
    }

    public void actualizaDesportoFav() {
        String r = this.atividades_realizadas.stream()
                    .collect(Collectors.groupingBy(Atividade::getDescricao, Collectors.counting()))
                    .entrySet()
                    .stream()
                    .max((a,b) ->  a.getValue().intValue() - b.getValue().intValue())
                    .get()
                    .getKey();

        this.setDesportoFav(r);
                
    }
    
    public boolean equals(Object o) {
        if (this==o) return true; 
        if ((o == null) || (this.getClass() != o.getClass())) return false; 
        
        Utilizador u = (Utilizador) o; 
        return u.getEmail().equals(this.email) && 
               u.getPassword().equals(this.password) &&
               u.getNome().equals(this.nome) &&               
               u.getGenero().equals(this.genero) && 
               u.getAltura() == this.altura &&
               u.getPeso() == this.peso &&
               u.getDataDeNascimento().equals(this.data_de_nascimento) &&
               u.getDesportoFav().equals(this.desporto_favorito) &&
               u.getAtividadesRealizadas().equals(this.atividades_realizadas);

    }
    

}
