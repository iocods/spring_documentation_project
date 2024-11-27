package iocode.web.app;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.http.HttpStatus.ACCEPTED;

@RestController
public class DocumentController {
    Set<String> documentTypes = new HashSet<>(Set.of("PDF", "TXT", "EPUB", "XML"));
    Set<String> acceptableDocumentTypes = new HashSet<>(
        Set.of("PDF", "TXT", "EPUB", "XML", "RAR", "ZIP", "XHTML", "HTML", "JSON", "DOCX")
    );

    @GetMapping("/")
    public ResponseEntity<Set<String>> getDocuments() {
        return ResponseEntity.ok(documentTypes);
    }

    @PostMapping("/")
    @ResponseStatus(ACCEPTED)
    public void addDocument(@RequestBody String documentType) throws Exception {
        if(documentTypes.size() >= 10)
            throw new DocumentListFullException("Document List full additional document type is not allowed");
        if(!acceptableDocumentTypes.contains(documentType.toUpperCase()))
            throw new DocumentFormatNotAcceptedException("Provided Document is not accepted " + documentType.toUpperCase() + " List of acceptable document includes \n" + List.of(acceptableDocumentTypes));
        documentTypes.add(documentType);
    }

    @GetMapping("/{type}")
    public ResponseEntity<String> getDocumentType(@PathVariable String type) throws Exception {
        var docType = documentTypes.stream().filter(
                doc -> doc.equalsIgnoreCase(type)
        ).findFirst().orElseThrow(DocumentNotFoundException::new);
        return ResponseEntity.ok(docType);
    }

}
