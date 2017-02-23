import Student
import Course
import Grades

studentList = []
courseList = []
gradeList = []
choice = -1


def cls():
    print('\n' * 1000)


def readData():
    students = open('Student.txt', 'r')
    courses = open('Course.txt', 'r')
    grades = open('Grades.txt', 'r')

    for line in students:
        temp = line.split(', ', 2)
        temp[2] = temp[2].replace("\n", "")
        tempStudent = Student.Student(int(temp[0]), temp[1], temp[2])
        studentList.append(tempStudent)

    for line in courses:
        temp = line.split(', ', 2)
        temp[2] = temp[2].replace("\n", "")
        tempCourse = Course.Course(int(temp[0]), temp[1], temp[2])
        courseList.append(tempCourse)

    for line in grades:
        temp = line.split(', ', 5)
        temp[5] = temp[5].replace("\n", "")
        tempGrade = Grades.Grades(int(temp[0]), int(temp[1]), float(temp[2]), float(temp[3]), float(temp[4]), float(temp[5]))
        gradeList.append(tempGrade)

    students.close()
    courses.close()
    grades.close()
    return


def writeData():
    students = open('Student.txt', 'r+')
    courses = open('Course.txt', 'r+')
    grades = open('Grades.txt', 'r+')

    students.seek(0)
    courses.seek(0)
    grades.seek(0)

    for i in range(0, len(studentList)):
        id = studentList[i].getId()
        name = studentList[i].getName()
        gender = studentList[i].getGender()

        tempStudent = id + ", " + name + ", " + gender + "\n"
        students.write(tempStudent)
        students.truncate()

    for i in range(0, len(courseList)):
        id = courseList[i].getId()
        code = courseList[i].getCode()
        name = courseList[i].getName()

        tempCourse = id + ", " + code + ", " + name + "\n"
        courses.write(tempCourse)
        courses.truncate()

    for i in range(0, len(gradeList)):
        student = gradeList[i].getStudent()
        course = gradeList[i].getCourse()
        exercises = gradeList[i].getExercises()
        exams = gradeList[i].getExams()
        finals = gradeList[i].getFinals()
        finalGrade = gradeList[i].getFinalGrade()

        tempGrade = student + ", " + course + ", " + exercises + ", " + exams + ", " + finals + ", " + finalGrade + "\n"
        grades.write(tempGrade)
        grades.truncate()

    students.close()
    courses.close()
    grades.close()
    return


def displayStudents():
    for i in range(0, len(studentList)):
        print(i+1, ".", studentList[i].getName(), "- ID#", studentList[i].getId(), "-", studentList[i].getGender())
    return


def addStudent():
    name = input("Enter Student's Name: ")
    gender = input("Enter Student's Gender(Male/Female): ")
    id = studentList[len(studentList) - 1].getId() + 1

    tempStudent = Student.Student(id, name, gender)
    studentList.append(tempStudent)
    cls()
    print(name, "has been added to the student list!")
    return


def editStudent(index):
    id = studentList[index].getId()

    print("Edit", id, "'s student profile:")
    newName = input("Enter Student's Name: ")
    newGender = input("Enter Student's Gender(Male/Female): ")

    studentList[index].setName(newName)
    studentList[index].setGender(newGender)
    cls()
    print(id, "'s student profile has been updated!")
    return


def deleteStudent(index):
    name = studentList[index].getName()
    cls()
    print(name, "'s student profile has been deleted!")
    deleteStudentGrades(index)
    studentList.pop(index)

def displayCourses():
    for i in range(0, len(courseList)):
        print(i+1, ".", courseList[i].getCode(), "- ID#", courseList[i].getId(), "-", courseList[i].getName())
    return


def addCourse():
    code = input("Enter Course Code: ")
    name = input("Enter Course Name: ")
    id = courseList[len(courseList) - 1].getId() + 1

    tempCourse = Course.Course(id, code, name)
    courseList.append(tempCourse)
    cls()
    print(code, "has been added to the course list!")
    return


def editCourse(index):
    id = courseList[index].getId()

    print("Edit", id, "'s course details:")
    newCode = input("Enter Course Code: ")
    newName = input("Enter Course Name: ")

    courseList[index].setCode(newCode)
    courseList[index].setName(newName)
    cls()
    print(id, "'s course details have been updated!")
    return


def deleteCourse(index):
    code = courseList[index].getCode()
    cls()
    print("The course", code, "has been deleted!")
    deleteCourseGrades(index)
    courseList.pop(index)


def displayStudentCourses(studentIndex):
    studentId = studentList[studentIndex].getId()
    courseCnt = 1

    for i in range(0, len(gradeList)):
        if gradeList[i].getStudent() == studentId:
            for j in range(0, len(courseList)):
                if gradeList[i].getCourse() == courseList[j].getId():
                    print(courseCnt, ".", courseList[j].getCode(), "- ID#", courseList[j].getId(), "-", courseList[j].getName())
                    courseCnt += 1
    return


