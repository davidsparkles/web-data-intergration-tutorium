package Part3_RDF;


import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.vocabulary.RDFS;

public class Task8 {

	public static void main(String[] args) {
		Model model = ModelFactory.createDefaultModel();
		model.read("src/resources/mondial-3.0-europe-countries.rdf");
		String queryString = "SELECT ?country ?label ?pop WHERE {?country <"
				+ RDFS.label + "> ?label . ?country <http://www.geonames.org/ontology#population> ?pop } ORDER BY ?label";
		// String queryString = "SELECT ?pop WHERE {?country <http://www.w3.org/2000/01/rdf-schema#label> \"Germany\". ?country <http://www.geonames.org/ontology#population> ?pop}";
		Query query = QueryFactory.create(queryString);
		QueryExecution execution = QueryExecutionFactory.create(query, model);
		ResultSet results = execution.execSelect();
		
		while (results.hasNext()) {
			QuerySolution solution = results.next();
			System.out.println(solution.get("country").toString() + "\t"
					+ solution.get("label").toString() + "\t"
					+ solution.get("pop").toString());
		}
	}

}
