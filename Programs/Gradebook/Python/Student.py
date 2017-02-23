class Student(object):
    def __init__(self, id, name, gender):
        self.id = id
        self.name = name
        self.gender = gender

    def __repr__(self):
        return repr((self.id, self.name, self.gender))

    def getId(self):
        return self.id

    def getName(self):
        return self.name

    def getGender(self):
        return self.gender

    def setId(self, id):
        self.id = id

    def setName(self, name):
        self.name = name

    def setGender(self, gender):
        self.gender = gender
