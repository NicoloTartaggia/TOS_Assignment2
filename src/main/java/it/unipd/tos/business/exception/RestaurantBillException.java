////////////////////////////////////////////////////////////////////
// [Nicolò] [Tartaggia] [1142836]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business.exception;

public class RestaurantBillException extends Exception{
    private String message;
    public RestaurantBillException(){
        message = "Non possono esserci più di 20 elementi per ordine";
    }
    @Override
    public String getMessage() {
        return message;
    }
}
