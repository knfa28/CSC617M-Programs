class Course(object):
    def __init__(self, id, code, name):
        self.id = id
        self.code = code
        self.name = name

    def __repr__(self):
        return repr((self.id, self.code, self.name))

    def getId(self):
        return self.id

    def getCode(self):
        return self.code

    def getName(self):
        return self.name

    def setId(self, id):
        self.id = id

    def setCode(self, code):
        self.code = code

    def setName(self, name):
        self.name = name