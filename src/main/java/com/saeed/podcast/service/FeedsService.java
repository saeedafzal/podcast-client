package com.saeed.podcast.service;

import com.rometools.rome.feed.synd.SyndFeed;
import com.saeed.podcast.RssXmlRepository;
import com.saeed.podcast.model.response.FeedResponse;
import com.saeed.podcast.repository.FeedDBRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedsService {

    private final RssXmlRepository rssXmlRepository;
    private final FeedDBRepository feedDBRepository;

    public FeedResponse addNewFeed(String url) {
        SyndFeed xml = rssXmlRepository.getRssXml(url);

        FeedResponse feedResponse = FeedResponse.builder()
            .title(xml.getTitle())
            .description(xml.getDescription())
            .imageUrl(xml.getImage().getUrl())
            .imageAlt(xml.getImage().getTitle())
            .build();

        feedDBRepository.storeFeed(feedResponse);

        return feedResponse;
    }

    public List<FeedResponse> getAllFeeds() {
        return feedDBRepository.getAllFeeds();
    }
}
