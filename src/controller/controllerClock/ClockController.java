/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.controllerClock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.MainViewJFrame;
import view.viewClock.ClockDialogView;

/**
 *
 * @author barreirolistediego
 */
public class ClockController {

    private final ClockDialogView view;
    private final MainViewJFrame frameView;

    public ClockController(ClockDialogView view, view.MainViewJFrame frameView) {
        this.frameView = frameView;
        this.view = view;
        this.view.setAddsetClockButtonActionListener(ClockButtonActionListener());
        this.view.setAddDisableAlarmButtonActionListener(EnableOrDisableAlarmButtonActionListener());
        updateView();
    }

    private void setClock() {
        frameView.getMode();
        frameView.setHourAlarm(view.getHourAlarm());
        frameView.setMinutesAlarm(view.getMinutesAlarm());
        frameView.setMessage(view.getMessage());
        if (view.getSelectedOption().equals("24h")) {
            frameView.setMode(true);
        } else {
            frameView.setMode(false);
        }

    }

    private void updateView() {
        view.setHourAlarm(frameView.getHourAlarm());
        view.setMinuteAlarm(frameView.getMinutesAlarm());
        view.setMessage(frameView.getMessage());
        view.setMode(frameView.getMode());
        view.setEnableAlarm(frameView.isEnableAlarm());
    }

    private ActionListener ClockButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setClock();
            }
        };
        return al;
    }

    private ActionListener EnableOrDisableAlarmButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (frameView.isEnableAlarm()) {
                    frameView.setEnableAlarm(false);
                } else {
                    frameView.setEnableAlarm(true);

                }
                setClock();
                updateView();
            }
        };
        return al;
    }

}
