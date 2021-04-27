import java.util.ArrayList;

public class Quadro {

    String[][] quadro;
    String jogador;
    String vencedor;
    int jogadas;
    boolean terminou;
    ArrayList<Integer> movimentosDisponiveis;
    ArrayList<Integer> jogadasJaFeitas;

    public Quadro() {
        this.quadro = new String[3][3];
        this.jogador = "x";
        this.vencedor = "vazio";
        this.terminou = false;
        this.jogadas = 0;
        this.movimentosDisponiveis = new ArrayList<Integer>();
        this.jogadasJaFeitas = new ArrayList<Integer>();


        // cria um quadro local de back end para poder ser analisado
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                this.quadro[i][j] = "vazio";
            }
        }

        for (int i=0; i<9; i++) {
            this.movimentosDisponiveis.add(i);
        }
    }



    void setJogador(String jogador) {
        this.jogador = jogador;
    }

    void jogar(int x, int y) {
        this.quadro[x][y] = this.jogador;
        this.jogadas++;

        this.movimentosDisponiveis.remove(Integer.valueOf(x * 3 + y));
        if (this.jogador == "o") {
            this.jogadasJaFeitas.add(Integer.valueOf(x * 3 + y));
        }

        this.verQuemGanhou();
        if (!this.terminou) {
            if (this.jogador == "x"){
                this.jogador = "o";
            }
            else{
                this.jogador = "x";
            }
        }
    }

    void verQuemGanhou() {
        if (this.quadro[0][0] == this.quadro[0][1] && this.quadro[0][1] == this.quadro[0][2] && this.quadro[0][0] != "vazio") {
            this.terminou = true;
            this.vencedor = this.jogador;
        }

        if (this.quadro[1][0] == this.quadro[1][1] && this.quadro[1][1] == this.quadro[1][2] && this.quadro[1][0] != "vazio") {
            this.terminou = true;
            this.vencedor = this.jogador;
        }

        if (this.quadro[2][0] == this.quadro[2][1] && this.quadro[2][1] == this.quadro[2][2] && this.quadro[2][0] != "vazio") {
            this.terminou = true;
            this.vencedor = this.jogador;
        }

        if (this.quadro[0][0] == this.quadro[1][0] && this.quadro[1][0] == this.quadro[2][0] && this.quadro[0][0] != "vazio") {
            this.terminou = true;
            this.vencedor = this.jogador;
        }

        if (this.quadro[0][1] == this.quadro[1][1] && this.quadro[1][1] == this.quadro[2][1] && this.quadro[0][1] != "vazio") {
            this.terminou = true;
            this.vencedor = this.jogador;
        }

        if (this.quadro[0][2] == this.quadro[1][2] && this.quadro[1][2] == this.quadro[2][2] && this.quadro[0][2] != "vazio") {
            this.terminou = true;
            this.vencedor = this.jogador;
        }

        if (this.quadro[0][0] == this.quadro[1][1] && this.quadro[1][1] == this.quadro[2][2] && this.quadro[0][0] != "vazio") {
            this.terminou = true;
            this.vencedor = this.jogador;
        }

        if (this.quadro[0][2] == this.quadro[1][1] && this.quadro[1][1] == this.quadro[2][0] && this.quadro[1][1] != "vazio") {
            this.terminou = true;
            this.vencedor = this.jogador;
        }

        if (this.movimentosDisponiveis.size() == 0 && this.vencedor == "vazio") {
            this.terminou = true;
        }
    }


    Quadro copiarQuadro() {
        Quadro copiaDoQuadro = new Quadro();

        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                copiaDoQuadro.quadro[i][j] = this.quadro[i][j];
            }
        }

        copiaDoQuadro.jogador = this.jogador;
        copiaDoQuadro.vencedor = this.vencedor;
        copiaDoQuadro.movimentosDisponiveis = new ArrayList<Integer>();
        copiaDoQuadro.movimentosDisponiveis.addAll(this.movimentosDisponiveis);
        copiaDoQuadro.jogadas = this.jogadas;
        copiaDoQuadro.terminou = this.terminou;

        return copiaDoQuadro;
    }
}
