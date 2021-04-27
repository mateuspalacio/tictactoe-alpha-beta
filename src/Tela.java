import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tela implements ActionListener {
    boolean jogadorX;
    JFrame moldura;
    JPanel painel;
    JButton[][] quadrados;
    JButton[][] botoes;
    JButton comecaJogo;
    Quadro quadro;


    public Tela() {
        this.quadrados = new JButton[3][3];
        this.botoes = new JButton[2][2];
        this.quadro = null;
        this.jogadorX = true;
        moldura = new JFrame("Alpha Beta - IA 2021.1 - UNIFOR - Eliude e Mateus Palácio");
        moldura.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        moldura.setPreferredSize(new Dimension(690, 690));
        moldura.setLayout(null);
        this.painel = new JPanel();
        this.painel.setSize(new Dimension(680, 680));
        this.painel.setLayout(null);
        adicionarQuadroDeJogo();
        adicionarBotoes();
        moldura.add(this.painel);
        moldura.pack();
        moldura.setVisible(true);
    }

    void adicionarQuadroDeJogo() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.quadrados[i][j] = new JButton();
                this.quadrados[i][j].setBounds(i*200, j*200, 200, 200);
                this.quadrados[i][j].setBackground(Color.WHITE);
                this.quadrados[i][j].setEnabled(false);
                this.quadrados[i][j].addActionListener(this);
                this.painel.add(this.quadrados[i][j]);
            }
        }
    }

    void adicionarBotoes() {

        botoes[0][0] = new JButton("Jogar de X");
        botoes[0][1] = new JButton("Jogar de O");

        comecaJogo = new JButton("Começar!");
        comecaJogo.setBounds(0,625, 300, 25);
        comecaJogo.addActionListener(this);
        painel.add(comecaJogo);

        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 2; j++) {
                botoes[i][j].setBounds((300*j), (25*i)+600, 300, 25);
                botoes[i][j].setBackground(Color.WHITE);
                botoes[i][j].addActionListener(this);
                painel.add(botoes[i][j]);
            }
        }
    }

    void ativarQuadroDeJogo(boolean bool) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                quadrados[i][j].setEnabled(bool);
            }
        }

        if (!bool) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    quadrados[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }

    void atualizarView() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (quadro.quadro[i][j] == "x") {
                    quadrados[i][j].setText("X");
                    quadrados[i][j].setFont(new Font("Garamond", Font.PLAIN, 69));
                    quadrados[i][j].setEnabled(false);
                } else if (quadro.quadro[i][j] == "o") {
                    quadrados[i][j].setText("O");
                    quadrados[i][j].setFont(new Font("Garamond", Font.PLAIN, 69));
                    quadrados[i][j].setEnabled(false);
                } else {
                    quadrados[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }

    void vezDaIA() {
        AlphaBeta m = new AlphaBeta();
        m.minMax(quadro, Integer.MIN_VALUE, Integer.MAX_VALUE, quadro.jogador);
        atualizarView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (quadrados[i][j].equals(e.getSource())) {
                    quadrados[i][j].setEnabled(false);

                    if (jogadorX && quadro.jogador == "x") {
                        quadro.jogar(i, j);
                        vezDaIA();
                    }

                    if (!jogadorX && quadro.jogador == "o") {
                        quadro.jogar(i, j);
                        vezDaIA();
                    }


                    if (quadro.terminou) {
                        if(quadro.vencedor == "x"){
                            JOptionPane.showMessageDialog(moldura, "X VENCEU!");
                        } else if (quadro.vencedor == "o") {
                            JOptionPane.showMessageDialog(moldura, "O VENCEU!");
                        } else {
                            JOptionPane.showMessageDialog(moldura, "DEU VELHA!");
                        }

                        ativarQuadroDeJogo(false);
                    }
                }
            }
        }

        if (botoes[0][0].equals(e.getSource())) {
            jogadorX = true;
            botoes[0][0].setBackground(Color.yellow);
            botoes[0][1].setBackground(Color.blue);
        } else if (botoes[0][1].equals(e.getSource())) {
            jogadorX = false;
            botoes[0][1].setBackground(Color.yellow);
            botoes[0][0].setBackground(Color.red);
        }
            else if (comecaJogo.equals(e.getSource())) {
            quadro = new Quadro();

            if (jogadorX) {
                quadro.jogador = "x";
            }
            else {
                quadro.jogador = "o";
            }

            if (quadro.jogador == "x") {
                quadro.jogador = "o";
            }
            else {
                quadro.jogador = "x";
            }
            quadro.jogar(1, 1);
                atualizarView();


            ativarQuadroDeJogo(true);
        }
    }
}
