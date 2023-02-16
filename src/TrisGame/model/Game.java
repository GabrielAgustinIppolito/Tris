package TrisGame.model;

import java.util.Scanner;

public class Game {
   private char[][] table;
   private int turno, number, character, a, b,c, one, two, three;
   private boolean isPlaying, isWin;
   private Scanner input = new Scanner(System.in);
   private String s;
   private char player1, player2;

   //costruttore
   public Game() {
      isPlaying = true;
      isWin = false;
      a = 1;
      b = 3;
      c = 5;
      one = 1;
      two = 3;
      three = 5;
      player1 = 'x';
      player2 = 'o';
      table = new char[6][6];
      table[0][0] = '*'; table[0][1] = '1'; table[0][2] = '*'; table[0][3] = '2'; table[0][4] = '*'; table[0][5] = '3';
      table[1][0] = 'A'; table[1][1] = ' '; table[1][2] = '|'; table[1][3] = ' '; table[1][4] = '|'; table[1][5] = ' ';
      table[2][0] = '*'; table[2][1] = '-'; table[2][2] = '+'; table[2][3] = '-'; table[2][4] = '+'; table[2][5] = '-';
      table[3][0] = 'B'; table[3][1] = ' '; table[3][2] = '|'; table[3][3] = ' '; table[3][4] = '|'; table[3][5] = ' ';
      table[4][0] = '*'; table[4][1] = '-'; table[4][2] = '+'; table[4][3] = '-'; table[4][4] = '+'; table[4][5] = '-';
      table[5][0] = 'C'; table[5][1] = ' '; table[5][2] = '|'; table[5][3] = ' '; table[5][4] = '|'; table[5][5] = ' ';

   }

   public void printTable() {
      for (int i = 0; i < table.length; i++) {
         System.out.println(table[i]);
      }
   }
   public void play(){
      System.out.println("Per iniziare a giocare inserisci una lettera e un numero es.: \'a2\':");
      do{
         do{
            this.printTable();
            System.out.println("Per continuare a giocare inserisci una lettera e un numero es.: \'a2\':");
            this.s = input.nextLine().toLowerCase().replace(" ","");
         }while(s.length()>2);
         boolean setted;
         do {
            setted= setChoice(s.charAt(1), s.charAt(0));
            if(!setted){
               printTable();
               System.out.println("Riprova,non sono riuscito a settare...");
               this.s = input.nextLine().toLowerCase().replace(" ","");
            }
         } while (!setted);
         isWin = isWinCombo();//controllingWin(controlling);
         if(isWin) {
            if(isXTurn()){
               System.out.printf("Player %s WINS!!!\n",player1);
            }
            System.out.printf("Player %s WINS!!!\n",player2);
            isPlaying = false;
            printTable();
         }
         turno++;                               //posso cambiare valore turno
      }while (isPlaying && turno <9);
   }

   public boolean isWinCombo(){
      if(table[a][one] != ' ' && table[a][one] == table[a][two] && table[a][two] == table[a][three]){
        return true;// setWinCombo(firstLine);
      }
      if(table[b][one] != ' ' && table[b][one] == table[b][two] && table[b][two] == table[b][three]){
         return true;//setWinCombo(secondLine);
      }
      if(table[c][one] != ' ' && table[c][one] == table[c][two] && table[c][two] == table[c][three]){
         return true;//setWinCombo(thirdLine);
      }
      if(table[a][one] != ' ' && table[a][one] == table[b][one] && table[b][one] == table[c][one]){
         return true;//setWinCombo(firstColumn);
      }
      if(table[a][two] != ' ' && table[a][two] == table[b][two] && table[b][two] == table[c][two]){
         return true;// setWinCombo(secondColumn);
      }
      if(table[a][three] != ' ' && table[a][three] == table[b][three] && table[b][three] == table[c][three]){
         return true;// setWinCombo(thirdColumn);
      }
      if(table[a][one] != ' ' && table[a][one] == table[b][two] && table[b][two] == table[c][three]){
         return true;//setWinCombo(topToDownDiag);
      }
      if(table[c][one] != ' ' && table[c][one] == table[b][two] && table[b][two] == table[a][three]){
         return true;//setWinCombo(downToTopDiag);
      }
      return false;
   } //controlla se c'è combo vincente


   //se riesce a collocarlo lo fa qui, se non riesce ritorna false
   public boolean setChoice(char number, char character){

      switch (character){
         case 'a':
            this.character = a;
            break;
         case 'b':
            this.character = b;
            break;
         case 'c':
            this.character = c;
            break;
      }
      switch (number){
         case '1':
            this.number = one;
            break;
         case '2':
            this.number = two;
            break;
         case '3':
            this.number = three;
            break;
      }
      if (table[this.character][this.number] == ' ') {
         if (isXTurn()) { //turno pari -> turno delle x
            table[this.character][this.number] = player1;
            return true;
         } else {             //turno dispari turno dell o
            table[this.character][this.number] = player2;
            return true;
         }
      }  else {
         return false;
      }
   }
   public boolean isXTurn(){
      return (turno % 2 == 0);
   }

}
