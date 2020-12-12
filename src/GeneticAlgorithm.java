import java.util.ArrayList;
import java.util.Arrays;

public class GeneticAlgorithm {
    private int populationSize;
    private double mutationRate;
    private double crossoverRate;
    private int elitismCount;
    protected int tournamentSize;
    private double temperature = 1.0;
    private double coolingRate;

    public GeneticAlgorithm(int populationSize, double mutationRate, double crossoverRate, int elitismCount,int tournamentSize) {
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.elitismCount = elitismCount;
        this.tournamentSize=tournamentSize;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setCoolingRate(double coolingRate) {
        this.coolingRate = coolingRate;
    }

    /**
     * Cool temperature
     */
    public void coolTemperature() {
        this.temperature *= (1 - this.coolingRate);
    }

    /**
     * Creates a new population
     * @param chromosomeLength
     * @return
     */
    public Population initPopulation(int chromosomeLength) {
        Population population = new Population(this.populationSize,
                chromosomeLength);
        return population;
    }

    /**
     * Calculates the fitness
     * @param individual
     * @param cities
     * @return
     */
    public double calcFitness(Individual individual, ArrayList<City> cities){
    // Get fitness
        Route route = new Route(individual, cities);
        double fitness = 1 / route.getDistance();
    // Store fitness
        individual.setFitness(fitness);
        return fitness;
    }

    /**
     *Loops over each individual
     * @param population
     * @param cities
     */
    public void evalPopulation(Population population, ArrayList<City> cities){
        double populationFitness = 0;
        // Loop over population evaluating individuals and summing population fitness
        for (Individual individual : population.getIndividuals()) {
            populationFitness += this.calcFitness(individual, cities);
        }
        double avgFitness = populationFitness / population.size();
        population.setPopulationFitness(avgFitness);
    }

    /**
     *Function to check if the maximum generation has reached
     * @param generationsCount
     * @param maxGenerations
     * @return
     */
    public boolean isTerminationConditionMet(int generationsCount, int maxGenerations) {
        return (generationsCount > maxGenerations);
    }

    /**
     *new individual is created
     * @param population
     * @return
     */
    public Individual selectParent(Population population) {
// Get individuals
        Individual individuals[] = population.getIndividuals();
// Spin roulette wheel
        double populationFitness = population.getPopulationFitness();
        double rouletteWheelPosition = Math.random() * populationFitness;
        // Find parent
        double spinWheel = 0;
        for (Individual individual : individuals) {
            spinWheel += individual.getFitness();
            if (spinWheel >= rouletteWheelPosition) {
                return individual;
            }
        }
        return individuals[population.size() - 1];
    }

    /**
     *method relies on the selectParent,uses tournament selection
     * @param population
     * @return
     */
    public Population crossoverPopulation(Population population){
        // Create new population
        Population newPopulation = new Population(population.size());
        // Loop over current population by fitness
        for (int populationIndex = 0; populationIndex < population.size();
             populationIndex++) {
            // Get parent1
            Individual parent1 = population.getFittest(populationIndex);
            // Apply crossover to this individual?
            if (this.crossoverRate > Math.random() && populationIndex >=
                    this.elitismCount) {
                // Find parent2 with tournament selection
                Individual parent2 = this.selectParent(population);
                // Create blank offspring chromosome
                int offspringChromosome[] = new
                        int[parent1.getChromosomeLength()];
                Arrays.fill(offspringChromosome, -1);
                Individual offspring = new Individual(offspringChromosome);
                // Get subset of parent chromosomes
                int substrPos1 = (int) (Math.random() *
                        parent1.getChromosomeLength());
                int substrPos2 = (int) (Math.random() *
                        parent1.getChromosomeLength());
                // make the smaller the start and the larger the end
                final int startSubstr = Math.min(substrPos1, substrPos2);
                final int endSubstr = Math.max(substrPos1, substrPos2);
                // Loop and add the sub tour from parent1 to our child
                for (int i = startSubstr; i < endSubstr; i++) {
                    offspring.setGene(i, parent1.getGene(i));
                }
                // Loop through parent2's city tour
                for (int i = 0; i < parent2.getChromosomeLength(); i++) {
                    int parent2Gene = i + endSubstr;
                    if (parent2Gene >= parent2.getChromosomeLength()) {
                        parent2Gene -= parent2.getChromosomeLength();
                    }
                    // If offspring doesn't have the city add it
                    if (offspring.containsGene(parent2.getGene(parent2Gene)) == false) {
                    // Loop to find a spare position in the child's tour
                        for (int ii = 0; ii <
                                offspring.getChromosomeLength(); ii++) {
                    // Spare position found, add city
                            if (offspring.getGene(ii) == -1) {
                                offspring.setGene(ii,
                                        parent2.getGene(parent2Gene));
                                break;
                            }
                        }
                    }
                }
                // Add child
                newPopulation.setIndividual(populationIndex, offspring);
            } else {
                // Add individual to new population without applying crossover
                newPopulation.setIndividual(populationIndex, parent1);
            }
        }
        return newPopulation;
    }

    /**
     * starts by creating a new empty population
     * for the mutated individuals and then begins to loop over the current population
     * @param population
     * @return
     */
    public Population mutatePopulation(Population population){
        // Initialize new population
        Population newPopulation=new Population(this.populationSize);
        //Get best fitness
        double bestFitness=population.getFittest(0).getFitness();
        // Loop over current population by fitness
        for(int populationIndex=0;populationIndex<population.size();populationIndex++){
            Individual individual= population.getFittest(populationIndex);
        // Calculate adaptive mutation rate
            double adaptiveMutationRate=this.mutationRate;
            if(individual.getFitness()>population.getAvgFitness()){
                double fitnessDelta1=bestFitness-individual.getFitness();
                double fitnessDelta2=bestFitness-population.getAvgFitness();
                adaptiveMutationRate=(fitnessDelta1/fitnessDelta2)*this.mutationRate;
            }
            // Skip mutation if this is an elite individual
            if(populationIndex>=this.elitismCount){
                // System.out.println("Mutating population member "+populationIndex);
                // Loop over individual's genes
                for(int geneIndex=0;geneIndex<individual.getChromosomeLength();geneIndex++){
                    // Does this gene need mutation?
                    if(adaptiveMutationRate* this.getTemperature()>Math.random()){
                        // Get new gene position
                        int newGenePos=(int)(Math.random()*
                                individual.getChromosomeLength());
                        // Get genes to swap
                        int gene1=individual.getGene(newGenePos);
                        int gene2=individual.getGene(geneIndex);
                        // Swap genes
                        individual.setGene(geneIndex,gene1);
                        individual.setGene(newGenePos,gene2);
                    }
                }
            }
            // Add individual to population
            newPopulation.setIndividual(populationIndex,individual);
        }
        // Return mutated population
        return newPopulation;
    }

}

