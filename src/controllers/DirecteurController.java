package controllers;

import dao.VoitureImpl;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import models.Diagram;

public class DirecteurController {
	VoitureImpl v=new VoitureImpl();
	Diagram d;
    @FXML
    private PieChart pLocation;
    
    public void initialize() {
    	v.listD().stream().forEach(x->{
    		pLocation.getData().add(new Data(x.getNom(),x.getSomme()));
    	});
    }

}
