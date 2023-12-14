package com.example.electronicrx_program;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.stage.StageStyle;

public class ElectronicRX extends Application {
    private static int prescriptionCount = 0;
    private static boolean drugIndexOpen = false;
    private static ArrayList<GridPane> prescriptionContainerList = new ArrayList<GridPane>();

    //creates an ArrayList to hold the Prescription prescriptions inside the prescriptionBox
    private static ArrayList<Prescription> prescriptionArray = new ArrayList<Prescription>();

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        //fulfills all requirements excluding ArrayList merge sort (merge sort ArrayList used in patient name, rx#, drug name, and ndc# sort buttons)
        String[] medicineArr = {"loratadine", "ibuprofen", "acetaminophen", "metoprolol"};
        System.out.println(getClaritinIndexArr(medicineArr, 0));
        int[] numArr = {2, 5, 1, 53, 123, -421, 64, 12};
        System.out.println(iterativeBinarySearch(numArr, 53));
        for(int i = 0; i < numArr.length; i++)
        {
            System.out.print(numArr[i] + ", ");
        }
        mergeSort(numArr);
        System.out.println("");
        for(int i = 0; i < numArr.length; i++)
        {
            System.out.print(numArr[i] + ", ");
        }
        traverseStringRecursively("Pharmacies are cool");
        ArrayList<String> medicineList = new ArrayList<String>();
        medicineList.add("ibuprofen");
        medicineList.add("loratadine");
        medicineList.add("acetaminophen");
        System.out.println(getClaritinIndexArrayList(medicineList, 0));


        //creates a Font object with the bold "Trebuchet MS" font, with size adjusted for titles
        Font titleFont = Font.font("Trebuchet MS", FontWeight.BOLD, 18);
        //creates a Font object with the bold "Trebuchet MS" font, with size adjusted for buttons
        Font buttonFont = Font.font("Trebuchet MS", FontWeight.BOLD, 15);


        //creates 1st titlelabel saying "Date-Time"
        Label dateTitleLabel = new Label("Date-Time");
        dateTitleLabel.setAlignment(Pos.CENTER);
        dateTitleLabel.setPrefSize(160, 30);
        dateTitleLabel.setFont(titleFont);
        dateTitleLabel.setStyle("-fx-border-color: black; -fx-border-width: 2 1 2 2; -fx-border-insets: 0 0 0 0;");

        //creates 2nd titlelabel saying "RX#"
        Label rxTitleLabel = new Label("RX#");
        rxTitleLabel.setAlignment(Pos.CENTER);
        rxTitleLabel.setPrefSize(100, 30);
        rxTitleLabel.setFont(titleFont);
        rxTitleLabel.setStyle("-fx-border-color: black; -fx-border-width: 2 1 2 1; -fx-border-insets: 0 0 0 0;");

        //creates 3rd titleLabel saying "Patient Name"
        Label patientTitleLabel = new Label("Patient Name");
        patientTitleLabel.setAlignment(Pos.CENTER);
        patientTitleLabel.setPrefSize(265, 30);
        patientTitleLabel.setFont(titleFont);
        patientTitleLabel.setStyle("-fx-border-color: black; -fx-border-width: 2 1 2 1; -fx-border-insets: 0 0 0 0;");

        //creates 4th titleLabel saying "Drug Name"
        Label drugTitleLabel = new Label("Drug Name");
        drugTitleLabel.setAlignment(Pos.CENTER);
        drugTitleLabel.setPrefSize(295, 30);
        drugTitleLabel.setFont(titleFont);
        drugTitleLabel.setStyle("-fx-border-color: black; -fx-border-width: 2 1 2 1; -fx-border-insets: 0 0 0 0;");

        //creates 5th titleLabel saying "Prescriber"
        Label prescriberTitleLabel = new Label("Prescriber");
        prescriberTitleLabel.setAlignment(Pos.CENTER);
        prescriberTitleLabel.setPrefSize(265, 30);
        prescriberTitleLabel.setFont(titleFont);
        prescriberTitleLabel.setStyle("-fx-border-color: black; -fx-border-width: 2 1 2 1; -fx-border-insets: 0 0 0 0;");

        //creates 6th titleLabel saying "Ref Sup" (Refills supplied)
        Label refillsTitleLabel = new Label("Refills Sup");
        refillsTitleLabel.setAlignment(Pos.CENTER);
        refillsTitleLabel.setPrefSize(115, 30);
        refillsTitleLabel.setFont(titleFont);
        refillsTitleLabel.setStyle("-fx-border-color: black; -fx-border-width: 2 1 2 1; -fx-border-insets: 0 0 0 0;");

