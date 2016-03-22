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

package com.safestream.sdk.api.watermarking.encoding;

import com.safestream.sdk.api.video.Resolution;
import com.safestream.sdk.api.watermarking.WatermarkConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The configuration for encoding and watermarking a video. This is used when requesting watermarks for a given video.
 */
public class EncodingConfiguration {

    /**
     * The level of saturation to be applied to the output
     *
     * Valid values are from 0 to 1
     */
    private Float saturation;

    /**
     * The target video resolution. See {@link Resolution}
     */
    private Resolution resolution;

    /**
     * The video bit rate in kilobits per second. For example, to create 4 Mb video the value would be 4000k
     */
    private String bitRate;

    /**
     * One or more watermarks that should be burned into the video
     */
    List<WatermarkConfiguration> watermarks;

    public EncodingConfiguration() {}

    public EncodingConfiguration(WatermarkConfiguration watermarkConfiguration) {
        this.watermarks = Arrays.asList(watermarkConfiguration);
    }

    public EncodingConfiguration(List<WatermarkConfiguration> watermarkConfigurations) {
        this.watermarks = watermarkConfigurations;
    }

    /**
     * Fluent setter for saturation
     * @param saturation
     * @return this
     */
    public EncodingConfiguration withSaturation(Float saturation) {
        this.saturation = saturation;
        return this;
    }

    /**
     * Fluent setter for resolution
     * @param resolution
     * @return this
     */
    public EncodingConfiguration withResoluation(Resolution resolution) {
        this.resolution = resolution;
        return this;
    }

    /**
     * Fluent setter for bitRate
     * @param bitRate
     * @return this
     */
    public EncodingConfiguration withBitRate(String bitRate) {
        this.bitRate = bitRate;
        return this;
    }

    /**
     * Fluent setter for watermark configurations
     * @param watermarkConfiguration
     * @return this
     */
    public EncodingConfiguration withWatermark(WatermarkConfiguration watermarkConfiguration) {
        if(this.watermarks == null) {
            this.watermarks = new ArrayList<WatermarkConfiguration>();
        }

        this.watermarks.add(watermarkConfiguration);
        return this;
    }
}
