package top.ylcao.StdMan.handler;

import top.ylcao.StdMan.main.Log;
import top.ylcao.StdMan.sql.OperationMysql;
import top.ylcao.StdMan.view.AdminPanelInter;
import top.ylcao.StdMan.view.AllStudentsTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
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

    private void addStudent(String ID, String name, String sex, String IDNumber, String grade, String studentClass) {

    }

    private void deleteStudent(String name) {

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
                addStudent(adminPanelInter.getAddStudentIDField().getText(), adminPanelInter.getAddStudentNameField().getText(), adminPanelInter.getAddStudentSexField().getText(), adminPanelInter.getAddStudentIDNumberField().getText(), adminPanelInter.getAddStudentGradeField().getText(), adminPanelInter.getAddStudentClassField().getText());
            }
        } else if (buttonText.equals("删除")) {
            if (adminPanelInter.getDeleteStudentNameField().getText().equals("")) {
                JOptionPane.showMessageDialog(adminPanelInter, "请输入要删除的学生姓名!");
            } else {
                deleteStudent(adminPanelInter.getDeleteStudentNameField().getText());
            }
        } else if (buttonText.equals("查看所有学生")) {
            try {
                if (this.allStudentsTable == null) {
                    this.allStudentsTable = new AllStudentsTable(operationMysql);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (buttonText.equals("关于")) {
            JOptionPane.showMessageDialog(adminPanelInter, "长沙学院 20计科03 曹应龙编写\n个人博客地址 http://ylcao.top\n个人Github地址 https://github.com/YinglongCao");
        }

    }
}