        //creates 7th titleLabel saying "Phone#"
        Label phoneTitleLabel = new Label("Phone#");
        phoneTitleLabel.setAlignment(Pos.CENTER);
        phoneTitleLabel.setPrefSize(230, 30);
        phoneTitleLabel.setFont(titleFont);
        phoneTitleLabel.setStyle("-fx-border-color: black; -fx-border-width: 2 2 2 1; -fx-border-insets: 0 0 0 0;");

        //creates a Label telling you you RXs done (corresponds with dateTitleLabel)
        Label numRxLabel = new Label("RXs:");
        numRxLabel.setPrefSize(100, 30);
        numRxLabel.setFont(titleFont);

        //creates a Label saying "Sorting:" (18 spaces)
        Label sortTitleLabel = new Label("                             Sorting:");
        sortTitleLabel.setAlignment(Pos.CENTER);
        sortTitleLabel.setFont(titleFont);

        //creates a Label saying "RXs:" (5 spaces)
        Label rxTitleLabel2 = new Label("          RXs:");
        rxTitleLabel2.setAlignment(Pos.CENTER);
        rxTitleLabel2.setFont(titleFont);

        //Creates a Label telling the amount of current RXs
        Label rxCountLabel = new Label("" + prescriptionCount);
        rxCountLabel.setAlignment(Pos.CENTER);
        rxCountLabel.setFont(titleFont);

        //creates a Button that sorts the RX List by Recent Date
        Button patientSortAscendingButton = new Button("Sort Patient (A-Z)");
        patientSortAscendingButton.setPrefSize(215, 30);
        patientSortAscendingButton.setFont(buttonFont);

        //creates a Button that sorts the RX List by Oldest Date
        Button patientSortDescendingButton = new Button("Sort Patient (Z-A)");
        patientSortDescendingButton.setPrefSize(215, 30);
        patientSortDescendingButton.setFont(buttonFont);

        //creates a Button that sorts the RX List by RX Number
        Button rxSortButton = new Button("Sort RX# (Ascending)");
        rxSortButton.setPrefSize(215, 30);
        rxSortButton.setFont(buttonFont);

        //creates a Button that sorts the RX List alphabetically by Patient Names
        Button rxSortDescendingButton = new Button("Sort RX# (Descending)");
        rxSortDescendingButton.setPrefSize(215, 30);
        rxSortDescendingButton.setFont(buttonFont);

        //creates a VBox to hold the prescription components inside the prescriptionPane
        GridPane prescriptionBox = new GridPane();

        //creates a ScrollPane holding the prescriptionBox and disables horizontal scrollbar
        ScrollPane prescriptionPane = new ScrollPane(prescriptionBox);
        prescriptionPane.setPrefSize(1430, 550);
        prescriptionPane.setStyle("-fx-border-color: black; -fx-border-width: 0 2 2 2; -fx-border-insets: 0 0 0 0; -fx-padding: 0px 0px 0px 0px;");
        prescriptionPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        //creates a Button that opens drugIndex with all information present
        Button drugIndexButton = new Button("Open Drug Index");
        drugIndexButton.setPrefSize(215, 30);
        drugIndexButton.setFont(buttonFont);
        drugIndexButton.setOnAction(event ->
        {
            if(!drugIndexOpen) {
                DrugIndex drugIndex = new DrugIndex();
            }
           drugIndexOpen = true;
        });
        //creates a Button that adds a new RX with all information present
        Button rxButton = new Button("Get Complete RX");
        rxButton.setPrefSize(215, 30);
        rxButton.setFont(buttonFont);
        rxButton.setOnAction(event ->
        {
            //increments prescriptionCount by 1
            prescriptionCount++;
            rxCountLabel.setText(prescriptionCount + "");
            //creates a new Prescription
            Prescription prescription = new Prescription(true);
            //creates a new prescription GridPane to hold all the buttons in each row
            GridPane prescriptionContainer = new GridPane();
            prescriptionContainer.setPrefHeight(37);
            //adds all new buttons into the prescription GridPane
            prescriptionContainer.add(prescription.getDateTitleLabelButton(), 0, 0);
            prescriptionContainer.add(prescription.getRxTitleLabelButton(), 1,0);
            prescriptionContainer.add(prescription.getPatientTitleLabelButton(), 2, 0);
            prescriptionContainer.add(prescription.getDrugTitleLabelButton(), 3, 0);
            prescriptionContainer.add(prescription.getPrescriberTitleLabelButton(), 4, 0);
            prescriptionContainer.add(prescription.getRefillsTitleLabelButton(), 5, 0);
            prescriptionContainer.add(prescription.getPhoneTitleLabelButton(), 6, 0);
            prescriptionContainer.setStyle("-fx-border-color: black; -fx-border-width: 0 0 2 0; -fx-border-insets: 0 0 0 0; -fx-padding: 0px;");
            prescriptionContainerList.add(prescriptionContainer);
            prescriptionArray.add(prescription);
            prescriptionBox.add(prescriptionContainer, 0, prescriptionCount);
        });

