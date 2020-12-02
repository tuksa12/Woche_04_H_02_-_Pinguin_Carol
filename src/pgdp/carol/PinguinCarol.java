package pgdp.carol;

import java.lang.reflect.Array;

import static pgdp.MiniJava.*;

public class PinguinCarol {

public static void main(String[] args) {

	//Declaration
	int x = 0;
	int y = 0;
	//Firs sequence of inputs
	int breit = readInt("Spielfeldbreite eingeben:");
	int hohe = readInt("Spielfeldhöhe eingeben:");
	if (breit < 1 || hohe < 1) {
		write("Spielfeldbreite und -höhe müssen größer als null sein.");
	} else {
		int[][] Spielfeld = new int[breit][hohe];

		//Construction of the playing field
		while(x<(breit)){
			Spielfeld [x][y] = randomInt(-1,9);
			if (y<(hohe-1)){
				y++;
			} else{
				y =0;
				x++;
			}
		}
		printPlayground(Spielfeld);

		//Second sequence of inputs
		int pos_x = readInt("Startposition x:");
		int pos_y = readInt("Startposition y:");
		int blick = readInt("Blickrichtung zu Beginn:");
		int eis = readInt("Eisblöcke zu Beginn:");

		//Error message and end of the program
		if (pos_x < 0 || pos_x >= breit || pos_y < 0 || pos_y >= hohe || blick < 0 || blick >3 || eis < 0 || eis > 10){
			write("Ungültige Startwerte.");
		}else{//Input values are valid, the simulation starts
			while(true){
				char command = readChar("Instruktion eingeben:");
				//Functions of each letter
				if (command == 'e'){//End
					break; }
				else if (command == 'a'){//Display
					printPlayground(Spielfeld,pos_x,pos_y,blick,eis);
				}
				else if (command == 'r'){//Right
					blick--;
					if (blick==-1){
						blick = 3;}
				}
				else if (command == 'l'){//Left
					blick++;
					if (blick==4){
						blick = 0;}
				}
				else if (command == 's'){//Move forward
						if (blick == 0) {
							pos_x++;
							if (pos_x >= breit) {
								pos_x--;
								write("Carol kann das Spielfeld nicht verlassen.");}
							else if (Spielfeld[pos_x][pos_y] > (Spielfeld[(pos_x - 1)][pos_y] + 1)) {
								pos_x--;
								write("Carol kann nicht auf das nächste Feld gehen da der Höhenunterschied zu groß ist.");
							}
						} else if (blick == 2) {
							pos_x--;
							if (pos_x < 0){
								pos_x++;
								write("Carol kann das Spielfeld nicht verlassen.");}
							else if (Spielfeld[pos_x][pos_y] > (Spielfeld[(pos_x + 1)][pos_y] + 1)) {
								pos_x++;
								write("Carol kann nicht auf das nächste Feld gehen da der Höhenunterschied zu groß ist.");
							}
						} else if (blick == 1) {
							pos_y++;
							if (pos_y >= hohe){
								pos_y--;
								write("Carol kann das Spielfeld nicht verlassen.");}
							else if (Spielfeld[pos_x][pos_y] > (Spielfeld[(pos_x)][pos_y - 1] + 1)) {
								pos_y--;
								write("Carol kann nicht auf das nächste Feld gehen da der Höhenunterschied zu groß ist.");
							}
						} else if (blick == 3) {
							pos_y--;
							if (pos_y < 0){
								pos_y++;
								write("Carol kann das Spielfeld nicht verlassen.");}
							else if (Spielfeld[pos_x][pos_y] > (Spielfeld[(pos_x)][pos_y + 1] + 1)) {
								pos_y++;
								write("Carol kann nicht auf das nächste Feld gehen da der Höhenunterschied zu groß ist.");
							}
						}
					}

				else if (command == 'n') {//Grab ice
					if (eis == 10) {
						write("Carol kann keinen Eisblock nehmen, sie trägt schon zehn.");
					} else if (Spielfeld[pos_x][pos_y] == -1)
						write("Carol kann im Wasser keine Eisblöcke nehmen.");
					else {
						if (blick == 0) {
							if ((pos_x+1) >= breit){
								write("Carol kann keine Eisblöcke außerhalb des Spielfelds nehmen.");
							}else if (Spielfeld[pos_x + 1][pos_y] == -1){
								write("Carol kann keinen Eisblock nehmen, es sind keine mehr vorhanden.");
							}else{
								Spielfeld[pos_x + 1][pos_y]--;
								eis ++;
							}
						} else if (blick == 2) {
							if ((pos_x - 1) < 0) {
								write("Carol kann keine Eisblöcke außerhalb des Spielfelds nehmen.");
							} else if (Spielfeld[pos_x - 1][pos_y] == -1) {
								write("Carol kann keinen Eisblock nehmen, es sind keine mehr vorhanden.");
							} else {
								Spielfeld[pos_x - 1][pos_y]--;
								eis++;
							}
						}else if (blick == 1) {
							if ((pos_y+1) >= hohe) {
								write("Carol kann keine Eisblöcke außerhalb des Spielfelds nehmen.");
							} else if (Spielfeld[pos_x][pos_y+1] == -1) {
								write("Carol kann keinen Eisblock nehmen, es sind keine mehr vorhanden.");
							} else {
								Spielfeld[pos_x][pos_y+1]--;
								eis++;
							}
						} else if (blick == 3) {
							if ((pos_y-1) >= hohe || (pos_y - 1) < 0) {
								write("Carol kann keine Eisblöcke außerhalb des Spielfelds nehmen.");
							} else if (Spielfeld[pos_x][pos_y-1] == -1) {
								write("Carol kann keinen Eisblock nehmen, es sind keine mehr vorhanden.");
							} else {
								Spielfeld[pos_x][pos_y-1]--;
								eis++;
							}
						}

					}
				}
				else if (command == 'p'){//Place ice
					if (eis == 0) {
						write("Carol kann keinen Eisblock legen, weil sie keine trägt.");
					} else if (Spielfeld[pos_x][pos_y] == -1)
						write("Carol kann im Wasser keine Eisblöcke legen.");
					else {
						if (blick == 0) {
							if ((pos_x+1) >= breit ){
								write("Carol kann keine Eisblöcke außerhalb des Spielfelds legen.");
							}else if (Spielfeld[pos_x + 1][pos_y] >= 9){
								write("Carol kann keinen Eisblock legen, es liegen schon zehn Eisblöcke auf dem Feld.");
							}else{
								Spielfeld[pos_x + 1][pos_y]++;
								eis --;
							}
						} else if (blick == 2) {
							if ((pos_x - 1) < 0) {
								write("Carol kann keine Eisblöcke außerhalb des Spielfelds legen.");
							} else if (Spielfeld[pos_x - 1][pos_y] >= 9) {
								write("Carol kann keinen Eisblock legen, es liegen schon zehn Eisblöcke auf dem Feld.");
							} else {
								Spielfeld[pos_x - 1][pos_y]++;
								eis--;
							}
						}else if (blick == 1) {
							if ((pos_y+1) >= hohe) {
								write("Carol kann keine Eisblöcke außerhalb des Spielfelds legen.");
							} else if (Spielfeld[pos_x][pos_y+1] >= 9) {
								write("Carol kann keinen Eisblock legen, es liegen schon zehn Eisblöcke auf dem Feld.");
							} else {
								Spielfeld[pos_x][pos_y+1]++;
								eis--;
							}
						} else if (blick == 3) {
							if ((pos_y-1) >= hohe || (pos_y - 1) < 0) {
								write("Carol kann keine Eisblöcke außerhalb des Spielfelds legen.");
							} else if (Spielfeld[pos_x][pos_y-1] >= 9) {
								write("Carol kann keinen Eisblock legen, es liegen schon zehn Eisblöcke auf dem Feld.");
							} else {
								Spielfeld[pos_x][pos_y-1]++;
								eis--;
							}
						}

					}

				}else{//Unknown command/error message
					write("Unbekannte Anweisung!");
				}
			}
		}
		}
	}
}
