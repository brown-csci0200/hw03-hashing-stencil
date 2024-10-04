package sol;

import org.junit.Assert;
import org.junit.Before;
import src.IDictionary;
import src.KeyAlreadyExistsException;
import src.KeyNotFoundException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertNotEquals;

public class YulpTests {

    @Test
    public void testExampleTwoRestaurants() {
        Yulp y = new Yulp();

        // Add two restaurants
        Restaurant rMango = y.addRestaurant("Mango", "22 15th st",
                new String[]{"bar", "open late", "pizza"});
        Restaurant rNonna = y.addRestaurant("Nonna Pie", "10 Main st",
                new String[]{"pizza", "family friendly"});

        Set<Restaurant> searchResults = y.lookupByTag("pizza");
        Assert.assertTrue(searchResults.contains(rNonna));
        Assert.assertTrue(searchResults.contains(rMango));
    }

    @Test
    public void testExampleAddTag() {
        Yulp y = new Yulp();

        // Add two restaurants
        Restaurant rMango = y.addRestaurant("Mango", "22 15th st",
                new String[]{"bar", "open late", "pizza"});
        Restaurant rNonna = y.addRestaurant("Nonna Pie", "10 Main st",
                new String[]{"pizza", "family friendly"});

        Assert.assertFalse(y.lookupByTag("open late").contains(rNonna));

        // User rando adds the tag "open late" to Nonna
        y.addTag("open late", rNonna, "anh", "They were open until midnight yesterday");

        // Nonna now shows up when we search for the tag
        Assert.assertTrue(y.lookupByTag("open late").contains(rNonna));

        Assert.assertEquals(2, y.lookupByTag("open late").size()); // Another way to do the same check
    }

    @Test
    public void testExampleRemoveTag() {
        Yulp y = new Yulp();

        // Add two restaurants
        Restaurant rMango = y.addRestaurant("Mango", "22 15th st",
                new String[]{"bar", "open late", "pizza"});
        Restaurant rNonna = y.addRestaurant("Nonna Pie", "10 Main st",
                new String[]{"pizza", "family friendly"});

        String tOpenLate = "open late"; // TIP:  To save on typing, make variables for tags you use often
        Assert.assertTrue(y.lookupByTag(tOpenLate).contains(rMango));

        y.removeTag(tOpenLate, rMango, "randy", "They close at 5pm now??");

        Assert.assertFalse(y.lookupByTag(tOpenLate).contains(rMango));
    }
}