        //creates a Button that adds a new RX with missing information when clicked
        Button incompleteRxButton = new Button("Get Incomplete RX");
        incompleteRxButton.setPrefSize(215, 30);
        incompleteRxButton.setFont(buttonFont);
        incompleteRxButton.setOnAction(event ->
        {
            //increments prescriptionCount by 1
            prescriptionCount++;
            rxCountLabel.setText(prescriptionCount + "");
            //creates a new Prescription
            Prescription prescription = new Prescription(false);
            //creates a new prescription GridPane to hold all the buttons in each row
            GridPane prescriptionContainer = new GridPane();
            prescriptionContainer.setPrefHeight(37);
            //adds all new buttons into the prescription GridPane
            prescriptionContainer.add(prescription.getDateTitleLabelButton(), 0, 0);
            prescriptionContainer.add(prescription.getRxTitleLabelButton(), 1,0);
            prescriptionContainer.add(prescription.getPatientTitleLabelButton(), 2, 0);
            prescriptionContainer.add(prescription.getDrugTitleLabelButton(), 3, 0);
            prescriptionContainer.add(prescription.getPrescriberTitleLabelButton(), 4, 0);
            prescriptionContainer.add(prescription.getRefillsTitleLabelButton(), 5, 0);
            prescriptionContainer.add(prescription.getPhoneTitleLabelButton(), 6, 0);
            prescriptionContainer.setStyle("-fx-border-color: black; -fx-border-width: 0 0 2 0; -fx-border-insets: 0 0 0 0; -fx-padding: 0px;");
            prescriptionContainerList.add(prescriptionContainer);
            prescriptionArray.add(prescription);
            prescriptionBox.add(prescriptionContainer, 0, prescriptionCount);
        });

        rxSortButton.setOnAction(event ->
        {
            prescriptionBox.getChildren().removeAll(prescriptionContainerList);
            prescriptionContainerList.clear();
            mergeSortPrescriptionCount(prescriptionArray);
            for(int i = 0; i < prescriptionArray.size(); i++)
            {
                GridPane prescriptionContainer = new GridPane();
                prescriptionContainer.setPrefHeight(37);
                final int indexKeeper = i;
                prescriptionContainer.add(prescriptionArray.get(indexKeeper).getDateTitleLabelButton(), 0, 0);
                prescriptionContainer.add(prescriptionArray.get(indexKeeper).getRxTitleLabelButton(), 1,0);
                prescriptionContainer.add(prescriptionArray.get(indexKeeper).getPatientTitleLabelButton(), 2, 0);
                prescriptionContainer.add(prescriptionArray.get(indexKeeper).getDrugTitleLabelButton(), 3, 0);
                prescriptionContainer.add(prescriptionArray.get(indexKeeper).getPrescriberTitleLabelButton(), 4, 0);
                prescriptionContainer.add(prescriptionArray.get(indexKeeper).getRefillsTitleLabelButton(), 5, 0);
                prescriptionContainer.add(prescriptionArray.get(indexKeeper).getPhoneTitleLabelButton(), 6, 0);
                prescriptionBox.add(prescriptionContainer, 0, indexKeeper);
                prescriptionContainerList.add(prescriptionContainer);
                prescriptionContainer.setStyle("-fx-border-color: black; -fx-border-width: 0 0 2 0; -fx-border-insets: 0 0 0 0; -fx-padding: 0px;");
            }
        });

