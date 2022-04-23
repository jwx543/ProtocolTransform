package com.nju.protocol.data;

public class DetectData {
    Double Temperature;
    Double Humidity;
    Double Light;
    Double CO2;
    Double HumidityRatio;
    Integer Occupancy;

    public DetectData() {
    }

    public DetectData(Double temperature, Double humidity, Double light, Double CO2, Double humidityRatio, Integer occupancy) {
        Temperature = temperature;
        Humidity = humidity;
        Light = light;
        this.CO2 = CO2;
        HumidityRatio = humidityRatio;
        Occupancy = occupancy;
    }

    public Double getTemperature() {
        return Temperature;
    }

    public void setTemperature(Double temperature) {
        Temperature = temperature;
    }

    public Double getHumidity() {
        return Humidity;
    }

    public void setHumidity(Double humidity) {
        Humidity = humidity;
    }

    public Double getLight() {
        return Light;
    }

    public void setLight(Double light) {
        Light = light;
    }

    public Double getCO2() {
        return CO2;
    }

    public void setCO2(Double CO2) {
        this.CO2 = CO2;
    }

    public Double getHumidityRatio() {
        return HumidityRatio;
    }

    public void setHumidityRatio(Double humidityRatio) {
        HumidityRatio = humidityRatio;
    }

    public Integer getOccupancy() {
        return Occupancy;
    }

    public void setOccupancy(Integer occupancy) {
        Occupancy = occupancy;
    }

    @Override
    public String toString() {
        return "detectData{" +
                "Temperature=" + Temperature +
                ", Humidity=" + Humidity +
                ", Light=" + Light +
                ", CO2=" + CO2 +
                ", HumidityRatio=" + HumidityRatio +
                ", Occupancy=" + Occupancy +
                '}';
    }
}
