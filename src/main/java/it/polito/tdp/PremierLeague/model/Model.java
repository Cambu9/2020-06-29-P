package it.polito.tdp.PremierLeague.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {
	
	PremierLeagueDAO dao = new PremierLeagueDAO();
	private List<Match> allMatches = new ArrayList<>();
	public List<Match> matches;
	private Graph<Match, DefaultWeightedEdge> grafo;
	private List<Adiacenza> adiacenze = new ArrayList<>();
	private int mese;
	public int pesoTot;
	List<Adiacenza2> parziale = new ArrayList<>();
	List<Match> vicini = new ArrayList<>();
	public List<Match> best;
	public Double pesoMax;
	
	public Model() {
		allMatches = dao.listAllMatches();
		matches = new ArrayList<>();
		pesoMax = 0.0;
	}
	
	public String creaGrafo(int meseSelezionato, int minutiGiocati) {
		
		//creo il grafo
		this.grafo = new SimpleWeightedGraph<Match, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		//aggiungo i vertici
		for(Match m: allMatches) {
			mese = m.getDate().getMonthValue();
			
			if(mese == meseSelezionato)
			 matches.add(m);
		}
		Collections.sort(matches , new Comparator<Match>() {
			@Override
			public int compare(Match o1, Match o2) {
				return o1.getMatchID().compareTo(o2.getMatchID());
			}			
		});
		Graphs.addAllVertices(this.grafo, matches);
		
		//aggiungo gli archi
		adiacenze = dao.getArchi(meseSelezionato, minutiGiocati);
		for(Adiacenza a: adiacenze) {
			for(Match m1: matches) {
				for(Match m2: matches) {
					if(m1.getMatchID().equals(a.getiD1()) && m2.getMatchID().equals(a.getiD2())) {
						Graphs.addEdgeWithVertices(this.grafo, m1, m2,a.getPeso());
					}
				}
			}
		}
		
		return "Grafo creato\n #VERTICI: " + this.grafo.vertexSet().size() + "\n#ARCHI: " + this.grafo.edgeSet().size() + "\n";
	}
	
	public String massimaConnessione() {
		Set<DefaultWeightedEdge> set = this.grafo.edgeSet();
		String result = "";
		int max = 0;
		for(DefaultWeightedEdge e: set) {
			if(this.grafo.getEdgeWeight(e) > max) {
				max = (int) this.grafo.getEdgeWeight(e);
			}
		}
		for(DefaultWeightedEdge e: set) {
			if(this.grafo.getEdgeWeight(e) == max) {
				result = result + (this.grafo.getEdgeSource(e).toString() + " - " + this.grafo.getEdgeTarget(e).toString() + " (" + this.grafo.getEdgeWeight(e) + ")\n");
			}
	}
		return result;
	}
	
	public List<Match> calcolaPercorso(Match sorgente, Match destinazione){
		best = new LinkedList<>();
		List<Match> parziale = new LinkedList<>();
		parziale.add(sorgente);
		cerca(parziale, destinazione, 0.0);
		return best;
	}

	private void cerca(List<Match> parziale, Match destinazione, Double peso) {
		
		if(peso > pesoMax) {
			pesoMax = peso;
		}
		
		//condizione di terminazione
		if(parziale.get(parziale.size() -1).equals(destinazione)) {
			//è la soluzione migliore?
			if(parziale.size() > best.size()) {
				best = new LinkedList<>(parziale);
			}
			return;
		}
		
		if(Graphs.neighborListOf(this.grafo, 
				parziale.get(parziale.size()-1)) == null) {
			return;
		}
		//scorro i vicini dell'ultimo inserito e provo le varie "strade"
		for(Match v : Graphs.neighborListOf(this.grafo, 
				parziale.get(parziale.size()-1))) {
			if(!(parziale.get(parziale.size() -1).getTeamHomeID().equals(v.getTeamHomeID()) ||
					parziale.get(parziale.size() -1).getTeamHomeID().equals(v.getTeamAwayID()) && 
					(!(parziale.get(parziale.size() -1).getTeamAwayID().equals(v.getTeamHomeID()) ||
							parziale.get(parziale.size() -1).getTeamAwayID().equals(v.getTeamAwayID()))))) {
					if(!parziale.contains(v)) {
						parziale.add(v);
						DefaultWeightedEdge e = this.grafo.getEdge(parziale.get(parziale.size()-2), v);
						cerca(parziale,destinazione, peso+this.grafo.getEdgeWeight(e));
						parziale.remove(parziale.size()-1);
					}
			}	
		}
	}
	
}
