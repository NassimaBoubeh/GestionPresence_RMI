package presentation;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import metier.Classe;
import metier.Student;
import service_classe.ClasseRemote;
import service_classe.StudentRemote;

public class StudentFenetre {
	 String url = "rmi://localhost:1099/Student";
	    ArrayList<Student> cls = new ArrayList<Student>();

	    public void listesStudentFenetre() {
	        JFrame frame = new JFrame("STUDENTS_PAGE");
	        frame.setLocation(500, 200);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setLayout(new BorderLayout());
	        frame.setSize(600, 500);

	        try {
	            JLabel labelHead = new JLabel("La liste des étudiants :");
	            labelHead.setFont(new Font("Arial", Font.TRUETYPE_FONT, 20));
	            frame.add(labelHead, BorderLayout.NORTH);

	            StudentRemote studentRemote = (StudentRemote) Naming.lookup(url);
	            List<Student> students = studentRemote.listStudent();

	            DefaultListModel<String> lstStudents = new DefaultListModel<>();
	            for (Student student : students) {
	                lstStudents.addElement(student.getName()+"\t ::\""+student.getStatus()+"\"");
	                cls.add(student);
	            }

	            JList<String> listStudents = new JList<>(lstStudents);
	            JScrollPane scrollPane = new JScrollPane(listStudents);
	            frame.add(scrollPane, BorderLayout.CENTER);

	            listStudents.addListSelectionListener(new ListSelectionListener() {

	            	 @Override
	            	    public void valueChanged(ListSelectionEvent e) {
	            		 String chemin = listStudents.getSelectedValue();
	            		 if (chemin != null) {
         	                String[] CN = chemin.split("\t ::\"");
         	                for (Student student : cls) {
         	                	if(CN[0].equals(student.getName())) {
         	                		 if (student.getStatus().indexOf("")==-1) {
         	                			 System.out.println("c1"+CN[0]);
         	                			 break;
         	                		 }else if (student.getStatus().indexOf("A")==-1) {
         	                			 System.out.println("c2"+CN[0]);
         	                			 break;
         	                		 }else if(student.getStatus().indexOf("P")==-1) {
         	                			 System.out.println("c3"+CN[0]);
         	                			 break;
         	                		 }
         	                	}
         	                   
         	                }
         	            }
	            	            
	            	        
	            	    }
	            	 
	            });

	            JButton addStudent = new JButton("ADD_Student");
	            addStudent.addActionListener(new ActionListener() {

	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    addStudent();
	                    frame.dispose();
	                }
	            });
	            
	            JButton retour = new JButton("CANCEL");
	            retour.addActionListener(new ActionListener() {

	                @Override
	                public void actionPerformed(ActionEvent e) {
	                	 new ClasseFenetre().listesClasseFenetre();
	                    frame.dispose();
	                }
	            });
	            
	            JButton editStudent = new JButton("EDIT_OR_DELETE");
	            editStudent.addActionListener(new ActionListener() {

	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    new StudentFenetre().listesStudentForEdit();
	                    frame.dispose();
	                }
	            });
	            JPanel pp = new JPanel();
	            pp.add(addStudent);
	            pp.add(editStudent);
	            pp.add(retour);
	            frame.add(pp, BorderLayout.SOUTH);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        frame.setVisible(true);
	    }
	    
	    public void listesStudentForEdit() {
	    	 JFrame frame = new JFrame("STUDENTS_PAGE");
		        frame.setLocation(500, 200);
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        frame.setLayout(new BorderLayout());
		        frame.setSize(600, 500);

		        try {
		            JLabel labelHead = new JLabel("La liste des étudiants :");
		            labelHead.setFont(new Font("Arial", Font.TRUETYPE_FONT, 20));
		            frame.add(labelHead, BorderLayout.NORTH);

		            StudentRemote studentRemote = (StudentRemote) Naming.lookup(url);
		            List<Student> students = studentRemote.listStudent();

		            DefaultListModel<String> lstStudents = new DefaultListModel<>();
		            for (Student student : students) {
		                lstStudents.addElement(student.getName()+" :: \t"+student.getStatus());
		                cls.add(student);
		            }

		            JList<String> listStudents = new JList<>(lstStudents);
		            JScrollPane scrollPane = new JScrollPane(listStudents);
		            frame.add(scrollPane, BorderLayout.CENTER);

		            listStudents.addListSelectionListener(new ListSelectionListener() {

		            	 @Override
		            	    public void valueChanged(ListSelectionEvent e) {
		            	        if (!e.getValueIsAdjusting()) {
		            	            String chemin = listStudents.getSelectedValue();
		            	            System.out.println("CHEMIN "+chemin);
		            	            if (chemin != null) {
		            	                String[] CN = chemin.split(" :: "); 
		            	                for (Student student : cls) {
		            	                    if (CN[0].equals(student.getName())) {
		            	                        editStudent(student);
		            	                        frame.dispose();
		            	                    }
		            	                }
		            	            }

		            	            
		            	        }
		            	    }
		            	 
		            });

		            JButton addStudent = new JButton("ADD_Student");
		            addStudent.addActionListener(new ActionListener() {

		                @Override
		                public void actionPerformed(ActionEvent e) {
		                    addStudent();
		                    frame.dispose();
		                }
		            });
		            
		            JButton retour = new JButton("CANCEL");
		            retour.addActionListener(new ActionListener() {

			            @Override
			            public void actionPerformed(ActionEvent e) {
			                new StudentFenetre().listesStudentFenetre();
			                frame.dispose();
			            }

			        });
		            
		            JPanel pp = new JPanel();
		            pp.add(addStudent);
		            pp.add(retour);
		            frame.add(pp, BorderLayout.SOUTH);
		            

		        } catch (Exception e) {
		            e.printStackTrace();
		        }

		        frame.setVisible(true);
	    }

	    public void addStudent() {
	        JFrame frame = new JFrame("ADD_STUDENT");
	        frame.setLocation(500, 200);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(600, 500);
	        frame.setLayout(new BorderLayout());

	        JLabel labelHead = new JLabel("Créer une classe:");
	        labelHead.setFont(new Font("Arial", Font.TRUETYPE_FONT, 25));
	        frame.add(labelHead, BorderLayout.NORTH);

	        JPanel p1 = new JPanel();
	        p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS)); // Utilisez un BoxLayout vertical pour p1

	        JLabel name = new JLabel("Student Name:");
	        JTextField nameField = new JTextField(20);
	        p1.add(name);
	        p1.add(nameField);

	        JLabel status = new JLabel("Status:");
	        JTextField statusField = new JTextField(20);
	        p1.add(status);
	        p1.add(statusField);

	        frame.add(p1, BorderLayout.CENTER);

	        JPanel p = new JPanel();
	        JButton retour = new JButton(" CANCEL ");
	        JButton valider = new JButton(" ADD ");
	        p.add(valider);
	        p.add(retour);
	        frame.add(p, BorderLayout.SOUTH);

	        retour.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {
	                new StudentFenetre().listesStudentFenetre();
	                frame.dispose();
	            }

	        });

	        valider.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {
	                try {
	                    StudentRemote studentRemote = (StudentRemote) Naming.lookup(url);
	                    String stdName = nameField.getText();
	                    String stt = statusField.getText();
	                    Student student = new Student(stdName, stt);
	                    studentRemote.createStudent(student);
	                    new StudentFenetre().listesStudentFenetre();
	                    frame.dispose();
	                } catch (Exception e1) {
	                    e1.printStackTrace();
	                }

	            }

	        });

	        frame.setVisible(true);
	    }

	    public void editStudent(Student student) {
	        JFrame frame = new JFrame("EDIT_OR_DELETE_STUDENT");
	        frame.setLocation(500, 200);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(600, 500);
	        frame.setLayout(new BorderLayout());

	        JLabel labelHead = new JLabel("modifier un étudiant:");
	        labelHead.setFont(new Font("Arial", Font.TRUETYPE_FONT, 25));
	        frame.add(labelHead, BorderLayout.NORTH);

	        JPanel p1 = new JPanel();
	        p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS)); // Utilisez un BoxLayout vertical pour p1

	        JLabel name = new JLabel("Student Name:");
	        JTextField nameField = new JTextField(20);
	        nameField.setText(student.getName());
	        p1.add(name);
	        p1.add(nameField);

	        JLabel status = new JLabel("Status:");
	        JTextField statusField = new JTextField(20);
	        statusField.setText(student.getStatus());
	        p1.add(status);
	        p1.add(statusField);

	        frame.add(p1, BorderLayout.CENTER);
	        

	        JPanel p = new JPanel();
	        JButton retour = new JButton(" CANCEL ");
	        JButton edit = new JButton(" EDIT ");
	        JButton delete = new JButton(" DELETE ");
	        p.add(delete);
	        p.add(edit);
	        p.add(retour);
	        frame.add(p, BorderLayout.SOUTH);

	        retour.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {
	                new StudentFenetre().listesStudentFenetre();
	                frame.dispose();
	            }

	        });

	        edit.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {
	                try {
	                	StudentRemote studentRemote = (StudentRemote) Naming.lookup(url);
	                	String stdName = nameField.getText();
	                    String stt = statusField.getText();
	                    Student student1 = new Student(student.getIdentifiant(),stdName, stt);
	                    studentRemote.editStudent(student1);
	                    new StudentFenetre().listesStudentFenetre();
	                    frame.dispose();
	                } catch (Exception e1) {
	                    e1.printStackTrace();
	                }

	            }

	        });
	        
	        delete.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {
	                try {
	                	 StudentRemote studentRemote = (StudentRemote) Naming.lookup(url);
	                    studentRemote.deleteStudent(student);
	                    new StudentFenetre().listesStudentFenetre();
	                    frame.dispose();
	                } catch (Exception e1) {
	                    e1.printStackTrace();
	                }

	            }

	        });

	        frame.setVisible(true);
	    }

}
