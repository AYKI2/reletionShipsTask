package org.example;

import org.example.config.HibernateConfiguration;
import org.example.models.Course;
import org.example.models.Instructor;
import org.example.models.Lesson;
import org.example.models.Task;
import org.example.services.*;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
     private static CourseService courseService = new CourseServiceImpl();
    private static InstructorService instructorService = new InstructorServiceImpl();
    private static LessonService lessonService = new LessonServiceImpl();
    private static TaskService taskService = new TaskServiceImpl();
    public static void main( String[] args ) {
        HibernateConfiguration.getSession();
        boolean isTrue = true;
        try {
            while (isTrue) {
                System.out.println("""
                        1 - Course
                        2 - Instructor
                        3 - Lesson
                        4 - Task
                        5 - Exit
                        """);
                int choose = new Scanner(System.in).nextInt();
                switch (choose) {
                    case 1 -> {
                        boolean isCourse = true;
                        while (isCourse) {
                            System.out.println("""
                                    ----------Course----------
                                    1 - Save Course
                                    2 - Find Course By Id
                                    3 - Show All Course(sort by creat At)
                                    4 - Update Course
                                    5 - Delete Course By Id
                                    6 - Find Course By Name
                                    7 - Back
                                    """);
                            int number1 = new Scanner(System.in).nextInt();
                            switch (number1) {
                                case 1 -> System.out.println(courseService.saveCourse(createCourse()));
                                case 2 -> System.out.println(courseService.getCourseById(scanId()));
                                case 3 -> {
                                    System.out.println("Select asc or desc: ");
                                    System.out.println(courseService.getAllCourses(new Scanner(System.in).nextLine().toLowerCase()));
                                }
                                case 4 -> {
                                    Long courseId = scanId();
                                    if (courseService.getCourseById(courseId).getId() >= 0) {
                                        System.out.println(courseService.updateCourse(createCourse(), courseId));
                                    } else {
                                        System.out.println("There is no course with this id in the database!");
                                    }
                                }
                                case 5 -> System.out.println(courseService.deleteCourseById(scanId()));
                                case 6 -> {
                                    System.out.println("Enter course name: ");
                                    System.out.println(courseService.getCourseByName(new Scanner(System.in).nextLine()));
                                }
                                case 7 -> isCourse = false;
                                default -> System.out.println("No such number!");

                            }
                        }
                    }
                    case 2 -> {
                        boolean isInstructor = true;
                        while (isInstructor) {
                            System.out.println("""
                                    ----------Instructor----------
                                    1 - Save Instructor
                                    2 - Find Instructor By Id
                                    3 - Find Instructors By Course Id
                                    4 - Show All Instructors
                                    5 - Delete Instructor By Id
                                    6 - Update Instructor
                                    7 - Assign Instructor To Course
                                    8 - Back
                                    """);
                            int number2 = new Scanner(System.in).nextInt();
                            switch (number2) {
                                case 1 -> System.out.println(instructorService.saveInstructor(createInstructor()));
                                case 2 -> System.out.println(instructorService.getInstructorById(scanId()));
                                case 3 -> System.out.println(instructorService.getInstructorsByCourseId(scanId()));
                                case 4 -> System.out.println(instructorService.getAllInstructors());
                                case 5 -> System.out.println(instructorService.deleteInstructorById(scanId()));
                                case 6 -> {
                                    Long instId = scanId();
                                    if (instructorService.getInstructorById(instId).getId() >= 0) {
                                        System.out.println(courseService.updateCourse(createCourse(), instId));
                                    } else {
                                        System.out.println("There is no instructor with this id in the database!");
                                    }
                                    System.out.println(instructorService.updateInstructor(createInstructor(), scanId()));
                                }
                                case 7 -> {
                                    System.out.println("Enter course id: ");
                                    Long courseId = new Scanner(System.in).nextLong();
                                    System.out.println("Enter instructor id: ");
                                    Long instId = new Scanner(System.in).nextLong();
                                    System.out.println(instructorService.assignInstructorToCourse(courseId, instId));
                                }
                                case 8 -> isInstructor = false;
                                default -> System.out.println("No such number!");
                            }
                        }
                    }
                    case 3 -> {
                        boolean isLesson = true;
                        while (isLesson) {
                            System.out.println("""
                                    1 - Save Lesson
                                    2 - Update Lesson
                                    3 - Find Lesson By Id
                                    4 - Find Lessons By Course Id
                                    5 - Back
                                    """);
                            int number3 = new Scanner(System.in).nextInt();
                            switch (number3) {
                                case 1 -> System.out.println(lessonService.saveLesson(createLesson(), scanId()));
                                case 2 -> System.out.println(lessonService.updateLesson(createLesson(), scanId()));
                                case 3 -> System.out.println(lessonService.getLessonById(scanId()));
                                case 4 -> System.out.println(lessonService.getLessonsByCourseId(scanId()));
                                case 5 -> isLesson = false;
                                default -> System.out.println("No such number!");
                            }
                        }
                    }
                    case 4 -> {
                        boolean isTask = true;
                        while (isTask) {
                            System.out.println("""
                                    1 - Save Task
                                    2 - Update Task
                                    3 - Get All Tasks By Lesson Id
                                    4 - Delete Task By Id
                                    5 - Back
                                    """);
                            int number4 = new Scanner(System.in).nextInt();
                            switch (number4) {
                                case 1 -> taskService.saveTask(createTask(), scanId());
                                case 2 -> taskService.updateTask(createTask(), scanId());
                                case 3 -> taskService.getAllTaskByLessonId(scanId());
                                case 4 -> {
                                    System.out.println("Enter lesson id: ");
                                    Long lesId = new Scanner(System.in).nextLong();
                                    System.out.println("Enter task id: ");
                                    Long taskId = new Scanner(System.in).nextLong();
                                    taskService.deleteTaskById(lesId, taskId);
                                }
                                case 5 -> isTask = false;
                                default -> System.out.println("No such number!");
                            }
                        }
                    }
                    case 5 -> isTrue = false;
                    default -> System.out.println("No such number!");
                }
            }
        }catch (InputMismatchException e){
            System.out.println(e.getMessage());
        }
    }
    public static Course createCourse(){
        System.out.println("Enter course name: ");
        String name = new Scanner(System.in).nextLine();
        System.out.println("Enter duration: ");
        int duration = new Scanner(System.in).nextInt();
        System.out.println("Enter created at(Y/M/D):");
        LocalDate createdAt = LocalDate.of(new Scanner(System.in).nextInt(),
                new Scanner(System.in).nextInt(),
                new Scanner(System.in).nextInt());
        System.out.println("Insert link: ");
        String imageLink = new Scanner(System.in).nextLine();
        System.out.println("Enter description: ");
        String description = new Scanner(System.in).nextLine();
        return new Course(name, duration, createdAt, imageLink, description);
    }
    public static Instructor createInstructor(){
        System.out.println("Enter first name: ");
        String firstName = new Scanner(System.in).nextLine();
        System.out.println("Enter last name: ");
        String lastName = new Scanner(System.in).nextLine();
        System.out.println("Enter email: ");
        String email = new Scanner(System.in).nextLine();
        System.out.println("Enter phone number: ");
        String phoneNumber = new Scanner(System.in).nextLine();
        return new Instructor(firstName,lastName,email,phoneNumber);
    }
    public static Task createTask(){
        System.out.println("Enter name task: ");
        String name = new Scanner(System.in).nextLine();
        System.out.println("Enter deadline(Y/M/D): ");
        LocalDate deadLine = LocalDate.of(new Scanner(System.in).nextInt(),
                new Scanner(System.in).nextInt(),
                new Scanner(System.in).nextInt());
        System.out.println("Enter task: ");
        String task = new Scanner(System.in).nextLine();
        return new Task(name,deadLine,task);
    }
    public static Lesson createLesson(){
        System.out.println("Enter name: ");
        String name = new Scanner(System.in).nextLine();
        System.out.println("Input video link: ");
        String videoLink = new Scanner(System.in).nextLine();
        return new Lesson(name,videoLink);
    }
    public static Long scanId(){
        try {
            System.out.println("Enter id: ");
            Long id = new Scanner(System.in).nextLong();
            return id;
        }catch (InputMismatchException e){
            System.out.println("Insert the number!!!");
        }
        return null;
    }
}
