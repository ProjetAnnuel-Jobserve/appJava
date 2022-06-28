package modele;

public class Task {
	private String name, description, date, state, _id, person;
	
	public Task(String name, String description, String date, String state, String _id, String person) {
		this.name = name;
		this.description = description;
		this.date = date;
		this.state = state;
		this._id = _id;
		this.person = person;
	}
	
	public Task() {
		this.name = "";
		this.description = "";
		this.date = "";
		this.state = "";
		this._id = "";
		this.person = "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public String getId() {
		return _id;
	}

	public void setId(String id) {
		this._id = id;
	}
	
	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}
	
	public String toString() {
        return String.format("%s\t%s\t%s\t%s\t%s\t%s", name, description, date, state, _id, person);
    }
}
