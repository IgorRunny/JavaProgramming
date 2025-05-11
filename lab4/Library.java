package lab4;

import java.util.*;

public class Library {
    private List<Book> bookCollection;
    private Set<String> distinctAuthors;
    private Map<String, Integer> authorBookCount;

    public Library() {
        bookCollection = new ArrayList<>();
        distinctAuthors = new HashSet<>();
        authorBookCount = new HashMap<>();
    }

    public void addBook(Book book) {
        bookCollection.add(book);
        String author = book.getAuthorName();
        distinctAuthors.add(author);
        authorBookCount.put(author, authorBookCount.getOrDefault(author, 0) + 1);
    }

    public void removeBook(Book book) {
        bookCollection.remove(book);
        String author = book.getAuthorName();
        int currentCount = authorBookCount.getOrDefault(author, 0);
        if (currentCount <= 1) {
            authorBookCount.remove(author);
            distinctAuthors.remove(author);
        } else {
            authorBookCount.put(author, currentCount - 1);
        }
    }

    public List<Book> findBooksByAuthor(String authorName) {
        List<Book> result = new ArrayList<>();
        for (Book book : bookCollection) {
            if (book.getAuthorName().equals(authorName)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> findBooksByYear(int publicationYear) {
        List<Book> result = new ArrayList<>();
        for (Book book : bookCollection) {
            if (book.getPublicationYear() == publicationYear) {
                result.add(book);
            }
        }
        return result;
    }

    public void printAllBooks() {
        for (Book book : bookCollection) {
            System.out.println(book);
        }
    }

    public void printDistinctAuthors() {
        for (String author : distinctAuthors) {
            System.out.println(author);
        }
    }

    public void printAuthorStatistics() {
        for (String author : distinctAuthors) {
            System.out.println("Author: " + author + " | Number of books: " + authorBookCount.get(author));
        }
    }
}
