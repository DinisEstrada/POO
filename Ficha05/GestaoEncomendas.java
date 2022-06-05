package Ficha05;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class GestaoEncomendas {

    private TreeMap<Integer, Encomenda> mapEncomendas;

    public GestaoEncomendas() {
        this.mapEncomendas = new TreeMap<>();
    }

    public GestaoEncomendas(TreeMap<Integer, Encomenda> mapEncomendas) {
        this.setMapEncomendas(mapEncomendas);
    }
    
    public void setMapEncomendas(TreeMap<Integer, Encomenda> treeEncomendas) {
        this.mapEncomendas = treeEncomendas.entrySet()
                            .stream()
                            .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().clone(), (a,b) -> b, TreeMap::new));
    }

    public Map<Integer, Encomenda> getMapEncomendas() {
        return this.mapEncomendas.entrySet()
                .stream()
                .collect(Collectors.toMap(e-> e.getKey(), e->e.getValue().clone()));
    }

    public Set<Integer> todosCodigosEnc() {
        return this.mapEncomendas.keySet();
    }

    public void addEncomenda(Encomenda enc) {
        this.mapEncomendas.put(enc.getNif(), enc);
    }

    public Encomenda getEncomenda(Integer codEnc) {
        return this.mapEncomendas.get(codEnc);
    }

    public void removeEncomenda(Integer codEnc) {
        this.mapEncomendas.remove(codEnc);
    }

    public Integer encomendaComMaisProdutos() {
        return this.mapEncomendas.entrySet()
                .stream()
                .max((e1,e2) -> e1.getValue().getLinhas().size() - e2.getValue().getLinhas().size())
                .get()
                .getKey();
    }

    public Set<Integer> encomendasComProduto(String codProd) {
        return this.mapEncomendas.entrySet()
                .stream()
                .filter(e -> e.getValue().getLinhas().stream().anyMatch(prod -> prod.getReferencia().equals(codProd)))
                .map(e -> e.getKey())
                .collect(Collectors.toSet());
    }

    public Set<Integer> encomendasAposData(LocalDate d) {
        return this.mapEncomendas.entrySet()
                .stream()
                .filter(e -> e.getValue().getData().isAfter(d))
                .map(e -> e.getKey())
                .collect(Collectors.toSet());
    }

    public Set<Encomenda> encomendasValorDecrescente() {
        return this.mapEncomendas.values()
                .stream()
                .sorted((e1,e2) -> e2.getNEnc() - e1.getNEnc())
                .collect(Collectors.toSet()); 
    }


        public Map<String, List<Integer>> encomendasDeProduto1() {
            return  this.mapEncomendas.values()
                    .stream()
                    .collect(Collectors.toMap(Encomenda::getNif, e -> e.getLinhas().stream().map(LinhaEncomenda::getReferencia).collect(Collectors.toList())))
                    .entrySet() // Set<Entry<Integer, List<String>>>
                    .stream()
                    .flatMap(e -> e.getValue().stream().map(s -> new AbstractMap.SimpleEntry<>(e.getKey(), s))) 
                    .collect(Collectors.groupingBy(AbstractMap.SimpleEntry::getValue, Collectors.mapping(AbstractMap.SimpleEntry::getKey, Collectors.toList())));
        }
}

        // 

