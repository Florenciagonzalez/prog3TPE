package algoritmos;

import entidades.Maquina;
import utils.CSVReader;

import java.util.ArrayList;

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
