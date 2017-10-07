public class Answer {
    private int count;
    private final String text;

    public Answer(String text) {
        count = 0;
        this.text = text;
    }

    public int getCount() {
        return count;
    }

    public String getText() {
        return text;
    }

    public void increaseCount() {
        count++;
    }
}