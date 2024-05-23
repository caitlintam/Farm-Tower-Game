package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.Cart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CartTest {
    private Cart cart;

    @BeforeEach
    public void setUp() {
        cart = new Cart(1, 100, "Gold", "Silver", 5);
    }

    @Test
    public void testCartConstructor() {
        assertNotNull(cart);
    }

    @Test
    public void testGetCartSize() {
        assertEquals(100, cart.getCartSize());
    }

    @Test
    public void testGetPrimaryCartResourceType() {
        assertEquals("Gold", cart.getPrimaryCartResourceType());
    }

    @Test
    public void testGetSecondaryCartResourceType() {
        assertEquals("Silver", cart.getSecondaryCartResourceType());
    }

    @Test
    public void testGetCartSpeed() {
        assertEquals(5, cart.getCartSpeed());
    }

    @Test
    public void testGetCartID() {
        assertEquals(1, cart.getCartID());
    }
}