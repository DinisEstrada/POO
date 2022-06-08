# Teste 22

Proposta de Resolucao

Avaliação: Teste de Programacao Orientada aos Objectos (A) MiEI e LCC - DI/UMinho 2022

by [Dinis Estrada](https://github.com/DinisEstrada)

## Parte 2

```java
/*
Considere as seguintes definições de classes de uma aplicação que implementa uma loja de
livros digitais. A aplicação da LivrosDigitais possui a informação dos utilizadores que nela
estão registados e para cada utilizador é guardada a informação respeitante à colecção de livros
que adquiriu. A informação dos livros indica as páginas lidas e por ler e consequentemente em
qualquer altura sabe-se sempre qual é o sítio do livro que se está a ler.
Considere os seguintes excertos de código:
*/

public class Livro implements Comparable<Livro>, Serializable {
    public String codISBN; //código ISBN do livro
    private String nomeLivro;
    private String autor;
    private String editora;
    private List<Pagina> pagLidas; // páginas já lidas
    private List<Pagina> pagPorLer; //páginas ainda por ler. [a,b,c,d] [c,d]
//o primeiro elemento é a página a ser lida no momento
....
    /* método que devolve a página com o número indicado */
    public Pagina devolvePag(int numPag) throws PagInexistenteException {
        Pagina res = null;
        int numLidas = this.pagLidas.size(); //número de páginas lidas
        int porLer = this.pagPorLer.size();
        
        if (numPag > numLidas+porLer)
            throw new PagInexistenteException(numLidas);
        if (numPag <= numLidas )
            res = this.pagLidas.get(numPag -1);
        else
            res = this.pagPorLer.get(numPag-numLidas -1);
        
        return res.clone();
    }
}
```
```java
public class Pagina implements Comparable<Pagina>, Serializable {
    private List<String> texto;
    
    public Pagina() {
        this.texto = new ArrayList<>();
    }
    
    ...
    
    /* método que devolve uma formatação do texto */
    public String reproduzPagina() {...}
}
```
```java
public class PaginaComAudio extends Pagina implements Comparable<PaginaComAudio>,Serializable {
    private String narrador;
    private List<Byte> som;

    ...

    public PaginaComAudio(List<String> texto, String narrador, List<Byte> audio) {
        super(texto);
        this.narrador = narrador;
        this.audio = new ArrayList<>(audio);
    }

    /* método que devolve uma formatação do texto e audio */
    public String reproduzPagina() {...}
}
```
```java
public class Utilizador implements Serializable {
    private String numUser;
    private String nomeUser;
    private LocalDate dataAdesao; // data de adesão do utilizador à aplicação
    
    ...
}
```
```java
// Assuma, para as perguntas seguintes, que os métodos usuais (equals, clone, hashcode, ...) estão
// disponíveis a menos que sejam solicitados e responda às questões:
```

### Ex.1

```java
public class Utilizador implements Serializable {
    private String numUser;
    private String nomeUser;
    private LocalDate dataAdesao; // data de adesão do utilizador à aplicação
    //private Set<Livro> books;
    private Map<String, Livro> books;
    
    public Utilizador(String numUser, String nomeUser, Iterator<Livro> livros) {
        this.numUser = numUser;
        this.nomeUser = nomeUser;
        this.dataAdesao = LocalDate.now();

        this.books = new HashMap<String,Livro>();
        while(livros.hasNext()) {
            this.books.put(livros.next().getcodISBN(), livros.next().clone());
        }
    }
}
```
```java
public class LivrosDigitais {
    private Map<String,Utilizador> users;
    private Map<String,Livro> livros;
}
```

### Ex.3
```java
public class Livro implements Comparable<Livro>, Serializable {

    //...

    public void alteraPags(int n) throws BookException {
        
        if(!(this.PagPorLer.size() < n)) throw new BookException();
        Livro l = new Livro(this);
        
        l.pagPorLer.stream()
                      .limit(n)
                      .forEach(p -> {this.PagPorLer.remove(l);
                                     this.PagLidas.add(l); })
        this = l;
    }

}
```
```java

public class BookException extends Exception {
    public String BookException() {
        super("BookException");
    }
};

public void avancaPags(String codISBN, int n) throws BookException {
    if(!(this.books.containsKey(codISBN))) throw new BookException();
    
    Map<String, Livro> livros = new HashMap<String,Livro>();    
    this.books.entrySet().stream().forEach(l -> livros.put(l.getKey(), l.getValue().clone()));
    
    livros.get(codISBN).alteraPags(n);

    this.books = livros;
    
}
```

### Ex.4
```java
public Livro livroMaisLido() {
    return this.livros.values()  //Collection<Livro>                                      
                     .stream() // Stream<Livro> 
                     .max((l1,l2) ->
                            int d1 = l1.getPagLidas().size();
                            int d2 = l2.getPagLidas().size();
                            if(d1 == d2) return l1.compareTo(l2);
                            return d1-d2;
                         )
                     .get();
}

```

### Ex.5

```java
public Map<String,List<Livro>> livrosPorEditora() {
    return this.livros.values()   // Collection<Livro>                                     
                     .stream() // Stream<Livro> 
                     .collect(Collectors.groupingBy(Livro::getEditora, 
                                                       Collectors.mapping(Livro::clone, Collectors.toList())));  
}


```

### Ex.6

```java
public class PaginaMultimedia {
    private List<String> texto;
    private List<Byte> audio;
    private List<Byte> video;
    public PaginaMultimedia(List<String> texto, List<Byte> audio, List<Byte> video) {
        this.texto = texto;
        this.audio = audio;
        this.video = video;
    }
    /**
    * método que devolve uma formatação do texto, audio e vídeo.
    * Está devidamente implementado.
    */
    public String fazPagina() {
        ...
    }
}
```

```java
// Classe Utilizador 
public List<String> reproduzLivros() {
    return this.books.values()   //Collection<Livro>                                     
                     .stream() //Stream<Livro> 
                     .map(Livro::getPagPorLer) //Stream<List<Pagina>>
                     .flatMap(Collection::stream) //Stream<Pagina>
                     .filter(p -> p instanceof PaginaMultimedia)
                     .map(p -> (PaginaMultimedia) p)
                     .map(PaginaMultimedia::fazPagina) // stream<String>
                     .collect(Collectors.toList());
}
```