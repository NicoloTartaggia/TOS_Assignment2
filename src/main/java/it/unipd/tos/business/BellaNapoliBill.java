////////////////////////////////////////////////////////////////////
// [Nicolò] [Tartaggia] [1142836]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.MenuItem;

import java.util.List;
import java.util.OptionalDouble;

public class BellaNapoliBill implements RestaurantBill{

    /**
     * @param itemsOrdered: list of the items in the order
     * @return total amount of the order
     */
    private double getTotal(List<MenuItem> itemsOrdered){
        return itemsOrdered.stream().mapToDouble(MenuItem::getPrice).sum();
    }
    private long getPizzasNumber(List<MenuItem> itemsOrdered){
        return itemsOrdered.stream().filter(MenuItem::isPizza).count();
    }

    double getCheapestPizzaPrice(List<MenuItem> itemsOrdered){
        OptionalDouble cheapest = itemsOrdered.stream().
                filter(MenuItem::isPizza).mapToDouble(MenuItem::getPrice).min();
        if(cheapest.isPresent()) {
            return cheapest.getAsDouble();
        } else {
            return 0;
        }
    }

    @Override
    public double getOrderPrice(List<MenuItem> itemsOrdered)
            throws RestaurantBillException {

        if(itemsOrdered.size() > 20) {
            throw new RestaurantBillException();
        }

        if(itemsOrdered.size() == 0) {
            return 0;
        }

        double total = getTotal(itemsOrdered);
        if(getPizzasNumber(itemsOrdered)>10) {
            total -= getCheapestPizzaPrice(itemsOrdered);
        }
        if(total>100) {
            total-=0.05*total;
        }
        return total;
    }
}
