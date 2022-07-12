package it.polito.tdp.PremierLeague.db;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.PremierLeague.model.Match;
import it.polito.tdp.PremierLeague.model.Model;

public class TestDao {

	Model model = new Model();
	
	public static void main(String[] args) {
		TestDao testDao = new TestDao();
		testDao.run();
	}
	
	public void run() {
		PremierLeagueDAO dao = new PremierLeagueDAO();
//		System.out.println("Players:");
//		System.out.println(dao.listAllPlayers());
//		System.out.println("Actions:");
//		System.out.println(dao.listAllActions());
//		System.out.println("Matches:");
//		List<Match> matches = new ArrayList<Match>(dao.listAllMatches());
//		for(Match m: matches) {
//			System.out.println(m.getDate().getMonthValue());
//		}
		model.creaGrafo(5, 10);
		List<Match> matches = model.matches;
		for(Match m: matches) {
			System.out.println(m + "\n");
		}
//		System.out.println(dao.listAllMatches());
	}

}
