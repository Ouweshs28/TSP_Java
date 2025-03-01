/**
 * Individual Class
 */
public class Individual {
    private int[] chromosome;
    private double fitness = -1;

    /**
     *
     * @param chromosome
     */
    public Individual(int[] chromosome) {
// Create individual chromosome
        this.chromosome = chromosome;
    }

    /**
     *
     * @param chromosomeLength
     */
    public Individual(int chromosomeLength) {
// Create random individual
        int[] individual;
        individual = new int[chromosomeLength];
        for (int gene = 0; gene < chromosomeLength; gene++) {
            individual[gene] = gene;
        }
        this.chromosome = individual;
    }

    public int[] getChromosome() {
        return this.chromosome;
    }
    public int getChromosomeLength() {
        return this.chromosome.length;
    }
    public void setGene(int offset, int gene) {
        this.chromosome[offset] = gene;
    }
    public int getGene(int offset) {
        return this.chromosome[offset];
    }
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
    public double getFitness() {
        return this.fitness;
    }

    /**
     * Checks if it contains the gene
     * @param gene
     * @return
     */
    public boolean containsGene(int gene) {
        for (int i = 0; i < this.chromosome.length; i++) {
            if (this.chromosome[i] == gene) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        String output = "";
        for (int gene = 0; gene < this.chromosome.length; gene++) {
            output += this.chromosome[gene];
        }
        return output;
    }
}

