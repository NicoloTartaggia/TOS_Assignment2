package it.unipd.tos.business;

import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.ItemType;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

////////////////////////////////////////////////////////////////////
//[Nicolò] [Tartaggia] [1142836]
////////////////////////////////////////////////////////////////////

public class BellaNapoliBillTest {

    private MenuItem item1 = new MenuItem(ItemType.PIZZA, "Margeherita", 5.50);
    private MenuItem item2 = new MenuItem(ItemType.PIZZA, "Diavola", 6.50);
    private MenuItem item3 = new MenuItem(ItemType.PIZZA, "Capricciosa", 7.50);
    private MenuItem item4 = new MenuItem(ItemType.PIZZA, "Viennese", 7);
    private MenuItem item5 = new MenuItem(ItemType.PIZZA, "Frutti di Mare", 10.50);
    private MenuItem item6 = new MenuItem(ItemType.PRIMO, "Spaghetti al pomodoro", 6.50);
    private MenuItem item7 = new MenuItem(ItemType.PIZZA, "Penne ai 4 formaggi", 7.50);

    private BellaNapoliBill bill;

    @Before
    public void initialize(){
        bill = new BellaNapoliBill();
    }

    @Test
    public void emptyListTest() throws RestaurantBillException{
        List<MenuItem> itemList0 = new ArrayList<>();
        assertEquals( 0, bill.getOrderPrice(itemList0),0);
    }
    @Test
    public void noPizzaCheapestTest(){
        List<MenuItem> itemList0 = new ArrayList<>();
        assertEquals(0, bill.getCheapestPizzaPrice(itemList0), 0);
    }

    @Test
    public void getTotalTest() throws RestaurantBillException {
        List<MenuItem> itemList1 = new ArrayList<>(
                Arrays.asList(item1, item2, item3, item4, item5, item6, item7));
        assertEquals(51, bill.getOrderPrice(itemList1),0);
    }

    @Test(expected = RestaurantBillException.class)
    public void getTotalExceptionTest() throws RestaurantBillException {

        List<MenuItem> itemList2 = new ArrayList<>(
                Arrays.asList(
                        item1, item2, item3, item4, item5, item6, item7,
                        item1, item2, item3, item4, item5, item6, item7,
                        item1, item2, item3, item4, item5, item6, item7));
        bill.getOrderPrice(itemList2);
    }
    @Test
    public void exceptionTextTest() throws RestaurantBillException {
        try {
            List<MenuItem> itemList2 = new ArrayList<>(
                    Arrays.asList(
                            item1, item2, item3, item4, item5, item6, item7,
                            item1, item2, item3, item4, item5, item6, item7,
                            item1, item2, item3, item4, item5, item6, item7));
            bill.getOrderPrice(itemList2);
        }
        catch (RestaurantBillException e){
            assertEquals(
                    "Non possono esserci più di 20 elementi per ordine",
                    e.getMessage()
            );
        }
    }

    @Test
    public void getLastPizzaFree() throws RestaurantBillException{
        List<MenuItem> itemList2 = new ArrayList<>(
                Arrays.asList(
                        item1, item2, item3, item4, item5, item6, item7,
                        item1, item2, item3, item4, item5, item1, item7));
        assertEquals( 95.5, bill.getOrderPrice(itemList2),0);
    }

    @Test
    public void getDiscountOver100() throws RestaurantBillException{
        List<MenuItem> itemList2 = new ArrayList<>(
                Arrays.asList(
                        item6, item6, item6, item6, item6, item6, item6,
                        item6, item6, item6, item4, item4, item4, item4,
                        item4, item4));
        assertEquals(101.65, bill.getOrderPrice(itemList2), 0);
    }

}
