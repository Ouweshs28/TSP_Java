import java.util.ArrayList;

public class TSP_CW {
    public static void main(String[] args) {
        FileManagement cityFile=new FileManagement();
        ArrayList <City> cities=cityFile.readFile("test3atsp.txt");

        for(City c:cities){
            TourManager.addCity(c);
        }
        Population pop = new Population(50, true);
        System.out.println("Initial distance: " + pop.getFittest().getDistance());

        // Evolve population for 100 generations
        pop = GA.evolvePopulation(pop);
        for (int i = 0; i < 100; i++) {
            pop = GA.evolvePopulation(pop);
        }

        // Print final results
        System.out.println("Finished");
        System.out.println("Final distance: " + pop.getFittest().getDistance());
        System.out.println("Solution:");
        System.out.println(pop.getFittest());
    }

}
