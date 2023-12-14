package com.example.electronicrx_program;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DrugIndex {
    private Prescription prescription;
    private ArrayList<Drug> drugIndexList = new ArrayList<Drug>();
    private ArrayList<Button> drugInfoButtonList = new ArrayList<Button>();
    public DrugIndex()
    {
        //creates a DecimalFormat with pattern "0.00" to round to two decimal places
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        //creates a Font object with the bold "Trebuchet MS" font, with size adjusted for titles
        Font titleFont = Font.font("Trebuchet MS", FontWeight.BOLD, 18);
        //creates a Font object with the bold "Trebuchet MS" font, with size adjusted for buttons
        Font buttonFont = Font.font("Trebuchet MS", FontWeight.BOLD, 16);
        //creates a Font object with the bold "Trebuchet MS" font, with size adjusted for drugInfo
        Font infoFont = Font.font("Trebuchet MS", FontWeight.BOLD, 15);
        //creates a Font object with the bold "Trebuchet MS" font, with size adjusted for TextFields
        Font textFont = Font.font("Trebuchet MS", FontWeight.NORMAL, 15);
        //creates the GridPane
        GridPane drugIndexPane = new GridPane();
        drugIndexPane.setPrefSize(1120, 491);
        drugIndexPane.setVgap(30);

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setValignment(VPos.TOP);
        drugIndexPane.getRowConstraints().add(rowConstraints);

        //column 0 components
        Label drugListLabel = new Label("Drug List:");
        drugListLabel.setFont(titleFont);
        drugIndexPane.add(drugListLabel, 0, 0);
        drugListLabel.setPrefHeight(40);
        drugListLabel.setPrefWidth(200);

        Label spacerLabel = new Label("");
        drugIndexPane.add(spacerLabel, 1, 0);
        spacerLabel.setPrefWidth(200);

        Label spacer1 = new Label("");
        drugIndexPane.add(spacer1, 2, 0);
        spacer1.setPrefSize(100, 40);

        //column 3 components
        Label genericNameLabel = new Label("Generic Name:");
        genericNameLabel.setFont(titleFont);
        drugIndexPane.add(genericNameLabel, 3, 0);
        genericNameLabel.setPrefHeight(40);
        genericNameLabel.setPrefWidth(200);

        TextField genericNameTextField = new TextField();
        genericNameTextField.setText(Prescription.getDrugList()[0].getName());
        genericNameTextField.setFont(textFont);
        drugIndexPane.add(genericNameTextField, 4, 0);
        genericNameTextField.setPrefSize(400, 40);
        genericNameTextField.setEditable(false);



        GridPane drugBox = new GridPane();

        //column 1 components (drugList spans from column 2 to 7)
        ScrollPane drugPane = new ScrollPane(drugBox);
        drugPane.setPrefSize(350, 350);
        drugIndexPane.add(drugPane, 0, 1, 2,7);
        drugPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);


        Label spacer2 = new Label("");
        drugIndexPane.add(spacer2, 2, 1);
        spacer2.setPrefSize(100, 40);

        //column 2 components
        Label brandNameLabel = new Label("Brand Name:");
        brandNameLabel.setFont(titleFont);
        drugIndexPane.add(brandNameLabel, 3, 1);

        TextField brandNameTextField = new TextField();
        brandNameTextField.setText(Prescription.getDrugList()[0].getBrandName());
        brandNameTextField.setFont(textFont);
        drugIndexPane.add(brandNameTextField, 4, 1);
        brandNameTextField.setPrefSize(350, 40);
        brandNameTextField.setEditable(false);


        Label spacer3 = new Label("");
        drugIndexPane.add(spacer3, 2, 2);
        spacer3.setPrefSize(100, 40);

        Label ndcLabel = new Label("NDC:");
        ndcLabel.setFont(titleFont);
        drugIndexPane.add(ndcLabel, 3, 2);

        TextField ndcTextField = new TextField();
        ndcTextField.setText(Prescription.getDrugList()[0].getNdc());
        ndcTextField.setFont(textFont);
        drugIndexPane.add(ndcTextField, 4, 2);
        ndcTextField.setPrefSize(350, 40);
        ndcTextField.setEditable(false);

        Label spacer4 = new Label("");
        drugIndexPane.add(spacer4, 2, 3);
        spacer4.setPrefSize(100, 40);

        Label sigLabel = new Label("SIG:");
        sigLabel.setFont(titleFont);
        drugIndexPane.add(sigLabel, 3, 3);

        TextField sigTextField = new TextField();
        sigTextField.setText(Prescription.getDrugList()[0].getInstructions());
        sigTextField.setFont(textFont);
        drugIndexPane.add(sigTextField, 4, 3);
        sigTextField.setPrefSize(350, 40);
        sigTextField.setEditable(false);


        Label spacer5 = new Label("");
        drugIndexPane.add(spacer5, 2, 4);
        spacer5.setPrefSize(100, 40);

        Label ingredientCostLabel = new Label("Price Per 30: ");
        ingredientCostLabel.setFont(titleFont);
        drugIndexPane.add(ingredientCostLabel, 3, 4);

        //formats the number using the decimalFormat
        String priceAmt = decimalFormat.format(Prescription.getDrugList()[0].getPricePer30());

        //adds trailing zeroes to bring it to the hundreds place
        if (priceAmt.endsWith(".")) {
            priceAmt += "00";
        } else if (priceAmt.charAt(priceAmt.length() - 2) == '.') {
            priceAmt += "0";
        }

        TextField ingredientCostTextField = new TextField();
        ingredientCostTextField.setText("$" + priceAmt);
        ingredientCostTextField.setFont(textFont);
        drugIndexPane.add(ingredientCostTextField, 4, 4);
        ingredientCostTextField.setPrefSize(350, 40);
        ingredientCostTextField.setEditable(false);

        Label usageLabel = new Label("General Usage:");
        usageLabel.setFont(titleFont);
        drugIndexPane.add(usageLabel, 3, 5);

        TextArea usageTextArea = new TextArea();
        usageTextArea.setText(Prescription.getDrugList()[0].getGeneralUsage());
        usageTextArea.setFont(textFont);
        usageTextArea.setPrefSize(350, 40);
        drugIndexPane.add(usageTextArea, 4, 5, 1,3);
        usageTextArea.setEditable(false);


        for(int i = 0; i < Prescription.getDrugList().length; i++)
        {
            drugIndexList.add(Prescription.getDrugList()[i]);
            final int indexKeeper = i;
            Button drugInfoButton = new Button("(" + Prescription.getDrugList()[i].getNdc() + ") " + Prescription.getDrugList()[i].getName());
            drugInfoButton.setFont(buttonFont);
            drugInfoButton.setPrefSize(400, 30);
            drugBox.add(drugInfoButton, 0, i);
            drugInfoButtonList.add(drugInfoButton);
            drugInfoButton.setOnAction(event ->
            {
                genericNameTextField.setText(Prescription.getDrugList()[indexKeeper].getName());
                brandNameTextField.setText(Prescription.getDrugList()[indexKeeper].getBrandName());
                ndcTextField.setText(Prescription.getDrugList()[indexKeeper].getNdc());
                sigTextField.setText(Prescription.getDrugList()[indexKeeper].getInstructions());
                //formats the number using the decimalFormat
                String subPriceAmt = decimalFormat.format(Prescription.getDrugList()[indexKeeper].getPricePer30());

                //adds trailing zeroes to bring it to the hundreds place
                if (subPriceAmt.endsWith(".")) {
                    subPriceAmt += "00";
                } else if (subPriceAmt.charAt(subPriceAmt.length() - 2) == '.') {
                    subPriceAmt += "0";
                }
                ingredientCostTextField.setText("$" + subPriceAmt);
                usageTextArea.setText(Prescription.getDrugList()[indexKeeper].getGeneralUsage());

            });
        }
        //adds a button made to sort by NDC numbers from low to high based on their starting numbers
        Button ndcSortButton = new Button("Sort NDC (Low-High)");
        ndcSortButton.setFont(buttonFont);
        ndcSortButton.setPrefSize(175, 30);
        drugIndexPane.add(ndcSortButton, 0, 8);
        ndcSortButton.setOnAction(event ->
        {
            drugBox.getChildren().removeAll(drugInfoButtonList);
            drugInfoButtonList.clear();
            mergeSortNdc(drugIndexList);
            for(int i = 0; i < drugIndexList.size(); i++)
            {
                final int indexKeeper = i;
                Button drugInfoButton = new Button("(" + drugIndexList.get(i).getNdc() + ") " + drugIndexList.get(i).getName());
                drugInfoButton.setFont(buttonFont);
                drugInfoButton.setPrefSize(400, 30);
                drugBox.add(drugInfoButton, 0, i);
                drugInfoButtonList.add(drugInfoButton);
                drugInfoButton.setOnAction(deeperEvent ->
                {
                    genericNameTextField.setText(drugIndexList.get(indexKeeper).getName());
                    brandNameTextField.setText(drugIndexList.get(indexKeeper).getBrandName());
                    ndcTextField.setText(drugIndexList.get(indexKeeper).getNdc());
                    sigTextField.setText(drugIndexList.get(indexKeeper).getInstructions());
                    ingredientCostTextField.setText("$" + drugIndexList.get(indexKeeper).getPricePer30());
                    usageTextArea.setText(drugIndexList.get(indexKeeper).getGeneralUsage());
                });
            }
        });

        Button nameSortButton = new Button("Sort Name (A-Z)");
        nameSortButton.setFont(buttonFont);
        nameSortButton.setPrefSize(175, 30);
        drugIndexPane.add(nameSortButton, 1, 8);
        drugIndexPane.setHalignment(nameSortButton, HPos.RIGHT);
        nameSortButton.setOnAction(event ->
        {
            drugBox.getChildren().removeAll(drugInfoButtonList);
            drugInfoButtonList.clear();
            mergeSortAlphabetical(drugIndexList);
            for(int i = 0; i < drugIndexList.size(); i++)
            {
                final int indexKeeper = i;
                Button drugInfoButton = new Button("(" + drugIndexList.get(i).getNdc() + ") " + drugIndexList.get(i).getName());
                drugInfoButton.setFont(buttonFont);
                drugInfoButton.setPrefSize(400, 30);
                drugBox.add(drugInfoButton, 0, i);
                drugInfoButtonList.add(drugInfoButton);
                drugInfoButton.setOnAction(deeperEvent ->
                {
                    genericNameTextField.setText(drugIndexList.get(indexKeeper).getName());
                    brandNameTextField.setText(drugIndexList.get(indexKeeper).getBrandName());
                    ndcTextField.setText(drugIndexList.get(indexKeeper).getNdc());
                    sigTextField.setText(drugIndexList.get(indexKeeper).getInstructions());
                    ingredientCostTextField.setText("$" + drugIndexList.get(indexKeeper).getPricePer30());
                    usageTextArea.setText(drugIndexList.get(indexKeeper).getGeneralUsage());
                });
            }
        });
//        Button rxButton = new Button("Get Complete RX");
//        nameSortButton.setOnAction(event ->

        //set an inner border to the drugIndexPane
        BorderPane innerBorderPane = new BorderPane();
        innerBorderPane.setCenter(drugIndexPane);
        //set a border to the borderPane
        innerBorderPane.setStyle("-fx-border-color: TRANSPARENT; -fx-border-width: 30px;");

        //create a borderPane to hold the pharmacyPane
        BorderPane outerBorderPane = new BorderPane();
        outerBorderPane.setCenter(innerBorderPane);
        outerBorderPane.setStyle("-fx-border-color: gray; -fx-border-width: 5px;");

        Scene drugIndexScene = new Scene(outerBorderPane, 1155, 526);
        Stage drugIndexStage = new Stage();
        drugIndexStage.setScene(drugIndexScene);

        drugIndexStage.setTitle("DRUG INDEX");
        drugIndexStage.show();
        drugIndexScene.getRoot().requestFocus();
        drugIndexStage.setOnCloseRequest(event -> {
            ElectronicRX.setIsDrugIndexOpen(false); // Change the variable when the "x" button is clicked
        });
    }
    //NDC merge sort algorithm for ArrayList
    public static void mergeSortNdc(ArrayList<Drug> list) {
        Drug[] temp = new Drug[list.size()];
        mergeSortNdcHelper(list, 0, list.size() - 1, temp);
        for (Drug drug : list) {
            System.out.print(drug + " ");
        }
        System.out.println();
    }

    private static void mergeSortNdcHelper(ArrayList<Drug> list, int from, int to, Drug[] temp) {
        if (from < to) {
            int middle = (from + to) / 2;
            mergeSortNdcHelper(list, from, middle, temp);
            mergeSortNdcHelper(list, middle + 1, to, temp);
            mergeNdc(list, from, middle, to, temp);
        }
    }

    private static void mergeNdc(ArrayList<Drug> list, int from, int mid, int to, Drug[] temp) {
        int i = from;
        int j = mid + 1;
        int k = from;

        while (i <= mid && j <= to) {
            if (list.get(i).compareToNdc(list.get(j)) < 0) {
                temp[k] = list.get(i);
                i++;
            } else {
                temp[k] = list.get(j);
                j++;
            }
            k++;
        }

        while (i <= mid) {
            temp[k] = list.get(i);
            i++;
            k++;
        }

        while (j <= to) {
            temp[k] = list.get(j);
            j++;
            k++;
        }

        for (k = from; k <= to; k++) {
            list.set(k, temp[k]);
        }
    }


    //Alphabetical merge sort algorithm for ArrayList
    public static void mergeSortAlphabetical(ArrayList<Drug> list) {
        Drug[] temp = new Drug[list.size()];
        mergeSortAlphabeticalHelper(list, 0, list.size() - 1, temp);
        for (Drug drug : list) {
            System.out.print(drug + " ");
        }
        System.out.println();
    }

    private static void mergeSortAlphabeticalHelper(ArrayList<Drug> list, int from, int to, Drug[] temp) {
        if (from < to) {
            int middle = (from + to) / 2;
            mergeSortAlphabeticalHelper(list, from, middle, temp);
            mergeSortAlphabeticalHelper(list, middle + 1, to, temp);
            mergeAlphabetical(list, from, middle, to, temp);
        }
    }

    private static void mergeAlphabetical(ArrayList<Drug> list, int from, int mid, int to, Drug[] temp) {
        int i = from;
        int j = mid + 1;
        int k = from;

        while (i <= mid && j <= to) {
            if (list.get(i).compareToDrugName(list.get(j)) < 0) {
                temp[k] = list.get(i);
                i++;
            } else {
                temp[k] = list.get(j);
                j++;
            }
            k++;
        }

        while (i <= mid) {
            temp[k] = list.get(i);
            i++;
            k++;
        }

        while (j <= to) {
            temp[k] = list.get(j);
            j++;
            k++;
        }

        for (k = from; k <= to; k++) {
            list.set(k, temp[k]);
        }
    }

}
