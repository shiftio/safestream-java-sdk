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

import com.safestream.sdk.api.config.VideoConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Video {

    /**
     * The unique identifier for the video
     */
    private String id;

    /**
     * Videos exist with a specific context. This will typically be the account ID
     */
    private String scope;

    /**
     * This can be an external key of any string value. If no value is given when the video is created then the key will be the source URL.
     */
    private String key;

    /**
     * An optional descriptive name for a video
     */
    private String name;

    /**
     * The URL where the video source exists at the time of creating this video. Currently, http and https URLs are supported
     */
    private String sourceUrl;

    /**
     * Target bit rate in kilobits. For example to create a 4Mb proxy this value would be 4000k.
     */
    private String targetBitRate;

    /**
     * A list of tags that identify characterize this video
     */
    private List<String> tags = new ArrayList<String>();

    /**
     * If we should use signed URLs for access tto he watermarked segments and M3U8 of this videos watermarked versions
     */
    private boolean allowHmacAuth = true;

    /**
     * If we should encrypt the watermarked segments of this video at rest
     */
    private boolean encrypt = true;

    /**
     * The unwatermarked HLS proxies for this video.
     */
    private List<SegmentedProxy> proxies = new ArrayList<SegmentedProxy>();

    /**
     * An optional configuration that overrides default account configurations and system configurations for storing this video and it's watermarked proxies
     */
    private VideoConfiguration config;

    /**
     * The ingest status of this video <code>PENDING, INGESTED</code>
     *
     * Videos can only be watermarked that are in the <code>INGESTED</code> status
     */
    private String status;

    /**
     * Epoch timestamp of the creation of this video
     */
    private long created;

    /**
     * ID of the user who created this video
     */
    private String createdBy;

    /**
     * Fluent setter for the property key
     */
    public Video withKey(String key) {
        this.key = key;
        return this;
    }

    /**
     * Fluent setter for the property name
     */
    public Video withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Fluent setter for the property sourceUrl
     */
    public Video withSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
        return this;
    }

    /**
     * Fluent setter for the property tags
     */
    public Video withTag(String tag) {
        if (this.tags == null) {
            this.tags = new ArrayList<String>();
        }
        this.tags.add(tag);
        return this;
    }

    /**
     * Fluent setter for the property tags
     */
    public Video withTags(List<String> _tags) {
        if (this.tags == null) {
            this.tags = new ArrayList<String>();
        }

        for (String tag : tags) {
            this.tags.add(tag);
        }

        return this;
    }

    /**
     * Fluent setter for the property tags
     */
    public Video withTags(String[] _tags) {
        return withTags(Arrays.asList(_tags));
    }

    /**
     * Fluent setter for the property allowHmacAuth
     */
    public Video withHMACAuth() {
        this.allowHmacAuth = true;
        return this;
    }

    /**
     * Fluent setter for the property allowHmacAuth
     */
    public Video withoutHMACAuth() {
        this.allowHmacAuth = false;
        return this;
    }

    /**
     * Fluent setter for the property encrypt
     */
    public Video withEncryption() {
        this.encrypt = true;
        return this;
    }

    /**
     * Fluent setter for the property encrypt
     */
    public Video withoutEncryption() {
        this.encrypt = false;
        return this;
    }

    /**
     * Fluent setter for the property proxies
     */
    public Video withExistingProxy(SegmentedProxy segmentedProxy) {
        if(this.proxies == null) {
            this.proxies = new ArrayList<SegmentedProxy>();
        }
        this.proxies.add(segmentedProxy);

        return this;
    }

    public String getId() {
        return id;
    }

    public String getScope() {
        return scope;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public String getTargetBitRate() {
        return targetBitRate;
    }

    public List<String> getTags() {
        return tags;
    }

    public boolean isAllowHmacAuth() {
        return allowHmacAuth;
    }

    public boolean isEncrypt() {
        return encrypt;
    }

    public List<SegmentedProxy> getProxies() {
        return proxies;
    }

    public VideoConfiguration getConfig() {
        return config;
    }

    public String getStatus() {
        return status;
    }

    public long getCreated() {
        return created;
    }

    public String getCreatedBy() {
        return createdBy;
    }
}
