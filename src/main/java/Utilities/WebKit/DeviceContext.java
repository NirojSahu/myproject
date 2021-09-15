//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.test.context;

import com.google.inject.Inject;
import com.test.context.Device.Type;
import cucumber.runtime.java.guice.ScenarioScoped;

@ScenarioScoped
public class DeviceContext {
    @Inject
    public Device device;

    public DeviceContext() {
    }

    public boolean isMobile() {
        return this.device != null && this.device.type != null ? this.device.type.equalsIgnoreCase(Type.Mobile.toString()) : false;
    }

    public boolean isAPI() {
        return this.device != null && this.device.type != null ? this.device.type.equalsIgnoreCase(Type.API.toString()) : false;
    }
}
