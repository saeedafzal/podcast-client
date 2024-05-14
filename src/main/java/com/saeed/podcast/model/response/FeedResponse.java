package com.saeed.podcast.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FeedResponse {
    private String title;
    private String description;
    private String imageUrl;
    private String imageAlt;
}
