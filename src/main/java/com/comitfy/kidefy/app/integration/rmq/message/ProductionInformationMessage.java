package com.comitfy.kidefy.app.integration.rmq.message;

import lombok.Data;

@Data
public class ProductionInformationMessage {

    /*
    {"durusSuresi":"0","hataBaslamaZamani":"Byte[12]","uretilenToplam":"580"}
    */

    private int durusSuresi;

    private String hataBaslamaZamani;

    private String uretimBitisSaati;

    private int login;

    private int hurdaAdedi;

    private int durusaBagliHurda;

    private String machineCode;


    //hurda miktarı,
    //duruşa bağlı hurda,
    //hata başlama zamanı

}
