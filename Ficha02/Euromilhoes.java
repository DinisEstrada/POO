package Ficha02;
import java.lang.Math;
import java.util.Arrays;

public class Euromilhoes {
    private int[] numeros;
    private int[] estrelas;
    
    // final int example; --> once the value is assigned, it can never be changed
    // static int example = 10; --> With the non-final static variable, any instance can change it, but if the value changes, it affects every instance.
    
    private static final int n_numeros = 5;
    private static final int n_estrelas = 2;
    //  it's both the same for each class and it can't be changed after it's initialized.

    public Euromilhoes() {
        this.numeros = new int[n_numeros];
        this.estrelas = new int[n_estrelas];
        int i;
        
        for(i = 0; i<n_numeros; i++) {
            this.numeros[i] = (int) (Math.random() * (50-1)) + 1;
        }

        for(i = 0; i<n_estrelas; i++) {
            this.estrelas[i] = (int) (Math.random() * (9-1)) + 1;
        }
    }
    
    
    public Euromilhoes(Euromilhoes e) {
        this.numeros = e.getNumeros();
        this.estrelas = e.getEstrelas();
    }
    
    
    public Euromilhoes clone() {
        return new Euromilhoes(this);
    }
    
    
    
    /* pode se fazer clone  ou nao vale a pena ??  VER ISTO*/
    
    public Euromilhoes(int[] numeros, int[] estrelas) {
        this.numeros = numeros.clone();
        this.estrelas = estrelas.clone();
    }

    public int[] getNumeros() {
        return this.numeros.clone();
    }

    public int[] getEstrelas() {
        return this.estrelas.clone();
    }

    public void setNumeros(int[] numeros) {
        this.numeros = numeros.clone();
    }

    public void setEstrelas(int[] estrelas) {
        this.estrelas = estrelas.clone();
    }
    
    public boolean equals(Euromilhoes e) {
        boolean r = false;
        if(e != null) {
            /*
            int i;
            for(i = 0; i<n_numeros && !r; i++) {
                r = this.numeros[i] == e.getNumeros()[i];
            }
            for(i = 0; i<n_estrelas && !r; i++) {
                r = this.estrelas[i] == e.getEstrelas()[i];
            }
            */
            r = Arrays.equals(this.numeros, e.getNumeros()) && Arrays.equals(this.estrelas, e.getEstrelas()) ;
        }
        return r;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("");
        sb.append("[Números: ");
        for(int num : this.numeros) {
            sb.append(num + " ");
        }
        sb.append("Estrelas: ");
        for(int star : this.estrelas) {
            sb.append(star + " ");
        }
        sb.append("]");
        return sb.toString();
    }
}
