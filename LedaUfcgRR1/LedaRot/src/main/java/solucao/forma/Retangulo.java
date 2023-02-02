package solucao.forma;
public class Retangulo {
    private double altura;
    private double base;    

    public Retangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }
 
    public double calculaArea() {
        return this.altura * this.base;
    }

}
