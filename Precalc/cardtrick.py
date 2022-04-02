import random
num_cards = 3
games_to_play =  100000
deck = []

def fill_deck(deck,num_cards):
    x = 1
    while x <= num_cards:
        deck.append(x)
        x = x + 1

def get_random_card(deck):
    card_num = random.randint(0,len(deck)-1)
    card = deck[card_num]
    deck.remove(card)
    return card

def play_game():
    fill_deck(deck,num_cards)
    called_out_number = 1
    while len(deck)>0:
        card = get_random_card(deck)
        print("You chose card:",card)
        if card == called_out_number:
            #print("You won!")
            return True
        called_out_number = called_out_number + 1
    #print("You lost!")
    return False

wins = 0
losses = 0
game_num = 0
while game_num < games_to_play:
    if play_game():
        wins = wins + 1
    else:
        losses = losses + 1
    game_num = game_num + 1

print("You won",wins,"games.")
print("You lost",losses,"games.")
print("You won",wins*100/(wins+losses),"% of the time games.")
