/**
 * Copyright (c) The openTCS Authors.
 *
 * This program is free software and subject to the MIT license. (For details,
 * see the licensing information (LICENSE.txt) you should have received with
 * this copy of the software.)
 */
package org.opentcs.operationsdesk.notifications;

import com.google.inject.assistedinject.Assisted;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import static java.util.Objects.requireNonNull;
import javax.inject.Inject;
import org.opentcs.data.notification.UserNotification;
import org.opentcs.guing.common.components.dialogs.DialogContent;
import org.opentcs.operationsdesk.util.I18nPlantOverviewOperating;
import org.opentcs.thirdparty.guing.common.jhotdraw.util.ResourceBundleUtil;

/**
 * A view of a user notification.
 *
 * @author Sebastian Bonna (Fraunhofer IML)
 */
public class UserNotificationView
    extends DialogContent {

  /**
   * A formatter for timestamps.
   */
  private static final DateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  /**
   * The user notification to be shown.
   */
  private final UserNotification fUserNotification;

  /**
   * Creates new instance.
   *
   * @param notification The user notification.
   */
  @Inject
  public UserNotificationView(@Assisted UserNotification notification) {
    this.fUserNotification = requireNonNull(notification, "notification");

    initComponents();
    setDialogTitle(ResourceBundleUtil.getBundle(I18nPlantOverviewOperating.UNDETAIL_PATH)
        .getString("userNotificationView.title"));
  }

  @Override
  public void update() {
  }

  @Override
  public final void initFields() {
    createdTextField.setText(TIMESTAMP_FORMAT.format(Date.from(fUserNotification.getTimestamp())));
    levelTextField.setText(fUserNotification.getLevel().name());
    sourceTextField.setText(
        fUserNotification.getSource() == null ? "-" : fUserNotification.getSource()
    );
    textTextArea.setText(fUserNotification.getText());
  }

  // CHECKSTYLE:OFF
  /**
   * This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {
    java.awt.GridBagConstraints gridBagConstraints;

    generalPanel = new javax.swing.JPanel();
    createdLabel = new javax.swing.JLabel();
    createdTextField = new javax.swing.JTextField();
    sourceLabel = new javax.swing.JLabel();
    sourceTextField = new javax.swing.JTextField();
    levelLabel = new javax.swing.JLabel();
    levelTextField = new javax.swing.JTextField();
    textPanel = new javax.swing.JPanel();
    jScrollPane1 = new javax.swing.JScrollPane();
    textTextArea = new javax.swing.JTextArea();

    setLayout(new java.awt.GridBagLayout());

    generalPanel.setLayout(new java.awt.GridBagLayout());

    createdLabel.setFont(createdLabel.getFont());
    java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("i18n/org/opentcs/plantoverview/operating/dialogs/userNotificationDetail"); // NOI18N
    createdLabel.setText(bundle.getString("userNotificationView.label_created.text")); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
    generalPanel.add(createdLabel, gridBagConstraints);

    createdTextField.setEditable(false);
    createdTextField.setColumns(10);
    createdTextField.setFont(createdTextField.getFont());
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 0.5;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
    generalPanel.add(createdTextField, gridBagConstraints);

    sourceLabel.setFont(sourceLabel.getFont());
    sourceLabel.setText(bundle.getString("userNotificationView.label_source.text")); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 4);
    generalPanel.add(sourceLabel, gridBagConstraints);

    sourceTextField.setEditable(false);
    sourceTextField.setColumns(10);
    sourceTextField.setFont(sourceTextField.getFont());
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 0.5;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
    generalPanel.add(sourceTextField, gridBagConstraints);

    levelLabel.setFont(levelLabel.getFont());
    levelLabel.setText(bundle.getString("userNotificationView.label_level.text")); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
    generalPanel.add(levelLabel, gridBagConstraints);

    levelTextField.setEditable(false);
    levelTextField.setColumns(10);
    levelTextField.setFont(levelTextField.getFont());
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 0.5;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
    generalPanel.add(levelTextField, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 0.1;
    add(generalPanel, gridBagConstraints);

    textPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("userNotificationView.label_text.text"))); // NOI18N
    textPanel.setLayout(new java.awt.GridBagLayout());

    textTextArea.setEditable(false);
    textTextArea.setColumns(40);
    textTextArea.setLineWrap(true);
    textTextArea.setRows(5);
    textTextArea.setWrapStyleWord(true);
    jScrollPane1.setViewportView(textTextArea);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    textPanel.add(jScrollPane1, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weighty = 1.0;
    add(textPanel, gridBagConstraints);
  }// </editor-fold>//GEN-END:initComponents
  // CHECKSTYLE:ON

  // CHECKSTYLE:OFF
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel createdLabel;
  private javax.swing.JTextField createdTextField;
  private javax.swing.JPanel generalPanel;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JLabel levelLabel;
  private javax.swing.JTextField levelTextField;
  private javax.swing.JLabel sourceLabel;
  private javax.swing.JTextField sourceTextField;
  private javax.swing.JPanel textPanel;
  private javax.swing.JTextArea textTextArea;
  // End of variables declaration//GEN-END:variables
  // CHECKSTYLE:ON

}
