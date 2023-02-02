package solucao.forma;
public class Quadrado {
    private double base;

    public Quadrado(double base) {
        this.base = base;
    }

    public double calculaArea() {
        return this.base * this.base;
    }
}