def displayStudentCourseGrades(studentIndex, courseIndex):
    name = studentList[studentIndex].getName()
    studentId = studentList[studentIndex].getId()
    course = courseList[courseIndex].getCode()
    courseId = courseList[courseIndex].getId()

    print(name, "'s grades for the course of", course, ":")

    for i in range(0, len(gradeList)):
        if (gradeList[i].getStudent() == studentId) & (gradeList[i].getCourse() == courseId):
            print("* Exercises =", gradeList[i].getExercises(), "%")
            print("* Exams =", gradeList[i].getExams(), "%")
            print("* Finals =", gradeList[i].getFinals(), "%")
            print("* Final Grade =", gradeList[i].getFinalGrade())
    return


def addStudentGrades(studentIndex, courseIndex):
    print("--Add a Gradebook--")
    name = studentList[studentIndex].getName()
    studentId = studentList[studentIndex].getId()
    course = courseList[courseIndex].getCode()
    courseId = courseList[courseIndex].getId()

    print(name, "'s grades for the course of", course, ":")
    exercises = float(input("* Exercises = "))
    exams = float(input("* Exams = "))
    finals = float(input("* Finals = "))
    finalGrade = float(input("* Final Grade = "))

    tempGrade = Grades.Grades(studentId, courseId, exercises, exams, finals, finalGrade)
    gradeList.append(tempGrade)
    cls()
    print(name, "'s grades for the course of", course, "has been added!")
    return


def editStudentGrades(studentIndex, courseIndex):
    print("--Edit a Gradebook--")
    name = studentList[studentIndex].getName()
    studentId = studentList[studentIndex].getId()
    course = courseList[courseIndex].getCode()
    courseId = courseList[courseIndex].getId()

    print(name, "'s grades for the course of", course, ":")
    newExercises = float(input("* Exercises = "))
    newExams = float(input("* Exams = "))
    newFinals = float(input("* Finals = "))
    newFinalGrade = float(input("* Final Grade = "))

    for i in range(0, len(gradeList)):
        if (gradeList[i].getStudent() == studentId) & (gradeList[i].getCourse() == courseId):
            gradeList[i].setExercises(newExercises)
            gradeList[i].setExams(newExams)
            gradeList[i].setFinals(newFinals)
            gradeList[i].setFinalGrade(newFinalGrade)

    cls()
    print(name, "'s grades for the course of", course, "has been updated!")
    return


def deleteStudentGrades(studentIndex):
    studentId = studentList[studentIndex].getId()
    toDelete = []

    for i in range(0, len(gradeList)):
        if studentId == gradeList[i].getStudent():
            toDelete.append(gradeList[i])

    for i in range(0, len(toDelete)):
        gradeList.remove(toDelete[i])

    return


def deleteCourseGrades(courseIndex):
    courseId = courseList[courseIndex].getId()
    toDelete = []

    for i in range(0, len(gradeList)):
        if courseId == gradeList[i].getCourse():
            toDelete.append(gradeList[i])

    for i in range(0, len(toDelete)):
        gradeList.remove(toDelete[i])

    return


def deleteStudentCourseGrades(studentIndex, courseIndex):
    studentId = studentList[studentIndex].getId()
    name = studentList[studentIndex].getName()
    courseId = courseList[courseIndex].getId()
    code = courseList[courseIndex].getCode()
    toDelete = []

    for i in range(0, len(gradeList)):
        if (studentId == gradeList[i].getStudent()) & (courseId == gradeList[i].getCourse()):
            toDelete.append(gradeList[i])

    for i in range(0, len(toDelete)):
        gradeList.remove(toDelete[i])

    print(name, "'s grades for the course of", code, "has been deleted!")
    return


def displayStudentGrades(studentIndex):
    name = studentList[studentIndex].getName()
    studentId = studentList[studentIndex].getId()

    for i in range(0, len(gradeList)):
        if gradeList[i].getStudent() == studentId:
            courseIndex = gradeList[i].getCourse()

            for j in range(0, len(courseList)):
                if courseList[j].getId() == courseIndex:
                    course = courseList[j].getCode()

            print(name, "'s grades for the course of", course, ":")
            print("* Exercises =", gradeList[i].getExercises(), "%")
            print("* Exams =", gradeList[i].getExams(), "%")
            print("* Finals =", gradeList[i].getFinals(), "%")
            print("* Final Grade =", gradeList[i].getFinalGrade())
    return


def studentMenu():
    choice = -1

    while choice != 0:
        cls()
        print("--Students List--")
        displayStudents()
        print("--Options--")
        print("[1] Add a Student")
        print("[2] Edit a Student")
        print("[3] Delete a Student")
        print("[0] Back to Menu")
        choice = int(input("Input: "))

        if choice == 1:
            cls()
            print("--Add a Student--")
            addStudent()
            input("Press Enter to continue...")
        elif choice == 2:
            cls()
            print("--Edit Student Profile--")
            displayStudents()
            student = int(input("Input: "))
            cls()
            editStudent(student - 1)
            input("Press Enter to continue...")
        elif choice == 3:
            cls()
            print("--Delete Student Profile--")
            displayStudents()
            student = int(input("Input: "))
            cls()
            deleteStudent(student - 1)
            input("Press Enter to continue...")
    return


