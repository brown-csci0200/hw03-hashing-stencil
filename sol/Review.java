package sol;

/**
 * A class that represents reviews.
 */
public class Review {
    int numStars; // int from 0 to 5
    String content;
    String byUsername;

    /**
     * Constructor for Review
     * @param numStars - star rating
     * @param content - content of review
     * @param byUsername - author of review
     */
    public Review(int numStars, String content, String byUsername) {
        if (numStars >=0 && numStars <= 5) {
            this.numStars = numStars;
        }
        else {
            throw new IllegalArgumentException("stars must be from 0 to 5");
        }
        this.content = content;
        this.byUsername = byUsername;
    }


}

