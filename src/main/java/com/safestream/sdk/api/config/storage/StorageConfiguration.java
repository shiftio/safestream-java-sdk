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

package com.safestream.sdk.api.config.storage;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides configuration for alternative storage services. This can be used to store videos and watermarked video proxies on a storage service other than the default SafeStream storage service.
 *
 * See StorageConfigurationType for a list of supported services {@link StorageConfigurationType}
 *
 */
public class StorageConfiguration {

    /**
     * The type of storage service adapter to use
     */
    StorageConfigurationType type;

    /**
     * The connection properties required for the storage configuration type
     */
    Map<String, Object> properties;

    public StorageConfiguration() {}

    public StorageConfiguration(StorageConfigurationType type, Map<String, Object> properties) {
        this.type = type;
        this.properties = properties;
    }

    public StorageConfigurationType getType() {
        return this.type;
    }

    public void setType(StorageConfigurationType type) {
        this.type = type;
    }

    public Map<String, Object> getProperties() {
        return this.properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public void addProperty(String k, Object v) {
        if(this.properties == null) {
            this.properties = new HashMap<String, Object>();
        }

        this.properties.put(k, v);
    }
}
