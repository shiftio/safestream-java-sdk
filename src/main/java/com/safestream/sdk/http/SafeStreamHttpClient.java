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

package com.safestream.sdk.http;

import com.google.gson.GsonBuilder;
import com.safestream.sdk.Protocol;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class SafeStreamHttpClient {

    /**
     * HTTP, HTTPS
     */
    private Protocol protocol;

    /**
     * SafeStream api hostname
     */
    private String hostName;

    /**
     * SafeStream API version
     */
    private String version;

    /**
     * SafeStream API key. This is required in order to retrieve an auth token and make ANY subsequent requests to the SafeStream API
     */
    private String apiKey;

    private static String authToken;

    protected SafeStreamHttpClient() {
        this.protocol = Protocol.HTTP;
        this.hostName = "api.safestream.com";
        this.version = "0.1";
    }

    public SafeStreamHttpClient(String apiKey) {
        this();
        this.apiKey = apiKey;
    }

    /**
     * HTTP post to a given SafeStream resource
     * @param resource The SafeStream resource. This is the relative API path. For example, "videos" or "watermark"
     * @param body An optional payload
     * @param httpClient
     * @return A response object
     * @throws SafeStreamHttpClientException
     */
    public SafeStreamHttpResponse post(String resource, String body, CloseableHttpClient httpClient) throws SafeStreamHttpClientException {

        return postEndpoint(getResourceUrl(resource), body, httpClient);
    }

    /**
     * HTTP post to a given SafeStream resource
     * @param resource The SafeStream resource. This is the relative API path. For example, "videos" or "watermark"
     * @param body An optional payload
     * @return A response object
     * @throws SafeStreamHttpClientException
     */
    public SafeStreamHttpResponse post(String resource, String body) throws SafeStreamHttpClientException {
        return post(resource, body, HttpClients.createDefault());
    }

    /**
     * HTTP post to a given SafeStream resource
     * @param resource The SafeStream resource. This is the relative API path. For example, "videos" or "watermark"
     * @param body An optional payload
     * @return A response object
     * @throws SafeStreamHttpClientException
     */
    public SafeStreamHttpResponse post(String resource, Object body) throws SafeStreamHttpClientException {
        return post(resource, new GsonBuilder().create().toJson(body), HttpClients.createDefault());
    }

    /**
     * HTTP post to a given URL
     * @param url The full URL for the request
     * @param body An optional payload
     * @param httpClient
     * @return A response object
     * @throws SafeStreamHttpClientException
     */
    public SafeStreamHttpResponse postEndpoint(String url, String body, CloseableHttpClient httpClient) throws SafeStreamHttpClientException {

        try {
            HttpPost httppost = new HttpPost(url);

            // Bearer authorization header for JWT
            httppost.addHeader("Authorization", String.format("Bearer %s", getAuthToken()));

            // SafeStream communicates in JSON
            httppost.addHeader("Content-Type", "application/json");
            if (body != null) {
                httppost.setEntity(new StringEntity(body));
            }

            CloseableHttpResponse response = httpClient.execute(httppost);
            return handleResponse(response);
        } catch (IOException e) {
            throw new SafeStreamHttpClientException(e);
        } finally {
            tryCloseHttpClient(httpClient);
        }

    }

    /**
     * HTTP get from a given SafeStream resource
     * @param resource The SafeStream resource. This is the relative API path. For example, "videos" or "watermark"
     * @param httpClient
     * @return A response object
     * @throws SafeStreamHttpClientException
     */
    public SafeStreamHttpResponse get(String resource, CloseableHttpClient httpClient) throws SafeStreamHttpClientException {
        return getEndpoint(getResourceUrl(resource), httpClient);
    }

    /**
     * HTTP get from a given SafeStream resource
     * @param url The full URL for the request
     * @return A response object
     * @throws SafeStreamHttpClientException
     */
    public SafeStreamHttpResponse get(String url) throws SafeStreamHttpClientException {
        return get(url, HttpClients.createDefault());
    }

    /**
     * HTTP get from a given URL
     * @param url The full URL for the request
     * @return A response object
     * @throws SafeStreamHttpClientException
     */
    public SafeStreamHttpResponse getEndpoint(String url) throws SafeStreamHttpClientException {
        return getEndpoint(url, HttpClients.createDefault());
    }

    /**
     * HTTP get from a given URL
     * @param url The full URL for the request
     * @param httpClient
     * @return A response object
     * @throws SafeStreamHttpClientException
     */
    public SafeStreamHttpResponse getEndpoint(String url, CloseableHttpClient httpClient) throws SafeStreamHttpClientException {

        try {
            HttpGet httpget = new HttpGet(url);

            // Bearer authorization header for JWT
            httpget.addHeader("Authorization", String.format("Bearer %s", getAuthToken()));
            CloseableHttpResponse response = httpClient.execute(httpget);
            return handleResponse(response);
        } catch(IOException e) {
            throw new SafeStreamHttpClientException(e);
        } finally {
            tryCloseHttpClient(httpClient);
        }

    }

    private SafeStreamHttpResponse handleResponse(CloseableHttpResponse response) throws SafeStreamHttpClientException {
        try {
            SafeStreamHttpResponse safeStreamHttpResponse = new SafeStreamHttpResponse(response.getStatusLine().getStatusCode());

            // Get hold of the response entity
            HttpEntity entity = response.getEntity();

            // If the response does not enclose an entity, there is no need
            // to bother about connection release
            if (entity != null) {
                InputStream responseContent = entity.getContent();
                try {
                    safeStreamHttpResponse.setBody(IOUtils.toString(responseContent));
                    // do something useful with the response
                } catch (IOException e) {
                    throw new SafeStreamHttpClientException(e);
                } finally {
                    // Closing the input stream will trigger connection release
                    responseContent.close();
                }
            }

            if(safeStreamHttpResponse.getHttpStatus() >= 400) {
                throw new SafeStreamHttpClientException(String.format("Received %s response: %s", safeStreamHttpResponse.getHttpStatus(), safeStreamHttpResponse.getBody()));
            }

            return safeStreamHttpResponse;
        } catch(IOException e) {
            throw new SafeStreamHttpClientException(e);
        } finally {
            tryCloseHttpResponse(response);
        }
    }

    private void tryCloseHttpResponse(CloseableHttpResponse httpResponse) throws SafeStreamHttpClientException{
        try {
            httpResponse.close();
        } catch (IOException e) {
            throw new SafeStreamHttpClientException(e);
        }
    }

    private void tryCloseHttpClient(CloseableHttpClient httpClient) throws SafeStreamHttpClientException{
        try {
            httpClient.close();
        } catch (IOException e) {
            throw new SafeStreamHttpClientException(e);
        }
    }

    private String getAuthToken() throws SafeStreamHttpClientException {

        if(SafeStreamHttpClient.authToken == null) {
            CloseableHttpClient httpClient = HttpClients.createDefault();

            try {
                HttpPost httppost = new HttpPost(getResourceUrl("token"));
                httppost.addHeader("x-api-key", this.apiKey);
                CloseableHttpResponse response = httpClient.execute(httppost);
                String responseBody = handleResponse(response).getBody();
                HashMap responseMap = new GsonBuilder().create().fromJson(responseBody, HashMap.class);
                SafeStreamHttpClient.authToken = responseMap.get("token").toString();
                return SafeStreamHttpClient.authToken;
            } catch (IOException e) {
                throw new SafeStreamHttpClientException(e);
            } finally {
                tryCloseHttpClient(httpClient);
            }
        } else {
            return SafeStreamHttpClient.authToken;
        }
    }

    private String getRootUrl() {
        return String.format("%s://%s/%s/", this.protocol, this.hostName, this.version);
    }

    private String getResourceUrl(String resource) {
        return String.format("%s%s", getRootUrl(), resource);
    }

}
