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

import java.util.Map;

public class SegmentedProxy {

    private ProxyType type;

    /**
     * The base URL for the un-watermarked proxy. SafeStream uses this URL to find the un-watermarked files so it can then use them to created watermarked versions
     */
    private String url;

    /**
     * SafeStream encoding requires the segment duration. This segment duration defines the duration for ALL segments in the proxy
     */
    private long segmentDuration;

    /**
     * The segment name format that SafeStream can use to locate each HLS segment in the base URL. Since we do not provide the specific URL to each segment and only provide the base URL to ALL segments
     */
    private String segmentNameFormat;

    /**
     * Specific segment duration overrides
     *
     * The following example overrides the duration of the 3rd segment to 5 seconds
     *
     * <code>
     *
     *     Map<String, Object> overrides - new HaspMap<String, Object>();
     *     overrides.put("3", 5.0);
     *
     * </code>
     */
    private Map<String, Object> segmentOverrides;

    /**
     * The total HLS segments in the proxy. Knowing the total number of segments allows SafeStream to locate the segments at the base URL by index.
     */
    private int segmentCount;

    public SegmentedProxy() { }

    public SegmentedProxy(ProxyType type, String url, long segmentDuration, String segmentNameFormat, Map<String, Object> segmentOverrides, int segmentCount) {
        this();
        this.type = type;
        this.url = url;
        this.segmentDuration = segmentDuration;
        this.segmentNameFormat = segmentNameFormat;
        this.segmentOverrides = segmentOverrides;
        this.segmentCount = segmentCount;
    }

    public SegmentedProxy(String url, long segmentDuration, String segmentNameFormat, Map<String, Object> segmentOverrides, int segmentCount) {
        this(ProxyType.HLSDEFAULT, url, segmentDuration, segmentNameFormat, segmentOverrides, segmentCount);
    }

    public ProxyType getType() {
        return type;
    }

    public void setType(ProxyType type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getSegmentDuration() {
        return segmentDuration;
    }

    public void setSegmentDuration(long segmentDuration) {
        this.segmentDuration = segmentDuration;
    }

    public String getSegmentNameFormat() {
        return segmentNameFormat;
    }

    public void setSegmentNameFormat(String segmentNameFormat) {
        this.segmentNameFormat = segmentNameFormat;
    }

    public Map<String, Object> getSegmentOverrides() {
        return segmentOverrides;
    }

    public void setSegmentOverrides(Map<String, Object> segmentOverrides) {
        this.segmentOverrides = segmentOverrides;
    }

    public int getSegmentCount() {
        return segmentCount;
    }

    public void setSegmentCount(int segmentCount) {
        this.segmentCount = segmentCount;
    }
}
