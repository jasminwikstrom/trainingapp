package se.jasmin.exjobb.trainapp.api.dto;

public class AverageStat {
    private int startWeight;
    private int endWeight;
    private int average;

    public int getStartWeight() {
        return startWeight;
    }

    public void setStartWeight(int startWeight) {
        this.startWeight = startWeight;
    }

    public int getEndWeight() {
        return endWeight;
    }

    public void setEndWeight(int endWeight) {
        this.endWeight = endWeight;
    }

    public int getAverage() {
        return average;
    }

    public void setaverage(int average) {
        this.average = average;
    }
}
