import scala.collection.mutable.ListBuffer
import scala.io.Source
import java.io.PrintWriter

object Gradebook {
	var studentList = new ListBuffer[Student]()
	var courseList = new ListBuffer[Course]()
	var gradeList = new ListBuffer[Grades]()

	def cls() {
		for(i <- 0 to 1000) {
	      	println("")
	      }
	}

	def readData(){
		val students = Source.fromFile("C:/Users/kurta/Desktop/Gradebook/resources/Student.txt").getLines.toList
		val courses = Source.fromFile("C:/Users/kurta/Desktop/Gradebook/resources/Course.txt").getLines.toList
		val grades = Source.fromFile("C:/Users/kurta/Desktop/Gradebook/resources/Grades.txt").getLines.toList

		for(i <- 0 to students.size - 1){
			var temp = students(i).split(", ")
			var tempStudent = new Student(temp(0).toInt, temp(1), temp(2))
			studentList += tempStudent
		}

		for(i <- 0 to courses.size - 1){
			var temp = courses(i).split(", ")
			var tempCourse = new Course(temp(0).toInt, temp(1), temp(2))
			courseList += tempCourse
		}

		for(i <- 0 to grades.size - 1){
			var temp = grades(i).split(", ")
			var tempGrade = new Grades(temp(0).toInt, temp(1).toInt, temp(2).toFloat, temp(3).toFloat, temp(4).toFloat, temp(5).toFloat)
			gradeList += tempGrade
		}
	}

	def writeData(){
		val students = new PrintWriter("Student.txt")
		val courses = new PrintWriter("Course.txt")
		val grades = new PrintWriter("Grades.txt")

	  	for(i <- 0 to studentList.size - 1){
	  		var id = studentList(i).getId().toString()
	  		var name = studentList(i).getName()
	  		var gender = studentList(i).getGender()

			students.print(id + ", " + name + ", " + gender + "\n")
	  	}
	  	
	  	for(i <- 0 to courseList.size - 1){
	  		var id = courseList(i).getId().toString()
	  		var code = courseList(i).getCode()
	  		var name = courseList(i).getName()

			courses.print(id + ", " + code + ", " + name + "\n")
	  	}

	  	for(i <- 0 to gradeList.size - 1){
	  		var student = gradeList(i).getStudent().toString()
	  		var course = gradeList(i).getCourse().toString()
	  		var exercises = gradeList(i).getExercises().toString()
	  		var exams = gradeList(i).getExams().toString()
	  		var finals = gradeList(i).getFinals().toString()
	  		var finalGrade = gradeList(i).getFinalGrade().toString()

			grades.print(student + ", " + course + ", " + exercises + ", " + exams + ", " + finals + ", " + finalGrade + "\n")
	  	}
	  	
	  	students.close()
	  	courses.close()
	  	grades.close()
	}

	def displayStudents() {
		for(i <- 0 to studentList.size - 1){
			var name = studentList(i).getName()
			var id = studentList(i).getId()
			var gender = studentList(i).getGender()
			println((i+1) + ". " + name + " - ID# " + id + " - " + gender)
		}
	}

	def addStudent() {
		print("Enter Student's Name: ") 
      	var name = scala.io.StdIn.readLine()
      	name.replace('\n', ' ')

      	print("Enter Student's Gender(Male/Female): ") 
      	var gender = scala.io.StdIn.readLine()
      	gender.replace('\n', ' ')

      	var id = studentList(studentList.size - 1).getId() + 1

      	var tempStudent = new Student(id, name, gender)
      	studentList += tempStudent
      	cls()
      	println(name + " has been added to the student list!")
	}

	def editStudent(index: Int){
		var id = studentList(index).getId()

		println("Edit " + id + "'s student profile:")
		print("Enter Student's Name: ") 
      	var newName = scala.io.StdIn.readLine()
      	newName.replace('\n', ' ')

      	print("Enter Student's Gender(Male/Female): ") 
      	var newGender = scala.io.StdIn.readLine()
      	newGender.replace('\n', ' ')

      	studentList(index).setName(newName)
      	studentList(index).setGender(newGender)
      	cls()
      	println(id + "'s student profile has been updated!")
	}

