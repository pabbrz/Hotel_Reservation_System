/**
 * Maintains info about a location, including the price of each room type, price range, 
 * # of rooms available for each type, and name. There will be methods that will check
 * availability, return price range, return the price of a room, return name, 
 * and update availability.
 * 
 * @author Patrick Brzezina
 * collaborators: none
 * teacher: Mrs. Ishman
 * Period: 3rd
 * Date Due: 5/7/20
 */
 
 public class Location 
 {
   private static final int ROOM_ONE = 1;
   private static final int ROOM_TWO = 2;
   
   //Keep track of vital info for a location object
   private double priceOfTypeOne;
   private double priceOfTypeTwo;
   private double priceMin;
   private double priceMax;
   private int numOneAvailable;
   private int numTwoAvailable;
   private String name;
   
   /**
    * Default constructor for this class
    */
   public Location ()
   {
     priceOfTypeOne = 0;
     priceOfTypeTwo = 0;
     priceMin = 0;
     priceMax = 0;
     numOneAvailable = 0;
     numTwoAvailable = 0;
     name = "";
   }
   
   /**
    * Instantiates instance variables to the info given 
    */
   public Location (double priceMin, double priceMax, int numOneAvailable, int numTwoAvailable, String name)
   {
     priceOfTypeOne = priceMin;
     priceOfTypeTwo = priceMax;
     this.priceMin = priceMin;
     this.priceMax = priceMax;
     this.numOneAvailable = numOneAvailable;
     this.numTwoAvailable = numTwoAvailable;
     this.name = name;
   }
   
   /**
    * Determines if there are any rooms available based on the given type
    * @param roomType, the integer representing the type of room 
    * @return whether or not that room is available
    */
    public boolean checkAvailability (int roomType)
    {
      if(roomType == ROOM_ONE)
      {
          return numOneAvailable > 0;
      }
      if(roomType == ROOM_TWO)
      {
          return numTwoAvailable > 0;
      }
      return false;
    }
    
    /**
     * creates and returns a string of the price range of the hotel =
     * @return the price range of this location as a string
     */
     public String getPriceRange ()
     {
       return priceMin + " to " + priceMax;
     }
     
     /**
      * Fetches the price of the given room 
      * @param roomType, the room type to get the price for
      * @return the price of the room type 
      */
      public double getPrice(int roomType)
      {
        if(roomType == ROOM_ONE)
          return priceOfTypeOne;
        else if(roomType == ROOM_TWO)
          return priceOfTypeTwo;
        else
        {
          System.out.println("Invalid room type. PLease state whether it's room type 1 or 2");
          return 0;
        }
      }
      
      /**
       * Fetches the name of this hotel
       * @return the name of this location
       */
      public String getName()
      {
        return name;
      }
      
      /**
       * Updates availability based on the given command
       * @param action whether or not to occupy or open up a specific room type
       */
      public void updateAvailability (String action)
      {
        if(action.equalsIgnoreCase("add room type one") || action.equalsIgnoreCase("add room type 1"))
        {
          openRoom(ROOM_ONE);
        }
        else if (action.equalsIgnoreCase("remove room type one") || action.equalsIgnoreCase("remove room type 1"))
        {
          occupyRoom(ROOM_ONE);
        }
        else if (action.equalsIgnoreCase("add room type two") || action.equalsIgnoreCase("add room type 2"))
        {
          openRoom(ROOM_TWO);
        }
        else if (action.equalsIgnoreCase("remove room type two") || action.equalsIgnoreCase("remove room type 2"))
        {
          occupyRoom(ROOM_TWO);
        }
        else
        {
          System.out.println("Invalid action command. Please retry.");
        }
      }
      
      /**
       * occupies a room based on the given type
       * @param roomType, the room type to occupy
       */
      public void occupyRoom (int roomType)
      {
        if(roomType == ROOM_ONE && numOneAvailable > 0)
        {
          numOneAvailable --;
        }
        else if(roomType == ROOM_TWO && numTwoAvailable > 0)
          numTwoAvailable --;
        else
        {
          System.out.println("Cannot occupy this room type. There are no more available");
        }
      }
      /**
       * Opens up the given room type
       * @param roomType, the room type to open up
       */
       public void openRoom (int roomType)
       {
         if(roomType == ROOM_ONE)
            numOneAvailable ++;
          else if (roomType == ROOM_TWO)
            numTwoAvailable ++;
          else
            System.out.println("Invalid room type. Unable to perform action");
       }

     /** Calculates and returns a hashcode for this location
      *  @return hashcode
      */
     @Override
     public int hashCode()
     {
         return name.hashCode();
     }

     /** Determines whether or not this location is the same as other
      *  @param other the other Location to check
      *  @return true if this and other are the same; false otherwise
      */
     @Override
     public boolean equals(Object other)
     {
         if (other == null || !(other instanceof Location))
             return false;

         Location otherLoc = (Location) other;
         return name.equals(otherLoc.name);
     }

     /**
      * Creates and returns a location as a string
      * @return this class as a string
      */
     @Override
     public String toString()
     {
         return name;
     }
 }