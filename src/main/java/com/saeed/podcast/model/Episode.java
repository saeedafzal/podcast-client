package com.saeed.podcast.model;

import com.rometools.rome.feed.synd.SyndEntry;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class Episode {

    private String title;
    private String description;
    private Date pubDate;
    private String url;
    private String type;

    public static Episode entryToEpisode(SyndEntry syndEntry) {
        return Episode.builder()
            .title(syndEntry.getTitle())
            .description(syndEntry.getDescription().getValue())
            .pubDate(new Date(syndEntry.getPublishedDate().getTime()))
            .url(syndEntry.getEnclosures().getFirst().getUrl())
            .type(syndEntry.getEnclosures().getFirst().getType())
            .build();
    }
}
