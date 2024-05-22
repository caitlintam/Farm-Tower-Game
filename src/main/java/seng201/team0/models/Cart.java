package seng201.team0.models;

public class Cart {
    private int cartSize;
    private String primaryCartResourceType;
    private String secondaryCartResourceType;

    private float cartSpeed;
    private int cartNumber;

    public Cart(int cartNumber, int cartSize, String primaryCartResourceType, String secondaryCartResourceType, float cartSpeed){
        this.cartNumber = cartNumber;
        this.cartSize = cartSize;
        this.primaryCartResourceType = primaryCartResourceType;
        this.secondaryCartResourceType = secondaryCartResourceType;
        this.cartSpeed = cartSpeed;
    }
    public int getCartSize(){
        return cartSize;
    }
    public String getPrimaryCartResourceType(){
        return primaryCartResourceType;
    }
    public String getSecondaryCartResourceType(){return secondaryCartResourceType;}
    public float getCartSpeed(){
        return cartSpeed;
    }
    public int getCartID(){return cartNumber;}



}
