package com.example.demo.Configration;

import com.example.demo.Controller.websocketTest;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

/**
 * Created by forget on 2019/4/22.
 */
@Configurable
public class websocket {

    @Bean
    public websocketTest serverEndpointExporter() {
        return new websocketTest();
    }

}
