import java.util.Arrays;
import java.util.Comparator;

public class Population {
    private Individual population[];
    private double populationFitness = -1;
    public Population(int populationSize) {
        this.population = new Individual[populationSize];
    }

    /**
     * Takes the current population size and chromosomeLength as argument to create a new population
     * @param populationSize
     * @param chromosomeLength
     */
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

    /**
     * Get fitness value by sorting it and comparing the value -1,1,0
     * @param offset
     * @return
     */
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

    /**
     *
     * @param fitness
     */
    public void setPopulationFitness(double fitness) {
        this.populationFitness = fitness;
    }

    /**
     *
     * @return
     */
    public double getPopulationFitness() {
        return this.populationFitness;
    }

    /**
     *
     * @return
     */
    public int size() {
        return this.population.length;
    }

    /**
     *
     * @param offset
     * @param individual
     * @return
     */
    public Individual setIndividual(int offset, Individual individual) {
        return population[offset] = individual;
    }

}
