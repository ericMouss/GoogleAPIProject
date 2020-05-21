package com.example.bikeproject.modele.beans;

import android.graphics.Color;

public class Trajet {
    private MarkerOptions depart, arrivee;
    private PolylineOptions polylineOptions;
    private LatLngBounds latLngBounds;

    public Trajet(DirectionResult directionResult) throws Exception {

        polylineOptions = new PolylineOptions();
        polylineOptions.color(Color.BLUE);

        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        if (directionResult.getRoutes() != null) {
            for (Route route : directionResult.getRoutes()) {
                if (route.getLegs() != null) {
                    for (Leg leg : route.getLegs()) {
                        if (leg.getSteps() != null) {
                            for (Step step : leg.getSteps()) {
                                polylineOptions.add(new LatLng(step.getStart_location().getLat(), step.getStart_location().getLng()));
                            }
                        }
                        else if (leg.getStart_location() != null) {
                            polylineOptions.add(new LatLng(leg.getStart_location().getLat(), leg.getStart_location().getLng()));
                        }
                        if (leg.getEnd_location() != null) {
                            polylineOptions.add(new LatLng(leg.getEnd_location().getLat(), leg.getEnd_location().getLng()));
                        }
                    }
                }
            }
        }

        if (polylineOptions.getPoints().isEmpty()) {
            throw new Exception("Aucun chemin");
        }

        for (LatLng latLng : polylineOptions.getPoints()) {
            builder.include(latLng);
        }
        //le cadre
        latLngBounds = builder.build();

        //On déclare un marker vert que l'on placera sur le départ
        depart = new MarkerOptions();
        depart.position(polylineOptions.getPoints().get(0));
        depart.title("Début");
        depart.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));

        //On déclare un marker rouge que l'on placera sur l'arrivée
        arrivee = new MarkerOptions();
        arrivee.position(polylineOptions.getPoints().get(polylineOptions.getPoints().size() - 1));
        arrivee.title("Fin");
        arrivee.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
    }

    public MarkerOptions getDepart() {
        return depart;
    }

    public void setDepart(MarkerOptions depart) {
        this.depart = depart;
    }

    public MarkerOptions getArrivee() {
        return arrivee;
    }

    public void setArrivee(MarkerOptions arrivee) {
        this.arrivee = arrivee;
    }

    public PolylineOptions getPolylineOptions() {
        return polylineOptions;
    }

    public void setPolylineOptions(PolylineOptions polylineOptions) {
        this.polylineOptions = polylineOptions;
    }

    public LatLngBounds getLatLngBounds() {
        return latLngBounds;
    }

    public void setLatLngBounds(LatLngBounds latLngBounds) {
        this.latLngBounds = latLngBounds;
    }
}
