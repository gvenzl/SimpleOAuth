/*
 * Since: April 2022
 * Author: gvenzl
 * Name: Tests.java
 * Description:
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

import com.gvenzl.oauth.SimpleOAuth1;
import com.gvenzl.oauth.SimpleOAuth2;
import org.junit.Assert;
import org.junit.Test;

public class Tests {
    @Test
    public void test_OAuth1Constructor() {
        SimpleOAuth1 oAuth1 = new SimpleOAuth1("a", "b", "c", "d");
        Assert.assertNotNull(oAuth1.getOAuthHeader("POST", "www.example.com"));
    }

    @Test
    public void test_OAuth2Constructor() {
        SimpleOAuth2 oAuth2 = new SimpleOAuth2("a");
        Assert.assertEquals("a", oAuth2.getBearerToken());
        Assert.assertEquals("Bearer a", oAuth2.getBearerAuthString());
    }

}
