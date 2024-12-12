package com.spring.app.mocks.instant;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

public class LocalDateTimeTest {
    
    @Test
    void localDateTimeTest() {
        LocalDateTime.now().compareTo(null);
        
    }
}
