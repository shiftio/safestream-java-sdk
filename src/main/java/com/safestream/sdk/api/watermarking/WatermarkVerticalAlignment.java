/*
 * MIT License
 *
 * Copyright (c) [year] [fullname]
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
 * Specifies the horizontal anchor point on the watermark.
 *
 * A value of TOP will anchor the watermark on it's top most point
 * A value of MIDDLE will anchor the watermark on it's middle most pixel
 * A value of BOTTOM will anchor the watermark in it's bottom most pixel
 */
public enum WatermarkVerticalAlignment {

    /**
     * Anchors the watermark on it's top most point
     */
    TOP,

    /**
     * Anchor the watermark on it's middle most point
     */
    MIDDLE,

    /**
     * anchor the watermark on it's bottom most point
     */
    BOTTOM;
}
