package week3MyCode;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.providers.AbstractMapProvider;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class LifeExpectancy extends PApplet {
	/**
	 * Do not know what this does, assume it assigns a ID
	 */
	private static final long serialVersionUID = 1L;
	private UnfoldingMap map;

	
	public void setup() {
		size(1000, 800, P2D);

		/* Initialize map */
		AbstractMapProvider provider = new OpenStreetMap.OpenStreetMapProvider();
		map = new UnfoldingMap(this, 100, 20, 900, 750, provider);
		MapUtils.createDefaultEventDispatcher(this, map);
	}

	
	public void draw() {

	}

}
