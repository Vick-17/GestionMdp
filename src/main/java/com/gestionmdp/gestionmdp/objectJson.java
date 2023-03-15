package com.gestionmdp.gestionmdp;

public class objectJson {
private String jsonMdp;
private String jsonMdpName;

    public objectJson(String jsonMdp, String jsonMdpName) {
        this.jsonMdp = jsonMdp;
        this.jsonMdpName = jsonMdpName;
    }

    public String getJsonMdp() {
        return jsonMdp;
    }

    public void setJsonMdp(String jsonMdp) {
        this.jsonMdp = jsonMdp;
    }

    public String getJsonMdpName() {
        return jsonMdpName;
    }

    public void setJsonMdpName(String jsonMdpName) {
        this.jsonMdpName = jsonMdpName;
    }
}
