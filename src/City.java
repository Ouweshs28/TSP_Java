public class City {
    private int postion;
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

    void printCity(){
        System.out.println("Position: "+postion+" ,X-Coordinate: "+xCoordinate+ " ,Y-Coordinate: "+yCoordinate);
    }
}
