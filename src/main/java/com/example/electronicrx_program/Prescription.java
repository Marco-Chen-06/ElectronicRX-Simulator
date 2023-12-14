package com.example.electronicrx_program;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

import static com.example.electronicrx_program.ElectronicRX.getPrescriptionCount;

public class Prescription {
    private static Drug atorvastatin = new Drug("Atorvastatin Calcium Tablet", "Lipitor (OTC)", "0378-3950-77", "Take 1 tablet with water daily", 9.56, "Treats high cholesterol, reduces heart-related risks,\nand prevents cardiovascular diseases.");
    private static Drug loratadine = new Drug("Loratadine Antihistamine Tablet", "Claritin (OTC)",  "45802-650-65", "Take 1 tablet daily with or without food", 17.41,  "Relieves allergy symptoms, such as sneezing and itching, \nby blocking histamine receptors.");
    private static Drug amlopidine = new Drug("Amlopidine Besylate Tablet", "Norvasc", "0904-6369-61", "Take 1 tablet with water daily", 18.42, "Lowers blood pressure and treats chest pain by relaxing \nblood vessels and improving blood flow.");
    private static Drug carboxymethylcellulose = new Drug("Carboxymethylcellulose Sodium", "Refresh Tears (OTC)", "0023-0798-01", "Instill 1 to 2 drops in affected eyes as needed", 15.10, "Relieves dry eyes by providing lubrication and reducing \neye irritation.");
    private static Drug oxymetazoline = new Drug("Oxymetazoline Nasal Spray", "Afrin (OTC)", "11523-1167-15", "Shake, clear nose, spray in one nostril when needed", 11.33, "Relieves nasal congestion by constricting blood \nvessels in the nasal passages.");
    private static Drug acetaminophen = new Drug("Acetaminophen Tablet", "Tylenol", "49483-341-01", "Take 2 tablets every 6 hours as needed", 10.73, "Reduces pain and fever by inhibiting certain chemicals \nin the brain.");
    private static Drug metoprolol = new Drug("Metoprolol Succinate Tablet", "Toprol-XL", "43353-583-30", "Take 1 tablet daily", 22.30, "Controls high blood pressure and prevents chest pain \nand heart attacks by blocking certain receptors.");

    private static Drug lisinopril = new Drug("Lisinopril Oral Tablet", "Zestril", "52427-438-90", "Take 1 tablet daily", 19.87, "Treats high blood pressure, and used with other \nmedications to treat heart failure.");
    private static Drug gabapentin = new Drug("Gabapentin Extended-Release", "Neurontin", "0071-0401-24", "Take 3 tablets daily, less than 12 hours between doses", 13.29, "Treats seizures in adults with epilepsy and nerve \npain. Beware of patient addiction and misuse.");
    private static Drug metaproterenol = new Drug("Metaproterenol Tablet", "Alupent", "0597-0070-08", "Take 1 tablet twice a day as needed", 23.84, "Used to prevent and treat wheezing, shortness of \nbreath, coughing, and chest tightness caused by \nasthma, chronic bronchitis, lung disease");
    private String realIngredientCost;
    private String ingredientCostPlaceholder;
    private String realDrugCost;
    private String drugCostPlaceholder;
    private String copayAmt;
    private boolean hasOpened = false;

