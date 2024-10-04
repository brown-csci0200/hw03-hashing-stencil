package sol;

import java.util.*;

/**
 * A class that represents Yulp, a restaurant review system.
 */
public class Yulp {
    // map usernames to member objects
    public HashMap<String, Member> allMembers;

    // collection of all restaurants
    public HashMap<String, Restaurant> allRestaurants;

    // map tag to set of restaurants with that tag
    public HashMap<String, LinkedList<Restaurant>> tagData;

    // map each restaurant to a map from tags to the history of modifications to the tag
    public HashMap<Restaurant, HashMap<String, LinkedList<String>>> tagHistory;

    /**
     * Constructor for Yulp.
     */
    public Yulp() {
        this.allMembers = new HashMap<String, Member>();
        this.allRestaurants = new HashMap<String, Restaurant>();
        this.tagData = new HashMap<String, LinkedList<Restaurant>>();
        this.tagHistory = new HashMap<Restaurant, HashMap<String, LinkedList<String>>>();
    }

    /**
     * Create a new Restaurant in the system, with a given set of initial tags
     *
     * @param name : the name of the restaurant
     * @param address : the address of the restaurant
     * @param tags : an array of the tags to put on the restaurant
     */
    public Restaurant addRestaurant(String name, String address, String[] tags) {

        // Create the object, add it to map of all Restaurants
        Restaurant r = new Restaurant(name, address, tags);
        this.allRestaurants.put(name, r);

        // Initialize the tag history
        HashMap<String, LinkedList<String>> history = new HashMap<String,LinkedList<String>>();
        this.tagHistory.put(r, history);

        for (String tag : tags) {
            // put the restaurant in the search data
            LinkedList<Restaurant> tagList = this.tagData.get(tag);
            if (tagList == null) {
                tagList = new LinkedList<Restaurant>();
                this.tagData.put(tag, tagList);
            }
            tagList.add(r);

            // Add entry to the history for this tag
            this.addToTagHistory(r, tag, "Added when Restaurant created");
        }
        return r;
    }

    /**
     * Create a new Member (user) in the system
     *
     * @param username : the username for the new Member
     */
    public void addMember(String username) {
        if (this.allMembers.containsKey(username)) {
            throw new RuntimeException("User " + username + " already exists");
        } else {
            this.allMembers.put(username, new Member(username));
        }
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
        if (m == null) {
            throw new IllegalArgumentException("unknown username " + byUsername);
        }

        m.addReview(newReview);
        toR.addReview(newReview);
    }

    /**
     * Retrieve all restaurants with a given tag
     *
     * @param tag : the tag for which to get matching restaurants
     * @return : the list of Restaurants with the given tag
     */
    public HashSet<Restaurant> lookupByTag(String tag) {
        HashSet<Restaurant> matches = new HashSet<Restaurant>();

        LinkedList<Restaurant> searchResults = this.tagData.get(tag);
        if (searchResults != null) {
            matches.addAll(searchResults);
        }

        return matches;
    }

    /**
     * Process a request to add a specific tag to a specific restaurant
     *
     * @param tag : the tag to add
     * @param toRestaurant : the Restaurant to which to add the tag
     * @param byUser : username of Member making the addition
     * @param reason : the Member's reason for adding the tag
     */
    public void addTag(String tag, Restaurant toRestaurant, String byUser, String reason) {
        if (!this.allRestaurants.containsKey(toRestaurant.name)) {
            throw new IllegalArgumentException("Unknown restaurant " + toRestaurant);
        }

        if (!this.tagData.containsKey(tag)) {
            this.tagData.put(tag, new LinkedList<>());
        }

        this.tagData.get(tag).add(toRestaurant);
        toRestaurant.addTag(tag);

        addToTagHistory(toRestaurant, tag, "User " + byUser +
                " added tag " + tag +
                ", Reason: " + reason);
    }

    /**
     * Process a request to remove a specific tag from a specific restaurant
     * @param tag : the tag to remove
     * @param toRestaurant : the Restaurant from which to remove the tag
     * @param byUser : username of Member making the removal
     * @param reason : the Member's reason for removing the tag
     */
    public void removeTag(String tag, Restaurant toRestaurant, String byUser, String reason) {
        if (!this.allRestaurants.containsKey(toRestaurant.name)) {
            throw new IllegalArgumentException("Unknown restaurant " + toRestaurant);
        }

        toRestaurant.removeTag(tag);

        if (this.tagData.containsKey(tag)) {
            this.tagData.get(tag).remove(toRestaurant);
        }

        addToTagHistory(toRestaurant, tag, "User " + byUser +
                " removed tag " + tag +
                ", Reason: " + reason);
    }


    /**
     * Private helper for updating the tag history
     * @param r Restaurant affected by tag update
     * @param tag The tag being updated
     * @param comment Comment from author on the change that was made
     */
    private void addToTagHistory(Restaurant r, String tag, String comment) {
        // Get per-tag history for this restaurant
        HashMap<String, LinkedList<String>> historyThisRestaurant = this.tagHistory.get(r);

        if (historyThisRestaurant == null) {
            throw new IllegalArgumentException("Restaurant " + r.name + " not found in tag history");
        }

        // If there's no list of comments for this tag yet, add a list for it
        if (!historyThisRestaurant.containsKey(tag)) {
            historyThisRestaurant.put(tag, new LinkedList<String>());
        }

        // Add a comment for this tag
        historyThisRestaurant.get(tag).addLast(comment);
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
