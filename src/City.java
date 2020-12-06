public class City {
    private int postion;

    public int getPostion() {
        return postion;
    }

    public void setPostion(int postion) {
        this.postion = postion;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    private int xCoordinate;
    private int yCoordinate;

    public City() {
        this.postion=0;
        this.xCoordinate=0;
        this.yCoordinate=0;

    }

    public City(int pos,int x,int y){
        postion=pos;
        xCoordinate=x;
        yCoordinate=y;
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
    void printCity(){
        System.out.println("Position: "+postion+" ,X-Coordinate: "+xCoordinate+ " ,Y-Coordinate: "+yCoordinate);
    }
}
