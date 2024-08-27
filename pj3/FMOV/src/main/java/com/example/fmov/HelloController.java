package com.example.fmov;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.*;


public class HelloController {

    @FXML
    private ListView<Movie> ListOfMovies;

    @FXML
    private TextField movie;

    @FXML
    private TextField year;

    @FXML
    void initialize() {
        ListOfMovies.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    void addMovie(MouseEvent event) {
        String movieTitle = movie.getText().trim().toLowerCase();
        String movieYearTxt = year.getText().trim();
        String movieYear = "";

        if (movieTitle.isEmpty()) {
            Alert warning = new Alert(Alert.AlertType.WARNING, "Enter Movie Title.", ButtonType.CLOSE);
            warning.showAndWait();
        } else {
            if (!movieYearTxt.isEmpty()) {
                movieYear = String.valueOf(Integer.parseInt(movieYearTxt));
            }
            Movie movieIn = new Movie(movieTitle, movieYear);
            ListOfMovies.getItems().add(movieIn);

            movie.clear();
            year.clear();
        }
    }

    @FXML
    void loadMovies(MouseEvent event) {
        try {
            String filePath = "C:\\Users\\vikto\\OneDrive\\Documents\\movies.txt"; // Change the file path as desired

            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            ListOfMovies.getItems().clear(); // Clear existing items in the list

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] movieData = line.split(",");
                if (movieData.length >= 1) {
                    String movieTitle = movieData[0];
                    String movieYear = "";
                    if (movieData.length >= 2) {
                        movieYear = movieData[1];
                    }
                    Movie movie = new Movie(movieTitle, movieYear);
                    ListOfMovies.getItems().add(movie);
                }
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.err.println("Error loading movie list: " + e.getMessage());
        }
    }


    @FXML
    void removeMovie(MouseEvent event) {
        ObservableList<Movie> movieSelected = ListOfMovies.getSelectionModel().getSelectedItems();
        if (movieSelected.isEmpty()) {
            Alert warning2 = new Alert(Alert.AlertType.WARNING, "No movie selected.", ButtonType.CLOSE);
            warning2.showAndWait();
        } else {
            ListOfMovies.getItems().removeAll(movieSelected);
            ListOfMovies.getSelectionModel().clearSelection();
        }

    }

    @FXML
    void saveMovies(MouseEvent event) {
        try {
            String filePath = "C:\\Users\\vikto\\OneDrive\\Documents\\movies.txt"; // Change the file path as desired

            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            ObservableList<Movie> movies = ListOfMovies.getItems();
            for (Movie movie : movies) {
                bufferedWriter.write(movie.getTitle() + "," + movie.getYear());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Movie list saved successfully!", ButtonType.OK);
            alert.showAndWait();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error saving movie list.", ButtonType.OK);
            alert.showAndWait();
        }

    }
}
