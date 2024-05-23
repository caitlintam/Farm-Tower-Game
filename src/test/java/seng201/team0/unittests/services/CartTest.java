package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.Cart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
/**
 * basic unit tests for the Cart class.
 */
public class CartTest {
    private Cart cart;
    /**
     * sets  up the test environment before each test case.
     */
    @BeforeEach
    public void setUp() {
        cart = new Cart(1, 100, "Gold", "Silver", 5);
    }
    /**
     * Tests the constructor of the Cart class, checks not empty.
     */
    @Test
    public void testCartConstructor() {
        assertNotNull(cart);
    }
    /**
     * Tests the getCartSize method of the Cart class, given the input value.
     */
    @Test
    public void testGetCartSize() {
        assertEquals(100, cart.getCartSize());
    }
    /**
     * Tests the getPrimaryCartResourceType method of the Cart class.
     */

    @Test
    public void testGetPrimaryCartResourceType() {
        assertEquals("Gold", cart.getPrimaryCartResourceType());
    }
    /**
     * Tests the getSecondaryCartResourceType method of the Cart class.
     */
    @Test
    public void testGetSecondaryCartResourceType() {
        assertEquals("Silver", cart.getSecondaryCartResourceType());
    }

    /**
     * Tests the getCartSpeed method of the Cart class.
     */
    @Test
    public void testGetCartSpeed() {
        assertEquals(5, cart.getCartSpeed());
    }
    /**
     * Tests the getCartID method of the Cart class.
     */
    @Test
    public void testGetCartID() {
        assertEquals(1, cart.getCartID());
    }
}