	def deleteStudent(index: Int){
		var name = studentList(index).getName()
		cls()
      	println(name + "'s student profile has been deleted!")
      	deleteStudentGrades(index)
      	studentList.remove(index)
	}

	def displayCourses(){
	    for (i <- 0 to courseList.size - 1){
	    	var id = courseList(i).getId()
	    	var code = courseList(i).getCode()
	    	var name = courseList(i).getName()

	        println((i+1) + ". " + code + " - ID# " + id + " - " + name)
	    }
    }

	def addCourse() {
		print("Enter Course Code: ") 
      	var code = scala.io.StdIn.readLine()
      	code.replace('\n', ' ')

      	print("Enter Course Name: ") 
      	var name = scala.io.StdIn.readLine()
      	name.replace('\n', ' ')

      	var id = courseList(courseList.size - 1).getId() + 1

      	var tempCourse = new Course(id, code, name)
      	courseList += tempCourse
      	cls()
      	println(name + " has been added to the course list!")
	}

	def editCourse(index: Int){
		var id = courseList(index).getId()

		print("Enter Course Code: ") 
      	var newCode = scala.io.StdIn.readLine()
      	newCode.replace('\n', ' ')

		println("Edit " + id + "'s course details:")
		print("Enter Course Name: ") 
      	var newName = scala.io.StdIn.readLine()
      	newName.replace('\n', ' ')

      	courseList(index).setCode(newCode)
      	courseList(index).setName(newName)
      	cls()
      	println(id + "'s course details have been updated!")
	}

	def deleteCourse(index: Int){
		var code = courseList(index).getCode()
		cls()
      	println("The course " + code + " has been deleted!")
      	deleteCourseGrades(index)
      	courseList.remove(index)
	}

	def displayStudentCourses(studentIndex: Int){
		var studentId = studentList(studentIndex).getId()
    	var courseCnt = 1

    	for(i <- 0 to gradeList.size - 1){
			if(gradeList(i).getStudent() == studentId){
				for(j <- 0 to courseList.size - 1){
					if(gradeList(i).getCourse() == courseList(j).getId()){
						var code = courseList(j).getCode()
						var id = courseList(j).getId()
						var name = courseList(j).getName()
						println(courseCnt + ". " + code + " - ID# " + id + " - " + name)
						courseCnt += 1
					}
				}
			}
		}
	}

	def displayStudentCourseGrades(studentIndex: Int, courseIndex: Int){
	    var name = studentList(studentIndex).getName()
	    var studentId = studentList(studentIndex).getId()
	    var course = courseList(courseIndex).getCode()
	    var courseId = courseList(courseIndex).getId()

	    println(name + "'s grades for the course of " + course + ":")

	    for (i <- 0 to gradeList.size - 1){
	        if ((gradeList(i).getStudent() == studentId) & (gradeList(i).getCourse() == courseId)){
	            println("* Exercises = " + gradeList(i).getExercises() + "%")
	            println("* Exams = " + gradeList(i).getExams() + "%")
	            println("* Finals = " + gradeList(i).getFinals() + "%")
	            println("* Final Grade = " + gradeList(i).getFinalGrade())
    		}
    	}
    }

    def addStudentGrades(studentIndex: Int, courseIndex: Int){
	    println("--Add a Gradebook--")
	    var name = studentList(studentIndex).getName()
	    var studentId = studentList(studentIndex).getId()
	    var course = courseList(courseIndex).getCode()
	    var courseId = courseList(courseIndex).getId()

	    println(name + "'s grades for the course of " + course + ":")
	    print("* Exercises = ") 
      	var exercises = scala.io.StdIn.readFloat()
     	print("* Exams = ") 
      	var exams = scala.io.StdIn.readFloat()
      	print("* Finals = ") 
      	var finals = scala.io.StdIn.readFloat()
      	print("* Final Grade = ") 
      	var finalGrade = scala.io.StdIn.readFloat()
	    

	    var tempGrade = new Grades(studentId, courseId, exercises, exams, finals, finalGrade)
	    gradeList += tempGrade
	    cls()
	    println(name + "'s grades for the course of " + course + " has been added!")
    }

