public class Magzine extends Library {
    private int issueNumber;

    public Magzine(int ID, String Title, String Author, int issueNumber) {
        super(ID, Title, Author);
        this.issueNumber = issueNumber;
    }

    @Override
    public void displayInfo() {
        System.out.println("Magazine ID: " + ID + ", Title: " + Title + ", Author: " + Author + ", Issue: " + issueNumber);
    }
}
