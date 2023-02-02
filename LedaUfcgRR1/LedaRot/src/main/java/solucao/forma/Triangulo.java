package solucao.forma;
public class Triangulo {
    private double altura;
    private double base;

    public Triangulo(double base, double altura) {
        this.altura = altura;
        this.base = base;
    }

    public double calculaArea() {
        return (this.base * this.altura)/2;
    }

}
