/**
 * Will test methods of other classes and read in data from a text file and 
 * store it appropriately using the Reservations class. Text files will contain info such as the # of guests, room type preference, and preferred price.
 * 
 * @author Patrick Brzezina
 * collaborators none
 * teacher: Mrs. Ishman
 * Period: 3rd
 * Date Due: 5/7/20
 * 
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main
{
    private static final int DEFAULT_NUM_AVAILABLE = 3;
    /*
    private static final double TYPE_ONE_MIN = 80;
    private static final double TYPE_TWO_MIN = 100;
    private static final double TYPE_ONE_MAX = 200;
    private static final double TYPE_TWO_MAX = 250;
    */

    public static void main(String[] args)
    {
        //System.out.println("Hello world!");
        Reservations hotels = new Reservations();
        /*
        String [] names = {"Hilton", "Holiday Inn", "Westin", "Best Western", "Marriott"};
        for(String x : names)
        {
            double min = Math.random() * (TYPE_ONE_MAX - TYPE_ONE_MIN);
            double max = Math.random() * (TYPE_TWO_MAX - TYPE_TWO_MIN);
            hotels.addLocation(new Location(min, max, DEFAULT_NUM_AVAILABLE, DEFAULT_NUM_AVAILABLE, x));
        }
        */
        boolean shouldContinue = true;
        while (shouldContinue)
        {
            Scanner userInput = new Scanner(System.in);
            try
            {

                Scanner scanHotels = new Scanner (new File ("Hotels.txt"));
                while(scanHotels.hasNextLine())
                {
                    String name = scanHotels.nextLine();
                    double min = scanHotels.nextDouble();
                    double max = scanHotels.nextDouble();
                    hotels.addLocation(new Location(min, max, DEFAULT_NUM_AVAILABLE, DEFAULT_NUM_AVAILABLE, name));
                    scanHotels.nextLine();
                }
                System.out.println("Enter the name of the file to read:");
                String fileName = userInput.nextLine();
                System.out.println();
                Scanner scan = new Scanner(new File(fileName));
                while(scan.hasNext())
                {
                    String firstName = scan.next();
                    String lastName = scan.next();
                    String guestName = firstName + " " + lastName;
                    int numGuests = scan.nextInt();
                    int roomType = hotels.getType(numGuests);
                    double preferredPrice = scan.nextDouble();
                    Location loc = hotels.getFittingLocation(preferredPrice, roomType);
                    Room room = new Room(numGuests, guestName, loc, roomType);
                    hotels.addRoom(room);
                }
                System.out.println(hotels);
                int numLocs = hotels.getNumLocs();
                System.out.println("Number of locations: " + numLocs);
                System.out.println("Number of total rooms reserved: " + hotels.totalNumRooms());
                System.out.println("Most occupied location: " + hotels.locWithMostOccupied().getName());
                System.out.println("Location with most availability: " + hotels.locWithMostAvailable().getName());
                System.out.println("Most expensive location: " + hotels.mostExpensiveHotel().getName());
                System.out.println();
                shouldContinue = checkContinue(userInput);
            }
            catch (FileNotFoundException e)
            {
                System.out.println("File doesn't exist. Please try again.\n");
                shouldContinue = checkContinue(userInput);
            }

        }


    }

    /**
     * Checks if the user wants to continue
     * @param scan the scanner object
     * @return whether or not the user wants to continue
     */
    private static boolean checkContinue(Scanner scan)
    {
        System.out.println("Do you want to continue ? (y/n)");
        String response = scan.next();
        System.out.println();
        return response.equalsIgnoreCase("y") || response.equalsIgnoreCase("yes");
    }

 }