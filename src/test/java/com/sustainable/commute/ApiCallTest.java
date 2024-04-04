package com.sustainable.commute.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.sustainable.commute.controllers.ApiCall;
import com.sustainable.commute.services.ArrivalInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ApiCallTest {

    @InjectMocks
    private ApiCall apiCall;

    @Test
    public void testParseJson() {
        // Given
        String jsonResponse = "[{\"lineName\":\"TestLine\",\"platformName\":\"TestPlatform\",\"timeToStation\":300,\"expectedArrival\":\"2024-03-10T10:00:00Z\",\"destinationName\":\"TestDestination\",\"modeName\":\"TestMode\"}]";

        // When
        List<ArrivalInfo> arrivalInfoList = apiCall.parseJson(jsonResponse);

        // Then
        assertEquals(1, arrivalInfoList.size());
        ArrivalInfo arrivalInfo = arrivalInfoList.get(0);
        assertEquals("TestLine", arrivalInfo.getLineName());
        assertEquals("TestPlatform", arrivalInfo.getPlatformName());
        assertEquals("TestDestination", arrivalInfo.getDestinationName());
        assertEquals("TestMode", arrivalInfo.getModeName());
    }
}
