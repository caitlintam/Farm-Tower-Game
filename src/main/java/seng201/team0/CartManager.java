package seng201.team0;
import java.util.ArrayList;
import seng201.team0.models.Cart;
import seng201.team0.models.Tower;
import seng201.team0.PlayerManager;

import java.util.Collections;
import java.util.List;

public class CartManager {
    private List<Cart> carts;
    private List<Cart> selectedCarts;
    private PlayerManager playerManager;
    // private int currentCartSize;
    //privcate int num_reloads = 0;

    public CartManager() {
        this.carts = new ArrayList<>();
        this.selectedCarts = new ArrayList<>();
        this.playerManager = playerManager;
        carts.addAll(List.of(
                new Cart(100, "Pigs", 20),
                new Cart(70, "Cows", 10),
                new Cart(100, "Hay", 40),
                new Cart(90, "Timber", 10),
                new Cart(120, "Water", 5),
                new Cart(50, "Steel", 30),
                new Cart(60, "Corn", 10),
                new Cart(120, "Wheat", 5),
                new Cart(100, "Chickens", 40)));


    }

    public void addCart(Cart cart) {
        carts.add(cart);
    }

    public void selectRandomCarts() {
        Collections.shuffle(carts);
        selectedCarts = new ArrayList<>(carts.subList(0, 3));
    }

    public List<Cart> getSelectedCarts() {
        return selectedCarts;
    }

    // methods:
    //fill cart
    public void runRound() {
        for (Cart cart : selectedCarts) {
            for (Tower tower : playerManager.getTowersInGame()) {
                if (cart.getCartResourceType() == tower.getTowerResourceType()) {
                    while (numReloads < (tower.getTimeOnTrack() / tower.getTowerReloadSpeed())) {
                        currentcartSize += tower.getTowerResourceAmount();
                        numReloads++;

                        if (currentCartSize >= cart.getCartSize()) {
                            System.out.println("You win! You filled " + numReloads + " carts");
                            playerManager.setMoney(playerManager.getMoney() *= numReloads);
                            // launch round win screen
                            // playerManager.setNumRoundsWon(getNumRoundsWon + 1));
                            break;
                        } else if (currentCartSize < cart.getCartSize()) {
                            System.out.println("You lose, you didnt manage to fill any carts!");
                            //launch round lose screen

                        }
                    }
                }
            }
        }
    }
}

    //new cart
    //round complete