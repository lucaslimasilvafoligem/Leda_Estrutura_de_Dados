package solucao.forma;
public class Circulo {
    private double raio;

    public Circulo(double raio) {
        this.raio = raio;
    }

    public double calculaArea() {
        return this.raio * Math.PI;
    }
}
