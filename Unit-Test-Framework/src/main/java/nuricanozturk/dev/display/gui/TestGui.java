package nuricanozturk.dev.display.gui;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.List;

public class TestGui extends javax.swing.JFrame
{
    public TestGui(List<TestWrapper> testWrappers)
    {
        initComponents();
        setTitle("Unit-Test Framework Basic GUI Example");
        testWrappers.forEach(classChoiceBox::addItem);
        setResizable(false);
        setVisible(true);
    }

    public void start()
    {
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(TestGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(TestGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(TestGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(TestGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents()
    {

        mainPanel = new javax.swing.JPanel();
        classChoiceBox = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        testResultTextArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(28, 28, 28));

        mainPanel.setBackground(new java.awt.Color(28, 28, 28));
        classChoiceBox.setForeground(new java.awt.Color(178, 178, 178));
        classChoiceBox.setBackground(new java.awt.Color(39, 39, 39));
        classChoiceBox.setModel(new javax.swing.DefaultComboBoxModel<>());
        classChoiceBox.addItemListener(this::choiceBoxListener);
        testResultTextArea.setEditable(false);
        testResultTextArea.setBackground(new java.awt.Color(39, 39, 39));
        testResultTextArea.setColumns(20);


        testResultTextArea.setRows(5);
        testResultTextArea.setForeground(new java.awt.Color(178, 178, 178));
        testResultTextArea.setFont(new Font("methodNameFont", Font.BOLD, 15));
        jScrollPane1.setViewportView(testResultTextArea);


        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(15, 157, 88));
        jLabel1.setText("Unit-Test Basic GUI Example");

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addGap(47, 47, 47)
                                                .addGroup(mainPanelLayout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jScrollPane1,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, 583,
                                                                Short.MAX_VALUE)
                                                        .addComponent(classChoiceBox, 0,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addGap(227, 227, 227)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 214,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(89, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(classChoiceBox, javax.swing.GroupLayout.PREFERRED_SIZE, 42,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 459,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        classChoiceBox.setFocusable(false);
        pack();
    }

    private void choiceBoxListener(ItemEvent e)
    {
        if (e.getStateChange() == ItemEvent.SELECTED)
        {
            testResultTextArea.setText("");

            var selectedClass = ((TestWrapper) e.getItem());
            var methods = selectedClass.getMethods();

            for (var m : methods)
            {
                var name = m.getMethodName();

                testResultTextArea.append("=>" + name + "\n");

                m.getTestResults().forEach(testResultTextArea::append);
            }
        }
    }


    private javax.swing.JComboBox<TestWrapper> classChoiceBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextArea testResultTextArea;
}
