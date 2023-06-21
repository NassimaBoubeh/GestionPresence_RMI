package presentation;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import metier.Classe;
import service_classe.ClasseRemote;

public class ClasseFenetre {

    String url = "rmi://localhost:1099/Classe";
    ArrayList<Classe> cls = new ArrayList<Classe>();

    public void listesClasseFenetre() {
        JFrame frame = new JFrame("CLASSES_PAGE");
        frame.setLocation(500, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(600, 500);

        try {
            JLabel labelHead = new JLabel("La liste des classes :");
            labelHead.setFont(new Font("Arial", Font.TRUETYPE_FONT, 20));
            frame.add(labelHead, BorderLayout.NORTH);

            ClasseRemote classeRemote = (ClasseRemote) Naming.lookup(url);
            List<Classe> classes = classeRemote.listCls();

            DefaultListModel<String> lstClasses = new DefaultListModel<>();
            for (Classe classe : classes) {
                lstClasses.addElement(classe.getClasseName() + " :: " + classe.getSubjectName());
                cls.add(classe);
            }

            JList<String> listClasses = new JList<>(lstClasses);
            JScrollPane scrollPane = new JScrollPane(listClasses);
            frame.add(scrollPane, BorderLayout.CENTER);

            listClasses.addListSelectionListener(new ListSelectionListener() {

            	 @Override
            	    public void valueChanged(ListSelectionEvent e) {
            	        new StudentFenetre().listesStudentFenetre();
            	        frame.dispose();
            	    }
            	 
            });

            JButton addClasse = new JButton("ADD_CLASSE");
            addClasse.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    addClasse();
                    frame.dispose();
                }
            });
            
            JButton editClasse = new JButton("EDIT_OR_DELETE");
            editClasse.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    new ClasseFenetre().listesClasseForEdit();
                    frame.dispose();
                }
            });
            JPanel pp = new JPanel();
            pp.add(addClasse);
            pp.add(editClasse);
            frame.add(pp, BorderLayout.SOUTH);

        } catch (Exception e) {
            e.printStackTrace();
        }

        frame.setVisible(true);
    }
    
    public void listesClasseForEdit() {
        JFrame frame = new JFrame("CLASSES_PAGE");
        frame.setLocation(500, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(600, 500);

        try {
            JLabel labelHead = new JLabel("La liste des classes :");
            labelHead.setFont(new Font("Arial", Font.TRUETYPE_FONT, 20));
            frame.add(labelHead, BorderLayout.NORTH);

            ClasseRemote classeRemote = (ClasseRemote) Naming.lookup(url);
            List<Classe> classes = classeRemote.listCls();

            DefaultListModel<String> lstClasses = new DefaultListModel<>();
            for (Classe classe : classes) {
                lstClasses.addElement(classe.getClasseName() + " :: " + classe.getSubjectName());
                cls.add(classe);
            }

            JList<String> listClasses = new JList<>(lstClasses);
            JScrollPane scrollPane = new JScrollPane(listClasses);
            frame.add(scrollPane, BorderLayout.CENTER);

            listClasses.addListSelectionListener(new ListSelectionListener() {

            	 @Override
            	    public void valueChanged(ListSelectionEvent e) {
            	        if (!e.getValueIsAdjusting()) {
            	            String chemin = listClasses.getSelectedValue();
            	            System.out.println("CHEMIN"+chemin);
            	            if (chemin != null) {
            	                String[] CN = chemin.split(" :: "); 
            	                for (Classe classe : cls) {
            	                    if (CN[0].equalsIgnoreCase(classe.getClasseName())) {
            	                    	System.out.println("je suis là");
            	                        editClasse(classe);
            	                        frame.dispose();
            	                    }
            	                }
            	            }

            	            
            	        }
            	    }
            	 
            });

            JButton addClasse = new JButton("ADD_CLASSE");
            addClasse.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    addClasse();
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
            
            JPanel pp = new JPanel();
            pp.add(addClasse);
            pp.add(retour);
            frame.add(pp, BorderLayout.SOUTH);

            
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame.setVisible(true);
    }

    public void addClasse() {
        JFrame frame = new JFrame("ADD_CLASSE");
        frame.setLocation(500, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());

        JLabel labelHead = new JLabel("Créer une classe:");
        labelHead.setFont(new Font("Arial", Font.TRUETYPE_FONT, 25));
        frame.add(labelHead, BorderLayout.NORTH);

        JPanel p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS)); // Utilisez un BoxLayout vertical pour p1

        JLabel classeName = new JLabel("class Name:");
        JTextField classeNameField = new JTextField(20);
        p1.add(classeName);
        p1.add(classeNameField);

        JLabel subjectName = new JLabel("subject Name:");
        JTextField subjectNameField = new JTextField(20);
        p1.add(subjectName);
        p1.add(subjectNameField);

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
                new ClasseFenetre().listesClasseFenetre();
                frame.dispose();
            }

        });

        valider.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ClasseRemote classeRemote = (ClasseRemote) Naming.lookup(url);
                    String clsName = classeNameField.getText();
                    String subName = subjectNameField.getText();
                    Classe classe = new Classe(clsName, subName);
                    classeRemote.createCls(classe);
                    new ClasseFenetre().listesClasseFenetre();
                    frame.dispose();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }

        });

        frame.setVisible(true);
    }

    public void editClasse(Classe classe) {
        JFrame frame = new JFrame("EDIT_OR_DELETE_CLASSE");
        frame.setLocation(500, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());

        JLabel labelHead = new JLabel("modifier une classe:");
        labelHead.setFont(new Font("Arial", Font.TRUETYPE_FONT, 25));
        frame.add(labelHead, BorderLayout.NORTH);

        JPanel p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS)); // Utilisez un BoxLayout vertical pour p1

        JLabel classeName = new JLabel("class Name:");
        JTextField classeNameField = new JTextField(20);
        classeNameField.setText(classe.getClasseName());
        p1.add(classeName);
        p1.add(classeNameField);

        JLabel subjectName = new JLabel("subject Name:");
        JTextField subjectNameField = new JTextField(20);
        subjectNameField.setText(classe.getSubjectName());
        p1.add(subjectName);
        p1.add(subjectNameField);

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
                new ClasseFenetre().listesClasseFenetre();
                frame.dispose();
            }

        });

        edit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ClasseRemote classeRemote = (ClasseRemote) Naming.lookup(url);
                    String clsName = classeNameField.getText();
                    String subName = subjectNameField.getText();
                    Classe classe1 = new Classe(classe.getIdentifiant(), clsName, subName);
                    classeRemote.editCls(classe1);
                    new ClasseFenetre().listesClasseFenetre();
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
                    ClasseRemote classeRemote = (ClasseRemote) Naming.lookup(url);
                    classeRemote.deleteCls(classe);
                    new ClasseFenetre().listesClasseFenetre();
                    frame.dispose();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }

        });

        frame.setVisible(true);
    }

}
