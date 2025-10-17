public class Book {
    protected String Title ;
    protected String Author ;
    protected double Price ;
    public Book(String title , String author, double price){
        this.Title = title;
        this.Author = author;
        this.Price = price;
    }
    public void displayInfo(){
        System.out.println(this.Title);
        System.out.println(this.Author);
        System.out.println(this.Price);
    }

}
