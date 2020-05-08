import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class Education {
    private JPanel GradeCalc;
    private JButton inputStudentScoresButton;
    private JButton checkStudentGradeButton;
    private JButton setGradeMeasurementButton;

    public boolean isInteger( String input )
    {
        try
        {
            Double.parseDouble( input );
            return true;
        }
        catch( Exception e )
        {
            return false;
        }
    }

public static class Global{
    static HashMap<String, ArrayList<Double>> gradesStudent = new HashMap<String, ArrayList<Double>>();


    static int ExamTotal = 100;
    static int TestTotal = 100;
    static int PTTotal = 100;
    static int AssignTotal = 100;

    static double ExamTotalP = 0.6;
    static double TestTotalP = 0.2;
    static double PTTotalP = 0.1;
    static double AssignTotalP = 0.1;
    }

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Education");
        frame.setContentPane(new Education().GradeCalc);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        File gradesTransfer = new File("OldGrades.txt");
        BufferedReader readGrades = new BufferedReader(new FileReader(gradesTransfer));
        String fileLines;
        String name ="";

        while((fileLines = readGrades.readLine())!= null){
            ArrayList <Double> grades = new ArrayList<Double>();
            String [] linesString = fileLines.split(" ");
            for(String x: linesString){
                try{
                    grades.add(Double.parseDouble(x));
                }catch (Exception e){
                    name = x;
                }
            }
            Global.gradesStudent.put(name, grades);
        }
    }

    public Education() {
        inputStudentScoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JPanel GradesPanel = new JPanel();
                JTextField StudentName = new JTextField(5);
                JTextField Exam = new JTextField(5);
                JTextField Test = new JTextField(5);
                JTextField PT = new JTextField(5);
                JTextField Assignment = new JTextField(5);

                GradesPanel.add(new JLabel("Student's Last Name:"));
                GradesPanel.add(StudentName);

                GradesPanel.setLayout(new BoxLayout(GradesPanel, BoxLayout.Y_AXIS));
                GradesPanel.add(new JLabel("Exam"));
                GradesPanel.add(Exam);

                GradesPanel.add(new JLabel("Test"));
                GradesPanel.add(Test);

                GradesPanel.add(new JLabel("Performance Tasks"));
                GradesPanel.add(PT);

                GradesPanel.add(new JLabel("Assignment"));
                GradesPanel.add(Assignment);

                int option = JOptionPane.showConfirmDialog(null, GradesPanel);

                while (option == 0) {


                    while (!isInteger(Exam.getText()) && option == 0) {
                        JOptionPane.showMessageDialog(null, "Exam score is not a number.");
                        option = JOptionPane.showConfirmDialog(null, GradesPanel);
                    }
                    while (!isInteger(Test.getText()) && option == 0) {
                        JOptionPane.showMessageDialog(null, "Test score is not a number.");
                        option = JOptionPane.showConfirmDialog(null, GradesPanel);
                    }
                    while (!isInteger(PT.getText()) && option == 0) {
                        JOptionPane.showMessageDialog(null, "Performance Tasks score is not a number.");
                        option = JOptionPane.showConfirmDialog(null, GradesPanel);
                    }
                    while (!isInteger(Assignment.getText()) && option == 0) {
                        JOptionPane.showMessageDialog(null, "Assignments score is not a number.");
                        option = JOptionPane.showConfirmDialog(null, GradesPanel);
                    }

                    if (option == 0 && isInteger(Exam.getText())  && isInteger(Test.getText())  && isInteger(PT.getText())  && isInteger(Assignment.getText())) {
                        double NumExam = Double.parseDouble(Exam.getText());
                        double NumTest = Double.parseDouble(Test.getText());
                        double NumPT = Double.parseDouble(PT.getText());
                        double NumAssignment = Double.parseDouble(Assignment.getText());
                        String Student = StudentName.getText().toUpperCase();

                        while (StudentName.getText().equals("") && option == 0) {
                            JOptionPane.showMessageDialog(null, "Please input name");
                            option = JOptionPane.showConfirmDialog(null, GradesPanel);
                        }
                        if (isInteger(Exam.getText())) {
                            NumExam = Double.parseDouble(Exam.getText());
                            while ((NumExam > Global.ExamTotal) && option == 0 && isInteger(Exam.getText())) {
                                JOptionPane.showMessageDialog(null, "Exam score input greater than total exam score");
                                option = JOptionPane.showConfirmDialog(null, GradesPanel);
                                if (isInteger(Exam.getText())) {
                                    NumExam = Double.parseDouble(Exam.getText());
                                }
                            }
                        }
                        if (isInteger(Test.getText())) {
                            NumTest = Double.parseDouble(Test.getText());
                            while (NumTest > Global.TestTotal && option == 0 && isInteger(Test.getText())) {
                                JOptionPane.showMessageDialog(null, "Test score input greater than total Test score");
                                option = JOptionPane.showConfirmDialog(null, GradesPanel);
                                if (isInteger(Test.getText())) {
                                    NumTest = Double.parseDouble(Test.getText());
                                }
                            }
                        }
                        if(isInteger(PT.getText())){
                            NumPT = Double.parseDouble(PT.getText());
                        while (NumPT > Global.PTTotal && option == 0&& isInteger(PT.getText())) {
                            JOptionPane.showMessageDialog(null, "Performance Tasks score input greater than total Performance tasks score");
                            option = JOptionPane.showConfirmDialog(null, GradesPanel);
                            if (isInteger(PT.getText())) {
                                NumPT = Double.parseDouble(PT.getText());
                            }
                        }
                        }
                        if (isInteger(Assignment.getText())){
                            NumAssignment = Double.parseDouble(Assignment.getText());
                        while (NumAssignment > Global.AssignTotal && option == 0&& isInteger(Assignment.getText())) {
                            JOptionPane.showMessageDialog(null, "Assignments score input greater than total Assignments score");
                            option = JOptionPane.showConfirmDialog(null, GradesPanel);
                            if (isInteger(Assignment.getText())) {
                                NumAssignment = Double.parseDouble(Assignment.getText());
                            }
                        }
                        }


                        if (option == 0 && NumExam <= Global.ExamTotal && NumTest <= Global.TestTotal && NumPT <= Global.PTTotal && NumAssignment <= Global.AssignTotal){
                            Student = StudentName.getText().toUpperCase();
                            double PExam = ((NumExam / Global.ExamTotal) * Global.ExamTotalP);
                            double PTest = ((NumTest / Global.TestTotal) * Global.TestTotalP);
                            double PPT = ((NumPT / Global.PTTotal) * Global.PTTotalP);
                            double PAssign = ((NumAssignment / Global.AssignTotal) * Global.AssignTotalP);
                            double Grade = ((PExam + PTest + PPT + PAssign) * 100);
                            ArrayList<Double> scoreList = new ArrayList<Double>();
                            String DupLine;
                            String fileGrades = Student + " "+ NumExam + " "+NumTest + " " + NumPT+ " " +NumAssignment + " "+Grade+"\n";

                            File duplicate = new File("OldGrades.txt");
                            File studentsGrades = new File("NewGrades.txt");

                            if(!(studentsGrades.exists())) {
                                try {
                                    boolean a = studentsGrades.createNewFile();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }

                            try {
                                if(duplicate.exists()) {
                                    BufferedReader reader = new BufferedReader(new FileReader(duplicate));
                                    BufferedWriter gradeWriter = new BufferedWriter(new FileWriter(studentsGrades));
                                    while ((DupLine = reader.readLine()) != null) {
                                        if (DupLine.contains(Student)) {
                                            continue;
                                        }
                                        gradeWriter.write(DupLine+"\n");
                                    }
                                    gradeWriter.write(fileGrades);
                                    gradeWriter.close();
                                    reader.close();
                                    boolean d = duplicate.delete();
                                }
                                else {
                                    BufferedWriter gradeWriter = new BufferedWriter(new FileWriter(studentsGrades));
                                    gradeWriter.write(fileGrades);
                                    gradeWriter.close();
                                }
                                boolean b = studentsGrades.renameTo(new File("OldGrades.txt"));
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }


                            scoreList.add(NumExam);
                            scoreList.add(NumTest);
                            scoreList.add(NumPT);
                            scoreList.add(NumAssignment);
                            scoreList.add(Grade);
                            if(Global.gradesStudent.containsKey(Student)){
                                Global.gradesStudent.remove(Student);
                            }
                            Global.gradesStudent.put(Student, scoreList);
                            JOptionPane.showMessageDialog(null, Student + "\'s grade is " + Grade, "Grade", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                    }
                }
            }
        });
        setGradeMeasurementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JPanel GradesMeasure = new JPanel();
                JTextField PExam = new JTextField(""+Global.ExamTotalP);
                JTextField PTest = new JTextField(""+Global.TestTotalP);
                JTextField PPT = new JTextField(""+Global.PTTotalP);
                JTextField PAssign = new JTextField(""+Global.AssignTotalP);

                JTextField TExam = new JTextField(""+Global.ExamTotal);
                JTextField TTest = new JTextField(""+Global.TestTotal);
                JTextField TPT = new JTextField(""+Global.PTTotal);
                JTextField TAssign = new JTextField(""+Global.AssignTotal);
                GradesMeasure.setLayout(new BoxLayout(GradesMeasure, BoxLayout.Y_AXIS));
                GradesMeasure.add(new JLabel("Exam Percentage Equivalent: "));
                GradesMeasure.add(PExam);
                GradesMeasure.add(new JLabel("Total Exam Score: "));
                GradesMeasure.add(TExam);

                GradesMeasure.add(new JLabel("Test Percentage Equivalent: "));
                GradesMeasure.add(PTest);
                GradesMeasure.add(new JLabel("Total Tests Score: "));
                GradesMeasure.add(TTest);

                GradesMeasure.add(new JLabel("Performance Tasks Percentage Equivalent: "));
                GradesMeasure.add(PPT);
                GradesMeasure.add(new JLabel("Total Performance Tasks Score: "));
                GradesMeasure.add(TPT);

                GradesMeasure.add(new JLabel("Assignment Percentage Equivalent: "));
                GradesMeasure.add(PAssign);
                GradesMeasure.add(new JLabel("Total Assignments Score: "));
                GradesMeasure.add(TAssign);

                int option = JOptionPane.showConfirmDialog(null, GradesMeasure);
                while(option==0) {
                    while (!isInteger(TExam.getText()) && option == 0) {
                        JOptionPane.showMessageDialog(null, "Exam score is not a number.");
                        option = JOptionPane.showConfirmDialog(null, GradesMeasure);
                    }
                    while (!isInteger(PExam.getText()) && option == 0) {
                        JOptionPane.showMessageDialog(null, "Exam percentage is not a number.");
                        option = JOptionPane.showConfirmDialog(null, GradesMeasure);
                    }
                    while (!isInteger(TTest.getText()) && option == 0) {
                        JOptionPane.showMessageDialog(null, "Tests score is not a number.");
                        option = JOptionPane.showConfirmDialog(null, GradesMeasure);
                    }
                    while (!isInteger(PTest.getText()) && option == 0) {
                        JOptionPane.showMessageDialog(null, "Test percentage is not a number.");
                        option = JOptionPane.showConfirmDialog(null, GradesMeasure);
                    }
                    while (!isInteger(TPT.getText()) && option == 0) {
                        JOptionPane.showMessageDialog(null, "Performance tasks score is not a number.");
                        option = JOptionPane.showConfirmDialog(null, GradesMeasure);
                    }
                    while (!isInteger(PPT.getText()) && option == 0) {
                        JOptionPane.showMessageDialog(null, "Performance tasks percentage is not a number.");
                        option = JOptionPane.showConfirmDialog(null, GradesMeasure);
                    }
                    while (!isInteger(TAssign.getText()) && option == 0) {
                        JOptionPane.showMessageDialog(null, "Assignments score is not a number.");
                        option = JOptionPane.showConfirmDialog(null, GradesMeasure);
                    }
                    while (!isInteger(PAssign.getText()) && option == 0) {
                        JOptionPane.showMessageDialog(null, "Assignments percentage is not a number.");
                        option = JOptionPane.showConfirmDialog(null, GradesMeasure);
                    }
                    while(isInteger(PExam.getText()) && isInteger(PTest.getText()) && isInteger(PPT.getText()) && isInteger(PAssign.getText()) && option == 0){
                        if(Double.parseDouble(PExam.getText()) + Double.parseDouble(PTest.getText()) + Double.parseDouble(PPT.getText()) +Double.parseDouble(PAssign.getText()) <= 1
                                && Double.parseDouble(PExam.getText()) + Double.parseDouble(PTest.getText()) + Double.parseDouble(PPT.getText()) +Double.parseDouble(PAssign.getText())>=0.999) {
                            break;
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Percentages equivalent does not add up to 1.");
                            option = JOptionPane.showConfirmDialog(null, GradesMeasure);
                        }
                    }
                    if(option == 0 && isInteger(TExam.getText()) && isInteger(PExam.getText()) && isInteger(TTest.getText()) && isInteger(PTest.getText()) && isInteger(TPT.getText())
                            && isInteger(PPT.getText()) && isInteger(TAssign.getText()) && isInteger(PAssign.getText())){
                        Global.ExamTotal = Integer.parseInt(TExam.getText());
                        Global.ExamTotalP = Double.parseDouble(PExam.getText());
                        Global.TestTotal = Integer.parseInt(TTest.getText());
                        Global.TestTotalP = Double.parseDouble(PTest.getText());
                        Global.PTTotal = Integer.parseInt(TPT.getText());
                        Global.PTTotalP = Double.parseDouble(PPT.getText());
                        Global.AssignTotal = Integer.parseInt(TAssign.getText());
                        Global.AssignTotalP = Double.parseDouble(PAssign.getText());
                        break;
                    }
                }
            }
        });
        checkStudentGradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel inputStudent = new JPanel();
                JTextField Tstudent = new JTextField(5);
                inputStudent.add(new JLabel("Enter student last name"));
                inputStudent.add(Tstudent);
                JPanel GradesList = new JPanel();
                GradesList.setLayout(new BoxLayout(GradesList, BoxLayout.Y_AXIS));


                int option = JOptionPane.showConfirmDialog(null, inputStudent);
                String student = Tstudent.getText().toUpperCase();

                while (option == 0){
                if(Global.gradesStudent.containsKey(student)) {
                    JTextField ExamScore = new JTextField(""+Global.gradesStudent.get(student).get(0));
                    JTextField TestScore = new JTextField(""+Global.gradesStudent.get(student).get(1));
                    JTextField PTScore = new JTextField(""+Global.gradesStudent.get(student).get(2));
                    JTextField AssignScore = new JTextField(""+Global.gradesStudent.get(student).get(3));
                    JTextField Grades = new JTextField(""+Global.gradesStudent.get(student).get(4));

                    ExamScore.setEditable(false);
                    TestScore.setEditable(false);
                    PTScore.setEditable(false);
                    AssignScore.setEditable(false);
                    Grades.setEditable(false);

                    GradesList.add(new JLabel("Exam Score:"));
                    GradesList.add(ExamScore);
                    GradesList.add(new JLabel("Tests Score:"));
                    GradesList.add(TestScore);
                    GradesList.add(new JLabel("Performance tasks Score:"));
                    GradesList.add(PTScore);
                    GradesList.add(new JLabel("Assignments Score:"));
                    GradesList.add(AssignScore);
                    GradesList.add(new JLabel("Total grades: "));
                    GradesList.add(Grades);

                    JOptionPane.showMessageDialog(null, GradesList, student+"\'s GRADE", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }else {
                    JOptionPane.showMessageDialog(null, "Student's name has no records.");
                    option = JOptionPane.showConfirmDialog(null, inputStudent);
                }
                }
            }
        });
    }
}
