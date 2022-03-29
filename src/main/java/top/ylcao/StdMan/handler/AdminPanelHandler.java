package top.ylcao.StdMan.handler;

import top.ylcao.StdMan.main.Log;
import top.ylcao.StdMan.sql.OperationMysql;
import top.ylcao.StdMan.view.AdminPanelInter;
import top.ylcao.StdMan.view.AllStudentsTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AdminPanelHandler implements ActionListener {

    private final AdminPanelInter adminPanelInter;
    private OperationMysql operationMysql;
    private Log log;
    private AllStudentsTable allStudentsTable;


    public AdminPanelHandler(AdminPanelInter adminPanelInter, OperationMysql operationMysql) {
        this.adminPanelInter = adminPanelInter;
        this.operationMysql = operationMysql;
        this.log = new Log();
        this.allStudentsTable = null;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // 强转JButton源
        JButton sourceButton = (JButton) e.getSource();
        String buttonText = sourceButton.getText();

        if (buttonText.equals("添加")) {
            // 添加学生
            if (adminPanelInter.getAddStudentIDField().getText().equals("") || adminPanelInter.getAddStudentNameField().getText().equals("") || adminPanelInter.getAddStudentSexField().getText().equals("") || adminPanelInter.getAddStudentIDNumberField().getText().equals("") || adminPanelInter.getAddStudentGradeField().getText().equals("") || adminPanelInter.getAddStudentClassField().getText().equals("")) {
                // 是否信息输入完全
                JOptionPane.showMessageDialog(adminPanelInter, "请完整输入学生信息!");
            } else {
                // 添加学生
                if (operationMysql.addStudent(adminPanelInter.getAddStudentIDField().getText(), adminPanelInter.getAddStudentNameField().getText(), adminPanelInter.getAddStudentSexField().getText(), adminPanelInter.getAddStudentIDNumberField().getText(), adminPanelInter.getAddStudentGradeField().getText(), adminPanelInter.getAddStudentClassField().getText())) {
                    // 如果操作成功
                    JOptionPane.showMessageDialog(adminPanelInter, String.format("添加成功!\n" + "学号:%s\n姓名:%s\n性别:%s\n身份证号:%s\n年级:%s\n班级:%s", adminPanelInter.getAddStudentIDField().getText(), adminPanelInter.getAddStudentNameField().getText(), adminPanelInter.getAddStudentSexField().getText(), adminPanelInter.getAddStudentIDNumberField().getText(), adminPanelInter.getAddStudentGradeField().getText(), adminPanelInter.getAddStudentClassField().getText()));
                } else {
                    // 如果操作失败
                    JOptionPane.showMessageDialog(adminPanelInter, "添加失败!可能已经有这个学生了");
                }
            }
        } else if (buttonText.equals("删除")) {
            if (adminPanelInter.getDeleteStudentNameField().getText().equals("")) {
                JOptionPane.showMessageDialog(adminPanelInter, "请输入要删除的学生姓名!");
            } else {
                // 删除学生
                if (operationMysql.deleteStudent(adminPanelInter.getDeleteStudentNameField().getText())) {
                    // 如果操作成功
                    JOptionPane.showMessageDialog(adminPanelInter, "删除成功!\n姓名:" + adminPanelInter.getDeleteStudentNameField().getText());
                } else {
                    // 如果操作失败
                    JOptionPane.showMessageDialog(adminPanelInter, "删除失败!可能没有这个学生");
                }
            }
        } else if (buttonText.equals("查看所有学生")) {
            try {
                if (this.allStudentsTable != null) {
                    this.allStudentsTable.dispose();
                }
                this.allStudentsTable = new AllStudentsTable(operationMysql);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (buttonText.equals("关于")) {
            JOptionPane.showMessageDialog(adminPanelInter, "长沙学院 20计科03 曹应龙编写\n个人博客地址 http://ylcao.top\n个人Github地址 https://github.com/YinglongCao");
        }

    }
}
