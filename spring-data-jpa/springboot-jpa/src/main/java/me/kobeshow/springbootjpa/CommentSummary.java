package me.kobeshow.springbootjpa;

import org.springframework.beans.factory.annotation.Value;

public interface CommentSummary {
    String getComment();
    int getUp();
    int getDown();

    default String getVotes() {
        return getUp() + " " + getDown();
    }
}
