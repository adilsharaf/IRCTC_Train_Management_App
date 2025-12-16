package com.tcs.dto;

public class TrainDTO {

    private Long trainId;
    private String source;
    private String destination;
    private Double acFare;
    private Double nonAcFare;
    private Double sleeperFare;

  
    public TrainDTO(Long trainId, String source, String destination, Double acFare, Double nonAcFare, Double sleeperFare) {
        this.trainId = trainId;
        this.source = source;
        this.destination = destination;
        this.acFare = acFare;
        this.nonAcFare = nonAcFare;
        this.sleeperFare = sleeperFare;
    }

 
    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Double getAcFare() {
        return acFare;
    }

    public void setAcFare(Double acFare) {
        this.acFare = acFare;
    }

    public Double getNonAcFare() {
        return nonAcFare;
    }

    public void setNonAcFare(Double nonAcFare) {
        this.nonAcFare = nonAcFare;
    }

    public Double getSleeperFare() {
        return sleeperFare;
    }

    public void setSleeperFare(Double sleeperFare) {
        this.sleeperFare = sleeperFare;
    }
}

