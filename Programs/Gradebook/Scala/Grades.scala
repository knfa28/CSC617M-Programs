class Grades(val newStudent: Int, val newCourse: Int, val newExercises: Float, val newExams: Float, val newFinals: Float, val newFinalGrade: Float){
	var student: Int = newStudent
	var course: Int = newCourse
	var exercises: Float = newExercises
	var exams: Float = newExams
	var finals: Float = newFinals
	var finalGrade: Float = newFinalGrade

	def getStudent(): Int = {
		return student
	}

	def getCourse(): Int = {
		return course
	}

	def getExercises(): Float = {
		return exercises
	}

	def getExams(): Float = {
		return exams
	}

	def getFinals(): Float = {
		return finals
	}

	def getFinalGrade(): Float = {
		return finalGrade
	}

	def setStudent(newStudent: Int) {
		student = newStudent
	}

	def setCourse(newCourse: Int) {
		course = newCourse
	}

	def setExercises(newExercises: Float) {
		exercises = newExercises
	}

	def setExams(newExams: Float) {
		exams = newExams
	}

	def setFinals(newFinals: Float) {
		finals = newFinals
	}

	def setFinalGrade(newFinalGrade: Float) {
		finalGrade = newFinalGrade
	}
}