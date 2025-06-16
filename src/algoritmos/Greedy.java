package algoritmos;

import entidades.Maquina;
import utils.CSVReader;

import java.util.ArrayList;

/*
* Para la resolución con el algoritmo Greedy, se planteó una estrategia de selección de máquinas ordenadas de mayor a menor,
* según la cantidad de piezas que puede producir cada máquina. Las máquinas ya vienen ordenadas desde el reader (se implementó Comparable en la clase Máquina).
* En cada iteración se recorre la lista de máquinas y se selecciona la primera que su producción, sumada a las piezas acumuladas, no supere el total de piezas a fabricar.
* En el caso que no se encuentre ninguna máquina que cumpla, se retorna null y se corta la busqueda indicando que no se encontró solución.
* Si las piezas actuales son iguales a las piezas totales, se encontró una solución y se imprimen los resultados.
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
        while (piezasActuales < piezasTotales){
            Maquina maquina = getMaquina(piezasActuales);
            if (maquina == null){
                break;
            }
            secuencia.add(maquina.getIdMaquina());
            piezasActuales += maquina.getCantPiezas();

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

    private Maquina getMaquina(int piezasActuales){
        for (Maquina m : maquinas) {
            candidatos++;
            if (piezasActuales + m.getCantPiezas() <= piezasTotales) {
                return m;
            }
        }
       return null;
    }
}
