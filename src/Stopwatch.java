public class Stopwatch {

    private final double start;

    /**
     * Initializes a new stopwatch.
     */
    public Stopwatch() {
        start = System.nanoTime();
    }


    /**
     * Returns the elapsed CPU time (in seconds) since the stopwatch was created.
     *
     * @return elapsed CPU time (nanseconds) since the stopwatch was created
     */
    public double elapsedTime() {
        double now = System.nanoTime();
        return (now - start) ;
    }
}
