package me.tamsil.designpatterns._03_behavioral_patterns._16_iterator._01_before;

import java.time.LocalDateTime;

public class Post {
    private String title;
    private LocalDateTime createDateTime;

    public Post(String title) {
        this.title = title;
        this.createDateTime = LocalDateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }
}
