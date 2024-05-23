package seng201.team0.models;
/**
 * Represents a cart in the game with various properties such as size, resource types, speed, and number.
 */
public class Cart {
    private final int cartSize;
    private final String primaryCartResourceType;
    private final String secondaryCartResourceType;
    private final float cartSpeed;
    private final int cartNumber;

    /**
     * Constructs a new Cart with the specified properties.
     *
     * @param cartNumber the identifier number of the cart
     * @param cartSize the size of the cart
     * @param primaryCartResourceType the primary type of resource the cart can carry
     * @param secondaryCartResourceType the secondary type of resource the cart can carry
     * @param cartSpeed the speed of the cart
     */
    public Cart(int cartNumber, int cartSize, String primaryCartResourceType, String secondaryCartResourceType, float cartSpeed){
        this.cartNumber = cartNumber;
        this.cartSize = cartSize;
        this.primaryCartResourceType = primaryCartResourceType;
        this.secondaryCartResourceType = secondaryCartResourceType;
        this.cartSpeed = cartSpeed;
    }
    /**
     * Returns the size of the cart.
     *
     * @return the size of the cart
     */
    public int getCartSize(){
        return cartSize;
    }
    /**
     * Returns the primary type of resource the cart can carry.
     *
     * @return the primary type of resource the cart can carry
     */
    public String getPrimaryCartResourceType(){
        return primaryCartResourceType;
    }
    /**
     * Returns the secondary type of resource the cart can carry.
     *
     * @return the secondary type of resource the cart can carry
     */
    public String getSecondaryCartResourceType(){
        return secondaryCartResourceType;
    }
    /**
     * Returns the speed of the cart.
     *
     * @return the speed of the cart
     */
    public float getCartSpeed(){
        return cartSpeed;
    }
    /**
     * Returns the identifier number of the cart.
     *
     * @return the identifier number of the cart
     */
    public int getCartID(){
        return cartNumber;
    }
}
