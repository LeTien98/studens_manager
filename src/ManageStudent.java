/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author PHAM KHAC VINH
 */
public class ManageStudent {

    List<Student> listStudent = new ArrayList<>();
    List<Report> listReport = new ArrayList<>();
    Utility valid = new Utility();

    public Student getStudentByID(String id) {
        for (Student student : listStudent) {
            if (student.getId().equalsIgnoreCase(id)) {
                return student;
            }
        }
        return null;
    }

    public void inputStudent() {
        //check size
        if (listStudent.size() > 2) {
            if (checkYesNo() == false) {
                return;
            }
        }

        //input information 
        String id, name;
        int semester, course;

        id = getId();
        if (getStudentByID(id) == null) {
            name = getName();
        } else {
            name = getStudentByID(id).getName();
            System.out.println("Enter name: " + name);
        }
        semester = getSemester();
        course = getCourse();

        if (checkDuplicate(id, name, semester, course)) {
            System.out.println("Duplicate !!");
        } else {
            listStudent.add(new Student(id, name, semester, course));
            System.out.println("Add successfull !!");
        }

    }

    public void findAndSort() {
        //input name
        String name = getName();

        List<Student> listFound = getStudentsByName(name);

        if (listFound.size() == 0) {
            System.out.println("NOT FOUND");
        } else {
            //sort
            Collections.sort(listFound, new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    return o1.getName().compareToIgnoreCase(o2.getName());
                }
            });

            System.out.format("%-10s %-15s %-10s %-10s\n",
                    "ID", "Name", "Semester", "Course");
            for (Student student : listFound) {
                System.out.println(student);
            }
        }

    }

    public void updateOrDelete() {
        //input id
        String id = getId();
        List<Student> listFound = getStudentsByID(id);

        if (listFound.size() == 0) {
            System.out.println("NOT FOUND");
        } else {
            if (checkUpdateDelete()) {
                update(listFound);
            } else {
                delete(listFound);
            }

        }

    }

    private void update(List<Student> listFound) {
        displayListFound(listFound);
        int number = valid.getInt("Enter number: ", "Wrong", "Wrong", "Wrong", 1, listFound.size());
        Student student = listFound.get(number - 1);

        String id = student.getId();
        String name = student.getName();
        int semester = student.getSemester();
        int course = student.getCourse();

        //ask user
        if (checkUpdate("id")) {
            id = getId();
        }

        if (checkUpdate("name")) {
            name = getName();
        }

        if (checkUpdate("semester")) {
            semester = getSemester();
        }

        if (checkUpdate("course")) {
            course = getCourse();
        }
        if (checkNotUpdate(id, name, semester, course, student)) {
            System.out.println("YOU NOT UPDATE !!");
        } else if (checkDuplicate(id, name, semester, course)) {
            System.out.println("Duplicate !! Cannot update  !!");
        } else {
            student.setId(id);
            updateAllName(id, name);
            student.setSemester(semester);
            student.setCourse(course);
        }

    }

    private void delete(List<Student> listFound) {
        displayListFound(listFound);
        int number = valid.getInt("Enter number: ", "Wrong", "Wrong", "Wrong", 1, listFound.size());
        Student student = listFound.get(number - 1);

        listStudent.remove(student);
        System.out.println("Delete successfull !!");
    }

    public void report() {
        listReport.clear();

        for (Student first : listStudent) {
            if (checkExistReport(first) == false) {
                int total = 0;

                for (Student second : listStudent) {
                    if (first.getId().equalsIgnoreCase(second.getId())
                            && first.getCourse() == second.getCourse()) {
                        total++;
                    }
                }

                listReport.add(new Report(first.getId(), first.getName(),
                        first.getCourse(), total));
            }

        }
        System.out.printf("%-20s | %-10s | %-10s\n", "Name", "Course", "Total Course");
        for (Report report : listReport) {
            System.out.println(report);
        }
    }

    public boolean checkDuplicate(String id, String name, int semester, int course) {
        for (Student student : listStudent) {
            if (student.getId().equalsIgnoreCase(id)
                    && student.getName().equalsIgnoreCase(name)
                    && student.getSemester() == semester
                    && student.getCourse() == course) {
                return true;
            }
        }
        return false;
    }

    public List<Student> getStudentsByName(String name) {
        List<Student> listFound = new ArrayList<>();
        name = name.toLowerCase();
        for (Student student : listStudent) {
            if (student.getName().toLowerCase().contains(name)) {
                listFound.add(student);
            }
        }
        return listFound;
    }

    public List<Student> getStudentsByID(String id) {
        List<Student> listFound = new ArrayList<>();
        for (Student student : listStudent) {
            if (student.getId().equalsIgnoreCase(id)) {
                listFound.add(student);
            }
        }
        return listFound;
    }

    public void updateAllName(String id, String name) {
        for (Student student : listStudent) {
            if (student.getId().equalsIgnoreCase(id)) {
                student.setName(name);
            }
        }
    }

    public boolean checkExistReport(Student student) {
        for (Report report : listReport) {
            if (report.getId().equalsIgnoreCase(student.getId())
                    && report.getCourse() == student.getCourse()) {
                return true;
            }
        }
        return false;
    }

    private String getId() {
        String id = valid.getString("Enter id: ", "Wrong", valid.REGEX_STRING);
        return id;
    }

    private String getName() {
        String name = valid.getString("Enter name: ", "Wrong", valid.REGEX_STRING);
        return name;
    }

    private int getSemester() {
        int semester = valid.getInt("Enter semester: ", "Wrong", "Wrong", "Wrong", 1, 10);
        return semester;
    }

    private int getCourse() {
        int course = valid.getInt("Enter course\n (1.Java || 2. .Net || 3. C/C++): ",
                "Wrong", "Wrong", "Wrong", 1, 3);
        return course;
    }

    private boolean checkYesNo() {
        String result = valid.getString("Do you want to continue? ( Y/ N): ", "Wrong", valid.REGEX_YN);

        if (result.equalsIgnoreCase("y")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkUpdateDelete() {
        String result = valid.getString("Do you want to UPDATE or DELETE? ( U/ D): ",
                "Wrong", valid.REGEX_UD);

        if (result.equalsIgnoreCase("u")) {
            return true;
        } else {
            return false;
        }
    }

    public void displayListFound(List<Student> listFound) {
        System.out.format("%-10s %-10s %-15s %-10s %-10s\n",
                "No", "ID", "Name", "Semester", "Course");
        for (int i = 0; i < listFound.size(); i++) {
            Student student = listFound.get(i);
            System.out.format("%-10s %-10s %-15s %-10s %-10s\n",
                    i + 1, student.getId(), student.getName(), student.getSemester(),
                    student.getCourseString());
        }
    }

    private boolean checkUpdate(String property) {
        String result = valid.getString("Do you want to update " + property + " ? ( Y/ N): ", "Wrong", valid.REGEX_YN);

        if (result.equalsIgnoreCase("y")) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkNotUpdate(String id, String name, int semester, int course, Student student) {
        if (student.getId().equalsIgnoreCase(id)
                && student.getName().equalsIgnoreCase(name)
                && student.getSemester() == semester
                && student.getCourse() == course) {
            return true;
        }
        return false;
    }

}
