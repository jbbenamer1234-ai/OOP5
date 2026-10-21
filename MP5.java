import java.util.Scanner;

class LibraryException extends Exception {
    public LibraryException(String message) {
        super(message);
    }
}

class Book {
    private int id;
    private String title;
    private int availableCopies;
    private int borrowedCopies;

    public Book(int id, String title, int availableCopies) {
        this.id = id;
        this.title = title;
        this.availableCopies = availableCopies;
        this.borrowedCopies = 0;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public int getBorrowedCopies() {
        return borrowedCopies;
    }

    public void borrow() throws LibraryException {
        if (availableCopies <= 0) {
            throw new LibraryException("No copies available.");
        }

        availableCopies--;
        borrowedCopies++;
    }

    public void returnCopy() throws LibraryException {
        if (borrowedCopies <= 0) {
            throw new LibraryException("No borrowed copies to return.");
        }

        borrowedCopies--;
        availableCopies++;
    }

    public void display() {
        System.out.println("ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Available: " + availableCopies);
        System.out.println("Borrowed: " + borrowedCopies);
    }
}

public class MP5 {

    static Book[] books = {
        new Book(1, "Java Basics", 2),
        new Book(2, "Algorithms", 1),
        new Book(3, "Databases", 0)
    };

    public static Book findBook(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    public static void displayMenu() {
        System.out.println("\n=== Library Borrowing and Return System ===");
        System.out.println("1. Borrow");
        System.out.println("2. Return");
        System.out.println("3. Find");
        System.out.println("4. List");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            displayMenu();

            String choice = sc.nextLine();

            try {

                switch (choice) {

                    case "1":
                        System.out.print("Enter Book ID: ");
                        int borrowId = Integer.parseInt(sc.nextLine());

                        Book borrowBook = findBook(borrowId);

                        if (borrowBook == null) {
                            throw new LibraryException("Book not found.");
                        }

                        borrowBook.borrow();

                        System.out.println("Borrow successful.");
                        System.out.println("Available: " +
                                borrowBook.getAvailableCopies());
                        System.out.println("Borrowed: " +
                                borrowBook.getBorrowedCopies());
                        break;

                    case "2":
                        System.out.print("Enter Book ID: ");
                        int returnId = Integer.parseInt(sc.nextLine());

                        Book returnBook = findBook(returnId);

                        if (returnBook == null) {
                            throw new LibraryException("Book not found.");
                        }

                        returnBook.returnCopy();

                        System.out.println("Return successful.");
                        System.out.println("Available: " +
                                returnBook.getAvailableCopies());
                        System.out.println("Borrowed: " +
                                returnBook.getBorrowedCopies());
                        break;

                    case "3":
                        System.out.print("Enter Book ID: ");
                        int searchId = Integer.parseInt(sc.nextLine());

                        Book foundBook = findBook(searchId);

                        if (foundBook == null) {
                            throw new LibraryException("Book not found.");
                        }

                        foundBook.display();
                        break;

                    case "4":
                        for (Book book : books) {
                            System.out.println();
                            book.display();
                        }
                        break;

                    case "0":
                        System.out.println("Exiting program...");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid menu option.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: Enter a valid number.");
            } catch (LibraryException e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                System.out.println("Operation complete.");
            }
        }
    }
}