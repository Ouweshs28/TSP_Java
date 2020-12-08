import java.util.ArrayList;

public class Route {
    public City[] getRoute() {
        return route;
    }

    private City route[];
    private double distance = 0;

    /**
     * Calcilates the route
     * @param individual
     * @param cities
     */
    public Route(Individual individual, ArrayList <City>cities) {
// Get individual's chromosome
        int chromosome[] = individual.getChromosome();
// Create route
        this.route = new City[cities.size()];
        for (int geneIndex = 0; geneIndex < chromosome.length; geneIndex++) {
            this.route[geneIndex] = cities.get(chromosome[geneIndex]);
        }
    }

    /**
     * Calculates the total distance of the route
     * @return
     */
    public double getDistance() {
        if (this.distance > 0) {
            return this.distance;
        }
// Loop over cities in route and calculate route distance
        double totalDistance = 0;
        for (int cityIndex = 0; cityIndex + 1 < this.route.length;
             cityIndex++) {
            totalDistance += this.route[cityIndex].
                    distanceFrom(this.route[cityIndex + 1]);
        }
        totalDistance += this.route[this.route.length - 1]
                .distanceFrom(this.route[0]);
        this.distance = totalDistance;
        return totalDistance;
    }
}
