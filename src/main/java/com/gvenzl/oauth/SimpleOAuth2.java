/*
 * Since: April 2022
 * Author: gvenzl
 * Name: SimpleOAuth2.java
 * Description: A simple OAuth 2 client.
 *
 * Copyright 2022 Gerald Venzl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.gvenzl.oauth;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class SimpleOAuth2 {

    private String bearerToken;

    /**
     * Creates a new SimpleOAuth2 object.
     * @param apiKey the API key.
     * @param apiKeySecret the API key secret.
     * @param tokenRequestURL the URL to request the token from.
     * @throws IOException if an error occurred during retrieval of the token.
     * @throws InterruptedException if the request has been interrupted.
     */
    public SimpleOAuth2 (String apiKey, String apiKeySecret, String tokenRequestURL)
            throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(tokenRequestURL))
                .header("Authorization",
                        "Basic " + Base64.getEncoder().encodeToString((apiKey + ":" + apiKeySecret).getBytes()))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString("grant_type=client_credentials"))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 300) {
            throw new IOException(String.format("Cannot retrieve bearer token: %s", response.body()));
        }

        JSONObject responseBody = new JSONObject(response.body());

        if (responseBody.getString("token_type").equals("bearer")) {
            bearerToken = responseBody.getString("access_token");
        }

    }

    /**
     * Creates a new SimpleOAuth2 object.
     * @param bearerToken the bearer token for the OAuth2 authentication.
     */
    public SimpleOAuth2 (String bearerToken) {
        this.bearerToken = bearerToken;
    }

    /**
     * Retrieves the OAuth header for the request.
     * @return the OAuth header.
     */
    public String getOAuthHeader() {
        return String.format("Bearer %s", bearerToken);
    }

    /**
     * Returns the bearer token of the OAuth2 client.
     * @return the bearer token.
     */
    public String getBearerToken() {
        return bearerToken;
    }

    /**
     * Returns the Bearer authentication string.<br/>
     *    For example: "Bearer ........."
     * @return the Bearer authentication string.
     */
    public String getBearerAuthString() {
        return "Bearer " + bearerToken;
    }

    /**
     * Returns the Bearer authentication key and value.
     * @return the Bearer authentication key and value.
     */
    public Map<String, String> getBearerAuthentication() {
        HashMap<String, String> map = new HashMap<>();
        map.put("Authorization", getBearerAuthString());
        return map;
    }
}
