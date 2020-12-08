/**
 * City class
 */
public class City {
    private int postion;
    private int xCoordinate;
    private int yCoordinate;

    public City(int pos,int x,int y){
        postion=pos;
        xCoordinate=x;
        yCoordinate=y;
    }

    public int getPostion() {
        return postion;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public double distanceFrom(City city) {
// Give difference in x,y
        double deltaXSq = Math.pow((city.getxCoordinate() - this.getxCoordinate()), 2);
        double deltaYSq = Math.pow((city.getyCoordinate() - this.getyCoordinate()), 2);
// Calculate shortest path
        double distance = Math.sqrt(Math.abs(deltaXSq + deltaYSq));
        return distance;
    }
    @Override
    public String toString(){
        return getxCoordinate()+", "+getyCoordinate();
    }

}
