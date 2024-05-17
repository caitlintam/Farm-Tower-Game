package seng201.team0.models;

public class Cart {
    private int cartSize;
    private String cartResourceType;
    private float cartSpeed;

    public Cart(int cartSize, String cartResourceType, float cartSpeed){
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


}
