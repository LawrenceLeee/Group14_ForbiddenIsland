module team.group14.forbiddenisland {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.web;
    requires java.desktop;
    requires jdk.jsobject;
    requires static lombok;

    opens team.group14.forbiddenisland.service to javafx.fxml;
    exports team.group14.forbiddenisland.service;
    opens team.group14.forbiddenisland.controller to javafx.fxml;
    exports team.group14.forbiddenisland.controller;
    opens team.group14.forbiddenisland.view to javafx.fxml;
    exports team.group14.forbiddenisland.view;
    opens team.group14.forbiddenisland.model to javafx.fxml;
    exports team.group14.forbiddenisland.model;


}