package application;

import java.util.Comparator;

/**
 * This class is used to alphabetize by comparing the names of fooditems
 * 
 * @author India (iarcher@wisc.edu)
 */

public class Alphabetize implements Comparator<FoodItem> {

    /**
     * This method compares the names of two food items
     * 
     * @param food1, food2 - the food items to be compared
     */
        public int compare(FoodItem food1, FoodItem food2) {
            //Compares names of the FoodItems for alphabetizing
            return (((FoodItem) food1).getName()).compareToIgnoreCase(((FoodItem)food2).getName());
        }
}
