package solucao.forma;

import javax.sound.sampled.SourceDataLine;

public class Main {
    Quadrado quadrado = new Quadrado(4);
    Triangulo triangulo = new Triangulo(4, 3);
    Retangulo retangulo = new Retangulo(4, 7);
    Circulo circulo = new Circulo(4);
    system.out.println(quadrado.calculaArea());
    system.out.println(circulo.calculaArea());
    system.out.println(retangulo.calculaArea());
    system.out.println(triangulo.calculaArea());
}
