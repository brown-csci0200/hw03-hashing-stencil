package sol;

import java.util.LinkedList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * A class that represents Yulp, a restaurant review system.
 */
public class Yulp {
    // map usernames to member objects
    HashMap<String, Member> allMembers;
    // collection of all restaurants
    HashSet<Restaurant> allRestaurants;
    // map tag to list of restaurants with that tag
    HashMap<String, LinkedList<Restaurant>> tagData;
    // map each restaurant to a map from tags to the history of modifications to the tag
    HashMap<Restaurant, HashMap<String, LinkedList<String>>> taghistory;

    /**
     * Constructor for Yulp.
     */
    public Yulp() {
        this.allMembers = new HashMap<String, Member>();
        this.allRestaurants = new HashSet<Restaurant>();
        this.tagData = new HashMap<String, LinkedList<Restaurant>>();
        this.taghistory = new HashMap<Restaurant, HashMap<String, LinkedList<String>>>();
    }

    /**
     * Create a new Restaurant in the system, with a given set of initial tags
     *
     * @param name : the name of the restaurant
     * @param address : the address of the restaurant
     * @param tags : an array of the tags to put on the restaurant
     */
    public Restaurant addRestaurant(String name, String address, String[] tags) {
        int index;

        // create the object
        Restaurant r = new Restaurant(name, address, tags);
        this.allRestaurants.add(r);
        // initialize the tag history
        HashMap<String, LinkedList<String>> history = new HashMap<String,LinkedList<String>>();
        this.taghistory.put(r, history);
        for(index=0; index < tags.length; index++) {
            // put the restaurant in the search data
            LinkedList<Restaurant> tagList = this.tagData.get(tags[index]);
            if (tagList == null) {
                tagList = new LinkedList<Restaurant>();
                this.tagData.put(tags[index], tagList);
            }
            tagList.add(r);
            // enter tag history
            LinkedList<String> historyComments = new LinkedList<String>();
            historyComments.add("initial tag on creation");
            history.put(tags[index], historyComments);
        }
        return r;
    }

    /**
     * Create a new Member (user) in the system
     *
     * @param username : the username for the new Member
     */
    public void addMember(String username) {
        if (this.allMembers.containsKey(username))
            throw new RuntimeException();
        else { this.allMembers.put(username, new Member(username)); }
    }

    /**
     * Create a new Review in the system
     *
     * @param toR : the restaurant being reviewed
     * @param stars : the stars for the review
     * @param comment : the comment for the review
     * @param byUsername : the username of the member leaving the review
     */
    public void addReview(Restaurant toR, int stars, String comment, String byUsername) {
        Review newReview = new Review(stars, comment, byUsername);
        Member m = this.allMembers.get(byUsername);
        if (m == null)
            throw new IllegalArgumentException("unknown username " + byUsername);
        m.addReview(newReview);
        toR.addReview(newReview);
    }

    /**
     * Retrieve all restaurants with a given tag
     *
     * @param tag : the tag for which to get matching restaurants
     * @return : the list of Restaurants with the given tag
     */
    public LinkedList<Restaurant> lookupByTag(String tag) {
        LinkedList<Restaurant> matches = this.tagData.get(tag);
        if (matches == null)
            return new LinkedList<Restaurant>();
        else
            return matches;
    }

    /**
     * Process a request to add a specific tag to a specific restaurant
     *
     * @param tag : the tag to add
     * @param toRestaurant : the Restaurant to which to add the tag
     * @param byUser : username of Member making the addition
     * @param justification : the Member's justification for adding the tag
     */
    public void addTag(String tag, Restaurant toRestaurant, String byUser, String justification) {
        // your implementation here
    }

    /**
     * Process a request to remove a specific tag from a specific restaurant
     * @param tag : the tag to remove
     * @param toRestaurant : the Restaurant from which to remove the tag
     * @param byUser : username of Member making the removal
     * @param justification : the Member's justification for removing the tag
     */
    public void remTag(String tag, Restaurant toRestaurant, String byUser, String justification) {
        // your implementation here
    }

    /**
     * Main method.
     * @param args - arguments for program
     */
    public static void main(String[] args) {
        Yulp y = new Yulp();

        // add two restaurants
        Restaurant bar = y.addRestaurant("Mango", "22 15th st",
                new String[]{"bar", "open late", "pizza"});
        Restaurant pizza = y.addRestaurant("Nonna Pie", "10 Main st",
                  new String[]{"pizza", "family friendly"});

        // print to inspect
        System.out.println(pizza);
        System.out.println(y.lookupByTag("pizza"));
        System.out.println(y.lookupByTag("vegetarian"));

        // what an addTag call looks like
        y.addTag("healthy", bar, "ted", "serves salad");
        System.out.println(bar);
    }
}
