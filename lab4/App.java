package lab4;

import java.util.List;

public class App {
    public String getGreeting() {
        return "Hello, World!";
    }

    public static void main(String[] args) {
        Book[] sampleBooks = {
            new Book("book1", "author1", 1),
            new Book("book2", "author1", 2),
            new Book("book3", "author2", 2),
            new Book("book4", "author3", 5),
            new Book("book5", "author3", 5)
        };

        Library library = new Library();

        for (Book book : sampleBooks) {
            library.addBook(book);
        }

        System.out.println("All books:");
        library.printAllBooks();

        System.out.println("\nDistinct authors:");
        library.printDistinctAuthors();

        System.out.println("\nBooks per author:");
        library.printAuthorStatistics();

        System.out.println("\nBooks by 'author3':");
        List<Book> booksByAuthor3 = library.findBooksByAuthor("author3");
        for (Book book : booksByAuthor3) {
            System.out.println(book.getTitle());
        }

        Book bookToRemove = sampleBooks[3];
        library.removeBook(bookToRemove);

        System.out.println("\nBooks published in year 5:");
        List<Book> booksFromYear5 = library.findBooksByYear(5);
        for (Book book : booksFromYear5) {
            System.out.println(book.getTitle());
        }
    }
}
