import com.sun.xml.internal.ws.util.StringUtils;

import java.util.Arrays;

public enum DemoEnum {
    MUA_DONG("1"),
    MUA_HE("2");

    private final String code;

    DemoEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public DemoEnum choose(String code) {
        Arrays.stream(DemoEnum.values()).forEach(demoEnum -> {
            if (code.equals(demoEnum.getCode())) {
            }
        });
        return null;
    }
}
