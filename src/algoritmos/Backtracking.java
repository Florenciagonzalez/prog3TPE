package algoritmos;

import entidades.Maquina;
import utils.CSVReader;

import java.util.ArrayList;

/*
* Las máquinas ya vienen ordenadas desde el reader (se implementó Comparable en la clase Máquina).
* Para la resolución con el algoritmo Backtracking, se lleva una solución parcial de secuencia y cantidad de piezas.
* Por cada máquina se verifica si la cantidad de piezas que produce más las acumuladas hasta el momento no supera el total de piezas a fabricar. Si es así,
* si la secuencia final está vacía o la cantidad de máquinas de la secuencia parcial con la nueva máquina agregada es menor a la secuencia ya encontrada,
* se agrega y continúa explorando recursivamente.
*
* También se lleva un contador de estados (cantidad de llamadas recursivas) para evaluar el costo de la solución.
*
* De encontrar una solución, se imprimen los resultados obtenidos, y en el caso de no encontrar una solución,
* se imprime el mensaje "No se encontró solución".
* */

public class Backtracking {
    private int piezasTotales;
    private ArrayList<Maquina> maquinas;
    private ArrayList<String> secuencia;
    private int estados;

    public Backtracking(){
        CSVReader reader = new CSVReader("datasets/Maquinas.csv");
        this.piezasTotales = reader.readPiezasTotales();
        this.maquinas = reader.readMaquinas();
        this.secuencia = new ArrayList<>();
        this.estados = 0;
    }


    public void backtracking(){
        if (backtracking(new ArrayList<>(), 0)){
            System.out.println("El total de piezas producidas es de: " + piezasTotales);
            System.out.println("La secuencia de máquinas obtenidas es: " + secuencia);
            System.out.println("La cantidad de puestas en funcionamiento de las máquinas es de: " + secuencia.size());
            System.out.println("La cantidad de estados generados es: " + estados);
        } else {
            System.out.println("No se encontró solución");
        }
    }

    private boolean backtracking(ArrayList<String> secuenciaParcial, int piezasActuales){
        estados++;
        if (piezasActuales == piezasTotales){
            if (secuenciaParcial.size() < secuencia.size() || secuencia.isEmpty()){
                secuencia.clear();
                secuencia.addAll(secuenciaParcial);
            }
        } else {
            for (Maquina m : maquinas){
                if (piezasActuales + m.getCantPiezas() <= piezasTotales){
                    if (secuenciaParcial.size() + 1 < secuencia.size() || secuencia.isEmpty()) {
                        secuenciaParcial.add(m.getIdMaquina());
                        backtracking(secuenciaParcial, piezasActuales + m.getCantPiezas());
                        secuenciaParcial.remove(secuenciaParcial.size() - 1);
                    }
                }
            }
        }
        return !secuencia.isEmpty();
    }
}
