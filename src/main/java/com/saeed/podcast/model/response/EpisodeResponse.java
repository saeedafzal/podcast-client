package com.saeed.podcast.model.response;

import com.saeed.podcast.model.Episode;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class EpisodeResponse {

    private String title;
    private String description;
    private Date pubDate;
    private String url;
    private String type;

    public static EpisodeResponse fromEpisode(Episode episode) {
        return EpisodeResponse.builder()
            .title(episode.getTitle())
            .description(episode.getDescription())
            .pubDate(episode.getPubDate())
            .url(episode.getUrl())
            .type(episode.getType())
            .build();
    }
}
