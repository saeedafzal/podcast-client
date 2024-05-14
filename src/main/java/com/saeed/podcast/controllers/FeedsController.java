package com.saeed.podcast.controllers;

import com.saeed.podcast.model.request.FeedRequest;
import com.saeed.podcast.model.response.FeedResponse;
import com.saeed.podcast.service.FeedsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feeds")
@Slf4j
@RequiredArgsConstructor
public class FeedsController {

    private final FeedsService feedsService;

    @PostMapping
    public FeedResponse addNewFeed(@RequestBody FeedRequest feedRequest) {
        log.info("Received request to add new feed: url={}", feedRequest);
        FeedResponse feedResponse = feedsService.addNewFeed(feedRequest.getUrl());
        log.info("Response: {}", feedResponse);
        return feedResponse;
    }

    @GetMapping
    public List<FeedResponse> getAllFeeds() {
        log.info("Received request to get all feeds.");
        return feedsService.getAllFeeds();
    }
}
