/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import controller.FrontControllerJFrame;
import java.net.MalformedURLException;
import javax.help.HelpSetException;
import model.modelAldComputerService.AldComputerService;
import view.MainViewJFrame;

/**
 *
 * @author dides
 */
public class Main {

    public static void main(String[] args) throws MalformedURLException, HelpSetException {
        MainViewJFrame mainview = new MainViewJFrame();
        AldComputerService model = new AldComputerService();
        FrontControllerJFrame fc = new FrontControllerJFrame(mainview, model);
        mainview.setVisible(true);

    }
}
