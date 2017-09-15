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

public class Task10 {

	public static void main(String[] args) {
		Model model = ModelFactory.createDefaultModel();
		model.read("src/resources/mondial-3.0-europe-countries.rdf");
		String queryString = "SELECT ?label ?country WHERE {?country <"
				+ RDFS.label + "> ?label . ?country <http://purl.org/dc/terms/language> ?language . ?language <" + RDFS.label + "> \"German\" }";
		Query query = QueryFactory.create(queryString);
		QueryExecution execution = QueryExecutionFactory.create(query, model);
		ResultSet results = execution.execSelect();
		
		while (results.hasNext()) {
			QuerySolution solution = results.next();
			System.out.println(solution.get("country").toString() + "\t"
					+ solution.get("label").toString() + "\t");
		}
	}

}
