package algoritmos;

import entidades.Maquina;
import utils.CSVReader;

import java.util.ArrayList;

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
