package me.whiteship.refactoring._02_duplicated_code._06_pull_up_method;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ParticipantDashboard extends Dashboard {

    public void printUsernames(int eventId) throws IOException {
        // Get github issue to check homework
        super.printUsernames(eventId);
    }

}
