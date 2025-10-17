public class Library {
    protected int ID;
    protected String Title;
    protected String Author;
    protected boolean borrowed;
    public Library(int id, String title, String author) {
        this.ID = id;
        this.Title = title;
        this.Author = author;
        this.borrowed = false;
    }
    public void borrow() {
        if (!borrowed) {
            borrowed = true;
            System.out.println(Title + " has been borrowed.");
        }
    }
    public void returnItem() {
        if (borrowed) {
            borrowed = false;
            System.out.println(Title + " has been returned.");
        }
    }

    // Method to be overridden by subclasses
    public void displayInfo() {
        System.out.println("ID: " + ID + ", Title: " + Title + ", Author: " + Author);
    }

}
