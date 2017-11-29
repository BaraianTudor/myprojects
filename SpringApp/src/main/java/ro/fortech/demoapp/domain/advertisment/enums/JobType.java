package ro.fortech.demoapp.domain.advertisment.enums;

public enum JobType {
	
	FULLTIME("Full time"), PARTTIME("Part time"), PROJECTBASED("Project based"), OTHER("Other");
	
	private String Name;

	private JobType(String name) {
		Name = name;
	}

	public String getName() {
		return Name;
	}
	
	
}