    def editStudentGrades(studentIndex: Int, courseIndex: Int){
	    println("--Edit a Gradebook--")
	    var name = studentList(studentIndex).getName()
	    var studentId = studentList(studentIndex).getId()
	    var course = courseList(courseIndex).getCode()
	    var courseId = courseList(courseIndex).getId()

	   	println(name + "'s grades for the course of " + course + ":")
	    print("* Exercises = ") 
      	var newExercises = scala.io.StdIn.readFloat()
     	print("* Exams = ") 
      	var newExams = scala.io.StdIn.readFloat()
      	print("* Finals = ") 
      	var newFinals = scala.io.StdIn.readFloat()
      	print("* Final Grade = ") 
      	var newFinalGrade = scala.io.StdIn.readFloat()

	    for (i <- 0 to gradeList.size - 1){
	        if ((gradeList(i).getStudent() == studentId) & (gradeList(i).getCourse() == courseId)){
	            gradeList(i).setExercises(newExercises)
	            gradeList(i).setExams(newExams)
	            gradeList(i).setFinals(newFinals)
	            gradeList(i).setFinalGrade(newFinalGrade)
	        }
	    }

	    cls()
	    println(name + "'s grades for the course of " + course + " has been updated!")
    }

    def deleteStudentGrades(studentIndex: Int){
	    var studentId = studentList(studentIndex).getId()
	    gradeList --= gradeList.filter(_.getStudent() == studentId)
    }

    def deleteCourseGrades(courseIndex: Int){
	    var courseId = courseList(courseIndex).getId()
	    gradeList --= gradeList.filter(_.getCourse() == courseId)
    }

    def deleteStudentCourseGrades(studentIndex: Int, courseIndex: Int){
	    var studentId = studentList(studentIndex).getId()
	    var name = studentList(studentIndex).getName()
	    var courseId = courseList(courseIndex).getId()
	    var code = courseList(courseIndex).getCode()

	    gradeList --= gradeList.filter(_.getStudent() == studentId).filter(_.getCourse() == courseId)

	    print(name + "'s grades for the course of " + code + " has been deleted!")
    }

    def displayStudentGrades(studentIndex: Int){
	    var name = studentList(studentIndex).getName()
	    var studentId = studentList(studentIndex).getId()

	    for (i <- 0 to gradeList.size - 1){
	        if (gradeList(i).getStudent() == studentId){
	            var courseIndex = gradeList(i).getCourse()

	            for (j <- 0 to courseList.size - 1){
	                if (courseList(j).getId() == courseIndex){
	                    var course = courseList(j).getCode()

	                    println(name + "'s grades for the course of " + course + ":")
			            println("* Exercises = " + gradeList(i).getExercises() + "%")
			            println("* Exams = " + gradeList(i).getExams() + "%")
			            println("* Finals = " + gradeList(i).getFinals() + "%")
			            println("* Final Grade = " + gradeList(i).getFinalGrade())
	                }
	            }    
	        }
	    }
    }

