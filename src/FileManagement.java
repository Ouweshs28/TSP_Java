import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class for File management
 */

public class FileManagement {
    private Scanner scan = null;


    public void getFile(String filename) {
        File tspfile = new File(filename);
        try {
            scan = new Scanner(tspfile);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }
    }


    /**
     * Takes the file name as argument and stores all cities in an arraylist
     * @param filename
     * @return
     */
    public ArrayList<City> readFile(String filename) {
        getFile(filename);
        ArrayList<City> cities=new ArrayList<City>();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            try {
                String[] details = line.split(" ");
                int pos = Integer.parseInt(details[0]);
                int xCoordinate = Integer.parseInt(details[1]);
                int yCoordinate = Integer.parseInt(details[2]);
                cities.add(new City(pos,xCoordinate,yCoordinate));

            } catch (ArrayIndexOutOfBoundsException exception) {
                System.out.println("File not valid");
                System.exit(0);
            }
        }
        //}
        return cities;
    }


}
