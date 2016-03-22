
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

package com.safestream.sdk.api;

import com.safestream.sdk.api.video.VideoAPI;
import com.safestream.sdk.api.watermarking.WatermarkAPI;
import com.safestream.sdk.http.SafeStreamHttpClient;

public class SafeStreamAPI {

    private SafeStreamHttpClient safeStreamHttpClient;

    protected SafeStreamAPI() { }

    public SafeStreamAPI(String apiKey) {
        this.safeStreamHttpClient = new SafeStreamHttpClient(apiKey);
    }

    /**
     * The video API is for ingesting and managing videos in SafeStream.
     *
     * Ingest refers to any process that takes in video. For example, when creating a vide, you can provide a source URL which SafeStream will use to download from before encoding the video.
     * @return A VideoAPI object {@see VideoAPI}
     */
    public VideoAPI video() {
        return new VideoAPI(safeStreamHttpClient);
    }

    /**
     * The watermark API is for creating videos with watermarks, In addition to watermarking videos it also provides support for variable bit rate and desaturation
     *
     * @return A WatermarkAPI object {@see WatermarkAPI}
     */
    public WatermarkAPI watermark() {
        return new WatermarkAPI(safeStreamHttpClient);
    }
}
