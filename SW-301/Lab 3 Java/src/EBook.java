public class EBook extends Book{
        protected double size;
        public EBook(String title, String author, double price,double size){
            super(title, author, price);
            this.size = size;
        }
        @Override
        public void displayInfo(){
            super.displayInfo();
            System.out.println("Downloading.........................\n");
            System.out.println(this.size + " " + this.Title + " " + this.Author + " " + this.Price);
        }

}
