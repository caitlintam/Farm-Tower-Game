package seng201.team0;
import java.util.ArrayList;
import seng201.team0.models.Cart;
import seng201.team0.models.Tower;

import java.util.Collections;
import java.util.List;

public class CartManager {
    private List<Cart> carts;
    private List<Cart> selectedCarts;

    public CartManager() {
        this.carts = new ArrayList<>();
        this.selectedCarts = new ArrayList<>();
        carts.addAll(List.of(
                new Cart(10, "Pigs", 20),
                new Cart(30, "Cows", 10),
                new Cart(25, "Hay", 40),
                new Cart(10, "Timber", 10),
                new Cart(50, "Water", 5),
                new Cart(5, "Steel", 30),
                new Cart(15, "Corn", 10),
                new Cart(35, "Wheat", 5),
                new Cart(7, "Chickens", 40)));


    }
    public void addCart(Cart cart) {
        carts.add(cart);
    }
    public void selectRandomCarts(){
        Collections.shuffle(carts);
        selectedCarts = new ArrayList<>(carts.subList(0,3));
    }
    public List<Cart> getSelectedCarts(){
        return selectedCarts;
    }

    // methods:
    //fill cart
    public void fillCart(){
    for (Cart cart : carts){
        for (Tower tower : towerList
        if (cart.getCartResourceType() == tower.getTowerResourceType())
            cart.
    }
    }
    //new cart
    //round complete
}
