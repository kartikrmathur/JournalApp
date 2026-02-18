package net.engineeringdigest.journalApp.dto;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * DTO for JournalEntry API responses.
 */
public class JournalEntryResponse {

    public static class IdObject {
        private long timestamp;
        private String date;

        public IdObject() {
        }

        public IdObject(long timestamp, String date) {
            this.timestamp = timestamp;
            this.date = date;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }

    private IdObject id;
    private String title;
    private String content;
    private LocalDateTime date;

    public JournalEntryResponse() {
    }

    public JournalEntryResponse(IdObject id, String title, String content, LocalDateTime date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public static JournalEntryResponse from(JournalEntry entry) {
        if (entry == null) {
            return null;
        }
        
        IdObject idObject = null;
        if (entry.getId() != null) {
            ObjectId objectId = entry.getId();
            // Extract timestamp from ObjectId (seconds since epoch)
            long timestamp = objectId.getTimestamp();
            // Convert to ISO 8601 format with UTC offset
            Instant instant = Instant.ofEpochSecond(timestamp);
            String dateStr = instant.atOffset(ZoneOffset.UTC)
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'+00:00'"));
            
            idObject = new IdObject(timestamp, dateStr);
        }
        
        return new JournalEntryResponse(
                idObject,
                entry.getTitle(),
                entry.getContent(),
                entry.getDate()
        );
    }

    public IdObject getId() {
        return id;
    }

    public void setId(IdObject id) {
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
