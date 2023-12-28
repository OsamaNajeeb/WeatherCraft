package com.example.weathercraft.data;

public class APIData {


    public APIData() {
    }

    private String locationData, updateTimeData, statusData, tempData,
            minData, maxData, feelData, sunriseData, sunsetData, windSpeedData,
            pressureData, humidityData, seaLvlData;

    public APIData(String locationData, String updateTimeData, String statusData, String tempData,
                   String minData, String maxData, String feelData, String sunriseData,
                   String sunsetData, String windSpeedData, String pressureData,
                   String humidityData, String seaLvlData)
    {
        this.locationData = locationData;
        this.updateTimeData = updateTimeData;
        this.statusData = statusData;
        this.tempData = tempData;
        this.minData = minData;
        this.maxData = maxData;
        this.feelData = feelData;
        this.sunriseData = sunriseData;
        this.sunsetData = sunsetData;
        this.windSpeedData = windSpeedData;
        this.pressureData = pressureData;
        this.humidityData = humidityData;
        this.seaLvlData = seaLvlData;
    }

    public String getLocationData() {
        return locationData;
    }

    public String getUpdateTimeData() {
        return updateTimeData;
    }

    public String getStatusData() {
        return statusData;
    }

    public String getTempData() {
        return tempData;
    }

    public String getMinData() {
        return minData;
    }

    public String getMaxData() {
        return maxData;
    }

    public String getFeelData() {
        return feelData;
    }

    public String getSunriseData() {
        return sunriseData;
    }

    public String getSunsetData() {
        return sunsetData;
    }

    public String getWindSpeedData() {
        return windSpeedData;
    }

    public String getPressureData() {
        return pressureData;
    }

    public String getHumidityData() {
        return humidityData;
    }

    public String getSeaLvlData() {
        return seaLvlData;
    }
}