using System;
using System.Collections;
using System.IO;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Gradebook
{
    class Student
    {
        private String name;
        private int id;
        private String gender;

        public Student()
        {

        }

        public Student(int id, String name, String gender)
        {
            this.id = id;
            this.name = name;
            this.gender = gender;
        }

        public int getID()
        {
            return this.id;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public void setGender(String gender)
        {
            this.gender = gender;
        }

        public String getName()
        {
            return this.name;
        }

        public String getGender()
        {
            return this.gender;
        }
    }

    class Course
    {
       private String name;
       private int id;
       private String code;
        

        public Course()
        {
            
        }
       public Course(int id, String name, String code)
       {
            this.id = id;
            this.name = name;
            this.code = code;
       }

        public int getID()
        {
            return this.id;
        }

       public void setName(String name)
       {
            this.name = name;
       }

       public void setCode(String code)
       {
            this.code = code;
       }

       public String getName()
       {
            return this.name;
       }

       public String getCode()
       {
            return this.code;
       }
    }

    class Grades
    {
        private int sID;
        private int cID;
        private float exercises;
        private float exams;
        private float finals;
        private float grade_final;

        public Grades(int sID, int cID, float exercises, float exams, float finals, float grade_final)
        {
            this.sID = sID;
            this.cID = cID;
            this.exercises = exercises;
            this.exams = exams;
            this.finals = finals;
            this.grade_final = grade_final;
        }

        public int SID
        {
            get
            {
                return sID;
            }

            set
            {
                sID = value;
            }
        }

        public int CID
        {
            get
            {
                return cID;
            }

            set
            {
                cID = value;
            }
        }

        public float Exercises
        {
            get
            {
                return exercises;
            }

            set
            {
                exercises = value;
            }
        }

        public float Exams
        {
            get
            {
                return exams;
            }

            set
            {
                exams = value;
            }
        }

        public float Finals
        {
            get
            {
                return finals;
            }

            set
            {
                finals = value;
            }
        }

        public float Grade_final
        {
            get
            {
                return grade_final;
            }

            set
            {
                grade_final = value;
            }
        }
    }

    class Student_Handler
    {
        private ArrayList als = new ArrayList();
        
        public Student_Handler()
        {
            String line;
            try
            {
                StreamReader sr = new StreamReader("Resources\\Student.txt");
                String[] delimiters = new string[] { "," };
                line = sr.ReadLine();

                while (line != null)
                {
                    String[] sContents = line.Split(delimiters, StringSplitOptions.RemoveEmptyEntries);
                    Student student = new Student(Int32.Parse(sContents[0]), sContents[1], sContents[2]);
                    Console.WriteLine(student);
                    als.Add(student);
                    line = sr.ReadLine();
                    sr.Close();
                }
            }catch(Exception e)
            {
                Console.WriteLine("Exception" + e.Message);
            }
            
        }

        public Student getStudent(int id)
        {
            return (Student) als[id];
        }

        public ArrayList getAllStudents()
        {
            return this.als;
        }

        public void AddStudent(String name, String gender)
        {
            als.Add(new Student(als.Count + 1, name, gender));
        }

        public void EditStudent(int id, String name, String gender)
        {
            if (id <= als.Count)
            {
                als[id] = new Student(id, name, gender);
            }
            else
            {
                Console.WriteLine("Student ID does not exist in the list.");
            }
        }

        public void deleteStudent(int id)
        {
            als.RemoveAt(id);
        }
    }

    class Course_Handler
    {
        private ArrayList cal = new ArrayList();

        public Course_Handler()
        {
            String line;
            try
            {
                StreamReader sr = new StreamReader("Resources\\Courses.txt");
                String[] delimiters = new string[] { "," };
                line = sr.ReadLine();

                while (line != null)
                {
                    String[] sContents = line.Split(delimiters, StringSplitOptions.RemoveEmptyEntries);
                    Course course= new Course(Int32.Parse(sContents[0]), sContents[1], sContents[2]);
                    cal.Add(course);
                    line = sr.ReadLine();
                }
                sr.Close();
            }
            catch (Exception e)
            {
                Console.WriteLine("Exception" + e.Message);
            }
        }

        public Course getCourse(int id)
        {
            return (Course)cal[id];
        }

        public ArrayList getAllCourses()
        {
            return this.cal;
        }

        public void AddCourse(String name, String code)
        {
            cal.Add(new Student(cal.Count + 1, name, code));
        }

        public void EditCourse(int id, String name, String gender)
        {
            if (id <= cal.Count)
            {
                cal[id] = new Student(id, name, gender);
            }
            else
            {
                Console.WriteLine("Student ID does not exist in the list.");
            }
        }

        public void deleteCourse(int id)
        {
            cal.RemoveAt(id);
        }
    }

    class Grades_Handler
    {
        private ArrayList gal = new ArrayList();

        public Grades_Handler()
        {
            String line;
            try
            {
                StreamReader sr = new StreamReader("Resources\\Grades.txt");
                String[] delimiters = new string[] { "," };
                line = sr.ReadLine();

                while (line != null)
                {
                    String[] sContents = line.Split(delimiters, StringSplitOptions.RemoveEmptyEntries);
                    Grades grades = new Grades(Int32.Parse(sContents[0]), Int32.Parse(sContents[1]),Single.Parse(sContents[2]), Single.Parse(sContents[3]), Single.Parse(sContents[4]), Single.Parse(sContents[5]));
                    gal.Add(grades);
                    line = sr.ReadLine();
                }
                sr.Close();
            }
            catch (Exception e)
            {
                Console.WriteLine("Exception" + e.Message);
            }
        }

        public ArrayList getAllGrades()
        {
            return this.gal;
        }

        public Grades getGradesFromStudentAndCourse(int sID, int cID)
        {
            Grades tempGrades = null;
            foreach(Grades grades in gal)
            {
                if (grades.SID == sID && grades.CID == cID)
                {
                    tempGrades = grades;
                    break;
                }
            }

            return tempGrades;
        }

        public ArrayList getGradesFromStudent(int sID)
        {
            ArrayList tempList = new ArrayList();

            foreach(Grades grades in gal)
            {
                if(grades.SID == sID)
                {
                    tempList.Add(grades);
                }
            }
            return tempList;
        }

        public ArrayList getGradesFromCourse(int cID)
        {
            ArrayList tempList = new ArrayList();

            foreach (Grades grades in gal)
            {
                if (grades.CID == cID)
                {
                    tempList.Add(grades);
                }
            }
            return tempList;
        }

        public void addGrades(Grades grades)
        {
            gal.Add(grades);
        }

        public void editGrades(int sID, int cID, Grades grades)
        {
            gal[gal.IndexOf(getGradesFromStudentAndCourse(sID, cID))] = grades;
        }

        public void deleteGrades(int sID, int cID)
        {
            gal.RemoveAt(gal.IndexOf(getGradesFromStudentAndCourse(sID, cID)));
        }
    }

    class Program
    {
        static void studentOption(Student_Handler sH)
        {
            String sInput;
            int input2;
            int tempID;
            do
            {
                Console.WriteLine("1. Add Student");
                Console.WriteLine("2. Edit Student");
                Console.WriteLine("3. Delete Student");
                Console.WriteLine("0. Back");

                Student student = new Student();

                sInput = Console.ReadLine();
                int.TryParse(sInput, out input2);

                Console.Clear();


                switch (input2)
                {
                    case 0:
                        break;
                    case 1:
                        Console.WriteLine("Name?");
                        student.setName(Console.ReadLine());
                        Console.WriteLine("Gender?");
                        student.setGender(Console.ReadLine());

                        sH.AddStudent(student.getName(), student.getGender());
                        break;
                    case 2:

                        foreach (Student tempStudent in sH.getAllStudents())
                        {
                            Console.WriteLine("ID: " + tempStudent.getID());
                        }
                        Console.WriteLine("Which one will you edit?");
                        sInput = Console.ReadLine();
                        int.TryParse(sInput, out tempID);
                        Console.WriteLine("Name?");
                        student.setName(Console.ReadLine());
                        Console.WriteLine("Gender?");
                        student.setGender(Console.ReadLine());

                        sH.EditStudent(tempID, student.getName(), student.getGender());

                        break;
                    case 3:
                        foreach (Student tempStudent in sH.getAllStudents())
                        {
                            Console.WriteLine("ID: " + tempStudent.getID());
                        }
                        Console.WriteLine("Which one will you delete?");
                        sInput = Console.ReadLine();
                        int.TryParse(sInput, out tempID);

                        sH.deleteStudent(tempID);

                        break;
                }

            } while (input2 != 0);
        }

        static void courseOption(Course_Handler cH)
        {
            int tempID;
            int input2;
            String sInput;
            do
            {
                Console.WriteLine("1. Add Course");
                Console.WriteLine("2. Edit Course");
                Console.WriteLine("3. Delete Course");
                Console.WriteLine("0. Back");

                Course course = new Course();

                sInput = Console.ReadLine();
                int.TryParse(sInput, out input2);

                Console.Clear();

                switch (input2)
                {
                    case 0:
                        break;
                    case 1:
                        Console.WriteLine("Name?");
                        course.setName(Console.ReadLine());
                        Console.WriteLine("Code?");
                        course.setCode(Console.ReadLine());

                        cH.AddCourse(course.getName(), course.getCode());
                        break;
                    case 2:

                        foreach (Course tempCourse in cH.getAllCourses())
                        {
                            Console.WriteLine("ID: " + tempCourse.getID());
                        }
                        Console.WriteLine("Which one will you edit?");
                        sInput = Console.ReadLine();
                        int.TryParse(sInput, out tempID);
                        Console.WriteLine("Name?");
                        course.setName(Console.ReadLine());
                        Console.WriteLine("Code?");
                        course.setCode(Console.ReadLine());

                        cH.EditCourse(tempID, course.getName(), course.getCode());

                        break;
                    case 3:
                        foreach (Course tempCourse in cH.getAllCourses())
                        {
                            Console.WriteLine("ID: " + tempCourse.getID());
                        }
                        Console.WriteLine("Which one will you delete?");
                        sInput = Console.ReadLine();
                        int.TryParse(sInput, out tempID);

                        cH.deleteCourse(tempID);

                        break;
                }

            } while (input2 != 0);
        }

        static void gradeOption(Grades_Handler gH, Student_Handler sH, Course_Handler cH)
        {

            String sInput;
            int sID, options, cID;
            do
            {

                Console.WriteLine("Which student's grade do you want to see?");

                foreach (Student student in sH.getAllStudents())
                {
                    Console.WriteLine("ID: " + student.getID());
                }

                sInput = Console.ReadLine();
                int.TryParse(sInput, out sID);

                ArrayList courses = cH.getAllCourses();

                foreach (Grades grades in gH.getGradesFromStudent(sID))
                {
                    Course course = (Course)courses[grades.CID];
                    Console.WriteLine("Course: " + course.getName());
                    Console.WriteLine("Exercises Grade: " + grades.Exercises);
                    Console.WriteLine("Exams Grade: " + grades.Exams);
                    Console.WriteLine("Finals Exam Grade: " + grades.Finals);
                    Console.WriteLine("Final Grade: " + grades.Grade_final);
                    Console.WriteLine();
                }

                Console.WriteLine("1. Add a gradebook");
                Console.WriteLine("2. Edit a gradebook");
                Console.WriteLine("3. Delete a gradebook");
                Console.WriteLine("4. Back");

                sInput = Console.ReadLine();
                int.TryParse(sInput, out options);
                Console.Clear();

                float exercises, exams, finals_exam, final_grade;
                switch (options)
                {
                    case 1:
                        foreach (Course course in cH.getAllCourses())
                        {
                            Console.WriteLine(course.getID() + " " + course.getName());
                        }

                        sInput = Console.ReadLine();
                        int.TryParse(sInput, out cID);

                        Console.WriteLine("Exercises: ");
                        sInput = Console.ReadLine();
                        Single.TryParse(sInput, out exercises);
                        Console.WriteLine("Exams: ");
                        sInput = Console.ReadLine();
                        Single.TryParse(sInput, out exams);
                        Console.WriteLine("Finals Exams: ");
                        sInput = Console.ReadLine();
                        Single.TryParse(sInput, out finals_exam);
                        Console.WriteLine("Final Grade: ");
                        Single.TryParse(sInput, out final_grade);

                        gH.addGrades(new Grades(sID, cID, exercises, exams, finals_exam, final_grade));
                        break;

                    case 2:
                        foreach (Course course in cH.getAllCourses())
                        {
                            Console.WriteLine(course.getID() + " " + course.getName());
                        }

                        sInput = Console.ReadLine();
                        int.TryParse(sInput, out cID);

                        Console.WriteLine("Exercises: ");
                        sInput = Console.ReadLine();
                        Single.TryParse(sInput, out exercises);
                        Console.WriteLine("Exams: ");
                        sInput = Console.ReadLine();
                        Single.TryParse(sInput, out exams);
                        Console.WriteLine("Finals Exams: ");
                        sInput = Console.ReadLine();
                        Single.TryParse(sInput, out finals_exam);
                        Console.WriteLine("Final Grade: ");
                        Single.TryParse(sInput, out final_grade);

                        gH.editGrades(sID, cID, new Grades(sID, cID, exercises, exams,
                            finals_exam, final_grade));
                        break;

                    case 3:
                        foreach (Course course in cH.getAllCourses())
                        {
                            Console.WriteLine(course.getID() + " " + course.getName());
                        }

                        sInput = Console.ReadLine();
                        int.TryParse(sInput, out cID);

                        gH.deleteGrades(sID, cID);

                        break;
                }

            } while (options != 0);    
        }

        static void GPAOption(Grades_Handler gH, Student_Handler sH)
        {

            foreach(Student student in sH.getAllStudents())
            {
                float finalSum = 0;
                int courseCnt = 0;

                foreach(Grades grades in gH.getGradesFromStudent(student.getID()))
                {
                    finalSum += grades.Grade_final;
                    courseCnt++;
                }

                Console.WriteLine(student.getName() + "'s GPA: " + finalSum / courseCnt);
                Console.WriteLine("Press any key to continue....");
                Console.ReadKey();
            }
        }

        static void PerCourseOption(Grades_Handler gH, Course_Handler cH)
        {
            foreach (Course course in cH.getAllCourses())
            {
                float finalSum = 0;
                int studentCnt = 0;

                foreach (Grades grades in gH.getGradesFromCourse(course.getID()))
                {
                    finalSum += grades.Grade_final;
                    studentCnt++;
                }

                Console.WriteLine(course.getName() + "'s Average GPA: " + finalSum / studentCnt);
                Console.WriteLine("Press any key to continue....");
                Console.ReadKey();
            }
        }

        struct Top5
        {
            public float Grade;
            public String name;
        };

        static void TopStudentsOption(Grades_Handler gH, Course_Handler cH, Student_Handler sH)
        {
            String sInput;
            int cID;
            Console.WriteLine("Which course?");
            foreach(Course course in cH.getAllCourses())
            {
                Console.WriteLine(course.getID() + ".");
            }

            sInput = Console.ReadLine();
            int.TryParse(sInput, out cID);  

            List<Top5> Top5List = new List<Top5>();

            foreach(Student student in sH.getAllStudents())
            {
                Grades grades = gH.getGradesFromStudentAndCourse(student.getID(), cID);
                if (grades != null)
                {
                    if (Top5List.Capacity != 5)
                    {
                        Top5List.Add(new Top5() { name = student.getName(), Grade = grades.Grade_final });
                    }
                    else {
                        foreach (Top5 top5 in Top5List)
                        {
                            if (grades.Grade_final > top5.Grade)
                            {
                                Top5List.Remove(top5);
                                Top5List.Add(new Top5() { name = student.getName(), Grade = grades.Grade_final });
                            }
                            break;
                        }
                    }
                }
            }

            Top5List.OrderBy(Top5=>Top5.Grade);

            Console.WriteLine("Top 5 is: ");

            foreach(Top5 top5 in Top5List)
            {
                Console.WriteLine(top5.name + " " + top5.Grade);
            }

            Console.WriteLine("Press any key to continue....");
            Console.ReadKey();

        }

        static void Main(string[] args)
        {
            Student_Handler sH = new Student_Handler();
            Course_Handler cH = new Course_Handler();
            Grades_Handler gH = new Grades_Handler();

            int input;
            do
            {
                Console.Clear();
                Console.WriteLine("What do you want to do?");
                Console.WriteLine("1. Students");
                Console.WriteLine("2. Courses");
                Console.WriteLine("3. Grades");
                Console.WriteLine("4. GPA per Student");
                Console.WriteLine("5. GPA per Course");
                Console.WriteLine("6. Top 5 Students per Course");
                Console.WriteLine("0. Exit");

                String sInput = Console.ReadLine();
                int.TryParse(sInput, out input);

                Console.Clear();

                switch (input)
                {
                    case 0:
                        break;
                    case 1:
                        studentOption(sH);
                        break;
                    case 2:
                        courseOption(cH);
                        break;
                    case 3:
                        gradeOption(gH, sH, cH);
                        break;
                    case 4:
                        GPAOption(gH, sH);
                        break;
                    case 5:
                        PerCourseOption(gH, cH);
                        break;
                    case 6:
                        TopStudentsOption(gH, cH, sH);
                        break;
                    default:
                        Console.WriteLine("INVALID INPUT!");
                        break;
                }

                
            } while (input != 0);

            List<String> students = new List<String>();

            foreach(Student student in sH.getAllStudents())
            {
                students.Add(student.getID().ToString() + "," + student.getName() + "," + student.getGender());
            }

            String[] sToFile;
            sToFile = (String[]) students.ToArray();

            File.WriteAllLines("Resources\\Student.txt", sToFile);
            
            List<String> courses = new List<String>();

            foreach(Course course in cH.getAllCourses())
            {
                courses.Add(course.getID().ToString() + "," + course.getName() + "," + course.getCode());
            }

            String[] cToFile;
            cToFile = (String[])courses.ToArray();

            File.WriteAllLines("Resources\\Courses.txt", cToFile);

            List<String> gradesList = new List<String>();

            foreach(Grades grades in gH.getAllGrades())
            {
                gradesList.Add(grades.SID.ToString() + "," + grades.CID.ToString() + "," + grades.Exercises.ToString() + "," + grades.Exams.ToString() + "," + grades.Finals.ToString() + "," + grades.Grade_final.ToString());
            }

            String[] gToFile;
            gToFile = (String[])gradesList.ToArray();

            File.WriteAllLines("Resources\\Grades.txt", gToFile);


            Environment.Exit(0);
        }
    }
}
