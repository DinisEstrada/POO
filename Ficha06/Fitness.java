package Ficha06;

import java.util.*;
import java.util.stream.Collectors;

public class Fitness {
    private Map<String,Utilizador> users;
    private Map<String,Comparator<Utilizador>> comparadores;

    public Fitness() {
        this.users = new TreeMap<>();
        this.comparadores = new HashMap<>();
    }
    
    
    public boolean existeUtilizador(String email) {
        return this.users.containsKey(email);
    }

    public int quantos() {
        return this.users.size();
    }

    public int quantos(String actividade, String email) {
        return this.users.get(email).getAtividadesRealizadas().stream()
                .filter(a -> a.getCodigo().equals(actividade))
                .collect(Collectors.toList())
                .size();
    }

    public Utilizador getUtilizador(String email) {
        return this.users.get(email);
    }

    public void adicionaERRADO(String email, Atividade act) {
        this.users.get(email).getAtividadesRealizadas().add(act); 
        // tou a adicionar uma atividade a um clone e ao fazer isto não estou a alterar o estado
    }

    public void adiciona(String email, Atividade act) {
        this.users.get(email).addAtividade(act);
        // agora estou a adicionar corretamente porque estou a aceder ao pointer original do user
    }

    public List<Atividade> getAllAtividades() {
        return  this.users.values().stream()
                .map(Utilizador::getAtividadesRealizadas)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());              
    }

    public List<Atividade> getAllAtividades2() {
        List<Atividade> atividades = new ArrayList<>();
        for (Utilizador u: this.users.values()){
            for (Atividade a : u.getAtividadesRealizadas()) 
                atividades.add(a.clone());
        }
        return atividades;            
    }    

    public void adiciona(String email, Set<Atividade> activs) {
        this.users.get(email).addAtividade(activs);
    }

    public int tempoTotalUtilizador(String email) {
        return this.users.get(email).getAtividadesRealizadas().stream()
                .mapToInt(Atividade::getDuracao)
                .sum();
    }

    public Atividade atividadeMaisExigente() {
        List<Atividade> atividades = this.getAllAtividades();
        return atividades.stream()
                .max((a,b) -> (int) a.calorias() - (int) b.calorias())
                .get();
    }

    public Utilizador utilizadorMaisAtivo() {
        return this.users.values().stream()
                .max((a,b) ->  (int) a.getAtividadesRealizadas().stream().mapToDouble(Atividade::calorias).sum()
                            - (int) b.getAtividadesRealizadas().stream().mapToDouble(Atividade::calorias).sum())
                .get();
    }

    public void actualizaDesportoFav() {
        for(Utilizador u : this.users.values()) 
            u.actualizaDesportoFav();
    }

    /*
    public Set<Utilizador> ordenarUtilizadores() {
        Comparator<Utilizador> comp -> (u1, u2) {
            if 
        }
    }
    ***/
    public Set<Utilizador> ordenarUtilizador(Comparator<Utilizador> c) {
        Set<Utilizador> utilizadores = new TreeSet<>(c);
        for(Utilizador u : this.users.values())
            utilizadores.add(u.clone());
        return utilizadores;
    }

    public void addComparador (String desc, Comparator<Utilizador> c) {
        this.comparadores.put(desc, c);
    }

    public Iterator<Utilizador> ordenarUtilizador (String criterio) {
        Comparator<Utilizador> c = comparadores.get(criterio);
        if (c == null) return null;
        return this.users.values().stream()
                .sorted(c)
                .iterator();
    }

    public Map<String, List<Utilizador>> podiumPorActiv() {
 
        List<Atividade> atividades = this.getAllAtividades();
        Map<String, List<Utilizador>> r = atividades.stream()
        .collect(Collectors.groupingBy(Atividade::getDescricao, Collectors.mapping(Atividade::getUser,Collectors.toList())));

        
            r.entrySet()
            .forEach(e -> e.getValue().stream()
                .sorted((a,b) ->  (int) b.getAtividadesRealizadas().stream()
                                            .filter(x -> x.getDescricao().equals(e.getKey()))
                                            .mapToDouble(Atividade::calorias)
                                            .sum()
                             -     (int) a.getAtividadesRealizadas().stream()
                                            .filter(x -> x.getDescricao().equals(e.getKey()))
                                            .mapToDouble(Atividade::calorias)
                                            .sum())
                .limit(3)
                .collect(Collectors.toList()));
        
                return r;

    }

    public List<FazMetros> daoPontos() {
        return this.users.values().stream()
                .filter(v -> v instanceof FazMetros)
                .map(v -> (FazMetros) v.clone())
                .collect(Collectors.toList());
    }

}