    //array of patient first names to be used for prescriptions
    private static String[] patientFirstNameList = {"John", "Marco", "Michael", "Robert", "David", "James", "Jose", "Juan", "Justin", "Edward",
            "Condoriano", "Martin", "Roy", "Al", "Louis", "Jean", "Joe", "Olivia", "Charlotte", "Amelia", "Sophia", "Emily", "Hannah", "Amy",
            "Mary", "Helen", "Leeven", "Marco", "Monkey D.", "Brook", "Ling", "Alex", "Tim", "Levi", "Zeke", "Petra", "Thomas", "Ian", "Cory",
            "Dylan", "Ivan", "Zhi Ming", "Edwin", "Qiyu", "Steven", "Kyle", "Aidan", "Ethan", "Kevin", "John", "David", "William", "Richard", "Linda",
            "Karen", "Lisa", "Nancy", "Betty", "Sandra", "Jennifer", "Patricia", "Ashley", "Melissa", "Deborah", "Dorothy", "Ruth", "Evelyn", "Megan",
            "Madison", "Teresa", "Gloria", "Sara", "Kayla", "Eugene", "Ralph", "Vincent", "Wayne", "Gerald", "Stanley", "Zachary", "Henry", "Nathan",
            "Adam", "Tyler", "Jerry", "Raymond", "Brandon", "Donald", "Mark", "Quincy"};
    //array of patient last names to be used for prescriptions
    private static String[] patientLastNameList = {"Liu", "Ng", "Chen", "Li", "Lin", "Tang", "Xu", "Zhu", "Wong", "Smith", "Johnson", "Williams",
            "Jones", "Garcia", "Davis", "Lee", "Holmer", "Jackson", "Rivera", "Nguyen", "Gomez", "Mustang", "Washington", "Tucker", "Ferguson",
            "Anderson", "Kennedy", "Rockefeller", "Vanderbilt", "Martins", "White", "Gonzales", "Chan"};
    //array of drugs to be used for prescriptions
    private static Drug[] drugList = {acetaminophen, amlopidine, atorvastatin, carboxymethylcellulose, gabapentin, lisinopril, loratadine, metaproterenol, metoprolol, oxymetazoline};
    private static int[] areaCodeList = {212, 718, 310, 302, 305, 702, 202, 415, 312, 626, 949, 646, 917, 329};
    private String date;
    private int rxNum;
    private String patient;
    private Drug drug;
    private String prescriber;
    private int refillsSupplied;
    private String phoneNumber;
    private Button dateTitleLabelButton;
    private Button rxTitleLabelButton;
    private Button patientTitleLabelButton;
    private Button drugTitleLabelButton;
    private Button prescriberTitleLabelButton;
    private Button refillsTitleLabelButton;
    private Button phoneTitleLabelButton;
    private boolean isRunning = false;
    private boolean isComplete= false;
    public Prescription(boolean isComplete) {
        this.isComplete = isComplete;

        if(isComplete)
        {
            //creates a DecimalFormat with pattern "0.00" to round to two decimal places
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            //creates a Font object with the bold "Trebuchet MS" font, with size adjusted for titles
            Font titleFont = Font.font("Trebuchet MS", FontWeight.BOLD, 18);
            //creates a Font object with the bold "Trebuchet MS" font, with size adjusted for buttons
            Font buttonFont = Font.font("Trebuchet MS", FontWeight.BOLD, 15);
            //creates a Font object with the bold "Trebuchet MS" font, with size adjusted for TextFields
            Font textFont = Font.font("Trebuchet MS", FontWeight.NORMAL, 15);
            //creates a Font object with the bold "Trebuchet MS" font, with size adjusted for congratulationText
            Font rewardFont = Font.font("Trebuchet MS", FontWeight.BOLD, 20);
            //gets and formats the date
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
            //instantiates random prescription information
            date = currentDate.format(formatter);
            rxNum = getPrescriptionCount();
            patient = getRandomName();
            prescriber = getRandomName();
            phoneNumber = getRandomPhoneNumber();
            drug = getRandomDrug();

            refillsSupplied = rand(1, 6);

            //formats the ingredientCost
            realIngredientCost = decimalFormat.format(refillsSupplied * drug.getPricePer30());

            //adds trailing zeroes to bring it to the hundreds place
            if (realIngredientCost.endsWith(".")) {
                realIngredientCost += "00";
            } else if (realIngredientCost.charAt(realIngredientCost.length() - 2) == '.') {
                realIngredientCost += "0";
            }

            //formats the ingredientCost
            ingredientCostPlaceholder = decimalFormat.format(refillsSupplied * drug.getPricePer30());

            //adds trailing zeroes to bring it to the hundreds place
            if (ingredientCostPlaceholder.endsWith(".")) {
                ingredientCostPlaceholder += "00";
            } else if (ingredientCostPlaceholder.charAt(ingredientCostPlaceholder.length() - 2) == '.') {
                ingredientCostPlaceholder += "0";
            }

            //formats the drugCost
            realDrugCost = decimalFormat.format((drug.getPricePer30() - drug.getCopay()) * refillsSupplied);

            //adds trailing zeroes to bring it to the hundreds place
            if (realDrugCost.endsWith(".")) {
                realDrugCost += "00";
            } else if (realDrugCost.charAt(realDrugCost.length() - 2) == '.') {
                realDrugCost += "0";
            }

            //formats the drugCostPlaceholder
            drugCostPlaceholder = decimalFormat.format((drug.getPricePer30() - drug.getCopay()) * refillsSupplied);

            //adds trailing zeroes to bring it to the hundreds place
            if (drugCostPlaceholder.endsWith(".")) {
                drugCostPlaceholder += "00";
            } else if (drugCostPlaceholder.charAt(drugCostPlaceholder.length() - 2) == '.') {
                drugCostPlaceholder += "0";
            }

            //creates a 1st button showing the date preview
            dateTitleLabelButton = new Button(date);
            dateTitleLabelButton.setPrefWidth(157);
            dateTitleLabelButton.setStyle("-fx-border-color: black; -fx-border-width: 0 1 0 0; -fx-border-insets: 0 0 0 0;");
            dateTitleLabelButton.setFocusTraversable(false);
            dateTitleLabelButton.setFont(buttonFont);
            dateTitleLabelButton.setPrefHeight(35);
            //creates a 2nd button showing the RX# preview
            rxTitleLabelButton = new Button("" + getPrescriptionCount());
            rxTitleLabelButton.setPrefWidth(100);
            rxTitleLabelButton.setStyle("-fx-border-color: black; -fx-border-width: 0 1 0 1; -fx-border-insets: 0 0 0 0;");
            rxTitleLabelButton.setFocusTraversable(false);
            rxTitleLabelButton.setFont(buttonFont);
            rxTitleLabelButton.setPrefHeight(35);
            //creates a 3rd button showing the patient name preview
            patientTitleLabelButton = new Button(patient.toUpperCase());
            patientTitleLabelButton.setPrefWidth(265);
            patientTitleLabelButton.setStyle("-fx-border-color: black; -fx-border-width: 0 1 0 1; -fx-border-insets: 0 0 0 0;");
            patientTitleLabelButton.setFocusTraversable(false);
            patientTitleLabelButton.setFont(buttonFont);
            patientTitleLabelButton.setPrefHeight(35);
            //creates a 4th button showing the drug name preview
            drugTitleLabelButton = new Button(drug.getNamePlaceholder().toUpperCase());
            drugTitleLabelButton.setPrefWidth(295);
            drugTitleLabelButton.setStyle("-fx-border-color: black; -fx-border-width: 0 1 0 1; -fx-border-insets: 0 0 0 0;");
            drugTitleLabelButton.setFocusTraversable(false);
            drugTitleLabelButton.setFont(buttonFont);
            drugTitleLabelButton.setPrefHeight(35);
            //creates a 5th button showing the prescriber name preview
            prescriberTitleLabelButton = new Button(prescriber.toUpperCase());
            prescriberTitleLabelButton.setPrefWidth(265);
            prescriberTitleLabelButton.setStyle("-fx-border-color: black; -fx-border-width: 0 1 0 1; -fx-border-insets: 0 0 0 0;");
            prescriberTitleLabelButton.setFocusTraversable(false);
            prescriberTitleLabelButton.setFont(buttonFont);
            prescriberTitleLabelButton.setPrefHeight(35);
            //creates a 6th button showing the refills prescribed preview
            refillsTitleLabelButton = new Button("" + refillsSupplied);
            refillsTitleLabelButton.setPrefWidth(115);
            refillsTitleLabelButton.setStyle("-fx-border-color: black; -fx-border-width: 0 1 0 1; -fx-border-insets: 0 0 0 0;");
            refillsTitleLabelButton.setFocusTraversable(false);
            refillsTitleLabelButton.setFont(buttonFont);
            refillsTitleLabelButton.setPrefHeight(35);
            //creates a 7th button showing the phone number preview
            phoneTitleLabelButton = new Button(phoneNumber);
            phoneTitleLabelButton.setPrefWidth(230);
            phoneTitleLabelButton.setStyle("-fx-border-color: black; -fx-border-width: 0 1 0 1; -fx-border-insets: 0 0 0 0;");
            phoneTitleLabelButton.setFocusTraversable(false);
            phoneTitleLabelButton.setFont(buttonFont);
            phoneTitleLabelButton.setPrefHeight(35);
            //creates a buttonHandler event to account for an entire row worth of events
            EventHandler<ActionEvent> buttonHandler = event -> {
                if (!isRunning) {
                    isRunning = true;
                    //clickedButton references the clicked button in case it needs to be reference
                    Button clickedButton = (Button) event.getSource();

                    //creates a Gridpane to hold the editing components
                    GridPane editPane = new GridPane();
                    editPane.setPrefSize(920, 461);
                    editPane.setVgap(30);


                    //set an inner border to the editPane
                    BorderPane innerBorderPane = new BorderPane();
                    innerBorderPane.setCenter(editPane);
                    //set a border to the borderPane
                    innerBorderPane.setStyle("-fx-border-color: TRANSPARENT; -fx-border-width: 30px;");

                    //create a borderPane to hold the pharmacyPane
                    BorderPane outerBorderPane = new BorderPane();
                    outerBorderPane.setCenter(innerBorderPane);
                    outerBorderPane.setStyle("-fx-border-color: gray; -fx-border-width: 5px;");
                    Scene editScene = new Scene(outerBorderPane, 955, 496);
                    Stage editStage = new Stage();

                    //sets isRunning to false if the stage is closed
                    editStage.setOnCloseRequest(closeEvent -> {
                        isRunning = false; // Change the variable when the "x" button is clicked
                    });

                    //adds spacers between each row
                    RowConstraints rowConstraints = new RowConstraints();
                    rowConstraints.setValignment(VPos.TOP);
                    editPane.getRowConstraints().add(rowConstraints);

                    //adds a label saying "RX#:" to row 0 column 0
                    Label rxLabel = new Label("RX#:");
                    rxLabel.setFont(titleFont);
                    editPane.add(rxLabel, 0, 0);
                    rxLabel.setPrefSize(100, 40);

                    //adds a textField to enter rxInfo to row 0 column 1
                    TextField rxTextField = new TextField();
                    rxTextField.setText("" + rxNum);
                    rxTextField.setFont(textFont);
                    editPane.add(rxTextField, 1, 0);
                    rxTextField.setPrefSize(250, 40);

                    //adds a spacer for visual appeal to row 0 column 2
                    Label spacer1 = new Label("");
                    editPane.add(spacer1, 2, 0);
                    spacer1.setPrefSize(100, 40);

                    //adds a label saying "Date:" to row 0 column 3
                    Label dateLabel = new Label("Date:");
                    dateLabel.setFont(titleFont);
                    editPane.add(dateLabel, 3, 0);

                    //adds a TextField to enter the date to row 0 column 4
                    TextField dateTextField = new TextField();
                    dateTextField.setText(date);
                    dateTextField.setFont(textFont);
                    editPane.add(dateTextField, 4, 0);
                    dateTextField.setPrefSize(250, 40);

                    //adds a label saying "Name:" to row 1 column 0
                    Label nameLabel = new Label("Name:");
                    nameLabel.setFont(titleFont);
                    editPane.add(nameLabel, 0, 1);

                    //adds a textField to enter the patient name to row 1 column 1
                    TextField nameTextField = new TextField();
                    nameTextField.setText(patient);
                    nameTextField.setFont(textFont);
                    editPane.add(nameTextField, 1, 1);
                    nameTextField.setPrefSize(250, 40);

                    //adds a spacer for visual appeal to row 1 column 2
                    Label spacer2 = new Label("");
                    editPane.add(spacer2, 2, 1);
                    spacer2.setPrefSize(100, 40);

                    //adds a label saying "Prescriber Name:" to row 1 column 3
                    Label prescriberLabel = new Label("Prescriber Name: ");
                    prescriberLabel.setFont(titleFont);
                    editPane.add(prescriberLabel, 3, 1);

                    //adds a textField to enter the prescriber name to row 1 column 4
                    TextField prescriberTextField = new TextField();
                    prescriberTextField.setText(prescriber);
                    prescriberTextField.setFont(textFont);
                    editPane.add(prescriberTextField, 4, 1);
                    prescriberTextField.setPrefSize(250, 40);

                    //adds a label saying "Drug Name" to row 2 column 0
                    Label drugNameLabel = new Label("Drug Name:");
                    drugNameLabel.setFont(titleFont);
                    editPane.add(drugNameLabel, 0, 2);

                    //adds a textField to enter the drug name to row 2 column 1
                    TextField drugNameTextField = new TextField();
                    drugNameTextField.setText(drug.getNamePlaceholder());
                    drugNameTextField.setFont(textFont);
                    editPane.add(drugNameTextField, 1, 2);
                    drugNameTextField.setPrefSize(250, 40);

                    //adds a spacer for visual appeal to row 2 column 2
                    Label spacer3 = new Label("");
                    editPane.add(spacer3, 2, 2);
                    spacer3.setPrefSize(100, 40);

                    //adds a label saying "QTY:" to row 2 column 3
                    Label qtyLabel = new Label("QTY:");
                    qtyLabel.setFont(titleFont);
                    editPane.add(qtyLabel, 3, 2);

                    //adds a textField to enter the quantity to row 2 column 4
                    TextField qtyTextField = new TextField();
                    qtyTextField.setText(refillsSupplied * 30 + "");
                    qtyTextField.setFont(textFont);
                    editPane.add(qtyTextField, 4, 2);
                    qtyTextField.setPrefSize(250, 40);

                    //adds a label saying "NDC:" to row 3 column 0
                    Label ndcLabel = new Label("NDC:");
                    ndcLabel.setFont(titleFont);
                    editPane.add(ndcLabel, 0, 3);

                    //adds a textField to enter the ndc number to row 3 column 1
                    TextField ndcTextField = new TextField();
                    ndcTextField.setText(drug.getNdcPlaceholder());
                    ndcTextField.setFont(textFont);
                    ndcTextField.setPrefSize(250, 40);
                    editPane.add(ndcTextField, 1, 3);

                    //adds a spacer for visual appeal to row 3 column 2
                    Label spacer4 = new Label("");
                    editPane.add(spacer4, 2, 3);
                    spacer4.setPrefSize(100, 40);

                    //adds a label saying "Refills Supplied" to row 3 column 3
                    Label refillsPresLabel = new Label("Refills Supplied:");
                    refillsPresLabel.setFont(titleFont);
                    editPane.add(refillsPresLabel, 3, 3);

                    //adds a textField to enter the refills supplied amount to row 3 column 4
                    TextField refillsPresTextField = new TextField();
                    refillsPresTextField.setText("" + refillsSupplied);
                    refillsPresTextField.setFont(textFont);
                    editPane.add(refillsPresTextField, 4, 3);
                    refillsPresTextField.setPrefSize(250, 40);

                    //adds a label saying "Phone #:" to row 4 column 0
                    Label phoneNumberLabel = new Label("Phone #:");
                    phoneNumberLabel.setFont(titleFont);
                    editPane.add(phoneNumberLabel, 0, 4);

                    //adds a textField letting you enter a phone number to row 4 column 1
                    TextField phoneNumberTextField = new TextField();
                    phoneNumberTextField.setText(phoneNumber);
                    phoneNumberTextField.setFont(textFont);
                    phoneNumberTextField.setPrefSize(250, 40);
                    editPane.add(phoneNumberTextField, 1, 4);

                    //adds a spacer for visual appeal to row 4 column 2
                    Label spacer5 = new Label("");
                    editPane.add(spacer5, 2, 4);
                    spacer5.setPrefSize(100, 40);

                    //adds a label saying "Ingr. Cost" to row 4 column 3
                    Label ingredientCostLabel = new Label("Ingr. Cost: ");
                    ingredientCostLabel.setFont(titleFont);
                    editPane.add(ingredientCostLabel, 3, 4);

                    //adds a textField to enter the ingredient cost to row 4 column 4
                    TextField ingredientCostTextField = new TextField();
                    ingredientCostTextField.setText("$" + ingredientCostPlaceholder);
                    ingredientCostTextField.setFont(textFont);
                    editPane.add(ingredientCostTextField, 4, 4);
                    ingredientCostTextField.setPrefSize(250, 40);

                    //adds a label saying "SIG" (Prescription Signature) to row 5 column 0
                    Label sigLabel = new Label("SIG:");
                    sigLabel.setFont(titleFont);
                    editPane.add(sigLabel, 0, 5);

                    //adds a textArea to enter the SIG to row 5 column 1, spanning 1 row horizontally and 3 rows vertically
                    TextArea sigTextArea = new TextArea();
                    sigTextArea.setText(drug.getInstructions());
                    sigTextArea.setFont(textFont);
                    sigTextArea.setPrefSize(250, 40);
                    editPane.add(sigTextArea, 1, 5, 1, 3);

                    //adds a label saying "Copay:" to row 5 column 3
                    Label copayLabel = new Label("Copay:");
                    copayLabel.setFont(titleFont);
                    editPane.add(copayLabel, 3, 5);

                    copayAmt = decimalFormat.format(drug.getCopay() * refillsSupplied);
                    //indicates that the prescription has been opened
                    hasOpened = true;

                    //adds trailing zeroes to bring it to the hundreds place
                    if (copayAmt.endsWith(".")) {
                        copayAmt += "00";
                    } else if (copayAmt.charAt(copayAmt.length() - 2) == '.') {
                        copayAmt += "0";
                    }

                    //adds a textField to enter the copay to row 5 column 4
                    TextField copayTextField = new TextField();
                    copayTextField.setText("$" + copayAmt);
                    copayTextField.setFont(textFont);
                    copayTextField.setPrefSize(250, 40);
                    editPane.add(copayTextField, 4, 5);

                    //adds a label saying "Amt Due:" to row 6 column 3
                    Label costLabel = new Label("Amt Due: ");
                    costLabel.setFont(titleFont);
                    editPane.add(costLabel, 3, 6);

                    //adds a textField to enter the Amt Due to row 6 column 4
                    TextField costTextField = new TextField();
                    costTextField.setText("$" + drugCostPlaceholder);
                    costTextField.setFont(textFont);
                    editPane.add(costTextField, 4, 6);
                    costTextField.setPrefSize(250, 40);

                    //adds a save button that lets you save your prescription to row 7 column 4
                    Button saveButton = new Button("SAVE");
                    saveButton.setFont(titleFont);
                    saveButton.setPrefSize(250, 40);
                    editPane.add(saveButton, 4, 7);
                    saveButton.setOnAction(saveEvent ->
                    {
                        try {
                            date = dateTextField.getText();
                            rxNum = Integer.parseInt(rxTextField.getText());
                            patient = nameTextField.getText();
                            prescriber = prescriberTextField.getText();
                            phoneNumber = phoneNumberTextField.getText();
                            drug.setNamePlaceholder(drugNameTextField.getText());
                            copayAmt = "" + Double.parseDouble(copayTextField.getText().substring(1));
                            drug.setNdcPlaceholder(ndcTextField.getText());

                            dateTitleLabelButton.setText(date);
                            rxTitleLabelButton.setText(rxNum + "");
                            patientTitleLabelButton.setText(patient.toUpperCase());
                            prescriberTitleLabelButton.setText(prescriber.toUpperCase());
                            phoneTitleLabelButton.setText(phoneNumber);
                            drugTitleLabelButton.setText(drug.getNamePlaceholder().toUpperCase());
                            refillsTitleLabelButton.setText(refillsSupplied + "");
                            System.out.println(drug.getNdc());
                            System.out.println(drug.getName());
                            System.out.println(realIngredientCost);
                            System.out.println(realDrugCost);
                            System.out.println(drug.getCopay());
                            System.out.println(Double.parseDouble(copayTextField.getText().substring(1)));
                            drugCostPlaceholder = costTextField.getText().substring(1);
                            ingredientCostPlaceholder = ingredientCostTextField.getText().substring(1);


                            //gives information on what you need to fill out (shows error message)
                            Stage dialogStage = new Stage();
                            dialogStage.initModality(Modality.APPLICATION_MODAL);
                            dialogStage.setTitle("IMPORTANT!!!");
                            dialogStage.setResizable(false);

                            Label messageLabel = new Label();
                            messageLabel.setFont(rewardFont);
                            Button closeButton = new Button("Close");
                            closeButton.setFont(buttonFont);
                            closeButton.setOnAction(eventClose -> dialogStage.close());

                            VBox dialogRoot = new VBox(10);
                            dialogRoot.setAlignment(Pos.CENTER);
                            dialogRoot.getChildren().addAll(messageLabel, closeButton);

                            String ndcText = ndcTextField.getText();
                            String drugNameText = drugNameTextField.getText();
                            String ingredientCostText = ingredientCostTextField.getText().substring(1);
                            String amtDueText = costTextField.getText().substring(1);

                            boolean isNdcCorrect = ndcText.equals(drug.getNdc());
                            boolean isDrugNameCorrect = drugNameText.equals(drug.getName());
                            boolean isIngredientCostCorrect = ingredientCostText.equals(realIngredientCost);
                            boolean isAmtDueCorrect = amtDueText.equals(realDrugCost);

                            if (isNdcCorrect && isDrugNameCorrect && isIngredientCostCorrect && isAmtDueCorrect) {
                                dialogStage.setTitle("CONGRATULATIONS!!!");
                                messageLabel.setText("Perfect Prescription! (GREAT JOB!)");
                            } else {
                                StringBuilder errorMessage = new StringBuilder("Incorrect Components:");

                                if (!isNdcCorrect) {
                                    errorMessage.append("\n- NDC is incorrect");
                                }
                                if (!isDrugNameCorrect) {
                                    errorMessage.append("\n- Drug Name is incorrect");
                                }
                                if (!isIngredientCostCorrect) {
                                    errorMessage.append("\n- Ingredient Cost is incorrect");
                                }
                                if (!isAmtDueCorrect) {
                                    errorMessage.append("\n- Amt Due is incorrect");
                                }

                                dialogStage.setTitle("Prescription Error");
                                messageLabel.setText(errorMessage.toString());
                            }

                            Scene dialogScene = new Scene(dialogRoot, 400, 200);
                            dialogStage.setScene(dialogScene);
                            dialogStage.showAndWait();

                        } catch (NumberFormatException e) {
                            //happens when a NumberFormatException is caught (Likely copay issue)
                            Stage dialogStage = new Stage();
                            dialogStage.initModality(Modality.APPLICATION_MODAL);
                            dialogStage.setTitle("Error!");
                            dialogStage.setResizable(false);

                            Label messageLabel = new Label();
                            messageLabel.setText("Copay Invalid -\nFollow this format: $#.##");
                            messageLabel.setFont(rewardFont);
                            Button closeButton = new Button("Close");
                            closeButton.setFont(buttonFont);
                            closeButton.setOnAction(eventClose -> dialogStage.close());

                            VBox dialogRoot = new VBox(10);
                            dialogRoot.setAlignment(Pos.CENTER);
                            dialogRoot.getChildren().addAll(messageLabel, closeButton);

                            dialogStage.initModality(Modality.APPLICATION_MODAL);
                            dialogStage.initOwner(editStage);
                            Scene dialogScene = new Scene(dialogRoot, 400, 200);
                            dialogStage.setScene(dialogScene);
                            dialogStage.showAndWait();

                        } catch (IndexOutOfBoundsException f) {
                            //happens when an IndexOutOfBoundsException is caught (Likely ingredient or amt due issue)
                            Stage dialogStage = new Stage();
                            dialogStage.initModality(Modality.APPLICATION_MODAL);
                            dialogStage.setTitle("Error!");
                            dialogStage.setResizable(false);

                            Label messageLabel = new Label();
                            messageLabel.setText("Ingredient Cost or Amt Due Invalid: \nFollow this format: $#.##");
                            messageLabel.setFont(rewardFont);
                            Button closeButton = new Button("Close");
                            closeButton.setFont(buttonFont);
                            closeButton.setOnAction(eventClose -> dialogStage.close());

                            VBox dialogRoot = new VBox(10);
                            dialogRoot.setAlignment(Pos.CENTER);
                            dialogRoot.getChildren().addAll(messageLabel, closeButton);

                            dialogStage.initModality(Modality.APPLICATION_MODAL);
                            dialogStage.initOwner(editStage);
                            Scene dialogScene = new Scene(dialogRoot, 400, 200);
                            dialogStage.setScene(dialogScene);
                            dialogStage.showAndWait();
                        }
                    });

                    editStage.setScene(editScene);
                    editStage.setTitle("EDITING " + patient.toUpperCase() + "'S PRESCRIPTION");
                    editStage.show();
                    editScene.getRoot().requestFocus();
                }
            };
            dateTitleLabelButton.setOnAction(buttonHandler);
            rxTitleLabelButton.setOnAction(buttonHandler);
            patientTitleLabelButton.setOnAction(buttonHandler);
            drugTitleLabelButton.setOnAction(buttonHandler);
            prescriberTitleLabelButton.setOnAction(buttonHandler);
            refillsTitleLabelButton.setOnAction(buttonHandler);
            phoneTitleLabelButton.setOnAction(buttonHandler);
        }
        //if isComplete is false
        else
        {
            //creates a DecimalFormat with pattern "0.00" to round to two decimal places
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            //creates a Font object with the bold "Trebuchet MS" font, with size adjusted for titles
            Font titleFont = Font.font("Trebuchet MS", FontWeight.BOLD, 18);
            //creates a Font object with the bold "Trebuchet MS" font, with size adjusted for buttons
            Font buttonFont = Font.font("Trebuchet MS", FontWeight.BOLD, 15);
            //creates a Font object with the bold "Trebuchet MS" font, with size adjusted for TextFields
            Font textFont = Font.font("Trebuchet MS", FontWeight.NORMAL, 15);
            //creates a Font object with the bold "Trebuchet MS" font, with size adjusted for congratulationText
            Font rewardFont = Font.font("Trebuchet MS", FontWeight.BOLD, 20);
            //gets and formats the date
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
            //instantiates random prescription information
            date = currentDate.format(formatter);
            rxNum = getPrescriptionCount();
            patient = getRandomName();
            prescriber = getRandomName();
            phoneNumber = getRandomPhoneNumber();
            drug = getRandomDrug();

            refillsSupplied = rand(1, 6);

            //formats the ingredientCost
            realIngredientCost = decimalFormat.format(refillsSupplied * drug.getPricePer30());

            //adds trailing zeroes to bring it to the hundreds place
            if (realIngredientCost.endsWith(".")) {
                realIngredientCost += "00";
            } else if (realIngredientCost.charAt(realIngredientCost.length() - 2) == '.') {
                realIngredientCost += "0";
            }

            //formats the ingredientCost
            ingredientCostPlaceholder = decimalFormat.format(refillsSupplied * drug.getPricePer30());

            //adds trailing zeroes to bring it to the hundreds place
            if (ingredientCostPlaceholder.endsWith(".")) {
                ingredientCostPlaceholder += "00";
            } else if (ingredientCostPlaceholder.charAt(ingredientCostPlaceholder.length() - 2) == '.') {
                ingredientCostPlaceholder += "0";
            }

            //formats the drugCost
            realDrugCost = decimalFormat.format((drug.getPricePer30() - drug.getCopay()) * refillsSupplied);

            //adds trailing zeroes to bring it to the hundreds place
            if (realDrugCost.endsWith(".")) {
                realDrugCost += "00";
            } else if (realDrugCost.charAt(realDrugCost.length() - 2) == '.') {
                realDrugCost += "0";
            }

            //formats the drugCostPlaceholder
            drugCostPlaceholder = decimalFormat.format((drug.getPricePer30() - drug.getCopay()) * refillsSupplied);

            //adds trailing zeroes to bring it to the hundreds place
            if (drugCostPlaceholder.endsWith(".")) {
                drugCostPlaceholder += "00";
            } else if (drugCostPlaceholder.charAt(drugCostPlaceholder.length() - 2) == '.') {
                drugCostPlaceholder += "0";
            }

            int incompleteRandomizer = rand(0, 3);
            if(incompleteRandomizer == 0)
            {
                //missing SIG and Drug Name
                System.out.println("0");
                drug.setInstructions("Please fill this out for me!");
                drug.setNamePlaceholder("I don't remember!");
            }
            else if(incompleteRandomizer == 1)
            {
                //missing SIG and NDC
                System.out.println("1");
                drug.setInstructions("Please fill this out for me!");
                drug.setNdc("");
            }
            else if(incompleteRandomizer == 2)
            {
                //missing NDC and Ingredient Cost
                System.out.println("2");
                drug.setNdcPlaceholder("");
                ingredientCostPlaceholder = "0.00";
            }
            else if(incompleteRandomizer == 3)
            {
                //missing Amt Due and Drug Name
                System.out.println("3");
                drug.setNamePlaceholder("What was it again?");
                drugCostPlaceholder = "0.00";
            }
            else if(incompleteRandomizer == 5)
            {
                //missing SIG, NDC, ingredient cost, and amt due
                System.out.println(4);
                drug.setNdcPlaceholder("");
                ingredientCostPlaceholder = "0.00";
                drugCostPlaceholder = "0.00";
            }
            else if(incompleteRandomizer == 6)
            {
                //missing Drug Name, SIG, Ingredient Cost, and Amt Due
                drug.setNamePlaceholder("");
                drug.setInstructions("I forgot how the patient should take this medication!");
                ingredientCostPlaceholder = "0.00";
                drugCostPlaceholder = "0.00";
            }
            //creates a 1st button showing the date preview
            dateTitleLabelButton = new Button(date);
            dateTitleLabelButton.setPrefWidth(157);
            dateTitleLabelButton.setStyle("-fx-border-color: black; -fx-border-width: 0 1 0 0; -fx-border-insets: 0 0 0 0;");
            dateTitleLabelButton.setFocusTraversable(false);
            dateTitleLabelButton.setFont(buttonFont);
            dateTitleLabelButton.setPrefHeight(35);
            //creates a 2nd button showing the RX# preview
            rxTitleLabelButton = new Button("" + getPrescriptionCount());
            rxTitleLabelButton.setPrefWidth(100);
            rxTitleLabelButton.setStyle("-fx-border-color: black; -fx-border-width: 0 1 0 1; -fx-border-insets: 0 0 0 0;");
            rxTitleLabelButton.setFocusTraversable(false);
            rxTitleLabelButton.setFont(buttonFont);
            rxTitleLabelButton.setPrefHeight(35);
            //creates a 3rd button showing the patient name preview
            patientTitleLabelButton = new Button(patient.toUpperCase());
            patientTitleLabelButton.setPrefWidth(265);
            patientTitleLabelButton.setStyle("-fx-border-color: black; -fx-border-width: 0 1 0 1; -fx-border-insets: 0 0 0 0;");
            patientTitleLabelButton.setFocusTraversable(false);
            patientTitleLabelButton.setFont(buttonFont);
            patientTitleLabelButton.setPrefHeight(35);
            //creates a 4th button showing the drug name preview
            drugTitleLabelButton = new Button(drug.getNamePlaceholder().toUpperCase());
            drugTitleLabelButton.setPrefWidth(295);
            drugTitleLabelButton.setStyle("-fx-border-color: black; -fx-border-width: 0 1 0 1; -fx-border-insets: 0 0 0 0;");
            drugTitleLabelButton.setFocusTraversable(false);
            drugTitleLabelButton.setFont(buttonFont);
            drugTitleLabelButton.setPrefHeight(35);
            //creates a 5th button showing the prescriber name preview
            prescriberTitleLabelButton = new Button(prescriber.toUpperCase());
            prescriberTitleLabelButton.setPrefWidth(265);
            prescriberTitleLabelButton.setStyle("-fx-border-color: black; -fx-border-width: 0 1 0 1; -fx-border-insets: 0 0 0 0;");
            prescriberTitleLabelButton.setFocusTraversable(false);
            prescriberTitleLabelButton.setFont(buttonFont);
            prescriberTitleLabelButton.setPrefHeight(35);
            //creates a 6th button showing the refills prescribed preview
            refillsTitleLabelButton = new Button("" + refillsSupplied);
            refillsTitleLabelButton.setPrefWidth(115);
            refillsTitleLabelButton.setStyle("-fx-border-color: black; -fx-border-width: 0 1 0 1; -fx-border-insets: 0 0 0 0;");
            refillsTitleLabelButton.setFocusTraversable(false);
            refillsTitleLabelButton.setFont(buttonFont);
            refillsTitleLabelButton.setPrefHeight(35);
            //creates a 7th button showing the phone number preview
            phoneTitleLabelButton = new Button(phoneNumber);
            phoneTitleLabelButton.setPrefWidth(230);
            phoneTitleLabelButton.setStyle("-fx-border-color: black; -fx-border-width: 0 1 0 1; -fx-border-insets: 0 0 0 0;");
            phoneTitleLabelButton.setFocusTraversable(false);
            phoneTitleLabelButton.setFont(buttonFont);
            phoneTitleLabelButton.setPrefHeight(35);
            //creates a buttonHandler event to account for an entire row worth of events
            EventHandler<ActionEvent> buttonHandler = event -> {
                if (!isRunning) {
                    isRunning = true;
                    //clickedButton references the clicked button in case it needs to be reference
                    Button clickedButton = (Button) event.getSource();

                    // Create the GridPane
                    GridPane editPane = new GridPane();
                    editPane.setPrefSize(920, 461);
                    editPane.setVgap(30);


                    //set an inner border to the editPane
                    BorderPane innerBorderPane = new BorderPane();
                    innerBorderPane.setCenter(editPane);
                    //set a border to the borderPane
                    innerBorderPane.setStyle("-fx-border-color: TRANSPARENT; -fx-border-width: 30px;");

                    //create a borderPane to hold the pharmacyPane
                    BorderPane outerBorderPane = new BorderPane();
                    outerBorderPane.setCenter(innerBorderPane);
                    outerBorderPane.setStyle("-fx-border-color: gray; -fx-border-width: 5px;");
                    Scene editScene = new Scene(outerBorderPane, 955, 496);
                    Stage editStage = new Stage();

                    editStage.setOnCloseRequest(closeEvent -> {
                        isRunning = false; // Change the variable when the "x" button is clicked
                    });

                    //adds spacers between each row
                    RowConstraints rowConstraints = new RowConstraints();
                    rowConstraints.setValignment(VPos.TOP);
                    editPane.getRowConstraints().add(rowConstraints);

                    //adds a label saying "RX#:" to row 0 column 0
                    Label rxLabel = new Label("RX#:");
                    rxLabel.setFont(titleFont);
                    editPane.add(rxLabel, 0, 0);
                    rxLabel.setPrefSize(100, 40);

                    //adds a textField to enter rxInfo to row 0 column 1
                    TextField rxTextField = new TextField();
                    rxTextField.setText("" + rxNum);
                    rxTextField.setFont(textFont);
                    editPane.add(rxTextField, 1, 0);
                    rxTextField.setPrefSize(250, 40);

                    //adds a spacer for visual appeal to row 0 column 2
                    Label spacer1 = new Label("");
                    editPane.add(spacer1, 2, 0);
                    spacer1.setPrefSize(100, 40);

                    //adds a label saying "Date:" to row 0 column 3
                    Label dateLabel = new Label("Date:");
                    dateLabel.setFont(titleFont);
                    editPane.add(dateLabel, 3, 0);

                    //adds a TextField to enter the date to row 0 column 4
                    TextField dateTextField = new TextField();
                    dateTextField.setText(date);
                    dateTextField.setFont(textFont);
                    editPane.add(dateTextField, 4, 0);
                    dateTextField.setPrefSize(250, 40);

                    //adds a label saying "Name:" to row 1 column 0
                    Label nameLabel = new Label("Name:");
                    nameLabel.setFont(titleFont);
                    editPane.add(nameLabel, 0, 1);

                    //adds a textField to enter the patient name to row 1 column 1
                    TextField nameTextField = new TextField();
                    nameTextField.setText(patient);
                    nameTextField.setFont(textFont);
                    editPane.add(nameTextField, 1, 1);
                    nameTextField.setPrefSize(250, 40);

                    //adds a spacer for visual appeal to row 1 column 2
                    Label spacer2 = new Label("");
                    editPane.add(spacer2, 2, 1);
                    spacer2.setPrefSize(100, 40);

                    //adds a label saying "Prescriber Name:" to row 1 column 3
                    Label prescriberLabel = new Label("Prescriber Name: ");
                    prescriberLabel.setFont(titleFont);
                    editPane.add(prescriberLabel, 3, 1);

                    //adds a textField to enter the prescriber name to row 1 column 4
                    TextField prescriberTextField = new TextField();
                    prescriberTextField.setText(prescriber);
                    prescriberTextField.setFont(textFont);
                    editPane.add(prescriberTextField, 4, 1);
                    prescriberTextField.setPrefSize(250, 40);

                    //adds a label saying "Drug Name" to row 2 column 0
                    Label drugNameLabel = new Label("Drug Name:");
                    drugNameLabel.setFont(titleFont);
                    editPane.add(drugNameLabel, 0, 2);

                    //adds a textField to enter the drug name to row 2 column 1
                    TextField drugNameTextField = new TextField();
                    drugNameTextField.setText(drug.getNamePlaceholder());
                    drugNameTextField.setFont(textFont);
                    editPane.add(drugNameTextField, 1, 2);
                    drugNameTextField.setPrefSize(250, 40);

                    //adds a spacer for visual appeal to row 2 column 2
                    Label spacer3 = new Label("");
                    editPane.add(spacer3, 2, 2);
                    spacer3.setPrefSize(100, 40);

                    //adds a label saying "QTY:" to row 2 column 3
                    Label qtyLabel = new Label("QTY:");
                    qtyLabel.setFont(titleFont);
                    editPane.add(qtyLabel, 3, 2);

                    //adds a textField to enter the quantity to row 2 column 4
                    TextField qtyTextField = new TextField();
                    qtyTextField.setText(refillsSupplied * 30 + "");
                    qtyTextField.setFont(textFont);
                    editPane.add(qtyTextField, 4, 2);
                    qtyTextField.setPrefSize(250, 40);

                    //adds a label saying "NDC:" to row 3 column 0
                    Label ndcLabel = new Label("NDC:");
                    ndcLabel.setFont(titleFont);
                    editPane.add(ndcLabel, 0, 3);

                    //adds a textField to enter the ndc number to row 3 column 1
                    TextField ndcTextField = new TextField();
                    ndcTextField.setText(drug.getNdcPlaceholder());
                    ndcTextField.setFont(textFont);
                    ndcTextField.setPrefSize(250, 40);
                    editPane.add(ndcTextField, 1, 3);

                    //adds a spacer for visual appeal to row 3 column 2
                    Label spacer4 = new Label("");
                    editPane.add(spacer4, 2, 3);
                    spacer4.setPrefSize(100, 40);

                    //adds a label saying "Refills Supplied" to row 3 column 3
                    Label refillsPresLabel = new Label("Refills Supplied:");
                    refillsPresLabel.setFont(titleFont);
                    editPane.add(refillsPresLabel, 3, 3);

                    //adds a textField to enter the refills supplied amount to row 3 column 4
                    TextField refillsPresTextField = new TextField();
                    refillsPresTextField.setText("" + refillsSupplied);
                    refillsPresTextField.setFont(textFont);
                    editPane.add(refillsPresTextField, 4, 3);
                    refillsPresTextField.setPrefSize(250, 40);

                    //adds a label saying "Phone #:" to row 4 column 0
                    Label phoneNumberLabel = new Label("Phone #:");
                    phoneNumberLabel.setFont(titleFont);
                    editPane.add(phoneNumberLabel, 0, 4);

                    //adds a textField letting you enter a phone number to row 4 column 1
                    TextField phoneNumberTextField = new TextField();
                    phoneNumberTextField.setText(phoneNumber);
                    phoneNumberTextField.setFont(textFont);
                    phoneNumberTextField.setPrefSize(250, 40);
                    editPane.add(phoneNumberTextField, 1, 4);

                    //adds a spacer for visual appeal to row 4 column 2
                    Label spacer5 = new Label("");
                    editPane.add(spacer5, 2, 4);
                    spacer5.setPrefSize(100, 40);

                    //adds a label saying "Ingr. Cost" to row 4 column 3
                    Label ingredientCostLabel = new Label("Ingr. Cost: ");
                    ingredientCostLabel.setFont(titleFont);
                    editPane.add(ingredientCostLabel, 3, 4);

                    //adds a textField to enter the ingredient cost to row 4 column 4
                    TextField ingredientCostTextField = new TextField();
                    ingredientCostTextField.setText("$" + ingredientCostPlaceholder);
                    ingredientCostTextField.setFont(textFont);
                    editPane.add(ingredientCostTextField, 4, 4);
                    ingredientCostTextField.setPrefSize(250, 40);

                    //adds a label saying "SIG" (Prescription Signature) to row 5 column 0
                    Label sigLabel = new Label("SIG:");
                    sigLabel.setFont(titleFont);
                    editPane.add(sigLabel, 0, 5);

                    //adds a textArea to enter the SIG to row 5 column 1, spanning 1 row horizontally and 3 rows vertically
                    TextArea sigTextArea = new TextArea();
                    sigTextArea.setText(drug.getInstructions());
                    sigTextArea.setFont(textFont);
                    sigTextArea.setPrefSize(250, 40);
                    editPane.add(sigTextArea, 1, 5, 1, 3);

                    //adds a label saying "Copay:" to row 5 column 3
                    Label copayLabel = new Label("Copay:");
                    copayLabel.setFont(titleFont);
                    editPane.add(copayLabel, 3, 5);

                    copayAmt = decimalFormat.format(drug.getCopay() * refillsSupplied);
                    //indicates that the prescription has been opened
                    hasOpened = true;

                    //adds trailing zeroes to bring it to the hundreds place
                    if (copayAmt.endsWith(".")) {
                        copayAmt += "00";
                    } else if (copayAmt.charAt(copayAmt.length() - 2) == '.') {
                        copayAmt += "0";
                    }

                    //adds a textField to enter copayAmount to row 5 column 4
                    TextField copayTextField = new TextField();
                    copayTextField.setText("$" + copayAmt);
                    copayTextField.setFont(textFont);
                    copayTextField.setPrefSize(250, 40);
                    editPane.add(copayTextField, 4, 5);

                    //adds a label saying "Amt Due:" to row 6 column 3
                    Label costLabel = new Label("Amt Due: ");
                    costLabel.setFont(titleFont);
                    editPane.add(costLabel, 3, 6);

                    //adds a textField to enter the Amt Due to row 6 column 4
                    TextField costTextField = new TextField();
                    costTextField.setText("$" + drugCostPlaceholder);
                    costTextField.setFont(textFont);
                    editPane.add(costTextField, 4, 6);
                    costTextField.setPrefSize(250, 40);

                    //adds a save button that lets you save your prescription to row 7 column 4
                    Button saveButton = new Button("SAVE");
                    saveButton.setFont(titleFont);
                    saveButton.setPrefSize(250, 40);
                    editPane.add(saveButton, 4, 7);
                    saveButton.setOnAction(saveEvent ->
                    {
                        try {
                            date = dateTextField.getText();
                            rxNum = Integer.parseInt(rxTextField.getText());
                            patient = nameTextField.getText();
                            prescriber = prescriberTextField.getText();
                            phoneNumber = phoneNumberTextField.getText();
                            drug.setNamePlaceholder(drugNameTextField.getText());
                            copayAmt = "" + Double.parseDouble(copayTextField.getText().substring(1));
                            drug.setNdcPlaceholder(ndcTextField.getText());

                            dateTitleLabelButton.setText(date);
                            rxTitleLabelButton.setText(rxNum + "");
                            patientTitleLabelButton.setText(patient.toUpperCase());
                            prescriberTitleLabelButton.setText(prescriber.toUpperCase());
                            phoneTitleLabelButton.setText(phoneNumber);
                            drugTitleLabelButton.setText(drug.getNamePlaceholder().toUpperCase());
                            refillsTitleLabelButton.setText(refillsSupplied + "");
                            System.out.println(drug.getNdc());
                            System.out.println(drug.getName());
                            System.out.println(realIngredientCost);
                            System.out.println(realDrugCost);
                            System.out.println(drug.getCopay());
                            System.out.println(Double.parseDouble(copayTextField.getText().substring(1)));
                            drugCostPlaceholder = costTextField.getText().substring(1);
                            ingredientCostPlaceholder = ingredientCostTextField.getText().substring(1);


                            //gives information on what you need to fill out (shows error message)
                            Stage dialogStage = new Stage();
                            dialogStage.initModality(Modality.APPLICATION_MODAL);
                            dialogStage.setTitle("IMPORTANT!!!");
                            dialogStage.setResizable(false);

                            Label messageLabel = new Label();
                            messageLabel.setFont(rewardFont);
                            Button closeButton = new Button("Close");
                            closeButton.setFont(buttonFont);
                            closeButton.setOnAction(eventClose -> dialogStage.close());

                            VBox dialogRoot = new VBox(10);
                            dialogRoot.setAlignment(Pos.CENTER);
                            dialogRoot.getChildren().addAll(messageLabel, closeButton);

                            String ndcText = ndcTextField.getText();
                            String drugNameText = drugNameTextField.getText();
                            String ingredientCostText = ingredientCostTextField.getText().substring(1);
                            String amtDueText = costTextField.getText().substring(1);

                            boolean isNdcCorrect = ndcText.equals(drug.getNdc());
                            boolean isDrugNameCorrect = drugNameText.equals(drug.getName());
                            boolean isIngredientCostCorrect = ingredientCostText.equals(realIngredientCost);
                            boolean isAmtDueCorrect = amtDueText.equals(realDrugCost);

                            if (isNdcCorrect && isDrugNameCorrect && isIngredientCostCorrect && isAmtDueCorrect) {
                                dialogStage.setTitle("CONGRATULATIONS!!!");
                                messageLabel.setText("Perfect Prescription! (GREAT JOB!)");
                            } else {
                                StringBuilder errorMessage = new StringBuilder("Incorrect Components:");

                                if (!isNdcCorrect) {
                                    errorMessage.append("\n- NDC is incorrect");
                                }
                                if (!isDrugNameCorrect) {
                                    errorMessage.append("\n- Drug Name is incorrect");
                                }
                                if (!isIngredientCostCorrect) {
                                    errorMessage.append("\n- Ingredient Cost is incorrect");
                                }
                                if (!isAmtDueCorrect) {
                                    errorMessage.append("\n- Amt Due is incorrect");
                                }

                                dialogStage.setTitle("Prescription Error");
                                messageLabel.setText(errorMessage.toString());
                            }

                            Scene dialogScene = new Scene(dialogRoot, 400, 200);
                            dialogStage.setScene(dialogScene);
                            dialogStage.showAndWait();

                        } catch (NumberFormatException e) {
                            //happens when a NumberFormatException is caught (Likely copay issue)
                            Stage dialogStage = new Stage();
                            dialogStage.initModality(Modality.APPLICATION_MODAL);
                            dialogStage.setTitle("Error!");
                            dialogStage.setResizable(false);

                            Label messageLabel = new Label();
                            messageLabel.setText("Copay Invalid -\nFollow this format: $#.##");
                            messageLabel.setFont(rewardFont);
                            Button closeButton = new Button("Close");
                            closeButton.setFont(buttonFont);
                            closeButton.setOnAction(eventClose -> dialogStage.close());

                            VBox dialogRoot = new VBox(10);
                            dialogRoot.setAlignment(Pos.CENTER);
                            dialogRoot.getChildren().addAll(messageLabel, closeButton);

                            dialogStage.initModality(Modality.APPLICATION_MODAL);
                            dialogStage.initOwner(editStage);
                            Scene dialogScene = new Scene(dialogRoot, 400, 200);
                            dialogStage.setScene(dialogScene);
                            dialogStage.showAndWait();

                        } catch (IndexOutOfBoundsException f) {
                            //happens when an IndexOutOfBoundsException is caught (Likely ingredient or amt due issue)
                            Stage dialogStage = new Stage();
                            dialogStage.initModality(Modality.APPLICATION_MODAL);
                            dialogStage.setTitle("Error!");
                            dialogStage.setResizable(false);

                            Label messageLabel = new Label();
                            messageLabel.setText("Ingredient Cost or Amt Due Invalid: \nFollow this format: $#.##");
                            messageLabel.setFont(rewardFont);
                            Button closeButton = new Button("Close");
                            closeButton.setFont(buttonFont);
                            closeButton.setOnAction(eventClose -> dialogStage.close());

                            VBox dialogRoot = new VBox(10);
                            dialogRoot.setAlignment(Pos.CENTER);
                            dialogRoot.getChildren().addAll(messageLabel, closeButton);

                            dialogStage.initModality(Modality.APPLICATION_MODAL);
                            dialogStage.initOwner(editStage);
                            Scene dialogScene = new Scene(dialogRoot, 400, 200);
                            dialogStage.setScene(dialogScene);
                            dialogStage.showAndWait();
                        }
                    });

                    editStage.setScene(editScene);
                    editStage.setTitle("EDITING " + patient.toUpperCase() + "'S PRESCRIPTION");
                    editStage.show();
                    editScene.getRoot().requestFocus();
                }
            };
            dateTitleLabelButton.setOnAction(buttonHandler);
            rxTitleLabelButton.setOnAction(buttonHandler);
            patientTitleLabelButton.setOnAction(buttonHandler);
            drugTitleLabelButton.setOnAction(buttonHandler);
            prescriberTitleLabelButton.setOnAction(buttonHandler);
            refillsTitleLabelButton.setOnAction(buttonHandler);
            phoneTitleLabelButton.setOnAction(buttonHandler);
        }

    }
    public Drug getDrug()
    {
        return drug;
    }
    public int getRxNum()
    {
        return rxNum;
    }
    public String getPatient()
    {
        return patient;
    }


