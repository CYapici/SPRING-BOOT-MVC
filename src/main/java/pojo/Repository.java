package pojo;
/**
 * POJOS 4 JSON
 * @author PRO2884
 *
 */
public class Repository {

	private String repositoryName;
	private String languagesList[]; 
	public String getName() {
		return repositoryName;
	}
	public void setName(String name) {
		this.repositoryName = name;
	}
	public String[] getStaffName() {
		return languagesList;
	}
	public void setStaffName(String[] staffName) {
		this.languagesList = staffName;
	} 
}