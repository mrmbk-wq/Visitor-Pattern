package kz.aitu.visitor.element;

import kz.aitu.visitor.visitor.FileSystemVisitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Directory implements FileSystemElement {

    private final String name;
    private final List<FileSystemElement> children = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    public Directory add(FileSystemElement child) {
        children.add(child);
        return this;
    }

    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visitDirectory(this);
    }

    @Override
    public String getName() {
        return name;
    }

    public List<FileSystemElement> getChildren() {
        return Collections.unmodifiableList(children);
    }
}
