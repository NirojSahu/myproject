//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utilities.WebKit.context;

import cucumber.runtime.java.guice.ScenarioScoped;

@ScenarioScoped
public class Device {
    public String type;

    public Device() {
    }

    public Device getDeviceType(String deviceType) {
        Device device = new Device();
        device.type = deviceType.equalsIgnoreCase(Device.Type.Mobile.toString()) ? Device.Type.Mobile.toString() : Device.Type.Desktop.toString();
        return device;
    }

    public static enum Type {
        Mobile,
        Desktop,
        API;

        private Type() {
        }
    }
}
