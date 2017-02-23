class Student(val newId: Int, val newName: String, val newGender: String){
	var id: Int = newId
	var name: String = newName
	var gender: String = newGender

	def getId(): Int = {
		return id
	}

	def getName(): String = {
		return name
	}

	def getGender(): String = {
		return gender
	}

	def setId(newId: Int) {
		id = newId
	}

	def setName(newName: String) {
		name = newName
	}

	def setGender(newGender: String) {
		gender = newGender
	}
}