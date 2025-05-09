package com.comitfy.kidefy.app.integration.rmq.message;

import lombok.Data;

@Data
public class GraphLogMessage {
    private int elektrikTuketimi;
    private int yagTuketimi;
    private int uretilenToplam;
    private String machineCode;
    private int login;
}
