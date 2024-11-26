package org.example.Laba9_1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {
    private boolean isSwitched = true;

    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage) {
        primaryStage.setTitle("Многофункциональное приложение");

        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(20));

        //Задание 1
        HBox wordSwitcher = createWordSwitcher();
        mainLayout.getChildren().add(wordSwitcher);

        //Задание 2
        VBox widgetHider = createWidgetHider();
        mainLayout.getChildren().add(widgetHider);

        //Задание 3
        VBox restaurantOrder = createRestaurantOrder();
        mainLayout.getChildren().add(restaurantOrder);

        //Задание 4
        VBox calculator = createCalculator();
        mainLayout.getChildren().add(calculator);

        //Задание 5
        VBox textFlag = createTextFlag();
        mainLayout.getChildren().add(textFlag);

        Scene scene = new Scene(mainLayout, 1280, 1024);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    //Задание 1
    private HBox createWordSwitcher() {
        TextField inputField = new TextField();
        TextField outputField = new TextField();
        Button switchButton = new Button("->");

        switchButton.setOnAction(e -> {
            if (isSwitched) {
                outputField.setText(inputField.getText());
                inputField.clear();
                switchButton.setText("<-");
            } else {
                inputField.setText(outputField.getText());
                outputField.clear();
                switchButton.setText("->");
            }
            isSwitched = !isSwitched;
        });

        return new HBox(10, inputField, switchButton, outputField);
    }

    //Задание 2
    private VBox createWidgetHider() {
        Label label1 = new Label("Виджет 1");
        Label label2 = new Label("Виджет 2");
        Label label3 = new Label("Виджет 3");

        CheckBox checkBox1 = new CheckBox("Скрыть Виджет 1");
        checkBox1.setOnAction(e -> label1.setVisible(!checkBox1.isSelected()));

        CheckBox checkBox2 = new CheckBox("Скрыть Виджет 2");
        checkBox2.setOnAction(e -> label2.setVisible(!checkBox2.isSelected()));

        CheckBox checkBox3 = new CheckBox("Скрыть Виджет 3");
        checkBox3.setOnAction(e -> label3.setVisible(!checkBox3.isSelected()));

        return new VBox(10, label1, checkBox1, label2, checkBox2, label3, checkBox3);
    }

    //Задание 3
    private VBox createRestaurantOrder() {
        CheckBox pizza = new CheckBox("Пицца - $10");
        TextField pizzaQuantity = new TextField("1");

        CheckBox burger = new CheckBox("Бургер - $5");
        TextField burgerQuantity = new TextField("1");

        CheckBox salad = new CheckBox("Салат - $7");
        TextField saladQuantity = new TextField("1");

        TextArea orderSummary = new TextArea();
        Button orderButton = new Button("Заказать");

        orderButton.setOnAction(e -> {
            StringBuilder summary = new StringBuilder("Чек:\n");
            double totalCost = 0;

            if (pizza.isSelected()) {
                int qty = Integer.parseInt(pizzaQuantity.getText());
                summary.append("Пицца: ").append(qty).append(" - $").append(qty * 10).append("\n");
                totalCost += qty * 10;
            }

            if (burger.isSelected()) {
                int qty = Integer.parseInt(burgerQuantity.getText());
                summary.append("Бургер: ").append(qty).append(" - $").append(qty * 5).append("\n");
                totalCost += qty * 5;
            }

            if (salad.isSelected()) {
                int qty = Integer.parseInt(saladQuantity.getText());
                summary.append("Салат: ").append(qty).append(" - $").append(qty * 7).append("\n");
                totalCost += qty * 7;
            }

            summary.append("Общая стоимость: $").append(totalCost);
            orderSummary.setText(summary.toString());
        });

        return new VBox(10, pizza, pizzaQuantity, burger, burgerQuantity, salad, saladQuantity, orderButton, orderSummary);
    }

    //Задание 4
    private VBox createCalculator() {
        TextField inputField = new TextField();
        Label resultLabel = new Label("Результат: 0");

        Button addButton = new Button("+");
        Button subtractButton = new Button("-");
        Button multiplyButton = new Button("*");
        Button divideButton = new Button("/");

        addButton.setOnAction(e -> calculate(inputField, resultLabel, "+"));
        subtractButton.setOnAction(e -> calculate(inputField, resultLabel, "-"));
        multiplyButton.setOnAction(e -> calculate(inputField, resultLabel, "*"));
        divideButton.setOnAction(e -> calculate(inputField, resultLabel, "/"));

        return new VBox(10, inputField, addButton, subtractButton, multiplyButton, divideButton, resultLabel);
    }

    private void calculate(TextField inputField, Label resultLabel, String operator) {
        String[] parts = inputField.getText().split(" ");
        if (parts.length == 2) {
            try {
                double num1 = Double.parseDouble(parts[0]);
                double num2 = Double.parseDouble(parts[1]);
                double result = 0;
                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 == 0) {
                            resultLabel.setText("Делить на ноль нельзя");
                            return;
                        }
                        result = num1 / num2;
                        break;
                }
                resultLabel.setText("Результат: " + result);
            } catch (NumberFormatException e) {
                resultLabel.setText("Неправильно введено");
            }
        } else {
            resultLabel.setText("Введите два числа");
        }
    }

    //Задание 5
    private VBox createTextFlag() {
        ToggleGroup group = new ToggleGroup();
        RadioButton redButton = new RadioButton("Красный");
        RadioButton greenButton = new RadioButton("Зеленый");
        RadioButton blueButton = new RadioButton("Синий");
        redButton.setToggleGroup(group);
        greenButton.setToggleGroup(group);
        blueButton.setToggleGroup(group);

        Button drawButton = new Button("Нарисовать");
        drawButton.setOnAction(e -> {
            StringBuilder message = new StringBuilder("Выбранные цвета: ");
            if (redButton.isSelected()) message.append("Красный, ");
            if (greenButton.isSelected()) message.append("Зеленый, ");
            if (blueButton.isSelected()) message.append("Синий, ");
            System.out.println(message.toString());
        });

        return new VBox(10, redButton, greenButton, blueButton, drawButton);
    }
}