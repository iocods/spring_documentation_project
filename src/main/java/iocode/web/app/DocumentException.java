package iocode.web.app;

class DocumentNotFoundException extends Exception {
    public DocumentNotFoundException() {
        super("Document not found");
    }
}

class DocumentListFullException extends Exception {
    public DocumentListFullException() {
        super("Document list is full");
    }

    public DocumentListFullException(String message) {
        super(message);
    }
}

class DocumentFormatNotAcceptedException extends Exception {
    public DocumentFormatNotAcceptedException() {}

    public DocumentFormatNotAcceptedException(String message) {
        super(message);
    }
}
