package game;

import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;


/** Login class allows the user to create an account and stores it as data object
 * 
 * @author Hailey Pong (hpong5)
 */
public class Login extends javax.swing.JFrame {

    private Player player;
    private String inputID;
    public static Data data = Data.getInstance();
    
    /** displays the GUI */
    public Login() {
        initComponents();
        wrongMess.setVisible(false);
        data.retrieveData();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel = new javax.swing.JPanel();
        id = new javax.swing.JTextField();
        login = new javax.swing.JButton();
        inst = new javax.swing.JButton();
        text1 = new javax.swing.JLabel();
        loginIcon = new javax.swing.JLabel();
        create = new javax.swing.JButton();
        newAccMess = new javax.swing.JLabel();
        wrongMess = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(0, 51, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusCycleRoot(false);

        jPanel.setBackground(new java.awt.Color(255, 255, 255));
        jPanel.setForeground(new java.awt.Color(255, 255, 255));

        id.setColumns(10);
        id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                idKeyPressed(evt);
            }
        });

        login.setBackground(new java.awt.Color(32, 32, 255));
        login.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        login.setForeground(new java.awt.Color(255, 255, 255));
        login.setText("Login");
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });

        inst.setBackground(new java.awt.Color(12, 4, 255));
        inst.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        inst.setForeground(new java.awt.Color(255, 255, 255));
        inst.setText("Instructor Dashboard");
        inst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                instActionPerformed(evt);
            }
        });

        text1.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        text1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        text1.setText("Welcome");
        text1.setToolTipText("");

        loginIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/avatar.png"))); // NOI18N
        loginIcon.setText("jLabel1");
        loginIcon.setAutoscrolls(true);
        loginIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginIconMouseClicked(evt);
            }
        });

        create.setBackground(new java.awt.Color(102, 102, 102));
        create.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        create.setForeground(new java.awt.Color(255, 255, 255));
        create.setText("Sign Up");
        create.setBorderPainted(false);
        create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createActionPerformed(evt);
            }
        });

        newAccMess.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        newAccMess.setForeground(new java.awt.Color(0, 6, 213));
        newAccMess.setText("Don't have an account?");

        wrongMess.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        wrongMess.setForeground(new java.awt.Color(255, 0, 0));
        wrongMess.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        wrongMess.setText("Wrong ID!");

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newAccMess, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(text1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(id)
                        .addComponent(login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(inst, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(create, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(wrongMess, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(loginIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(149, Short.MAX_VALUE))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(loginIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(text1)
                .addGap(35, 35, 35)
                .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wrongMess)
                .addGap(42, 42, 42)
                .addComponent(login)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inst)
                .addGap(18, 18, 18)
                .addComponent(newAccMess)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(create)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /** Creates input user id
     * 
     * @param evt click on the button
     *
    */
    private void createActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createActionPerformed
        inputID = id.getText();
        player = new Player(inputID);
        wrongMess.setVisible(false);
        
        javax.swing.JFrame popup = new javax.swing.JFrame(); 
        popup.setSize(200,200);
        setLocationRelativeTo(null);
        Boolean b = false;
        
        if (inputID == null || inputID.length() < 5 ) {
           id.setText("Needs to be longer");
        }
        /** ID Already exists */
        else if (data.checkDuplicateId(inputID)){
            JOptionPane.showMessageDialog(this, "ID already exists. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);

        }
        /** ID Created */
        else{
            JOptionPane.showMessageDialog(this, "Account created. Sign in!", "Account Creation", javax.swing.JOptionPane.PLAIN_MESSAGE);
            data.addPlayer(player);
            data.updateData();
        }
       popup.setVisible(b);
    }//GEN-LAST:event_createActionPerformed

    /** Instructor login appears
     * 
     * @param evt 
     */
    private void instActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_instActionPerformed
        InstructorPin inst = new InstructorPin();
        inst.setVisible(true);
        dispose();
    }//GEN-LAST:event_instActionPerformed

    /**
     * Calls login method
     * @param evt 
     */
    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        login();
    }//GEN-LAST:event_loginActionPerformed

    /**
     * login via enter key
     * @param evt 
     */
    private void idKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            login();
        }
    }//GEN-LAST:event_idKeyPressed
/** 
 * When the icon is clicked go to debugging mode
 * @param evt 
 */
    private void loginIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginIconMouseClicked
            Debugging debugMode = new Debugging(player);
            debugMode.setVisible(true);
            dispose();        
    }//GEN-LAST:event_loginIconMouseClicked

    /**
     * if the user exists then login, allow the user to create an account
     */
    private void login() {
        inputID = id.getText();
        player = new Player(inputID);
        boolean exist = data.checkDuplicateId(inputID);
        
        /** Opens the debugging if the user is an admin */
        if (player.isAdmin()){
            Debugging debugMode = new Debugging(player);
            debugMode.setVisible(true);
            dispose();
        }
        /** Existing user */
        if (exist) {
            player = data.getPlayer(inputID);
            Menu menu = new Menu(player);
            menu.setVisible(true);
            dispose();
        }
        /** New user */
        else {
            wrongMess.setVisible(true);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
    
    /**
     * Variables for GUI
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton create;
    private javax.swing.JTextField id;
    private javax.swing.JButton inst;
    private javax.swing.JPanel jPanel;
    private javax.swing.JButton login;
    private javax.swing.JLabel loginIcon;
    private javax.swing.JLabel newAccMess;
    private javax.swing.JLabel text1;
    private javax.swing.JLabel wrongMess;
    // End of variables declaration//GEN-END:variables
}
