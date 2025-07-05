package algoritmos;

import entidades.Maquina;
import utils.CSVReader;

import java.util.ArrayList;

/*
* Para la resolución con el algoritmo Greedy, se planteó una estrategia de selección de máquinas ordenadas de mayor a menor,
* según la cantidad de piezas que puede producir cada máquina. Las máquinas ya vienen ordenadas desde el reader (se implementó Comparable en la clase Máquina).
* En cada iteración se toma la primera máquina de la lista y se intenta añadir a la solución tantas veces como sea posible.
* Cuando ya no es posible añadirla, es decir, supera el total de piezas a fabricar, se elimina de la lista, ya que no es más una candidata válida.
* Si se encuentra solución o la lista de máquinas está vacía, se rompe el bucle y se imprime el resultado obtenido, ya sea satisfactorio o sin solución.
* También se lleva un contador de candidatos, que aumenta cada vez que una máquina es considerada para ser agregada a la solución.
* */

public class Greedy {
    private int piezasTotales;
    private ArrayList<Maquina> maquinas;
    private int candidatos;

    public Greedy(){
        CSVReader reader = new CSVReader("datasets/Maquinas.csv");
        this.piezasTotales = reader.readPiezasTotales();
        this.maquinas = reader.readMaquinas();
        this.candidatos = 0;
    }

    public void greedy(){
        ArrayList<String> secuencia = new ArrayList<>();
        int piezasActuales = 0;
        while (piezasActuales < piezasTotales && !maquinas.isEmpty()){
                Maquina maquina = maquinas.get(0);
                candidatos++;
                while (piezasActuales + maquina.getCantPiezas() <= piezasTotales) {
                    secuencia.add(maquina.getIdMaquina());
                    piezasActuales += maquina.getCantPiezas();
                }
                maquinas.remove(0);
        }
        if (piezasActuales == piezasTotales){
            System.out.println("El total de piezas producidas es de: " + piezasTotales);
            System.out.println("La secuencia de máquinas obtenidas es: " + secuencia);
            System.out.println("La cantidad de puestas en funcionamiento de las máquinas es de: " + secuencia.size());
            System.out.println("Cantidad de candidatos considerados: " + candidatos);
        } else {
            System.out.println("No se encontró solución");
        }
    }
}
