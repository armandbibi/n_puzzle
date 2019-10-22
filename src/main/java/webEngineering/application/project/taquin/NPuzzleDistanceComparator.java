package webEngineering.application.project.taquin;

import java.util.Comparator;

public class NPuzzleDistanceComparator implements Comparator<NPuzzle> {

	@Override
	public int compare(NPuzzle o1, NPuzzle o2) {
		return  o1.getDistance() - o2.getDistance();
	}

}
