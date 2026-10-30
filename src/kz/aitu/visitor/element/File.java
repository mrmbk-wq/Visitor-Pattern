package kz.aitu.visitor.element;

import kz.aitu.visitor.visitor.FileSystemVisitor;

public class File implements FileSystemElement {

    private final String name;
    private final long sizeBytes;

    public File(String name, long sizeBytes) {
        if (sizeBytes < 0) {
            throw new IllegalArgumentException("File size must not be negative, got " + sizeBytes);
        }
        this.name = name;
        this.sizeBytes = sizeBytes;
    }

    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visitFile(this);
    }

    @Override
    public String getName() {
        return name;
    }

    public long getSizeBytes() {
        return sizeBytes;
    }

    public String getExtension() {
        int dotIndex = name.lastIndexOf('.');
        return dotIndex < 0 ? "" : name.substring(dotIndex + 1);
    }
}