        rxSortDescendingButton.setOnAction(event ->
        {
            prescriptionBox.getChildren().removeAll(prescriptionContainerList);
            prescriptionContainerList.clear();
            mergeSortPrescriptionCount(prescriptionArray);
            reverseArray(prescriptionArray);
            for(int i = 0; i < prescriptionArray.size(); i++)
            {
                GridPane prescriptionContainer = new GridPane();
                prescriptionContainer.setPrefHeight(37);
                final int indexKeeper = i;
                prescriptionContainer.add(prescriptionArray.get(indexKeeper).getDateTitleLabelButton(), 0, 0);
                prescriptionContainer.add(prescriptionArray.get(indexKeeper).getRxTitleLabelButton(), 1,0);
                prescriptionContainer.add(prescriptionArray.get(indexKeeper).getPatientTitleLabelButton(), 2, 0);
                prescriptionContainer.add(prescriptionArray.get(indexKeeper).getDrugTitleLabelButton(), 3, 0);
                prescriptionContainer.add(prescriptionArray.get(indexKeeper).getPrescriberTitleLabelButton(), 4, 0);
                prescriptionContainer.add(prescriptionArray.get(indexKeeper).getRefillsTitleLabelButton(), 5, 0);
                prescriptionContainer.add(prescriptionArray.get(indexKeeper).getPhoneTitleLabelButton(), 6, 0);
                prescriptionBox.add(prescriptionContainer, 0, indexKeeper);
                prescriptionContainerList.add(prescriptionContainer);
                prescriptionContainer.setStyle("-fx-border-color: black; -fx-border-width: 0 0 2 0; -fx-border-insets: 0 0 0 0; -fx-padding: 0px;");
            }
        });

        patientSortAscendingButton.setOnAction(event ->
        {
            prescriptionBox.getChildren().removeAll(prescriptionContainerList);
            prescriptionContainerList.clear();
            mergeSortPatientName(prescriptionArray);
            for(int i = 0; i < prescriptionArray.size(); i++)
            {
                GridPane prescriptionContainer = new GridPane();
                prescriptionContainer.setPrefHeight(37);
                final int indexKeeper = i;
                prescriptionContainer.add(prescriptionArray.get(indexKeeper).getDateTitleLabelButton(), 0, 0);
                prescriptionContainer.add(prescriptionArray.get(indexKeeper).getRxTitleLabelButton(), 1,0);
                prescriptionContainer.add(prescriptionArray.get(indexKeeper).getPatientTitleLabelButton(), 2, 0);
                prescriptionContainer.add(prescriptionArray.get(indexKeeper).getDrugTitleLabelButton(), 3, 0);
                prescriptionContainer.add(prescriptionArray.get(indexKeeper).getPrescriberTitleLabelButton(), 4, 0);
                prescriptionContainer.add(prescriptionArray.get(indexKeeper).getRefillsTitleLabelButton(), 5, 0);
                prescriptionContainer.add(prescriptionArray.get(indexKeeper).getPhoneTitleLabelButton(), 6, 0);
                prescriptionBox.add(prescriptionContainer, 0, indexKeeper);
                prescriptionContainerList.add(prescriptionContainer);
                prescriptionContainer.setStyle("-fx-border-color: black; -fx-border-width: 0 0 2 0; -fx-border-insets: 0 0 0 0; -fx-padding: 0px;");
            }
        });

        patientSortDescendingButton.setOnAction(event ->
        {
            prescriptionBox.getChildren().removeAll(prescriptionContainerList);
            prescriptionContainerList.clear();
            mergeSortPatientName(prescriptionArray);
            reverseArray(prescriptionArray);
            for(int i = 0; i < prescriptionArray.size(); i++)
            {
                GridPane prescriptionContainer = new GridPane();
                prescriptionContainer.setPrefHeight(37);
                final int indexKeeper = i;
                prescriptionContainer.add(prescriptionArray.get(indexKeeper).getDateTitleLabelButton(), 0, 0);
                prescriptionContainer.add(prescriptionArray.get(indexKeeper).getRxTitleLabelButton(), 1,0);
                prescriptionContainer.add(prescriptionArray.get(indexKeeper).getPatientTitleLabelButton(), 2, 0);
                prescriptionContainer.add(prescriptionArray.get(indexKeeper).getDrugTitleLabelButton(), 3, 0);
                prescriptionContainer.add(prescriptionArray.get(indexKeeper).getPrescriberTitleLabelButton(), 4, 0);
                prescriptionContainer.add(prescriptionArray.get(indexKeeper).getRefillsTitleLabelButton(), 5, 0);
                prescriptionContainer.add(prescriptionArray.get(indexKeeper).getPhoneTitleLabelButton(), 6, 0);
                prescriptionBox.add(prescriptionContainer, 0, indexKeeper);
                prescriptionContainerList.add(prescriptionContainer);
                prescriptionContainer.setStyle("-fx-border-color: black; -fx-border-width: 0 0 2 0; -fx-border-insets: 0 0 0 0; -fx-padding: 0px;");
            }
        });

