package Ficha04;

import java.time.LocalDate;
import java.util.ArrayList;
//import java.util.Iterator;

public class EncEficiente {
    private String client_name;
    private int nif;
    private String adress;
    private int order_id;
    private LocalDate order_date;
    private ArrayList<LinhaEncomenda> orders;

    public EncEficiente() {
        this.client_name = null;
        this.nif = -1;
        this.adress = null;
        this.order_id = -1;
        this.order_date = LocalDate.now();
        this.orders = new ArrayList<LinhaEncomenda>();

    }

        public EncEficiente(String client_name, int nif, String adress, int order_id, LocalDate order_date, ArrayList<LinhaEncomenda> orders) {
        this.client_name = client_name;
        this.nif = nif;
        this.adress = adress;
        this.order_id = order_id;
        this.order_date = order_date;
        this.setorders(orders);
    }

    public EncEficiente(EncEficiente encEficiente) {
        this.client_name = encEficiente.getclient_name();
        this.nif = encEficiente.getNif();
        this.adress = encEficiente.getadress();
        this.order_id = encEficiente.getorder_id();
        this.order_date = encEficiente.getorder_date();
        this.setorders(encEficiente.getorders());
    }

    public String getclient_name() {
        return this.client_name;
    }

    public void setclient_name(String client_name) {
        this.client_name = client_name;
    }

    public int getNif() {
        return this.nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public String getadress() {
        return this.adress;
    }

    public void setadress(String adress) {
        this.adress = adress;
    }

    public int getorder_id() {
        return this.order_id;
    }

    public void setorder_id(int order_id) {
        this.order_id = order_id;
    }

    public LocalDate getorder_date() {
        return this.order_date;
    }

    public void setorder_date(LocalDate order_date) {
        this.order_date = order_date;
    }

    public ArrayList<LinhaEncomenda> getorders() {
        return this.orders;
    }

    public void setorders(ArrayList<LinhaEncomenda> orders) {
        this.orders = new ArrayList<>();
        for(LinhaEncomenda l : orders) {
            this.orders.add(l.clone());
        }
    }

    public double calculaValorTotal() {
        double valorTotal = 0;

        for(LinhaEncomenda l : this.orders) {
            valorTotal += l.calculaValorLinhaEnc();
        }

        return valorTotal;
    }

    public double calculaValorDesconto() {
        double valorTotal = 0;

        for(LinhaEncomenda l : this.orders) {
            valorTotal += l.calculaValorDesconto();
        }

        return valorTotal;
    }

    public int numeroTotalProdutos() {
        /*
        int valorTotal = 0;

        for(LinhaEncomenda l : this.orders) {
            valorTotal += l.getQuantidade();
        }

        return valorTotal;
        */
        return (int) this.orders.stream().mapToDouble(LinhaEncomenda::getQuantidade).sum();
    }

    public boolean existeProdutoEncomenda(String refProduto) {
        
        //return this.orders.stream().anyMatch(a -> a.getReferencia().equals(refProduto)); <------ È o q faz mais sentido fazer;
        
        /* 
        Iterator<LinhaEncomenda> it = this.orders.iterator();
        LinhaEncomenda a;
        boolean existe = false;
        while(it.hasNext() && !existe) {
            a = it.next();
            existe = a.getReferencia().equals(refProduto);
        }
        return existe;
        */

        for(LinhaEncomenda l : this.orders) {
            if(refProduto.equals(l.getReferencia()))
                return true;
        }
        return false;
    }

    public void adicionaLinha(LinhaEncomenda linha) {
        this.orders.add(linha.clone());
    }

    public void removeProduto(String codProd) {
        //this.orders.stream().filter(a -> !a.getReferencia().equals(codProd));
        this.orders.removeIf(a -> a.getReferencia().equals(codProd));
    }

}
