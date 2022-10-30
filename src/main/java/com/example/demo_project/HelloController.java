package com.example.demo_project;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import com.example.demo_project.HelloApplication;
import javafx.stage.Window;


public class HelloController {

    private final HashMap<String, String> info_map = new HashMap<>();
    private double temp_value_converted;
    private String from_temp_selected;
    private String to_temp_selected;

    @FXML
    private TextField value_enter;

    @FXML
    private Button calc_button, about_button;

    @FXML
    private RadioButton r_cel_set_button, r_kal_set_button, r_far_set_button;

    @FXML
    private RadioButton r_cel_get_button, r_kal_get_button, r_far_get_button;

    @FXML
    private Label add_info_label, formula_label, answer_label;

    @FXML
    private CheckBox check_all_at_once;



    public void calculate(ActionEvent e){
        temp_value_converted = 0;
        String formula_text = "Виберіть шкалу...";
        double temp_given;
        try {
            temp_given = Double.parseDouble(value_enter.getText());

            if (from_temp_selected == null || to_temp_selected == null ){
                formula_label.setText(formula_text);
                answer_label.setText(String.valueOf(temp_value_converted));
                return;
            }

            if (check_all_at_once.isSelected()){
                String formulas_arr = "";
                double[] temp_values_array = new double[3];
                if (from_temp_selected.equals("Celsius")){

                    to_temp_selected = "Celsius";
                    formulas_arr += calculate_from_Celsius(temp_given) + "\n";
                    temp_values_array[0] = temp_value_converted;

                    to_temp_selected = "Kalvin";
                    formulas_arr += calculate_from_Celsius(temp_given) + "\n";
                    temp_values_array[1] = temp_value_converted;

                    to_temp_selected = "Fahrenheit";
                    formulas_arr += calculate_from_Celsius(temp_given) + "\n";
                    temp_values_array[2] = temp_value_converted;

                } else if (from_temp_selected.equals("Kalvin")){

                    to_temp_selected = "Celsius";
                    formulas_arr += calculate_from_Kalvin(temp_given) + "\n";
                    temp_values_array[0] = temp_value_converted;

                    to_temp_selected = "Kalvin";
                    formulas_arr += calculate_from_Kalvin(temp_given) + "\n";
                    temp_values_array[1] = temp_value_converted;

                    to_temp_selected = "Fahrenheit";
                    formulas_arr += calculate_from_Kalvin(temp_given) + "\n";
                    temp_values_array[2] = temp_value_converted;

                } else {

                    to_temp_selected = "Celsius";
                    formulas_arr += calculate_from_Fahrenheit(temp_given) + "\n";
                    temp_values_array[0] = temp_value_converted;

                    to_temp_selected = "Kalvin";
                    formulas_arr += calculate_from_Fahrenheit(temp_given) + "\n";
                    temp_values_array[1] = temp_value_converted;

                    to_temp_selected = "Fahrenheit";
                    formulas_arr += calculate_from_Fahrenheit(temp_given) + "\n";
                    temp_values_array[2] = temp_value_converted;

                }


                formula_label.setText(formulas_arr);

                String results_numeric = "";
                for (int i = 0; i < 3; i++){
                    results_numeric += String.format("%.2f", temp_values_array[i]) + "\n" ;
                }

                answer_label.setText(results_numeric);
                return;
            }

            switch (from_temp_selected) {
                case "Celsius" -> formula_text = calculate_from_Celsius(temp_given);
                case "Kalvin" -> formula_text = calculate_from_Kalvin(temp_given);
                case "Fahrenheit" -> formula_text = calculate_from_Fahrenheit(temp_given);
            }


            formula_label.setText(formula_text);
            answer_label.setText(String.valueOf(String.format("%.2f", temp_value_converted)));

        } catch (Exception a){
            formula_label.setText("Введіть правильне значення...");
            answer_label.setText("Введіть правильне значення...");

        }

    }

