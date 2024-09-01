package com.boozni.momento;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class EditorTest {

    private Editor editor;

    @BeforeEach
    public void init() {
        editor= new Editor();
    }

    @Test
    public void shouldRestorePreviousState () {
        History history = new History();

        editor.setContent("abc");
        history.push(editor.createState());

        editor.setContent("def");
        history.push(editor.createState());

        editor.setContent("ghi");
        editor.restoreState(history.pop());

        Assertions.assertEquals("def", editor.getContent());
    }

    @Test
    void testMultipleRestores() {
        Editor editor = new Editor();
        History history = new History();

        editor.setContent("Version 1");
        history.push(editor.createState());

        editor.setContent("Version 2");
        history.push(editor.createState());

        editor.setContent("Version 3");

        editor.restoreState(history.pop());
        Assertions.assertEquals("Version 2", editor.getContent());

        editor.restoreState(history.pop());
        Assertions.assertEquals("Version 1", editor.getContent());
    }

    @Test
    void testPopOnEmptyHistory() {
        History history = new History();
        Executable popOperation = history::pop;

        Assertions.assertThrows(IllegalStateException.class, popOperation);
    }
}