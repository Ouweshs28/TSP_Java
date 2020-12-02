import java.util.ArrayList;

public class TSP_CW {
    public static void main(String[] args) {
        FileManagement cityFile=new FileManagement();
        ArrayList <City> cities=cityFile.readFile("test3atsp.txt");
    }

}
