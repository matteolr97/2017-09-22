package it.polito.tdp.formulaone.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.formulaone.db.FormulaOneDAO;

public class Model {

	private Graph<Race, DefaultWeightedEdge>grafo;
	private Map<Integer, Race> idMap;
	private FormulaOneDAO dao;
	
	private Map<Integer, Driver> piloti;
	
	public Model() {
		dao = new FormulaOneDAO();
		
	}

	public List<Season> getSeason(){
		return dao.getAllSeasons();
	}
	public void creaGrafo(int anno) {
		idMap = new HashMap<Integer, Race>();
		grafo = new SimpleWeightedGraph<Race,DefaultWeightedEdge>(DefaultWeightedEdge.class); 
		Graphs.addAllVertices(grafo, dao.getGarePerStagione(idMap, anno));
		for(Archi a: dao.getArchi(idMap, anno)) {
			Graphs.addEdgeWithVertices(this.grafo, a.getRace1(), a.getRace2(), a.getPeso());
			
		}
	System.out.println("Numero vertici:"+grafo.vertexSet().size());
	System.out.println("Numero archi:"+grafo.edgeSet().size());
	
	}
	public Set<Race> getVertici() {
		return grafo.vertexSet();
	}
	public int getNumeroArchi() {
		return grafo.edgeSet().size();
	}
	public List<Archi> getPesoMassimo(){
		int temp = 0;
		List<Archi> result = new LinkedList<Archi>();
		for(DefaultWeightedEdge d: this.grafo.edgeSet()) {
			if(grafo.getEdgeWeight(d)>temp)
				temp=(int) grafo.getEdgeWeight(d);
		}
		for(DefaultWeightedEdge d: this.grafo.edgeSet()) {
			if(grafo.getEdgeWeight(d)==temp) {
				result.add(new Archi(grafo.getEdgeSource(d), grafo.getEdgeTarget(d),(int)(grafo.getEdgeWeight(d))));
				
			}
		}
	return result;
	}
	
	public void Simula() {
		
	}
}
