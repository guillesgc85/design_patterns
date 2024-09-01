package com.boozni.momento;

import java.util.ArrayList;
import java.util.List;

public class History {
    private List<EditorState> states = new ArrayList<>();

    public void push(EditorState state) {
        states.add(state);
    }

    public EditorState pop(){
        if (states.isEmpty()) throw new IllegalStateException("No states to restore");
        int lastIndex = states.size() - 1;
        EditorState lastState = states.get(lastIndex);
        states.remove(lastIndex);
        return lastState;
    }
}
