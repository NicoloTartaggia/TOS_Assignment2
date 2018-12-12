package it.unipd.tos.model;

public class MenuItem {
	private ItemType type;
    private String name;
    private double price;
    public MenuItem(ItemType type, String name, double price){
        this.type = type;
        this.name = name;
        this.price = price;
    }
    public double getPrice(){
        return price;
    }
    public boolean isPizza(){
        return type == ItemType.PIZZA;
    }
}
