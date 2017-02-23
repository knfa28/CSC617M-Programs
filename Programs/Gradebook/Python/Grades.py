class Grades(object):
    def __init__(self, student, course, exercises, exams, finals, finalGrade):
        self.student = student
        self.course = course
        self.exercises = exercises
        self.exams = exams
        self.finals = finals
        self.finalGrade = finalGrade

    def __repr__(self):
        return repr((self.student, self.course, self.exercises, self.exams, self.finals, self.finalGrade))

    def getStudent(self):
        return self.student

    def getCourse(self):
        return self.course

    def getExercises(self):
        return self.exercises

    def getExams(self):
        return self.exams

    def getFinals(self):
        return self.finals

    def getFinalGrade(self):
        return self.finalGrade

    def setStudent(self, student):
        self.student = student

    def setCourse(self, course):
        self.course = course

    def setExercises(self, exercises):
        self.exercises = exercises

    def setExams(self, exams):
        self.exams = exams

    def setFinals(self, finals):
        self.finals = finals

    def setFinalGrade(self, finalGrade):
        self.finalGrade = finalGrade
