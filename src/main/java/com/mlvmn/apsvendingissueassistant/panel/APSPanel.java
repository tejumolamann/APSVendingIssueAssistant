/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mlvmn.apsvendingissueassistant.panel;

/**
 *
 * @author tejum
 */
public class APSPanel extends javax.swing.JFrame {

    /**
     * Creates new form APSPanel
     */
    public APSPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogMeterNumber = new javax.swing.JDialog();
        jPanelValidateMeterNum = new javax.swing.JPanel();
        jLabelValidateMeterNum = new javax.swing.JLabel();
        jTextFieldValidateMeterNum = new javax.swing.JTextField();
        jButtonClearMeterNum = new javax.swing.JButton();
        jButtonCancelValidateMeterNum = new javax.swing.JButton();
        jButtonValidateMeterNum = new javax.swing.JButton();
        jDialogCredentials = new javax.swing.JDialog();
        jDialogCredentials.setLocationRelativeTo(null);
        jPanelCredentials = new javax.swing.JPanel();
        jLabelUsername = new javax.swing.JLabel();
        jTextFieldUsername = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabelPassword = new javax.swing.JLabel();
        jTextFieldPassword = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabelAuthCode = new javax.swing.JLabel();
        jTextFieldAuthCode = new javax.swing.JTextField();
        jPanelCredentials2 = new javax.swing.JPanel();
        jButtonClearCredentials = new javax.swing.JButton();
        jButtonCancelSave = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jDialogDemoLive = new javax.swing.JDialog();
        jPanelDemoLive = new javax.swing.JPanel();
        jRadioButtonLive = new javax.swing.JRadioButton();
        jRadioButtonDemo = new javax.swing.JRadioButton();
        jButtonCancelDemoLive = new javax.swing.JButton();
        jButtonSaveDemoLive1 = new javax.swing.JButton();
        buttonGroupDemoLive = new javax.swing.ButtonGroup();
        jPanelControls = new javax.swing.JPanel();
        jButtonValidate = new javax.swing.JButton();
        jButtonPreviewVend = new javax.swing.JButton();
        jButtonVend = new javax.swing.JButton();
        jButtonGetBalance = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanelDisplay = new javax.swing.JPanel();
        jScrollPaneDisplayValues = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        exitMenuItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        credentialsMenuItem = new javax.swing.JMenuItem();
        jMenuItemDemoLive = new javax.swing.JMenuItem();

        jDialogMeterNumber.setTitle("Validate Meter Number");
        jDialogMeterNumber.setAlwaysOnTop(true);
        jDialogMeterNumber.setModal(true);
        jDialogMeterNumber.setResizable(false);
        jDialogMeterNumber.setSize(new java.awt.Dimension(400, 170));
        jDialogMeterNumber.setLocationRelativeTo(null);

        jPanelValidateMeterNum.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelValidateMeterNum.setText("Enter a meter number");

