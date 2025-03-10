/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import clock.AlarmEvent;
import clock.IAlarmListener;
import controller.Computer.ComputerController;
import controller.Repairs.RepairsController;
import controller.Reports.ReportsController;
import controller.controllerAldComputerService.AldComputerServiceController;
import controller.controllerClock.ClockController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.help.HelpSetException;
import javax.swing.JOptionPane;
import model.modelAldComputerService.AldComputerService;
import view.MainViewJFrame;
import view.viewAldComputerService.AldComputerServiceView;
import view.viewClock.ClockDialogView;
import view.viewComputersAndRepairs.ComputersAndRepairsView;
import view.viewReports.ReportsDialogView;

/**
 *
 * @author dides
 */
public class FrontControllerJFrame implements IAlarmListener {

    private final MainViewJFrame view;
    private final AldComputerService model;
    private HelpBroker hb;

    public FrontControllerJFrame(MainViewJFrame view, AldComputerService model) throws MalformedURLException, HelpSetException {
        this.view = view;
        this.model = model;
        this.view.setQuitMenuItemListener(this.setQuitMenuItemActionListener());
        this.view.setAldComputerServiceMenuItemListener(this.setAldComputerServiceItemActionListener());
        this.view.setComputerMenuItemListener(this.setComputerMenuItemActionListener());
        this.view.setClockNotificationsMenuItemListener(this.setClockDialogActionListener());
        this.view.setReportsMenuItemListener(this.setReportsMenuItemActionListener());
        this.configureAlarmListener();
        this.view.getContentPane().setFocusable(true);
        this.initHelp();
        
        
    }

    private ActionListener setQuitMenuItemActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
                System.exit(0);
            }
        };
        return al;
    }

    private ActionListener setAldComputerServiceItemActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AldComputerServiceView aldcsView = new AldComputerServiceView(view, true);
                AldComputerServiceController aldcsController = new AldComputerServiceController(aldcsView, model);
                aldcsView.setVisible(true);
            }
        };
        return al;
    }

    private ActionListener setComputerMenuItemActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ComputersAndRepairsView computerView = new ComputersAndRepairsView(view, true);
                ComputerController computerController = new ComputerController(computerView, model);
                RepairsController repairsController=new RepairsController(computerView,model);
                computerView.setVisible(true);

            }
        };
        return al;
    }

    private ActionListener setClockDialogActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClockDialogView cd = new ClockDialogView(view, true);
                ClockController cc = new ClockController(cd, view);
                cd.setVisible(true);
            }
        };

        return al;

    }

    private void configureAlarmListener() {
        view.addAlarmListener(this);
    }

    @Override
    public void captureAlarm(AlarmEvent ae) {
        view.setEnableAlarm(false);
        JOptionPane.showMessageDialog(view, view.getMessage());

    }

    private ActionListener setReportsMenuItemActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReportsDialogView rdv = new ReportsDialogView(view, true);
                ReportsController rc = new ReportsController(rdv, model);
                rdv.setVisible(true);

            }
        };
        return al;
    }

    private void initHelp() throws MalformedURLException, HelpSetException {
        File helpFile = new File("help/help_set.hs");
        URL hsURL = helpFile.toURI().toURL();
        HelpSet helpSet = new HelpSet(getClass().getClassLoader(), hsURL);
        this.hb = helpSet.createHelpBroker();
        hb.enableHelpKey(view.getContentPane(), "files", helpSet);
    }
    

}
