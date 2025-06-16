package utils;

import entidades.Maquina;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVReader {
    private String path;
    private ArrayList<String[]> lines;

    public CSVReader(String path){
        this.path = path;
        this.lines = this.readContent(path);
    }

    public int readPiezasTotales(){
        int piezasTotales = Integer.parseInt(lines.get(0)[0].trim());
        return piezasTotales;
    }

    public ArrayList<Maquina> readMaquinas(){
        ArrayList<Maquina> maquinas = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++){
            String idMaquina = lines.get(i)[0].trim();
            int cantPiezas = Integer.parseInt(lines.get(i)[1].trim());
            Maquina maquina = new Maquina(idMaquina, cantPiezas);
            maquinas.add(maquina);
        }
        Collections.sort(maquinas); //Ordeno la lista de máquinas
        return maquinas;
    }

    private ArrayList<String[]> readContent(String path) {
        ArrayList<String[]> lines = new ArrayList<String[]>();

        File file = new File(path);
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                lines.add(line.split(","));
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (bufferedReader != null)
                try {
                    bufferedReader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
        }

        return lines;
    }
}
