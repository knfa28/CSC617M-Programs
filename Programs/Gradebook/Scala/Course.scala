class Course(val newId: Int, val newCode: String, val newName: String){
	var id: Int = newId
	var code: String = newCode
	var name: String = newName

	def getId(): Int = {
		return id
	}

	def getCode(): String = {
		return code
	}

	def getName(): String = {
		return name
	}

	def setId(newId: Int) {
		id = newId
	}

	def setCode(newCode: String) {
		code = newCode
	}

	def setName(newName: String) {
		name = newName
	}
}