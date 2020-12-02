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

    // Gets the distance to given city
    public double distanceTo(City city){
        int xDistance = Math.abs(getxCoordinate() - city.getxCoordinate());
        int yDistance = Math.abs(getyCoordinate() - city.getyCoordinate());
        double distance = Math.sqrt( (xDistance*xDistance) + (yDistance*yDistance) );

        return distance;
    }



    void printCity(){
        System.out.println("Position: "+postion+" ,X-Coordinate: "+xCoordinate+ " ,Y-Coordinate: "+yCoordinate);
    }
}
