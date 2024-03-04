package sol;

import java.util.LinkedList;
import java.util.Arrays;

/**
 * A class that represents a restaurant.
 */
public class Restaurant {
    String name;
    String address;
    LinkedList<Review> reviews;
    LinkedList<String> tags;

    /**
     * Constructor for Restaurant
     * @param name - name of restaurant
     * @param address - address of restaurant
     * @param tags - associated tags
     */
    public Restaurant(String name, String address, String[] tags) {
        this.name = name;
        this.address = address;
        this.reviews = new LinkedList<Review>();
        this.tags = new LinkedList<String>(Arrays.asList(tags));
    }

    /**
     * Adds a review to this restaurant
     *
     * @param r: the review to add
     */
    public void addReview(Review r) {
        this.reviews.add(r);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Restaurant) {
            Restaurant otherR = ((Restaurant) other);
            return this.name.equals(otherR.name) &&
                    this.address.equals(otherR.address);
        } else return false;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() * 2 +  this.address.hashCode() * 3;
    }

    @Override
    public String toString() {
        return this.name + " @ " + this.address + " -- tags:" + this.tags.toString();
    }
}
