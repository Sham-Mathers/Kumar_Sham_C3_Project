import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException{
        for(int i=0;i<restaurants.size();i++){
            if(restaurantName.equals(restaurants.get(i).getName())){
                return restaurants.get(i);
            }
        }

        throw new restaurantNotFoundException(restaurantName);
    }


    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void addItemToResturantMenu(String restaurantName,String itemName, int price){
        for(int i=0;i<restaurants.size();i++){
            if(restaurantName.equalsIgnoreCase(restaurants.get(i).getName())){
                Restaurant obj = restaurants.get(i);

                obj.addToMenu(itemName,price);
            }
        }
    }

    public int totalCost(String restaurant,List<String> items){
        int sumOfItem=0;
        for(int i=0;i<restaurants.size();i++){
            if(restaurant.equalsIgnoreCase(restaurants.get(i).getName())){
                Restaurant obj=restaurants.get(i);
                sumOfItem=sum(obj,items);
            }
        }
        return sumOfItem;
    }

    private int sum(Restaurant restaurant,List<String> itemToProccess){
        int cost=0;
        for(int i=0;i<itemToProccess.size();i++){
            String itemName=itemToProccess.get(i);

            for(int j=0;j<restaurant.getMenu().size();j++){
                if(itemName.equalsIgnoreCase(restaurant.getMenu().get(j).getName())){
                    cost=cost+restaurant.getMenu().get(j).getPrice();
                }
            }
        }
        return cost;
    }

}
