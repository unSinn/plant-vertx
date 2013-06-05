package ch.ma3.plant.converters.rickshaw;

import java.util.ArrayList;

class RickshawCollection {

	private ArrayList<RickshawSerie> series;

	public RickshawCollection() {
		series = new ArrayList<>();
	}

	public ArrayList<RickshawSerie> getSeries() {
		return series;
	}

	public void addSerie(RickshawSerie serie) {
		this.series.add(serie);
	}
}
