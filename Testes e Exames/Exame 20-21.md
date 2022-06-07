# Exame 20-21

Proposta de Resolucao

Avaliação: Exame de Programacao Orientada aos Objectos (A) MiEI e LCC - DI/UMinho 21/05/2021

by [Dinis Estrada](https://github.com/DinisEstrada)

## Parte 1

### Ex.1

```java
public Equipa getEquipa(String idClube, String idEquipa) throws ClubeNaoExisteException, EquipaNaoExisteException{
    Equipa res = null;
    Clube c = this.clubes.get(idClube);
    if (c!= null){
        Map<String,Equipa > equipas = c.getEquipas();
        if (equipas.containsKey(idEquipa)){
            res = equipas.get(idEquipa).clone() ;
        }
    }
    
    else{
        throw new ClubeNaoExisteException()
    }
    
    if (res == null){
    throw new EquipaNaoExisteException();
    }
    
    return res;
}
```

### Ex.2

```java
public List<Equipa> getEquipas(String idClube, String escalao) throws ClubeNaoExisteException {
    List<Equipa> res = new ArrayList<>();
    
    if (this.clubes.containsKey(idClube)){
        for(Equipa e: this.clubes.get(idClube).getEquipas().values()){
            if(e.getEscalao().equals(escalao)){
                res.add(e.clone());
            }
        }
    }
    
    else{
        throw new ClubeNaoExisteException();
    }

    return res;
}
```

### Ex.3

```java
/*
lal -> [AlunoTE, Aluno, AlunoTE, Aluno]
lemp -> [Funcionario, AlunoTE, Funcionario, AlunoTE, Funcionario]

getEEstatus1(lemp); --> Erro de Compilação 
    -- ao fazer o map está a aplicar um método da classe Aluno a uma instancia do tipo Empregado
getEEstatus2(lemp); --> Erro de Compilação 
    -- recebe List<Aluno> e não List<Empregado>
getEEstatus3(lemp); --> Erro de Execução 
    -- no map não pode instanciar um Funcionario como Aluno
getEEstatus1(lal); --> Erro de Compilação 
    -- recebe List<Empregado> e não List<Aluno>
getEEstatus2(lal); --> [True, True] 
getEEstatus3(lal); --> Erro de Compilação 
    -- recebe List<Empregado> e não List<Aluno>

a) getEEstatus1(lemp);
b) getEEstatus2(lal);
c) getEEstatus3(lemp);
d) nenhuma
/*
```

### Ex.4
```java
public class Curso {
    
    private Map<String, Map<Integer, Aluno>> turmas;

    //...
}
```

```java
public String melhorTurma() {
    Comparator<Map.Entry<String, Map<Integer, Aluno>>> comp = (a, b) -> {
        double va = a.getValue().values().stream().filter(al -> al.getMedia() > 10.0)
                     .mapToDouble(Aluno::getMedia).average().orElse(0.0);
        double vb = b.getValue().values().stream().filter(al -> al.getMedia() > 10.0)
                     .mapToDouble(Aluno::getMedia).average().orElse(0.0);
        int sizea = a.getValue().size();
        int sizeb = b.getValue().size();
        if (va==vb) return sizeb - sizea;
        else return (int) (vb - va);
    };
return turmas.entrySet().stream().sorted(comp)
             .map(e -> e.getKey()).findFirst().orElse("N/A");
}
```

```java
// O método está corretamente implementado, mas as turmas sem alunos são consideradas
// com média 0.0.
```

### Ex.5

```java
public Set<Pessoa> getAtletas() {
    return atletas.clone().stream().collect(Collectors.toSet());
}
public void setAtletas(Set<Pessoa> s) {
    atletas = s.stream().map(Pessoa::clone).collect(Collectors.toSet());
}
```

```java
//Os métodos não estão correctamente implementados, não sendo consistentes no
//tratamento do encapsulamento.
```

## Parte 2

### Ex. 6

```java
public class Utilizador {
    private String email;
    private String nome;
    private Collection<Podcast> subscrito;
}
```

```java
public class Podcast {
    private nomePodcast;
    private List<Episodio> episodios;

    public List<Episodio> getEpisodios() {
        return this.episodios.stream()
                             .map(Episodio::clone)
                             .collect(Collectors.toList());
    }
}
```

```java
public class SpotifyPOO {
    private Map<String,Utilizador> utilizadores;
    private Map<String,Podcast> podcasts;

    public List<Episodio> getEpisodios(String nomePodcast) {
        Podcast p = this.podcasts.get(nomePodcast);
        if (p == null) return new ArrayList<Episodio>();
        return p.getEpisodios();
                            
    }
}
```

### Ex.8

```java
private static class PodCastNotFoundException extends Exception {};
private static class PodCastNotSubscribedException extends Exception {};
public void remove(String nomeP) throws PodCastException {
    if (!(this.podcasts.containsKey(nomeP))) throw new PodCastNotFoundException();
    if(!(this.utilizadores.values()
                        .stream()
                        .map(Utilizador::subscrito)
                        .flatMap(Collection::stream)
                        .anyMatch(n -> n.equals(nomeP)))    
      ) throw new PodCastNotSubscribedException();
    
    this.podcasts.remove(nomeP);
}
```

### Ex.9

```java
public Episodio getEpisodioMaisLongo(String u) {
  Utilizador u = this.utilizadores.get(u)  
  if(u == null) return null;
  return u.getSubscrito()
          .stream()
          .map(Podcast::getEpisodes)
          .flatMap(Collection::stream)
          .max((a,b) -> Double.compare(a.getDuracao(), b.getDuracao()))
          .get()
}
```

### Ex.10

```java
public Map<Integer,List<Episodio>> episodiosPorClassf() {
    return this.podcasts.values()
                        .stream()
                        .map(Podcast::getEpisodes)
                        .flatMap(Collection::stream)
                        .collection(Collectors.groupingBy(Episodio::getClassificacao, 
                                                          Collectors.mapping(Episodio::clone,Collectors.toList())))
}
```

### Ex.11

```java
public interface Playable {
    public void play();
}
```

```java
public class Episodio implements Playable {
    private String nome;
    private double duracao;
    private int classificacao; //dada pelos seus ouvintes (valor de 0..100)
    private List<String> conteudo; //corresponde ao texto que e’ dito
    //quando se reproduz o episodio
    private int numeroVezesTocada; //qts vezes e’ que o podcast foi ouvido
    private LocalDateTime ultimaVez; //regista quando foi reproduzido
    //ultima vez

     public void play() {this.conteudo.stream().forEach(System.media::print);}
}
```

### Ex.12

```java
public class EpisodioVideo extends Episodio {
    List<Byte> video;

    public EpisodioVideo(String nome, double duracao, int classificacao, List<String> conteudo, int numeroVezesTocada,
                         LocalDateTime ultimaVez, List<Byte> video)
    {
        super(nome, duracao, classificacao, conteudo, numeroVezesTocada, ultimaVez);
        this.video = video;
    }

    @Override
    public void play() {
        super.play();
        video.forEach(System.media::print);
    }
```

### Ex.13

```java
public class UtilizadorPremium extends Utilizador {
    private List<Episodio> queue;
}


```java
public class Utilizador {
    private String email;
    private String nome;
    private Collection<Podcast> subscrito;

    public void playEpisodio(String idPodCast, String nomeEpisodio) throws AlreadyPlayingException {
        // falta ver este fds    
    }
}
```

### Ex.14

```java
public void gravaInfoEpisodiosParaTocarMaisTarde(String fich) throws IOException {
    PrintWriter writer = new PrintWriter(fich);
    users.values()
         .stream()
         .filter(u -> u instanceof UtilizadorPremium)
         .map(u -> (UtilizadorPremium) u)
         .forEach(u -> {
             writer.write(u.getNome() + "\n");
             u.waitQueue.forEach(e -> writer.write(e.getNome() + " - " + e.getDuracao() + "\n"));
         });
    writer.flush();
    writer.close();
}
```