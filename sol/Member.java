package sol;

import java.util.LinkedList;

/**
 * A class that represents Yulp members.
 */
public class Member {
    String username;
    LinkedList<Review> reviews;

    /**
     * Constructor for Member
     * @param username - username of member
     */
    public Member(String username) {
        this.username = username;
        this.reviews = new LinkedList<Review>();
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
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;

        Member member = (Member) o;
        return username.equals(member.username);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }
}
