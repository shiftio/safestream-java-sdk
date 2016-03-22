/*
 * MIT License
 *
 * Copyright (c) 2016 MediaSilo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.safestream.sdk.api.video;

import com.google.gson.reflect.TypeToken;
import com.safestream.sdk.http.SafeStreamHttpClient;
import com.safestream.sdk.http.SafeStreamHttpClientException;
import org.apache.commons.validator.routines.UrlValidator;

import java.util.List;


/**
 * The Video API provides support for ingesting and managing videos within SafeStream.
 */
public class VideoAPI {

    /**
     * Manages REST requests to the SafeStream HTTP API
     */
    private SafeStreamHttpClient safeStreamHttpClient;

    /**
     * The SafeStream REST endpoint for video requests
     */
    private String apiResourcePath = "videos";

    /**
     * A SafeStreamHttpClient must be passed into the VideoAPI constructor. So, we disable the use of the default constructor.
     */
    protected VideoAPI() { }

    public VideoAPI(SafeStreamHttpClient safeStreamHttpClient) {
        this.safeStreamHttpClient = safeStreamHttpClient;
    }

    /**
     * Creates a new video in SafeStream. This will return the video before it has been fully ingested into SafeStream. The video cannot be watermarked until it has been ingested at which point the video status will be <code>INGESTED</code>
     * @param video {@link Video}
     * @return A new Video
     * @throws VideoAPIException
     */
    public Video create(Video video) throws VideoAPIException {
        return create(video, 0);
    }

    /**
     * Creates a new video allowing a specific timeout while waiting for the video to downloaded and encoded.
     *
     * This will block until either the video is ingested OR the timeout is reached.
     *
     * @param video {@link Video}
     * @param waitForIngest Time in millis to wait for the video to be ingested
     * @return The newly created video {@link Video}
     * @throws VideoAPIException
     */
    public Video create(Video video, long waitForIngest) throws VideoAPIException {

        // We need a source URL before we can ingest the video
        UrlValidator urlValidator = new UrlValidator(new String[]{"http", "https"});
        if(video.getSourceUrl() == null || video.getSourceUrl().isEmpty() || !urlValidator.isValid(video.getSourceUrl())) {
            throw new VideoAPIException("Invalid source URL. Cannot ingest video.");
        }

        final String ingestedStatus = "INGESTED";

        try {
            // Make the request to the SafeStream REST API
            Video videoResponse = safeStreamHttpClient.post(apiResourcePath, video).getEntity(Video.class);

            // Wait for the video to be ingested before returning
            // TODO: Have the ingest wait be on a separate thread
            if(waitForIngest > 0 && !ingestedStatus.equals(videoResponse.getStatus())) {
                long startTime = System.currentTimeMillis();
                boolean ingested = false;
                while(!ingested && (System.currentTimeMillis() - startTime) < waitForIngest) {
                    Video test = find(video.getKey());
                    if(ingestedStatus.equals(videoResponse.getStatus())) {
                        return test;
                    }

                    try {
                        Thread.sleep(1500);
                    } catch(InterruptedException e) {
                        throw new VideoAPIException("Thread interrupted while waiting for watermarking", e);
                    }
                }

                throw new VideoAPIException("Timeout reached waiting for video to be ingested");

            } else {
                return videoResponse;
            }
        } catch (SafeStreamHttpClientException e) {
            throw new VideoAPIException(e);
        }
    }

    /**
     * Gets an existing video by it's key.
     * @param key If no key was passed in when creating the video then the key will be the source URL of the video
     * @return An existing video {@link Video}
     * @throws VideoAPIException
     */
    public Video find(String key) throws VideoAPIException {
        if(key == null) {
            throw new VideoAPIException("A key is needed to fnd a video");
        }

        try {
            // Request the video from the SafeStream REST API
            List<Video> videos = safeStreamHttpClient.get(String.format("%s?key=%s", apiResourcePath, key)).getEntity(new TypeToken<List<Video>>(){}.getType());

            return videos.get(0);
        } catch (SafeStreamHttpClientException e) {
            throw new VideoAPIException(e);
        }
    }
}
