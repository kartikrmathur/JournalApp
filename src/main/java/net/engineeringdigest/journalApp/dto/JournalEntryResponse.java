package net.engineeringdigest.journalApp.dto;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

/**
 * DTO for JournalEntry API responses.
 */
public class JournalEntryResponse {

    private String id;
    private String title;
    private String content;
    private LocalDateTime date;

    public JournalEntryResponse() {
    }

    public JournalEntryResponse(String id, String title, String content, LocalDateTime date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public static JournalEntryResponse from(JournalEntry entry) {
        if (entry == null) {
            return null;
        }
        String idStr = entry.getId() != null ? entry.getId().toHexString() : null;
        return new JournalEntryResponse(
                idStr,
                entry.getTitle(),
                entry.getContent(),
                entry.getDate()
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
