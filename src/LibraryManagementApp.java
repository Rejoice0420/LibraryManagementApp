import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Encapsulation: Using private fields with public getter and setter methods
class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isIssued;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isIssued = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void setIssued(boolean issued) {
        isIssued = issued;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", isIssued=" + isIssued +
                '}';
    }
}

// Inheritance: Staff inherits properties and methods of the User class
abstract class User {
    protected String name;
    protected String userId;

    public User(String name, String userId) {
        this.name = name;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public abstract void displayRole(); // Abstraction: Abstract method
}

class Student extends User {
    public Student(String name, String userId) {
        super(name, userId);
    }

    @Override
    public void displayRole() {
        System.out.println("Role: Student");
    }
}

class Librarian extends User {
    public Librarian(String name, String userId) {
        super(name, userId);
    }

    @Override
    public void displayRole() {
        System.out.println("Role: Librarian");
    }

    public void addBook(List<Book> books, Book book) {
        books.add(book);
        System.out.println("Book added successfully.");
    }

    public void removeBook(List<Book> books, String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
        System.out.println("Book removed successfully.");
    }
}

// Polymorphism: Dynamic method dispatch for User and its subclasses
public class LibraryManagementApp {
    private List<Book> books = new ArrayList<>();

    public void issueBook(String isbn, String userId) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn) && !book.isIssued()) {
                book.setIssued(true);
                System.out.println("Book issued to user: " + userId);
                return;
            }
        }
        System.out.println("Book not available or already issued.");
    }

    public void returnBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn) && book.isIssued()) {
                book.setIssued(false);
                System.out.println("Book returned successfully.");
                return;
            }
        }
        System.out.println("Invalid book ISBN or book was not issued.");
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public static void main(String[] args) {
        LibraryManagementApp libraryApp = new LibraryManagementApp();
        Librarian librarian = new Librarian("John Doe", "L001");

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Display Books");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter book ISBN: ");
                    String isbn = scanner.nextLine();
                    librarian.addBook(libraryApp.books, new Book(title, author, isbn));
                    break;
                case 2:
                    System.out.print("Enter book ISBN to remove: ");
                    String removeIsbn = scanner.nextLine();
                    librarian.removeBook(libraryApp.books, removeIsbn);
                    break;
                case 3:
                    System.out.print("Enter book ISBN to issue: ");
                    String issueIsbn = scanner.nextLine();
                    System.out.print("Enter user ID: ");
                    String userId = scanner.nextLine();
                    libraryApp.issueBook(issueIsbn, userId);
                    break;
                case 4:
                    System.out.print("Enter book ISBN to return: ");
                    String returnIsbn = scanner.nextLine();
                    libraryApp.returnBook(returnIsbn);
                    break;
                case 5:
                    libraryApp.displayBooks();
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
        System.out.println("Exiting Library Management System. Goodbye!");
    }
}
