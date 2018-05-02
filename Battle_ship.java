import java.util.Arrays;
import java.util.Scanner;
import java.lang.Object;
import java.util.concurrent.ThreadLocalRandom;
public class Battle_ship {
    public static void main(String arg[]){
        int [][] map_0 = new int[10][10];
        ocean_map_0(map_0);
        int[][] map_1 = deploy_player();
        int [][] map_2 = deploy_pc(map_1);
        ocean_map(map_2);
        //Start game
        int[][] mapa = map_2;
        int barcos_propios = conteo(map_2, 1);
        int barcos_pc = conteo(map_2, 2);
        //game
        while (barcos_propios > 0 && barcos_pc > 0){
            mapa = player_turn(mapa);
            mapa = pc_turn(mapa);
            barcos_propios = conteo(mapa, 1);
            barcos_pc = conteo(mapa, 2);
            ocean_map(mapa);
        }
        if (barcos_pc == 0){
           System.out.println("Hooray! you win the battle :)");
        }
        else if (barcos_propios == 0){
            System.out.println("You lost");
        }
    }
    public static void ocean_map_0(int[][] map){
        int i;
        int j;
        System.out.println("****Welcome to battle Ships game****");
        System.out.println();
        System.out.println("Right now, the sea is empty.");
        System.out.println("  0123456789   ");
        for ( i = 0; i <map.length; i++) {
            System.out.print(i+"|");
            for (j = 0; j <map[0].length; j++){
                if (map[i][j] == 0 || map[i][j] == 2){
                    System.out.print(" ");
                }
                else if (map[i][j] ==1){
                    System.out.print("@");
                }
            }
            System.out.println("|"+i);
        }
        System.out.println("  0123456789   ");
    }

    public static void ocean_map(int[][] map){
        int i;
        int j;
        System.out.println("  0123456789   ");
        for ( i = 0; i <map.length; i++) {
            System.out.print(i+"|");
            for (j = 0; j <map[0].length; j++){
                if (map[i][j] == 0 || map[i][j] == 2 ||map[i][j] == 6){
                    System.out.print(" ");
                }
                else if (map[i][j] ==1){
                    System.out.print("@");
                }
                else if (map[i][j] == 3){
                    System.out.print("!");
                }
                else if (map[i][j] == 4){
                    System.out.print("x");
                }
                else if (map[i][j] == 5){
                    System.out.print("-");
                }
            }
            System.out.println("|"+i);
        }
        System.out.println("  0123456789   ");
        System.out.print("your ships: " + conteo(map, 1)+"| ");
        System.out.println("Computer ships: " + conteo(map, 2));
    }

    public static int[][] deploy_player(){
        int map[][] = new int[10][10];
        Scanner input = new Scanner(System.in);
        int  k =1;
        System.out.println("Deploy your ships: ");
        while (k <6) {
            System.out.println("Enter X coordinate for your " + k + ". ship: ");
            int x = input.nextInt();
            System.out.println("Enter Y coordinate for your " + k + ". ship: ");
            int y = input.nextInt();
            if (x < 0 || y < 0 || x >= 10 || y >= 10) {
                System.out.println("Invalid coordinate, try with a number between 0 and 9 ");
            } else if (map[x][y] == 1 ||map[x][y] == 2) {

                System.out.println("There is a ship in this position, try with other position");
            } else {
                map[x][y] = 1;
                k += 1;
            }
        }
         return map;

    }

    public static int[][] deploy_pc(int[][] map){
        int[][] new_map = map;
        int k = 1;
        System.out.println("Computer is deploying ships");
        while (k < 6){
            int x = value_rn();
            int y = value_rn();
            if (new_map[x][y] == 0){
                new_map[x][y] = 2;
                System.out.println(k + ". ship DEPLOYED");
                k += 1;
            }
        }
        System.out.println("----------------------------------------");
        return new_map;
    }


    public static int value_rn(){
        int rn = ThreadLocalRandom.current().nextInt(0, 10);
        return rn;
    }

    public static int[][] player_turn(int[][] map){
        int[][] new_map = map;
        Scanner input = new Scanner(System.in);
        int k = 1;
        System.out.println("YOUR TURN");
        while (k !=0 ){
            System.out.println("Enter X coordinate: ");
            int x = input.nextInt();
            System.out.println("Enter Y coordinate: ");
            int y = input.nextInt();
            // || new_map[x][y] == 3 || new_map[x][y] == 4 || new_map[x][y] == 5|| new_map[x][y] == 6
            if ( x <0 || y<0 || y>=10 || x >=10 || new_map[x][y] == 3 || new_map[x][y] == 4 || new_map[x][y] == 5|| new_map[x][y] == 6 ){
                System.out.println("invalid attack, try with other ");
            }
            else if (new_map[x][y] == 1){
                System.out.println("Oh no, you sunk your own ship :(");
                new_map[x][y] = 4;
                k = 0;
            }
            else if (new_map[x][y] == 2){
                System.out.println("Boom! you sunk the ship!");
                new_map[x][y] = 3;
                k = 0;
            }
            else {
                System.out.println("You missed");
                new_map[x][y] = 5;
                k=0;
            }
        }
        return new_map;
    }

    public static int[][] pc_turn(int[][] map){
        int[][] new_map = map;
        int k = 1;
        System.out.println("COMPUTER'S TURN");
        int x = value_rn();
        int y = value_rn();
        while (k != 0){
            if (new_map[x][y] == 0 ||new_map[x][y] == 3 || new_map[x][y] == 4 || new_map[x][y] == 5 ||new_map[x][y] == 6 ){
                System.out.println("Computer missed");
                new_map[x][y] = 5;
                k = 0;
            }
            else if (new_map[x][y] == 1){
                System.out.println("The Computer sunk one of your ships!");
                new_map[x][y] = 4;
                k = 0;
            }
            else if(new_map[x][y] == 2){
                System.out.println("The Computer sunk one of its own ships");
                new_map[x][y] = 3;
                k = 0;
            }
        }
        return new_map;
    }

    public static int conteo(int[][] map, int a){
        int cont = 0;
        int i;
        int j;
        for (i = 0; i <map.length;i++){
            for (j = 0; j < map[0].length;j++){
                if (map[i][j] == a){
                    cont += 1;
                }
            }
        }
        return cont;
    }


}
