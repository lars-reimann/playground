public class Survey {
    private final Answer[] answers;
    private final String question;

    public Survey(String question, String[] texts) {
        this.question = question;
        this.answers = new Answer[texts.length];

        for (int i = 0; i < answers.length; i++) {
            answers[i] = new Answer(texts[i]);
        }
    }

    public Answer[] getAnswers() {
        return answers;
    }

    public String getQuestion() {
        return question;
    }
}