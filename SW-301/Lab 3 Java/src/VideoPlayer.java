public class VideoPlayer implements Playble {
    @Override
    public void play() {
        System.out.println("Playing Video...");
    }


    @Override
    public void stop() {
        System.out.println("Video paused.");
    }
}