package entidades;

public class Maquina implements Comparable<Maquina> {
    private String idMaquina;
    private int cantPiezas;

    public Maquina(String idMaquina, int cantPiezas){
        this.idMaquina = idMaquina;
        this.cantPiezas = cantPiezas;
    }

    public String getIdMaquina() {
        return idMaquina;
    }

    public int getCantPiezas() {
        return cantPiezas;
    }

    @Override
    public int compareTo(Maquina o) {
        return o.getCantPiezas() - this.cantPiezas;
    }
}
