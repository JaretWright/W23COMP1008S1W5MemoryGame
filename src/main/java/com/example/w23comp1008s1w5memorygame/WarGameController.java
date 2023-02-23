package com.example.w23comp1008s1w5memorygame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WarGameController implements Initializable {

    @FXML
    private Label p1CardCountLabel;

    @FXML
    private ImageView p1ImageView;

    @FXML
    private Label p2CardCountLabel;

    @FXML
    private Label winnerLabel;

    @FXML
    private ImageView p2ImageView;

    @FXML
    private Button playButton;

    private ArrayList<Card> p1Hand, p2Hand, cardPile;

    @FXML
    private void playHand() {
        p1ImageView.setVisible(true);
        p2ImageView.setVisible(true);

        //Does P1 have cards?
        if (p1Hand.size()==0)
            declareWinner("Player 2");
        //Does P2 have cards?
        else if (p2Hand.size()==0)
            declareWinner("Player 1");

        else {
            //P1 removes top card and adds to pile
            Card p1Card = p1Hand.remove(0);
            cardPile.add(p1Card);
            p1ImageView.setImage(p1Card.getImage());

            //P2 removes top card and adds to the pile
            Card p2Card = p2Hand.remove(0);
            cardPile.add(p2Card);
            p2ImageView.setImage(p2Card.getImage());

            //P1 card value = P2 card value?
            if (p1Card.getCardValue() == p2Card.getCardValue())
                playWarHand();
            else if (p1Card.getCardValue()>p2Card.getCardValue())//player 1 wins the hand
            {
                p1Hand.addAll(cardPile);
                //Clear the center Pile
                cardPile.clear();
            }
            else //player 2 wins the hand
            {
                p2Hand.addAll(cardPile);
                //Clear the center Pile
                cardPile.clear();
            }
            updateLabels();
        }
    }

    @FXML
    private void newGame()
    {
        playAgainButton.setDisable(true);
        playButton.setDisable(false);
        winnerLabel.setVisible(false);
        p1ImageView.setVisible(false);
        p2ImageView.setVisible(false);

        //Create variables for p1, p2 & card pile
        p1Hand = new ArrayList<>();
        p2Hand = new ArrayList<>();
        cardPile = new ArrayList<>();

        //create a deck of cards and shuffle the deck
        DeckOfCards deck = new DeckOfCards();
        deck.shuffle();

        //this was added to reduce the deck size and make testing easier
//        for (int i=1; i<=46; i++)
//            deck.dealTopCard();

        //Are all Cards Dealt?
        while (deck.getNumOfCardsInDeck()>=2)
        {
            p1Hand.add(deck.dealTopCard());
            p2Hand.add(deck.dealTopCard());
        }

        //designed an experiment to test what is going on with the "war hand"
//        Card ace = new Card("ace","spades");
//        p1Hand.add(0,ace);
//        p2Hand.add(0,ace);

        updateLabels();
    }

    @FXML
    private Button playAgainButton;

    /**
     * This method updates the labels to show the status of the game
     */
    private void updateLabels()
    {
        if (p1Hand.size()==1)
            p1CardCountLabel.setText(p1Hand.size() + " Card");
        else
            p1CardCountLabel.setText(p1Hand.size() + " Cards");

        p2CardCountLabel.setText(String.format("%d %s",p2Hand.size(),
                                                    p2Hand.size()==1?"Card":"Cards"));
    }

    /**
     * This method handles the "war hand" situation where both players had a card of the same value
     * @param
     */
    private void playWarHand()
    {
        //Does P1 have 4 or more cards?
        if (p1Hand.size()<4)
            declareWinner("Player 2");
        //Does P1 have 4 or more cards?
        else if (p2Hand.size()<4)
            declareWinner("Player 1");
        else //both players have enough cards to play a war hand
        {
            //P1 & P2 add 3 cards each to the center pile
            for (int i=1; i<=3; i++)
            {
                cardPile.add(p1Hand.remove(0));
                cardPile.add(p2Hand.remove(0));
            }
        }
    }

    private void declareWinner(String winnerName)
    {
        playButton.setDisable(true);
        playAgainButton.setDisable(false);

        //Make p1 card image invisible
        p1ImageView.setVisible(false);

        //Make p2 card image invisible
        p2ImageView.setVisible(false);

        //Make winner label visible and display winner name
        winnerLabel.setVisible(true);
        winnerLabel.setText(winnerName + " wins!!");

        updateLabels();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newGame();
    }



}
