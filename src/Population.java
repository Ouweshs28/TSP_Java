import java.util.Arrays;
import java.util.Comparator;

public class Population {
    private Individual population[];
    private double populationFitness = -1;
    public Population(int populationSize) {
        this.population = new Individual[populationSize];
    }

    public Population(int populationSize, int chromosomeLength) {
        this.population = new Individual[populationSize];
        for (int individualCount = 0; individualCount <
                populationSize; individualCount++) {
            Individual individual = new
                    Individual(chromosomeLength);
            this.population[individualCount] = individual;
        }
    }
    public Individual[] getIndividuals() {
        return this.population;
    }

    public Individual getFittest(int offset) {
        Arrays.sort(this.population, new Comparator<Individual>() {
            @Override
            public int compare(Individual o1, Individual o2) {
                if (o1.getFitness() > o2.getFitness()) {
                    return -1;
                } else if (o1.getFitness() < o2.getFitness()) {
                    return 1;
                }
                return 0;
            }
        });
        return this.population[offset];
    }

    /**
     * Method to calculate the average fitness
     * @return
     */
    public double getAvgFitness(){
        if (this.populationFitness == -1) {
            double totalFitness = 0;
            for (Individual individual : population) {
                totalFitness += individual.getFitness();
            }
            this.populationFitness = totalFitness;
        }
        return populationFitness / this.size();
    }

    public void setPopulationFitness(double fitness) {
        this.populationFitness = fitness;
    }
    public double getPopulationFitness() {
        return this.populationFitness;
    }
    public int size() {
        return this.population.length;
    }
    public Individual setIndividual(int offset, Individual individual) {
        return population[offset] = individual;
    }

}