    def studentMenu(){
	    var choice = -1

	    while (choice != 0){
	        cls()
	        println("--Students List--")
	        displayStudents()
	        println("--Options--")
	        println("[1] Add a Student")
	        println("[2] Edit a Student")
	        println("[3] Delete a Student")
	        println("[0] Back to Menu")
	        print("Input: ") 
      		choice = scala.io.StdIn.readInt()

	        if (choice == 1){
	            cls()
	            println("--Add a Student--")
	            addStudent()
	            print("Press Enter to continue...")
	            scala.io.StdIn.readLine()
	        } else if (choice == 2){
	            cls()
	            println("--Edit Student Profile--")
	            displayStudents()
	            print("Input: ") 
      			var student = scala.io.StdIn.readInt()
	            cls()
	            editStudent(student - 1)
	            print("Press Enter to continue...")
	            scala.io.StdIn.readLine()
	        } else if (choice == 3){
	            cls()
	            println("--Delete Student Profile--")
	            displayStudents()
	            print("Input: ") 
      			var student = scala.io.StdIn.readInt()
	            cls()
	            deleteStudent(student - 1)
	            print("Press Enter to continue...")
	            scala.io.StdIn.readLine()
	        }
	    }
    }

    def courseMenu(){
    	var choice = -1

	    while (choice != 0){
	        cls()
	        println("--Course List--")
	        displayCourses()
	        println("--Options--")
	        println("[1] Add a Course")
	        println("[2] Edit a Course")
	        println("[3] Delete a Course")
	        println("[0] Back to Menu")
	        print("Input: ") 
      		choice = scala.io.StdIn.readInt()

	        if (choice == 1){
	            cls()
	            println("--Add a Course--")
	            addCourse()
	            print("Press Enter to continue...")
	            scala.io.StdIn.readLine()
	        } else if (choice == 2){
	            cls()
	            println("--Edit Course Details--")
	            displayCourses()
	            print("Input: ") 
      			var course = scala.io.StdIn.readInt()
	            cls()
	            editCourse(course - 1)
	            print("Press Enter to continue...")
	            scala.io.StdIn.readLine()
	        } else if (choice == 3){
	            cls()
	            println("--Delete Course--")
	            displayCourses()
	            print("Input: ") 
      			var course = scala.io.StdIn.readInt()
	            cls()
	            deleteCourse(course - 1)
	            print("Press Enter to continue...")
	            scala.io.StdIn.readLine()
	        }
        }
    }

    def gradesMenu(){
	    var choice = -1

	    while (choice != 0){
	        cls()
	        println("--View Grades--")
	        displayStudents()
	        print("Input: ") 
      		var student = scala.io.StdIn.readInt()
	        cls()
	        println("--Student's Grades---")
	        displayStudentGrades(student - 1)
	        println("--Options--")
	        println("[1] Add a Gradebook")
	        println("[2] Edit a Gradebook")
	        println("[3] Delete a Gradebook")
	        println("[0] Back to Menu")
	        print("Input: ") 
      		choice = scala.io.StdIn.readInt()

	        if (choice == 1){
	            cls()
	            println("--Add a Gradebook--")
	            displayCourses()
	            print("Input: ") 
      			var course = scala.io.StdIn.readInt()
	            cls()
	            addStudentGrades(student - 1, course - 1)
	            print("Press Enter to continue...")
	            scala.io.StdIn.readLine()
	        } else if (choice == 2){
	            cls()
	            println("--Edit a Gradebook--")
	            displayStudentCourses(student - 1)
	            print("Input: ") 
      			var course = scala.io.StdIn.readInt()
	            cls()
	            editStudentGrades(student - 1, course - 1)
	            print("Press Enter to continue...")
	            scala.io.StdIn.readLine()
	        } else if (choice == 3){
	            cls()
	            println("--Delete a Gradebook--")
	            displayStudentCourses(student - 1)
	            print("Input: ") 
      			var course = scala.io.StdIn.readInt()
	            deleteStudentCourseGrades(student - 1, course - 1)
	            print("Press Enter to continue...")
	            scala.io.StdIn.readLine()
	        }
	    }
    }

