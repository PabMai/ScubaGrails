package scubagrails.gestionLogs

import java.util.List;

class GestionLogs {
	
	private List<String> logs
	private int nbSucces
	private int nbErrors

	public GestionLogs(List<String> logs, int nbSucces, int nbErrors) {
		logs = new ArrayList<String>()
		this.logs = logs;
		this.nbSucces = nbSucces;
		this.nbErrors = nbErrors;
	}
	
	public void ajouterLogs(String log) {
		logs.add(log)
	}
	
	public void incrementerSucces() {
		nbSucces++
	}
	
	public void incrementerErrors() {
		nbErrors++
	}
	
	
	

}
