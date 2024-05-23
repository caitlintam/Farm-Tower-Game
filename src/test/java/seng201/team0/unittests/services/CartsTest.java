//package seng201.team0.unittests.services;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import seng201.team0.gui.service.CartService;
//import seng201.team0.PlayerManager;
//import seng201.team0.TowerManager;
//import seng201.team0.models.Cart;
//import seng201.team0.models.Tower;
//
//import java.util.ArrayList;
//import java.util.List;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//public class CartsTest {
//    private CartService cartService;
//    private TowerManager towerManager;
//
//    private PlayerManager playerManager;
//    List<Cart> testCartsInRound;
//    List<Tower> testTowerInGame = new ArrayList<Tower>();
//    @BeforeEach
//    void init(){
//
//        Cart cart0 = new Cart(0, 0, "type1", "type2", 0);
//        Cart cart1 = new Cart(1, 20, "type3", "type4", 28);
//        Cart cart2 = new Cart( 10, 200, "type5", "type6", 200);
//        towerManager = new TowerManager();
//        playerManager = new PlayerManager(null,null,null,null,null,null,null,null,null,null,null,null);
//        cartService = new CartService(playerManager,new TowerManager());
//    }
//    @Test
//    /*
//    test setting the potential car types by the towers in the deafult towers
//     */
//    void testGenerateNewCarts(){
//        Tower tower0 = new Tower("TowerTest0", 0, "Test1", 0, 0, 0,"In-Game");
//        Tower tower1 = new Tower("TowerTest2", 10, "Test1", 25, 1, 100,"In-Game");
//        playerManager.addTowersToInventory(tower0);
//        playerManager.addTowersToInventory(tower1);
//        cartService.generateNewCartsInGame();
//        assertEquals(2, cartService.getCartsInRound().size());
//    }
//
////    @Test
////    /*
////    test that random cart size and speed are within given range of 0.8-12 and 0.7-1.3 respecticely
////     */
////    void testGenerateRandomCartSizeSpeed(){
////        int testResAmount = cartService.generateRan
////        int testReloadSpeed = 1;
////        assertThat(g).isBetween(min, max);;
////    }
////    void testShuffleCarts(){
////        cartService.setPotentialCartResourceTypes();
////        List<Cart> resTypes;
////        cartService.shuffleCartsInGameResourceTypes();
////
////    }
//
//
//}
