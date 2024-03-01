public class ReviewBuilder {

    private String text;
    private int rating;
    private String date;
    private String author;

    public ReviewBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public ReviewBuilder setRating(int rating) {
        this.rating = rating;
        return this;
    }

    public ReviewBuilder setDate(String date) {
        this.date = date;
        return this;
    }

    public ReviewBuilder setAuthor(String author) {
        this.author = author;
        return this;
    }

    public Review build() {
        return new Review(text, rating);
    }
}
