package seng201.team0.models;

public class Cart {
    private int cartSize;
    private String cartResourceType;
    private float cartSpeed;
    private int numReloads;
    private int cartNumber;

    public Cart(int cartNumber, int cartSize, String cartResourceType, float cartSpeed){
        this.cartNumber = cartNumber;
        this.cartSize = cartSize;
        this.cartResourceType = cartResourceType;
        this.cartSpeed = cartSpeed;
    }
    public int getCartSize(){
        return cartSize;
    }
    public String getCartResourceType(){
        return cartResourceType;
    }
    public float getCartSpeed(){
        return cartSpeed;
    }
    public int getCartID(){return cartNumber;}



}
