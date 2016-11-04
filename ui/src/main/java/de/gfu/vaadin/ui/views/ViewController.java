package de.gfu.vaadin.ui.views;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.UI;

/**
 * Created by mbecker on 29.07.2016.
 */
public class ViewController {

    public static void registerViewsAt(Navigator navigator) {
        navigator.addView("", ShowIssueView.class);
        navigator.addView("editIssue", EditIssueView.class);
        navigator.addView("showIssue", ShowIssueView.class);
        navigator.addView("issues", IssuesView.class);
        navigator.setErrorView(ErrorView.class);
    }

    public static void showEditIssueView() {
        UI.getCurrent().getNavigator().navigateTo("editIssue");
    }

    public static void showEditIssueView(Integer issueId) {
        UI.getCurrent().getNavigator().navigateTo("editIssue/" + issueId);
    }

    public static void showShowIssueView(Integer issueId) {
        UI.getCurrent().getNavigator().navigateTo("showIssue/" + issueId);
    }

    public static void showShowIssueView() {
        UI.getCurrent().getNavigator().navigateTo("issues");
    }
}