        Label bottomSpacer = new Label("");
        bottomSpacer.setPrefHeight(25);



        //creates a pharmacyPane to hold all the components
        GridPane pharmacyPane = new GridPane();
        //creates 6 spacers, all with heights of 100 starting from row 2
        for (int i = 1; i <= 6; i++) {
            Label spacer = new Label();
            spacer.setPrefWidth(0);
            spacer.setPrefHeight(100);
            pharmacyPane.add(spacer, 0, i+1);
        }
        pharmacyPane.add(dateTitleLabel, 0, 1);
        pharmacyPane.add(rxTitleLabel, 1, 1);
        pharmacyPane.add(patientTitleLabel, 2, 1);
        pharmacyPane.add(drugTitleLabel, 3, 1);
        pharmacyPane.add(prescriberTitleLabel, 4, 1);
        pharmacyPane.add(refillsTitleLabel, 5, 1);
        pharmacyPane.add(phoneTitleLabel, 6, 1);
        pharmacyPane.add(numRxLabel, 0, 7);
        pharmacyPane.add(rxCountLabel, 1, 7);
        pharmacyPane.add(drugIndexButton, 0, 8, 2, 1);
        pharmacyPane.add(sortTitleLabel, 2, 7);
        pharmacyPane.add(patientSortAscendingButton, 3, 7);
        pharmacyPane.add(patientSortDescendingButton, 3, 8);
        pharmacyPane.add(rxSortButton, 4, 7);
        pharmacyPane.add(rxSortDescendingButton, 4, 8);
        pharmacyPane.add(rxTitleLabel2, 5, 7);
        pharmacyPane.add(rxButton, 6, 7);
        pharmacyPane.add(incompleteRxButton, 6, 8);
        pharmacyPane.add(prescriptionPane, 0, 2, 7,5 );
        pharmacyPane.add(bottomSpacer, 0, 9, GridPane.REMAINING, 1);


        BorderPane innerBorderPane = new BorderPane();
        innerBorderPane.setCenter(pharmacyPane);
        //set a border to the borderPane
        innerBorderPane.setStyle("-fx-border-color: TRANSPARENT; -fx-border-width: 10px;");

        //create a borderPane to hold the pharmacyPane
        BorderPane outerBorderPane = new BorderPane();
        outerBorderPane.setCenter(innerBorderPane);
        outerBorderPane.setStyle("-fx-border-color: gray; -fx-border-width: 5px;");





        //creates the scene and adds the pharmacyPane to it (1460 + 4 to account for the additional border pixels)
        Scene scene = new Scene(outerBorderPane, 1464, 750);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Electronic RX Simulation Software");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    public static int getPrescriptionCount()
    {
        return prescriptionCount;
    }
    public static ArrayList<Prescription> getPrescriptionArray()
    {
        return prescriptionArray;
    }
    public static boolean isDrugIndexOpen()
    {
        return drugIndexOpen;
    }
    public static void setIsDrugIndexOpen(boolean x)
    {
        drugIndexOpen = x;
    }


    //RXCount merge sort algorithm for ArrayList
    public static void mergeSortPrescriptionCount(ArrayList<Prescription> list) {
        Prescription[] temp = new Prescription[list.size()];
        mergeSortPrescriptionHelperCount(list, 0, list.size() - 1, temp);
        for (Prescription prescription : list) {
            System.out.print(prescription + " ");
        }
        System.out.println();
    }

    private static void mergeSortPrescriptionHelperCount(ArrayList<Prescription> list, int from, int to, Prescription[] temp) {
        if (from < to) {
            int middle = (from + to) / 2;
            mergeSortPrescriptionHelperCount(list, from, middle, temp);
            mergeSortPrescriptionHelperCount(list, middle + 1, to, temp);
            mergePrescriptionCount(list, from, middle, to, temp);
        }
    }

