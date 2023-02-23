package com.example.w23comp1008s1w5memorygame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private ImageView p2ImageView;

    private ArrayList<Card> p1Hand, p2Hand, cardPile;

    @FXML
    void playHand() {
        p1ImageView.setVisible(true);
        p2ImageView.setVisible(true);

        //Does P1 have cards?
        if (p1Hand.size()==0)
            declareWinner("Player 2");
        //Does P2 have cards?
        else if (p2Hand.size()==0)
            declareWinner("Player 1");

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
        System.out.println("The winner is "+winnerName);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newGame();
    }

    private void newGame()
    {
        p1ImageView.setVisible(false);
        p2ImageView.setVisible(false);

        //Create variables for p1, p2 & card pile
        p1Hand = new ArrayList<>();
        p2Hand = new ArrayList<>();
        cardPile = new ArrayList<>();

        //create a deck of cards and shuffle the deck
        DeckOfCards deck = new DeckOfCards();
        deck.shuffle();

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
    }

}
