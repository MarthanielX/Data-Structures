Assignment Description by Dave Musicant: 

For this assignment, we are going to encrypt a string of text using a deck of cards. This technique is considerably
harder to break than some basic encryption techniques (such as the Caeser Cipher, yet it’s still relatively
strightforward. We’ll be assuming that we have just two suits (say, hearts and spades) from a deck of cards,
plus the two jokers, just to keep things simple. Further, let’s assume that the values of the 26 suit cards are
1 to 26 (Ace through King of hearts, followed by Ace through King of spades), that the “A” joker is 27, and
that the “B” joker is 28. Thus, 15 represents the 2 of spades. Now that you’ve got the idea, note that because
we are doing this in a computer, note that the actual card names are irrelevant; really, we just need to track
a list of numbers from 1 through 28. Well, we do need to know which ones are considered jokers (27 and 28).
The main part of this technique is the generation of the keystream values. (They will be used to encrypt or
decrypt our messages.) Here are the steps used in our version of this algorithm, assuming that we start with
a list of the values from 1–28 as described above:
1. Find the A joker (27). Exchange it with the card beneath (after) it in the deck, to move the card down
the deck by one position. (What if the joker is the last card in the deck? Imagine that the deck of cards
is continuous; the card following the bottom card is the top card of the deck, and you’d just exchange
them.)
2. Find the B joker (28). Move it two cards down by performing two exchanges.
3. Swap the cards above the first joker (the one closest to the top of the deck) with the cards below the
second joker. This is called a triple cut.
4. Take the bottom card from the deck. Count down from the top card by a quantity of cards equal to the
value of that bottom card. (If the bottom card is a joker, let its value be 27, regardless of which joker it
is.) Take that group of cards and move them to the bottom of the deck. Return the bottom card to the
bottom of the deck.
5. (Last step!) Look at the top card’s value (which is again 1-27, as it was in the previous step). Put the
card back on top of the deck. Count down the deck by that many cards, starting with the top card.
Record the value of the NEXT card after that in the deck, but don’t remove it from the deck. If that
next card happens to be a joker, don’t record anything. Leave the deck the way it is, and start again
from the first step, repeating until that next card is not a joker.
The value that you recorded in the last step is one value of the keystream, and will be in the range 1 – 26,
inclusive (to match with the number of letters in the alphabet). To generate another value, we take the deck
as it is after the last step and repeat the algorithm. We need to generate as many keystream values as there
are letters in the message being encrypted or decrypted.