    private String calculate_from_Celsius(double given_value){
        if (to_temp_selected.equals("Celsius")){
            temp_value_converted = given_value;
            // String.format("%.2f", temp_values_array[i])
            return String.format("%.2f", given_value) + " C = " + String.format("%.2f", temp_value_converted) + " C";
        }

        if (to_temp_selected.equals("Kalvin")){
            temp_value_converted = given_value + 273.15;
            return String.format("%.2f", given_value) + " C + 273.15 = " + String.format("%.2f", temp_value_converted) + " K";
        }

        if (to_temp_selected.equals("Fahrenheit")){
            temp_value_converted = (given_value * 9/5) + 32;
            return "(" + String.format("%.2f", given_value) + " C * 9/5) + 32 = " + String.format("%.2f", temp_value_converted) + " F";
        }

        return "NULL_FROM_CELSIUS";
    }

    private String calculate_from_Kalvin(double given_value){
        if (to_temp_selected.equals("Kalvin")){
            temp_value_converted = given_value;
            return String.format("%.2f", given_value) + " K = " + String.format("%.2f", temp_value_converted) + " K";
        }

        if (to_temp_selected.equals("Celsius")){
            temp_value_converted = given_value - 273.15;
            return String.format("%.2f", given_value) + " K - 273.15 = " + String.format("%.2f", temp_value_converted) + " C";
        }

        if (to_temp_selected.equals("Fahrenheit")){
            temp_value_converted = ((given_value - 273) * 9/5) + 32;
            return "(" + String.format("%.2f", given_value) + " K - 273) * 9/5)) + 32 = " + String.format("%.2f", temp_value_converted) + " F";
        }

        return "NULL_FROM_KALVIN";
    }

    private String calculate_from_Fahrenheit(double given_value){

        if (to_temp_selected.equals("Fahrenheit")){
            temp_value_converted = given_value;
            return String.format("%.2f", given_value) + " F = " + String.format("%.2f", temp_value_converted) + " F";
        }

        if (to_temp_selected.equals("Celsius")){
            temp_value_converted = (given_value - 32) * 0.5556;
            return "(" + String.format("%.2f", given_value) + " F - 32) x 5.5556 = " + String.format("%.2f", temp_value_converted) + " C";
        }

        if (to_temp_selected.equals("Kalvin")){
            temp_value_converted = ((given_value - 32) * 0.5556) + 273.15;
            return "(" + String.format("%.2f", given_value) + " F - 32) * 0.5556 + 273.15 = " + String.format("%.2f", temp_value_converted) + " K";
        }

        return "NULL_FROM_FAHRENHEIT";
    }

    public void from_temp_set(ActionEvent e){
        if (r_cel_set_button.isSelected()){
            from_temp_selected = "Celsius";
            System.out.println(from_temp_selected);
            return;
        }

        if (r_kal_set_button.isSelected()){
            from_temp_selected = "Kalvin";
            System.out.println(from_temp_selected);
            return;
        }

        if (r_far_set_button.isSelected()){
            from_temp_selected = "Fahrenheit";
            System.out.println(from_temp_selected);
        }

    }

    public void to_temp_set(ActionEvent e){

        if (r_cel_get_button.isSelected()){
            add_info_label.setText(info_map.get("Celsius"));
            to_temp_selected = "Celsius";
            System.out.println(to_temp_selected);
            return;
        }

        if (r_kal_get_button.isSelected()){
            add_info_label.setText(info_map.get("Kalvin"));
            to_temp_selected = "Kalvin";
            System.out.println(to_temp_selected);
            return;
        }

        if (r_far_get_button.isSelected()){
            add_info_label.setText(info_map.get("Fahrenheit"));
            to_temp_selected = "Fahrenheit";
            System.out.println(to_temp_selected);
        }
    }

    public void exit(ActionEvent e){
        System.out.println("Exit button clicked");
        Platform.exit();
    }

    public void about_window_call(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("about_window.fxml"));

        Stage about_window = new Stage();

        Scene scene = new Scene(fxmlLoader.load());

        about_window.initModality(Modality.WINDOW_MODAL);
        about_window.initOwner(((Node) e.getTarget()).getScene().getWindow());
        about_window.setTitle("Інформація про студента");
        about_window.setScene(scene);
        about_window.show();
    }

    public HelloController(){
        info_map.put("Celsius", "Шкала Цельсія\nРік створення: 1742\nАвтор: Андерс Цельсій");
        info_map.put("Kalvin", "Шкала Кальвіна\nРік створення: 1848\nАвтор: Вільям Томсон");
        info_map.put("Fahrenheit", "Шкала Фаренгейта\nРік створення: 1724\nАвтор: Данієль Фаренгейт");

    }


}