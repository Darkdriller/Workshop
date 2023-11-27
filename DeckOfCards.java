import java.util.Random;

class Card {
    String suit;
    String rank;

    Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}

class Node {
    Card card;
    Node next;

    Node(Card card) {
        this.card = card;
        this.next = null;
    }
}

class LinkedList {
    Node head;

    void add(Card card) {
        if (head == null) {
            head = new Node(card);
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node(card);
        }
    }

    Card get(int index) {
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
            if (current == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return current.card;
    }

    int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}

class Player {
    LinkedList cards = new LinkedList();

    void receiveCard(Card card) {
        cards.add(card);
    }

    void sortCards() {
        for (int i = 0; i < cards.size(); i++) {
            for (int j = 0; j < cards.size() - 1; j++) {
                if (compareCards(cards.get(j), cards.get(j + 1)) > 0) {
                    swapCards(j, j + 1);
                }
            }
        }
    }

    private void swapCards(int index1, int index2) {
        Node node1 = getNode(index1);
        Node node2 = getNode(index2);
        Card temp = node1.card;
        node1.card = node2.card;
        node2.card = temp;
    }

    private Node getNode(int index) {
        Node current = cards.head;
        for (int i = 0; i < index; i++) {
            current = current.next;
            if (current == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return current;
    }

    private int compareCards(Card card1, Card card2) {
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        int rank1 = -1, rank2 = -1;
        for (int i = 0; i < ranks.length; i++) {
            if (ranks[i].equals(card1.rank)) rank1 = i;
            if (ranks[i].equals(card2.rank)) rank2 = i;
        }
        return rank1 - rank2;
    }

    void showCards() {
        Node current = cards.head;
        while (current != null) {
            System.out.println(current.card);
            current = current.next;
        }
    }
}

public class DeckOfCards {
    static String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
    static String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    static Card[] deck = new Card[52];

    public static void main(String[] args) {
        initializeDeck();
        shuffleDeck();

        Player[] players = new Player[4];
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player();
        }

        distributeCards(players);

        for (int i = 0; i < players.length; i++) {
            System.out.println("Player " + (i + 1) + "'s cards:");
            players[i].sortCards();
            players[i].showCards();
            System.out.println();
        }
    }

    static void initializeDeck() {
        int index = 0;
        for (String suit : suits) {
            for (String rank : ranks) {
                deck[index++] = new Card(suit, rank);
            }
        }
    }

    static void shuffleDeck() {
        Random rand = new Random();
        for (int i = 0; i < deck.length; i++) {
            int r = rand.nextInt(deck.length);
            Card temp = deck[i];
            deck[i] = deck[r];
            deck[r] = temp;
        }
    }

    static void distributeCards(Player[] players) {
        int cardIndex = 0;
        for (int i = 0; i < 9; i++) {  // 9 cards each
            for (Player player : players) {
                player.receiveCard(deck[cardIndex++]);
            }
        }
    }
}
