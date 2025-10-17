public class DVD extends Library {
    private int duration; // in minutes

    public DVD(int ID, String Title, String Author, int duration) {
        super(ID, Title, Author);
        this.duration = duration;
    }

    @Override
    public void displayInfo() {
        System.out.println("DVD ID: " + ID + ", Title: " + Title + ", Author: " + Author + ", Duration: " + duration + " mins");
    }
}
