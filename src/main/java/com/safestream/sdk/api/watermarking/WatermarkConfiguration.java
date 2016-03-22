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

/**
 * Defines the settings for a given watermark. At minimum this configuration requires "content", a string that will be watermarked onto the video.
 */
public class WatermarkConfiguration {

    /**
     * The text string to watermark onto the video
     */
    private String content;

    /**
     * Currently <code>TEXT</code> is support.
     */
    private WatermarkType type;

    /**
     * Specifies the horizontal anchor point on the watermark.
     *
     * A value of LEFT will anchor the watermark on it's right most point
     * A value of RIGHT will anchor the watermark on it's left most pixel
     * A value of CENTER will anchor the watermark in it's center most pixel
     */
    private WatermarkHorizontalAlignment horizontalAlignment;

    /**
     * Specifies the vertical anchor point on the watermark.
     *
     * A value of TOP will anchor the watermark on it's top most point
     * A value of MIDDLE will anchor the watermark on it's middle most pixel
     * A value of BOTTOM will anchor the watermark in it's bottom most pixel
     */
    private WatermarkVerticalAlignment verticalAlignment;


    /**
     * The relative x position of the anchor. The position is relative to the width of the video. So, a video with a width of 1080 and an x value of .5 will put the anchor point at 540 pixels. The anchor position is defined by the horizontal and vertical alignment.
     */
    private float x;

    /**
     * The relative y position of the anchor. The position is relative to the height of the video. So, a video with a height of 720 and an y value of .5 will put the anchor point at 360 pixels. The anchor position is defined by the horizontal and vertical alignment.
     */
    private float y;

    /**
     * Size of the watermark text relative to the height of the video. For example, a video with a height of 720 and a font size of .05 will produce a watermark with a text font size of 36
     */
    private float fontSize;

    /**
     * Values from 0 (totally transparent) to 1 (totally opaque)
     */
    private float fontOpacity;

    /**
     * Hex value of font color (ex 0xffffff)
     */
    private String fontColor;

    /**
     * Opacity of the drop shadow of the watermark text. 0 (totally transparent) to 1 (totally opaque)
     */
    private float shadowOpacity;

    /**
     * Hex value of watermark text drop shadow color (ex 0xffffff)
     */
    private String shadowColor;

    private float shadowOffsetX;
    private float shadowOffsetY;

    public WatermarkConfiguration() {
        this.type = WatermarkType.TEXT;
        this.x = 0.5F;
        this.y = 0.5F;
        this.fontSize = 0.05F;
        this.fontOpacity = 0.3F;
        this.fontColor = "0xFFFFFF";
        this.shadowOpacity = 0.1F;
        this.shadowColor = "0x000000";
        this.shadowOffsetX = 0.08F;
        this.shadowOffsetY = 0.08F;
        this.content = "";
        this.horizontalAlignment = WatermarkHorizontalAlignment.CENTER;
        this.verticalAlignment = WatermarkVerticalAlignment.MIDDLE;
        this.type = WatermarkType.TEXT;
    }

    /**
     * Fluent setter for content
     * @param content
     * @return this
     */
    public WatermarkConfiguration withContent(String content) {
        this.content = content;
        return this;
    }

    /**
     * Fluent setter for type
     * @param type
     * @return this
     */
    public WatermarkConfiguration withType(WatermarkType type) {
        this.type = type;
        return this;
    }

    /**
     * Fluent setter for x
     * @param x
     * @return this
     */
    public WatermarkConfiguration withX(Float x) {
        this.x = x;
        return this;
    }

    /**
     * Fluent setter for y
     * @param y
     * @return this
     */
    public WatermarkConfiguration withY(Float y) {
        this.y = y;
        return this;
    }

    /**
     * Fluent setter for fontSize
     * @param fontSize
     * @return this
     */
    public WatermarkConfiguration withFontSize(Float fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    /**
     * Fluent setter for fontOpacity
     * @param fontOpacity
     * @return this
     */
    public WatermarkConfiguration withFontOpacity(Float fontOpacity) {
        this.fontOpacity = fontOpacity;
        return this;
    }

    /**
     * Fluent setter for fontColor
     * @param fontColor
     * @return this
     */
    public WatermarkConfiguration withFontColor(String fontColor) {
        this.fontColor = fontColor;
        return this;
    }

    /**
     * Fluent setter for shadowOpacity
     * @param shadowOpacity
     * @return this
     */
    public WatermarkConfiguration withShadowOpacity(Float shadowOpacity) {
        this.shadowOpacity = shadowOpacity;
        return this;
    }

    /**
     * Fluent setter for shadowColor
     * @param shadowColor
     * @return this
     */
    public WatermarkConfiguration withShadowColor(String shadowColor) {
        this.shadowColor = shadowColor;
        return this;
    }

    /**
     * Fluent setter for shadowOffsetX
     * @param shadowOffsetX
     * @return this
     */
    public WatermarkConfiguration withShadowOffsetX(Float shadowOffsetX) {
        this.shadowOffsetX = shadowOffsetX;
        return this;
    }

    /**
     * Fluent setter for shadowOffsetY
     * @param shadowOffsetY
     * @return this
     */
    public WatermarkConfiguration withShadowOffsetY(Float shadowOffsetY) {
        this.shadowOffsetY = shadowOffsetY;
        return this;
    }

    /**
     * Fluent setter for horizontalAlignment
     * @param horizontalAlignment
     * @return this
     */
    public WatermarkConfiguration withHorizontalAlignment(WatermarkHorizontalAlignment horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
        return this;
    }

    /**
     * Fluent setter for verticalAlignment
     * @param verticalAlignment
     * @return this
     */
    public WatermarkConfiguration withVerticalAlignment(WatermarkVerticalAlignment verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
        return this;
    }

}
