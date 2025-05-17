import algoritmos.Backtracking;
import algoritmos.Greedy;
import utils.CSVReader;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Backtracking backtracking = new Backtracking();
        Greedy greedy = new Greedy();

        System.out.println("Backtracking");
        backtracking.backtracking();
        System.out.println();
        System.out.println("Greedy");
        greedy.greedy();
    }
}