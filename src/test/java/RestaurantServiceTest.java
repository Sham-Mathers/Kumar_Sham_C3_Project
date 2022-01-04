import org.junit.jupiter.api.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE


    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @BeforeEach
    void setUp() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        //WRITE UNIT TEST CASE HERE
        service.addRestaurant("Amelie's Cafe","Chennai",LocalTime.parse("09:00:00"),LocalTime.parse("23:00:00"));
        service.addRestaurant("BBQ Nation","Bangalore",LocalTime.parse("10:00:00"),LocalTime.parse("23:00:00"));

        Restaurant obj = service.findRestaurantByName("Amelie's Cafe");
        assertNotNull(obj);
        assertEquals("Amelie's Cafe",obj.getName());
    }

    //You may watch the video by Muthukumaran on how to write exceptions in Course 3: Testing and Version control: Optional content
    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception(){
        //WRITE UNIT TEST CASE HERE
        assertThrows(restaurantNotFoundException.class,() ->{
                    service.findRestaurantByName("Pantry dor");
                }
        );
    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Test
    public void test_sum_of_item_should_give_an_positive_value(){
        service.addRestaurant("Amelie's cafe","Chennai",LocalTime.parse("10:30:00"),LocalTime.parse("22:00:00"));
        service.addRestaurant("Pantry dor","Bangalore",LocalTime.parse("10:00:00"),LocalTime.parse("23:00:00"));

        service.addItemToResturantMenu("Amelie's cafe","Pizza",340);
        service.addItemToResturantMenu("Amelie's cafe","Burger",380);
        service.addItemToResturantMenu("Amelie's cafe","Pinacolado",640);
        service.addItemToResturantMenu("Amelie's cafe","PanCake",440);
        service.addItemToResturantMenu("Amelie's cafe","Pasta",240);

        service.addItemToResturantMenu("Pantry dor","Idly",80);
        service.addItemToResturantMenu("Pantry dor","Dosa",140);
        service.addItemToResturantMenu("Pantry dor","Soup",200);
        service.addItemToResturantMenu("Pantry dor","PannerTikka",240);
        service.addItemToResturantMenu("Pantry dor","ChickenTikka",440);

        List<String> itemToBeTested1 = new ArrayList<>();
        itemToBeTested1.add("Pizza");
        itemToBeTested1.add("PanCake");

        List<String> itemToBeTested2 = new ArrayList<>();
        itemToBeTested2.add("Dosa");
        itemToBeTested2.add("ChickenTikka");

        assertEquals(780,service.totalCost("Amelie's cafe",itemToBeTested1));
        assertEquals(580,service.totalCost("Pantry dor",itemToBeTested2));
    }
    //>>>>>>>>>>>>>>>>>>>>>>Find Total Cost<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Amelie's cafe");
        assertEquals(initialNumberOfRestaurants-1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {

        assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
}