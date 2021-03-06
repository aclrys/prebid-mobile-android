/*
 *    Copyright 2018-2019 Prebid.org, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.prebid.mobile;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.prebid.mobile.testutils.BaseSetup;
import org.prebid.mobile.testutils.MockPrebidServerResponses;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = BaseSetup.testSDK)
public class PrebidMobileTest extends BaseSetup {
    @Test
    public void testPrebidMobileSettings() {
        PrebidMobile.setPrebidServerAccountId("123456");
        assertEquals("123456", PrebidMobile.getPrebidServerAccountId());
        PrebidMobile.setTimeoutMillis(2500);
        assertEquals(2500, PrebidMobile.getTimeoutMillis());
        PrebidMobile.setApplicationContext(activity.getApplicationContext());
        assertEquals(activity.getApplicationContext(), PrebidMobile.getApplicationContext());
        PrebidMobile.setShareGeoLocation(true);
        assertTrue(PrebidMobile.isShareGeoLocation());
        PrebidMobile.setPrebidServerHost(Host.RUBICON);
        assertEquals(Host.RUBICON, PrebidMobile.getPrebidServerHost());
        PrebidMobile.setStoredAuctionResponse("111122223333");
        assertEquals("111122223333", PrebidMobile.getStoredAuctionResponse());
        PrebidMobile.addStoredBidResponse("appnexus", "221144");
        PrebidMobile.addStoredBidResponse("rubicon", "221155");
        assertFalse(PrebidMobile.getStoredBidResponses().isEmpty());
        PrebidMobile.clearStoredBidResponses();
        assertTrue(PrebidMobile.getStoredBidResponses().isEmpty());
        PrebidMobile.setPbsDebug(true);
        assertTrue(PrebidMobile.getPbsDebug());
    }

    @Test
    public void testSetCustomHeaders() {
        HashMap<String, String> customHeaders = new HashMap<>();
        customHeaders.put("key1", "value1");
        customHeaders.put("key2", "value2");
        PrebidMobile.setCustomHeaders(customHeaders);

        assertFalse(PrebidMobile.getCustomHeaders().isEmpty());
        assertEquals(2, PrebidMobile.getCustomHeaders().size());
    }
}
