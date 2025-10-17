    import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
  //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
  // to see how IntelliJ IDEA suggests fixing it.
//  IO.println(String.format("Hello and welcome!"));

  Book book = new Book("Mahmoud", "Abdullah", 30000);
  book.displayInfo();
    EBook Ebook = new EBook("Mahmoud", "Abdullah", 30000, 25);
    Ebook.displayInfo();

    Shape circle = new Circle(5);
    circle.Area();
    circle.Perimeter();
    Engine engine = new Engine(500, "SUV");
    Car car = new Car("Porche","Macan", engine);
    car.showCar();


    Playble player;

    // Assign MusicPlayer object
    player = new MusicPlayer();
    player.play();
    player.stop();



    player = new VideoPlayer();
    player.play();
    player.stop();



    System.out.println("-----------------library-------------");
    ArrayList<Library> library = new ArrayList<>();

    library.add(new Book2(0,"mMM", "abbas",100));
    library.add(new Magzine(1, "Gamal", "Kamal", 230));
    library.add(new DVD(3, "Inception", "Fuck oofffff", 140221));

    for (Library item : library) {
        item.displayInfo();
    }

    library.get(0).borrow();
    library.get(1).borrow();
    library.get(0).borrow();
    library.get(0).returnItem();
    library.get(0).returnItem();

}