    def getStudentGpa(){
	    var studentGpa = new ListBuffer[Float]()
	    println("--Student GPAs--")

	    for (i <- 0 to studentList.size - 1){
	        var studentId = studentList(i).getId()
	        var name = studentList(i).getName()
	        var finalSum = 0.toFloat
	        var courseCnt = 0
	        
	        for (j <- 0 to gradeList.size - 1){
	            if (gradeList(j).getStudent() == studentId){
	                finalSum += gradeList(j).getFinalGrade()
	                courseCnt += 1
	            }
	        }

	        if (courseCnt > 0){
	            studentGpa += finalSum/courseCnt
	        } else{
	            studentGpa += 0.0.toFloat
	        }

	        println((i+1) + ". " + name + " = " + studentGpa(i))
	    }
    }

    def getCourseAvg(){
	    var courseAvg = new ListBuffer[Float]()
	    println("--Course Averages--")

	    for (i <- 0 to courseList.size - 1){
	        var courseId = courseList(i).getId()
	        var code = courseList(i).getCode()
	        var finalSum = 0.toFloat
	        var studentCnt = 0

	        for (j <- 0 to gradeList.size - 1){
	            if (gradeList(j).getCourse() == courseId){
	                finalSum += gradeList(j).getFinalGrade()
	                studentCnt += 1
	            }
	        }

	        if (studentCnt > 0){
	            courseAvg += finalSum/studentCnt
	        } else{
	            courseAvg += 0.0.toFloat
	        }

	        println((i+1) + ". " + code + " = " + courseAvg(i))
	    }
    }

    def getCourseTops(){
	    println("--Top 5 Students per Course--")
	    displayCourses()
	    print("Input: ") 
      	var course = scala.io.StdIn.readInt()
	    cls()

	    var courseId = courseList(course - 1).getId()
	    var code = courseList(course  - 1).getCode()
	    var tempList = new ListBuffer[Grades]()
	    var sortedList = new ListBuffer[Grades]()
	    var topFive = new ListBuffer[Grades]()

	    println(code + " Top 5:")

	    for (i <- 0 to gradeList.size - 1){
	        if (gradeList(i).getCourse() == courseId){
	            tempList += gradeList(i)
	        }
	    }

	    sortedList = tempList sortBy(_.getFinalGrade())
	    
	    for (i <- 0 to sortedList.size - 1){
	    	topFive += sortedList(sortedList.size - 1 - i)
	    }

	    for (i <- 0 to 4){
	        if (i < topFive.size){
	            var studentId = topFive(i).getStudent()
	            var name = "temp"

	            for (j <- 0 to studentList.size - 1){
	                if (studentList(j).getId() == studentId){
	                    name = studentList(j).getName()
	                }
	            }

	            println((i+1) + ". " + name + " = " + topFive(i).getFinalGrade())
	        } else{
	            println((i+1) + ". N/A")
	        }
	    }
    }

   	def main(args: Array[String]) {
      	var choice = -1
      	
      	readData()
     
      	while (choice != 0){
		    cls()
		    println("--Scala Gradebook App--")
		    println("[1] View Students")
		    println("[2] View Courses")
		    println("[3] View a Student's Grades")
		    println("[4] View Students' GPAs")
		    println("[5] View Average Grade per Course")
		    println("[6] View Top 5 Students per Course")
		    println("[0] Exit")
		    print("Input: ")
		    choice = scala.io.StdIn.readInt()

		    if (choice == 1){
		        cls()
		        studentMenu()
		    } else if (choice == 2){
		        cls()
		        courseMenu()
		    } else if (choice == 3){
		        cls()
		        gradesMenu()
		    } else if (choice == 4){
		        cls()
		        getStudentGpa()
		        print("Press Enter to continue...")
	            scala.io.StdIn.readLine()
		    } else if (choice == 5){
		        cls()
		        getCourseAvg()
		        print("Press Enter to continue...")
	            scala.io.StdIn.readLine()
		    } else if (choice == 6){
		        cls()
		        getCourseTops()
		        print("Press Enter to continue...")
	            scala.io.StdIn.readLine()
		    }
	    }
	    
	    writeData()
   	}
}