def courseMenu():
    choice = -1

    while choice != 0:
        cls()
        print("--Course List--")
        displayCourses()
        print("--Options--")
        print("[1] Add a Course")
        print("[2] Edit a Course")
        print("[3] Delete a Course")
        print("[0] Back to Menu")
        choice = int(input("Input: "))

        if choice == 1:
            cls()
            print("--Add a Course--")
            addCourse()
            input("Press Enter to continue...")
        elif choice == 2:
            cls()
            print("--Edit Course Details--")
            displayCourses()
            course = int(input("Input: "))
            cls()
            editCourse(course - 1)
            input("Press Enter to continue...")
        elif choice == 3:
            cls()
            print("--Delete Course--")
            displayCourses()
            course = int(input("Input: "))
            cls()
            deleteCourse(course - 1)
            input("Press Enter to continue...")
    return


def gradesMenu():
    choice = -1

    while choice != 0:
        cls()
        print("--View Grades--")
        displayStudents()
        student = int(input("Input: "))
        cls()
        print("--Student's Grades--")
        displayStudentGrades(student - 1)
        print("--Options--")
        print("[1] Add a Gradebook")
        print("[2] Edit a Gradebook")
        print("[3] Delete a Gradebook")
        print("[0] Back to Menu")
        choice = int(input("Input: "))

        if choice == 1:
            cls()
            print("--Add a Gradebook--")
            displayCourses()
            course = int(input("Input: "))
            cls()
            addStudentGrades(student - 1, course - 1)
            input("Press Enter to continue...")
        elif choice == 2:
            cls()
            print("--Edit a Gradebook--")
            displayStudentCourses(student - 1)
            course = int(input("Input: "))
            cls()
            editStudentGrades(student - 1, course - 1)
            input("Press Enter to continue...")
        elif choice == 3:
            cls()
            print("--Delete a Gradebook--")
            displayStudentCourses(student - 1)
            course = int(input("Input: "))
            deleteStudentCourseGrades(student - 1, course - 1)
            input("Press Enter to continue...")
    return


def getStudentGpa():
    studentGpa = []
    print("--Student GPAs--")

    for i in range(0, len(studentList)):
        studentId = studentList[i].getId()
        name = studentList[i].getName()
        finalSum = 0
        courseCnt = 0
        for j in range(0, len(gradeList)):
            if gradeList[j].getStudent() == studentId:
                finalSum += gradeList[j].getFinalGrade()
                courseCnt += 1

        if courseCnt > 0:
            studentGpa.append(finalSum/courseCnt)
        else:
            studentGpa.append(0.0)

        print(i+1, ".", name, "=", studentGpa[i])
    return


def getCourseAvg():
    courseAvg = []
    print("--Course Averages--")

    for i in range(0, len(courseList)):
        courseId = courseList[i].getId()
        code = courseList[i].getCode()
        finalSum = 0
        studentCnt = 0

        for j in range(0, len(gradeList)):
            if gradeList[j].getCourse() == courseId:
                finalSum += gradeList[j].getFinalGrade()
                studentCnt += 1

        if studentCnt > 0:
            courseAvg.append(finalSum/studentCnt)
        else:
            courseAvg.append(0.0)

        print(i+1, ".", code, "=", courseAvg[i])
    return


def getCourseTops():
    print("--Top 5 Students per Course--")
    displayCourses()
    course = int(input("Input: "))
    cls()

    courseId = courseList[course - 1].getId()
    code = courseList[course  -1].getCode()
    topFive = []

    print(code, "Top 5:")

    for i in range(0, len(gradeList)):
        if gradeList[i].getCourse() == courseId:
            topFive.append(gradeList[i])

    newList = sorted(topFive, key=lambda Grades: Grades.finalGrade, reverse=True)

    for i in range(0, 5):
        if i < len(newList):
            studentId = newList[i].getStudent()

            for j in range(0, len(studentList)):
                if studentList[j].getId() == studentId:
                    name = studentList[j].getName()

            print(i+1, ".", name, "=", newList[i].getFinalGrade())
        else:
            print(i+1, ". N/A")
    return


#MAIN
readData()
while choice != 0:
    cls()
    print("--Python Gradebook App--")
    print("[1] View Students")
    print("[2] View Courses")
    print("[3] View a Student's Grades")
    print("[4] View Students' GPAs")
    print("[5] View Average Grade per Course")
    print("[6] View Top 5 Students per Course")
    print("[0] Exit")
    choice = int(input("Input: "))

    if choice == 1:
        cls()
        studentMenu()
    elif choice == 2:
        cls()
        courseMenu()
    elif choice == 3:
        cls()
        gradesMenu()
    elif choice == 4:
        cls()
        getStudentGpa()
        input("Press Enter to continue...")
    elif choice == 5:
        cls()
        getCourseAvg()
        input("Press Enter to continue...")
    elif choice == 6:
        cls()
        getCourseTops()
        input("Press Enter to continue...")

writeData()
