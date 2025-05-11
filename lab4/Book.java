package lab4;

public class Book {
    private String title = "";
    private String authorName = "";
    private int publicationYear = 0;

    public Book(String title, String authorName, int publicationYear) {
        this.title = title;
        this.authorName = authorName;
        this.publicationYear = publicationYear;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    @Override
    public String toString() {
        return "Book: " + title + ", Author: " + authorName + ", Year: " + publicationYear;
    }

    public boolean equals(Book otherBook) {
        if (otherBook == null) {
            return false;
        }
        return title.equals(otherBook.getTitle()) &&
               authorName.equals(otherBook.getAuthorName()) &&
               publicationYear == otherBook.getPublicationYear();
    }

    @Override
    public int hashCode() {
        int result = publicationYear;
        result += title.hashCode();
        result += authorName.hashCode();
        return result;
    }
}
