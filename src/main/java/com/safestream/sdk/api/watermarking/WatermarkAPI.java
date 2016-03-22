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

package com.safestream.sdk.api.watermarking;

import com.safestream.sdk.api.watermarking.encoding.EncodingConfiguration;
import com.safestream.sdk.http.SafeStreamHttpClient;
import com.safestream.sdk.http.SafeStreamHttpClientException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Watermark API provides support for adding a destructive watermark to videos. Additionally, it supports desaturation and resolution.
 */
public class WatermarkAPI {

    /**
     * Manages REST requests to the SafeStream HTTP API
     */
    private SafeStreamHttpClient safeStreamHttpClient;

    /**
     * The SafeStream REST endpoint for video requests
     */
    private String apiResourcePath = "watermark";

    /**
     * A SafeStreamHttpClient must be passed into the VideoAPI constructor. So, we disable the use of the default constructor.
     */
    protected WatermarkAPI() { }

    public WatermarkAPI(SafeStreamHttpClient safeStreamHttpClient) {
        this.safeStreamHttpClient = safeStreamHttpClient;
    }

    /**
     * Creates a watermarked version of an existing video with a single watermark
     *
     * This function will wait until the watermarking has completed before returning. This will block.
     *
     * @param watermarkConfiguration {@link WatermarkConfiguration}
     * @return A watermark result {@link WatermarkResult}
     * @throws WatermarkAPIException
     */
    public WatermarkResult create(String key, WatermarkConfiguration watermarkConfiguration) throws WatermarkAPIException {
        return create(key, Arrays.asList(watermarkConfiguration));
    }

    /**
     * Creates a watermarked version of an existing video with one or more watermarks
     *
     * This function will wait until the watermarking has completed before returning. This will block.
     *
     * @param watermarkConfigurations {@link WatermarkConfiguration}
     * @return A watermark result {@link WatermarkResult}
     * @throws WatermarkAPIException
     */
    public WatermarkResult create(String key, List<WatermarkConfiguration> watermarkConfigurations) throws WatermarkAPIException {
        return create(key, new EncodingConfiguration(watermarkConfigurations));
    }

    /**
     * Creates a watermarked version of an existing video with a single watermark
     *
     * This function will wait until the watermarking has completed before returning. This will block.
     *
     * @param watermarkConfiguration {@link WatermarkConfiguration}
     * @param timeout Time in millis to wait for watermarking to complete
     * @return A watermark result {@link WatermarkResult}
     * @throws WatermarkAPIException
     */
    public WatermarkResult create(String key, WatermarkConfiguration watermarkConfiguration, long timeout) throws WatermarkAPIException {
        return create(key, new EncodingConfiguration(watermarkConfiguration), timeout);
    }

    /**
     * Creates a watermarked version of an existing video with one or more watermarks
     *
     * This function will wait until the watermarking has completed before returning. This will block.
     *
     * @param watermarkConfigurations {@link WatermarkConfiguration}
     * @param timeout Time in millis to wait for watermarking to complete
     * @return A watermark result {@link WatermarkResult}
     * @throws WatermarkAPIException
     */
    public WatermarkResult create(String key, List<WatermarkConfiguration> watermarkConfigurations, long timeout) throws WatermarkAPIException {
        return create(key, new EncodingConfiguration(watermarkConfigurations), timeout);
    }

    /**
     * Creates a watermarked version of an existing video from an encoding configuration. Using the encoding configuration allows for changes to saturation and resolution as well as adding watermarks to a video.
     *
     * This function will wait until the watermarking has completed before returning. This will block.
     *
     * @param encodingConfiguration {@link EncodingConfiguration}
     * @return A watermark result {@link WatermarkResult}
     * @throws WatermarkAPIException
     */
    public WatermarkResult create(String key, EncodingConfiguration encodingConfiguration) throws WatermarkAPIException {
        return create(key, encodingConfiguration, 90000);
    }

    /**
     * Creates a watermarked version of an existing video with a single watermark.
     *
     * This function does not wait for watermarking to complete before returning.
     *
     * @param watermarkConfiguration {@link WatermarkConfiguration}
     * @return A watermark result {@link WatermarkResult}
     * @throws WatermarkAPIException
     */
    public WatermarkResult createAsync(String key, WatermarkConfiguration watermarkConfiguration) throws WatermarkAPIException {
        return createAsync(key, Arrays.asList(watermarkConfiguration));
    }

    /**
     * Creates a watermarked version of an existing video with one or more watermarks
     *
     * This function does not wait for watermarking to complete before returning.
     *
     * @param watermarkConfigurations {@link WatermarkConfiguration}
     * @return A watermark result {@link WatermarkResult}
     * @throws WatermarkAPIException
     */
    public WatermarkResult createAsync(String key, List<WatermarkConfiguration> watermarkConfigurations) throws WatermarkAPIException {
        return createAsync(key, new EncodingConfiguration(watermarkConfigurations));
    }

    /**
     * Creates a watermarked version of an existing video from an encoding configuration. Using the encoding configuration allows for changes to saturation and resolution as well as adding watermarks to a video.
     *
     * This function does not wait for watermarking to complete before returning.
     *
     * @param encodingConfiguration {@link EncodingConfiguration}
     * @return A watermark result {@link WatermarkResult}
     * @throws WatermarkAPIException
     */
    public WatermarkResult createAsync(String key, EncodingConfiguration encodingConfiguration) throws WatermarkAPIException {
        return create(key, encodingConfiguration, -1);
    }

    /**
     * Creates a watermarked version of an existing video from an encoding configuration. Using the encoding configuration allows for changes to saturation and resolution as well as adding watermarks to a video.
     *
     * This function allows you to specify the timeout for watermarking completion. Specifying -1 will result in no wait.
     *
     * @param encodingConfiguration {@link EncodingConfiguration}
     * @return A watermark result {@link WatermarkResult}
     * @throws WatermarkAPIException
     */
    public WatermarkResult create(String key, EncodingConfiguration encodingConfiguration, long timeout) throws WatermarkAPIException {
        try {
            // Make request to SafeStream REST API
            Map<String, Object> payload = new HashMap<String, Object>();
            payload.put("key", key);
            payload.put("settings", encodingConfiguration);
            WatermarkResult watermarkResult = safeStreamHttpClient.post(apiResourcePath, payload).getEntity(WatermarkResult.class);

            // TODO: Handle this on a separate thread so that it's not blocking
            if(timeout > -1 && !"READY".equals(watermarkResult.getStatus())) {
                long startTime = System.currentTimeMillis();
                boolean ready = false;
                while(!ready && (System.currentTimeMillis() - startTime) < timeout) {
                    WatermarkResult test = safeStreamHttpClient.getEndpoint(watermarkResult.getHref()).getEntity(WatermarkResult.class);
                    if("READY".equals(test.getStatus())) {
                        return test;
                    }

                    try {
                        Thread.sleep(1500);
                    } catch(InterruptedException e) {
                        throw new WatermarkAPIException("Thread interrupted while waiting for watermarking", e);
                    }
                }

                throw new WatermarkAPIException("Timeout reached waiting for video to be watermarked");

            } else {
                return watermarkResult;
            }
        } catch (SafeStreamHttpClientException e) {
            throw new WatermarkAPIException(e);
        }
    }

}
