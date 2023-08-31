/** 
 * Maintains various info about a specific room type that is created upon instantiation
 * Methods will add and remove guests in the room based on max. Occupancy, 
 * and maintain what hotel location it belongs to, return the room type, 
 * as well as the cost, and the name of the customer who reserved it.
 * 
 * @author Patrick Brzezina
 * collaborators: none
 * teacher: Mrs. Ishman
 * Period: 3rd
 * Date Due: 5/7/20
 * 
 * Class for objects being stored in 1 of your data structures
 * 
 * Note: click on 3 dots next to "Class2.java" to rename
 *       click on button to the left of Main.java to create new files
 */
 

public class Room implements Comparable<Room>
{
  private static final int ROOM_ONE = 1;
  private static final int ROOM_TWO = 2;
    // Max occupancy for room types 1 and 2
  private static final int  MAX_OCCUPANCY_ONE = 2;
  private static final int MAX_OCCUPANCY_TWO = 4;
  
  //Store important info for the room class
  private int numGuests;
  private String nameOfReserver;
  private int maxOccupancy;
  private Location loc;
  private int roomType;
  private double cost;
  
  /**
   * Default constructor for this class
   */
  public Room ()
  {
    numGuests = 0;
    nameOfReserver = "";
    maxOccupancy = 0;
    loc = new Location();
    roomType = 0;
    cost = 0;
  }
  /**
   * Creates a room given specifc info
   * @param numGuests, the # of guests in the Room
   * @param nameOfReserver, name of the guest who reserved this Room
   * @param place, the Location object this room belongs to
   * @param roomType, the integer representing either room types 1 or 2
   */
  public Room (int numGuests, String nameOfReserver, Location place, int roomType)
  {
    this.numGuests = numGuests;
    this.nameOfReserver = nameOfReserver;
    loc = place;
    this.roomType = roomType;
    maxOccupancy = getMaxOccupancy(roomType);
    cost = loc.getPrice(roomType);
  }
  
  /**
   * adds the given number of guests if possible
   * @param guestsToAdd, the # of guests to add to this room
   */
  public void addGuests(int guestsToAdd)
  {
    if(numGuests + guestsToAdd > maxOccupancy)
    {
      System.out.println("Error. Cannot add these guests because it exceeds maximum occupancy \n");
    }
    else
     numGuests = numGuests + guestsToAdd;
  }
  
  /**
   * removes the given number of guests if possible
   * @param guestsToSubtract, the # of guests to remove from this room
   */
  public void removeGuests(int guestsToSubtract)
  {
    if(numGuests - guestsToSubtract < 0)
    {
      System.out.println("Error. Cannot remove these guests because ther are not this many guests in this room\n");
    }
    else
     numGuests -= guestsToSubtract;
  }
  
  /**
   * updates the location of this class to the one given
   * @param newLoc, the location to update to
   */
   public void changeLoc (Location newLoc)
   {
     loc = newLoc;
     cost = loc.getPrice(roomType);
   }
   
   /**
    * fetches the number of guests in this room 
    * @return the number of guests in this room
    */
    public int getNumGuests()
    {
      return numGuests;
    }
    
    /**
     * fetches the name of the person who reserved this room
     * @return the name of the person who reserved this room
     */
     public String getNameOfReserver()
     {
       return nameOfReserver;
     }

    /**
     * Fetches the room type
     * @return the integer that stands for a certain room type
     */
    public int getRoomType()
     {
         return roomType;
     }
     
     /**
      * fetches the max amount of people allowed in this room based on its type
      * @param roomType the type of room
      * @return max occupancy allowed
      */
      public int getMaxOccupancy(int roomType)
      {
          if(roomType == ROOM_ONE)
              return MAX_OCCUPANCY_ONE;
          if(roomType == ROOM_TWO)
            return MAX_OCCUPANCY_TWO;
          else
          {
              System.out.println("Incorrect room type given");
              return 0;
          }

      }
      
      /**
       * fetches and returns the location object of this room
       * @return the location of this room
       */
       public Location getLoc()
       {
         return loc;
       }
       
       /**
        * fetches the cost of this room
        * @return the cost of this room
        */
        public double getCost()
        {
          return cost;
        }

    /**
     * Returns a string of how a room object should be displayed
     * @return how the room object should be displayed
     */
    @Override
    public String toString()
    {
        StringBuilder ret = new StringBuilder();
        ret.append(getNameOfReserver());
        ret.append(", ");
        ret.append(getNumGuests());
        ret.append(" guests, $");
        String finalPrice = String.format("%.2f", getCost());
        ret.append(finalPrice);
        return ret.toString();
    }

    /**
     * Room objects will be ordered by alphabetical order by the name of reserver
     * @param other, the other Room object to compare to
     * @return whether this room object's name is bigger or smaller alphabetically
     */
      @Override
      public int compareTo(Room other)
      {
          if(equals(other))
              return 0;
          for (int idx = 0; idx < nameOfReserver.length(); idx++)
          {
              char letter = nameOfReserver.charAt(idx);
              char letterOther = other.getNameOfReserver().charAt(idx);
              if(letter < letterOther)
                  return -1;
              if(letter > letterOther)
                  return 1;
          }
          return 0;
      }

    /** Determines whether or not this room is the same as other
     *  @param other the other Room to check
     *  @return true if this and other are the same; false otherwise
     */
    @Override
    public boolean equals(Object other)
    {
        if (other == null || !(other instanceof Room))
            return false;

        Room otherRoom = (Room) other;
        return nameOfReserver.equals(otherRoom.nameOfReserver);
    }
}