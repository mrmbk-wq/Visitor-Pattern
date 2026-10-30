package kz.aitu.visitor.element;

import kz.aitu.visitor.visitor.FileSystemVisitor;

public interface FileSystemElement {

    void accept(FileSystemVisitor visitor);

    String getName();
}
