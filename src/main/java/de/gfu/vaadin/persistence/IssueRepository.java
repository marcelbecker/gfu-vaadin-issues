package de.gfu.vaadin.persistence;

import de.gfu.vaadin.model.Issue;

import java.util.List;
import java.util.function.Predicate;

import static de.gfu.vaadin.persistence.DatabaseProvider.databaseProviderInstance;
import static java.util.stream.Collectors.toList;

/**
 * Created by mbecker on 21.10.2016.
 */
public class IssueRepository {



    public static List<Issue> findIssuesBy(Predicate<Issue> filter) {
        return databaseProviderInstance().get().items().loadAll().stream()
                .filter(item -> item instanceof Issue)
                .map(item -> (Issue) item)
                .filter(filter)
                .collect(toList());
    }
}
