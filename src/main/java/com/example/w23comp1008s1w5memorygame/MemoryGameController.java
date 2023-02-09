package com.example.w23comp1008s1w5memorygame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class MemoryGameController implements Initializable {

    @FXML
    private Label correctLabel;

    @FXML
    private FlowPane flowPane;

    @FXML
    private Label guessesLabel;

    private int guesses, correct;
    private ArrayList<MemoryCard> cardsInGame;

    @FXML
    void playAgain() {
        cardsInGame = new ArrayList<>();

        //create a Deck of Cards and shuffle them
        DeckOfCards deck = new DeckOfCards();
        deck.shuffle();

        //take the first 5 cards (for a game with 10 total cards) and duplicate each one so there can be a match
        for (int i=0; i<flowPane.getChildren().size()/2;i++)
        {
            Card cardDealt = deck.dealTopCard();
            cardsInGame.add(new MemoryCard(cardDealt.getFaceName(),cardDealt.getSuit()));
            cardsInGame.add(new MemoryCard(cardDealt.getFaceName(),cardDealt.getSuit()));
        }
        Collections.shuffle(cardsInGame);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        flipAllCards();
        playAgain();
        //add a click listener to each ImageView object
        for (int i=0; i<flowPane.getChildren().size();i++)
        {
            ImageView imageView = (ImageView) flowPane.getChildren().get(i);
            imageView.setUserData(i);  //give each ImageView a # we can reference

            //add a click listener to the ImageView
            imageView.setOnMouseClicked(event ->{
                flipCard((int) imageView.getUserData());
            });
        }
    }

    private void flipCard(int indexOfCard)
    {
        ImageView imageView = (ImageView) flowPane.getChildren().get(indexOfCard);
        imageView.setImage(cardsInGame.get(indexOfCard).getImage());
    }

    private void flipAllCards()
    {
        for (int i=0; i<flowPane.getChildren().size(); i++)
        {
            ImageView imageView = (ImageView) flowPane.getChildren().get(i);
            imageView.setImage(new Image(Card.class.getResourceAsStream("images/back_of_card.png")));
        }
    }
}