    private static void mergePrescriptionCount(ArrayList<Prescription> list, int from, int mid, int to, Prescription[] temp) {
        int i = from;
        int j = mid + 1;
        int k = from;

        while (i <= mid && j <= to) {
            if (list.get(i).compareToRXNum(list.get(j)) < 0) {
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
    //reverses an arrayList
    public static ArrayList<Prescription> reverseArray(ArrayList<Prescription> arr) {
        ArrayList<Prescription> tempArray = new ArrayList<Prescription>(arr.size());
        for (int i = arr.size() - 1; i >= 0; i--) {
            tempArray.add(arr.get(i));
        }
        arr.clear();
        arr.addAll(tempArray);
        return arr;
    }







    //RXCount merge sort algorithm for ArrayList
    public static void mergeSortPatientName(ArrayList<Prescription> list) {
        Prescription[] temp = new Prescription[list.size()];
        mergeSortPatientNameHelper(list, 0, list.size() - 1, temp);
        for (Prescription prescription : list) {
            System.out.print(prescription + " ");
        }
        System.out.println();
    }

    private static void mergeSortPatientNameHelper(ArrayList<Prescription> list, int from, int to, Prescription[] temp) {
        if (from < to) {
            int middle = (from + to) / 2;
            mergeSortPatientNameHelper(list, from, middle, temp);
            mergeSortPatientNameHelper(list, middle + 1, to, temp);
            mergePatientNameCount(list, from, middle, to, temp);
        }
    }

    private static void mergePatientNameCount(ArrayList<Prescription> list, int from, int mid, int to, Prescription[] temp) {
        int i = from;
        int j = mid + 1;
        int k = from;

        while (i <= mid && j <= to) {
            if (list.get(i).compareToPatientName(list.get(j)) < 0) {
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

    //returns the sum of all numbers in an int array, starting from index x
    public static int getClaritinIndexArr(String[] arr, int x) {
        if (arr[x].equals("claritin") || arr[x].equals("Claritin")) {
            return x;
        }
        if (arr[x].equals("loratadine") || arr[x].equals("Loratadine")) {
            return x;
        }
        if (arr.length == x)
        {
            return -1;
        }
        return getClaritinIndexArr(arr, x+1);
    }
    //returns the index of claritin or loratadine in the array of Strings, starting from index x
    public static int getClaritinIndexArrayList(ArrayList<String> arr, int x) {
        if (arr.get(x).equals("claritin") || arr.get(x).equals("Claritin")) {
            return x;
        }
        if (arr.get(x).equals("loratadine") || arr.get(x).equals("Loratadine")) {
            return x;
        }
        if (arr.size() == x)
        {
            return -1;
        }
        return getClaritinIndexArrayList(arr, x+1);
    }
    //searches an array for a target iteratively
    public static int iterativeBinarySearch(int[] arr, int target)
    {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right)
        {
            int middle = (left + right) / 2;
            if (target < arr[middle])
            {
                right = middle - 1;
            }
            else if (target > arr[middle])
            {
                left = middle + 1;
            }
            else return middle;
        }
        return -1;
    }
    //traverses the string recursively
    public static void traverseStringRecursively(String str) {
        //base case if the string is empty
        if (str.isEmpty()) {
            return;
        }
        //print the letter
        System.out.print(str.substring(0, 1));

        //recursive call
        traverseStringRecursively(str.substring(1));
    }

    //merge sorts an array
    public static void mergeSort(int[] arr)
    {
        int[] temp = new int[arr.length];
        mergeSortHelper(arr, 0, arr.length - 1, temp);
        for(int i : arr) System.out.print(i + " ");
        System.out.println();
    }

    private static void mergeSortHelper(int[] arr, int from, int to, int[] temp)
    {
        if (from < to)
        {
            int middle = (from + to) / 2;
            mergeSortHelper(arr, from, middle, temp);
            mergeSortHelper(arr, middle + 1, to, temp);
            merge(arr, from, middle, to, temp);
        }
    }

    private static void merge(int[] arr, int from, int mid, int to, int[] temp)
    {
        int i = from;
        int j = mid + 1;
        int k = from;

        while (i <= mid && j <= to)
        {
            if (arr[i] < arr[j])
            {
                temp[k] = arr[i];
                i++;
            }
            else
            {
                temp[k] = arr[j];
                j++;
            }
            k++;
        }

        while (i <= mid)
        {
            temp[k] = arr[i];
            i++;
            k++;
        }

        while (j <= to)
        {
            temp[k] = arr[j];
            j++;
            k++;
        }

        for (k = from; k <= to; k++)
        {
            arr[k] = temp[k];
        }
    }

}