    //rolls a random name
    public String getRandomName()
    {
        int firstNameRand = rand(0, patientFirstNameList.length - 1);
        String firstName = patientFirstNameList[firstNameRand];
        int lastNameRand = rand(0, patientLastNameList.length - 1);
        String lastName = patientLastNameList[lastNameRand];
        return firstName + " " + lastName;
    }
    //rolls a random drug
    public Drug getRandomDrug()
    {
        int drugRand = rand(0, drugList.length - 1);
        Drug selectedDrug = drugList[drugRand];
        Drug copiedDrug = new Drug(selectedDrug.getName(), selectedDrug.getBrandName(), selectedDrug.getNdc(),selectedDrug.getInstructions(), selectedDrug.getPricePer30(), selectedDrug.getGeneralUsage());
        return copiedDrug;
    }
    //rolls a random phone number
    public String getRandomPhoneNumber()
    {
        int areaCodeRand = rand(0, areaCodeList.length - 1);
        int areaCode = areaCodeList[areaCodeRand];
        int middleNumRand = rand(100, 999);
        int lastNumRand = rand(1000, 9999);
        return areaCode + "-" + middleNumRand + "-" + lastNumRand;
    }
    public Button getDateTitleLabelButton()
    {
        return dateTitleLabelButton;
    }
    public Button getRxTitleLabelButton()
    {
        return rxTitleLabelButton;
    }
    public Button getPatientTitleLabelButton()
    {
        return patientTitleLabelButton;
    }
    public Button getDrugTitleLabelButton()
    {
        return drugTitleLabelButton;
    }
    public Button getPrescriberTitleLabelButton()
    {
        return prescriberTitleLabelButton;
    }
    public Button getRefillsTitleLabelButton()
    {
        return refillsTitleLabelButton;
    }
    public Button getPhoneTitleLabelButton()
    {
        return phoneTitleLabelButton;
    }
    //rolls a random number from parameter 1 to parameter 2, inclusive
    public static int rand(int min, int max)
    {
        return (int)(Math.random()*(max-min+1)+min);
    }

    public static Drug getAtorvastatin() {
        return atorvastatin;
    }

    public static Drug getLoratadine() {
        return loratadine;
    }

    public static Drug getAmlopidine() {
        return amlopidine;
    }

    public static Drug getCarboxymethylcellulose() {
        return carboxymethylcellulose;
    }

    public static Drug getOxymetazoline() {
        return oxymetazoline;
    }
    public static Drug getAcetaminophen() {
        return acetaminophen;
    }
    public static Drug getMetoprolol() {
        return metoprolol;
    }
    public static Drug getLisinopril()
    {
        return lisinopril;
    }
    public static Drug getGabapentin()
    {
        return gabapentin;
    }
    public static Drug[] getDrugList()
    {
        return drugList;
    }
    public int compareToPatientName(Prescription other) {

        return patient.compareTo(other.getPatient());
    }
    public int compareToRXNum(Prescription other) {
        if (rxNum > other.getRxNum())
        {
            return 1;
        }
        return -1;
    }
}