package module2;

import java.util.ArrayList;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.providers.AbstractMapProvider;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.utils.MapUtils;
import parsing.ParseFeed;
import processing.core.*;

public class EarthQuakeCityMap extends PApplet {

	/**
	 * Do not know what this does, assume it assigns a ID
	 */
	private static final long serialVersionUID = 1L;
	private UnfoldingMap map;

	// Less than this threshold is a light earthquake
	public static final float THRESHOLD_MODERATE = 6;
	// Less than this threshold is a minor earthquake
	public static final float THRESHOLD_LIGHT = 4;

	// feed with magnitude 2.5+ Earthquakes
	private String earthquakesURL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";
	private PImage keyImg;

	public void setup() {
		size(1000, 800, P2D);

		/* Initialize map */
		AbstractMapProvider provider = new OpenStreetMap.OpenStreetMapProvider();
		map = new UnfoldingMap(this, 100, 20, 900, 750, provider);
		MapUtils.createDefaultEventDispatcher(this, map);

		// Use provided parser to collect properties for each earthquake
		// PointFeatures have a getLocation method, Goal accomplished
		List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);

		/*
		 * List of markers, fill with marker associated with each PointFeature
		 * object.Goal accomplished.
		 */
		List<Marker> eQMarkers = new ArrayList<Marker>();
		for (PointFeature pf : earthquakes) {
			eQMarkers.add(createMarker(pf));
		}

		/* Add markers */
		map.addMarkers(eQMarkers);

	}

	public void draw() {
		background(220);
		map.draw();

		addKey();
	}

	private void addKey() {
		// TODO Auto-generated method stub
		String URL = "/home/knuth/Documents/Github/UnfoldingMaps/data/key.jpg";
		keyImg = loadImage(URL, "jpg");
		image(keyImg,10,200);

	}

	private SimplePointMarker createMarker(PointFeature point) {

		SimplePointMarker marker = new SimplePointMarker(point.getLocation(), point.getProperties());

		Object magObj = marker.getProperty("magnitude");
		float magnitude = Float.parseFloat(magObj.toString());

		int yellow = color(250, 200, 0);
		int red = color(250, 0, 0);
		int blue = color(0, 0, 200);

		if (magnitude < THRESHOLD_LIGHT) {
			marker.setColor(blue);

		} else if (magnitude < THRESHOLD_MODERATE) {
			marker.setColor(yellow);
		} else {
			marker.setColor(red);
			marker.setRadius(20);
		}

		return marker;
	}

}
