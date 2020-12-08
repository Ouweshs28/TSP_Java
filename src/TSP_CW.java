import java.util.ArrayList;

public class TSP_CW {
    public static int maxGenerations = 4000;
    public static void main(String[] args) {
        FileManagement cityFile = new FileManagement();
        ArrayList<City> cities = cityFile.readFile("TSP4-18.txt");
        // Initial GA
        Stopwatch stopwatch= new Stopwatch();
        System.out.println("Algorithm Started");
        GeneticAlgorithm ga = new GeneticAlgorithm(200, 0.01, 0.9, 2, 5);
        // Initialize population
        Population population = ga.initPopulation(cities.size());
        // set cooling rate
        ga.setCoolingRate(0.001);
        // Evaluate population
        ga.evalPopulation(population, cities);
        Route startRoute = new Route(population.getFittest(0), cities);
        System.out.println("Start Distance: " + startRoute.getDistance());
        // Keep track of current generation
        int generation = 1;
        // Start evolution loop
        while (ga.isTerminationConditionMet(generation, maxGenerations) == false) {

        // Apply crossover
            population = ga.crossoverPopulation(population);
        // Apply mutation
            population = ga.mutatePopulation(population);
        // Evaluate population
            ga.evalPopulation(population,  cities);
        // Increment the current generation
            generation++;
            // cool temperature
            ga.coolTemperature();
        }
        System.out.println("Finished after: "+stopwatch.elapsedTime());
        System.out.println("Stopped after " + maxGenerations + " generations.");
        Route route = new Route(population.getFittest(0), cities);
        System.out.println("Best distance: " + route.getDistance());
        System.out.print("Best Route:");
        for(City i:route.getRoute()){
            System.out.print(i.getPostion()+",");
        }
    }
}
