/** 
 * Maintains various info about a specific room type that is created upon instantiation.
 * Methods will add and remove guests in the room based on max. Occupancy, 
 * and maintain what hotel location it belongs to, return the room type, 
 * as well as the cost, and the name of the customer who reserved it.
 * 
 * Class for creating and maintaining your data structures
 * 
 * @author Patrick Brzezina
 * @collaborators none
 * teacher: Mrs. Ishman
 * Period: 3rd
 * Date Due: 5/11/20
 */
 

import java.util.HashMap;
import java.util.TreeSet;

public class Reservations 
{
  //Used to store info for hotel locations the rooms occupied in them
  private HashMap<Location, TreeSet<Room>> hotels;

  /**
   * Constructor for this class, instantiates hotels as a hash Map
   */
  public Reservations()
  {
    hotels = new HashMap<>();
  }
  
  /**
   * Adds the give new Location as a key to the Map
   * @param newLoc the Location object to add to the map
   */
  public void addLocation(Location newLoc)
  {
    hotels.put(newLoc, new TreeSet<>());
  }
  
  /**
   * Removes the given LOcation object from the map
   * @param  loc, the location object to remove
   */
   public void removeLocation (Location loc)
   {
     hotels.remove(loc);
   }
   
   /**
    * Adds the given room to the map to its matching Location object
    * @param newRoom, the room object to add to the map
    */
   public void addRoom (Room newRoom)
   {
     Location loc = newRoom.getLoc();
     if(hotels.get(loc) != null && !hotels.get(loc).contains(newRoom))
     {
         hotels.get(loc).add(newRoom);
     }

     else
     {
        if(hotels.get(loc) != null && hotels.get(loc).contains(newRoom))
        {
            System.out.println("Cannot add this room, because it is already stored with its appropriate Location Object\n");
            return;
        }
        TreeSet <Room> rooms = new TreeSet<>();
        rooms.add(newRoom);
        hotels.put(loc, rooms);
     }
     loc.occupyRoom(newRoom.getRoomType());

   }
   
   /**
    * Removes the given room from the map and its matching Location object
    * @param room, the room object to remove from the map
    */
   public void removeRoom(Room room)
   {
     Location loc = room.getLoc();
     if(!hotels.get(loc).contains(room))
      System.out.println("Unable to remove this room because it is not stored with its appropriate Location Object\n");
     else
     {
         hotels.get(loc).remove(room);
         loc.openRoom(room.getRoomType());
     }
   }
   
   /**
    * Fetches the location with the most rooms occupied
    * @return the location object with the most occupied rooms
    */
    public Location locWithMostOccupied()
    {
      if(hotels.size() == 0)
        return null;
      
      Location mostOccupied = null;
      int count = 0;
      for(Location l : hotels.keySet())
      {
        if(hotels.get(l).size() > count)
        {
          mostOccupied = l;
          count = hotels.get(l).size();
        }
          
      }
      return mostOccupied;
    }
    
    /**
    * Fetches the location with the most rooms available
    * @return the location object with the most available rooms
    */
    public Location locWithMostAvailable()
    {
      if(hotels.size() == 0)
        return null;
      
      Location mostAvailable = (Location)(hotels.keySet().toArray()[0]);
      for(Location l : hotels.keySet())
      {
        if(hotels.get(l).size() < hotels.get(mostAvailable).size())
        {
          mostAvailable = l;
        }
          
      }
      return mostAvailable;
    }
    
    /**
     * Fetches the hotel with the most expensive rooms
     * @return the location object with them most expensive price range
     */
     public Location mostExpensiveHotel()
     {
       if(hotels.size() == 0)
        return null;
        
       Location mostExpensive = null;
       double min = 0;
       double max = 0;
       int typeOne = 1;
       int typeTwo = 2;
       
       for(Location l : hotels.keySet())
       {
         if(l.getPrice(typeOne) > min && l.getPrice(typeTwo) > max)
         {
           mostExpensive = l;
           min = l.getPrice(typeOne);
           max = l.getPrice(typeTwo);
         }
       }
       return mostExpensive;
     }
     
     /**
      * Fetches the number of Locations in the map
      * @return the number of Locations in the map
      */
      public int getNumLocs()
      {
        return hotels.size();
      }
     
     /**
      * Fetches the number of rooms for the given Location
      * @param loc, the location to get the number of rooms for
      * @return the number of rooms for the given location
      */
      private int getNumRooms(Location loc)
      {
        return hotels.get(loc).size();
      }

    /**
     * Calculates the total # of rooms in the map
     * @return the total number of room objects in the map
     */
    public int totalNumRooms()
    {
        int total = 0;
        for(Location w : hotels.keySet())
        {
            total += getNumRooms(w);
        }
        return total;
    }

    /**
     * Finds the best hotel location for the given price and room type
     * @param price, the price to search for
     * @param type, the room type to search for
     * @return the Location object with the most matching price and with enough rooms available
     */
      public Location getFittingLocation (double price, int type)
      {
          double diff = 0;
          for(Location x : hotels.keySet())
          {
              diff = Math.abs(x.getPrice(type) - price);
              if (x.getPrice(type) == price && x.checkAvailability(type))
              {
                  return x;
              }
          }
          Location match = (Location) (hotels.keySet().toArray()[0]);
          for (Location y : hotels.keySet())
          {
              double currentDiff = Math.abs(y.getPrice(type) - price);
              if(currentDiff < diff && y.checkAvailability(type))
              {
                  match = y;
                  diff = currentDiff;
              }
          }
          return match;
      }

    /**
     * Gets the appropriate type of room based on the given number of guests
     * @param numGuests the # of guests that will be staying in the room
     * @return the type of room appropriate for the given number of guests
     */
    public int getType(int numGuests)
      {
          if(numGuests <= 2)
              return 1;
          if(numGuests <= 4)
              return 2;
          else
              System.out.println("Incorrect number of guests given\n");
          return 0;
      }

    /**
     * Creates and returns an appropriate String for the info stored in the map
     * @return the string full of info of the map
     */
      public String toString()
      {
          StringBuilder info = new StringBuilder("All hotels with their reservations: \n");
          for(Location x : hotels.keySet())
          {
              info.append(x);
              info.append(": ");
              for(Room r : hotels.get(x))
              {
                  info.append(r);
                  info.append("; ");
              }
              info.append("\n");
          }
          return info.toString();
      }
}