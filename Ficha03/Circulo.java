package Ficha03;
import java.lang.Math;

public class Circulo {
    private double x;
    private double y;
    private double raio;

    public Circulo() {
        this.x = 0;
        this.y = 0;
        this.raio = 0;
    }

    public Circulo(double x, double y, double raio) {
        this.x = x;
        this.y = y;
        this.raio = raio;
    }

    public Circulo(Circulo c) {
        this.x = c.getX();
        this.y = c.getY();
        this.raio = c.getRaio();
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getRaio() {
        return this.raio;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setRaio(double raio) {
        this.raio = raio;
    }

    public void alteraCentro(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double calculaArea() {
        return Math.pow(this.raio,2) * Math.PI;
    }

    public double calculaPerimetro() {
        return 2 * this.raio * Math.PI;
    }


    // .clone() , .toString() ,
    
    public Circulo clone() {
        return new Circulo(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Circulo{");
        sb.append("X: ").append(this.x);
        sb.append(", Y: ").append(this.y);
        sb.append(", Raio: ").append(this.raio);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Circulo that = (Circulo) o;
        return Double.compare(that.x, this.x) == 0 &&
                Double.compare(that.y, this.y) == 0 &&
                Double.compare(that.raio, this.raio) == 0;
    }
}
