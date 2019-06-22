package it.polito.tdp.formulaone.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.formulaone.model.Race;
import it.polito.tdp.formulaone.model.Season;

public class TestFormulaOneDAO {

	public static void main(String[] args) {

		FormulaOneDAO dao = new FormulaOneDAO();

		List<Season> seasons = dao.getAllSeasons();
		System.out.println(seasons);

	Map<Integer,Race>map = new HashMap<>();
	 dao.getGarePerStagione(map, 2012);
		System.out.println(dao.getArchi(map, 2012));
	}

}
