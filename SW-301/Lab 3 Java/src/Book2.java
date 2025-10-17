public class Book2 extends Library {
    private int pages;

    public Book2(int ID, String Title, String Author, int pages) {
        super(ID, Title, Author);
        this.pages = pages;
    }

    @Override
    public void displayInfo() {
        System.out.println("Book ID: " + ID + ", Title: " + Title + ", Author: " + Author + ", Pages: " + pages);
    }
}
