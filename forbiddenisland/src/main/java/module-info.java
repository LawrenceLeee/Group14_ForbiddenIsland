module edu.bdic.forbiddenisland {
    // JavaFX
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    // Netty
    requires io.netty.common;
    requires io.netty.buffer;
    requires io.netty.transport;
    requires io.netty.codec;
    requires io.netty.handler;

    // Jackson
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires java.smartcardio;

    // 导出顶层包给 javafx.graphics，使 Launcher 能访问 MainApp
    exports edu.bdic.forbiddenisland to javafx.graphics;

    // 其余子包导出（如需跨模块访问）
    exports edu.bdic.forbiddenisland.view;
    exports edu.bdic.forbiddenisland.controller;
    exports edu.bdic.forbiddenisland.controller.commands;
    exports edu.bdic.forbiddenisland.model;
    exports edu.bdic.forbiddenisland.network;
    exports edu.bdic.forbiddenisland.network.handler;

    // 让 FXML 反射访问控制器包
    opens edu.bdic.forbiddenisland.view to javafx.fxml;
    opens edu.bdic.forbiddenisland.controller to javafx.fxml;

    opens edu.bdic.forbiddenisland.network to com.fasterxml.jackson.databind;
    opens edu.bdic.forbiddenisland.network.handler to com.fasterxml.jackson.databind;
}
