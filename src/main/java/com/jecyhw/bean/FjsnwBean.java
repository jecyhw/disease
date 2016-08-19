package com.jecyhw.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.awt.*;

/**
 * Created by jecyhw on 16-8-19.
 */
public class FjsnwBean {
    @JsonProperty("病害名称")
    private String diseaseName;
    @JsonProperty("危害植物")
    private String harmPlant;
    @JsonProperty("病因")
    private String diseaseReason;
    @JsonProperty("症状描述")
    private String diseaseDescription;
    @JsonProperty("防治方法")
    private String cureMethod;
    @JsonProperty("参考文献")
    private String reference;
    @JsonProperty("病害图片")
    private Image diseaseImage;

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getHarmPlant() {
        return harmPlant;
    }

    public void setHarmPlant(String harmPlant) {
        this.harmPlant = harmPlant;
    }

    public String getDiseaseReason() {
        return diseaseReason;
    }

    public void setDiseaseReason(String diseaseReason) {
        this.diseaseReason = diseaseReason;
    }

    public String getDiseaseDescription() {
        return diseaseDescription;
    }

    public void setDiseaseDescription(String diseaseDescription) {
        this.diseaseDescription = diseaseDescription;
    }

    public String getCureMethod() {
        return cureMethod;
    }

    public void setCureMethod(String cureMethod) {
        this.cureMethod = cureMethod;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Image getDiseaseImage() {
        return diseaseImage;
    }

    public void setDiseaseImage(Image diseaseImage) {
        this.diseaseImage = diseaseImage;
    }
}
