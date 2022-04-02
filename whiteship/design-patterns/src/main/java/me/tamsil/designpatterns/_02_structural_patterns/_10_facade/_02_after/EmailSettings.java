package me.tamsil.designpatterns._02_structural_patterns._10_facade._02_after;

public class EmailSettings {
    private String host;
    private String text;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