        jTextFieldValidateMeterNum.setToolTipText("Type in a meter number to validate it");
        jTextFieldValidateMeterNum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldValidateMeterNumKeyTyped(evt);
            }
        });

        jButtonClearMeterNum.setText("Clear");
        jButtonClearMeterNum.setToolTipText("Clear field");
        jButtonClearMeterNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearMeterNumActionPerformed(evt);
            }
        });

        jButtonCancelValidateMeterNum.setText("Cancel");
        jButtonCancelValidateMeterNum.setToolTipText("Cancel");
        jButtonCancelValidateMeterNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelValidateMeterNumActionPerformed(evt);
            }
        });

        jButtonValidateMeterNum.setText("Validate");
        jButtonValidateMeterNum.setToolTipText("Click to validate meter number");
        jButtonValidateMeterNum.setEnabled(false);

        javax.swing.GroupLayout jPanelValidateMeterNumLayout = new javax.swing.GroupLayout(jPanelValidateMeterNum);
        jPanelValidateMeterNum.setLayout(jPanelValidateMeterNumLayout);
        jPanelValidateMeterNumLayout.setHorizontalGroup(
            jPanelValidateMeterNumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelValidateMeterNumLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelValidateMeterNumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldValidateMeterNum)
                    .addGroup(jPanelValidateMeterNumLayout.createSequentialGroup()
                        .addComponent(jLabelValidateMeterNum)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelValidateMeterNumLayout.createSequentialGroup()
                        .addComponent(jButtonClearMeterNum)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 141, Short.MAX_VALUE)
                        .addComponent(jButtonValidateMeterNum)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCancelValidateMeterNum)))
                .addContainerGap())
        );
        jPanelValidateMeterNumLayout.setVerticalGroup(
            jPanelValidateMeterNumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelValidateMeterNumLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelValidateMeterNum)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldValidateMeterNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelValidateMeterNumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonClearMeterNum)
                    .addComponent(jButtonCancelValidateMeterNum)
                    .addComponent(jButtonValidateMeterNum))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogMeterNumberLayout = new javax.swing.GroupLayout(jDialogMeterNumber.getContentPane());
        jDialogMeterNumber.getContentPane().setLayout(jDialogMeterNumberLayout);
        jDialogMeterNumberLayout.setHorizontalGroup(
            jDialogMeterNumberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogMeterNumberLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelValidateMeterNum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDialogMeterNumberLayout.setVerticalGroup(
            jDialogMeterNumberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogMeterNumberLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelValidateMeterNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jDialogCredentials.setTitle("Credentials");
        jDialogCredentials.setAlwaysOnTop(true);
        jDialogCredentials.setLocation(new java.awt.Point(0, 0));
        jDialogCredentials.setLocationByPlatform(true);
        jDialogCredentials.setMinimumSize(new java.awt.Dimension(400, 350));
        jDialogCredentials.setModal(true);
        jDialogCredentials.setResizable(false);

        jPanelCredentials.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelUsername.setText("Username");

        jTextFieldUsername.setToolTipText("Enter username");

        jLabelPassword.setText("Password");

        jTextFieldPassword.setToolTipText("Enter password");

        jLabelAuthCode.setText("Authorisation Code");

        jTextFieldAuthCode.setToolTipText("Enter authorisation code");

        javax.swing.GroupLayout jPanelCredentialsLayout = new javax.swing.GroupLayout(jPanelCredentials);
        jPanelCredentials.setLayout(jPanelCredentialsLayout);
        jPanelCredentialsLayout.setHorizontalGroup(
            jPanelCredentialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCredentialsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCredentialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldUsername)
                    .addComponent(jSeparator1)
                    .addComponent(jTextFieldPassword)
                    .addComponent(jSeparator2)
                    .addComponent(jTextFieldAuthCode)
                    .addGroup(jPanelCredentialsLayout.createSequentialGroup()
                        .addGroup(jPanelCredentialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelUsername)
                            .addComponent(jLabelPassword)
                            .addComponent(jLabelAuthCode))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelCredentialsLayout.setVerticalGroup(
            jPanelCredentialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCredentialsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelUsername)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelAuthCode)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldAuthCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanelCredentials2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonClearCredentials.setText("Clear form");

        jButtonCancelSave.setText("Cancel");

        jButtonSave.setText("Save");

        javax.swing.GroupLayout jPanelCredentials2Layout = new javax.swing.GroupLayout(jPanelCredentials2);
        jPanelCredentials2.setLayout(jPanelCredentials2Layout);
        jPanelCredentials2Layout.setHorizontalGroup(
            jPanelCredentials2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCredentials2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonClearCredentials, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(133, 133, 133)
                .addComponent(jButtonCancelSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButtonSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelCredentials2Layout.setVerticalGroup(
            jPanelCredentials2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCredentials2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCredentials2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonClearCredentials)
                    .addComponent(jButtonCancelSave)
                    .addComponent(jButtonSave))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogCredentialsLayout = new javax.swing.GroupLayout(jDialogCredentials.getContentPane());
        jDialogCredentials.getContentPane().setLayout(jDialogCredentialsLayout);
        jDialogCredentialsLayout.setHorizontalGroup(
            jDialogCredentialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogCredentialsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialogCredentialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelCredentials, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelCredentials2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jDialogCredentialsLayout.setVerticalGroup(
            jDialogCredentialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogCredentialsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelCredentials, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelCredentials2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jDialogDemoLive.setTitle("Demo/Live Vending");
        jDialogDemoLive.setAlwaysOnTop(true);
        jDialogDemoLive.setModal(true);
        jDialogDemoLive.setResizable(false);
        jDialogDemoLive.setSize(new java.awt.Dimension(400, 175));
        jDialogDemoLive.setLocationRelativeTo(null);

        jPanelDemoLive.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonGroupDemoLive.add(jRadioButtonLive);
        jRadioButtonLive.setText("Live Vend");
        jRadioButtonLive.setToolTipText("Select for live vending");

        buttonGroupDemoLive.add(jRadioButtonDemo);
        jRadioButtonDemo.setText("Demo Vend");
        jRadioButtonDemo.setToolTipText("Select for demo or none live vending");

        jButtonCancelDemoLive.setText("Cancel");
        jButtonCancelDemoLive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelDemoLiveActionPerformed(evt);
            }
        });

        jButtonSaveDemoLive1.setText("Save");

        javax.swing.GroupLayout jPanelDemoLiveLayout = new javax.swing.GroupLayout(jPanelDemoLive);
        jPanelDemoLive.setLayout(jPanelDemoLiveLayout);
        jPanelDemoLiveLayout.setHorizontalGroup(
            jPanelDemoLiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDemoLiveLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDemoLiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDemoLiveLayout.createSequentialGroup()
                        .addGroup(jPanelDemoLiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButtonLive)
                            .addComponent(jRadioButtonDemo))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDemoLiveLayout.createSequentialGroup()
                        .addGap(0, 230, Short.MAX_VALUE)
                        .addComponent(jButtonSaveDemoLive1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonCancelDemoLive)))
                .addContainerGap())
        );
        jPanelDemoLiveLayout.setVerticalGroup(
            jPanelDemoLiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDemoLiveLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButtonLive)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButtonDemo)
                .addGap(18, 18, 18)
                .addGroup(jPanelDemoLiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelDemoLive)
                    .addComponent(jButtonSaveDemoLive1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogDemoLiveLayout = new javax.swing.GroupLayout(jDialogDemoLive.getContentPane());
        jDialogDemoLive.getContentPane().setLayout(jDialogDemoLiveLayout);
        jDialogDemoLiveLayout.setHorizontalGroup(
            jDialogDemoLiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogDemoLiveLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelDemoLive, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDialogDemoLiveLayout.setVerticalGroup(
            jDialogDemoLiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogDemoLiveLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelDemoLive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Access Power Systems Vending Issue Assistant");
        setAlwaysOnTop(true);

        jPanelControls.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonValidate.setText("Validate");
        jButtonValidate.setToolTipText("Validate Meter Number");
        jButtonValidate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonValidateActionPerformed(evt);
            }
        });

        jButtonPreviewVend.setText("<html><p align='center'>Preview Vend/</p>Generate Transaction Reference");
        jButtonPreviewVend.setToolTipText("Preview a vend before actual vend");

        jButtonVend.setText("Vend Meter Number");
        jButtonVend.setToolTipText("Vend for a meter number");

        jButtonGetBalance.setText("Get Balance");
        jButtonGetBalance.setToolTipText("Get balance in wallet");

        jButton1.setText("Vend Transaction Reference");
        jButton1.setToolTipText("Vend a previously generated transaction reference");

        jButton2.setText("Copy Token");

        javax.swing.GroupLayout jPanelControlsLayout = new javax.swing.GroupLayout(jPanelControls);
        jPanelControls.setLayout(jPanelControlsLayout);
        jPanelControlsLayout.setHorizontalGroup(
            jPanelControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelControlsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonValidate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonPreviewVend, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonVend, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonGetBalance, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelControlsLayout.setVerticalGroup(
            jPanelControlsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelControlsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonValidate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonPreviewVend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonVend)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonGetBalance)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap(168, Short.MAX_VALUE))
        );

        jPanelDisplay.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPaneDisplayValues.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanelDisplayLayout = new javax.swing.GroupLayout(jPanelDisplay);
        jPanelDisplay.setLayout(jPanelDisplayLayout);
        jPanelDisplayLayout.setHorizontalGroup(
            jPanelDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDisplayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneDisplayValues, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelDisplayLayout.setVerticalGroup(
            jPanelDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDisplayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneDisplayValues)
                .addContainerGap())
        );

        jMenu1.setText("File");

        exitMenuItem.setText("Exit");
        jMenu1.add(exitMenuItem);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Settings");

        credentialsMenuItem.setText("Credentials...");
        credentialsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                credentialsMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(credentialsMenuItem);

        jMenuItemDemoLive.setText("Demo or Live...");
        jMenuItemDemoLive.setToolTipText("Click to switch vending between Live and Demo");
        jMenuItemDemoLive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDemoLiveActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemDemoLive);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelControls, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelControls, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void credentialsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_credentialsMenuItemActionPerformed
        jDialogCredentials.setVisible(true);
    }//GEN-LAST:event_credentialsMenuItemActionPerformed

    private void jMenuItemDemoLiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDemoLiveActionPerformed
        jDialogDemoLive.setVisible(true);
    }//GEN-LAST:event_jMenuItemDemoLiveActionPerformed

    private void jButtonCancelDemoLiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelDemoLiveActionPerformed
        jDialogDemoLive.setVisible(false);
    }//GEN-LAST:event_jButtonCancelDemoLiveActionPerformed

    private void jButtonValidateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonValidateActionPerformed
        jDialogMeterNumber.setVisible(true);
    }//GEN-LAST:event_jButtonValidateActionPerformed

    private void jTextFieldValidateMeterNumKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldValidateMeterNumKeyTyped
        if(evt.getComponent().equals(jTextFieldValidateMeterNum)){
            if(!jTextFieldValidateMeterNum.getText().isEmpty()){
                jButtonValidateMeterNum.setEnabled(true);
            } else{
                jButtonValidateMeterNum.setEnabled(false);
            }
        }
    }//GEN-LAST:event_jTextFieldValidateMeterNumKeyTyped

    private void jButtonClearMeterNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearMeterNumActionPerformed
        jTextFieldValidateMeterNum.setText("");
        jButtonValidateMeterNum.setEnabled(false);
    }//GEN-LAST:event_jButtonClearMeterNumActionPerformed

    private void jButtonCancelValidateMeterNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelValidateMeterNumActionPerformed
        jDialogMeterNumber.setVisible(false);
    }//GEN-LAST:event_jButtonCancelValidateMeterNumActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(APSPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(APSPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(APSPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(APSPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new APSPanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupDemoLive;
    private javax.swing.JMenuItem credentialsMenuItem;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonCancelDemoLive;
    private javax.swing.JButton jButtonCancelSave;
    private javax.swing.JButton jButtonCancelValidateMeterNum;
    private javax.swing.JButton jButtonClearCredentials;
    private javax.swing.JButton jButtonClearMeterNum;
    private javax.swing.JButton jButtonGetBalance;
    private javax.swing.JButton jButtonPreviewVend;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSaveDemoLive1;
    private javax.swing.JButton jButtonValidate;
    private javax.swing.JButton jButtonValidateMeterNum;
    private javax.swing.JButton jButtonVend;
    private javax.swing.JDialog jDialogCredentials;
    private javax.swing.JDialog jDialogDemoLive;
    private javax.swing.JDialog jDialogMeterNumber;
    private javax.swing.JLabel jLabelAuthCode;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JLabel jLabelUsername;
    private javax.swing.JLabel jLabelValidateMeterNum;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemDemoLive;
    private javax.swing.JPanel jPanelControls;
    private javax.swing.JPanel jPanelCredentials;
    private javax.swing.JPanel jPanelCredentials2;
    private javax.swing.JPanel jPanelDemoLive;
    private javax.swing.JPanel jPanelDisplay;
    private javax.swing.JPanel jPanelValidateMeterNum;
    private javax.swing.JRadioButton jRadioButtonDemo;
    private javax.swing.JRadioButton jRadioButtonLive;
    private javax.swing.JScrollPane jScrollPaneDisplayValues;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextFieldAuthCode;
    private javax.swing.JTextField jTextFieldPassword;
    private javax.swing.JTextField jTextFieldUsername;
    private javax.swing.JTextField jTextFieldValidateMeterNum;
    // End of variables declaration//GEN-END:variables
}
