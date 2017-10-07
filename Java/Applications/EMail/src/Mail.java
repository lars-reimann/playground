public final class Mail {
    private final String from;
    private final String subject;
    private final String text;
    private final String time;
    private final String to;
    
    // TODO
    public Mail(String mail) {
        this.from = "";
        this.to = "";
        this.time = "";
        this.subject = "";
        this.text = mail;
    }
    
    public Mail(String from, String to, String time, String subject, String text) {
        this.from = from;
        this.to = to;
        this.time = time;
        this.subject = subject;
        this.text = text;
    }
    
    public String getFrom() {
        return from;
    }
    
    public String getSubject() {
        return subject;
    }
    
    public String getText() {
        return text;
    }
    
    public String getTime() {
        return time;
    }
    
    public String getTo() {
        return to;
    }
}
