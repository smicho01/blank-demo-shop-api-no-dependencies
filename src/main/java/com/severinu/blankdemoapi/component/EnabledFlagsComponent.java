package com.severinu.blankdemoapi.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EnabledFlagsComponent {

    @Value("${serviceX.enabled}")
    private boolean serviceXEnabled;

    public EnabledFlagsComponent() {
        if(serviceXEnabled) {
            System.out.println("serviceX=Enabled");
        } else {
            System.out.println("serviceX=Disabled");
        }
    }

}
