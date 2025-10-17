public class MusicPlayer implements Playble {
    @Override
    public void play() {
        System.out.println("Playing music...");
    }


    @Override
    public void stop() {
        System.out.println("Music paused.");
    }
}